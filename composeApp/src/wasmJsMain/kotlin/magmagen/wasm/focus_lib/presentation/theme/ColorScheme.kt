package magmagen.wasm.focus_lib.presentation.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val primaryContainer: Color,
    val onPrimary: Color,
    val link: Color,
    val selected: Color,
    val error: Color,
    val success: Color,
) {

    @Composable
    fun animateColor(
        targetColor: Color,
        duration: Int = 500
    ) = animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(duration)
    ).value

    @Composable
    fun animated(): ColorScheme = ColorScheme(
        primaryContainer = animateColor(primaryContainer),
        onPrimary = animateColor(onPrimary),
        link = animateColor(link),
        selected = animateColor(selected),
        error = animateColor(error),
        success = animateColor(success),
    )
}

fun lightTheme(
    primaryContainer: Color = LightBackground,
    onPrimary: Color = onLightPrimary,
    link: Color = Link,
    selected: Color = LinkSelected,
    error: Color = Error,
    success: Color = Success,
) = ColorScheme(
    primaryContainer = primaryContainer,
    onPrimary = onPrimary,
    link = link,
    selected = selected,
    error = error,
    success = success
)

fun darkTheme(
    primaryContainer: Color = DarkBackground,
    onPrimary: Color = onDarkPrimary,
    link: Color = Link,
    selected: Color = LinkSelected,
    error: Color = Error,
    success: Color = Success,
) = ColorScheme(
    primaryContainer = primaryContainer,
    onPrimary = onPrimary,
    link = link,
    selected = selected,
    error = error,
    success = success,
)

internal fun getScheme(isDarkTheme: Boolean) = if (isDarkTheme) darkTheme() else lightTheme()