package com.amazon.music.explore.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class Blur2Transformation implements Transformation {
    private static final float BITMAP_SCALE = 0.4f;
    private static final String KEY = "Blur2Transformation";
    private int blurRadius = 25;
    private final RenderScript renderScript;

    public Blur2Transformation(Context context) {
        this.renderScript = RenderScript.create(context);
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        return blurredBitmap(bitmap);
    }

    private Bitmap blurredBitmap(Bitmap bitmap) {
        Bitmap blurBitmap = getBlurBitmap(bitmap, this.blurRadius);
        bitmap.recycle();
        Bitmap blurBitmap2 = getBlurBitmap(blurBitmap, this.blurRadius);
        blurBitmap.recycle();
        Bitmap blurBitmap3 = getBlurBitmap(blurBitmap2, this.blurRadius);
        blurBitmap2.recycle();
        this.renderScript.destroy();
        return blurBitmap3;
    }

    private Bitmap getBlurBitmap(Bitmap bitmap, float f) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * BITMAP_SCALE), (int) (bitmap.getHeight() * BITMAP_SCALE), false);
        bitmap.recycle();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
        bitmapCreateScaledBitmap.setConfig(Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setConfig(Bitmap.Config.ARGB_8888);
        RenderScript renderScript = this.renderScript;
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(this.renderScript, bitmapCreateScaledBitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(this.renderScript, bitmapCreateBitmap);
        scriptIntrinsicBlurCreate.setRadius(f);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
        allocationCreateFromBitmap.destroy();
        allocationCreateFromBitmap2.destroy();
        scriptIntrinsicBlurCreate.destroy();
        bitmapCreateScaledBitmap.recycle();
        return bitmapCreateBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return KEY;
    }
}
