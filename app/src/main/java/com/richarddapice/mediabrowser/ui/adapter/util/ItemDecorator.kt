package com.richarddapice.mediabrowser.ui.adapter.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.richarddapice.mediabrowser.R

class ItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val padding = parent.context.resources.getDimensionPixelOffset(R.dimen.item_spacing) / 2
        outRect.right = padding
        outRect.left = padding
    }
}
