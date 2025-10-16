package dal

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import dal.schema.*
import java.io.File

object DatabaseConfig {
    lateinit var db: Database
        private set

    fun init() {
        val url = System.getenv("SBOM_JDBC_URL")
            ?: ("jdbc:sqlite:" + File("sbom.db").absolutePath)
        db = Database.connect(url, driver = "org.sqlite.JDBC")

        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                Sboms, Components, Licenses, ComponentLicenses, SbomComponents
            )
        }
    }

    inline fun <T> tx(crossinline block: () -> T): T = transaction(db) { block() }

}
