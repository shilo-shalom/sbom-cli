package bl

import dal.DatabaseConfig
import dal.repositories.QueryRepository
import model.Sbom

class QueryService {
    private val repo = QueryRepository()

    fun findByComponent(name: String, version: String?): List<Sbom> =
        DatabaseConfig.tx { repo.findByComponent(name, version) }

    fun findByLicense(license: String): List<Sbom> =
        DatabaseConfig.tx { repo.findByLicense(license) }
}
