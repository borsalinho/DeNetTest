package com.DeNet.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.s21.domain.model.Node

@Composable
fun NodeItem(node: Node, onNodeClick: (Node) -> Unit, onDeleteClick: (Node) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(
            text = node.name,
            modifier = Modifier
                .clickable { onNodeClick(node) }
                .align(Alignment.CenterStart)
        )
        IconButton(
            onClick = { onDeleteClick(node) },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Node")
        }
    }
}