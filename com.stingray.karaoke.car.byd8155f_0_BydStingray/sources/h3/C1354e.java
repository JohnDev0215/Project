package h3;

import B2.C0073m;
import B2.N;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: renamed from: h3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1354e implements B2.r {
    public final h2.t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final N f15311e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public T f15312f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f15313g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15316j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f15317k;
    public boolean l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f15309a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1355f f15310b = new C1355f(null, 0, true);
    public final h2.t c = new h2.t(2048);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15315i = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f15314h = -1;

    public C1354e() {
        h2.t tVar = new h2.t(10);
        this.d = tVar;
        byte[] bArr = tVar.f15241a;
        this.f15311e = new N(bArr.length, bArr);
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f15312f = (T) tVar;
        this.f15310b.f(tVar, new H(0, 1));
        ((T) tVar).e();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r19, B2.v r20) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.C1354e.c(B2.s, B2.v):int");
    }

    public final int d(C0073m c0073m) throws EOFException, InterruptedIOException {
        int i7 = 0;
        while (true) {
            h2.t tVar = this.d;
            c0073m.k(tVar.f15241a, 0, 10, false);
            tVar.H(0);
            if (tVar.y() != 4801587) {
                break;
            }
            tVar.I(3);
            int iU = tVar.u();
            i7 += iU + 10;
            c0073m.e(iU, false);
        }
        c0073m.f590q = 0;
        c0073m.e(i7, false);
        if (this.f15314h == -1) {
            this.f15314h = i7;
        }
        return i7;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.f15317k = false;
        this.f15310b.b();
        this.f15313g = j10;
    }

    @Override // B2.r
    public final boolean f(B2.s sVar) throws EOFException, InterruptedIOException {
        C0073m c0073m = (C0073m) sVar;
        int iD = d(c0073m);
        int i7 = iD;
        int i10 = 0;
        int i11 = 0;
        do {
            h2.t tVar = this.d;
            c0073m.k(tVar.f15241a, 0, 2, false);
            tVar.H(0);
            if ((tVar.B() & 65526) == 65520) {
                i10++;
                if (i10 >= 4 && i11 > 188) {
                    return true;
                }
                c0073m.k(tVar.f15241a, 0, 4, false);
                N n10 = this.f15311e;
                n10.r(14);
                int i12 = n10.i(13);
                if (i12 <= 6) {
                    i7++;
                    c0073m.f590q = 0;
                    c0073m.e(i7, false);
                } else {
                    c0073m.e(i12 - 6, false);
                    i11 += i12;
                }
            } else {
                i7++;
                c0073m.f590q = 0;
                c0073m.e(i7, false);
            }
            i10 = 0;
            i11 = 0;
        } while (i7 - iD < 8192);
        return false;
    }
}
