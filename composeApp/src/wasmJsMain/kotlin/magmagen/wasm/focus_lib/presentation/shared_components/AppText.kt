package magmagen.wasm.focus_lib.presentation.shared_components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import magmagen.wasm.focus_lib.presentation.theme.AppTheme

@Composable
fun AppText(
    text: String,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = AppTheme.typography.body,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = AppTheme.colorScheme.onPrimary,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        letterSpacing = letterSpacing,

    )
}