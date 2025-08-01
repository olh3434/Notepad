package com.olh.notepad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.olh.notepad.databinding.ActivityTodoListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding
    private lateinit var adapter: TodoAdapter
    private lateinit var database: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TodoAdapter(mutableListOf())
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewItems.adapter = adapter

        database = TodoDatabase.getDatabase(this)

        binding.buttonAddItem.setOnClickListener {
            adapter.addItem(TodoItem("", "", false))
        }

        binding.buttonSaveTodo.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val items = adapter.getItems()

            val todo = TodoEntity(title = title, items = items)

            lifecycleScope.launch(Dispatchers.IO) {
                database.todoDao().insertTodo(todo)
                finish()
            }
        }
    }
}
