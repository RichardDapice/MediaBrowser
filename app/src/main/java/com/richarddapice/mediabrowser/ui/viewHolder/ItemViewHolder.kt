package com.richarddapice.mediabrowser.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.richarddapice.mediabrowser.R
import com.richarddapice.mediabrowser.databinding.ItemBinding
import com.richarddapice.mediabrowser.model.Result
import com.richarddapice.mediabrowser.util.Constants

class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {
        binding.apply {
            Glide.with(root.context)
                .load(Constants.IMAGE_URL + result.backdropPath)
                .transform(RoundedCorners(root.context.resources.getDimensionPixelSize(R.dimen.rounded_corner)))
                .into(imageView)

            val title = result.titleName

            tvTitle.text = root.context.getString(
                R.string.media_row_title,
                title,
                result.formattedDate,
                result.voteAverage.toInt()
            )

            root.setOnClickListener {
            }
        }
    }
}
