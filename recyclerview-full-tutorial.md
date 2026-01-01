
# RecyclerView Full Tutorial (Interview + Practical)
## Android Kotlin – Complete Guide

This tutorial covers **RecyclerView from beginner to interview level** with clear explanations and complete Kotlin code.

---

## 1. What is RecyclerView?
RecyclerView is an advanced and flexible ViewGroup used to display **large sets of data efficiently**.

It replaces:
- ListView
- GridView

---

## 2. Why RecyclerView?
- Better performance
- View recycling
- Supports animations
- Flexible layouts

---

## 3. RecyclerView Main Components

| Component | Description |
|---------|-------------|
| RecyclerView | Container view |
| Adapter | Binds data to views |
| ViewHolder | Holds item view |
| LayoutManager | Defines layout |
| ItemDecoration | Adds spacing |
| DiffUtil | Efficient updates |

---

## 4. Basic RecyclerView Setup

### XML Layout

```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

---

## 5. Model Class

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```

---

## 6. RecyclerView Adapter

```kotlin
class UserAdapter(
    private val userList: List<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvName)
        val email = itemView.findViewById<TextView>(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.name.text = user.name
        holder.email.text = user.email
    }

    override fun getItemCount(): Int = userList.size
}
```

---

## 7. Layout Managers

### LinearLayoutManager

```kotlin
recyclerView.layoutManager = LinearLayoutManager(this)
```

### GridLayoutManager

```kotlin
recyclerView.layoutManager = GridLayoutManager(this, 2)
```

### StaggeredGridLayoutManager

```kotlin
recyclerView.layoutManager =
    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
```

---

## 8. Set Adapter in Activity

```kotlin
val adapter = UserAdapter(userList)

recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = adapter
```

---

## 9. Click Listener in Adapter

```kotlin
itemView.setOnClickListener {
    Toast.makeText(itemView.context, user.name, Toast.LENGTH_SHORT).show()
}
```

---

## 10. RecyclerView with ViewBinding

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
        holder.binding.tvName.text = users[position].name
    }

    override fun getItemCount() = users.size
}
```

---

## 11. notifyDataSetChanged() Problem

- Refreshes entire list
- No animation
- Poor performance

👉 Avoid using it.

---

## 12. RecyclerView with DiffUtil (Recommended)

```kotlin
class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem == newItem
    }
}
```

---

## 13. RecyclerView in MVVM

```kotlin
viewModel.users.observe(this) { list ->
    adapter.submitList(list)
}
```

---

## 14. Common Interview Questions

**Q: Difference between ListView and RecyclerView?**  
RecyclerView is more flexible, efficient, and modular.

**Q: What is ViewHolder?**  
It holds view references to avoid findViewById calls.

**Q: What is LayoutManager?**  
It controls how items are displayed.

---

## 15. Best Practices
- Use ListAdapter + DiffUtil
- Avoid notifyDataSetChanged
- Use ViewBinding
- Use MVVM

---

## 16. Key Takeaways
- RecyclerView is essential for Android UI
- Improves performance for large lists
- Very important interview topic

---

⬅️ Back to Home
