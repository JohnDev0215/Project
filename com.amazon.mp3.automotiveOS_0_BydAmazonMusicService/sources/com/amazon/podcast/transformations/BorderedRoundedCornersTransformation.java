package com.amazon.podcast.transformations;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.amazon.podcast.R$color;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes5.dex */
public final class BorderedRoundedCornersTransformation implements Transformation {
    private static final String KEY = "BorderedRoundedCornersTransformation";
    private final float cornerRadius;
    private final Resources resources;

    public BorderedRoundedCornersTransformation(final float cornerRadius, final Resources resources) {
        this.cornerRadius = cornerRadius;
        this.resources = resources;
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
        RectF rectF = new RectF(1.0f, 1.0f, width - 1, height - 1);
        Paint paint2 = new Paint();
        paint2.setColor(this.resources.getColor(R$color.primary_glass_2));
        paint2.setAntiAlias(true);
        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
        float f = this.cornerRadius;
        canvas.drawRoundRect(rectF2, f, f, paint2);
        float f2 = this.cornerRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
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
