package com.richarddapice.mediabrowser.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.richarddapice.mediabrowser.R
import com.richarddapice.mediabrowser.databinding.ActivityMainBinding
import com.richarddapice.mediabrowser.ui.fragment.MediaBrowseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        supportFragmentManager.commit {
            replace(R.id.main_activity_container, MediaBrowseFragment.newInstance())
        }
    }
}
