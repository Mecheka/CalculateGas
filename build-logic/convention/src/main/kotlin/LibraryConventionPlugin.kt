import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            extensions.getByType<LibraryExtension>().apply {
                compileSdk = 33
                defaultConfig {
                    minSdk = 24
                    targetSdk = 33
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
                sourceSets {

                }
                tasks.withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_11.toString()
                    }
                }
                dependencies {
                    add("implementation", libs.findLibrary("androidx-corektx").get())
                    add("implementation", platform(libs.findLibrary("kotlin-bom").get()))
                }
            }
        }
    }
}