package bl

import dal.repositories.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

class IngestService {
    private val handlers = listOf(CycloneDxHandler(), SpdxHandler())

    fun ingest(file: File) {
        val json = file.readText()
        val handler = handlers.find { it.supports(json) }
            ?: error("Unsupported SBOM format")

        val normalized = handler.parseAndNormalize(file)
        transaction {
            val sbomRepo = SbomRepository()
            val compRepo = ComponentRepository()
            val licRepo = LicenseRepository()
            val sbomCompRepo = SbomComponentRepository()
            val compLicRepo = ComponentLicenseRepository()

            val sbomId = sbomRepo.upsert(normalized)
            normalized.components.forEach { comp ->
                val compId = compRepo.upsert(comp)
                sbomCompRepo.link(sbomId, compId)
                comp.licenses.forEach { lic ->
                    val licId = licRepo.upsert(lic)
                    compLicRepo.link(compId, licId)
                }
            }
        }
    }
}
