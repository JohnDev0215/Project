package com.amazon.music.explore.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.amazon.music.find.utils.PageUriUtils;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornerTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/explore/picasso/RoundedCornerTransformation;", "Lcom/squareup/picasso/Transformation;", "cornerRadius", "", "outlineColor", "outlineWidthPx", "(ILjava/lang/Integer;I)V", "getCornerRadius", "()I", "getOutlineColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOutlineWidthPx", "key", "", "kotlin.jvm.PlatformType", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RoundedCornerTransformation implements Transformation {
    private final int cornerRadius;
    private final Integer outlineColor;
    private final int outlineWidthPx;

    @JvmOverloads
    public RoundedCornerTransformation(int i) {
        this(i, null, 0, 6, null);
    }

    @JvmOverloads
    public RoundedCornerTransformation(int i, Integer num) {
        this(i, num, 0, 4, null);
    }

    @JvmOverloads
    public RoundedCornerTransformation(int i, Integer num, int i2) {
        this.cornerRadius = i;
        this.outlineColor = num;
        this.outlineWidthPx = i2;
    }

    public /* synthetic */ RoundedCornerTransformation(int i, Integer num, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? null : num, (i3 & 4) != 0 ? 1 : i2);
    }

    public final int getCornerRadius() {
        return this.cornerRadius;
    }

    public final Integer getOutlineColor() {
        return this.outlineColor;
    }

    public final int getOutlineWidthPx() {
        return this.outlineWidthPx;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        Bitmap destination = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
        Canvas canvas = new Canvas(destination);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(source, tileMode, tileMode));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, source.getWidth(), source.getHeight());
        int i = this.cornerRadius;
        canvas.drawRoundRect(rectF, i, i, paint);
        Integer num = this.outlineColor;
        if (num != null) {
            int iIntValue = num.intValue();
            Paint paint2 = new Paint();
            paint2.setColor(iIntValue);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setAntiAlias(true);
            paint2.setStrokeWidth(getOutlineWidthPx());
            rectF.inset(getOutlineWidthPx(), getOutlineWidthPx());
            canvas.drawRoundRect(rectF, getCornerRadius(), getCornerRadius(), paint2);
        }
        source.recycle();
        Intrinsics.checkNotNullExpressionValue(destination, "destination");
        return destination;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return RoundedCornerTransformation.class.getSimpleName();
    }
}
