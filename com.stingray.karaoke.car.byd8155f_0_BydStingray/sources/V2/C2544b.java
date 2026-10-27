package v2;

import d5.C1077F;
import d5.C1094p;
import d5.C1103z;
import d5.a0;
import d5.d0;
import d5.k0;
import h2.AbstractC1346c;
import java.util.ArrayList;
import u2.I;

/* JADX INFO: renamed from: v2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2544b implements InterfaceC2543a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C1103z f22979m;
    public final ArrayList l = new ArrayList();

    static {
        a0 a0Var = a0.l;
        I i7 = new I(3);
        a0Var.getClass();
        C1094p c1094p = new C1094p(i7, a0Var);
        k0 k0Var = k0.l;
        I i10 = new I(4);
        k0Var.getClass();
        f22979m = new C1103z(c1094p, new C1094p(i10, k0Var));
    }

    @Override // v2.InterfaceC2543a
    public final d5.I a(long j3) {
        ArrayList arrayList = this.l;
        if (!arrayList.isEmpty()) {
            if (j3 >= ((Y2.a) arrayList.get(0)).f10630b) {
                ArrayList arrayList2 = new ArrayList();
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    Y2.a aVar = (Y2.a) arrayList.get(i7);
                    if (j3 >= aVar.f10630b && j3 < aVar.d) {
                        arrayList2.add(aVar);
                    }
                    if (j3 < aVar.f10630b) {
                        break;
                    }
                }
                d0 d0VarD = d5.I.D(f22979m, arrayList2);
                C1077F c1077fU = d5.I.u();
                for (int i10 = 0; i10 < d0VarD.size(); i10++) {
                    c1077fU.d(((Y2.a) d0VarD.get(i10)).f10629a);
                }
                return c1077fU.h();
            }
        }
        return d5.I.z();
    }

    @Override // v2.InterfaceC2543a
    public final long b(long j3) {
        int i7 = 0;
        long jMin = -9223372036854775807L;
        while (true) {
            ArrayList arrayList = this.l;
            if (i7 >= arrayList.size()) {
                break;
            }
            long j10 = ((Y2.a) arrayList.get(i7)).f10630b;
            long j11 = ((Y2.a) arrayList.get(i7)).d;
            if (j3 < j10) {
                jMin = jMin == -9223372036854775807L ? j10 : Math.min(jMin, j10);
            } else {
                if (j3 < j11) {
                    jMin = jMin == -9223372036854775807L ? j11 : Math.min(jMin, j11);
                }
                i7++;
            }
        }
        if (jMin != -9223372036854775807L) {
            return jMin;
        }
        return Long.MIN_VALUE;
    }

    @Override // v2.InterfaceC2543a
    public final boolean c(Y2.a aVar, long j3) {
        long j10 = aVar.f10630b;
        AbstractC1346c.c(j10 != -9223372036854775807L);
        AbstractC1346c.c(aVar.c != -9223372036854775807L);
        boolean z6 = j10 <= j3 && j3 < aVar.d;
        ArrayList arrayList = this.l;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (j10 >= ((Y2.a) arrayList.get(size)).f10630b) {
                arrayList.add(size + 1, aVar);
                return z6;
            }
        }
        arrayList.add(0, aVar);
        return z6;
    }

    @Override // v2.InterfaceC2543a
    public final void clear() {
        this.l.clear();
    }

    @Override // v2.InterfaceC2543a
    public final long d(long j3) {
        ArrayList arrayList = this.l;
        if (arrayList.isEmpty()) {
            return -9223372036854775807L;
        }
        if (j3 < ((Y2.a) arrayList.get(0)).f10630b) {
            return -9223372036854775807L;
        }
        long jMax = ((Y2.a) arrayList.get(0)).f10630b;
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            long j10 = ((Y2.a) arrayList.get(i7)).f10630b;
            long j11 = ((Y2.a) arrayList.get(i7)).d;
            if (j11 > j3) {
                if (j10 > j3) {
                    break;
                }
                jMax = Math.max(jMax, j10);
            } else {
                jMax = Math.max(jMax, j11);
            }
        }
        return jMax;
    }

    @Override // v2.InterfaceC2543a
    public final void e(long j3) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i7 >= arrayList.size()) {
                return;
            }
            long j10 = ((Y2.a) arrayList.get(i7)).f10630b;
            if (j3 > j10 && j3 > ((Y2.a) arrayList.get(i7)).d) {
                arrayList.remove(i7);
                i7--;
            } else if (j3 < j10) {
                return;
            }
            i7++;
        }
    }
}
