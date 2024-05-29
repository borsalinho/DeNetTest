package com.s21.domain.model

data class Node(
    val id: String,
    val name: String,
    val children: MutableList<Node> = mutableListOf(),
    var parent: Node? = null
)