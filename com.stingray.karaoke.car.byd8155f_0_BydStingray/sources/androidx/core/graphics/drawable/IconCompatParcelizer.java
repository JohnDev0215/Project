package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.Charset;
import x3.AbstractC2674b;
import x3.C2675c;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(AbstractC2674b abstractC2674b) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f11742a = abstractC2674b.f(iconCompat.f11742a, 1);
        byte[] bArr = iconCompat.c;
        if (abstractC2674b.e(2)) {
            Parcel parcel = ((C2675c) abstractC2674b).f23772e;
            int i7 = parcel.readInt();
            if (i7 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i7];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.c = bArr;
        iconCompat.d = abstractC2674b.g(iconCompat.d, 3);
        iconCompat.f11744e = abstractC2674b.f(iconCompat.f11744e, 4);
        iconCompat.f11745f = abstractC2674b.f(iconCompat.f11745f, 5);
        iconCompat.f11746g = (ColorStateList) abstractC2674b.g(iconCompat.f11746g, 6);
        String string = iconCompat.f11748i;
        if (abstractC2674b.e(7)) {
            string = ((C2675c) abstractC2674b).f23772e.readString();
        }
        iconCompat.f11748i = string;
        String string2 = iconCompat.f11749j;
        if (abstractC2674b.e(8)) {
            string2 = ((C2675c) abstractC2674b).f23772e.readString();
        }
        iconCompat.f11749j = string2;
        iconCompat.f11747h = PorterDuff.Mode.valueOf(iconCompat.f11748i);
        switch (iconCompat.f11742a) {
            case -1:
                Parcelable parcelable = iconCompat.d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f11743b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.d;
                if (parcelable2 != null) {
                    iconCompat.f11743b = parcelable2;
                } else {
                    byte[] bArr3 = iconCompat.c;
                    iconCompat.f11743b = bArr3;
                    iconCompat.f11742a = 3;
                    iconCompat.f11744e = 0;
                    iconCompat.f11745f = bArr3.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.c, Charset.forName("UTF-16"));
                iconCompat.f11743b = str;
                if (iconCompat.f11742a == 2 && iconCompat.f11749j == null) {
                    iconCompat.f11749j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f11743b = iconCompat.c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, AbstractC2674b abstractC2674b) {
        abstractC2674b.getClass();
        iconCompat.f11748i = iconCompat.f11747h.name();
        switch (iconCompat.f11742a) {
            case -1:
                iconCompat.d = (Parcelable) iconCompat.f11743b;
                break;
            case 1:
            case 5:
                iconCompat.d = (Parcelable) iconCompat.f11743b;
                break;
            case 2:
                iconCompat.c = ((String) iconCompat.f11743b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.c = (byte[]) iconCompat.f11743b;
                break;
            case 4:
            case 6:
                iconCompat.c = iconCompat.f11743b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i7 = iconCompat.f11742a;
        if (-1 != i7) {
            abstractC2674b.j(i7, 1);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            abstractC2674b.i(2);
            int length = bArr.length;
            Parcel parcel = ((C2675c) abstractC2674b).f23772e;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            abstractC2674b.k(parcelable, 3);
        }
        int i10 = iconCompat.f11744e;
        if (i10 != 0) {
            abstractC2674b.j(i10, 4);
        }
        int i11 = iconCompat.f11745f;
        if (i11 != 0) {
            abstractC2674b.j(i11, 5);
        }
        ColorStateList colorStateList = iconCompat.f11746g;
        if (colorStateList != null) {
            abstractC2674b.k(colorStateList, 6);
        }
        String str = iconCompat.f11748i;
        if (str != null) {
            abstractC2674b.i(7);
            ((C2675c) abstractC2674b).f23772e.writeString(str);
        }
        String str2 = iconCompat.f11749j;
        if (str2 != null) {
            abstractC2674b.i(8);
            ((C2675c) abstractC2674b).f23772e.writeString(str2);
        }
    }
}
