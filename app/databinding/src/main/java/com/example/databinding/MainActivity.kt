package com.example.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.viewmodel.SimpleViewModelSolution


// velog: https://velog.io/write?id=de17fcf3-2b1b-474e-bb34-d886decc3e4c
// 코드 랩: https://developer.android.com/codelabs/android-databinding#1
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[SimpleViewModelSolution::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(binding.root)


        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

    }


}

