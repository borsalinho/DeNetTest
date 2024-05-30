package com.DeNet.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.model.Node
import com.s21.domain.model.NodeId

import com.s21.domain.usecases.AddNodeUseCase
import com.s21.domain.usecases.GetNodesByParentIdUseCase
import com.s21.domain.usecases.GetRootNodeUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class MyViewModel(
    private val addNodeUseCase : AddNodeUseCase,
    private val getNodesByParentIdUseCase : GetNodesByParentIdUseCase,
    private val getRootNodeUseCases : GetRootNodeUseCases
) :  ViewModel() {

    private val _nodes = MutableStateFlow<List<Node>>(emptyList())
    val nodes: StateFlow<List<Node>> get() = _nodes

    private var _parentNode = MutableStateFlow<Node?>(null)
    val parentNode: StateFlow<Node?> get() = _parentNode

//    private var _parentId : Long = 0L

    init {
        loadRootNode()
    }

    fun addNode() {
        viewModelScope.launch {

            val newNode = Node(
                name = generateNodeName(),
                parentId = parentNode.value!!.id
            )
            addNodeUseCase.execute(newNode)

            loadNodesByParentId(NodeId(parentNode.value!!.id))
        }
    }

    fun loadNodesByParentId(parentId: NodeId) {
        viewModelScope.launch {
            val nodes = getNodesByParentIdUseCase.execute(parentId)
            _nodes.value = nodes
        }
    }

    private fun loadRootNode() {
        viewModelScope.launch {
            try {
                println("я загрузил существующую ноду")
                val nodes = getRootNodeUseCases.execute()
                _parentNode.value = nodes
                loadNodesByParentId(NodeId(parentNode.value!!.id))
            } catch (e: Exception) {
                println("я создал первую")
                val rootNode = Node(
                    name = generateNodeName(),
                    parentId = null
                )
                addNodeUseCase.execute(rootNode)
                _parentNode.value = rootNode


            }


        }
    }


    private fun generateNodeName(): String {
        val randomValue = System.currentTimeMillis().toString()
        val hash = MessageDigest.getInstance("SHA-256").digest(randomValue.toByteArray())
        return hash.takeLast(20).joinToString("") { "%02x".format(it) }
    }
}