package com.fourgen.dogapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fourgen.dogapp.R
import com.fourgen.dogapp.databinding.ActivityDogDetailBinding

class DogDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillContent()
    }

    private fun fillContent() {
        val dogUrl = intent.getStringExtra(getString(R.string.breed_url))
        val breedName = intent.getStringExtra(getString(R.string.breed_name))
            ?: getString(R.string.default_breed)

        val actionbar = supportActionBar
        actionbar!!.title = breedName
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(dogUrl).into(binding.ivDog)
        binding.tvDescription.text = getString(R.string.dog_detail, breedName)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}