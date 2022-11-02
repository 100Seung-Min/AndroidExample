object Dependency {
    object GradlePlugin {
        const val GRADLE_ANDROID = "com.android.tools.build:gradle:${Versions.GRADLE_ANDROID}"
        const val GRADLE_KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.GRADLE_KOTLIN}"
        const val GRADLE_FIREBASE = "com.google.gms:google-services:${Versions.GRADLE_FIREBASE}"
    }

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx:${Versions.FIREBASE_ANALYTICS}"
    }

    object Junit {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val JUNIT_TEST = "androidx.test.ext:junit:${Versions.JUNIT_TEST}"
    }

    object Espresso {
        const val ESPRESSO_TEST = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_TEST}"
    }
}