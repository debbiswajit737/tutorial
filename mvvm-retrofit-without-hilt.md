
# MVVM Architecture in Android (Without Hilt)
## Retrofit API Call + LiveData Old Data Issue (Solution)

This tutorial explains how to implement **MVVM architecture without Hilt**, make an **API call using Retrofit**, and **solve the common LiveData problem where old data is emitted again**.

---

## 1. What is MVVM?
MVVM stands for **Model – View – ViewModel**.

| Layer | Responsibility |
|------|----------------|
| Model | Data source (API, DB, Repository) |
| View | Activity / Fragment (UI) |
| ViewModel | Business logic + LiveData |

---

## 2. Project Structure (Without Hilt)

```
com.example.mvvmapp
│
├── api
│   ├── ApiService.kt
│   └── RetrofitClient.kt
│
├── model
│   └── User.kt
│
├── repository
│   └── UserRepository.kt
│
├── viewmodel
│   └── UserViewModel.kt
│
└── ui
    └── MainActivity.kt
```

---

## 3. Model Class

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```

---

## 4. Retrofit API Interface

```kotlin
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
```

---

## 5. Retrofit Client (Singleton)

```kotlin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
```

---

## 6. Repository Layer

The repository decides **where data comes from**.

```kotlin
class UserRepository {

    suspend fun getUsers() =
        RetrofitClient.apiService.getUsers()
}
```

---

## 7. ViewModel (Important Part)

### ❌ Common Problem
LiveData stores the **last value**, so when:
- API is called again
- Observer is re-attached

👉 Old data is emitted again.

---

### ✅ Correct ViewModel Implementation

```kotlin
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>?>()
    val users: LiveData<List<User>?> = _users

    fun fetchUsers() {

        // Clear previous data to avoid old value emission
        _users.value = null

        viewModelScope.launch {
            val response = repository.getUsers()
            if (response.isSuccessful) {
                _users.postValue(response.body())
            } else {
                _users.postValue(emptyList())
            }
        }
    }
}
```

---

## 8. Why LiveData Emits Old Data?

Because:
- LiveData is lifecycle-aware
- It **caches the last emitted value**
- New observers receive the cached value automatically

This is **expected behavior**, not a bug.

---

## 9. Solutions to LiveData Old Data Issue

### ✔ Solution 1: Clear LiveData Before API Call (Most Used)

```kotlin
_users.value = null
```

---

### ✔ Solution 2: Event Wrapper (One-Time Event)

```kotlin
class Event<out T>(private val data: T) {

    private var handled = false

    fun getDataIfNotHandled(): T? {
        return if (handled) null
        else {
            handled = true
            data
        }
    }
}
```

Usage:
```kotlin
private val _users = MutableLiveData<Event<List<User>>>()
```

---

### ✔ Solution 3: UI State (Best Practice)

```kotlin
sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<User>) : UiState()
    data class Error(val message: String) : UiState()
}
```

---

## 10. Activity (View Layer)

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.users.observe(this) { users ->
            users?.let {
                // Update RecyclerView here
            }
        }

        viewModel.fetchUsers()
    }
}
```

---

## 11. Interview One-Line Answer

**Question:** Why does LiveData return old data?  
**Answer:**  
> LiveData caches the last emitted value, so when a new observer is attached, it automatically emits the previous data.

---

## 12. Key Takeaways
- MVVM works perfectly without Hilt
- Repository handles data source logic
- ViewModel survives configuration changes
- LiveData keeps old data by design
- Clear or wrap data to avoid duplicate emissions

---

⬅️ Back to Home
