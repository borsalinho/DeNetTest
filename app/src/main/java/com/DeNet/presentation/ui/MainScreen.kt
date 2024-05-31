package com.DeNet.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.DeNet.presentation.viewmodel.MyViewModel

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
                    Text(
                        text= parentNode?.name ?: "Загрузка...",
                        fontSize = 16.sp,
                    )
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {

            Column(modifier = Modifier.fillMaxSize()) {
                Divider(
                    color = Color.Black,
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                val lazyListState = rememberLazyListState()
                var displayedItems by remember { mutableStateOf(20) }

                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(nodes.take(displayedItems)) { index, node ->
                        if (index >= displayedItems - 1 && index < nodes.size - 1) {
                            LaunchedEffect(Unit) {
                                displayedItems += 20
                            }
                        }
                        NodeItem(node, viewModel::openNode, viewModel::deleteNode)
                    }
                }
            }

            IconButton(
                onClick = {
                    if (viewModel.parentNode.value?.parentId != null) {
                        viewModel.goBack()
                    } else {
                        viewModel.showToastOnce("Конечная")
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

