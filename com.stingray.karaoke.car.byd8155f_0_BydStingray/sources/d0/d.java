package D0;

import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.F;
import E0.InterfaceC0163m;
import E0.Y;
import E0.q0;
import g0.n;
import g1.AbstractC1274a;

/* JADX INFO: loaded from: classes.dex */
public interface d extends InterfaceC0163m {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r8v0, types: [D0.d, E0.m] */
    /* JADX WARN: Type inference failed for: r8v5, types: [A7.o, z7.a] */
    default Object E(e eVar) {
        Y y10;
        n nVar = ((n) this).l;
        boolean z6 = nVar.f14695x;
        if (!z6) {
            l9.d.g0("ModifierLocal accessed from an unattached node");
            throw null;
        }
        if (!z6) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        n nVar2 = nVar.f14687p;
        F fT = AbstractC0156f.t(this);
        while (fT != null) {
            if ((((n) fT.f1469H.f1603f).o & 32) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 32) != 0) {
                        ?? F9 = nVar2;
                        ?? dVar = 0;
                        while (F9 != 0) {
                            if (F9 instanceof d) {
                                d dVar2 = (d) F9;
                                if (dVar2.i().z(eVar)) {
                                    return dVar2.i().J(eVar);
                                }
                            } else if ((F9.f14686n & 32) != 0 && (F9 instanceof AbstractC0164n)) {
                                n nVar3 = ((AbstractC0164n) F9).f1690z;
                                int i7 = 0;
                                F9 = F9;
                                dVar = dVar;
                                while (nVar3 != null) {
                                    if ((nVar3.f14686n & 32) != 0) {
                                        i7++;
                                        dVar = dVar;
                                        if (i7 == 1) {
                                            F9 = nVar3;
                                        } else {
                                            if (dVar == 0) {
                                                dVar = new V.d(new n[16]);
                                            }
                                            if (F9 != 0) {
                                                dVar.b(F9);
                                                F9 = 0;
                                            }
                                            dVar.b(nVar3);
                                        }
                                    }
                                    nVar3 = nVar3.f14688q;
                                    F9 = F9;
                                    dVar = dVar;
                                }
                                if (i7 == 1) {
                                }
                            }
                            F9 = AbstractC0156f.f(dVar);
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
        return eVar.f1127a.a();
    }

    default AbstractC1274a i() {
        return b.f1122e;
    }
}
