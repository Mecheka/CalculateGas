import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("mecheka.android.library")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            extensions.getByType<LibraryExtension>().apply {
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("compose-compiler").get().toString()
                }
                dependencies {
                    val bom = libs.findLibrary("compose-bom").get()
                    val composes = libs.findBundle("compose").get()
                    val composeDebug = libs.findBundle("composeDebug").get()
                    add("implementation", platform(bom))
                    add("implementation", composes)
                    add("debugImplementation", composeDebug)
                    add("implementation", libs.findLibrary("androidx-corektx").get())
                }
            }
        }
    }
}