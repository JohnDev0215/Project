package com.amazon.music.explore.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class CircularTransformation implements Transformation {
    private static final String KEY = "CircularTransformation";

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        float f = iMin / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return KEY;
    }
}
