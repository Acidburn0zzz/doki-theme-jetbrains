package io.unthrottled.doki.stickers

import com.intellij.ide.util.PropertiesComponent
import io.unthrottled.doki.util.toOptional
import java.util.Optional

object CustomStickerService {
  private const val CUSTOM_STICKER_PROPERTY = "io.unthrottled.doki.theme.custom-sticker"
  fun setCustomStickerPath(path: String) {
    PropertiesComponent.getInstance().setValue(
      CUSTOM_STICKER_PROPERTY,
      path
    )
  }

  fun getCustomStickerPath(): Optional<String> =
    PropertiesComponent.getInstance().getValue(CUSTOM_STICKER_PROPERTY)
      .toOptional()
      .filter { it.isNotBlank() }

  fun getCustomStickerUrl(): Optional<String> =
    getCustomStickerPath()
      .map { "file://${it.replace('\\', '/')}" }
}
