package com.infosystest.databinding

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("newsAvatarUrl")
        @JvmStatic
        fun setMovieAvatarUrl(imageView: ImageView, profileAvatarUrl: String) {
            val context = imageView.context
            if (!TextUtils.isEmpty(profileAvatarUrl)) {
                Picasso.with(context)
                    .load(profileAvatarUrl)
                    .into(imageView)
            }
        }
    }
}