package bl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import model.NormalizedSbom
import model.SpdxSbom
import java.io.File

class SpdxHandler : SbomHandler {
    override fun supports(json: String): Boolean = json.contains("\"spdxVersion\"")

    override fun parseAndNormalize(file: File): NormalizedSbom {
        val sbom: SpdxSbom = mapper.readValue(file)

        return sbom.normalized(file)
    }

    companion object {
        private val mapper = jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        fun SpdxSbom.normalized(file: File): NormalizedSbom {
            val components = this.packages.map {
                NormalizedSbom.Component(it.name, it.versionInfo ?: "N/A", listOfNotNull(it.licenseDeclared))
            }
            return NormalizedSbom(this.name, file.readText(), components)
        }
    }
}
