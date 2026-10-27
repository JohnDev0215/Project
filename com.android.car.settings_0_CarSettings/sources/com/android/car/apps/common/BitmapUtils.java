package com.android.car.apps.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Size;

/* JADX INFO: loaded from: classes5.dex */
public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    public static Bitmap scaleBitmap(Bitmap bm, int width, int height) {
        if (bm == null || (bm.getHeight() == height && bm.getWidth() == width)) {
            return bm;
        }
        float heightScale = 1.0f;
        if (bm.getHeight() > height) {
            heightScale = height / bm.getHeight();
        }
        float widthScale = 1.0f;
        if (bm.getWidth() > width) {
            widthScale = width / bm.getWidth();
        }
        float scale = heightScale > widthScale ? heightScale : widthScale;
        int scaleWidth = (int) Math.ceil(bm.getWidth() * scale);
        int scaleHeight = (int) Math.ceil(bm.getHeight() * scale);
        Bitmap scaledBm = bm;
        if (scale < 0.5f) {
            scaledBm = Bitmap.createScaledBitmap(scaledBm, scaleWidth * 2, scaleHeight * 2, true);
        }
        if (scale != 1.0f) {
            Bitmap newScaledBitmap = Bitmap.createScaledBitmap(scaledBm, scaleWidth, scaleHeight, true);
            if (scaledBm != bm) {
                scaledBm.recycle();
            }
            return newScaledBitmap;
        }
        return scaledBm;
    }

    public static Bitmap cropBitmap(Bitmap bm, int width, int height) {
        if (bm == null) {
            return bm;
        }
        if (bm.getHeight() < height || bm.getWidth() < width) {
            if (Log.isLoggable(TAG, 4)) {
                Log.i(TAG, String.format("Can't crop bitmap to larger dimensions (%d, %d) -> (%d, %d).", Integer.valueOf(bm.getWidth()), Integer.valueOf(bm.getHeight()), Integer.valueOf(width), Integer.valueOf(height)));
            }
            return bm;
        }
        int x = (bm.getWidth() - width) / 2;
        int y = (bm.getHeight() - height) / 2;
        return Bitmap.createBitmap(bm, x, y, width, height);
    }

    public static Bitmap createTintedBitmap(Bitmap image, int colorOverlay) {
        if (image == null) {
            return null;
        }
        Bitmap clone = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(clone);
        canvas.drawBitmap(image, 0.0f, 0.0f, new Paint());
        canvas.drawColor(colorOverlay);
        return clone;
    }

    public static Drawable maybeFlagDrawable(Context context, Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            CommonFlags flags = CommonFlags.getInstance(context);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (flags.shouldFlagImproperImageRefs() && bitmap != null) {
                Resources res = context.getResources();
                int tint = context.getColor(R.color.improper_image_refs_tint_color);
                return new BitmapDrawable(res, createTintedBitmap(bitmap, tint));
            }
            return drawable;
        }
        return drawable;
    }

    public static Bitmap fromDrawable(Drawable drawable, Size bitmapSize) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        Matrix matrix = new Matrix();
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmapSize = new Size(1, 1);
            drawable.setBounds(0, 0, bitmapSize.getWidth(), bitmapSize.getHeight());
        } else {
            if (bitmapSize == null) {
                bitmapSize = new Size(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            RectF srcR = new RectF(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            RectF dstR = new RectF(0.0f, 0.0f, bitmapSize.getWidth(), bitmapSize.getHeight());
            matrix.setRectToRect(srcR, dstR, Matrix.ScaleToFit.CENTER);
        }
        Bitmap bitmap = Bitmap.createBitmap(bitmapSize.getWidth(), bitmapSize.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.setMatrix(matrix);
        drawable.draw(canvas);
        return bitmap;
    }
}
