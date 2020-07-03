package com.infosystest.databinding

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("newsAvatarUrl")
        @JvmStatic
        fun setMovieAvatarUrl(imageView: ImageView, imageAvatarUrl: String) {
            val context = imageView.context
            if (!TextUtils.isEmpty(imageAvatarUrl)) {
                Picasso.with(context)
                    .load(imageAvatarUrl)
                    .centerCrop()
                    .fit()
                    .into(imageView)
            }
        }

        @BindingAdapter("descriptionText")
        @JvmStatic
        fun setIntegerText(textView: TextView, descriptionText: String) {
            textView.setText(descriptionText)
        }
    }
}