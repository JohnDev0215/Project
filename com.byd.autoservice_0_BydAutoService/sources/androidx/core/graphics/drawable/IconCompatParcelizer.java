package androidx.core.graphics.drawable;

import a1.a;
import android.content.res.ColorStateList;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1164b = aVar.p(iconCompat.f1164b, 1);
        iconCompat.f1166d = aVar.j(iconCompat.f1166d, 2);
        iconCompat.f1167e = aVar.r(iconCompat.f1167e, 3);
        iconCompat.f1168f = aVar.p(iconCompat.f1168f, 4);
        iconCompat.f1169g = aVar.p(iconCompat.f1169g, 5);
        iconCompat.f1170h = (ColorStateList) aVar.r(iconCompat.f1170h, 6);
        iconCompat.f1172j = aVar.t(iconCompat.f1172j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.x(true, true);
        iconCompat.k(aVar.f());
        int i3 = iconCompat.f1164b;
        if (-1 != i3) {
            aVar.F(i3, 1);
        }
        byte[] bArr = iconCompat.f1166d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1167e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i4 = iconCompat.f1168f;
        if (i4 != 0) {
            aVar.F(i4, 4);
        }
        int i5 = iconCompat.f1169g;
        if (i5 != 0) {
            aVar.F(i5, 5);
        }
        ColorStateList colorStateList = iconCompat.f1170h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.f1172j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
