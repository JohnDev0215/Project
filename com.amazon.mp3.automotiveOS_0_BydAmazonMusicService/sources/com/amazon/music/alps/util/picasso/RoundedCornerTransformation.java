package com.amazon.music.alps.util.picasso;

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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornerTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/alps/util/picasso/RoundedCornerTransformation;", "Lcom/squareup/picasso/Transformation;", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "transform", "", "kotlin.jvm.PlatformType", "key", "", "cornerRadius", "I", "getCornerRadius", "()I", "outlineColor", "Ljava/lang/Integer;", "getOutlineColor", "()Ljava/lang/Integer;", "outlineWidthPx", "getOutlineWidthPx", "<init>", "(ILjava/lang/Integer;I)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class RoundedCornerTransformation implements Transformation {
    private final int cornerRadius;
    private final Integer outlineColor;
    private final int outlineWidthPx;

    @JvmOverloads
    public RoundedCornerTransformation(int i, Integer num, int i2) {
        this.cornerRadius = i;
        this.outlineColor = num;
        this.outlineWidthPx = i2;
    }

    public final int getCornerRadius() {
        return this.cornerRadius;
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
