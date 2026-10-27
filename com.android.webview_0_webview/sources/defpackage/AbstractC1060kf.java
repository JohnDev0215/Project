package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: renamed from: kf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1060kf {
    public static int a(Parcel parcel, int i) {
        return (i & (-65536)) != -65536 ? (i >> 16) & 65535 : parcel.readInt();
    }

    public static Parcelable b(Parcel parcel, int i, Parcelable.Creator creator) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(iDataPosition + iA);
        return parcelable;
    }

    public static void c(Parcel parcel, int i, int i2) {
        int iA = a(parcel, i);
        if (iA == i2) {
            return;
        }
        String hexString = Integer.toHexString(iA);
        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
        sb.append("Expected size ");
        sb.append(i2);
        sb.append(" got ");
        sb.append(iA);
        sb.append(" (0x");
        sb.append(hexString);
        sb.append(")");
        throw new C1004jf(sb.toString(), parcel);
    }

    public static String[] d(Parcel parcel, int i) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        String[] strArrCreateStringArray = parcel.createStringArray();
        parcel.setDataPosition(iDataPosition + iA);
        return strArrCreateStringArray;
    }

    public static void e(Parcel parcel, int i) {
        if (parcel.dataPosition() == i) {
            return;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(i);
        throw new C1004jf(sb.toString(), parcel);
    }

    public static void f(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + a(parcel, i));
    }

    public static Object[] g(Parcel parcel, int i, Parcelable.Creator creator) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        Object[] objArrCreateTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(iDataPosition + iA);
        return objArrCreateTypedArray;
    }

    public static ArrayList h(Parcel parcel, int i, Parcelable.Creator creator) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(iDataPosition + iA);
        return arrayListCreateTypedArrayList;
    }

    public static boolean i(Parcel parcel, int i) {
        c(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static int j(Parcel parcel) {
        int i = parcel.readInt();
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if ((65535 & i) != 20293) {
            String strValueOf = String.valueOf(Integer.toHexString(i));
            throw new C1004jf(strValueOf.length() != 0 ? "Expected object header. Got 0x".concat(strValueOf) : new String("Expected object header. Got 0x"), parcel);
        }
        int i2 = iA + iDataPosition;
        if (i2 >= iDataPosition && i2 <= parcel.dataSize()) {
            return i2;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("Size read is invalid start=");
        sb.append(iDataPosition);
        sb.append(" end=");
        sb.append(i2);
        throw new C1004jf(sb.toString(), parcel);
    }

    public static int k(Parcel parcel, int i) {
        c(parcel, i, 4);
        return parcel.readInt();
    }

    public static long l(Parcel parcel, int i) {
        c(parcel, i, 8);
        return parcel.readLong();
    }

    public static float m(Parcel parcel, int i) {
        c(parcel, i, 4);
        return parcel.readFloat();
    }

    public static String n(Parcel parcel, int i) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        String string = parcel.readString();
        parcel.setDataPosition(iDataPosition + iA);
        return string;
    }

    public static IBinder o(Parcel parcel, int i) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(iDataPosition + iA);
        return strongBinder;
    }

    public static Bundle p(Parcel parcel, int i) {
        int iA = a(parcel, i);
        int iDataPosition = parcel.dataPosition();
        if (iA == 0) {
            return null;
        }
        Bundle bundle = parcel.readBundle();
        parcel.setDataPosition(iDataPosition + iA);
        return bundle;
    }
}
