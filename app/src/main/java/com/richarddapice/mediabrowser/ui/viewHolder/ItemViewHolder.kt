package com.richarddapice.mediabrowser.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.richarddapice.mediabrowser.R
import com.richarddapice.mediabrowser.databinding.ItemBinding
import com.richarddapice.mediabrowser.model.MediaRow
import com.richarddapice.mediabrowser.model.MediaType
import com.richarddapice.mediabrowser.model.Result
import com.richarddapice.mediabrowser.util.Constants

class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {
        binding.apply {
            Glide.with(root.context)
                .load(Constants.IMAGE_URL + result.backdropPath)
                .transform(
                    RoundedCorners(root.context.resources.getDimensionPixelSize(R.dimen.rounded_corner))
                )
                .into(imageView)


            if (result.mediaType == MediaType.TV.type) {
                tvTitle.text = root.context.getString(
                    R.string.media_row_title,
                    result.name,
                    result.firstAirDate,
                    result.voteAverage.toInt()
                )
            } else {
                tvTitle.text = root.context.getString(
                    R.string.media_row_title,
                    result.title,
                    result.releaseDate.take(4),
                    result.voteAverage.toInt()
                )
            }
        }
    }
}