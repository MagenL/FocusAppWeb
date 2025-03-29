package magmagen.wasm.focus_lib.presentation.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.browser.document
import magmagen.wasm.focus_lib.presentation.html.HtmlView
import magmagen.wasm.focus_lib.presentation.r.dictionary
import magmagen.wasm.focus_lib.presentation.shared_components.AppText
import magmagen.wasm.focus_lib.presentation.theme.AppTheme
import org.w3c.dom.HTMLIFrameElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.events.Event

class HomePage : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.rememberNavigatorScreenModel { HomePageModel() }
//        val model = navigator.koinNavigatorScreenModel<HomePageModel>()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.normalSpace)
        ) {
            item {
                AppText(
                    text = dictionary.screens.homePage.introTitle,
                    style = AppTheme.typography.titles
                )
            }
            item {
                AppText(
                    text = dictionary.screens.homePage.introSubTitle,
                    style = AppTheme.typography.subTitles
                )
            }
            item {
                AppText(
                    text = dictionary.screens.homePage.introBody,
                )
            }
            item {
                AppText(
                    text = dictionary.screens.homePage.overViewTitle,
                    style = AppTheme.typography.titles
                )
            }
            item {
                AppText(
                    text = dictionary.screens.homePage.overViewBody,
                )
            }
            item {
                AppText(
                    modifier = Modifier.padding(start = AppTheme.dimensions.minSpace),
                    text = dictionary.screens.homePage.overViewBullet1,
                )
            }
            item {
                AppText(
                    modifier = Modifier.padding(start = AppTheme.dimensions.normalSpace),
                    text = dictionary.screens.homePage.overViewBullet2,
                )
            }
            item {
                AppText(
                    modifier = Modifier.padding(start = AppTheme.dimensions.normalSpace),
                    text = dictionary.screens.homePage.overViewBullet3,
                )
            }
            item {
                AppText(
                    modifier = Modifier.padding(start = AppTheme.dimensions.normalSpace),
                    text = dictionary.screens.homePage.overViewBullet4,
                )
            }
            item {
                document.createElement("video") as HTMLVideoElement
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    HtmlView(
                        modifier = Modifier
                            .height(400.dp)
                            .aspectRatio(16/9f)
                            .padding(32.dp),
                        factory = {
                            (document.createElement("video") as HTMLVideoElement).apply {
                                this.autoplay = true
                                this.loop = true
                                this.src = viewModel.videoUrl
                                this.muted = true
                            }
                        }
                    )

                }
            }
        }
    }
}
