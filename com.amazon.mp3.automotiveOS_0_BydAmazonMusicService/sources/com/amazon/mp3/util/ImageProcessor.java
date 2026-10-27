package com.amazon.mp3.util;

import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public interface ImageProcessor extends Transformation {
    void process(BitmapDrawable drawable, ImageView... imageViews);
}
