package com.app.responsimp3.View.Activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.app.responsimp3.R
import com.app.responsimp3.View.Fragment.HistoryFragment
import com.app.responsimp3.View.Fragment.HomeFragment
import com.app.responsimp3.View.Fragment.PaymentFragment
import com.app.responsimp3.View.Fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var ivprofil : ImageView
    private lateinit var tvuser : TextView
    private lateinit var toolbar: Toolbar
    private lateinit var bottomnav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = findViewById(R.id.tbout)
        bottomnav = findViewById(R.id.bottomnav)
        tvuser = findViewById(R.id.tvUser)
        ivprofil = findViewById(R.id.ivprofile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        replacefragment(HomeFragment())
        bottomnav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replacefragment(HomeFragment())
                R.id.payment -> replacefragment(PaymentFragment())
                R.id.history -> replacefragment(HistoryFragment())
                R.id.setting -> replacefragment(SettingFragment())
            }
            true
        }
    }

    private fun replacefragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnLogOut ->{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Peringatan !!! ")
                    .setMessage("Apakah Anda Ingin Keluar ? ")
                    .setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                        Toast.makeText(this, "Sign Out Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }.setNegativeButton("No") { dialog: DialogInterface, which: Int ->
                        dialog.cancel()
                    }.show()
            }
        }
        return true
    }
}