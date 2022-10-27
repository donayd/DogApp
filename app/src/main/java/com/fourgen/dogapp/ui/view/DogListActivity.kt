package com.fourgen.dogapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.dreammkr.pokedex.ui.adapter.DogsAdapter
import com.fourgen.dogapp.R
import com.fourgen.dogapp.databinding.ActivityDogListBinding
import com.fourgen.dogapp.ui.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogListBinding
    private val dogViewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillContent()
        initModels()
    }

    private fun fillContent() {
        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.list_activity_name)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun initModels() {

        val breed = intent.getStringExtra(getString(R.string.breed_request))
            ?: getString(R.string.default_breed)

        dogViewModel.onCreate(breed)

        dogViewModel.dogsBreeds.observe(this) { breeds ->
            val dogAdapter = DogsAdapter(breeds, breed)
            binding.rvDogs.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = dogAdapter
            }
        }

        dogViewModel.isLoading.observe(this) {
            binding.avLoading.isVisible = it
        }

        dogViewModel.isFail.observe(this) {
            if (it) {
                Toast.makeText(this, "Raza no encontrada", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DogSearchActivity::class.java))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}