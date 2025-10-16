package dal.repositories

import dal.schema.SbomComponents
import org.jetbrains.exposed.sql.insertIgnore

class SbomComponentRepository {
    fun link(sbomId: Int, compId: Int) {
        SbomComponents.insertIgnore {
            it[SbomComponents.sbomId] = sbomId
            it[SbomComponents.componentId] = compId
        }
    }
}
