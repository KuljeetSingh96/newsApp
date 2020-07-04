package com.infosystest.databinding

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("newsAvatarUrl")
        @JvmStatic
        fun setNewsAvatarUrl(imageView: ImageView, newsAvatarUrl: String?) {
            val context = imageView.context
            if (!TextUtils.isEmpty(newsAvatarUrl)) {
                Picasso.with(context)
                    .load(newsAvatarUrl)
                    .centerCrop()
                    .fit()
                    .into(imageView)
            }
        }

    }
}