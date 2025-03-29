package magmagen.wasm.focus_lib.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp

@Immutable
class Typography {

    val titles = TextStyle(
        fontSize = 36.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.SemiBold,
    )

    val subTitles = TextStyle(
        fontSize = 26.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Medium,
    )

    val body = TextStyle(
        fontSize = 18.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        lineBreak = LineBreak.Paragraph

    )
}