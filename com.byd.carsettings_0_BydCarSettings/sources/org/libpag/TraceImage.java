package org.libpag;

import android.graphics.Bitmap;
import android.util.Log;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class TraceImage {
    public static void Trace(String str, ByteBuffer byteBuffer, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
        Log.i(str, "Image(width = " + bitmapCreateBitmap.getWidth() + ", height = " + bitmapCreateBitmap.getHeight() + ")");
    }
}
