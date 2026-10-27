package com.android.bluetooth.avrcpcontroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class BipImage {
    private Bitmap mImage;
    private final String mImageHandle;

    public BipImage(String imageHandle, InputStream inputStream) {
        this.mImage = null;
        this.mImageHandle = imageHandle;
        parse(inputStream);
    }

    public BipImage(String imageHandle, Bitmap image) {
        this.mImage = null;
        this.mImageHandle = imageHandle;
        this.mImage = image;
    }

    private void parse(InputStream inputStream) {
        this.mImage = BitmapFactory.decodeStream(inputStream);
    }

    public String getImageHandle() {
        return this.mImageHandle;
    }

    public Bitmap getImage() {
        return this.mImage;
    }
}
