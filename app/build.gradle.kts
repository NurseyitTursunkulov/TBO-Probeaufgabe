plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
//   kotlin("kapt")
}

android {
    namespace = "com.example.tbo_probeaufgabe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tbo_probeaufgabe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin {
        jvmToolchain(8)
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    val koinVersion = "3.5.3"
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")
    testImplementation("io.insert-koin:koin-test-junit4:$koinVersion")

    val retrofit_version = "2.9.0"
    val moshi_converter_version = "2.9.0"
    val moshi_version = "1.14.0"
    val room_version = "2.5.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")

    implementation("com.squareup.retrofit2:converter-moshi:$moshi_converter_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")

    implementation("com.squareup.retrofit2:converter-scalars:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")

    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-paging:$room_version")

    val junit_version = "4.13.2"
    val mockito_version = "4.8.0"
    val robolectric_version = "4.10.3"
    val google_truth_version = "1.1.2"
    val androidx_core_testing_version = "2.2.0"
    val coroutines_test_version = "1.4.2"
    // Testing Dependencies
    testImplementation ("junit:junit:$junit_version")
    testImplementation ("org.mockito:mockito-core:$mockito_version")
    testImplementation ("org.robolectric:robolectric:$robolectric_version")


    // Dependencies for both test and AndroidTest
    debugImplementation ("com.google.truth:truth:$google_truth_version")
    debugImplementation ("androidx.arch.core:core-testing:$androidx_core_testing_version")
    debugImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_test_version")
    testImplementation("io.mockk:mockk:1.13.7")
//    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.13.7")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.1.0")
    implementation ("androidx.navigation:navigation-compose:2.7.6")
}

//kapt {
//    correctErrorTypes = true
//}
