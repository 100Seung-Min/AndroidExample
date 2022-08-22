object Dependency {
    object GradlePlugin {
        const val GRADLE_ANDROID = "com.android.tools.build:gradle:${Versions.GRADLE_ANDROID}"
        const val GRADLE_KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.GRADLE_KOTLIN}"
        const val GRADLE_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
    }

    object COROUTINES {
        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
    }

    object Android {
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
        const val LIFECYCLE_VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
    }

    object HILT {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    }

    object RETROFIT {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    }

    object EXOPLAYER {
        const val EXOPLAYER = "com.google.android.exoplayer:exoplayer-core:${Versions.EXOPLAYER}"
        const val EXOPLAYER_UI = "com.google.android.exoplayer:exoplayer-ui:${Versions.EXOPLAYER}"
    }

    object MOSHI {
        const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
        const val MOSHI_COMPILER = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    }

    object UnitTest {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

    object BottomNav {
        const val NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAV}"
        const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAV}"
    }
}