package com.infosystest.databinding

import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.infosystest.R

class BindingAdapters {
    companion object {
        @BindingAdapter("newsAvatarUrl")
        @JvmStatic
        fun setNewsAvatarUrl(imageView: ImageView, newsAvatarUrl: String?) {
            if (!TextUtils.isEmpty(newsAvatarUrl)) {
                Glide
                    .with(imageView.context)
                    .load(newsAvatarUrl!!.replace("http", "https",false))
                    .centerCrop()
                    .placeholder(R.drawable.square_bg)
                    .error( ColorDrawable(ContextCompat.getColor(imageView.context, R.color.colorAccent)))
                    .into(imageView)
            }
        }

    }
}