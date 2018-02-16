# Android Icon Badge

> A simple badge widget to display numbers and shapes

[![](https://jitpack.io/v/liefery/android-icon-badge.svg)](https://jitpack.io/#liefery/android-icon-badge)

![Sample app screenshot](https://liefery.github.io/android-icon-badge/screenshot.png)

## Installation

### sbt

```scala
resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.liefery" % "android-icon-badge" % "1.3.3"
```

### Gradle

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.liefery:android-icon-badge:1.3.3'
}
```

## Usage

```xml
<com.liefery.android.icon_badge.IconBadgeView
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:elevation="3dp"
    app:iconBadge_backgroundShapeColor="#4c8c4a"
    app:iconBadge_foregroundShapeColor="#003300"
    app:iconBadge_number="123" />
```

Please have a look at the sample application for more details.
