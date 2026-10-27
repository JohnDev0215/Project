package com.amazon.music.explore.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class BlurTransformation implements Transformation {
    private static final String KEY = "BlurTransformation";
    private float blurRadius;
    private Context context;

    public BlurTransformation(Context context, float f) {
        this.context = context;
        this.blurRadius = f;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        Bitmap blurBitmap = BitmapEffectUtils.INSTANCE.getBlurBitmap(this.context, bitmap, this.blurRadius);
        bitmap.recycle();
        return blurBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return KEY;
    }
}
