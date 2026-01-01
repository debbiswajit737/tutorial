# Data Binding in Android

## What is Data Binding?
Data Binding allows binding UI directly with data.

## Enable Data Binding <a id="enable-db"></a>

```gradle
android {
    buildFeatures {
        dataBinding true
    }
}
```

## XML Layout <a id="layout-db"></a>

```xml
<layout>
    <data>
        <variable
            name="user"
            type="com.example.User"/>
    </data>
</layout>
```

⬅️ [Back to Index](index.md)
