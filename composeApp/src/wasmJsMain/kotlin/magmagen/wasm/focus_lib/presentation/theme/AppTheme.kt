package magmagen.wasm.focus_lib.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element

@Composable
fun AppTheme(
    userTheme: Boolean,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    onBrowserDarkThemeChange: (Boolean) -> Unit = {},
    content: @Composable () -> Unit
) {
    val isBrowserDarkTheme = isBrowserDarkTheme
    val initialCompositionFinished = remember { mutableStateOf(false) }
    LaunchedEffect(isBrowserDarkTheme.value) {
        if (!initialCompositionFinished.value) {
            initialCompositionFinished.value = true
            return@LaunchedEffect
        }
        onBrowserDarkThemeChange(isBrowserDarkTheme.value)
    }
    val scheme = getScheme(userTheme).animated()
    val isLargeScreen = isLargeScreen()
    CompositionLocalProvider(
        LocalColorScheme provides scheme,
        LocalTypography provides Typography(),
        LocalLayoutDirection provides layoutDirection,
        LocalAppTheme provides userTheme,
        LocalLayerContainer provides document.getElementById("components")!!,
        LocalScreenSize provides isLargeScreen,
        LocalDimensions provides Dimensions(isLargeScreen.value)
    ) {
        MaterialTheme {
            content()
        }
    }
}

object AppTheme {
    val colorScheme: ColorScheme
        @Composable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        get() = LocalTypography.current

    val dimensions: Dimensions
        @Composable
        get() = LocalDimensions.current

    val isDarkTheme: Boolean
        @Composable
        get() = LocalAppTheme.current

    val isLargeScreen: State<Boolean>
        @Composable
        get() = LocalScreenSize.current
}

internal val LocalColorScheme = staticCompositionLocalOf {
    lightTheme()
}

internal val LocalTypography = staticCompositionLocalOf {
    Typography()
}

internal val LocalDimensions = staticCompositionLocalOf<Dimensions> {
    error("implementation must be provided")
//    Dimensions(true)
}

private val LocalAppTheme: ProvidableCompositionLocal<Boolean> = staticCompositionLocalOf {
    error("implementation must be provided")
}

val LocalLayerContainer = staticCompositionLocalOf<Element> {
    error("implementation must be provided")
}

val LocalScreenSize = staticCompositionLocalOf<State<Boolean>> {
    error("must be implemented")
}

@Composable
private fun isLargeScreen(): State<Boolean> {
    val windowWidth = remember { mutableStateOf(window.innerWidth) }
    LaunchedEffect(Unit) {
        window.addEventListener("resize") {
            windowWidth.value = window.innerWidth
        }
    }
    return remember {
        derivedStateOf { windowWidth.value >= 1024 }
    }
}

private val isBrowserDarkTheme: State<Boolean>
    @Composable
    get() {
        val current =
            remember { mutableStateOf(window.matchMedia("(prefers-color-scheme: dark)").matches) }
        LaunchedEffect(Unit) {
            window.matchMedia("(prefers-color-scheme: dark)").addEventListener("change") {
                current.value = window.matchMedia("(prefers-color-scheme: dark)").matches
            }
        }
        return current
    }