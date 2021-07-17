package com.mirzayogy.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mirzayogy.practice.databinding.ActivityMainBinding
import com.mirzayogy.practice.ui.user.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel1: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE

        viewModel1.getUsers()
        viewModel1.response.observe(this, {
            binding.progressBar.visibility = View.INVISIBLE
            binding.tvMainActivity.text = it.data[0].first_name
        })

    }
}