package cli

import bl.IngestService
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import java.io.File

class IngestCommand : CliktCommand(name = "ingest", help = "Ingest an SBOM file into the database") {
    private val sbomFile: String by argument("sbom-file", help = "Path to SBOM file (CycloneDX or SPDX)")

    override fun run() {
        val file = File(sbomFile)
        if (!file.exists()) {
            echo("Error: file not found: ${file.absolutePath}")
            return
        }

        val service = IngestService()
        service.ingest(file)
        echo("✅ Ingested ${file.name}")
    }
}
