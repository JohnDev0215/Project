package n2;

import B2.C0063c;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import e2.C1208p;
import e2.c0;
import e2.f0;
import e2.q0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;
import java.util.List;
import l3.C1764u;
import l8.C1789g;
import o2.C2024a;
import o2.C2025b;
import o2.C2026c;
import p2.RunnableC2092g;
import u2.C2492C;
import x1.AbstractC2663b;

/* JADX INFO: renamed from: n2.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1918s implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f19720m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f19721n;

    public /* synthetic */ RunnableC1918s(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f19720m = obj;
        this.f19721n = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j3;
        boolean z6;
        long j10;
        boolean z10 = true;
        switch (this.l) {
            case 0:
                C1923x c1923x = (C1923x) this.f19720m;
                h3.x xVar = (h3.x) this.f19721n;
                int i7 = c1923x.f19734I - xVar.c;
                c1923x.f19734I = i7;
                if (xVar.d) {
                    c1923x.f19735J = xVar.f15497e;
                    c1923x.f19736K = true;
                }
                if (i7 == 0) {
                    f0 f0Var = ((U) xVar.f15498f).f19586a;
                    if (!c1923x.f19767j0.f19586a.p() && f0Var.p()) {
                        c1923x.f19769k0 = -1;
                        c1923x.f19770l0 = 0L;
                    }
                    if (!f0Var.p()) {
                        List listAsList = Arrays.asList(((Z) f0Var).f19618k);
                        AbstractC1346c.j(listAsList.size() == c1923x.f19773p.size());
                        for (int i10 = 0; i10 < listAsList.size(); i10++) {
                            ((C1922w) c1923x.f19773p.get(i10)).c = (f0) listAsList.get(i10);
                        }
                    }
                    if (c1923x.f19736K) {
                        if (((U) xVar.f15498f).f19587b.equals(c1923x.f19767j0.f19587b) && ((U) xVar.f15498f).d == c1923x.f19767j0.f19600s) {
                            z10 = false;
                        }
                        if (z10) {
                            if (f0Var.p() || ((U) xVar.f15498f).f19587b.c()) {
                                j10 = ((U) xVar.f15498f).d;
                            } else {
                                U u4 = (U) xVar.f15498f;
                                C2492C c2492c = u4.f19587b;
                                long j11 = u4.d;
                                Object obj = c2492c.f22663a;
                                c0 c0Var = c1923x.o;
                                f0Var.g(obj, c0Var);
                                j10 = j11 + c0Var.f14061e;
                            }
                            j3 = j10;
                        } else {
                            j3 = -9223372036854775807L;
                        }
                        z6 = z10;
                    } else {
                        j3 = -9223372036854775807L;
                        z6 = false;
                    }
                    c1923x.f19736K = false;
                    c1923x.y1((U) xVar.f15498f, 1, z6, c1923x.f19735J, j3, -1, false);
                    return;
                }
                return;
            case 1:
                X x8 = (X) this.f19721n;
                ((D) this.f19720m).getClass();
                try {
                    synchronized (x8) {
                    }
                    try {
                        x8.f19602a.b(x8.d, x8.f19604e);
                        return;
                    } finally {
                        x8.b(true);
                    }
                } catch (C1913m e10) {
                    AbstractC1346c.q("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e10);
                    throw new RuntimeException(e10);
                }
            case 2:
                C1789g c1789g = (C1789g) this.f19720m;
                C1906f c1906f = (C1906f) this.f19721n;
                c1789g.getClass();
                synchronized (c1906f) {
                }
                SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = (SurfaceHolderCallbackC1920u) c1789g.f18861m;
                int i11 = AbstractC1343A.f15195a;
                o2.e eVar = surfaceHolderCallbackC1920u.l.f19776s;
                eVar.Z(eVar.W((C2492C) eVar.o.f2768q), 1013, new C2025b(16));
                return;
            case 3:
                C0063c c0063c = (C0063c) this.f19721n;
                C1789g c1789g2 = ((p2.x) ((C1764u) this.f19720m).f18608m).f20838O0;
                Handler handler = (Handler) c1789g2.l;
                if (handler != null) {
                    handler.post(new RunnableC2092g(c1789g2, c0063c, 2));
                    return;
                }
                return;
            case 4:
                q2.e eVar2 = (q2.e) this.f19720m;
                q2.f fVar = eVar2.o;
                if (fVar.f21196p == 0 || eVar2.f21185n) {
                    return;
                }
                Looper looper = fVar.f21200t;
                looper.getClass();
                eVar2.f21184m = fVar.g(looper, eVar2.l, (C1208p) this.f19721n, false);
                fVar.f21195n.add(eVar2);
                return;
            case 5:
                ((h2.g) this.f19720m).accept(this.f19721n);
                return;
            case 6:
                ((u2.T) this.f19720m).B((B2.F) this.f19721n);
                return;
            case 7:
                ((AbstractC2663b) this.f19720m).h((Typeface) this.f19721n);
                return;
            case 8:
                m2.b bVar = (m2.b) this.f19720m;
                bVar.getClass();
                int i12 = AbstractC1343A.f15195a;
                C1923x c1923x2 = ((SurfaceHolderCallbackC1920u) bVar.f18945m).l;
                q0 q0Var = (q0) this.f19721n;
                c1923x2.f19763h0 = q0Var;
                c1923x2.f19771m.f(25, new C1919t(q0Var));
                return;
            default:
                m2.b bVar2 = (m2.b) this.f19720m;
                C1906f c1906f2 = (C1906f) this.f19721n;
                bVar2.getClass();
                synchronized (c1906f2) {
                }
                SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u2 = (SurfaceHolderCallbackC1920u) bVar2.f18945m;
                int i13 = AbstractC1343A.f15195a;
                o2.e eVar3 = surfaceHolderCallbackC1920u2.l.f19776s;
                C2024a c2024aW = eVar3.W((C2492C) eVar3.o.f2768q);
                eVar3.Z(c2024aW, 1020, new C2026c(c2024aW, c1906f2));
                return;
        }
    }
}
