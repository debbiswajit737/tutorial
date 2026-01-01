# MVVM Architecture in Android (Without Hilt)
## Retrofit API Call + LiveData Issue Solution

এই tutorial-এ আমরা দেখবো:
- MVVM কী
- Hilt ছাড়া MVVM structure
- Retrofit দিয়ে API call
- LiveData-তে পুরোনো data থাকার সমস্যা
- সেই সমস্যার correct solution (Interview favorite 🔥)

---

## 🔷 What is MVVM?
MVVM = **Model – View – ViewModel**

| Layer | Responsibility |
|-----|---------------|
| Model | Data, API, Repository |
| View | Activity / Fragment (UI) |
| ViewModel | UI logic + LiveData |

---

## 📁 Project Structure (Without Hilt)

