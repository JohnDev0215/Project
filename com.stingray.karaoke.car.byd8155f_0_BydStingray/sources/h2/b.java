package H2;

import B2.C0073m;
import B2.C0076p;
import B2.r;
import B2.s;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class b implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f2774a = new t(4);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f2775b = new t(9);
    public final t c = new t(11);
    public final t d = new t();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f2776e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public T f2777f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2778g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2779h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f2780i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2781j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2782k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f2783m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2784n;
    public a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e f2785p;

    public b() {
        c cVar = new c(new C0076p(), 1);
        cVar.f2786n = -9223372036854775807L;
        cVar.o = new long[0];
        cVar.f2787p = new long[0];
        this.f2776e = cVar;
        this.f2778g = 1;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f2777f = (T) tVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x038d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0007 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0289  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r32, B2.v r33) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 1121
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: H2.b.c(B2.s, B2.v):int");
    }

    public final t d(C0073m c0073m) throws EOFException, InterruptedIOException {
        int i7 = this.l;
        t tVar = this.d;
        byte[] bArr = tVar.f15241a;
        if (i7 > bArr.length) {
            tVar.F(0, new byte[Math.max(bArr.length * 2, i7)]);
        } else {
            tVar.H(0);
        }
        tVar.G(this.l);
        c0073m.c(tVar.f15241a, 0, this.l, false);
        return tVar;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        if (j3 == 0) {
            this.f2778g = 1;
            this.f2779h = false;
        } else {
            this.f2778g = 3;
        }
        this.f2781j = 0;
    }

    @Override // B2.r
    public final boolean f(s sVar) throws EOFException, InterruptedIOException {
        t tVar = this.f2774a;
        C0073m c0073m = (C0073m) sVar;
        c0073m.k(tVar.f15241a, 0, 3, false);
        tVar.H(0);
        if (tVar.y() != 4607062) {
            return false;
        }
        c0073m.k(tVar.f15241a, 0, 2, false);
        tVar.H(0);
        if ((tVar.B() & 250) != 0) {
            return false;
        }
        c0073m.k(tVar.f15241a, 0, 4, false);
        tVar.H(0);
        int iH = tVar.h();
        c0073m.f590q = 0;
        c0073m.e(iH, false);
        c0073m.k(tVar.f15241a, 0, 4, false);
        tVar.H(0);
        return tVar.h() == 0;
    }
}
