package X1;

import h3.y;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import t5.C2438c;
import v5.C2552b;
import v5.EnumC2551a;
import w5.AbstractC2600b;
import w5.C2602d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9970a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f9971b;
    public int c;
    public final Serializable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f9972e;

    public a(String str, Charset charset, boolean z6, int i7) {
        this.d = str;
        this.f9971b = z6;
        this.f9972e = new C2438c(str, charset);
        this.c = i7;
    }

    public static void a(C2602d[][][] c2602dArr, int i7, C2602d c2602d) {
        C2602d[] c2602dArr2 = c2602dArr[i7 + c2602d.d][c2602d.c];
        EnumC2551a enumC2551a = c2602d.f23331a;
        int iOrdinal = enumC2551a.ordinal();
        char c = 2;
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                c = 1;
            } else if (iOrdinal == 4) {
                c = 3;
            } else {
                if (iOrdinal != 6) {
                    throw new IllegalStateException("Illegal mode " + enumC2551a);
                }
                c = 0;
            }
        }
        C2602d c2602d2 = c2602dArr2[c];
        if (c2602d2 != null) {
            if (c2602d2.f23334f <= c2602d.f23334f) {
                return;
            }
        }
        c2602dArr2[c] = c2602d;
    }

    public static boolean c(EnumC2551a enumC2551a, char c) {
        int i7;
        int iOrdinal = enumC2551a.ordinal();
        if (iOrdinal == 1) {
            return c >= '0' && c <= '9';
        }
        if (iOrdinal != 2) {
            if (iOrdinal == 4) {
                return true;
            }
            if (iOrdinal != 6) {
                return false;
            }
            return AbstractC2600b.b(String.valueOf(c));
        }
        if (c < '`') {
            i7 = AbstractC2600b.f23327a[c];
        } else {
            int[] iArr = AbstractC2600b.f23327a;
            i7 = -1;
        }
        return i7 != -1;
    }

    public static C2552b e(int i7) {
        int iC = AbstractC2641j.c(i7);
        return iC != 0 ? iC != 1 ? C2552b.b(40) : C2552b.b(26) : C2552b.b(9);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(v5.C2552b r17, w5.C2602d[][][] r18, int r19, w5.C2602d r20) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: X1.a.b(v5.b, w5.d[][][], int, w5.d):void");
    }

    public y d(C2552b c2552b) throws I7.a {
        CharsetEncoder[] charsetEncoderArr;
        int i7;
        String str = (String) this.d;
        int length = str.length();
        C2438c c2438c = (C2438c) this.f9972e;
        int i10 = 1;
        C2602d[][][] c2602dArr = (C2602d[][][]) Array.newInstance((Class<?>) C2602d.class, length + 1, c2438c.f22469a.length, 4);
        b(c2552b, c2602dArr, 0, null);
        while (true) {
            charsetEncoderArr = c2438c.f22469a;
            if (i10 > length) {
                break;
            }
            for (int i11 = 0; i11 < charsetEncoderArr.length; i11++) {
                for (int i12 = 0; i12 < 4; i12++) {
                    C2602d c2602d = c2602dArr[i10][i11][i12];
                    if (c2602d != null && i10 < length) {
                        b(c2552b, c2602dArr, i10, c2602d);
                    }
                }
            }
            i10++;
        }
        int i13 = -1;
        int i14 = Integer.MAX_VALUE;
        int i15 = -1;
        for (int i16 = 0; i16 < charsetEncoderArr.length; i16++) {
            for (int i17 = 0; i17 < 4; i17++) {
                C2602d c2602d2 = c2602dArr[length][i16][i17];
                if (c2602d2 != null && (i7 = c2602d2.f23334f) < i14) {
                    i13 = i16;
                    i15 = i17;
                    i14 = i7;
                }
            }
        }
        if (i13 >= 0) {
            return new y(this, c2552b, c2602dArr[length][i13][i15]);
        }
        throw new I7.a(android.hardware.bydauto.ac.tsimpl.a.i("Internal error: failed to encode \"", str, "\""));
    }

    public String toString() {
        switch (this.f9970a) {
            case 0:
                StringBuilder sb = new StringBuilder(128);
                sb.append("BackStackEntry{");
                sb.append(Integer.toHexString(System.identityHashCode(this)));
                if (this.c >= 0) {
                    sb.append(" #");
                    sb.append(this.c);
                }
                sb.append("}");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public a(W2.j jVar) {
        jVar.getClass();
        this.d = new ArrayList();
        this.c = -1;
        this.f9972e = jVar;
    }
}
