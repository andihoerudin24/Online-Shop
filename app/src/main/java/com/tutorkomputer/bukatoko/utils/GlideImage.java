package com.tutorkomputer.bukatoko.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.tutorkomputer.bukatoko.R;

public class GlideImage {

    public static void get(Context context, String urlimage, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.no_profile)
                .error(R.drawable.no_profile)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(urlimage)
                .apply(options)
                .into(imageView);

    }
}
