package com.mirzayogy.practice.ui.jenisbarang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.practice.R
import com.mirzayogy.practice.databinding.ActivityJenisbarangBinding
import com.mirzayogy.practice.model.JenisbarangData

class JenisbarangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJenisbarangBinding
    private val list = ArrayList<JenisbarangData>()
    private val viewModel: JenisbarangViewModel by lazy {
        ViewModelProvider(this).get(JenisbarangViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJenisbarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvJenisbarang.setHasFixedSize(true)

        binding.progressBarJenisbarang.visibility = View.VISIBLE

        getListJenisbarang()

        binding.btTambahJenisbarang.setOnClickListener{
            binding.btTambahJenisbarang.setOnClickListener{
                val intent = Intent(this, JenisbarangPostActivity::class.java)
                intent.putExtra("STATUS","TAMBAH")
                getContract.launch(intent)
            }
        }

    }

    private fun getListJenisbarang() {
        viewModel.getJenisbarang()
        viewModel.response.observe(this, {
            binding.progressBarJenisbarang.visibility = View.INVISIBLE
            list.clear()
            list.addAll(it.data)
            binding.rvJenisbarang.layoutManager = LinearLayoutManager(this)
            val listJenisbarangAdapter = ListJenisbarangAdapter(list)
            binding.rvJenisbarang.adapter = listJenisbarangAdapter
        })
    }

    private val getContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK) {
            getListJenisbarang()
        }
    }
}