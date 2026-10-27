package androidx.core.graphics.drawable;

import a.t.a;
import a.t.b;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(a aVar) {
        Parcelable parcelable;
        IconCompat iconCompat = new IconCompat();
        iconCompat.f1528a = aVar.i(iconCompat.f1528a, 1);
        byte[] bArr = iconCompat.f1530c;
        if (aVar.h(2)) {
            b bVar = (b) aVar;
            int i = bVar.f1362e.readInt();
            if (i < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i];
                bVar.f1362e.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f1530c = bArr;
        iconCompat.f1531d = aVar.j(iconCompat.f1531d, 3);
        iconCompat.f1532e = aVar.i(iconCompat.f1532e, 4);
        iconCompat.f1533f = aVar.i(iconCompat.f1533f, 5);
        iconCompat.g = (ColorStateList) aVar.j(iconCompat.g, 6);
        String string = iconCompat.i;
        if (aVar.h(7)) {
            string = ((b) aVar).f1362e.readString();
        }
        iconCompat.i = string;
        String string2 = iconCompat.j;
        if (aVar.h(8)) {
            string2 = ((b) aVar).f1362e.readString();
        }
        iconCompat.j = string2;
        iconCompat.h = PorterDuff.Mode.valueOf(iconCompat.i);
        switch (iconCompat.f1528a) {
            case -1:
                parcelable = iconCompat.f1531d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f1529b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                parcelable = iconCompat.f1531d;
                if (parcelable != null) {
                    iconCompat.f1529b = parcelable;
                } else {
                    byte[] bArr3 = iconCompat.f1530c;
                    iconCompat.f1529b = bArr3;
                    iconCompat.f1528a = 3;
                    iconCompat.f1532e = 0;
                    iconCompat.f1533f = bArr3.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.f1530c, Charset.forName("UTF-16"));
                iconCompat.f1529b = str;
                if (iconCompat.f1528a == 2 && iconCompat.j == null) {
                    iconCompat.j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f1529b = iconCompat.f1530c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, a aVar) {
        if (aVar == null) {
            throw null;
        }
        iconCompat.i = iconCompat.h.name();
        switch (iconCompat.f1528a) {
            case -1:
            case 1:
            case 5:
                iconCompat.f1531d = (Parcelable) iconCompat.f1529b;
                break;
            case 2:
                iconCompat.f1530c = ((String) iconCompat.f1529b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f1530c = (byte[]) iconCompat.f1529b;
                break;
            case 4:
            case 6:
                iconCompat.f1530c = iconCompat.f1529b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.f1528a;
        if (-1 != i) {
            aVar.m(i, 1);
        }
        byte[] bArr = iconCompat.f1530c;
        if (bArr != null) {
            aVar.l(2);
            b bVar = (b) aVar;
            bVar.f1362e.writeInt(bArr.length);
            bVar.f1362e.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f1531d;
        if (parcelable != null) {
            aVar.l(3);
            ((b) aVar).f1362e.writeParcelable(parcelable, 0);
        }
        int i2 = iconCompat.f1532e;
        if (i2 != 0) {
            aVar.m(i2, 4);
        }
        int i3 = iconCompat.f1533f;
        if (i3 != 0) {
            aVar.m(i3, 5);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            aVar.l(6);
            ((b) aVar).f1362e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.i;
        if (str != null) {
            aVar.l(7);
            ((b) aVar).f1362e.writeString(str);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            aVar.l(8);
            ((b) aVar).f1362e.writeString(str2);
        }
    }
}
