package magmagen.wasm.focus_lib.presentation.screens.homepage

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.browser.document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLVideoElement

class HomePageModel: ScreenModel {

    val videoUrl = "https://raw.githubusercontent.com/MagenL/FocusTV/0cb51c82116c1bd2bb5650b51f92cd5809ed53b9/videos/initial_video.mp4"

    suspend fun a() {
        screenModelScope.launch(Dispatchers.Default) {

        }
    }
}