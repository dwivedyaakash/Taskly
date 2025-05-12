package com.github.dwivedyaakash.taskly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.dwivedyaakash.taskly.viewmodel.TodoViewModel
import com.github.dwivedyaakash.taskly.ui.components.AddTodoBottomSheet
import com.github.dwivedyaakash.taskly.ui.components.Header
import com.github.dwivedyaakash.taskly.ui.components.TodoItem

@Composable
fun TodoScreen(modifier: Modifier = Modifier, viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Header(
            title = "Today's tasks",
            showAddBtn = true,
            showBottomSheet = { showBottomSheet = true }
        )

        if (!todoList.isNullOrEmpty()) {
            LazyColumn {
                itemsIndexed(todoList!!) { _, item ->
                    TodoItem(
                        item = item,
                        onDelete = {
                            viewModel.deleteTodo(item.id)
                        }
                    )
                }
            }
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "No items",
                textAlign = TextAlign.Center,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 16.sp
            )
        }

        if (showBottomSheet) {
            AddTodoBottomSheet(
                viewModel,
                dismissBottomSheet = { showBottomSheet = false }
            )
        }

    }
}
