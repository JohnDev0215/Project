package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import m1.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1253b = aVar.p(iconCompat.f1253b, 1);
        iconCompat.f1255d = aVar.j(iconCompat.f1255d, 2);
        iconCompat.f1256e = aVar.r(iconCompat.f1256e, 3);
        iconCompat.f1257f = aVar.p(iconCompat.f1257f, 4);
        iconCompat.f1258g = aVar.p(iconCompat.f1258g, 5);
        iconCompat.f1259h = (ColorStateList) aVar.r(iconCompat.f1259h, 6);
        iconCompat.f1261j = aVar.t(iconCompat.f1261j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.x(true, true);
        iconCompat.k(aVar.f());
        int i8 = iconCompat.f1253b;
        if (-1 != i8) {
            aVar.F(i8, 1);
        }
        byte[] bArr = iconCompat.f1255d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1256e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i9 = iconCompat.f1257f;
        if (i9 != 0) {
            aVar.F(i9, 4);
        }
        int i10 = iconCompat.f1258g;
        if (i10 != 0) {
            aVar.F(i10, 5);
        }
        ColorStateList colorStateList = iconCompat.f1259h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.f1261j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
