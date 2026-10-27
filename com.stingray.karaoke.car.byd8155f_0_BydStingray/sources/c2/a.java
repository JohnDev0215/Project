package C2;

import B2.C;
import B2.C0072l;
import B2.C0073m;
import B2.C0076p;
import B2.F;
import B2.L;
import B2.r;
import B2.s;
import B2.t;
import e2.M;
import h2.AbstractC1343A;
import h2.p;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int[] f949s = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int[] f950t = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final byte[] f951u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final byte[] f952v;
    public final C0076p c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f956f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f957g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f958h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f960j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f961k;
    public T l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public L f962m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public L f963n;
    public F o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f964p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f965q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f966r;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f954b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f953a = new byte[1];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f959i = -1;

    static {
        int i7 = AbstractC1343A.f15195a;
        Charset charset = StandardCharsets.UTF_8;
        f951u = "#!AMR\n".getBytes(charset);
        f952v = "#!AMR-WB\n".getBytes(charset);
    }

    public a() {
        C0076p c0076p = new C0076p();
        this.c = c0076p;
        this.f963n = c0076p;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(t tVar) {
        T t10 = (T) tVar;
        this.l = t10;
        L lU = t10.u(0, 1);
        this.f962m = lU;
        this.f963n = lU;
        t10.e();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e2  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r25, B2.v r26) throws e2.M {
        /*
            Method dump skipped, instruction units count: 398
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C2.a.c(B2.s, B2.v):int");
    }

    public final int d(C0073m c0073m) throws M {
        boolean z6;
        c0073m.f590q = 0;
        byte[] bArr = this.f953a;
        c0073m.k(bArr, 0, 1, false);
        byte b10 = bArr[0];
        if ((b10 & 131) > 0) {
            throw M.a(null, "Invalid padding bits for frame header " + ((int) b10));
        }
        int i7 = (b10 >> 3) & 15;
        if (i7 >= 0 && i7 <= 15 && (((z6 = this.d) && (i7 < 10 || i7 > 13)) || (!z6 && (i7 < 12 || i7 > 14)))) {
            return z6 ? f950t[i7] : f949s[i7];
        }
        StringBuilder sb = new StringBuilder("Illegal AMR ");
        sb.append(this.d ? "WB" : "NB");
        sb.append(" frame type ");
        sb.append(i7);
        throw M.a(null, sb.toString());
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.f955e = 0L;
        this.f956f = 0;
        this.f957g = 0;
        this.f965q = j10;
        F f10 = this.o;
        if (!(f10 instanceof C)) {
            if (j3 == 0 || !(f10 instanceof C0072l)) {
                this.f961k = 0L;
                return;
            } else {
                C0072l c0072l = (C0072l) f10;
                this.f961k = (Math.max(0L, j3 - c0072l.f583b) * 8000000) / ((long) c0072l.f584e);
                return;
            }
        }
        C c = (C) f10;
        p pVar = c.f502b;
        long jE = pVar.f15229a == 0 ? -9223372036854775807L : pVar.e(AbstractC1343A.c(c.f501a, j3));
        this.f961k = jE;
        if (Math.abs(this.f965q - jE) < 20000) {
            return;
        }
        this.f964p = true;
        this.f963n = this.c;
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        return h((C0073m) sVar);
    }

    public final boolean h(C0073m c0073m) {
        c0073m.f590q = 0;
        byte[] bArr = f951u;
        byte[] bArr2 = new byte[bArr.length];
        c0073m.k(bArr2, 0, bArr.length, false);
        if (Arrays.equals(bArr2, bArr)) {
            this.d = false;
            c0073m.i(bArr.length);
            return true;
        }
        c0073m.f590q = 0;
        byte[] bArr3 = f952v;
        byte[] bArr4 = new byte[bArr3.length];
        c0073m.k(bArr4, 0, bArr3.length, false);
        if (!Arrays.equals(bArr4, bArr3)) {
            return false;
        }
        this.d = true;
        c0073m.i(bArr3.length);
        return true;
    }
}
