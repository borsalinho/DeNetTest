package com.DeNet.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.model.Node
import com.s21.domain.model.NodeId

import com.s21.domain.usecases.AddNodeUseCase
import com.s21.domain.usecases.DeleteNodeUseCase
import com.s21.domain.usecases.GetNodeByIdUseCase
import com.s21.domain.usecases.GetNodesByParentIdUseCase
import com.s21.domain.usecases.GetRootNodeUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class MyViewModel(
    val context: Context,
    private val addNodeUseCase : AddNodeUseCase,
    private val getNodesByParentIdUseCase : GetNodesByParentIdUseCase,
    private val getRootNodeUseCases : GetRootNodeUseCases,
    private val deleteNodeUseCase : DeleteNodeUseCase,
    private val getNodeByIdUseCase : GetNodeByIdUseCase
) :  ViewModel() {

    private val _nodes = MutableStateFlow<List<Node>>(emptyList())
    val nodes: StateFlow<List<Node>> get() = _nodes

    private var _parentNode = MutableStateFlow<Node?>(null)
    val parentNode: StateFlow<Node?> get() = _parentNode

    init {
        loadRootNode()
    }

    fun openNode(node : Node){
        viewModelScope.launch {
            _parentNode.value = node
            loadNodesByParentId(NodeId(parentNode.value!!.id))
        }
    }

    fun goBack(){
        viewModelScope.launch {
            _parentNode.value = getNodeByIdUseCase.execute(
                NodeId(parentNode.value!!.parentId!!)
            )
            loadNodesByParentId(NodeId(parentNode.value!!.id))
        }
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

                _parentNode.value= getRootNodeUseCases.execute()
                loadNodesByParentId(NodeId(parentNode.value!!.id))

            } catch (e: Exception) {

                addNodeUseCase.execute(
                    Node(
                        name = generateNodeName(),
                        parentId = null
                    )
                )
                _parentNode.value = getRootNodeUseCases.execute()

            }

        }
    }

    fun deleteNode(node: Node){
        viewModelScope.launch{
            deleteNodeUseCase.execute(node = node)
            loadNodesByParentId(NodeId(parentNode.value!!.id))
        }
    }

    private fun generateNodeName(): String {
        val randomValue = System.currentTimeMillis().toString()
        val hash = MessageDigest.getInstance("SHA-256").digest(randomValue.toByteArray())
        return hash.takeLast(20).joinToString("") { "%02x".format(it) }
    }
}