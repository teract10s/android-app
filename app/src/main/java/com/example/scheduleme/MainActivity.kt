package com.example.scheduleme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.scheduleme.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))
        replaceFragment(Timetable())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.Timetable -> replaceFragment(Timetable())
                R.id.Todos -> replaceFragment(Todos())
                R.id.Exams -> replaceFragment(Exams())

                else -> {
                }
            }
            true
        }
        
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}