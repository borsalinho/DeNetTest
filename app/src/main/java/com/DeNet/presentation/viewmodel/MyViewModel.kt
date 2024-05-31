package com.DeNet.presentation.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.DeNet.presentation.preferencemanager.PreferenceManager
import com.s21.domain.model.Node
import com.s21.domain.model.NodeId

import com.s21.domain.usecases.AddNodeUseCase
import com.s21.domain.usecases.DeleteNodeUseCase
import com.s21.domain.usecases.GetNodeByIdUseCase
import com.s21.domain.usecases.GetNodesByParentIdUseCase
import com.s21.domain.usecases.GetNodeUseCases
import com.s21.domain.usecases.GetRootNodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class MyViewModel(
    val context: Context,
    private val addNodeUseCase : AddNodeUseCase,
    private val getNodesByParentIdUseCase : GetNodesByParentIdUseCase,
    private val getNodeUseCases : GetNodeUseCases,
    private val deleteNodeUseCase : DeleteNodeUseCase,
    private val getNodeByIdUseCase : GetNodeByIdUseCase,
    private val preferenceManager : PreferenceManager,
    private val getRootNodeUseCase : GetRootNodeUseCase

) :  ViewModel() {

    private val _nodes = MutableStateFlow<List<Node>>(emptyList())
    val nodes: StateFlow<List<Node>> get() = _nodes

    private var _parentNode = MutableStateFlow<Node?>(null)
    val parentNode: StateFlow<Node?> get() = _parentNode

    private val _isDataBSLoaded = MutableStateFlow(false)
    val isDataBSLoaded: StateFlow<Boolean> get() = _isDataBSLoaded

    private var isToastShowing = false
    init {
        loadRootNode()
    }

    fun openNode(node : Node){
        viewModelScope.launch {
            _parentNode.value = node
            loadNodesByParentId(NodeId(parentNode.value!!.id))
            preferenceManager.saveNodeId(node.id)
        }
    }

    fun goBack(){

        viewModelScope.launch {
            preferenceManager.saveNodeId(parentNode.value!!.parentId!!)
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
                val nodeId = preferenceManager.getNodeId()
                if (nodeId != -1L){
                    _parentNode.value = getNodeUseCases.execute(NodeId(nodeId))
                    loadNodesByParentId(NodeId(parentNode.value!!.id))
                } else {
                    val newNode = Node(
                        name = generateNodeName(),
                        parentId = null
                    )
                    addNodeUseCase.execute(newNode)
                    preferenceManager.saveNodeId(
                        getRootNodeUseCase.execute().id
                    )
                    val newNodeId = preferenceManager.getNodeId()
                    _parentNode.value = getNodeUseCases.execute(
                        NodeId(newNodeId)
                    )
                }
                _isDataBSLoaded.value = true
            } catch (e: Exception) {
                showToastOnce("Не удалось загрузить с ДБ")
            }
        }
    }

    fun deleteNode(node: Node){
        viewModelScope.launch{
            deleteNodeUseCase.execute(node = node)
            loadNodesByParentId(NodeId(parentNode.value!!.id))
        }
    }

    fun showToastOnce(message: String) {
        if (!isToastShowing) {
            isToastShowing = true
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                isToastShowing = false
            }, Toast.LENGTH_SHORT + 2000L)
        }
    }

    private fun generateNodeName(): String {
        val randomValue = System.currentTimeMillis().toString()
        val hash = MessageDigest.getInstance("SHA-256").digest(randomValue.toByteArray())
        return hash.takeLast(20).joinToString("") { "%02x".format(it) }
    }
}