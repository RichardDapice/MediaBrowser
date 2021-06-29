package com.richarddapice.mediabrowser.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.richarddapice.mediabrowser.databinding.FragmentBrowseBinding
import com.richarddapice.mediabrowser.model.MediaList
import com.richarddapice.mediabrowser.model.MediaRow
import com.richarddapice.mediabrowser.ui.adapter.MediaRowAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MediaBrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MediaBrowseViewModel by viewModels()
    private lateinit var mediaRowAdapter: MediaRowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        mediaRowAdapter = MediaRowAdapter()

        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mediaRowAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        watchForData()
    }

    private fun watchForData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trendingMoviesState.collect {
                    when (it) {
                        is UiState.Success -> updateUI("Trending Movies", it.mediaList)
                        is UiState.Error -> {
                        } // TODO Show Error
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trendingShowsState.collect {
                    when (it) {
                        is UiState.Success -> updateUI("Trending Shows", it.mediaList)
                        is UiState.Error -> {
                        } // TODO Show Error
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trendingTodayState.collect {
                    when (it) {
                        is UiState.Success -> updateUI("Popular Today", it.mediaList)
                        is UiState.Error -> {
                        } // TODO Show Error
                    }
                }
            }
        }
    }

    private val mediaRows = mutableListOf<MediaRow>()

    // TODO Just adding a bunch of rows and stuff for now.
    private fun updateUI(title: String, mediaList: MediaList?) {
        mediaList?.results?.run {
            when {
                mediaRows.isEmpty() -> {
                    mediaRows.apply {
                        add(MediaRow(title, this@run))
                    }
                }
                else -> {
                    mediaRows.apply {
                        dropWhile { it.title == title }
                        add(MediaRow(title, this@run))
                    }
                }
            }
            val mediaRows = mediaRows + mediaRows
            mediaRowAdapter.setList(mediaRows)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MediaBrowseFragment()
    }
}
