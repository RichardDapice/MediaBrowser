package com.richarddapice.mediabrowser.ui.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.richarddapice.mediabrowser.model.MediaRow
import com.richarddapice.mediabrowser.model.Result

class RowDiffCallback(
    private val oldRows: MutableList<MediaRow>,
    private val newRows: List<MediaRow>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldRows.size

    override fun getNewListSize() = newRows.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ITEM_CALLBACK.areItemsTheSame(oldRows[oldItemPosition], newRows[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        ITEM_CALLBACK.areContentsTheSame(oldRows[oldItemPosition], newRows[newItemPosition])
}

private val ITEM_CALLBACK: DiffUtil.ItemCallback<MediaRow> =
    object : DiffUtil.ItemCallback<MediaRow>() {
        override fun areItemsTheSame(oldItem: MediaRow, newItem: MediaRow) = oldItem == newItem

        override fun areContentsTheSame(oldItem: MediaRow, newItem: MediaRow): Boolean {
            oldItem.results.forEachIndexed { i: Int, result: Result ->
                if (result.id != newItem.results[i].id) return false
            }
            return true
        }
    }
