package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import defpackage.hf;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(hf hfVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.b = hfVar.p(iconCompat.b, 1);
        iconCompat.d = hfVar.j(iconCompat.d, 2);
        iconCompat.e = hfVar.r(iconCompat.e, 3);
        iconCompat.f = hfVar.p(iconCompat.f, 4);
        iconCompat.g = hfVar.p(iconCompat.g, 5);
        iconCompat.h = (ColorStateList) hfVar.r(iconCompat.h, 6);
        iconCompat.j = hfVar.t(iconCompat.j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, hf hfVar) {
        hfVar.x(true, true);
        iconCompat.k(hfVar.f());
        int i = iconCompat.b;
        if (-1 != i) {
            hfVar.F(i, 1);
        }
        byte[] bArr = iconCompat.d;
        if (bArr != null) {
            hfVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.e;
        if (parcelable != null) {
            hfVar.H(parcelable, 3);
        }
        int i2 = iconCompat.f;
        if (i2 != 0) {
            hfVar.F(i2, 4);
        }
        int i3 = iconCompat.g;
        if (i3 != 0) {
            hfVar.F(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.h;
        if (colorStateList != null) {
            hfVar.H(colorStateList, 6);
        }
        String str = iconCompat.j;
        if (str != null) {
            hfVar.J(str, 7);
        }
    }
}
