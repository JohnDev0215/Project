package h2;

import E0.C0158h;
import T.C0761q;
import android.os.Bundle;
import io.sentry.C1489n1;
import io.sentry.K;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import n0.C1891t;

/* JADX INFO: renamed from: h2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1347d {
    public static float a(float f10, float f11, float f12, float f13) {
        return ((f10 - f11) * f12) + f13;
    }

    public static int b(int i7, int i10, float f10) {
        return (Float.hashCode(f10) + i7) * i10;
    }

    public static int c(int i7, int i10, long j3) {
        return (Long.hashCode(j3) + i7) * i10;
    }

    public static int d(int i7, int i10, boolean z6) {
        return (Boolean.hashCode(z6) + i7) * i10;
    }

    public static Object e(int i7, ArrayList arrayList) {
        return arrayList.get(arrayList.size() - i7);
    }

    public static Object f(Bundle bundle, String str, String str2, String str3, String str4) {
        A7.m.f(bundle, str);
        A7.m.f(str2, str3);
        return bundle.get(str4);
    }

    public static String g(int i7, String str) {
        StringBuilder sb = new StringBuilder(i7);
        sb.append(str);
        return sb.toString();
    }

    public static String h(StringBuilder sb, float f10, char c) {
        sb.append(f10);
        sb.append(c);
        return sb.toString();
    }

    public static String i(StringBuilder sb, boolean z6, char c) {
        sb.append(z6);
        sb.append(c);
        return sb.toString();
    }

    public static StringBuilder j(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        return sb;
    }

    public static void k(int i7, int i10, int i11, int i12, int i13) {
        h6.r.a(i7);
        h6.r.a(i10);
        h6.r.a(i11);
        h6.r.a(i12);
        h6.r.a(i13);
    }

    public static void l(int i7, C0761q c0761q, int i10, C0158h c0158h) {
        c0761q.a0(Integer.valueOf(i7));
        c0761q.b(Integer.valueOf(i10), c0158h);
    }

    public static void m(int i7, HashMap map, String str, int i10, String str2) {
        map.put(str, Integer.valueOf(i7));
        map.put(str2, Integer.valueOf(i10));
    }

    public static void n(long j3, String str, StringBuilder sb) {
        sb.append((Object) C1891t.i(j3));
        sb.append(str);
    }

    public static void o(h3.y yVar, long j3) {
        yVar.m().k();
        yVar.L(j3);
    }

    public static void p(String str, int i7, String str2) {
        AbstractC1346c.z(str2, str + i7);
    }

    public static void q(String str, String str2, String str3) {
        AbstractC1346c.z(str3, str + str2);
    }

    public static void r(StringBuilder sb, String str, String str2, String str3, String str4) {
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
    }

    public static void s(HashMap map, String str, C1489n1 c1489n1, String str2, K k10) {
        Object obj = map.get(str);
        c1489n1.w(str2);
        c1489n1.I(k10, obj);
    }

    public static void t(ConcurrentHashMap concurrentHashMap, String str, C1489n1 c1489n1, String str2, K k10) {
        Object obj = concurrentHashMap.get(str);
        c1489n1.w(str2);
        c1489n1.I(k10, obj);
    }

    public static /* synthetic */ String u(int i7) {
        switch (i7) {
            case 1:
                return "NONE";
            case 2:
                return "LEFT";
            case 3:
                return "TOP";
            case 4:
                return "RIGHT";
            case 5:
                return "BOTTOM";
            case 6:
                return "BASELINE";
            case 7:
                return "CENTER";
            case 8:
                return "CENTER_X";
            case 9:
                return "CENTER_Y";
            default:
                throw null;
        }
    }

    public static /* synthetic */ String v(int i7) {
        switch (i7) {
            case 1:
                return "BEGIN_ARRAY";
            case 2:
                return "END_ARRAY";
            case 3:
                return "BEGIN_OBJECT";
            case 4:
                return "END_OBJECT";
            case 5:
                return "NAME";
            case 6:
                return "STRING";
            case 7:
                return "NUMBER";
            case 8:
                return "BOOLEAN";
            case 9:
                return "NULL";
            case 10:
                return "END_DOCUMENT";
            default:
                return "null";
        }
    }
}
