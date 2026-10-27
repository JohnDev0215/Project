package com.amazon.music.skyfire.ui.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverlayTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/transformations/OverlayTransformation;", "Lcom/amazon/music/skyfire/ui/transformations/BaseTransformation;", "color", "", "(I)V", "getColor", "()I", "key", "", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OverlayTransformation extends BaseTransformation {
    private final int color;

    public final int getColor() {
        return this.color;
    }

    public OverlayTransformation(int i) {
        this.color = i;
    }

    @Override // com.amazon.music.skyfire.ui.transformations.BaseTransformation, com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int width = source.getWidth();
        int height = source.getHeight();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        Bitmap bitmapArgbClone = argbClone(source);
        Canvas canvas = new Canvas(bitmapArgbClone);
        source.recycle();
        canvas.drawRect(0.0f, 0.0f, width, height, paint);
        return bitmapArgbClone;
    }

    @Override // com.amazon.music.skyfire.ui.transformations.BaseTransformation, com.squareup.picasso.Transformation
    public String key() {
        return ((Object) OverlayTransformation.class.getCanonicalName()) + " (" + this.color + ')';
    }
}
