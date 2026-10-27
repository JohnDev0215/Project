package com.amazon.music.views.library.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import com.amazon.music.find.utils.PageUriUtils;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GradientOpacityTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/utils/GradientOpacityTransformation;", "Lcom/squareup/picasso/Transformation;", "()V", "key", "", "transform", "Landroid/graphics/Bitmap;", PageUriUtils.SOURCE_QUERY_PARAM, "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GradientOpacityTransformation implements Transformation {
    private static final String KEY = GradientOpacityTransformation.class.getSimpleName();

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int width = source.getWidth();
        float height = source.getHeight();
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, height, Color.parseColor("#00FFFFFF"), Color.parseColor("#99000000"), Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        new Canvas(source).drawRect(0.0f, 0.0f, width, height, paint);
        return source;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        String KEY2 = KEY;
        Intrinsics.checkNotNullExpressionValue(KEY2, "KEY");
        return KEY2;
    }
}
