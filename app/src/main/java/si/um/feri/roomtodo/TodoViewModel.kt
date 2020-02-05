package si.um.feri.roomtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository

    val allTodos: LiveData<List<Todo>>

    init {
        val todosDao = TodoRoomDatabase.getDatabase(application, viewModelScope).todoDao()
        repository = TodoRepository(todosDao)
        allTodos = repository.allTodos
    }

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }
}