package M5;

import A.C0050y0;
import A7.m;
import A9.k;
import N0.L;
import T.C0735d;
import T.C0761q;
import android.util.Log;
import java.util.Arrays;
import l3.C1764u;
import n3.M;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static final boolean a(int i7) {
        return i7 == 6 || i7 == 4;
    }

    public static int b(int i7, int i10, L l) {
        return (l.hashCode() + i7) * i10;
    }

    public static int c(String str, int i7, int i10) {
        return (str.hashCode() + i7) * i10;
    }

    public static k d(int i7, C0761q c0761q, boolean z6) {
        c0761q.Q(i7);
        c0761q.p(z6);
        return new k();
    }

    public static Object e(int i7, C0761q c0761q, boolean z6) {
        c0761q.p(z6);
        c0761q.Q(i7);
        return c0761q.G();
    }

    public static String f(String str, long j3) {
        return str + j3;
    }

    public static String g(String str, String str2) {
        return str + str2;
    }

    public static String h(StringBuilder sb, int i7, String str) {
        sb.append(i7);
        sb.append(str);
        return sb.toString();
    }

    public static StringBuilder i(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static StringBuilder j(String str, int i7, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i7);
        sb.append(str2);
        return sb;
    }

    public static StringBuilder k(String str, String str2) {
        m.e(str, str2);
        return new StringBuilder();
    }

    public static StringBuilder l(String str, String str2, int i7, int i10, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i7);
        sb.append(str2);
        sb.append(i10);
        sb.append(str3);
        return sb;
    }

    public static C1764u m() {
        C1764u c1764u = new C1764u(7);
        ((C0050y0) c1764u.f18608m).f301n = M.f19839e;
        return c1764u;
    }

    public static void n(C0761q c0761q, boolean z6, boolean z10, boolean z11) {
        c0761q.p(z6);
        c0761q.p(z10);
        c0761q.p(z11);
    }

    public static void o(String str, int i7, String str2) {
        Log.v(str2, str + i7);
    }

    public static void p(StringBuilder sb, int i7, String str, String str2, String str3) {
        sb.append(i7);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
    }

    public static void q(int[] iArr, StringBuilder sb, String str) {
        sb.append(Arrays.toString(iArr));
        Log.i(str, sb.toString());
    }

    public static boolean r(C0761q c0761q, int i7, m6.b bVar, C0761q c0761q2, boolean z6) {
        c0761q.Q(i7);
        boolean zK = m6.b.k(bVar, c0761q2);
        c0761q.p(z6);
        return zK;
    }

    public static void s(String str, int i7, String str2) {
        android.hardware.bydauto.Log.v(str2, str + i7);
    }

    public static void t(StringBuilder sb, int i7, String str, String str2, String str3) {
        sb.append(i7);
        sb.append(str);
        sb.append(str2);
        sb.append(str3);
        C0735d.Y(sb.toString());
        throw null;
    }

    public static /* synthetic */ String u(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? "null" : "SYNTHESIZED" : "DELEGATION" : "FAKE_OVERRIDE" : "DECLARATION";
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
