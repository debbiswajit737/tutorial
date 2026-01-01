
# 📘 Android Data Binding – Complete Guide
## Interview + Practical Tutorial (Kotlin)

This tutorial explains **Android Data Binding from basics to advanced**, with **copy‑paste ready code** for:
- Activity
- Fragment
- RecyclerView Adapter
- Dialog
- DialogFragment
- Click handling
- Two‑way Data Binding

---

## 📌 What is Data Binding?
Data Binding allows you to bind **UI components in XML directly with data sources** (Kotlin/Java objects).

It reduces:
- Boilerplate code
- Manual UI updates
- findViewById usage

---

## 1️⃣ Enable Data Binding

In `build.gradle (Module)`:

```gradle
android {
    buildFeatures {
        dataBinding true
    }
}
```

Sync the project.

---

## 2️⃣ Model Class

```kotlin
data class User(
    var name: String,
    var age: Int
)
```

---

## 3️⃣ Data Binding in Activity

### XML Layout (`activity_main.xml`)

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="user"
            type="com.example.model.User" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:text="@{user.name}"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>

        <TextView
            android:text='@{String.valueOf(user.age)}'
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>

    </LinearLayout>
</layout>
```

### Activity Code

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.user = User("Biswajit", 25)
    }
}
```

---

## 4️⃣ Data Binding in Fragment (Memory Safe)

```kotlin
private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.user = User("Android", 10)
    return binding.root
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
```

---

## 5️⃣ Click Handling using Data Binding

### XML

```xml
<data>
    <variable
        name="handler"
        type="com.example.ui.ClickHandler"/>
</data>

<Button
    android:onClick="@{() -> handler.onButtonClick()}"
    android:text="Click Me"/>
```

### ClickHandler Class

```kotlin
class ClickHandler {
    fun onButtonClick() {
        Log.d("DataBinding", "Button clicked")
    }
}
```

---

## 6️⃣ RecyclerView Adapter with Data Binding

### Item Layout (`item_user.xml`)

```xml
<layout>

    <data>
        <variable
            name="user"
            type="com.example.model.User"/>
    </data>

    <TextView
        android:text="@{user.name}"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</layout>
```

### Adapter Code

```kotlin
class UserAdapter(
    private val users: List<User>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.user = users[position]
    }

    override fun getItemCount() = users.size
}
```

---

## 7️⃣ Two‑Way Data Binding (Very Important)

### XML

```xml
<EditText
    android:text="@={user.name}"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

👉 Changes in EditText automatically update `user.name`.

---

## 8️⃣ Data Binding in Dialog

```kotlin
class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.user = User("Dialog User", 30)
    }
}
```

---

## 9️⃣ Data Binding in DialogFragment

```kotlin
class CustomDialogFragment : DialogFragment() {

    private lateinit var binding: DialogCustomBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogCustomBinding.inflate(layoutInflater)
        binding.user = User("Fragment Dialog", 20)

        return Dialog(requireContext()).apply {
            setContentView(binding.root)
        }
    }
}
```

---

## 🔟 Data Binding vs View Binding (Interview)

| Feature | Data Binding | View Binding |
|-------|--------------|--------------|
| XML logic | ✅ | ❌ |
| Two‑way binding | ✅ | ❌ |
| Compile time | Slower | Faster |
| Complexity | Higher | Lower |

---

## 1️⃣1️⃣ Common Interview Questions

**Q: Does Data Binding replace MVVM?**  
No. It only helps bind UI with ViewModel data.

**Q: Why Data Binding is slower?**  
Because it generates more binding logic during compilation.

---

## 1️⃣2️⃣ Best Practices
- Use Data Binding with MVVM
- Avoid heavy logic in XML
- Prefer View Binding if two‑way binding is not required

---

## ✅ Key Takeaways
- Data Binding reduces UI boilerplate
- Supports two‑way binding
- Powerful but should be used carefully
- Very common interview topic

---

⬅️ Back to Home
