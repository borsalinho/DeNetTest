package com.s21.data.storage.model

import androidx.room.Embedded
import androidx.room.Relation

data class NodeWithChildren(
    @Embedded val node: NodeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val children: List<NodeEntity>
)