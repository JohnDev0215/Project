package com.amazon.music.curate.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import com.amazon.music.find.utils.PageUriUtils;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornersGradientTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/curate/view/RoundedCornersGradientTransformation;", "Lcom/squareup/picasso/Transformation;", "gradientSize", "", "radius", "", "(IF)V", "key", "", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RoundedCornersGradientTransformation implements Transformation {
    private final int gradientSize;
    private final float radius;

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "gradientImage";
    }

    public RoundedCornersGradientTransformation(int i, float f) {
        this.gradientSize = i;
        this.radius = f;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNull(source);
        int iMin = Math.min(source.getWidth(), source.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(iMin, iMin, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        float f = iMin;
        int i = this.gradientSize;
        LinearGradient linearGradient = new LinearGradient(0.0f, f - i, 0.0f, f - (i * 2.0f), new int[]{0, -1}, (float[]) null, Shader.TileMode.CLAMP);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new ComposeShader(linearGradient, new BitmapShader(source, tileMode, tileMode), PorterDuff.Mode.SRC_IN));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, source.getWidth(), source.getHeight());
        float f2 = this.radius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        source.recycle();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }
}
