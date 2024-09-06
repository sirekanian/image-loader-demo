# ImageLoader

<img src="app/src/main/res/mipmap-xxhdpi/ic_launcher.webp" align="right" />

This repository consists of two modules:
- `imageloader` - ImageLoader library
- `app` - demo application for testing ImageLoader

To register ImageLoader write this line in your [Application.onCreate()][1]:
```kotlin
ImageLoader.register(this)
```

To load an image into `imageView` use this code:
```kotlin
ImageLoader.load("https://...").into(imageView)
```

The library provides a class with static methods for ease of use. The library monitors the activity
lifecycle: when leaving the screen, current network requests are canceled.

In this project I have used:

- Kotlin coroutines
- Ktor

[Release APK][2] size 1.3 MB

[1]: https://developer.android.com/reference/android/app/Application#onCreate()
[2]: https://github.com/sirekanian/image-loader-demo/raw/master/app-release.apk
