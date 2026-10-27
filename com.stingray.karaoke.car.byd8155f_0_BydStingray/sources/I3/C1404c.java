package i3;

import B2.C0073m;
import B2.L;
import T0.j;
import e2.C1207o;
import e2.C1208p;
import e2.M;
import h2.AbstractC1343A;
import java.math.RoundingMode;
import u2.T;

/* JADX INFO: renamed from: i3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1404c implements InterfaceC1403b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f15731a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f15732b;
    public final j c;
    public final C1208p d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f15733e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f15734f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f15735g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f15736h;

    public C1404c(T t10, L l, j jVar, String str, int i7) throws M {
        this.f15731a = t10;
        this.f15732b = l;
        this.c = jVar;
        int i10 = jVar.f8456p;
        int i11 = jVar.f8454m;
        int i12 = (i10 * i11) / 8;
        int i13 = jVar.o;
        if (i13 != i12) {
            throw M.a(null, "Expected block size: " + i12 + "; got: " + i13);
        }
        int i14 = jVar.f8455n;
        int i15 = i14 * i12;
        int i16 = i15 * 8;
        int iMax = Math.max(i12, i15 / 10);
        this.f15733e = iMax;
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l(str);
        c1207o.f14242h = i16;
        c1207o.f14243i = i16;
        c1207o.f14247n = iMax;
        c1207o.B = i11;
        c1207o.f14228C = i14;
        c1207o.f14229D = i7;
        this.d = new C1208p(c1207o);
    }

    @Override // i3.InterfaceC1403b
    public final void a(long j3, int i7) {
        this.f15731a.w(new C1407f(this.c, 1, i7, j3));
        this.f15732b.d(this.d);
    }

    @Override // i3.InterfaceC1403b
    public final boolean b(C0073m c0073m, long j3) {
        int i7;
        int i10;
        long j10 = j3;
        while (j10 > 0 && (i7 = this.f15735g) < (i10 = this.f15733e)) {
            int iC = this.f15732b.c(c0073m, (int) Math.min(i10 - i7, j10), true);
            if (iC == -1) {
                j10 = 0;
            } else {
                this.f15735g += iC;
                j10 -= (long) iC;
            }
        }
        j jVar = this.c;
        int i11 = this.f15735g;
        int i12 = jVar.o;
        int i13 = i11 / i12;
        if (i13 > 0) {
            long j11 = this.f15734f;
            long j12 = this.f15736h;
            long j13 = jVar.f8455n;
            int i14 = AbstractC1343A.f15195a;
            long jN = j11 + AbstractC1343A.N(j12, 1000000L, j13, RoundingMode.DOWN);
            int i15 = i13 * i12;
            int i16 = this.f15735g - i15;
            this.f15732b.b(jN, 1, i15, i16, null);
            this.f15736h += (long) i13;
            this.f15735g = i16;
        }
        return j10 <= 0;
    }

    @Override // i3.InterfaceC1403b
    public final void c(long j3) {
        this.f15734f = j3;
        this.f15735g = 0;
        this.f15736h = 0L;
    }
}
