package spi;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public abstract class z1 implements qt<Bitmap> {
    @Override // spi.qt
    @NonNull
    public final wm<Bitmap> b(@NonNull Context context, @NonNull wm<Bitmap> wmVar, int i, int i2) {
        if (!tu.j(i, i2)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i + " or height: " + i2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        w1 w1Var = com.bumptech.glide.a.b(context).a;
        Bitmap bitmap = wmVar.get();
        if (i == Integer.MIN_VALUE) {
            i = bitmap.getWidth();
        }
        if (i2 == Integer.MIN_VALUE) {
            i2 = bitmap.getHeight();
        }
        Bitmap bitmapC = c(w1Var, bitmap, i, i2);
        return bitmap.equals(bitmapC) ? wmVar : y1.b(bitmapC, w1Var);
    }

    public abstract Bitmap c(@NonNull w1 w1Var, @NonNull Bitmap bitmap, int i, int i2);
}
