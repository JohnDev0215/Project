package com.amazon.music.views.library.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AverageColorTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/utils/AverageColorTransformation;", "Lcom/squareup/picasso/Transformation;", "()V", "key", "", "transform", "Landroid/graphics/Bitmap;", "bitmap", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AverageColorTransformation implements Transformation {
    private static final String KEY = AverageColorTransformation.class.getSimpleName();

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        boolean zHasAlpha = bitmap.hasAlpha();
        int width = bitmap.getWidth() * bitmap.getHeight();
        int[] iArr = new int[width];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int height = bitmap.getHeight();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i >= height) {
                break;
            }
            int width2 = bitmap.getWidth();
            for (int i6 = 0; i6 < width2; i6++) {
                int i7 = iArr[(i * width2) + i6];
                i3 += (i7 >> 16) & 255;
                i4 += (i7 >> 8) & 255;
                i5 += i7 & 255;
                if (zHasAlpha) {
                    i2 += i7 >>> 24;
                }
            }
            i++;
        }
        int iArgb = Color.argb(zHasAlpha ? i2 / width : 255, i3 / width, i4 / width, i5 / width);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(iArgb, PorterDuff.Mode.SRC_IN));
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(bitmapResult).drawBitmap(bitmap, 0.0f, 0.0f, paint);
        bitmap.recycle();
        Intrinsics.checkNotNullExpressionValue(bitmapResult, "bitmapResult");
        return bitmapResult;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        String KEY2 = KEY;
        Intrinsics.checkNotNullExpressionValue(KEY2, "KEY");
        return KEY2;
    }
}
