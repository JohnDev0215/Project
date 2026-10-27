package D0;

import A.I0;
import D.AbstractC0143z;
import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.C0153c;
import E0.F;
import androidx.compose.ui.platform.AndroidComposeView;
import g0.n;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AndroidComposeView f1123a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V.d f1124b = new V.d(new C0153c[16]);
    public final V.d c = new V.d(new e[16]);
    public final V.d d = new V.d(new F[16]);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V.d f1125e = new V.d(new e[16]);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1126f;

    public c(AndroidComposeView androidComposeView) {
        this.f1123a = androidComposeView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static void b(n nVar, e eVar, HashSet hashSet) {
        n nVar2 = nVar.l;
        if (!nVar2.f14695x) {
            l9.d.h0("visitSubtreeIf called on an unattached node");
            throw null;
        }
        V.d dVar = new V.d(new n[16]);
        n nVar3 = nVar2.f14688q;
        if (nVar3 == null) {
            AbstractC0156f.b(dVar, nVar2);
        } else {
            dVar.b(nVar3);
        }
        while (dVar.m()) {
            n nVar4 = (n) dVar.o(dVar.f9279n - 1);
            if ((nVar4.o & 32) != 0) {
                for (n nVar5 = nVar4; nVar5 != null; nVar5 = nVar5.f14688q) {
                    if ((nVar5.f14686n & 32) != 0) {
                        ?? dVar2 = 0;
                        ?? F9 = nVar5;
                        while (F9 != 0) {
                            if (F9 instanceof d) {
                                d dVar3 = (d) F9;
                                if (dVar3 instanceof C0153c) {
                                    C0153c c0153c = (C0153c) dVar3;
                                    if ((c0153c.f1617y instanceof AbstractC0143z) && c0153c.f1616A.contains(eVar)) {
                                        hashSet.add(dVar3);
                                    }
                                }
                                if (dVar3.i().z(eVar)) {
                                    break;
                                }
                            } else if ((F9.f14686n & 32) != 0 && (F9 instanceof AbstractC0164n)) {
                                n nVar6 = ((AbstractC0164n) F9).f1690z;
                                int i7 = 0;
                                F9 = F9;
                                dVar2 = dVar2;
                                while (nVar6 != null) {
                                    if ((nVar6.f14686n & 32) != 0) {
                                        i7++;
                                        dVar2 = dVar2;
                                        if (i7 == 1) {
                                            F9 = nVar6;
                                        } else {
                                            if (dVar2 == 0) {
                                                dVar2 = new V.d(new n[16]);
                                            }
                                            if (F9 != 0) {
                                                dVar2.b(F9);
                                                F9 = 0;
                                            }
                                            dVar2.b(nVar6);
                                        }
                                    }
                                    nVar6 = nVar6.f14688q;
                                    F9 = F9;
                                    dVar2 = dVar2;
                                }
                                if (i7 == 1) {
                                }
                            }
                            F9 = AbstractC0156f.f(dVar2);
                        }
                    }
                }
            }
            AbstractC0156f.b(dVar, nVar4);
        }
    }

    public final void a() {
        if (this.f1126f) {
            return;
        }
        this.f1126f = true;
        I0 i02 = new I0(this, 4);
        V.d dVar = this.f1123a.B0;
        if (dVar.i(i02)) {
            return;
        }
        dVar.b(i02);
    }
}
