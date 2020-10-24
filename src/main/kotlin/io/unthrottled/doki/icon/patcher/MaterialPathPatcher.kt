package io.unthrottled.doki.icon.patcher

import com.intellij.openapi.util.IconPathPatcher
import io.unthrottled.doki.icon.provider.MaterialIconProvider.Companion.MATERIAL_DIRECTORY

class MaterialPathPatcher(
  private val patcherDefinition: PathPatcherDefinition
) : IconPathPatcher() {

  private val cache: MutableMap<String, String?> = HashMap(512)

  override fun getContextClassLoader(path: String, originalClassLoader: ClassLoader?): ClassLoader? =
    javaClass.classLoader

  override fun patchPath(path: String, classLoader: ClassLoader?): String? {
    return if (cache.containsKey(path)) {
      cache[path]
    } else {
      val adjustedPath = path.replace(patcherDefinition.pathToRemove, "")
      val fullIconPath = "$MATERIAL_DIRECTORY${patcherDefinition.pathToAppend}$adjustedPath"
      val patchedIconPath = if (javaClass.getResource(fullIconPath) != null) {
        fullIconPath
      } else {
        null
      }
      cache[path] = patchedIconPath
      patchedIconPath
    }
  }
}
