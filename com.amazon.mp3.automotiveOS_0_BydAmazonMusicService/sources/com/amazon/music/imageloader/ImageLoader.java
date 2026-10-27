package com.amazon.music.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.amazon.music.imageloader.transformation.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public interface ImageLoader {

    public interface ImageLoaderCallback {
        void onBitmapLoaded(Bitmap bitmap);

        void onPrepareLoad(Drawable drawable);
    }

    public enum TransformationType {
        ImageOverlay,
        Circle,
        None,
        Custom,
        Blur,
        Blur2,
        RoundCorner
    }

    void loadImage(String str, View view, int i);

    void loadImage(String str, View view, int i, TransformationType transformationType);

    void loadImage(String str, View view, int i, TransformationType transformationType, int i2, Boolean bool);

    void loadImage(String str, ImageLoaderCallback imageLoaderCallback);

    void loadImage(String str, ImageLoaderCallback imageLoaderCallback, TransformationType transformationType);

    void loadImage(String str, ImageLoaderCallback imageLoaderCallback, Transformation transformation);
}
