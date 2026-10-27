package com.amazon.music.imageloader.transformation;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes4.dex */
public interface Transformation {
    String key();

    Bitmap transform(Bitmap bitmap);
}
