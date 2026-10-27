package com.amazon.podcast.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes5.dex */
public class PTCPodcastRoundedBorderTransformation implements Transformation {
    private static final String KEY = "PTCPodcastRoundedBorderTransformation";
    private int borderColor;
    private final float cornerRadius;
    private final float cornerRadiusBottom;

    public PTCPodcastRoundedBorderTransformation(final float cornerRadius, final float cornerRadiusBottom, final int borderColor) {
        this.cornerRadius = cornerRadius;
        this.cornerRadiusBottom = cornerRadiusBottom;
        this.borderColor = borderColor;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(final Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setColor(this.borderColor);
        paint.setAntiAlias(true);
        float f = height;
        RectF rectF = new RectF(0.0f, 0.0f, (width * 3) / 5, f);
        float f2 = width;
        RectF rectF2 = new RectF(0.0f, 0.0f, f2, (height * 3) / 5);
        RectF rectF3 = new RectF(0.0f, 0.0f, f2 + 1.0f, f + 1.0f);
        float f3 = this.cornerRadius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        float f4 = this.cornerRadius;
        canvas.drawRoundRect(rectF2, f4, f4, paint);
        float f5 = this.cornerRadiusBottom;
        canvas.drawRoundRect(rectF3, f5, f5, paint);
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
