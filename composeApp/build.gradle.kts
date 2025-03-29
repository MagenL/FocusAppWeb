import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerializable)
}

kotlin {

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.coil.image)
            implementation(libs.coil.network)
            implementation(libs.coil.compose.core)
            implementation(libs.coil.mp)
//            implementation("androidx.compose.material:material:1.7.8")
//            implementation(libs.compose.icons)
        }

        wasmJsMain.dependencies {
            //voyager
//            implementation(libs.androidx.lifecycle.viewmodel)
//            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.voyager.koin)
            implementation(libs.koin.compose)
            implementation(libs.voyager.navigation)
            implementation(libs.voyager.screenmodel)
            implementation(libs.voyager.bottomsheet)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transition)
            implementation(libs.kotlin.serializeable)
        }
    }
}


