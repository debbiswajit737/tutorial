# 📘 Android Data Binding – Complete Guide

## 📌 What is Data Binding?
Data Binding allows you to bind UI components in XML directly with data sources (Kotlin/Java objects).

## 1️⃣ Enable Data Binding
```gradle
android {
    buildFeatures {
        dataBinding true
    }
}
```

## 2️⃣ Model Class
```kotlin
data class User(
    val name: String,
    val age: Int
)
```

## 3️⃣ Activity, Fragment, Adapter, Dialog, DialogFragment
This file contains full copy-paste ready examples for:
- Activity
- Fragment
- RecyclerView Adapter
- Dialog
- DialogFragment
- Click handling
- Two-way Data Binding

(See full version created above)
