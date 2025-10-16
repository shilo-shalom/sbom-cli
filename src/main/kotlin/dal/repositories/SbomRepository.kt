package dal.repositories

import dal.schema.Sboms
import model.NormalizedSbom
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class SbomRepository {
    fun upsert(sbom: NormalizedSbom): Int {
        val existing = Sboms.selectAll().where { Sboms.name eq sbom.name }.singleOrNull()
        return if (existing != null) existing[Sboms.id]
        else Sboms.insert {
            it[name] = sbom.name
            it[rawContent] = sbom.rawContent
        }[Sboms.id]
    }
}
