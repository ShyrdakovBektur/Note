package com.itproger.note.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.itproger.note.R
import com.itproger.note.data.local.Pref
import com.itproger.note.databinding.ActivityMainBinding
import kotlinx.coroutines.tasks.asDeferred

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController
    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
    }


    private fun initNavController() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        controller = navHostFragment.navController
        setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_prfile
        )

        if (!pref.isUserSeen()) navHostFragment.navController.navigate(R.id.onBoardFragment)

        binding.navView.setupWithNavController(controller)
        if (FirebaseAuth.getInstance().currentUser == null)
            navHostFragment.navController.navigate(R.id.auth_navigation)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.e("ololo", "onCreate" + it)
        }.addOnFailureListener {
            Log.e("ololo", "onCreate" + it)
        }
    }
}
