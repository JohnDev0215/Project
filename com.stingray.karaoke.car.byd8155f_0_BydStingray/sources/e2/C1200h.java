package e2;

import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: renamed from: e2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1200h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C1200h f14107h = new C1200h(1, 2, 3, -1, -1, null);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14108i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14109j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14110k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f14111m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f14112n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14114b;
    public final int c;
    public final byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14115e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14116f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14117g;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14108i = Integer.toString(0, 36);
        f14109j = Integer.toString(1, 36);
        f14110k = Integer.toString(2, 36);
        l = Integer.toString(3, 36);
        f14111m = Integer.toString(4, 36);
        f14112n = Integer.toString(5, 36);
    }

    public C1200h(int i7, int i10, int i11, int i12, int i13, byte[] bArr) {
        this.f14113a = i7;
        this.f14114b = i10;
        this.c = i11;
        this.d = bArr;
        this.f14115e = i12;
        this.f14116f = i13;
    }

    public static String a(int i7) {
        return i7 != -1 ? i7 != 1 ? i7 != 2 ? android.hardware.bydauto.ac.tsimpl.a.e(i7, "Undefined color range ") : "Limited range" : "Full range" : "Unset color range";
    }

    public static String b(int i7) {
        return i7 != -1 ? i7 != 6 ? i7 != 1 ? i7 != 2 ? android.hardware.bydauto.ac.tsimpl.a.e(i7, "Undefined color space ") : "BT601" : "BT709" : "BT2020" : "Unset color space";
    }

    public static String c(int i7) {
        return i7 != -1 ? i7 != 10 ? i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 6 ? i7 != 7 ? android.hardware.bydauto.ac.tsimpl.a.e(i7, "Undefined color transfer ") : "HLG" : "ST2084 PQ" : "SDR SMPTE 170M" : "sRGB" : "Linear" : "Gamma 2.2" : "Unset color transfer";
    }

    public static boolean e(C1200h c1200h) {
        int i7;
        int i10;
        int i11;
        int i12;
        if (c1200h == null) {
            return true;
        }
        int i13 = c1200h.f14113a;
        return (i13 == -1 || i13 == 1 || i13 == 2) && ((i7 = c1200h.f14114b) == -1 || i7 == 2) && (((i10 = c1200h.c) == -1 || i10 == 3) && c1200h.d == null && (((i11 = c1200h.f14116f) == -1 || i11 == 8) && ((i12 = c1200h.f14115e) == -1 || i12 == 8)));
    }

    public static int f(int i7) {
        if (i7 == 1) {
            return 1;
        }
        if (i7 != 9) {
            return (i7 == 4 || i7 == 5 || i7 == 6 || i7 == 7) ? 2 : -1;
        }
        return 6;
    }

    public static int g(int i7) {
        if (i7 == 1) {
            return 3;
        }
        if (i7 == 4) {
            return 10;
        }
        if (i7 == 13) {
            return 2;
        }
        if (i7 == 16) {
            return 6;
        }
        if (i7 != 18) {
            return (i7 == 6 || i7 == 7) ? 3 : -1;
        }
        return 7;
    }

    public final boolean d() {
        return (this.f14113a == -1 || this.f14114b == -1 || this.c == -1) ? false : true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1200h.class != obj.getClass()) {
            return false;
        }
        C1200h c1200h = (C1200h) obj;
        return this.f14113a == c1200h.f14113a && this.f14114b == c1200h.f14114b && this.c == c1200h.c && Arrays.equals(this.d, c1200h.d) && this.f14115e == c1200h.f14115e && this.f14116f == c1200h.f14116f;
    }

    public final int hashCode() {
        if (this.f14117g == 0) {
            this.f14117g = ((((Arrays.hashCode(this.d) + ((((((527 + this.f14113a) * 31) + this.f14114b) * 31) + this.c) * 31)) * 31) + this.f14115e) * 31) + this.f14116f;
        }
        return this.f14117g;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ColorInfo(");
        sb.append(b(this.f14113a));
        sb.append(", ");
        sb.append(a(this.f14114b));
        sb.append(", ");
        sb.append(c(this.c));
        sb.append(", ");
        sb.append(this.d != null);
        sb.append(", ");
        String str2 = "NA";
        int i7 = this.f14115e;
        if (i7 != -1) {
            str = i7 + "bit Luma";
        } else {
            str = "NA";
        }
        sb.append(str);
        sb.append(", ");
        int i10 = this.f14116f;
        if (i10 != -1) {
            str2 = i10 + "bit Chroma";
        }
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, str2, ")");
    }
}
