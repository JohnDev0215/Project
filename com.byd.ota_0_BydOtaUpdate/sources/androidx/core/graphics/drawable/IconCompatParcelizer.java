package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import b.q.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f454b = aVar.p(iconCompat.f454b, 1);
        iconCompat.f456d = aVar.j(iconCompat.f456d, 2);
        iconCompat.f457e = aVar.r(iconCompat.f457e, 3);
        iconCompat.f458f = aVar.p(iconCompat.f458f, 4);
        iconCompat.f459g = aVar.p(iconCompat.f459g, 5);
        iconCompat.f460h = (ColorStateList) aVar.r(iconCompat.f460h, 6);
        iconCompat.j = aVar.t(iconCompat.j, 7);
        iconCompat.j();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.x(true, true);
        iconCompat.k(aVar.f());
        int i2 = iconCompat.f454b;
        if (-1 != i2) {
            aVar.F(i2, 1);
        }
        byte[] bArr = iconCompat.f456d;
        if (bArr != null) {
            aVar.B(bArr, 2);
        }
        Parcelable parcelable = iconCompat.f457e;
        if (parcelable != null) {
            aVar.H(parcelable, 3);
        }
        int i3 = iconCompat.f458f;
        if (i3 != 0) {
            aVar.F(i3, 4);
        }
        int i4 = iconCompat.f459g;
        if (i4 != 0) {
            aVar.F(i4, 5);
        }
        ColorStateList colorStateList = iconCompat.f460h;
        if (colorStateList != null) {
            aVar.H(colorStateList, 6);
        }
        String str = iconCompat.j;
        if (str != null) {
            aVar.J(str, 7);
        }
    }
}
