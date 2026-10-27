package com.amazon.music.picassoimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class ImageRoundCornerTransformation implements Transformation {
    private Float customCornerRadius = null;

    public void setCornerRadius(Float f) {
        this.customCornerRadius = f;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        float cornerRadius = getCornerRadius(bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    private float getCornerRadius(int i, int i2) {
        Float f = this.customCornerRadius;
        if (f != null) {
            return f.floatValue();
        }
        return Math.min(i, i2) > 100 ? 8.0f : 4.0f;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return ImageRoundCornerTransformation.class.getSimpleName();
    }
}
