package com.byd.music.view.jingyun.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes5.dex */
public class FileUitl {
    public static File bitmapToPath(Bitmap bitmap, String str) {
        File file = new File(str);
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void drawSmallPoint(Bitmap bitmap, int i, int i2, int i3) {
        drawPoint(bitmap, i, i2, 1, i3);
    }

    public static void drawPoint(Bitmap bitmap, int i, int i2, int i3, int i4) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(i4);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(i - i3, i2 - i3, i + i3, i2 + i3, paint);
    }

    public static void drawRect(Bitmap bitmap, Rect rect, int i) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(i);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);
    }

    public static Bitmap cropBitmapY(Bitmap bitmap, double d, double d2) {
        return Bitmap.createBitmap(bitmap, 0, (int) (((double) bitmap.getHeight()) * d), bitmap.getWidth(), (int) (((double) bitmap.getHeight()) * d2));
    }
}
