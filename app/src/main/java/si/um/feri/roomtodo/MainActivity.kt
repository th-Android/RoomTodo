package si.um.feri.roomtodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import si.um.feri.roomtodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var todoViewModel: TodoViewModel
    private val newTodoActivityRequestCode = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerview
        val adapter = TodoListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        todoViewModel.allTodos.observe(this, Observer { todos ->
            todos?.let { adapter.setTodos(it) }
        })

        val fab = binding.fab
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTodoActivity::class.java)
            startActivityForResult(intent, newTodoActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newTodoActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewTodoActivity.EXTRA_REPLY)?.let {
                val todo = Todo(it)
                todoViewModel.insert(todo)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
