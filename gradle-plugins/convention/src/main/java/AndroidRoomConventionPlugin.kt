import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.popkov.android.core.gradleplugins.commonExtension
import ru.popkov.android.core.gradleplugins.libs

class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            commonExtension.apply {
                defaultConfig {
                    javaCompileOptions {
                        annotationProcessorOptions {
                            arguments += mapOf(
                                "room.schemaLocation" to "$projectDir/schemas",
                                "room.incremental" to "true"
                            )
                        }
                    }
                }
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-room-runtime").get())
                add("implementation", libs.findLibrary("androidx-room-ktx").get())
                add("ksp", libs.findLibrary("androidx-room-compiler").get())
            }
        }
    }
}
