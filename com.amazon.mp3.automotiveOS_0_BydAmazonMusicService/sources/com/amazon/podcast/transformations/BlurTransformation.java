package com.amazon.podcast.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes5.dex */
public class BlurTransformation implements Transformation {
    private static final String KEY = "BlurTransformation";
    private Context context;
    private RenderScript renderScript;

    public BlurTransformation(final Context context) {
        this.context = context;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(final Bitmap bitmap) {
        return blurredBitmap(bitmap);
    }

    private Bitmap blurredBitmap(final Bitmap bitmap) {
        this.renderScript = RenderScript.create(this.context);
        Bitmap blurBitmap = getBlurBitmap(bitmap, 25.0f);
        bitmap.recycle();
        Bitmap blurBitmap2 = getBlurBitmap(blurBitmap, 25.0f);
        blurBitmap.recycle();
        Bitmap blurBitmap3 = getBlurBitmap(blurBitmap2, 25.0f);
        this.renderScript.destroy();
        return blurBitmap3;
    }

    private Bitmap getBlurBitmap(final Bitmap bitmap, final float blurRadius) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.4f), (int) (bitmap.getHeight() * 0.4f), false);
        bitmap.recycle();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
        bitmapCreateScaledBitmap.setConfig(Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setConfig(Bitmap.Config.ARGB_8888);
        RenderScript renderScript = this.renderScript;
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(this.renderScript, bitmapCreateScaledBitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(this.renderScript, bitmapCreateBitmap);
        scriptIntrinsicBlurCreate.setRadius(blurRadius);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
        bitmapCreateScaledBitmap.recycle();
        allocationCreateFromBitmap.destroy();
        allocationCreateFromBitmap2.destroy();
        scriptIntrinsicBlurCreate.destroy();
        return bitmapCreateBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return KEY;
    }
}
