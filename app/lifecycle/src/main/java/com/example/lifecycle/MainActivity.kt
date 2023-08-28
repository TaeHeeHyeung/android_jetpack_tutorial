package com.example.lifecycle

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.lifecycle.lifecycle.GPSLifeCycle

//class MainActivity : AppCompatActivity(), LifecycleOwner {
class MainActivity : AppCompatActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LifecycleRegistry(this).addObserver(GPSLifeCycle())
    }


}