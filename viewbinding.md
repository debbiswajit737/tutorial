# View Binding in Android

## What is View Binding?
View Binding automatically generates a binding class for each XML layout file.

## Enable View Binding <a id="enable-vb"></a>

```gradle
android {
    buildFeatures {
        viewBinding true
    }
}
```

## Declare Binding Variable <a id="binding-variable"></a>

```kotlin
private lateinit var binding: ActivityMainBinding
```

## Initialize Binding <a id="init-binding"></a>

```kotlin
binding = ActivityMainBinding.inflate(layoutInflater)
setContentView(binding.root)
```

⬅️ [Back to Index](index.md)
