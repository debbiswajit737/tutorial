
# MVVM Architecture with Hilt (Complete Tutorial)
## Retrofit API Call + Data Retention Issue (Solved)

This tutorial explains **MVVM architecture using Hilt**, **Retrofit API calls**, and how to **solve the common issue where data is retained after the first API call**.

This is **interview-focused + production-ready**.

---

## 1. What is MVVM?
MVVM stands for **Model – View – ViewModel**.

| Layer | Responsibility |
|------|----------------|
| Model | API, database, repository |
| View | Activity / Fragment |
| ViewModel | Business logic, UI state |

---

## 2. What is Hilt?
Hilt is a **dependency injection (DI) library** built on top of Dagger.

### Why Hilt?
- Reduces boilerplate code
- Manages object lifecycle
- Easy to use with MVVM

---

## 3. Project Structure (MVVM + Hilt)

```
com.example.mvvmhilt
│
├── di
│   └── NetworkModule.kt
│
├── api
│   └── ApiService.kt
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

## 4. Gradle Dependencies

```gradle
implementation "com.google.dagger:hilt-android:2.48"
kapt "com.google.dagger:hilt-compiler:2.48"

implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.8.4"

implementation "com.squareup.retrofit2:retrofit:2.11.0"
implementation "com.squareup.retrofit2:converter-gson:2.11.0"
```

Enable Hilt:
```gradle
kapt {
    correctErrorTypes true
}
```

---

## 5. Application Class

```kotlin
@HiltAndroidApp
class MyApplication : Application()
```

---

## 6. Model Class

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```

---

## 7. API Service (Retrofit)

```kotlin
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
```

---

## 8. Hilt Network Module

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}
```

---

## 9. Repository Layer

```kotlin
class UserRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun fetchUsers() = apiService.getUsers()
}
```

---

## 10. ViewModel (Main Logic)

### Problem:
Once the API is called, **data is retained in ViewModel**.
On configuration change or re-observe, **old data is emitted again**.

This is **expected behavior**.

---

### Solution Strategy:
Use **UI State** and **explicit refresh control**.

---

### ViewModel Implementation (Correct)

```kotlin
@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadUsers(forceRefresh: Boolean = false) {

        if (_uiState.value is UiState.Success && !forceRefresh) {
            return // prevent duplicate API call
        }

        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.fetchUsers()
                if (response.isSuccessful) {
                    _uiState.postValue(UiState.Success(response.body() ?: emptyList()))
                } else {
                    _uiState.postValue(UiState.Error("API Error"))
                }
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
```

---

## 11. UI State (Best Practice)

```kotlin
sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<User>) : UiState()
    data class Error(val message: String) : UiState()
}
```

---

## 12. Activity (View Layer)

```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    // show loader
                }
                is UiState.Success -> {
                    // update RecyclerView
                }
                is UiState.Error -> {
                    // show error
                }
            }
        }

        viewModel.loadUsers()
    }
}
```

---

## 13. Interview Question: Why API is not called again?

**Answer:**  
ViewModel survives configuration changes and holds the last UI state.  
We intentionally prevent duplicate API calls unless a refresh is requested.

---

## 14. How to Force Refresh?

```kotlin
viewModel.loadUsers(forceRefresh = true)
```

---

## 15. Key Takeaways
- Hilt simplifies dependency injection
- ViewModel retains data by design
- Prevent unnecessary API calls
- UI State pattern is best practice
- Interview favorite topic

---

⬅️ Back to Home
