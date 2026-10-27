package e2;

import F0.E0;
import android.net.Uri;
import h2.AbstractC1346c;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final String f14070A;
    public static final String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final String f14071C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final String f14072D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final String f14073E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final String f14074F;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Object f14075q = new Object();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Object f14076r = new Object();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final F f14077s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f14078t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f14079u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String f14080v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final String f14081w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final String f14082x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String f14083y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final String f14084z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f14086b;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f14087e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f14088f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f14089g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f14090h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f14091i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C1217z f14092j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f14093k;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f14094m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14095n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f14096p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f14085a = f14075q;
    public F c = f14077s;

    static {
        C1192A c1192a;
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d5.d0 d0Var = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        B b10 = B.d;
        Uri uri = Uri.EMPTY;
        AbstractC1346c.j(((Uri) e02.f2016e) == null || ((UUID) e02.d) != null);
        if (uri != null) {
            c1192a = new C1192A(uri, null, ((UUID) e02.d) != null ? new C1215x(e02) : null, null, listEmptyList, null, d0Var, -9223372036854775807L);
        } else {
            c1192a = null;
        }
        f14077s = new F("androidx.media3.common.Timeline", new C1214w(c1212u), c1192a, new C1217z(c1216y), I.f13914K, b10);
        f14078t = Integer.toString(1, 36);
        f14079u = Integer.toString(2, 36);
        f14080v = Integer.toString(3, 36);
        f14081w = Integer.toString(4, 36);
        f14082x = Integer.toString(5, 36);
        f14083y = Integer.toString(6, 36);
        f14084z = Integer.toString(7, 36);
        f14070A = Integer.toString(8, 36);
        B = Integer.toString(9, 36);
        f14071C = Integer.toString(10, 36);
        f14072D = Integer.toString(11, 36);
        f14073E = Integer.toString(12, 36);
        f14074F = Integer.toString(13, 36);
    }

    public final boolean a() {
        return this.f14092j != null;
    }

    public final void b(Object obj, F f10, Object obj2, long j3, long j10, long j11, boolean z6, boolean z10, C1217z c1217z, long j12, long j13, int i7, int i10, long j14) {
        this.f14085a = obj;
        this.c = f10 != null ? f10 : f14077s;
        if (f10 != null) {
            C1192A c1192a = f10.f13879b;
        }
        this.d = obj2;
        this.f14087e = j3;
        this.f14088f = j10;
        this.f14089g = j11;
        this.f14090h = z6;
        this.f14091i = z10;
        this.f14092j = c1217z;
        this.l = j12;
        this.f14094m = j13;
        this.f14095n = i7;
        this.o = i10;
        this.f14096p = j14;
        this.f14093k = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !e0.class.equals(obj.getClass())) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return Objects.equals(this.f14085a, e0Var.f14085a) && Objects.equals(this.c, e0Var.c) && Objects.equals(this.d, e0Var.d) && Objects.equals(this.f14092j, e0Var.f14092j) && this.f14087e == e0Var.f14087e && this.f14088f == e0Var.f14088f && this.f14089g == e0Var.f14089g && this.f14090h == e0Var.f14090h && this.f14091i == e0Var.f14091i && this.f14093k == e0Var.f14093k && this.l == e0Var.l && this.f14094m == e0Var.f14094m && this.f14095n == e0Var.f14095n && this.o == e0Var.o && this.f14096p == e0Var.f14096p;
    }

    public final int hashCode() {
        int iHashCode = (this.c.hashCode() + ((this.f14085a.hashCode() + 217) * 31)) * 31;
        Object obj = this.d;
        int iHashCode2 = (iHashCode + (obj == null ? 0 : obj.hashCode())) * 31;
        C1217z c1217z = this.f14092j;
        int iHashCode3 = (iHashCode2 + (c1217z != null ? c1217z.hashCode() : 0)) * 31;
        long j3 = this.f14087e;
        int i7 = (iHashCode3 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        long j10 = this.f14088f;
        int i10 = (i7 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.f14089g;
        int i11 = (((((((i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31) + (this.f14090h ? 1 : 0)) * 31) + (this.f14091i ? 1 : 0)) * 31) + (this.f14093k ? 1 : 0)) * 31;
        long j12 = this.l;
        int i12 = (i11 + ((int) (j12 ^ (j12 >>> 32)))) * 31;
        long j13 = this.f14094m;
        int i13 = (((((i12 + ((int) (j13 ^ (j13 >>> 32)))) * 31) + this.f14095n) * 31) + this.o) * 31;
        long j14 = this.f14096p;
        return i13 + ((int) (j14 ^ (j14 >>> 32)));
    }
}
