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
            //todo image url is blocked which is in response and  not working so currently using working image url for example
            var localImage="https://avatars3.githubusercontent.com/u/16484556?s=60&v=4"
            if (!TextUtils.isEmpty(newsAvatarUrl)) {
                Picasso.with(context)
                    .load(localImage)
                    .centerCrop()
                    .fit()
                    .into(imageView)
            }
        }

    }
}