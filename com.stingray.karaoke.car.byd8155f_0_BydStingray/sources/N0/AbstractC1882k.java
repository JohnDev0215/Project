package n0;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.util.DisplayMetrics;
import o0.AbstractC2007c;
import o0.C2008d;

/* JADX INFO: renamed from: n0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1882k {
    public static final AbstractC2007c a(Bitmap bitmap) {
        AbstractC2007c abstractC2007cB;
        ColorSpace colorSpace = bitmap.getColorSpace();
        return (colorSpace == null || (abstractC2007cB = AbstractC1895x.b(colorSpace)) == null) ? C2008d.c : abstractC2007cB;
    }

    public static final Bitmap b(int i7, int i10, int i11, boolean z6, AbstractC2007c abstractC2007c) {
        return Bitmap.createBitmap((DisplayMetrics) null, i7, i10, AbstractC1861K.F(i11), z6, AbstractC1895x.a(abstractC2007c));
    }
}
