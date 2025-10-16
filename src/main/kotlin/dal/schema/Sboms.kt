package dal.schema

import org.jetbrains.exposed.sql.Table

object Sboms : Table("sbom") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    val rawContent = text("raw_content")
    override val primaryKey = PrimaryKey(id)
}

object Components : Table("component") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    val version = text("version")
    override val primaryKey = PrimaryKey(id)
    init {
        index(true, name, version)
        index(false, name)
    }
}

object Licenses : Table("license") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    override val primaryKey = PrimaryKey(id)
    init {
        index(true, name)
    }
}

object ComponentLicenses : Table("component_license") {
    val componentId = reference("component_id", Components.id)
    val licenseId = reference("license_id", Licenses.id)
    override val primaryKey = PrimaryKey(componentId, licenseId)
    init {
        index(false, componentId)
        index(false, licenseId)
    }
}

object SbomComponents : Table("sbom_component") {
    val sbomId = reference("sbom_id", Sboms.id)
    val componentId = reference("component_id", Components.id)
    override val primaryKey = PrimaryKey(sbomId, componentId)
    init {
        index(false, componentId)
        index(false, sbomId)
    }
}
