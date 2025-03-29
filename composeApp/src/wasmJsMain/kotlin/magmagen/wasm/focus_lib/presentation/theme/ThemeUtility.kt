package magmagen.wasm.focus_lib.presentation.theme

import kotlinx.browser.window

val isDarkTheme: Boolean = window.matchMedia("(prefers-color-scheme: dark)").matches


val isUserPreferDarkTheme: Boolean =
    window.localStorage.getItem("theme")?.matches("true".toRegex()) ?: isDarkTheme
