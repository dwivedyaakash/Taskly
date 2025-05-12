package com.github.dwivedyaakash.taskly.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dwivedyaakash.taskly.data.TodoManager
import com.github.dwivedyaakash.taskly.data.model.Todo

class TodoViewModel : ViewModel() {
    private var _todoList = MutableLiveData<List<Todo>>(emptyList())
    val todoList: LiveData<List<Todo>> get() = _todoList

    init {
        getAllTodo()
    }

    private fun getAllTodo() {
        _todoList.value = TodoManager.getAllTodo()
    }

    fun addTodo(title: String) {
        TodoManager.addTodo(title)
        getAllTodo()
    }

    fun deleteTodo(id: Int) {
        TodoManager.deleteTodo(id)
        getAllTodo()
    }
}
