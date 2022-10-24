package si.um.feri.roomtodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import si.um.feri.roomtodo.databinding.RecyclerviewItemBinding

class TodoListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var todos = emptyList<Todo>()

    inner class TodoViewHolder(binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val todoItemView: TextView = binding.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todos[position]
        holder.todoItemView.text = current.todo
    }

    internal fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    override fun getItemCount() = todos.size
}