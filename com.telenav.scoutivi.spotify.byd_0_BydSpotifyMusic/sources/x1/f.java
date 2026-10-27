package x1;

import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements n1.m<Bitmap> {
    @Override // n1.m
    public final q1.v<Bitmap> b(Context context, q1.v<Bitmap> vVar, int i7, int i8) {
        if (!k2.k.s(i7, i8)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i7 + " or height: " + i8 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        r1.d dVarF = com.bumptech.glide.c.c(context).f();
        Bitmap bitmap = vVar.get();
        if (i7 == Integer.MIN_VALUE) {
            i7 = bitmap.getWidth();
        }
        if (i8 == Integer.MIN_VALUE) {
            i8 = bitmap.getHeight();
        }
        Bitmap bitmapC = c(dVarF, bitmap, i7, i8);
        return bitmap.equals(bitmapC) ? vVar : e.f(bitmapC, dVarF);
    }

    protected abstract Bitmap c(r1.d dVar, Bitmap bitmap, int i7, int i8);
}
