package W2;

import B2.AbstractC0062b;
import B2.C0073m;
import B2.r;
import B2.s;
import B2.t;
import e2.M;
import h2.AbstractC1343A;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class d implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f9728a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f9729b;
    public boolean c;

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(t tVar) {
        this.f9728a = (T) tVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x017c  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r19, B2.v r20) throws e2.M {
        /*
            Method dump skipped, instruction units count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W2.d.c(B2.s, B2.v):int");
    }

    public final boolean d(C0073m c0073m) {
        boolean zW;
        f fVar = new f();
        if (fVar.a(c0073m, true) && (fVar.f9733a & 2) == 2) {
            int iMin = Math.min(fVar.f9735e, 8);
            h2.t tVar = new h2.t(iMin);
            c0073m.k(tVar.f15241a, 0, iMin, false);
            tVar.H(0);
            if (tVar.a() >= 5 && tVar.v() == 127 && tVar.x() == 1179402563) {
                this.f9729b = new c();
            } else {
                tVar.H(0);
                try {
                    zW = AbstractC0062b.w(1, tVar, true);
                } catch (M unused) {
                    zW = false;
                }
                if (zW) {
                    this.f9729b = new k();
                } else {
                    tVar.H(0);
                    if (h.e(tVar, h.o)) {
                        this.f9729b = new h();
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        i iVar = this.f9729b;
        if (iVar != null) {
            e eVar = iVar.f9740a;
            f fVar = eVar.f9730a;
            fVar.f9733a = 0;
            fVar.f9734b = 0L;
            fVar.c = 0;
            fVar.d = 0;
            fVar.f9735e = 0;
            eVar.f9731b.E(0);
            eVar.c = -1;
            eVar.f9732e = false;
            if (j3 == 0) {
                iVar.d(!iVar.l);
                return;
            }
            if (iVar.f9745h != 0) {
                long j11 = (((long) iVar.f9746i) * j10) / 1000000;
                iVar.f9742e = j11;
                g gVar = iVar.d;
                int i7 = AbstractC1343A.f15195a;
                gVar.i(j11);
                iVar.f9745h = 2;
            }
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        try {
            return d((C0073m) sVar);
        } catch (M unused) {
            return false;
        }
    }
}
