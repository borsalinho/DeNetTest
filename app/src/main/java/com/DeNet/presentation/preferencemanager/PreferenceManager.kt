package com.DeNet.presentation.preferencemanager

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    fun saveNodeId(nodeId: Long) {
        preferences.edit().putLong("node_id", nodeId).apply()
    }

    fun getNodeId(): Long {
        return preferences.getLong("node_id", -1L)
    }
}