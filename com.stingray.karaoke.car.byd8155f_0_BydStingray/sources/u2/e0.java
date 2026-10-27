package u2;

import e2.C1208p;
import h2.AbstractC1343A;
import l5.C1778f;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements a0 {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f22812n;

    public e0(long j3) {
        C1208p c1208p = f0.f22822j;
        int i7 = AbstractC1343A.f15195a;
        this.l = ((long) 4) * ((j3 * 44100) / 1000000);
        a(0L);
    }

    public final void a(long j3) {
        C1208p c1208p = f0.f22822j;
        int i7 = AbstractC1343A.f15195a;
        this.f22812n = AbstractC1343A.j(((long) 4) * ((j3 * 44100) / 1000000), 0L, this.l);
    }

    @Override // u2.a0
    public final boolean k() {
        return true;
    }

    @Override // u2.a0
    public final int p(C1778f c1778f, m2.g gVar, int i7) {
        if (!this.f22811m || (i7 & 2) != 0) {
            c1778f.f18639n = f0.f22822j;
            this.f22811m = true;
            return -5;
        }
        long j3 = this.f22812n;
        long j10 = this.l - j3;
        if (j10 == 0) {
            gVar.b(4);
            return -4;
        }
        C1208p c1208p = f0.f22822j;
        int i10 = AbstractC1343A.f15195a;
        gVar.f18957r = ((j3 / ((long) 4)) * 1000000) / 44100;
        gVar.b(1);
        byte[] bArr = f0.l;
        int iMin = (int) Math.min(bArr.length, j10);
        if ((4 & i7) == 0) {
            gVar.j(iMin);
            gVar.f18955p.put(bArr, 0, iMin);
        }
        if ((i7 & 1) == 0) {
            this.f22812n += (long) iMin;
        }
        return -4;
    }

    @Override // u2.a0
    public final void r() {
    }

    @Override // u2.a0
    public final int s(long j3) {
        long j10 = this.f22812n;
        a(j3);
        return (int) ((this.f22812n - j10) / ((long) f0.l.length));
    }
}
