package magmagen.wasm.focus_lib.presentation.r

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val dictionary: Dictionary
    get() {
        return Json.decodeFromString<Dictionary>(dictionaryString)
    }

private val dictionaryString = """
{
  "menu": {
    "sideMenu": {
      "a": "a"
    },
    "topAppBar": {
      "a": "a"
    }
  },
  "screens": {
    "homePage": {
        "introTitle": "Focus Management in Jetpack Compose",
        "introSubTitle": "Introduction",
        "introBody": "Managing focus in Jetpack Compose, especially within scrollable and lazy layouts, presents significant challenges. Unlike traditional UI frameworks, where focus behavior is often implicitly handled, Jetpack Compose requires explicit management to ensure focus restoration, movement, and accessibility. When items enter or leave the composition, maintaining the correct focus state can be difficult. Additionally, restoring focus when navigating between screens adds complexity, as recomposition can cause the loss of manually managed focus states. Developers need to implement mechanisms to save and restore focus when switching views to ensure a seamless user experience. This library provides a structured way to manage focus within LazyColumn, LazyRow, and LazyGrid, ensuring a smooth and predictable experience.",
        "overViewTitle": "Overview of the Focusable Lazy Layout Library",
        "overViewBody": "This library introduces a set of classes and components aimed at handling focus movement efficiently within lazy layouts. The key objectives include:",
        "overViewBullet1": "🔵 Restoring focus when items enter or leave the composition.",
        "overViewBullet2": "● Ensuring logical focus movement within scrolling lists and grids.",
        "overViewBullet3": "● Providing a structured API to manage focus states across multiple lazy containers.",
        "overViewBullet4": "● Enhancing accessibility by ensuring predictable keyboard and screen reader navigation."
    }
  }
}
""".trimIndent()

@Serializable
data class Dictionary(
    val menu: Menu,
    val screens: Screens
)

@Serializable
data class Screens(
    val homePage: HomePage
)

@Serializable
data class HomePage(
    val introTitle: String,
    val introSubTitle: String,
    val introBody: String,
    val overViewTitle: String,
    val overViewBody: String,
    val overViewBullet1: String,
    val overViewBullet2: String,
    val overViewBullet3: String,
    val overViewBullet4: String,
)

@Serializable
data class Menu(
    val sideMenu: SideMenu,
    val topAppBar: TopAppBar
)

@Serializable
data class SideMenu(
    val a: String
)

@Serializable
data class TopAppBar(
    val a: String
)
