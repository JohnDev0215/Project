package u2;

import d5.C1077F;
import h2.AbstractC1346c;
import java.util.List;

/* JADX INFO: renamed from: u2.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2509l implements b0 {
    public final d5.d0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f22865m;

    public C2509l(List list, List list2) {
        C1077F c1077fU = d5.I.u();
        AbstractC1346c.c(list.size() == list2.size());
        for (int i7 = 0; i7 < list.size(); i7++) {
            c1077fU.a(new C2508k((b0) list.get(i7), (List) list2.get(i7)));
        }
        this.l = c1077fU.h();
        this.f22865m = -9223372036854775807L;
    }

    @Override // u2.b0
    public final boolean d() {
        int i7 = 0;
        while (true) {
            d5.d0 d0Var = this.l;
            if (i7 >= d0Var.size()) {
                return false;
            }
            if (((C2508k) d0Var.get(i7)).d()) {
                return true;
            }
            i7++;
        }
    }

    @Override // u2.b0
    public final long g() {
        int i7 = 0;
        long jMin = Long.MAX_VALUE;
        while (true) {
            d5.d0 d0Var = this.l;
            if (i7 >= d0Var.size()) {
                break;
            }
            long jG = ((C2508k) d0Var.get(i7)).g();
            if (jG != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jG);
            }
            i7++;
        }
        if (jMin == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return jMin;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        boolean zI;
        boolean z6 = false;
        do {
            long jG = g();
            if (jG == Long.MIN_VALUE) {
                break;
            }
            int i7 = 0;
            zI = false;
            while (true) {
                d5.d0 d0Var = this.l;
                if (i7 >= d0Var.size()) {
                    break;
                }
                long jG2 = ((C2508k) d0Var.get(i7)).g();
                boolean z10 = jG2 != Long.MIN_VALUE && jG2 <= g7.f19536a;
                if (jG2 == jG || z10) {
                    zI |= ((C2508k) d0Var.get(i7)).i(g7);
                }
                i7++;
            }
            z6 |= zI;
        } while (zI);
        return z6;
    }

    @Override // u2.b0
    public final long l() {
        int i7 = 0;
        long jMin = Long.MAX_VALUE;
        long jMin2 = Long.MAX_VALUE;
        while (true) {
            d5.d0 d0Var = this.l;
            if (i7 >= d0Var.size()) {
                break;
            }
            C2508k c2508k = (C2508k) d0Var.get(i7);
            long jL = c2508k.l();
            if ((c2508k.c().contains(1) || c2508k.c().contains(2) || c2508k.c().contains(4)) && jL != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jL);
            }
            if (jL != Long.MIN_VALUE) {
                jMin2 = Math.min(jMin2, jL);
            }
            i7++;
        }
        if (jMin != Long.MAX_VALUE) {
            this.f22865m = jMin;
            return jMin;
        }
        if (jMin2 == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        long j3 = this.f22865m;
        return j3 != -9223372036854775807L ? j3 : jMin2;
    }

    @Override // u2.b0
    public final void q(long j3) {
        int i7 = 0;
        while (true) {
            d5.d0 d0Var = this.l;
            if (i7 >= d0Var.size()) {
                return;
            }
            ((C2508k) d0Var.get(i7)).q(j3);
            i7++;
        }
    }
}
