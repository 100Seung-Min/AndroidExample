import org.gradle.api.JavaVersion

object Versions {
    const val COMPILE_SDK_VERSION = 32
    const val MIN_SDK_VERSION = 30
    const val TARGET_SDK_VERSION = 32
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val GRADLE_ANDROID = "7.3.0"
    const val GRADLE_KOTLIN = "1.7.0"

    const val CORE_KTX = "1.7.0"
    const val APP_COMPAT = "1.5.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"

    const val MATERIAL = "1.6.1"
    const val MAP = "18.1.0"

    const val JUNIT = "4.13.2"
    const val JUNIT_TEST = "1.1.3"

    const val ESPRESSO_TEST = "3.4.0"
}