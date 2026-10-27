package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(androidx.versionedparcelable.a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1235b = aVar.p(iconCompat.f1235b, 1);
        iconCompat.f1237d = aVar.j(iconCompat.f1237d, 2);
        iconCompat.f1238e = aVar.r(iconCompat.f1238e, 3);
        iconCompat.f1239f = aVar.p(iconCompat.f1239f, 4);
        iconCompat.f1240g = aVar.p(iconCompat.f1240g, 5);
        iconCompat.f1241h = (ColorStateList) aVar.r(iconCompat.f1241h, 6);
        iconCompat.j = aVar.t(iconCompat.j, 7);
        iconCompat.e();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, androidx.versionedparcelable.a aVar) {
        aVar.x(true, true);
        iconCompat.f(aVar.f());
        int i2 = iconCompat.f1235b;
        if (-1 != i2) {
            aVar.F(i2, 1);
        }
        byte[] bArr = iconCompat.f1237d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1238e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i3 = iconCompat.f1239f;
        if (i3 != 0) {
            aVar.F(i3, 4);
        }
        int i4 = iconCompat.f1240g;
        if (i4 != 0) {
            aVar.F(i4, 5);
        }
        ColorStateList colorStateList = iconCompat.f1241h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
