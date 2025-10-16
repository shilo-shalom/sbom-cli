package dal.repositories

import dal.schema.Licenses
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class LicenseRepository {
    fun upsert(name: String): Int {
        val existing = Licenses.selectAll().where { Licenses.name eq name }.singleOrNull()
        return existing?.get(Licenses.id)
            ?: Licenses.insert { it[Licenses.name] = name }.get(Licenses.id)
    }
}
