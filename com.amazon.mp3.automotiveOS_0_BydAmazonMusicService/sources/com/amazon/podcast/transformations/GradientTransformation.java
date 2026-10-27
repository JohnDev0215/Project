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
public final class GradientTransformation implements Transformation {
    private float bottomOffset;
    private float topOffset;
    private boolean useTopOffset = false;

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "Gradient";
    }

    public GradientTransformation(float bottomOffset) {
        this.bottomOffset = bottomOffset;
    }

    public GradientTransformation(float bottomOffset, float topOffset) {
        this.bottomOffset = bottomOffset;
        this.topOffset = topOffset;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(final Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setShader(new LinearGradient(0.0f, bitmap.getHeight() - this.bottomOffset, 0.0f, bitmap.getHeight() / 2, 0, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint);
        Paint paint2 = new Paint();
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint2.setShader(new LinearGradient(0.0f, bitmap.getHeight(), 0.0f, bitmap.getHeight() - this.bottomOffset, ViewCompat.MEASURED_STATE_MASK, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint2);
        if (this.useTopOffset) {
            Paint paint3 = new Paint();
            paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            paint3.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.topOffset, 0, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
            canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint3);
        }
        bitmap.recycle();
        new Canvas().drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }
}
