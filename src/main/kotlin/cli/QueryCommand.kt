package cli

import bl.QueryService
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option

class QueryCommand : CliktCommand(name = "query", help = "Query SBOMs by component or license") {
    private val component: String? by option("--component", help = "Component name")
    private val version: String? by option("--version", help = "Component version")
    private val license: String? by option("--license", help = "License name or SPDX ID")

    override fun run() {
        val queryService = QueryService()

        val results = when {
            component != null -> queryService.findByComponent(component!!, version)
            license != null -> queryService.findByLicense(license!!)
            else -> {
                echo("Please specify --component or --license")
                return
            }
        }

        if (results.isEmpty()) {
            echo("No matching SBOMs found.")
        } else {
            results.forEach { echo("${it.id} | ${it.name}") }
            echo("Found ${results.size} SBOM(s).")
        }
    }
}
