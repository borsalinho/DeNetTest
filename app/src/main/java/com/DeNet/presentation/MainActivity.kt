package com.DeNet.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.DeNet.app.App
import com.DeNet.presentation.ui.MainScreen
import com.DeNet.presentation.viewmodel.MyViewModel

import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        setContent {
            MainScreen(myViewModel)
        }
    }
}




