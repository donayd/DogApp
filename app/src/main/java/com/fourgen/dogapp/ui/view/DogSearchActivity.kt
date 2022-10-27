package com.fourgen.dogapp.ui.view

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fourgen.dogapp.R
import com.fourgen.dogapp.databinding.ActivityDogSearchBinding

class DogSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {
        binding.btnSearch.setOnClickListener {
            if (checkForInternet()) {
                val breed = binding.tiBreed.text.toString()
                if (breed.isNotEmpty()) {
                    val intent = Intent(this, DogListActivity::class.java)
                    intent.putExtra(getString(R.string.breed_request), breed)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, getString(R.string.any_text), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkForInternet(): Boolean {

        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // Check android version to use the NetworkCapabilities
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Wi-Fi transport or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Cellular transport or Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}