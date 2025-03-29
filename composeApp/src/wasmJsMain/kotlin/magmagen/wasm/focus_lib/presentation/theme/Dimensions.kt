package magmagen.wasm.focus_lib.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(isBigScreen: Boolean) {
    val sideMenuBigScreenWidthPercent: Float = if (isBigScreen) 0.25f else 0.75f
    val topMenuHeightPercent: Float = 0.10f
    val maxSpaceWeight: Float = 1f
    val minSpace: Dp = 8.dp
    val normalSpace: Dp = 16.dp
}