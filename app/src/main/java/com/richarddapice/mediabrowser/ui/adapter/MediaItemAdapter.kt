package com.richarddapice.mediabrowser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.richarddapice.mediabrowser.databinding.ItemBinding
import com.richarddapice.mediabrowser.model.Result
import com.richarddapice.mediabrowser.ui.viewHolder.ItemViewHolder

class MediaItemAdapter(private val results: List<Result>) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size
}
