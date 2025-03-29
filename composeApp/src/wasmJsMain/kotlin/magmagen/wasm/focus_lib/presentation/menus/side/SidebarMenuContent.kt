package magmagen.wasm.focus_lib.presentation.menus.side

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import magmagen.wasm.focus_lib.presentation.screens.setup.SetupPage
import magmagen.wasm.focus_lib.presentation.shared_components.AppText
import magmagen.wasm.focus_lib.presentation.theme.AppTheme

@Composable
internal fun SidebarMenuContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(AppTheme.colorScheme.primaryContainer)
            .fillMaxHeight()
            .fillMaxWidth(AppTheme.dimensions.sideMenuBigScreenWidthPercent)
            .padding(AppTheme.dimensions.normalSpace),
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Spacer(Modifier.fillMaxHeight(AppTheme.dimensions.topMenuHeightPercent))
        MenuItem(
            modifier = Modifier.padding(vertical = AppTheme.dimensions.normalSpace),
            text = "Home",
            icon = Icons.Default.Home,
            onClick = {
                navigator.popUntilRoot()
            }
        )
        MenuItem(
            modifier = Modifier.padding(vertical = AppTheme.dimensions.normalSpace),
            text = "Profile",
            onClick = {
                navigator.popUntil {
                    it.key == SetupPage::class.simpleName
                }
                navigator.push(SetupPage())
            }
        )
        MenuItem(
            modifier = Modifier.padding(vertical = AppTheme.dimensions.normalSpace),
            text = "Settings"
        )
    }
}

@Composable
private fun MenuItem(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = LocalIndication.current,
                interactionSource = interactionSource
            )
            .padding(horizontal = AppTheme.dimensions.normalSpace),
        horizontalArrangement = Arrangement.Center
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .padding(end = AppTheme.dimensions.normalSpace)
                    .align(Alignment.CenterVertically),
                tint = AppTheme.colorScheme.onPrimary,
                imageVector = icon,
                contentDescription = text
            )
        }
        AppText(
            modifier = modifier.align(Alignment.CenterVertically),
            text = text,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = if (interactionSource.collectIsHoveredAsState().value) AppTheme.colorScheme.selected else AppTheme.colorScheme.onPrimary
        )
    }
}

