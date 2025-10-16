package bl

import dal.DatabaseConfig
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File

class IngestAndQueryTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            Database.connect("jdbc:sqlite::memory:", driver = "org.sqlite.JDBC")
            DatabaseConfig.init()
        }
    }

    @Test
    fun `ingest and query CycloneDX`() {
        val file = File.createTempFile("sbom", ".json")
        file.writeText("""
            {
              "bomFormat": "CycloneDX",
              "specVersion": "1.6",
              "metadata": {"component": {"name": "example-app", "version": "1.0", "license": "MIT"}},
              "components": [{"name": "express", "version": "4.18.2", "license": "MIT"}]
            }
        """.trimIndent())

        val ingest = IngestService()
        ingest.ingest(file)

        val query = QueryService()
        val results = query.findByComponent("express", "4.18.2")
        assertTrue(results.isNotEmpty())
    }
}
