package C0;

import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.r0;
import E0.s0;
import androidx.compose.ui.platform.AndroidComposeView;

/* JADX INFO: loaded from: classes.dex */
public final class F implements d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G f846a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f847b;

    public F(G g7, Object obj) {
        this.f846a = g7;
        this.f847b = obj;
    }

    @Override // C0.d0
    public final void a() {
        G g7 = this.f846a;
        g7.b();
        E0.F f10 = (E0.F) g7.f856u.remove(this.f847b);
        if (f10 != null) {
            if (g7.f861z <= 0) {
                throw new IllegalStateException("No pre-composed items to dispose");
            }
            E0.F f11 = g7.l;
            int iK = ((V.a) f11.r()).l.k(f10);
            int i7 = ((V.a) f11.r()).l.f9279n;
            int i10 = g7.f861z;
            if (iK < i7 - i10) {
                throw new IllegalStateException("Item is not in pre-composed item range");
            }
            g7.f860y++;
            g7.f861z = i10 - 1;
            int i11 = (((V.a) f11.r()).l.f9279n - g7.f861z) - g7.f860y;
            f11.f1490w = true;
            f11.g0(iK, i11, 1);
            f11.f1490w = false;
            g7.a(i11);
        }
    }

    @Override // C0.d0
    public final int c() {
        E0.F f10 = (E0.F) this.f846a.f856u.get(this.f847b);
        if (f10 != null) {
            return ((V.a) f10.p()).l.f9279n;
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [G.g0] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v7 */
    @Override // C0.d0
    public final void d(G.g0 g0Var) {
        E0.Y y10;
        g0.n nVar;
        r0 r0Var;
        E0.F f10 = (E0.F) this.f846a.f856u.get(this.f847b);
        if (f10 == null || (y10 = f10.f1469H) == null || (nVar = (g0.n) y10.f1603f) == null) {
            return;
        }
        g0.n nVar2 = nVar.l;
        if (!nVar2.f14695x) {
            l9.d.h0("visitSubtreeIf called on an unattached node");
            throw null;
        }
        V.d dVar = new V.d(new g0.n[16]);
        g0.n nVar3 = nVar2.f14688q;
        if (nVar3 == null) {
            AbstractC0156f.b(dVar, nVar2);
        } else {
            dVar.b(nVar3);
        }
        while (dVar.m()) {
            g0.n nVar4 = (g0.n) dVar.o(dVar.f9279n - 1);
            if ((nVar4.o & 262144) != 0) {
                for (g0.n nVar5 = nVar4; nVar5 != null; nVar5 = nVar5.f14688q) {
                    if ((nVar5.f14686n & 262144) != 0) {
                        ?? dVar2 = 0;
                        ?? F9 = nVar5;
                        while (F9 != 0) {
                            if (F9 instanceof s0) {
                                s0 s0Var = (s0) F9;
                                boolean zEquals = "androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode".equals(s0Var.r());
                                r0 r0Var2 = r0.f1697m;
                                if (zEquals) {
                                    g0Var.b(s0Var);
                                    r0Var = r0Var2;
                                } else {
                                    r0Var = r0.l;
                                }
                                if (r0Var == r0.f1698n) {
                                    return;
                                }
                                if (r0Var == r0Var2) {
                                    break;
                                }
                            } else if ((F9.f14686n & 262144) != 0 && (F9 instanceof AbstractC0164n)) {
                                g0.n nVar6 = ((AbstractC0164n) F9).f1690z;
                                int i7 = 0;
                                F9 = F9;
                                dVar2 = dVar2;
                                while (nVar6 != null) {
                                    if ((nVar6.f14686n & 262144) != 0) {
                                        i7++;
                                        dVar2 = dVar2;
                                        if (i7 == 1) {
                                            F9 = nVar6;
                                        } else {
                                            if (dVar2 == 0) {
                                                dVar2 = new V.d(new g0.n[16]);
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

    @Override // C0.d0
    public final void e(long j3, int i7) {
        G g7 = this.f846a;
        E0.F f10 = (E0.F) g7.f856u.get(this.f847b);
        if (f10 == null || !f10.V()) {
            return;
        }
        int i10 = ((V.a) f10.p()).l.f9279n;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException("Index (" + i7 + ") is out of bound of [0, " + i10 + ')');
        }
        if (f10.W()) {
            throw new IllegalArgumentException("Pre-measure called on node that is not placed");
        }
        E0.F f11 = g7.l;
        f11.f1490w = true;
        ((AndroidComposeView) E0.I.a(f10)).q((E0.F) ((V.a) f10.p()).get(i7), j3);
        f11.f1490w = false;
    }
}
