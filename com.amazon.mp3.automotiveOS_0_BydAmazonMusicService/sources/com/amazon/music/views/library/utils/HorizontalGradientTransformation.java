package com.amazon.music.views.library.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import androidx.core.view.ViewCompat;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HorizontalGradientTransformation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/utils/HorizontalGradientTransformation;", "Lcom/squareup/picasso/Transformation;", "()V", "key", "", "transform", "Landroid/graphics/Bitmap;", "bitmap", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HorizontalGradientTransformation implements Transformation {
    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "HorizontalGradient";
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          … Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setShader(new LinearGradient(0.0f, 0.0f, bitmap.getWidth() / 2, 0.0f, 0, ViewCompat.MEASURED_STATE_MASK, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight(), paint);
        bitmap.recycle();
        new Canvas().drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }
}
