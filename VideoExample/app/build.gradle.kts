plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.example.videoexample"
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA_VERSION
        targetCompatibility = Versions.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Versions.JAVA_VERSION.toString()
    }
    buildFeatures {
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerVersion = Versions.KOTLIN_VERSION
    }
    packagingOptions.resources.excludes += setOf(
            "META-INF/DEPENDENCIES",
            "META-INF/LICENSE",
            "META-INF/LICENSE.txt",
            "META-INF/license.txt",
            "META-INF/NOTICE",
            "META-INF/NOTICE.txt",
            "META-INF/INDEX.LIST",
            "META-INF/notice.txt",
            "META-INF/ASL2.0",
            "META-INF/gradle/incremental.annotation.processors"
    )
}

dependencies {
    implementation(Dependency.Android.CORE_KTX)
    implementation(Dependency.Android.APP_COMPAT)
    implementation(Dependency.Android.CORE_KTX)
    implementation(Dependency.Android.FRAGMENT_KTX)
    implementation(Dependency.Android.CONSTRAINT_LAYOUT)
    implementation(Dependency.Android.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependency.Android.MATERIAL)

    implementation(Dependency.COROUTINES.COROUTINES_CORE)
    implementation(Dependency.COROUTINES.COROUTINES_ANDROID)

    implementation(Dependency.HILT.HILT_ANDROID)
    kapt(Dependency.HILT.HILT_ANDROID_COMPILER)

    implementation(Dependency.RETROFIT.RETROFIT)
    implementation(Dependency.RETROFIT.RETROFIT_CONVERTER_GSON)
    implementation(Dependency.RETROFIT.OKHTTP)
    implementation(Dependency.RETROFIT.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Dependency.EXOPLAYER.EXOPLAYER)
    implementation(Dependency.EXOPLAYER.EXOPLAYER_UI)

    implementation(Dependency.MOSHI.MOSHI)
    kapt(Dependency.MOSHI.MOSHI_COMPILER)

    testImplementation(Dependency.UnitTest.JUNIT)
    testImplementation(Dependency.UnitTest.MOCKITO)

    androidTestImplementation(Dependency.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)

    implementation(Dependency.BottomNav.NAV_FRAGMENT)
    implementation(Dependency.BottomNav.NAV_UI)

    implementation(Dependency.Coil.COIL)
}