package com.github.dwivedyaakash.taskly

object TodoManager {
    private val todoList = mutableListOf<Todo>()

    fun getAllTodo(): List<Todo> {
        return todoList.toList()
    }

    fun addTodo(title: String) {
        todoList.add(Todo(System.currentTimeMillis().toInt(), title))
    }

    fun deleteTodo(id: Int) {
        todoList.removeIf {
            it.id == id
        }
    }
}
