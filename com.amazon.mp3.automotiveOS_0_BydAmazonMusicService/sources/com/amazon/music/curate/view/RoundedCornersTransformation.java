package com.amazon.music.curate.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class RoundedCornersTransformation implements Transformation {
    private static final String KEY = "RoundedCornersTransformation";
    private final float cornerRadius;

    public RoundedCornersTransformation(final float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(final Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        float f = this.cornerRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
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
