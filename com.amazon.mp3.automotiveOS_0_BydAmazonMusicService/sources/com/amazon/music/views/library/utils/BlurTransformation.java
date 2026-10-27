package com.amazon.music.views.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BlurTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/utils/BlurTransformation;", "Lcom/squareup/picasso/Transformation;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "renderScript", "Landroid/renderscript/RenderScript;", "blurredBitmap", "Landroid/graphics/Bitmap;", "bitmap", "getBlurBitmap", "blurRadius", "", "key", "", "transform", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BlurTransformation implements Transformation {
    private static final String KEY = BlurTransformation.class.getSimpleName();
    private final Context context;
    private RenderScript renderScript;

    public BlurTransformation(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return blurredBitmap(bitmap);
    }

    private final Bitmap blurredBitmap(Bitmap bitmap) {
        this.renderScript = RenderScript.create(this.context);
        Bitmap blurBitmap = getBlurBitmap(bitmap, 25.0f);
        bitmap.recycle();
        Bitmap blurBitmap2 = getBlurBitmap(blurBitmap, 25.0f);
        blurBitmap.recycle();
        Bitmap blurBitmap3 = getBlurBitmap(blurBitmap2, 25.0f);
        RenderScript renderScript = this.renderScript;
        if (renderScript != null) {
            renderScript.destroy();
        }
        return blurBitmap3;
    }

    private final Bitmap getBlurBitmap(Bitmap bitmap, float blurRadius) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.4f), (int) (bitmap.getHeight() * 0.4f), false);
        bitmap.recycle();
        Bitmap outputBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
        bitmapCreateScaledBitmap.setConfig(Bitmap.Config.ARGB_8888);
        outputBitmap.setConfig(Bitmap.Config.ARGB_8888);
        RenderScript renderScript = this.renderScript;
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(this.renderScript, bitmapCreateScaledBitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(this.renderScript, outputBitmap);
        scriptIntrinsicBlurCreate.setRadius(blurRadius);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(outputBitmap);
        bitmapCreateScaledBitmap.recycle();
        allocationCreateFromBitmap.destroy();
        allocationCreateFromBitmap2.destroy();
        scriptIntrinsicBlurCreate.destroy();
        Intrinsics.checkNotNullExpressionValue(outputBitmap, "outputBitmap");
        return outputBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        String KEY2 = KEY;
        Intrinsics.checkNotNullExpressionValue(KEY2, "KEY");
        return KEY2;
    }
}
