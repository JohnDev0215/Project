package android.hardware.bydauto.ac.tsimpl;

import A7.B;
import android.util.Log;
import androidx.datastore.preferences.protobuf.C0948k;
import h6.n;
import java.util.HashMap;
import java.util.Map;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ boolean a(int i7) {
        if (i7 == 1 || i7 == 2) {
            return false;
        }
        if (i7 == 3 || i7 == 4) {
            return true;
        }
        throw null;
    }

    public static int b(int i7, int i10, int i11, int i12) {
        return C0948k.F(i7) + i10 + i11 + i12;
    }

    public static ClassCastException c(Map.Entry entry) {
        entry.getValue().getClass();
        return new ClassCastException();
    }

    public static Integer d(int i7, HashMap map, Integer num, int i10) {
        map.put(num, Integer.valueOf(i7));
        return Integer.valueOf(i10);
    }

    public static String e(int i7, String str) {
        return str + i7;
    }

    public static String f(long j3, String str, StringBuilder sb) {
        sb.append(j3);
        sb.append(str);
        return sb.toString();
    }

    public static String g(B b10, Class cls, StringBuilder sb) {
        sb.append(b10.b(cls));
        return sb.toString();
    }

    public static String h(String str, String str2) {
        return str + str2;
    }

    public static String i(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String j(StringBuilder sb, int i7, char c) {
        sb.append(i7);
        sb.append(c);
        return sb.toString();
    }

    public static String k(StringBuilder sb, String str, char c) {
        sb.append(str);
        sb.append(c);
        return sb.toString();
    }

    public static String l(StringBuilder sb, String str, String str2) {
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    public static StringBuilder m(int i7, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(i7);
        sb.append(str);
        return sb;
    }

    public static void n(int i7, C2465e c2465e, String str, int i10, String str2) {
        c2465e.put(str, Integer.valueOf(i7));
        c2465e.put(str2, Integer.valueOf(i10));
    }

    public static void o(String str, int i7, String str2) {
        Log.d(str2, str + i7);
    }

    public static void p(StringBuilder sb, int i7, String str) {
        sb.append(i7);
        android.hardware.bydauto.Log.v(str, sb.toString());
    }

    public static void q(StringBuilder sb, int i7, String str, String str2) {
        sb.append(i7);
        sb.append(str);
        android.hardware.bydauto.Log.v(str2, sb.toString());
    }

    public static void r(String str, int i7, String str2) {
        n.t(str2, str + i7);
    }

    public static void s(StringBuilder sb, int i7, String str) {
        sb.append(i7);
        android.hardware.bydauto.Log.i(str, sb.toString());
    }

    public static void t(StringBuilder sb, int i7, String str, String str2) {
        sb.append(i7);
        sb.append(str);
        android.hardware.bydauto.Log.d(str2, sb.toString());
    }

    public static void u(StringBuilder sb, int i7, String str, String str2) {
        sb.append(i7);
        sb.append(str);
        Log.d(str2, sb.toString());
    }

    public static void v(StringBuilder sb, int i7, String str, String str2) {
        sb.append(i7);
        sb.append(str);
        n.j(str2, sb.toString());
    }

    public static /* synthetic */ String w(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? "null" : "MEMORY_CACHE" : "RESOURCE_DISK_CACHE" : "DATA_DISK_CACHE" : "REMOTE" : "LOCAL";
    }

    public static /* synthetic */ String x(int i7) {
        switch (i7) {
            case 1:
                return "INITIALIZE";
            case 2:
                return "RESOURCE_CACHE";
            case 3:
                return "DATA_CACHE";
            case 4:
                return "SOURCE";
            case 5:
                return "ENCODE";
            case 6:
                return "FINISHED";
            default:
                return "null";
        }
    }
}
