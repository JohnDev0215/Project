package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import b.z.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f801b = aVar.p(iconCompat.f801b, 1);
        iconCompat.f803d = aVar.j(iconCompat.f803d, 2);
        iconCompat.f804e = aVar.r(iconCompat.f804e, 3);
        iconCompat.f805f = aVar.p(iconCompat.f805f, 4);
        iconCompat.f806g = aVar.p(iconCompat.f806g, 5);
        iconCompat.f807h = (ColorStateList) aVar.r(iconCompat.f807h, 6);
        iconCompat.j = aVar.t(iconCompat.j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.x(true, true);
        iconCompat.k(aVar.f());
        int i2 = iconCompat.f801b;
        if (-1 != i2) {
            aVar.F(i2, 1);
        }
        byte[] bArr = iconCompat.f803d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f804e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i3 = iconCompat.f805f;
        if (i3 != 0) {
            aVar.F(i3, 4);
        }
        int i4 = iconCompat.f806g;
        if (i4 != 0) {
            aVar.F(i4, 5);
        }
        ColorStateList colorStateList = iconCompat.f807h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
