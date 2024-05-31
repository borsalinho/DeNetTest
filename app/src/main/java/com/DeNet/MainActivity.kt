package com.DeNet

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.DeNet.app.App
import com.DeNet.presentation.viewmodel.MyViewModel
import com.s21.domain.model.Node
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        setContent {

            MainScreen(myViewModel)
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MyViewModel) {
    val nodes by viewModel.nodes.collectAsState()
    val parentNode by viewModel.parentNode.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(parentNode?.name ?: "Загрузка...")
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(nodes) { node ->
                    NodeItem(node, viewModel::openNode, viewModel::deleteNode)
                }
            }

            IconButton(
                onClick = {
                    if (viewModel.parentNode.value?.parentId != null) {
                        viewModel.goBack()
                    } else {
                        Toast.makeText(
                            viewModel.context,
                            "Конечная!!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(56.dp)
                    .background (Color.LightGray,shape = CircleShape),
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            IconButton(
                onClick = { viewModel.addNode() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(56.dp)
                    .background(Color.LightGray, shape = CircleShape)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Node")
            }
        }
    }
}
@Composable
fun NodeItem(node: Node, onNodeClick: (Node) -> Unit, onDeleteClick: (Node) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
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

