package com.s21.data.extensions

import com.s21.data.storage.model.NodeEntity
import com.s21.domain.model.Node

fun NodeEntity.toNode(): Node {
    return Node(
        id = this.id,
        name = this.name,
        parentId = this.parentId
    )
}