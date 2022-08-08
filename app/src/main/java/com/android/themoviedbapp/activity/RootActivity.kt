package com.android.themoviedbapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.themoviedbapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_layout)
    }
}