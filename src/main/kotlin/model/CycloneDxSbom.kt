package model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CycloneDxSbom(
    val bomFormat: String,
    val specVersion: String,
    val metadata: Metadata,
    val components: List<Component>
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Metadata(val component: Component)
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Component(
        val name: String,
        val version: String,
        val license: String?
    )
}
