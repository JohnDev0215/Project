package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import defpackage.kl3;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(kl3 kl3Var) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.a = kl3Var.j(iconCompat.a, 1);
        byte[] bArrF = iconCompat.c;
        if (kl3Var.h(2)) {
            bArrF = kl3Var.f();
        }
        iconCompat.c = bArrF;
        iconCompat.d = kl3Var.l(iconCompat.d, 3);
        iconCompat.e = kl3Var.j(iconCompat.e, 4);
        iconCompat.f = kl3Var.j(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) kl3Var.l(iconCompat.g, 6);
        String strM = iconCompat.i;
        if (kl3Var.h(7)) {
            strM = kl3Var.m();
        }
        iconCompat.i = strM;
        String strM2 = iconCompat.j;
        if (kl3Var.h(8)) {
            strM2 = kl3Var.m();
        }
        iconCompat.j = strM2;
        iconCompat.h = PorterDuff.Mode.valueOf(iconCompat.i);
        switch (iconCompat.a) {
            case -1:
                Parcelable parcelable = iconCompat.d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.d;
                if (parcelable2 != null) {
                    iconCompat.b = parcelable2;
                } else {
                    byte[] bArr = iconCompat.c;
                    iconCompat.b = bArr;
                    iconCompat.a = 3;
                    iconCompat.e = 0;
                    iconCompat.f = bArr.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.c, Charset.forName("UTF-16"));
                iconCompat.b = str;
                if (iconCompat.a == 2 && iconCompat.j == null) {
                    iconCompat.j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.b = iconCompat.c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, kl3 kl3Var) {
        kl3Var.getClass();
        iconCompat.i = iconCompat.h.name();
        switch (iconCompat.a) {
            case -1:
                iconCompat.d = (Parcelable) iconCompat.b;
                break;
            case 1:
            case 5:
                iconCompat.d = (Parcelable) iconCompat.b;
                break;
            case 2:
                iconCompat.c = ((String) iconCompat.b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.c = (byte[]) iconCompat.b;
                break;
            case 4:
            case 6:
                iconCompat.c = iconCompat.b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.a;
        if (-1 != i) {
            kl3Var.t(i, 1);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            kl3Var.o(2);
            kl3Var.q(bArr);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            kl3Var.o(3);
            kl3Var.u(parcelable);
        }
        int i2 = iconCompat.e;
        if (i2 != 0) {
            kl3Var.t(i2, 4);
        }
        int i3 = iconCompat.f;
        if (i3 != 0) {
            kl3Var.t(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            kl3Var.o(6);
            kl3Var.u(colorStateList);
        }
        String str = iconCompat.i;
        if (str != null) {
            kl3Var.o(7);
            kl3Var.v(str);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            kl3Var.o(8);
            kl3Var.v(str2);
        }
    }
}
