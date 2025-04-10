package com.github.dwivedyaakash.taskly

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoScreen(modifier: Modifier = Modifier, viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(end = 16.dp),
                value = inputText,
                onValueChange = {
                    inputText = it
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }) {
                Text("Add")
            }
        }

        todoList?.let {
            LazyColumn(content = {
                itemsIndexed(it) { _: Int, item: Todo ->
                    TodoItem(item = item, onDelete = {
                        viewModel.deleteTodo(item.id)
                    })
                }
            })
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items",
            fontSize = 16.sp
        )
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Text(
            modifier = Modifier.clickable { onDelete() },
            text = "Delete",
            color = Color.Red
        )
    }
}
