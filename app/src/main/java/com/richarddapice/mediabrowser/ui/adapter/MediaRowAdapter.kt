package com.richarddapice.mediabrowser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.richarddapice.mediabrowser.databinding.RowBinding
import com.richarddapice.mediabrowser.model.MediaRow
import com.richarddapice.mediabrowser.ui.adapter.util.ItemDecorator
import com.richarddapice.mediabrowser.ui.adapter.util.RowDiffCallback
import com.richarddapice.mediabrowser.ui.adapter.util.UnboundedViewPool
import com.richarddapice.mediabrowser.ui.viewHolder.RowViewHolder

class MediaRowAdapter : RecyclerView.Adapter<RowViewHolder>() {

    private val rows: MutableList<MediaRow> = mutableListOf()

    private val sharedViewPool = UnboundedViewPool()

    fun setList(rowList: List<MediaRow>) {
        doDiffCallback(rowList)
    }

    private fun doDiffCallback(rowList: List<MediaRow>) {
        val diffCallback = RowDiffCallback(rows, rowList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        rows.clear()
        rows.addAll(rowList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RowViewHolder(RowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply {
                binding.recyclerViewHorizontal.apply {
                    setRecycledViewPool(sharedViewPool)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    addItemDecoration(ItemDecorator())
                }
            }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val item = rows[position]
        holder.binding.run {
            tvTitle.text = item.title
            recyclerViewHorizontal.adapter = MediaItemAdapter(item.results)
        }
    }

    override fun getItemCount() = rows.size
}
