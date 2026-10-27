package com.amazon.music.picassoimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class CircleTransformation implements Transformation {
    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "circle";
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - iMin) / 2, (bitmap.getHeight() - iMin) / 2, iMin, iMin);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmapCreateBitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        float f = iMin / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        bitmapCreateBitmap.recycle();
        return bitmapCreateBitmap2;
    }
}
