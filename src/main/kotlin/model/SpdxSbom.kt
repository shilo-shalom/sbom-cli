package model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SpdxSbom(
    val spdxVersion: String,
    val name: String,
    val packages: List<Package>
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Package(
        val name: String,
        val versionInfo: String?,
        val licenseDeclared: String?
    )
}
