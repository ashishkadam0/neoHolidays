package com.kadamab.neos.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kadamab.neos.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var bindings: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater);
        setContentView(bindings.root)
    }
}