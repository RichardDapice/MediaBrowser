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
import com.richarddapice.mediabrowser.R
import com.richarddapice.mediabrowser.databinding.FragmentBrowseBinding
import com.richarddapice.mediabrowser.model.MediaList
import com.richarddapice.mediabrowser.model.MediaRow
import com.richarddapice.mediabrowser.ui.adapter.MediaRowAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MediaBrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MediaBrowseViewModel by viewModels()
    private lateinit var mediaRowAdapter: MediaRowAdapter

    private val mediaRows = mutableListOf<MediaRow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.run {
            subscribeTo(getString(R.string.row_trending_movies), trendingMoviesState)
            subscribeTo(getString(R.string.row_trending_shows), trendingShowsState)
            subscribeTo(getString(R.string.row_trending_today), trendingTodayState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        mediaRowAdapter = MediaRowAdapter()

        bindRecyclerView()

        return binding.root
    }

    private fun subscribeTo(rowTitle: String, stateFlow: StateFlow<UiState>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlow.collect {
                    when (it) {
                        is UiState.Success -> updateUI(rowTitle, it.mediaList)
                        is UiState.Error -> Timber.e(it.exception)
                    }
                }
            }
        }
    }

    private fun bindRecyclerView() {
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mediaRowAdapter
        }
    }

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
            mediaRowAdapter.setList(mediaRows)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onStop() {
        mediaRows.clear()
        super.onStop()
    }

    companion object {
        fun newInstance() = MediaBrowseFragment()
    }
}
