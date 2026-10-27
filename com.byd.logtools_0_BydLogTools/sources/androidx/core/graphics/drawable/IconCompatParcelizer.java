package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(androidx.versionedparcelable.a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1341b = aVar.p(iconCompat.f1341b, 1);
        iconCompat.f1343d = aVar.j(iconCompat.f1343d, 2);
        iconCompat.f1344e = aVar.r(iconCompat.f1344e, 3);
        iconCompat.f1345f = aVar.p(iconCompat.f1345f, 4);
        iconCompat.f1346g = aVar.p(iconCompat.f1346g, 5);
        iconCompat.f1347h = (ColorStateList) aVar.r(iconCompat.f1347h, 6);
        iconCompat.j = aVar.t(iconCompat.j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(true, true);
        iconCompat.k(aVar.f());
        int i = iconCompat.f1341b;
        if (-1 != i) {
            aVar.F(i, 1);
        }
        byte[] bArr = iconCompat.f1343d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1344e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i2 = iconCompat.f1345f;
        if (i2 != 0) {
            aVar.F(i2, 4);
        }
        int i3 = iconCompat.f1346g;
        if (i3 != 0) {
            aVar.F(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.f1347h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
