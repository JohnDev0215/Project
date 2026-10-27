package com.byd.trafficmonitor.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class SerialDrawable implements Serializable {
    private static final long serialVersionUID = 1;
    private byte[] mBitmapBytes;

    public SerialDrawable(byte[] bArr) {
        this.mBitmapBytes = null;
        this.mBitmapBytes = bArr;
    }

    public byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public Drawable bitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public Bitmap bytesToBitmap(byte[] bArr) {
        if (bArr.length != 0) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public Drawable bytesToDrawable(byte[] bArr) {
        return bitmapToDrawable(bytesToBitmap(bArr));
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final byte[] drawableToBytes(Drawable drawable) {
        return bitmapToBytes(drawableToBitmap(drawable));
    }

    public byte[] getBitmapBytes() {
        return this.mBitmapBytes;
    }

    public Drawable getDrawable() {
        return bytesToDrawable(this.mBitmapBytes);
    }

    public SerialDrawable(Drawable drawable) {
        this.mBitmapBytes = null;
        this.mBitmapBytes = drawableToBytes(drawable);
    }
}
