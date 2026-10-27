package com.amazon.music.find.converter.transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BitmapTransformationUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/find/converter/transformation/BitmapTransformationUtil;", "", "()V", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BitmapTransformationUtil {
    public static final BitmapTransformationUtil INSTANCE = new BitmapTransformationUtil();

    private BitmapTransformationUtil() {
    }

    public final Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int iMin = Math.min(source.getWidth(), source.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(source, (source.getWidth() - iMin) / 2, (source.getHeight() - iMin) / 2, iMin, iMin);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmapCreateBitmap, tileMode, tileMode));
        paint.setAntiAlias(true);
        float f = iMin / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        return bitmapCreateBitmap2;
    }
}
