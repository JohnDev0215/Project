package com.amazon.music.skyfire.ui.transformations;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GradientTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/transformations/GradientTransformation;", "Lcom/amazon/music/skyfire/ui/transformations/BaseTransformation;", "startColor", "", "endColor", "orientation", "(III)V", "getEndColor", "()I", "getOrientation", "getStartColor", "key", "", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GradientTransformation extends BaseTransformation {
    public static final int ORIENTATION_BOTTOM_TO_TOP = 1;
    public static final int ORIENTATION_LEFT_TO_RIGHT = 2;
    public static final int ORIENTATION_RIGHT_TO_LEFT = 3;
    public static final int ORIENTATION_TOP_TO_BOTTOM = 0;
    private final int endColor;
    private final int orientation;
    private final int startColor;

    public /* synthetic */ GradientTransformation(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 2 : i3);
    }

    public final int getStartColor() {
        return this.startColor;
    }

    public final int getEndColor() {
        return this.endColor;
    }

    public final int getOrientation() {
        return this.orientation;
    }

    public GradientTransformation(int i, int i2, int i3) {
        this.startColor = i;
        this.endColor = i2;
        this.orientation = i3;
    }

    @Override // com.amazon.music.skyfire.ui.transformations.BaseTransformation, com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int width = source.getWidth();
        int height = source.getHeight();
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(getOrientation() == 3 ? width : 0.0f, getOrientation() == 1 ? height : 0.0f, getOrientation() == 2 ? width : 0.0f, getOrientation() == 0 ? height : 0.0f, getStartColor(), getEndColor(), Shader.TileMode.MIRROR));
        Bitmap bitmapArgbClone = argbClone(source);
        Canvas canvas = new Canvas(bitmapArgbClone);
        source.recycle();
        canvas.drawRect(0.0f, 0.0f, width, height, paint);
        return bitmapArgbClone;
    }

    @Override // com.amazon.music.skyfire.ui.transformations.BaseTransformation, com.squareup.picasso.Transformation
    public String key() {
        return ((Object) GradientTransformation.class.getCanonicalName()) + " (" + this.startColor + ',' + this.endColor + ',' + this.orientation + ')';
    }
}
