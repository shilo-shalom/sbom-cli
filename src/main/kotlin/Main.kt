import cli.IngestCommand
import cli.QueryCommand
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import dal.DatabaseConfig

fun main(args: Array<String>) {
    DatabaseConfig.init()
    SbomCli().subcommands(IngestCommand(), QueryCommand()).main(args)
}

class SbomCli : CliktCommand(name = "sbom-cli", help = "SBOM ingestion and query CLI") {
    override fun run() = Unit
}
