package dal.repositories

import dal.schema.*
import model.Sbom
import org.jetbrains.exposed.sql.*

class QueryRepository {
    fun findByComponent(name: String, version: String?): List<Sbom> {
        val join = Sboms
            .innerJoin(SbomComponents)
            .innerJoin(Components)
        val query = join.select(Sboms.id, Sboms.name)
            .where {
                (Components.name eq name) and
                        (if (version != null) Components.version eq version else Op.TRUE)
            }.withDistinct()
        return query.map { Sbom(it[Sboms.id], it[Sboms.name]) }
    }

    fun findByLicense(license: String): List<Sbom> {
        val join = Sboms
            .innerJoin(SbomComponents)
            .innerJoin(Components)
            .innerJoin(ComponentLicenses)
            .innerJoin(Licenses)
        val query = join.select(Sboms.id, Sboms.name)
            .where { Licenses.name eq license }
            .withDistinct()
        return query.map { Sbom(it[Sboms.id], it[Sboms.name]) }
    }
}
