package magmagen.wasm.focus_lib.presentation.menus.side

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import magmagen.wasm.focus_lib.presentation.theme.AppTheme

@Composable
fun NavigationDrawer(
    modifier: Modifier = Modifier,
    isDrawerOpen: State<Boolean>,
) {
    AnimatedVisibility(
        visible = AppTheme.isLargeScreen.value || isDrawerOpen.value,
        enter = slideInHorizontally() + fadeIn(),
        exit = slideOutHorizontally() + fadeOut()
    ) {
        SidebarMenuContent(modifier = modifier)
    }
}
