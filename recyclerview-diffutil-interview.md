
# RecyclerView DiffUtil (Interview Focused)
## Improve Performance & Fix notifyDataSetChanged()

This tutorial explains **DiffUtil** in a clear, interview-oriented way with **full working code**.

---

## 1. What is DiffUtil?

DiffUtil is a utility class that calculates the **difference between two lists** and updates only the changed items in RecyclerView.

👉 It avoids calling `notifyDataSetChanged()`.

---

## 2. Why DiffUtil is Important? (Interview Question)

**Problem with notifyDataSetChanged():**
- Refreshes the entire list
- Poor performance
- No item animation

**DiffUtil solves:**
- Updates only changed items
- Better performance
- Smooth animations

---

## 3. When Should You Use DiffUtil?
- RecyclerView data changes frequently
- API data refresh
- Pagination
- Search result updates

---

## 4. Sample Model Class

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```

---

## 5. DiffUtil Callback Implementation

```kotlin
import androidx.recyclerview.widget.DiffUtil

class UserDiffCallback(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
```

---

## 6. RecyclerView Adapter Using DiffUtil

```kotlin
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = ArrayList<User>()

    fun updateList(newList: List<User>) {

        val diffCallback = UserDiffCallback(userList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        userList.clear()
        userList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            // bind views
        }
    }
}
```

---

## 7. Using Adapter in Activity / Fragment

```kotlin
userAdapter = UserAdapter()
recyclerView.adapter = userAdapter

viewModel.users.observe(this) { list ->
    list?.let {
        userAdapter.updateList(it)
    }
}
```

---

## 8. DiffUtil vs notifyDataSetChanged()

| Feature | DiffUtil | notifyDataSetChanged |
|------|----------|----------------------|
| Performance | High | Low |
| Animation | Yes | No |
| Item-level update | Yes | No |
| Recommended | ✅ | ❌ |

---

## 9. Common Interview Questions

### Q1. How does DiffUtil work?
**Answer:**  
DiffUtil compares old and new lists in the background and updates only the changed items.

---

### Q2. What should be used in areItemsTheSame()?
**Answer:**  
A **unique ID** like database ID or server ID.

---

### Q3. What happens if areContentsTheSame() returns false?
**Answer:**  
RecyclerView rebinds that item.

---

## 10. Best Practice (Interview Tip)

👉 Prefer **ListAdapter** instead of manual DiffUtil.

```kotlin
class UserAdapter :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {
}
```

---

## 11. Key Takeaways
- Never use notifyDataSetChanged() for large lists
- DiffUtil improves performance
- Required for pagination & MVVM
- Very common interview topic

---

⬅️ Back to Home
