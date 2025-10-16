package dal.repositories

import dal.schema.Components
import model.NormalizedSbom
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.selectAll

class ComponentRepository {
    fun upsert(c: NormalizedSbom.Component): Int {
        val existing =
            Components.selectAll().where { (Components.name eq c.name) and (Components.version eq c.version) }.singleOrNull()
        return existing?.get(Components.id)
            ?: Components.insert {
                it[name] = c.name
                it[version] = c.version
            }.get(Components.id)
    }
}
