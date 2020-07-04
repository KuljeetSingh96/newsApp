# News Application Assignement

Android Mobile Application

* Design pattern used is MVVMP(Data Binding + Live Data). 

* This project also contains basic utility classes.

* For network calls we have used Retrofit in this project which is annotation based network libraryto prepare requests. 

* For better optimisation we have used Rx Java/Android function for all API calls.

* Dagger for dependency Injection.

# This Project Structure follow  a clean architecture.

It uses of following libraries:

* [Retrofit2] (https://square.github.io/retrofit/) for REST API.

* [RX java] (https://github.com/ReactiveX/RxJava) for background process and Retrofit integration.

* [Picasso] (http://square.github.io/picasso/) for image loading.

* [Dagger 2] (https://github.com/google/dagger) Library to achieve Dependency Injection.

* [Shimmer Layout] (https://facebook.github.io/shimmer-android/#:~:text=Shimmer%20is%20an%20Android%20library,view%20in%20your%20Android%20app.&text=Shimmer%20for%20Android%20is%20implemented,the%20animation%20from%20your%20code.) Library to achieve Dependency Injection.

# Here is what the app gradle look likes.
```
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.infosystest"
        minSdkVersion 19
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    dataBinding {
        enabled = true
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    //google
    implementation "androidx.appcompat:appcompat:$rootProject.appCompatVersion"
    implementation "androidx.core:core-ktx:$rootProject.ktxCompatVersion"
    implementation "androidx.constraintlayout:constraintlayout:$rootProject.constraintLayoutVersion"
    implementation "androidx.recyclerview:recyclerview:$rootProject.recyclerViewVersion"
    implementation "androidx.cardview:cardview:1.0.0"
    //ProcessLifecycleOwner
    implementation "android.arch.lifecycle:extensions:$rootProject.archLifecycleVersion"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    //rx
    implementation "io.reactivex.rxjava2:rxandroid:$rootProject.rxAndroidVersion"
    implementation "io.reactivex.rxjava2:rxjava:$rootProject.rxJavaVersion"
    implementation "com.jakewharton.rxbinding:rxbinding:$rootProject.rxBindingVersion"
    //networking
    implementation "com.squareup.retrofit2:retrofit:$rootProject.retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$rootProject.retrofitVersion"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$rootProject.retrofitVersion"
    implementation "com.squareup.okhttp3:logging-interceptor:$rootProject.loggerVersion"
    // Dagger
    implementation "com.google.dagger:dagger:$rootProject.daggerVersion"
    kapt "com.google.dagger:dagger-compiler:$rootProject.daggerVersion"
    //image
    implementation "com.squareup.picasso:picasso:$rootProject.picassoVersion"
    //shimmer layout
    implementation "com.facebook.shimmer:shimmer:$rootProject.shimmerVersion"
    //swipe to refresh
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:$rootProject.swipeToRefreshVersion"
    //instrument libraries
    androidTestImplementation "com.android.support.test:runner:$rootProject.runnerVersion"
    androidTestImplementation "com.android.support.test:rules:$rootProject.runnerVersion"
    androidTestImplementation "androidx.test.espresso:espresso-core:$rootProject.espressoVersion"
    androidTestImplementation("androidx.test.espresso:espresso-intents:$rootProject.espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$rootProject.espressoVersion") {
        // Necessary to avoid version conflicts
        exclude group: 'com.android.support', module: 'appcompat'
        exclude group: 'com.android.support', module: 'support-v4'
        exclude group: 'com.android.support', module: 'support-annotations'
        exclude module: 'recyclerview-v7'
    }
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    //unit test libraries
    testImplementation 'org.mockito:mockito-core:2.19.0'
    testImplementation 'junit:junit:4.12'
    testImplementation "android.arch.core:core-testing:1.1.1"

}

```

# Start from

minSdkVersion 19

# Author

Kuljeet Singh Bhadwal
