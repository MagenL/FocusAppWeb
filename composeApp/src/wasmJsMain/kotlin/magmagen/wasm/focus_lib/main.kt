package magmagen.wasm.focus_lib

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import cafe.adriel.voyager.navigator.Navigator
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import kotlinx.browser.document
import kotlinx.browser.window
import magmagen.wasm.focus_lib.presentation.menus.side.NavigationDrawer
import magmagen.wasm.focus_lib.presentation.menus.top.TopAppBar
import magmagen.wasm.focus_lib.presentation.screens.homepage.HomePage
import magmagen.wasm.focus_lib.presentation.theme.AppTheme
import magmagen.wasm.focus_lib.presentation.theme.isUserPreferDarkTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    document.body!!.removeAttribute("tabIndex")
    ComposeViewport(document.body!!) {
        var isDarkTheme by remember { mutableStateOf(isUserPreferDarkTheme) }
        AppTheme(
            userTheme = isDarkTheme,
            onBrowserDarkThemeChange = {
                isDarkTheme = it
                window.localStorage.setItem("theme", it.toString())
            }
        ) {
            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .crossfade(true)
                    .logger(DebugLogger())
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build()
            }
            val drawerState = remember { mutableStateOf(false) }
            val animatedBackgroundColor = animateAppBackground(drawerState)
            Navigator(HomePage()) { navigator ->
                Column(
                    modifier = Modifier
                        .drawWithContent {
                            drawContent()
                            drawRect(color = Color.Black, alpha = animatedBackgroundColor.value)
                        }
                        .clickable(enabled = drawerState.value, onClick = {
                            drawerState.value = false
                        })
                ) {
                    TopAppBar(
                        isLargeScreen = AppTheme.isLargeScreen,
                        isDrawerOpen = drawerState,
                        openDrawer = {
                            drawerState.value = true
                        },
                        onSwitchTheme = {
                            println("switch theme to - ${!isDarkTheme} ")
                            isDarkTheme = !isDarkTheme
                            window.localStorage.setItem("theme", isDarkTheme.toString())
                        }
                    )
                    AppContent(
                        navigator = navigator,
                        drawerState = drawerState,
                    )
                }
                NavigationDrawer(
                    isDrawerOpen = drawerState,
                )
            }

        }
    }
}

@Composable
private fun animateAppBackground(drawerState: MutableState<Boolean>): Animatable<Float, AnimationVector1D> {
    val animatedBackgroundColor = remember { Animatable(0f) }
    LaunchedEffect(drawerState.value) {
        if (drawerState.value) {
            animatedBackgroundColor.animateTo(0.5f, tween(300))
        } else {
            animatedBackgroundColor.animateTo(0f, tween(200))
        }
    }
    return animatedBackgroundColor
}

@Composable
private fun AppContent(
    navigator: Navigator,
    drawerState: State<Boolean>,
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            Modifier.fillMaxWidth(
                if (drawerState.value || AppTheme.isLargeScreen.value)
                    AppTheme.dimensions.sideMenuBigScreenWidthPercent
                else 0f
            )
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .animateContentSize(animationSpec = tween(300))
        ) {
            navigator.lastItem.Content()
        }
    }
}