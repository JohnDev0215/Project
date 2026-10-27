package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import b.s.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f331b = aVar.p(iconCompat.f331b, 1);
        iconCompat.f333d = aVar.j(iconCompat.f333d, 2);
        iconCompat.e = aVar.r(iconCompat.e, 3);
        iconCompat.f = aVar.p(iconCompat.f, 4);
        iconCompat.g = aVar.p(iconCompat.g, 5);
        iconCompat.h = (ColorStateList) aVar.r(iconCompat.h, 6);
        iconCompat.j = aVar.t(iconCompat.j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.x(true, true);
        iconCompat.k(aVar.f());
        int i = iconCompat.f331b;
        if (-1 != i) {
            aVar.F(i, 1);
        }
        byte[] bArr = iconCompat.f333d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i2 = iconCompat.f;
        if (i2 != 0) {
            aVar.F(i2, 4);
        }
        int i3 = iconCompat.g;
        if (i3 != 0) {
            aVar.F(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
