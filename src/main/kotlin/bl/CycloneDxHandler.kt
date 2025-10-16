package bl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import model.CycloneDxSbom
import model.NormalizedSbom
import java.io.File

class CycloneDxHandler : SbomHandler {
    override fun supports(json: String): Boolean = json.contains("\"bomFormat\"") && json.contains("CycloneDX")

    override fun parseAndNormalize(file: File): NormalizedSbom {
        val sbom: CycloneDxSbom = mapper.readValue(file)

        return sbom.normalized(file)
    }


    companion object {
        private val mapper = jacksonObjectMapper()

        fun CycloneDxSbom.normalized(file: File): NormalizedSbom {
            val components = this.components.map {
                NormalizedSbom.Component(it.name, it.version, listOfNotNull(it.license))
            }
            return NormalizedSbom(this.metadata.component.name, file.readText(), components)
        }
    }
}
