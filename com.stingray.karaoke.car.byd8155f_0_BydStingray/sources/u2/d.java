package U2;

import B2.AbstractC0062b;
import B2.B;
import B2.C0072l;
import B2.C0073m;
import B2.C0074n;
import B2.C0076p;
import B2.D;
import B2.F;
import B2.L;
import B2.r;
import B2.s;
import B2.z;
import P2.n;
import e2.J;
import e2.K;
import h2.AbstractC1343A;
import h2.p;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class d implements r {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0074n f8970u = new C0074n(16);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0076p f8974f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public T f8975g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public L f8976h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public L f8977i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8978j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public K f8979k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f8980m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f8981n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8982p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public f f8983q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f8984r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f8985s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f8986t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8971a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f8972b = new t(10);
    public final D c = new D();
    public final z d = new z();
    public long l = -9223372036854775807L;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final B f8973e = new B(0);

    public d() {
        C0076p c0076p = new C0076p();
        this.f8974f = c0076p;
        this.f8977i = c0076p;
        this.o = -1L;
    }

    public static long d(K k10) {
        if (k10 == null) {
            return -9223372036854775807L;
        }
        int length = k10.l.length;
        for (int i7 = 0; i7 < length; i7++) {
            J j3 = k10.l[i7];
            if (j3 instanceof n) {
                n nVar = (n) j3;
                if (nVar.l.equals("TLEN")) {
                    return AbstractC1343A.H(Long.parseLong((String) nVar.f5327n.get(0)));
                }
            }
        }
        return -9223372036854775807L;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        T t10 = (T) tVar;
        this.f8975g = t10;
        L lU = t10.u(0, 1);
        this.f8976h = lU;
        this.f8977i = lU;
        this.f8975g.e();
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x036d A[PHI: r4
  0x036d: PHI (r4v26 B2.m) = (r4v25 B2.m), (r4v35 B2.m) binds: [B:149:0x030f, B:254:0x036d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x052d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r51, B2.v r52) throws java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 1415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U2.d.c(B2.s, B2.v):int");
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.f8978j = 0;
        this.l = -9223372036854775807L;
        this.f8980m = 0L;
        this.f8982p = 0;
        this.f8986t = j10;
        f fVar = this.f8983q;
        if (fVar instanceof b) {
            p pVar = ((b) fVar).c.f502b;
            int i7 = pVar.f15229a;
            if (i7 != 0 && j10 - pVar.e(i7 - 1) < 100000) {
                return;
            }
            this.f8985s = true;
            this.f8977i = this.f8974f;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        return j((C0073m) sVar, true);
    }

    public final void h() {
        F f10 = this.f8983q;
        if ((f10 instanceof a) && ((C0072l) f10).h()) {
            long j3 = this.o;
            if (j3 == -1 || j3 == this.f8983q.d()) {
                return;
            }
            a aVar = (a) this.f8983q;
            long j10 = this.o;
            int i7 = aVar.f8964j;
            this.f8983q = new a(j10, aVar.f8962h, aVar.f8963i, i7, aVar.f8965k);
            T t10 = this.f8975g;
            t10.getClass();
            t10.w(this.f8983q);
        }
    }

    public final boolean i(C0073m c0073m) {
        f fVar = this.f8983q;
        if (fVar != null) {
            long jD = fVar.d();
            if (jD != -1 && c0073m.l() > jD - 4) {
                return true;
            }
        }
        try {
            return !c0073m.k(this.f8972b.f15241a, 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    public final boolean j(C0073m c0073m, boolean z6) throws EOFException, InterruptedIOException {
        int iL;
        int i7;
        int iH;
        int i10 = z6 ? 32768 : 131072;
        c0073m.f590q = 0;
        if (c0073m.o == 0) {
            K kA = this.f8973e.a(c0073m, (this.f8971a & 8) == 0 ? null : f8970u);
            this.f8979k = kA;
            if (kA != null) {
                this.d.b(kA);
            }
            iL = (int) c0073m.l();
            if (!z6) {
                c0073m.i(iL);
            }
            i7 = 0;
        } else {
            iL = 0;
            i7 = 0;
        }
        int i11 = i7;
        int i12 = i11;
        while (true) {
            if (!i(c0073m)) {
                t tVar = this.f8972b;
                tVar.H(0);
                int iH2 = tVar.h();
                if ((i7 == 0 || ((-128000) & iH2) == (((long) i7) & (-128000))) && (iH = AbstractC0062b.h(iH2)) != -1) {
                    i11++;
                    if (i11 != 1) {
                        if (i11 == 4) {
                            break;
                        }
                    } else {
                        this.c.a(iH2);
                        i7 = iH2;
                    }
                    c0073m.e(iH - 4, false);
                } else {
                    int i13 = i12 + 1;
                    if (i12 == i10) {
                        if (z6) {
                            return false;
                        }
                        h();
                        throw new EOFException();
                    }
                    if (z6) {
                        c0073m.f590q = 0;
                        c0073m.e(iL + i13, false);
                    } else {
                        c0073m.i(1);
                    }
                    i11 = 0;
                    i12 = i13;
                    i7 = 0;
                }
            } else if (i11 <= 0) {
                h();
                throw new EOFException();
            }
        }
        if (z6) {
            c0073m.i(iL + i12);
        } else {
            c0073m.f590q = 0;
        }
        this.f8978j = i7;
        return true;
    }
}
