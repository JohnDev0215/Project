package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: renamed from: lf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1116lf {
    public static void a(Parcel parcel, int i, float f) {
        k(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void b(Parcel parcel, int i, long j) {
        k(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void c(Parcel parcel, int i, IBinder iBinder) {
        if (iBinder == null) {
            return;
        }
        int i2 = i(parcel, i);
        parcel.writeStrongBinder(iBinder);
        j(parcel, i2);
    }

    public static void d(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable == null) {
            if (z) {
                k(parcel, i, 0);
            }
        } else {
            int i3 = i(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            j(parcel, i3);
        }
    }

    public static void e(Parcel parcel, int i, String str, boolean z) {
        if (str == null) {
            if (z) {
                k(parcel, i, 0);
            }
        } else {
            int i2 = i(parcel, i);
            parcel.writeString(str);
            j(parcel, i2);
        }
    }

    public static void f(Parcel parcel, int i, boolean z) {
        k(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void g(Parcel parcel, int i, Parcelable[] parcelableArr, int i2) {
        if (parcelableArr == null) {
            return;
        }
        int i3 = i(parcel, i);
        parcel.writeInt(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                h(parcel, parcelable, i2);
            }
        }
        j(parcel, i3);
    }

    public static void h(Parcel parcel, Parcelable parcelable, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int iDataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int iDataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition3 - iDataPosition2);
        parcel.setDataPosition(iDataPosition3);
    }

    public static int i(Parcel parcel, int i) {
        parcel.writeInt(i | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void j(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(iDataPosition - i);
        parcel.setDataPosition(iDataPosition);
    }

    public static void k(Parcel parcel, int i, int i2) {
        if (i2 < 65535) {
            parcel.writeInt(i | (i2 << 16));
        } else {
            parcel.writeInt(i | (-65536));
            parcel.writeInt(i2);
        }
    }

    public static void l(Parcel parcel, int i, int i2) {
        k(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void m(Parcel parcel, int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                k(parcel, i, 0);
                return;
            }
            return;
        }
        int i2 = i(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            Parcelable parcelable = (Parcelable) list.get(i3);
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                h(parcel, parcelable, 0);
            }
        }
        j(parcel, i2);
    }

    public static int n(Parcel parcel) {
        return i(parcel, 20293);
    }
}
