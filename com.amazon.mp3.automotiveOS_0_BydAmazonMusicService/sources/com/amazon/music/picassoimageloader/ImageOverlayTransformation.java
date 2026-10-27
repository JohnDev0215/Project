package com.amazon.music.picassoimageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class ImageOverlayTransformation implements Transformation {
    private static final float[] positions = {0.0f, 0.4f, 0.5f, 0.6f, 0.9f};
    private int[] colors;
    private int maxHeight;

    public ImageOverlayTransformation(Context context, int i) {
        int[] iArr = new int[positions.length];
        this.colors = iArr;
        this.maxHeight = i;
        Resources resources = context.getResources();
        int i2 = R$color.white;
        iArr[0] = resources.getColor(i2);
        this.colors[1] = context.getResources().getColor(i2);
        this.colors[2] = context.getResources().getColor(R$color.white_64);
        this.colors[3] = context.getResources().getColor(R$color.white_20);
        this.colors[4] = context.getResources().getColor(R$color.white_00);
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        float fMin = Math.min(bitmap.getHeight(), this.maxHeight);
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, fMin, this.colors, positions, Shader.TileMode.REPEAT);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), fMin, paint);
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return ImageOverlayTransformation.class.getSimpleName();
    }
}
