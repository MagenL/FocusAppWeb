package magmagen.wasm.focus_lib.presentation.menus.top

import DarkMode
import LightMode
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import magmagen.wasm.focus_lib.presentation.shared_components.AppText
import magmagen.wasm.focus_lib.presentation.theme.AppTheme

@Composable
fun TopAppBar(
    isLargeScreen: State<Boolean>,
    isDrawerOpen: State<Boolean> = remember { mutableStateOf(false) },
    openDrawer: () -> Unit = {},
    onSwitchTheme: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(AppTheme.dimensions.topMenuHeightPercent)
            .background(AppTheme.colorScheme.primaryContainer)
            .padding(AppTheme.dimensions.normalSpace),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (!isLargeScreen.value) {
            AnimatedVisibility(visible = !isDrawerOpen.value) {
                IconButton(onClick = openDrawer) {
                    Icon(Icons.Default.Menu, "Open Menu", tint = AppTheme.colorScheme.onPrimary)
                }
            }
        }
//        Spacer(modifier = Modifier.fillMaxWidth(AppTheme.dimensions.sideMenuBigScreenWidthPercent))
        AsyncImage(
            modifier = Modifier.size(80.dp),
            model = "https://entrepreneurhandbook.co.uk/wp-content/uploads/2014/04/Focus-in-the-center-of-blurred-text.jpg.webp",
            contentDescription = null,
            onError = {
            },
            onLoading = {
            }
        )
        Spacer(modifier = Modifier.weight(AppTheme.dimensions.maxSpaceWeight))
        AppText("search..")
        IconButton(onClick = onSwitchTheme) {
            AnimatedContent(targetState = AppTheme.isDarkTheme) { isDarkTheme ->
                Icon(
                    imageVector = if (isDarkTheme) DarkMode else LightMode,
                    contentDescription = "app theme",
                    tint = AppTheme.colorScheme.onPrimary
                )
            }
        }
    }
}