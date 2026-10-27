package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.versionedparcelable.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1956a = aVar.p(iconCompat.f1956a, 1);
        iconCompat.f1958c = aVar.j(iconCompat.f1958c, 2);
        iconCompat.f1959d = aVar.r(iconCompat.f1959d, 3);
        iconCompat.f1960e = aVar.p(iconCompat.f1960e, 4);
        iconCompat.f1961f = aVar.p(iconCompat.f1961f, 5);
        iconCompat.f1962g = (ColorStateList) aVar.r(iconCompat.f1962g, 6);
        iconCompat.f1964i = aVar.t(iconCompat.f1964i, 7);
        iconCompat.f1965j = aVar.t(iconCompat.f1965j, 8);
        iconCompat.c();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.x(true, true);
        iconCompat.d(aVar.f());
        int i7 = iconCompat.f1956a;
        if (-1 != i7) {
            aVar.F(i7, 1);
        }
        byte[] bArr = iconCompat.f1958c;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f1959d;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i8 = iconCompat.f1960e;
        if (i8 != 0) {
            aVar.F(i8, 4);
        }
        int i9 = iconCompat.f1961f;
        if (i9 != 0) {
            aVar.F(i9, 5);
        }
        ColorStateList colorStateList = iconCompat.f1962g;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.f1964i;
        if (str != null) {
            aVar.J(str, 7);
        }
        String str2 = iconCompat.f1965j;
        if (str2 != null) {
            aVar.J(str2, 8);
        }
    }
}
