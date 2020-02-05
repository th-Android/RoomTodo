package si.um.feri.roomtodo

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = todoDao.getAlphabetizedTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }
}