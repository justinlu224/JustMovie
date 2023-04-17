package com.justin.justmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.justin.justmovie.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController

        navController?.run {
            binding.bottomNav.setupWithNavController(this)
        }

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d(TAG, "destination.id: ${destination.displayName}")
        }

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = ApiRepository.getMovieDetail("")

                if (response.isSuccessful == false) {
                    Log.e(TAG,"api response is fail: ${response.errorBody()}")
                    return@launch
                }

                val result = response.body()

                Log.d(TAG, "api Result: ${result?.title}")

            }catch (e:Exception) {
                Log.e(TAG,"api error: ${e.message}")
            }
        }
    }


    fun step1() {

    }

    fun step2() {
        
    }

}