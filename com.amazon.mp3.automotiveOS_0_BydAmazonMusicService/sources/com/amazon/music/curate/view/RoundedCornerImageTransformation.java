package com.amazon.music.curate.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.amazon.music.find.utils.PageUriUtils;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornerImageTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/curate/view/RoundedCornerImageTransformation;", "Lcom/squareup/picasso/Transformation;", "radius", "", "(F)V", "key", "", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RoundedCornerImageTransformation implements Transformation {
    private final float radius;

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "roundedCorner";
    }

    public RoundedCornerImageTransformation(float f) {
        this.radius = f;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNull(source);
        int iMin = Math.min(source.getWidth(), source.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(source, (source.getWidth() - iMin) / 2, (source.getHeight() - iMin) / 2, iMin, iMin);
        if (!Intrinsics.areEqual(bitmapCreateBitmap, source)) {
            source.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(iMin, iMin, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmapCreateBitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, source.getWidth(), source.getHeight());
        float f = this.radius;
        canvas.drawRoundRect(rectF, f, f, paint);
        bitmapCreateBitmap.recycle();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }
}
