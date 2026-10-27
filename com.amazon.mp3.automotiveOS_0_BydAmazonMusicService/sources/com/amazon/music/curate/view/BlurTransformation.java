package com.amazon.music.curate.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;

/* JADX INFO: compiled from: BlurTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/music/curate/view/BlurTransformation;", "Lcom/squareup/picasso/Transformation;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "key", "", "transform", "Landroid/graphics/Bitmap;", ContextMappingConstants.IMAGE, "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BlurTransformation implements Transformation {
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 25.0f;
    private final Context context;

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "BlurTransformation";
    }

    public BlurTransformation(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap image) {
        Intrinsics.checkNotNull(image);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(image, MathKt__MathJVMKt.roundToInt(image.getWidth() * BITMAP_SCALE), MathKt__MathJVMKt.roundToInt(image.getHeight() * BITMAP_SCALE), false);
        Bitmap outputBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
        RenderScript renderScriptCreate = RenderScript.create(this.context);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, outputBitmap);
        scriptIntrinsicBlurCreate.setRadius(BLUR_RADIUS);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(outputBitmap);
        image.recycle();
        Intrinsics.checkNotNullExpressionValue(outputBitmap, "outputBitmap");
        return outputBitmap;
    }
}
