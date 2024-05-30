package com.s21.domain.model

data class Node(
    val id: Long = 0L,
    val name: String,
//    val children: MutableList<Node> = mutableListOf(),
    var parentId: Long?
)