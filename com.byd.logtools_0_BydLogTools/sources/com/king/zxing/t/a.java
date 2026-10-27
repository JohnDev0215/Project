package com.king.zxing.t;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.king.zxing.h;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: CodeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static Bitmap a(Bitmap bitmap, Bitmap bitmap2, float f2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f3 = (width * f2) / width2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f3, f3, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            Log.w(h.f4603a, e2.getMessage());
            return null;
        }
    }

    public static Bitmap b(String str, int i) {
        return c(str, i, null);
    }

    public static Bitmap c(String str, int i, Bitmap bitmap) {
        return f(str, i, bitmap, -16777216);
    }

    public static Bitmap d(String str, int i, Bitmap bitmap, float f2, int i2) {
        HashMap map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        map.put(EncodeHintType.MARGIN, 1);
        return e(str, i, bitmap, f2, map, i2);
    }

    public static Bitmap e(String str, int i, Bitmap bitmap, float f2, Map<EncodeHintType, ?> map, int i2) {
        try {
            b bVarA = new com.google.zxing.p.b().a(str, BarcodeFormat.QR_CODE, i, i, map);
            int[] iArr = new int[i * i];
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    if (bVarA.d(i4, i3)) {
                        iArr[(i3 * i) + i4] = i2;
                    } else {
                        iArr[(i3 * i) + i4] = -1;
                    }
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return bitmap != null ? a(bitmapCreateBitmap, bitmap, f2) : bitmapCreateBitmap;
        } catch (WriterException e2) {
            Log.w(h.f4603a, e2.getMessage());
            return null;
        }
    }

    public static Bitmap f(String str, int i, Bitmap bitmap, int i2) {
        return d(str, i, bitmap, 0.2f, i2);
    }
}
