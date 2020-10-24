package io.unthrottled.doki.schema

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider
import com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory
import com.jetbrains.jsonSchema.extension.SchemaType
import java.util.Collections

class DokiJetbrainsThemeJsonSchemaProvider :
  JsonSchemaProviderFactory {
  companion object {
    const val THEME_SCHEMA = "/theme-schema/jetbrains.theme.schema.json"
  }

  override fun getProviders(project: Project): MutableList<JsonSchemaFileProvider> =
    Collections.singletonList(
      object :
        JsonSchemaFileProvider {
        override fun getName(): String = "Doki Jetbrains Templates"

        override fun isAvailable(file: VirtualFile): Boolean =
          StringUtil.endsWithIgnoreCase(
            file.name,
            ".jetbrains.definition.json"
          )

        override fun getSchemaFile(): VirtualFile? =
          VfsUtil.findFileByURL(javaClass.getResource(THEME_SCHEMA))

        override fun getSchemaType(): SchemaType =
          SchemaType.embeddedSchema
      }
    )
}
