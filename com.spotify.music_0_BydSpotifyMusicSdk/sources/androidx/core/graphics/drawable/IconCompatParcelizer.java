package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;
import p.ys2;
import p.zs2;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(ys2 ys2Var) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.a = ys2Var.f(iconCompat.a, 1);
        byte[] bArr = iconCompat.c;
        if (ys2Var.e(2)) {
            Parcel parcel = ((zs2) ys2Var).e;
            int i = parcel.readInt();
            if (i < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.c = bArr;
        iconCompat.d = ys2Var.g(iconCompat.d, 3);
        iconCompat.e = ys2Var.f(iconCompat.e, 4);
        iconCompat.f = ys2Var.f(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) ys2Var.g(iconCompat.g, 6);
        String string = iconCompat.i;
        if (ys2Var.e(7)) {
            string = ((zs2) ys2Var).e.readString();
        }
        iconCompat.i = string;
        String string2 = iconCompat.j;
        if (ys2Var.e(8)) {
            string2 = ((zs2) ys2Var).e.readString();
        }
        iconCompat.j = string2;
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
                    byte[] bArr3 = iconCompat.c;
                    iconCompat.b = bArr3;
                    iconCompat.a = 3;
                    iconCompat.e = 0;
                    iconCompat.f = bArr3.length;
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

    public static void write(IconCompat iconCompat, ys2 ys2Var) {
        ys2Var.getClass();
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
            ys2Var.j(i, 1);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            ys2Var.i(2);
            int length = bArr.length;
            Parcel parcel = ((zs2) ys2Var).e;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            ys2Var.k(parcelable, 3);
        }
        int i2 = iconCompat.e;
        if (i2 != 0) {
            ys2Var.j(i2, 4);
        }
        int i3 = iconCompat.f;
        if (i3 != 0) {
            ys2Var.j(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            ys2Var.k(colorStateList, 6);
        }
        String str = iconCompat.i;
        if (str != null) {
            ys2Var.i(7);
            ((zs2) ys2Var).e.writeString(str);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            ys2Var.i(8);
            ((zs2) ys2Var).e.writeString(str2);
        }
    }
}
