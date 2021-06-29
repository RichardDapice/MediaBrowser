package com.richarddapice.mediabrowser.ui.adapter.util

import android.util.SparseArray
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class UnboundedViewPool : RecyclerView.RecycledViewPool() {

    private val scrapHeaps = SparseArray<Queue<RecyclerView.ViewHolder>>()

    override fun clear() {
        scrapHeaps.clear()
    }

    override fun setMaxRecycledViews(viewType: Int, max: Int) {
        throw UnsupportedOperationException(
            "UnboundedViewPool does not support setting a maximum number of recycled views"
        )
    }

    override fun getRecycledView(viewType: Int): RecyclerView.ViewHolder? {
        val scrapHeap = scrapHeaps.get(viewType)
        return scrapHeap?.poll()
    }

    override fun putRecycledView(viewHolder: RecyclerView.ViewHolder) {
        getScrapHeapForType(viewHolder.itemViewType).add(viewHolder)
    }

    override fun getRecycledViewCount(viewType: Int): Int {
        return scrapHeaps.get(viewType)?.size ?: 0
    }

    private fun getScrapHeapForType(viewType: Int): Queue<RecyclerView.ViewHolder> {
        var scrapHeap: Queue<RecyclerView.ViewHolder>? = scrapHeaps.get(viewType)
        if (scrapHeap == null) {
            scrapHeap = LinkedList()
            scrapHeaps.put(viewType, scrapHeap)
        }
        return scrapHeap
    }
}