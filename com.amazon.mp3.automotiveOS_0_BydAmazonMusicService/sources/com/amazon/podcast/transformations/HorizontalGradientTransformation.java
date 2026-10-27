package com.amazon.podcast.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import androidx.core.view.ViewCompat;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes5.dex */
public final class HorizontalGradientTransformation implements Transformation {
    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "HorizontalGradient";
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(final Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setShader(new LinearGradient(0.0f, 0.0f, bitmap.getWidth() / 2, 0.0f, 0, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint);
        Paint paint2 = new Paint();
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint2.setShader(new LinearGradient(0.0f, 0.0f, bitmap.getWidth(), 0.0f, ViewCompat.MEASURED_STATE_MASK, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
        bitmap.recycle();
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint2);
        new Canvas().drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }
}
