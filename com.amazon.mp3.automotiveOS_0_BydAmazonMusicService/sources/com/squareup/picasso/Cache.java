package com.squareup.picasso;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes7.dex */
public interface Cache {
    void clearKeyUri(String str);

    Bitmap get(String str);

    int maxSize();

    void set(String str, Bitmap bitmap);

    int size();
}
