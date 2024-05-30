package com.s21.data.extensions

import com.s21.data.storage.model.NodeEntity
import com.s21.domain.model.Node

fun Node.toEntity(): NodeEntity {
    return NodeEntity(
        id = this.id,
        name = this.name,
//        children = this.children ,
        parentId = this.parentId
    )
}