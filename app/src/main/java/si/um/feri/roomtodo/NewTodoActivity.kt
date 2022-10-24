package si.um.feri.roomtodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import si.um.feri.roomtodo.databinding.ActivityMainBinding
import si.um.feri.roomtodo.databinding.ActivityNewTodoActiivtyBinding

class NewTodoActivity : AppCompatActivity() {

    private lateinit var editTodoView: EditText
    private lateinit var binding: ActivityNewTodoActiivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewTodoActiivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editTodoView = binding.editWord

        val button = binding.buttonSave
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTodoView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editTodoView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "si.um.feri.todolistsql.REPLY"
    }
}
