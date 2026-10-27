package com.amazon.music.views.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedCornersTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/utils/RoundedCornersTransformation;", "Lcom/squareup/picasso/Transformation;", "cornerRadius", "", "(F)V", "key", "", "transform", "Landroid/graphics/Bitmap;", "bitmap", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RoundedCornersTransformation implements Transformation {
    private static final String KEY = RoundedCornersTransformation.class.getSimpleName();
    private final float cornerRadius;

    public RoundedCornersTransformation(float f) {
        this.cornerRadius = f;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap roundedBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(roundedBitmap);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        float f = this.cornerRadius;
        canvas.drawRoundRect(rectF, f, f, paint);
        if (roundedBitmap != bitmap) {
            bitmap.recycle();
        }
        Intrinsics.checkNotNullExpressionValue(roundedBitmap, "roundedBitmap");
        return roundedBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        String KEY2 = KEY;
        Intrinsics.checkNotNullExpressionValue(KEY2, "KEY");
        return KEY2;
    }
}
