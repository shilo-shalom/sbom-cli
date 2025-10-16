package bl

import java.io.File
import model.NormalizedSbom

interface SbomHandler {
    fun supports(json: String): Boolean
    fun parseAndNormalize(file: File): NormalizedSbom
}
