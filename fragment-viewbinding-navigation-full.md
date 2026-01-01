
# 📘 Android Fragment – Complete Guide
## ViewBinding + Navigation Component (Copy-Paste Ready)

This tutorial covers **everything about Fragment** with **ViewBinding** and **Navigation Component**.
It is **interview-focused + practical**, written in **copy-paste format**.

---

## 1️⃣ What is a Fragment?
A Fragment represents a **reusable portion of UI** inside an Activity.

👉 Activity = container  
👉 Fragment = UI block

---

## 2️⃣ Fragment vs Activity (Interview)

| Fragment | Activity |
|--------|----------|
| Reusable UI | Single screen |
| Lives inside Activity | App entry point |
| Lightweight | Heavy |
| Supports back stack | Managed by system |

---

## 3️⃣ Fragment Lifecycle (Very Important)

```
onAttach()
onCreate()
onCreateView()
onViewCreated()
onStart()
onResume()
onPause()
onStop()
onDestroyView()
onDestroy()
onDetach()
```

📌 Interview Tip:  
Always clear binding in `onDestroyView()`.

---

## 4️⃣ Enable ViewBinding

```gradle
android {
    buildFeatures {
        viewBinding true
    }
}
```

---

## 5️⃣ Activity Layout with NavHostFragment

### `activity_main.xml`

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/nav_host_fragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph"
    app:fragmentNavigatorName="androidx.navigation.fragment.NavHostFragment"/>
```

---

## 6️⃣ Navigation Graph

### `nav_graph.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation
    xmlns:android="http://schemas.android.com/apk/res/android"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.ui.HomeFragment"
        android:label="Home">

        <action
            android:id="@+id/action_home_to_detail"
            app:destination="@id/detailFragment"/>
    </fragment>

    <fragment
        android:id="@+id/detailFragment"
        android:name="com.example.ui.DetailFragment"
        android:label="Detail"/>
</navigation>
```

---

## 7️⃣ Fragment Layout

### `fragment_home.xml`

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:orientation="vertical">

        <Button
            android:id="@+id/btnNext"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Go to Detail"/>
    </LinearLayout>
</layout>
```

---

## 8️⃣ HomeFragment with ViewBinding

```kotlin
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_detail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

---

## 9️⃣ DetailFragment with ViewBinding

```kotlin
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

---

## 🔟 Passing Data Between Fragments (Safe Args)

### Step 1: Enable Safe Args

```gradle
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.8.4"
```

### Step 2: nav_graph.xml

```xml
<argument
    android:name="userName"
    app:argType="string"/>
```

### Step 3: Send Data

```kotlin
val action =
    HomeFragmentDirections.actionHomeToDetail("Biswajit")

findNavController().navigate(action)
```

### Step 4: Receive Data

```kotlin
val args: DetailFragmentArgs by navArgs()
binding.textView.text = args.userName
```

---

## 1️⃣1️⃣ Common Fragment Mistakes (Interview)

❌ Not clearing binding  
❌ Heavy logic in Fragment  
❌ Manual FragmentTransaction instead of Navigation  

---

## 1️⃣2️⃣ Best Practices
- Use Navigation Component
- Use ViewBinding
- Clear binding in onDestroyView()
- Keep Fragment lightweight

---

## ✅ Key Takeaways
- Fragment is reusable UI
- ViewBinding prevents crashes
- Navigation simplifies fragment transactions
- Very common interview topic

---

⬅️ Back to Home
