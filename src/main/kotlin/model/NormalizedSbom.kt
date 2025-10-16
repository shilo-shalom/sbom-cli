package model

data class NormalizedSbom(
    val name: String,
    val rawContent: String,
    val components: List<Component>
) {
    data class Component(
        val name: String,
        val version: String,
        val licenses: List<String>
    )
}
