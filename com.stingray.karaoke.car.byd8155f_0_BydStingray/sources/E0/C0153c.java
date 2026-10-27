package E0;

import C0.AbstractC0099f;
import C0.InterfaceC0115w;
import D.AbstractC0143z;
import G.C0293c;
import G.C0306p;
import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import d1.C1059c;
import g1.AbstractC1274a;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import k0.InterfaceC1589a;
import k0.InterfaceC1593e;
import l0.EnumC1726q;
import l0.InterfaceC1712c;
import l0.InterfaceC1718i;
import l0.InterfaceC1721l;
import l0.InterfaceC1724o;
import q7.C2196j;
import y0.AbstractC2737o;
import y0.C2730h;
import y0.EnumC2731i;

/* JADX INFO: renamed from: E0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0153c extends g0.n implements InterfaceC0174y, InterfaceC0165o, p0, n0, D0.d, l0, InterfaceC0173x, InterfaceC0166p, InterfaceC1712c, InterfaceC1721l, InterfaceC1724o, j0, InterfaceC1589a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public HashSet f1616A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public g0.m f1617y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public D0.a f1618z;

    @Override // E0.j0
    public final boolean A() {
        return this.f14695x;
    }

    @Override // g0.n
    public final void A0() {
        I0();
    }

    @Override // l0.InterfaceC1712c
    public final void D(EnumC1726q enumC1726q) {
        l9.d.h0("onFocusEvent called on wrong node");
        throw null;
    }

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
    /* JADX WARN: Type inference failed for: r8v4, types: [A7.o, z7.a] */
    @Override // D0.d
    public final Object E(D0.e eVar) {
        Y y10;
        this.f1616A.add(eVar);
        g0.n nVar = this.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        g0.n nVar2 = nVar.f14687p;
        F fT = AbstractC0156f.t(this);
        while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 32) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 32) != 0) {
                        ?? F9 = nVar2;
                        ?? dVar = 0;
                        while (F9 != 0) {
                            if (F9 instanceof D0.d) {
                                D0.d dVar2 = (D0.d) F9;
                                if (dVar2.i().z(eVar)) {
                                    return dVar2.i().J(eVar);
                                }
                            } else if ((F9.f14686n & 32) != 0 && (F9 instanceof AbstractC0164n)) {
                                g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
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
                                                dVar = new V.d(new g0.n[16]);
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

    @Override // E0.p0
    public final void H(L0.j jVar) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsModifier");
        AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) mVar;
        L0.j jVar2 = new L0.j();
        jVar2.f3909m = appendedSemanticsElement.f11692a;
        appendedSemanticsElement.f11693b.b(jVar2);
        A7.m.d(jVar, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsConfiguration");
        if (jVar2.f3909m) {
            jVar.f3909m = true;
        }
        if (jVar2.f3910n) {
            jVar.f3910n = true;
        }
        for (Map.Entry entry : jVar2.l.entrySet()) {
            L0.u uVar = (L0.u) entry.getKey();
            Object value = entry.getValue();
            LinkedHashMap linkedHashMap = jVar.l;
            if (!linkedHashMap.containsKey(uVar)) {
                linkedHashMap.put(uVar, value);
            } else if (value instanceof L0.a) {
                Object obj = linkedHashMap.get(uVar);
                A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
                L0.a aVar = (L0.a) obj;
                String str = aVar.f3875a;
                if (str == null) {
                    str = ((L0.a) value).f3875a;
                }
                m7.c cVar = aVar.f3876b;
                if (cVar == null) {
                    cVar = ((L0.a) value).f3876b;
                }
                linkedHashMap.put(uVar, new L0.a(str, cVar));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H0(boolean r6) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.C0153c.H0(boolean):void");
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [A7.o, z7.a] */
    public final void I0() {
        if (!this.f14695x) {
            l9.d.h0("unInitializeModifier called on unattached node");
            throw null;
        }
        g0.m mVar = this.f1617y;
        if ((this.f14686n & 32) != 0) {
            if (mVar instanceof C0306p) {
                D0.c modifierLocalManager = AbstractC0156f.u(this).getModifierLocalManager();
                ((C0306p) mVar).getClass();
                D0.e eVar = AbstractC0099f.f896a;
                modifierLocalManager.d.b(AbstractC0156f.t(this));
                modifierLocalManager.f1125e.b(eVar);
                modifierLocalManager.a();
            }
            if (mVar instanceof AbstractC0143z) {
                AbstractC0143z abstractC0143z = (AbstractC0143z) mVar;
                C0154d c0154d = AbstractC0156f.f1666a;
                abstractC0143z.getClass();
                D0.e eVar2 = D.Y.f1059a;
                c0154d.getClass();
                D.D d = (D.D) eVar2.f1127a.a();
                if (!A7.m.a(d, abstractC0143z.f1120a)) {
                    abstractC0143z.f1120a = d;
                    throw null;
                }
            }
        }
        if ((this.f14686n & 8) != 0) {
            ((AndroidComposeView) AbstractC0156f.u(this)).x();
        }
    }

    public final void J0() {
        if (this.f14695x) {
            this.f1616A.clear();
            AbstractC0156f.u(this).getSnapshotObserver().a(this, C0155e.f1652n, new C0152b(this, 1));
        }
    }

    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object, java.util.List] */
    @Override // E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        boolean z6;
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        B5.u uVar = ((y0.t) mVar).d;
        ?? r92 = c2730h.f24065a;
        y0.t tVar = (y0.t) uVar.o;
        if (tVar.c) {
            z6 = true;
            break;
        }
        int size = r92.size();
        for (int i7 = 0; i7 < size; i7++) {
            y0.q qVar = (y0.q) r92.get(i7);
            if (AbstractC2737o.a(qVar) || AbstractC2737o.c(qVar)) {
                z6 = true;
                break;
            }
        }
        z6 = false;
        int i10 = uVar.f735m;
        EnumC2731i enumC2731i2 = EnumC2731i.f24068n;
        if (i10 != 3) {
            if (enumC2731i == EnumC2731i.l && z6) {
                uVar.l(c2730h);
            }
            if (enumC2731i == enumC2731i2 && !z6) {
                uVar.l(c2730h);
            }
        }
        if (enumC2731i == enumC2731i2) {
            int size2 = r92.size();
            for (int i11 = 0; i11 < size2; i11++) {
                if (!AbstractC2737o.c((y0.q) r92.get(i11))) {
                    return;
                }
            }
            uVar.f735m = 1;
            tVar.c = false;
        }
    }

    @Override // E0.n0
    public final boolean V() {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        ((y0.t) mVar).d.getClass();
        return true;
    }

    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.draw.DrawModifier");
        ((InterfaceC1593e) mVar).a(h10);
    }

    @Override // E0.InterfaceC0174y
    public final int b(Q q4, C0.J j3, int i7) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((InterfaceC0115w) mVar).b(q4, j3, i7);
    }

    @Override // E0.InterfaceC0174y
    public final int c(Q q4, C0.J j3, int i7) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((InterfaceC0115w) mVar).c(q4, j3, i7);
    }

    @Override // E0.InterfaceC0174y
    public final int d(Q q4, C0.J j3, int i7) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((InterfaceC0115w) mVar).d(q4, j3, i7);
    }

    @Override // k0.InterfaceC1589a
    public final long e() {
        return H7.H.S(AbstractC0156f.r(this, 128).f872n);
    }

    @Override // E0.n0
    public final void e0() {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        B5.u uVar = ((y0.t) mVar).d;
        if (uVar.f735m == 2) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            motionEventObtain.setSource(0);
            y0.t tVar = (y0.t) uVar.o;
            ((C1059c) tVar.i()).b(motionEventObtain);
            motionEventObtain.recycle();
            uVar.f735m = 1;
            tVar.c = false;
        }
    }

    @Override // E0.InterfaceC0174y
    public final int f(Q q4, C0.J j3, int i7) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((InterfaceC0115w) mVar).f(q4, j3, i7);
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((InterfaceC0115w) mVar).g(m10, j3, j10);
    }

    @Override // k0.InterfaceC1589a
    public final a1.b getDensity() {
        return AbstractC0156f.t(this).f1464C;
    }

    @Override // k0.InterfaceC1589a
    public final a1.k getLayoutDirection() {
        return AbstractC0156f.t(this).f1465D;
    }

    @Override // D0.d
    public final AbstractC1274a i() {
        D0.a aVar = this.f1618z;
        return aVar != null ? aVar : D0.b.f1122e;
    }

    @Override // E0.l0
    public final Object j(a1.b bVar, Object obj) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.ParentDataModifier");
        return ((C0.S) mVar).j();
    }

    @Override // E0.InterfaceC0166p
    public final void l0(d0 d0Var) {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.layout.OnGloballyPositionedModifier");
        C0293c c0293c = (C0293c) mVar;
        if (c0293c.f2549a) {
            return;
        }
        c0293c.f2549a = true;
        C2196j c2196j = c0293c.f2550b;
        if (c2196j != null) {
            c2196j.resumeWith(m7.q.f19267a);
        }
        c0293c.f2550b = null;
    }

    @Override // E0.InterfaceC0165o
    public final void n0() {
        AbstractC0156f.m(this);
    }

    @Override // l0.InterfaceC1721l
    public final void o(InterfaceC1718i interfaceC1718i) {
        l9.d.h0("applyFocusProperties called on wrong node");
        throw null;
    }

    @Override // E0.n0
    public final void o0() {
        g0.m mVar = this.f1617y;
        A7.m.d(mVar, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
    }

    @Override // E0.InterfaceC0173x
    public final void t0(C0.r rVar) {
    }

    public final String toString() {
        return this.f1617y.toString();
    }

    @Override // E0.InterfaceC0173x
    public final void u(long j3) {
    }

    @Override // g0.n
    public final void z0() {
        H0(true);
    }
}
