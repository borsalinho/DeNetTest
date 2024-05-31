package com.DeNet.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.DeNet.presentation.viewmodel.MyViewModel


//@Composable
//fun NodesList(viewModel: MyViewModel) {
//    val nodes by viewModel.pager.collectAsLazyPagingItems()
//
//    LazyColumn {
//        items(nodes) { node ->
//            node?.let {
//                NodeItem(node, viewModel::openNode, viewModel::deleteNode)
//            }
//        }
//    }
//}