package dal.repositories

import dal.schema.ComponentLicenses
import org.jetbrains.exposed.sql.insertIgnore

class ComponentLicenseRepository {
    fun link(compId: Int, licId: Int) {
        ComponentLicenses.insertIgnore {
            it[componentId] = compId
            it[licenseId] = licId
        }
    }
}
