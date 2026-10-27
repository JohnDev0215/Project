package l0;

import A.C0022k;
import C0.c0;
import D.Q;
import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.F;
import E0.Y;
import E0.d0;
import E0.q0;
import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.Arrays;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: l0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1713d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f18326a = new int[2];

    public static final boolean A(C1727r c1727r) {
        Boolean boolB = B(c1727r, 7);
        if (boolB != null) {
            return boolB.booleanValue();
        }
        return false;
    }

    public static final Boolean B(C1727r c1727r, int i7) {
        Boolean boolValueOf;
        t4.e eVarE = E(c1727r);
        C1728s c1728s = new C1728s(c1727r, 1);
        try {
            if (eVarE.f22462m) {
                t4.e.b(eVarE);
            }
            eVarE.f22462m = true;
            ((V.d) eVarE.o).b(c1728s);
            int iC = AbstractC2641j.c(v(c1727r, i7));
            if (iC == 0) {
                boolValueOf = Boolean.valueOf(w(c1727r));
            } else if (iC == 1) {
                boolValueOf = null;
            } else if (iC == 2) {
                boolValueOf = Boolean.TRUE;
            } else {
                if (iC != 3) {
                    throw new A9.k();
                }
                boolValueOf = null;
            }
            return boolValueOf;
        } finally {
            t4.e.c(eVarE);
        }
    }

    public static final boolean C(C1727r c1727r, C1727r c1727r2) {
        g0.n nVarF;
        g0.n nVarF2;
        Y y10;
        Y y11;
        g0.n nVar = c1727r2.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        g0.n nVar2 = nVar.f14687p;
        F fT = AbstractC0156f.t(c1727r2);
        loop0: while (true) {
            if (fT == null) {
                nVarF = null;
                break;
            }
            if ((((g0.n) fT.f1469H.f1603f).o & 1024) != 0) {
                while (nVar2 != null) {
                    if ((nVar2.f14686n & 1024) != 0) {
                        nVarF = nVar2;
                        V.d dVar = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                break loop0;
                            }
                            if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i7 = 0;
                                for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                    if ((nVar3.f14686n & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            nVarF = nVar3;
                                        } else {
                                            if (dVar == null) {
                                                dVar = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar.b(nVar3);
                                        }
                                    }
                                }
                                if (i7 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar);
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y11 = fT.f1469H) == null) ? null : (q0) y11.f1602e;
        }
        if (!A7.m.a(nVarF, c1727r)) {
            throw new IllegalStateException("Non child node cannot request focus.");
        }
        int iOrdinal = c1727r.I0().ordinal();
        EnumC1726q enumC1726q = EnumC1726q.f18351m;
        if (iOrdinal == 0) {
            p(c1727r2);
            c1727r.M0(enumC1726q);
        } else if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                return false;
            }
            if (iOrdinal != 3) {
                throw new A9.k();
            }
            g0.n nVar4 = c1727r.l;
            if (!nVar4.f14695x) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            g0.n nVar5 = nVar4.f14687p;
            F fT2 = AbstractC0156f.t(c1727r);
            loop4: while (true) {
                if (fT2 == null) {
                    nVarF2 = null;
                    break;
                }
                if ((((g0.n) fT2.f1469H.f1603f).o & 1024) != 0) {
                    while (nVar5 != null) {
                        if ((nVar5.f14686n & 1024) != 0) {
                            nVarF2 = nVar5;
                            V.d dVar2 = null;
                            while (nVarF2 != null) {
                                if (nVarF2 instanceof C1727r) {
                                    break loop4;
                                }
                                if ((nVarF2.f14686n & 1024) != 0 && (nVarF2 instanceof AbstractC0164n)) {
                                    int i10 = 0;
                                    for (g0.n nVar6 = ((AbstractC0164n) nVarF2).f1690z; nVar6 != null; nVar6 = nVar6.f14688q) {
                                        if ((nVar6.f14686n & 1024) != 0) {
                                            i10++;
                                            if (i10 == 1) {
                                                nVarF2 = nVar6;
                                            } else {
                                                if (dVar2 == null) {
                                                    dVar2 = new V.d(new g0.n[16]);
                                                }
                                                if (nVarF2 != null) {
                                                    dVar2.b(nVarF2);
                                                    nVarF2 = null;
                                                }
                                                dVar2.b(nVar6);
                                            }
                                        }
                                    }
                                    if (i10 == 1) {
                                    }
                                }
                                nVarF2 = AbstractC0156f.f(dVar2);
                            }
                        }
                        nVar5 = nVar5.f14687p;
                    }
                }
                fT2 = fT2.G();
                nVar5 = (fT2 == null || (y10 = fT2.f1469H) == null) ? null : (q0) y10.f1602e;
            }
            C1727r c1727r3 = (C1727r) nVarF2;
            if (c1727r3 != null || !((Boolean) ((androidx.compose.ui.focus.b) AbstractC0156f.u(c1727r).getFocusOwner()).f11566a.invoke(null, null)).booleanValue()) {
                if (c1727r3 == null || !C(c1727r3, c1727r)) {
                    return false;
                }
                boolean zC = C(c1727r, c1727r2);
                if (c1727r.I0() != enumC1726q) {
                    throw new IllegalStateException("Deactivated node is focused");
                }
                if (!zC) {
                    return zC;
                }
                z(c1727r3);
                return zC;
            }
            p(c1727r2);
            c1727r.M0(enumC1726q);
        } else {
            if (n(c1727r) == null) {
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            C1727r c1727rN = n(c1727r);
            if (!(c1727rN != null ? e(c1727rN, false, true) : true)) {
                return false;
            }
            p(c1727r2);
        }
        return true;
    }

    public static final boolean D(View view, Integer num, Rect rect) {
        if (num == null) {
            return view.requestFocus();
        }
        if (!(view instanceof ViewGroup)) {
            return view.requestFocus(num.intValue(), rect);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isFocused()) {
            return true;
        }
        if ((!viewGroup.isFocusable() || view.hasFocus()) && !(view instanceof AndroidComposeView)) {
            if (rect != null) {
                View viewFindNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(viewGroup, rect, num.intValue());
                return viewFindNextFocusFromRect != null ? viewFindNextFocusFromRect.requestFocus(num.intValue(), rect) : view.requestFocus(num.intValue(), rect);
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(viewGroup, view.hasFocus() ? view.findFocus() : null, num.intValue());
            return viewFindNextFocus != null ? viewFindNextFocus.requestFocus(num.intValue()) : view.requestFocus(num.intValue());
        }
        return view.requestFocus(num.intValue(), rect);
    }

    public static final t4.e E(C1727r c1727r) {
        return ((androidx.compose.ui.focus.b) AbstractC0156f.u(c1727r).getFocusOwner()).f11571h;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00a9 A[PHI: r0
  0x00a9: PHI (r0v16 int) = (r0v4 int), (r0v5 int), (r0v6 int), (r0v7 int) binds: [B:53:0x00a7, B:56:0x00b0, B:59:0x00b8, B:62:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object F(l0.C1727r r9, int r10, z7.InterfaceC2782k r11) {
        /*
            Method dump skipped, instruction units count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.F(l0.r, int, z7.k):java.lang.Object");
    }

    public static final boolean G(int i7, C0022k c0022k, C1727r c1727r, m0.d dVar) {
        C1727r c1727rH;
        V.d dVar2 = new V.d(new C1727r[16]);
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        V.d dVar3 = new V.d(new g0.n[16]);
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 == null) {
            AbstractC0156f.b(dVar3, nVar);
        } else {
            dVar3.b(nVar2);
        }
        while (dVar3.m()) {
            g0.n nVarF = (g0.n) dVar3.o(dVar3.f9279n - 1);
            if ((nVarF.o & 1024) == 0) {
                AbstractC0156f.b(dVar3, nVarF);
            } else {
                while (true) {
                    if (nVarF == null) {
                        break;
                    }
                    if ((nVarF.f14686n & 1024) != 0) {
                        V.d dVar4 = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r2 = (C1727r) nVarF;
                                if (c1727r2.f14695x) {
                                    dVar2.b(c1727r2);
                                }
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i10 = 0;
                                for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                    if ((nVar3.f14686n & 1024) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nVarF = nVar3;
                                        } else {
                                            if (dVar4 == null) {
                                                dVar4 = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar4.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar4.b(nVar3);
                                        }
                                    }
                                }
                                if (i10 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar4);
                        }
                    } else {
                        nVarF = nVarF.f14688q;
                    }
                }
            }
        }
        while (dVar2.m() && (c1727rH = h(dVar2, dVar, i7)) != null) {
            if (c1727rH.H0().f18338a) {
                return ((Boolean) c0022k.b(c1727rH)).booleanValue();
            }
            if (l(i7, c0022k, c1727rH, dVar)) {
                return true;
            }
            dVar2.n(c1727rH);
        }
        return false;
    }

    public static final boolean H(C1727r c1727r, C1727r c1727r2, int i7, C0022k c0022k) {
        g0.n nVar;
        Y y10;
        if (c1727r.I0() != EnumC1726q.f18351m) {
            throw new IllegalStateException("This function should only be used within a parent that has focus.");
        }
        Object[] objArrCopyOf = new C1727r[16];
        g0.n nVar2 = c1727r.l;
        if (!nVar2.f14695x) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        V.d dVar = new V.d(new g0.n[16]);
        g0.n nVar3 = nVar2.f14688q;
        if (nVar3 == null) {
            AbstractC0156f.b(dVar, nVar2);
        } else {
            dVar.b(nVar3);
        }
        int i10 = 0;
        while (dVar.m()) {
            g0.n nVarF = (g0.n) dVar.o(dVar.f9279n - 1);
            if ((nVarF.o & 1024) == 0) {
                AbstractC0156f.b(dVar, nVarF);
            } else {
                while (true) {
                    if (nVarF == null) {
                        break;
                    }
                    if ((nVarF.f14686n & 1024) != 0) {
                        V.d dVar2 = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r3 = (C1727r) nVarF;
                                int i11 = i10 + 1;
                                if (objArrCopyOf.length < i11) {
                                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, Math.max(i11, objArrCopyOf.length * 2));
                                    A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
                                }
                                objArrCopyOf[i10] = c1727r3;
                                i10 = i11;
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i12 = 0;
                                for (g0.n nVar4 = ((AbstractC0164n) nVarF).f1690z; nVar4 != null; nVar4 = nVar4.f14688q) {
                                    if ((nVar4.f14686n & 1024) != 0) {
                                        i12++;
                                        if (i12 == 1) {
                                            nVarF = nVar4;
                                        } else {
                                            if (dVar2 == null) {
                                                dVar2 = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar2.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar2.b(nVar4);
                                        }
                                    }
                                }
                                if (i12 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar2);
                        }
                    } else {
                        nVarF = nVarF.f14688q;
                    }
                }
            }
        }
        C1729t c1729t = C1729t.l;
        A7.m.f(objArrCopyOf, "<this>");
        Arrays.sort(objArrCopyOf, 0, i10, c1729t);
        if (C1711b.a(i7, 1)) {
            int i13 = new G7.d(0, i10 - 1, 1).f2712m;
            if (i13 >= 0) {
                boolean z6 = false;
                int i14 = 0;
                while (true) {
                    if (z6) {
                        C1727r c1727r4 = (C1727r) objArrCopyOf[i14];
                        if (s(c1727r4) && k(c1727r4, c0022k)) {
                            return true;
                        }
                    }
                    if (A7.m.a(objArrCopyOf[i14], c1727r2)) {
                        z6 = true;
                    }
                    if (i14 == i13) {
                        break;
                    }
                    i14++;
                }
            }
        } else {
            if (!C1711b.a(i7, 2)) {
                throw new IllegalStateException("This function should only be used for 1-D focus search");
            }
            int i15 = new G7.d(0, i10 - 1, 1).f2712m;
            if (i15 >= 0) {
                boolean z10 = false;
                while (true) {
                    if (z10) {
                        C1727r c1727r5 = (C1727r) objArrCopyOf[i15];
                        if (s(c1727r5) && a(c1727r5, c0022k)) {
                            return true;
                        }
                    }
                    if (A7.m.a(objArrCopyOf[i15], c1727r2)) {
                        z10 = true;
                    }
                    if (i15 == 0) {
                        break;
                    }
                    i15--;
                }
            }
        }
        if (!C1711b.a(i7, 1) && c1727r.H0().f18338a) {
            g0.n nVar5 = c1727r.l;
            if (!nVar5.f14695x) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            g0.n nVar6 = nVar5.f14687p;
            F fT = AbstractC0156f.t(c1727r);
            loop5: while (true) {
                if (fT == null) {
                    nVar = null;
                    break;
                }
                if ((((g0.n) fT.f1469H.f1603f).o & 1024) != 0) {
                    while (nVar6 != null) {
                        if ((nVar6.f14686n & 1024) != 0) {
                            g0.n nVarF2 = nVar6;
                            V.d dVar3 = null;
                            while (nVarF2 != null) {
                                if (nVarF2 instanceof C1727r) {
                                    nVar = nVarF2;
                                    break loop5;
                                }
                                if ((nVarF2.f14686n & 1024) != 0 && (nVarF2 instanceof AbstractC0164n)) {
                                    int i16 = 0;
                                    for (g0.n nVar7 = ((AbstractC0164n) nVarF2).f1690z; nVar7 != null; nVar7 = nVar7.f14688q) {
                                        if ((nVar7.f14686n & 1024) != 0) {
                                            i16++;
                                            if (i16 == 1) {
                                                nVarF2 = nVar7;
                                            } else {
                                                if (dVar3 == null) {
                                                    dVar3 = new V.d(new g0.n[16]);
                                                }
                                                if (nVarF2 != null) {
                                                    dVar3.b(nVarF2);
                                                    nVarF2 = null;
                                                }
                                                dVar3.b(nVar7);
                                            }
                                        }
                                    }
                                    if (i16 == 1) {
                                    }
                                }
                                nVarF2 = AbstractC0156f.f(dVar3);
                            }
                        }
                        nVar6 = nVar6.f14687p;
                    }
                }
                fT = fT.G();
                nVar6 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
            }
            if (nVar != null) {
                return ((Boolean) c0022k.b(c1727r)).booleanValue();
            }
        }
        return false;
    }

    public static final Integer I(int i7) {
        if (C1711b.a(i7, 5)) {
            return 33;
        }
        if (C1711b.a(i7, 6)) {
            return 130;
        }
        if (C1711b.a(i7, 3)) {
            return 17;
        }
        if (C1711b.a(i7, 4)) {
            return 66;
        }
        if (C1711b.a(i7, 1)) {
            return 2;
        }
        return C1711b.a(i7, 2) ? 1 : null;
    }

    public static final C1711b J(int i7) {
        if (i7 == 1) {
            return new C1711b(2);
        }
        if (i7 == 2) {
            return new C1711b(1);
        }
        if (i7 == 17) {
            return new C1711b(3);
        }
        if (i7 == 33) {
            return new C1711b(5);
        }
        if (i7 == 66) {
            return new C1711b(4);
        }
        if (i7 != 130) {
            return null;
        }
        return new C1711b(6);
    }

    public static final Boolean K(int i7, C0022k c0022k, C1727r c1727r, m0.d dVar) {
        int iOrdinal = c1727r.I0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                C1727r c1727rN = n(c1727r);
                if (c1727rN == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild");
                }
                int iOrdinal2 = c1727rN.I0().ordinal();
                if (iOrdinal2 != 0) {
                    if (iOrdinal2 == 1) {
                        Boolean boolK = K(i7, c0022k, c1727rN, dVar);
                        if (!A7.m.a(boolK, Boolean.FALSE)) {
                            return boolK;
                        }
                        if (dVar == null) {
                            if (c1727rN.I0() != EnumC1726q.f18351m) {
                                throw new IllegalStateException("Searching for active node in inactive hierarchy");
                            }
                            C1727r c1727rG = g(c1727rN);
                            if (c1727rG == null) {
                                throw new IllegalStateException("ActiveParent must have a focusedChild");
                            }
                            dVar = j(c1727rG);
                        }
                        return Boolean.valueOf(l(i7, c0022k, c1727r, dVar));
                    }
                    if (iOrdinal2 != 2) {
                        if (iOrdinal2 != 3) {
                            throw new A9.k();
                        }
                        throw new IllegalStateException("ActiveParent must have a focusedChild");
                    }
                }
                if (dVar == null) {
                    dVar = j(c1727rN);
                }
                return Boolean.valueOf(l(i7, c0022k, c1727r, dVar));
            }
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    return c1727r.H0().f18338a ? (Boolean) c0022k.b(c1727r) : dVar == null ? Boolean.valueOf(i(c1727r, i7, c0022k)) : Boolean.valueOf(G(i7, c0022k, c1727r, dVar));
                }
                throw new A9.k();
            }
        }
        return Boolean.valueOf(i(c1727r, i7, c0022k));
    }

    public static final boolean a(C1727r c1727r, C0022k c0022k) {
        int iOrdinal = c1727r.I0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                C1727r c1727rN = n(c1727r);
                if (c1727rN == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild");
                }
                int iOrdinal2 = c1727rN.I0().ordinal();
                if (iOrdinal2 != 0) {
                    if (iOrdinal2 != 1) {
                        if (iOrdinal2 != 2) {
                            if (iOrdinal2 != 3) {
                                throw new A9.k();
                            }
                            throw new IllegalStateException("ActiveParent must have a focusedChild");
                        }
                    } else if (!a(c1727rN, c0022k) && !m(c1727r, c1727rN, 2, c0022k) && (!c1727rN.H0().f18338a || !((Boolean) c0022k.b(c1727rN)).booleanValue())) {
                        return false;
                    }
                }
                return m(c1727r, c1727rN, 2, c0022k);
            }
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new A9.k();
                }
                if (!x(c1727r, c0022k)) {
                    if (!(c1727r.H0().f18338a ? ((Boolean) c0022k.b(c1727r)).booleanValue() : false)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return x(c1727r, c0022k);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean b(m0.d r16, m0.d r17, m0.d r18, int r19) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.b(m0.d, m0.d, m0.d, int):boolean");
    }

    public static final boolean c(int i7, m0.d dVar, m0.d dVar2) {
        if (C1711b.a(i7, 3) ? true : C1711b.a(i7, 4)) {
            if (dVar.d > dVar2.f18883b && dVar.f18883b < dVar2.d) {
                return true;
            }
        } else {
            if (!(C1711b.a(i7, 5) ? true : C1711b.a(i7, 6))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            if (dVar.c > dVar2.f18882a && dVar.f18882a < dVar2.c) {
                return true;
            }
        }
        return false;
    }

    public static final m0.d d(View view) {
        int[] iArr = f18326a;
        view.getLocationInWindow(iArr);
        float f10 = iArr[0];
        return new m0.d(f10, iArr[1], view.getWidth() + f10, iArr[1] + view.getHeight());
    }

    public static final boolean e(C1727r c1727r, boolean z6, boolean z10) {
        int iOrdinal = c1727r.I0().ordinal();
        EnumC1726q enumC1726q = EnumC1726q.f18352n;
        if (iOrdinal == 0) {
            c1727r.M0(enumC1726q);
            if (z10) {
                z(c1727r);
            }
        } else if (iOrdinal == 1) {
            C1727r c1727rN = n(c1727r);
            if (!(c1727rN != null ? e(c1727rN, z6, z10) : true)) {
                return false;
            }
            c1727r.M0(enumC1726q);
            if (z10) {
                z(c1727r);
            }
        } else {
            if (iOrdinal == 2) {
                if (!z6) {
                    return z6;
                }
                c1727r.M0(enumC1726q);
                if (!z10) {
                    return z6;
                }
                z(c1727r);
                return z6;
            }
            if (iOrdinal != 3) {
                throw new A9.k();
            }
        }
        return true;
    }

    public static final void f(C1727r c1727r, V.d dVar) {
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        V.d dVar2 = new V.d(new g0.n[16]);
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 == null) {
            AbstractC0156f.b(dVar2, nVar);
        } else {
            dVar2.b(nVar2);
        }
        while (dVar2.m()) {
            g0.n nVarF = (g0.n) dVar2.o(dVar2.f9279n - 1);
            if ((nVarF.o & 1024) == 0) {
                AbstractC0156f.b(dVar2, nVarF);
            } else {
                while (true) {
                    if (nVarF == null) {
                        break;
                    }
                    if ((nVarF.f14686n & 1024) != 0) {
                        V.d dVar3 = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r2 = (C1727r) nVarF;
                                if (c1727r2.f14695x && !AbstractC0156f.t(c1727r2).f1478Q) {
                                    if (c1727r2.H0().f18338a) {
                                        dVar.b(c1727r2);
                                    } else {
                                        f(c1727r2, dVar);
                                    }
                                }
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i7 = 0;
                                for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                    if ((nVar3.f14686n & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            nVarF = nVar3;
                                        } else {
                                            if (dVar3 == null) {
                                                dVar3 = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar3.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar3.b(nVar3);
                                        }
                                    }
                                }
                                if (i7 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar3);
                        }
                    } else {
                        nVarF = nVarF.f14688q;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0035, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final l0.C1727r g(l0.C1727r r8) {
        /*
            l0.q r0 = r8.I0()
            int r0 = r0.ordinal()
            if (r0 == 0) goto Lad
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L1b
            r1 = 2
            if (r0 == r1) goto Lad
            r8 = 3
            if (r0 != r8) goto L15
            return r2
        L15:
            A9.k r8 = new A9.k
            r8.<init>()
            throw r8
        L1b:
            g0.n r8 = r8.l
            boolean r0 = r8.f14695x
            if (r0 == 0) goto La5
            V.d r0 = new V.d
            r3 = 16
            g0.n[] r4 = new g0.n[r3]
            r0.<init>(r4)
            g0.n r4 = r8.f14688q
            if (r4 != 0) goto L32
            E0.AbstractC0156f.b(r0, r8)
            goto L35
        L32:
            r0.b(r4)
        L35:
            boolean r8 = r0.m()
            if (r8 == 0) goto La4
            int r8 = r0.f9279n
            int r8 = r8 - r1
            java.lang.Object r8 = r0.o(r8)
            g0.n r8 = (g0.n) r8
            int r4 = r8.o
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 != 0) goto L4e
            E0.AbstractC0156f.b(r0, r8)
            goto L35
        L4e:
            if (r8 == 0) goto L35
            int r4 = r8.f14686n
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 == 0) goto La1
            r4 = r2
        L57:
            if (r8 == 0) goto L35
            boolean r5 = r8 instanceof l0.C1727r
            if (r5 == 0) goto L66
            l0.r r8 = (l0.C1727r) r8
            l0.r r8 = g(r8)
            if (r8 == 0) goto L9c
            return r8
        L66:
            int r5 = r8.f14686n
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L9c
            boolean r5 = r8 instanceof E0.AbstractC0164n
            if (r5 == 0) goto L9c
            r5 = r8
            E0.n r5 = (E0.AbstractC0164n) r5
            g0.n r5 = r5.f1690z
            r6 = 0
        L76:
            if (r5 == 0) goto L99
            int r7 = r5.f14686n
            r7 = r7 & 1024(0x400, float:1.435E-42)
            if (r7 == 0) goto L96
            int r6 = r6 + 1
            if (r6 != r1) goto L84
            r8 = r5
            goto L96
        L84:
            if (r4 != 0) goto L8d
            V.d r4 = new V.d
            g0.n[] r7 = new g0.n[r3]
            r4.<init>(r7)
        L8d:
            if (r8 == 0) goto L93
            r4.b(r8)
            r8 = r2
        L93:
            r4.b(r5)
        L96:
            g0.n r5 = r5.f14688q
            goto L76
        L99:
            if (r6 != r1) goto L9c
            goto L57
        L9c:
            g0.n r8 = E0.AbstractC0156f.f(r4)
            goto L57
        La1:
            g0.n r8 = r8.f14688q
            goto L4e
        La4:
            return r2
        La5:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            r8.<init>(r0)
            throw r8
        Lad:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.g(l0.r):l0.r");
    }

    public static final C1727r h(V.d dVar, m0.d dVar2, int i7) {
        m0.d dVarG;
        if (C1711b.a(i7, 3)) {
            dVarG = dVar2.g(dVar2.c() + 1, 0.0f);
        } else if (C1711b.a(i7, 4)) {
            dVarG = dVar2.g(-(dVar2.c() + 1), 0.0f);
        } else if (C1711b.a(i7, 5)) {
            dVarG = dVar2.g(0.0f, dVar2.b() + 1);
        } else {
            if (!C1711b.a(i7, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            dVarG = dVar2.g(0.0f, -(dVar2.b() + 1));
        }
        int i10 = dVar.f9279n;
        C1727r c1727r = null;
        if (i10 > 0) {
            Object[] objArr = dVar.l;
            int i11 = 0;
            do {
                C1727r c1727r2 = (C1727r) objArr[i11];
                if (s(c1727r2)) {
                    m0.d dVarJ = j(c1727r2);
                    if (q(i7, dVarJ, dVar2) && (!q(i7, dVarG, dVar2) || b(dVar2, dVarJ, dVarG, i7) || (!b(dVar2, dVarG, dVarJ, i7) && r(i7, dVar2, dVarJ) < r(i7, dVar2, dVarG)))) {
                        c1727r = c1727r2;
                        dVarG = dVarJ;
                    }
                }
                i11++;
            } while (i11 < i10);
        }
        return c1727r;
    }

    public static final boolean i(C1727r c1727r, int i7, InterfaceC2782k interfaceC2782k) {
        m0.d dVar;
        V.d dVar2 = new V.d(new C1727r[16]);
        f(c1727r, dVar2);
        if (dVar2.f9279n <= 1) {
            C1727r c1727r2 = (C1727r) (dVar2.l() ? null : dVar2.l[0]);
            if (c1727r2 != null) {
                return ((Boolean) interfaceC2782k.b(c1727r2)).booleanValue();
            }
            return false;
        }
        if (C1711b.a(i7, 7)) {
            i7 = 4;
        }
        if (C1711b.a(i7, 4) ? true : C1711b.a(i7, 6)) {
            m0.d dVarJ = j(c1727r);
            float f10 = dVarJ.f18883b;
            float f11 = dVarJ.f18882a;
            dVar = new m0.d(f11, f10, f11, f10);
        } else {
            if (!(C1711b.a(i7, 3) ? true : C1711b.a(i7, 5))) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            m0.d dVarJ2 = j(c1727r);
            float f12 = dVarJ2.d;
            float f13 = dVarJ2.c;
            dVar = new m0.d(f13, f12, f13, f12);
        }
        C1727r c1727rH = h(dVar2, dVar, i7);
        if (c1727rH != null) {
            return ((Boolean) interfaceC2782k.b(c1727rH)).booleanValue();
        }
        return false;
    }

    public static final m0.d j(C1727r c1727r) {
        d0 d0Var = c1727r.f14690s;
        return d0Var != null ? c0.h(d0Var).o(d0Var, false) : m0.d.f18881e;
    }

    public static final boolean k(C1727r c1727r, C0022k c0022k) {
        int iOrdinal = c1727r.I0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                C1727r c1727rN = n(c1727r);
                if (c1727rN != null) {
                    return k(c1727rN, c0022k) || m(c1727r, c1727rN, 1, c0022k);
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild");
            }
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    return c1727r.H0().f18338a ? ((Boolean) c0022k.b(c1727r)).booleanValue() : y(c1727r, c0022k);
                }
                throw new A9.k();
            }
        }
        return y(c1727r, c0022k);
    }

    public static final boolean l(int i7, C0022k c0022k, C1727r c1727r, m0.d dVar) {
        if (G(i7, c0022k, c1727r, dVar)) {
            return true;
        }
        Boolean bool = (Boolean) F(c1727r, i7, new Q(c1727r, dVar, i7, c0022k, 5));
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final boolean m(C1727r c1727r, C1727r c1727r2, int i7, C0022k c0022k) {
        if (H(c1727r, c1727r2, i7, c0022k)) {
            return true;
        }
        Boolean bool = (Boolean) F(c1727r, i7, new Q(c1727r, c1727r2, i7, c0022k, 4));
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x001e, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final l0.C1727r n(l0.C1727r r8) {
        /*
            g0.n r8 = r8.l
            boolean r0 = r8.f14695x
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            if (r0 == 0) goto L9f
            V.d r0 = new V.d
            r2 = 16
            g0.n[] r3 = new g0.n[r2]
            r0.<init>(r3)
            g0.n r3 = r8.f14688q
            if (r3 != 0) goto L1b
            E0.AbstractC0156f.b(r0, r8)
            goto L1e
        L1b:
            r0.b(r3)
        L1e:
            boolean r8 = r0.m()
            if (r8 == 0) goto L9e
            int r8 = r0.f9279n
            r3 = 1
            int r8 = r8 - r3
            java.lang.Object r8 = r0.o(r8)
            g0.n r8 = (g0.n) r8
            int r4 = r8.o
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 != 0) goto L38
            E0.AbstractC0156f.b(r0, r8)
            goto L1e
        L38:
            if (r8 == 0) goto L1e
            int r4 = r8.f14686n
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 == 0) goto L9b
            r4 = r1
        L41:
            if (r8 == 0) goto L1e
            boolean r5 = r8 instanceof l0.C1727r
            if (r5 == 0) goto L60
            l0.r r8 = (l0.C1727r) r8
            g0.n r5 = r8.l
            boolean r5 = r5.f14695x
            if (r5 == 0) goto L96
            l0.q r5 = r8.I0()
            int r5 = r5.ordinal()
            if (r5 == 0) goto L5f
            if (r5 == r3) goto L5f
            r6 = 2
            if (r5 == r6) goto L5f
            goto L96
        L5f:
            return r8
        L60:
            int r5 = r8.f14686n
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L96
            boolean r5 = r8 instanceof E0.AbstractC0164n
            if (r5 == 0) goto L96
            r5 = r8
            E0.n r5 = (E0.AbstractC0164n) r5
            g0.n r5 = r5.f1690z
            r6 = 0
        L70:
            if (r5 == 0) goto L93
            int r7 = r5.f14686n
            r7 = r7 & 1024(0x400, float:1.435E-42)
            if (r7 == 0) goto L90
            int r6 = r6 + 1
            if (r6 != r3) goto L7e
            r8 = r5
            goto L90
        L7e:
            if (r4 != 0) goto L87
            V.d r4 = new V.d
            g0.n[] r7 = new g0.n[r2]
            r4.<init>(r7)
        L87:
            if (r8 == 0) goto L8d
            r4.b(r8)
            r8 = r1
        L8d:
            r4.b(r5)
        L90:
            g0.n r5 = r5.f14688q
            goto L70
        L93:
            if (r6 != r3) goto L96
            goto L41
        L96:
            g0.n r8 = E0.AbstractC0156f.f(r4)
            goto L41
        L9b:
            g0.n r8 = r8.f14688q
            goto L38
        L9e:
            return r1
        L9f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.n(l0.r):l0.r");
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x0075, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final l0.EnumC1726q o(l0.InterfaceC1712c r10) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.o(l0.c):l0.q");
    }

    public static final void p(C1727r c1727r) {
        AbstractC0156f.q(c1727r, new C1728s(c1727r, 0));
        int iOrdinal = c1727r.I0().ordinal();
        if (iOrdinal == 1 || iOrdinal == 3) {
            c1727r.M0(EnumC1726q.l);
        }
    }

    public static final boolean q(int i7, m0.d dVar, m0.d dVar2) {
        boolean zA = C1711b.a(i7, 3);
        float f10 = dVar.f18882a;
        float f11 = dVar.c;
        if (zA) {
            float f12 = dVar2.c;
            float f13 = dVar2.f18882a;
            if ((f12 <= f11 && f13 < f11) || f13 <= f10) {
                return false;
            }
        } else if (C1711b.a(i7, 4)) {
            float f14 = dVar2.f18882a;
            float f15 = dVar2.c;
            if ((f14 >= f10 && f15 > f10) || f15 >= f11) {
                return false;
            }
        } else {
            boolean zA2 = C1711b.a(i7, 5);
            float f16 = dVar.f18883b;
            float f17 = dVar.d;
            if (zA2) {
                float f18 = dVar2.d;
                float f19 = dVar2.f18883b;
                if ((f18 <= f17 && f19 < f17) || f19 <= f16) {
                    return false;
                }
            } else {
                if (!C1711b.a(i7, 6)) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                float f20 = dVar2.f18883b;
                float f21 = dVar2.d;
                if ((f20 >= f16 && f21 > f16) || f21 >= f17) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long r(int r10, m0.d r11, m0.d r12) {
        /*
            r0 = 3
            boolean r1 = l0.C1711b.a(r10, r0)
            float r2 = r12.f18883b
            float r3 = r12.f18882a
            java.lang.String r4 = "This function should only be used for 2-D focus search"
            r5 = 6
            r6 = 5
            r7 = 4
            if (r1 == 0) goto L16
            float r1 = r11.f18882a
            float r8 = r12.c
        L14:
            float r1 = r1 - r8
            goto L36
        L16:
            boolean r1 = l0.C1711b.a(r10, r7)
            if (r1 == 0) goto L21
            float r1 = r11.c
            float r1 = r3 - r1
            goto L36
        L21:
            boolean r1 = l0.C1711b.a(r10, r6)
            if (r1 == 0) goto L2c
            float r1 = r11.f18883b
            float r8 = r12.d
            goto L14
        L2c:
            boolean r1 = l0.C1711b.a(r10, r5)
            if (r1 == 0) goto L91
            float r1 = r11.d
            float r1 = r2 - r1
        L36:
            r8 = 0
            float r1 = java.lang.Math.max(r8, r1)
            float r1 = java.lang.Math.abs(r1)
            long r8 = (long) r1
            boolean r0 = l0.C1711b.a(r10, r0)
            r1 = 1
            if (r0 == 0) goto L49
            r0 = r1
            goto L4d
        L49:
            boolean r0 = l0.C1711b.a(r10, r7)
        L4d:
            r7 = 2
            if (r0 == 0) goto L61
            float r10 = r11.b()
            float r0 = (float) r7
            float r10 = r10 / r0
            float r11 = r11.f18883b
            float r10 = r10 + r11
            float r11 = r12.b()
            float r11 = r11 / r0
            float r11 = r11 + r2
        L5f:
            float r10 = r10 - r11
            goto L7e
        L61:
            boolean r0 = l0.C1711b.a(r10, r6)
            if (r0 == 0) goto L68
            goto L6c
        L68:
            boolean r1 = l0.C1711b.a(r10, r5)
        L6c:
            if (r1 == 0) goto L8b
            float r10 = r11.c()
            float r0 = (float) r7
            float r10 = r10 / r0
            float r11 = r11.f18882a
            float r10 = r10 + r11
            float r11 = r12.c()
            float r11 = r11 / r0
            float r11 = r11 + r3
            goto L5f
        L7e:
            float r10 = java.lang.Math.abs(r10)
            long r10 = (long) r10
            r12 = 13
            long r0 = (long) r12
            long r0 = r0 * r8
            long r0 = r0 * r8
            long r10 = r10 * r10
            long r10 = r10 + r0
            return r10
        L8b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r4)
            throw r10
        L91:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r4)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.r(int, m0.d, m0.d):long");
    }

    public static final boolean s(C1727r c1727r) {
        F f10;
        d0 d0Var;
        F f11;
        d0 d0Var2 = c1727r.f14690s;
        return (d0Var2 == null || (f10 = d0Var2.f1648w) == null || !f10.W() || (d0Var = c1727r.f14690s) == null || (f11 = d0Var.f1648w) == null || !f11.V()) ? false : true;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, z7.k] */
    public static final int t(C1727r c1727r, int i7) {
        int iOrdinal = c1727r.I0().ordinal();
        int i10 = 1;
        if (iOrdinal == 0) {
            return 1;
        }
        if (iOrdinal == 1) {
            C1727r c1727rN = n(c1727r);
            if (c1727rN == null) {
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            int iT = t(c1727rN, i7);
            if (iT == 1) {
                iT = 0;
            }
            if (iT != 0) {
                return iT;
            }
            if (c1727r.f18354y) {
                return 1;
            }
            c1727r.f18354y = true;
            try {
                C1723n c1723n = (C1723n) c1727r.H0().f18346k.b(new C1711b(i7));
                if (c1723n != C1723n.f18348b) {
                    if (c1723n != C1723n.c) {
                        i10 = c1723n.a(C1717h.f18335q) ? 3 : 4;
                    }
                }
                return i10;
            } finally {
                c1727r.f18354y = false;
            }
        }
        if (iOrdinal != 2) {
            if (iOrdinal == 3) {
                return 1;
            }
            throw new A9.k();
        }
        return 2;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, z7.k] */
    public static final int u(C1727r c1727r, int i7) {
        if (!c1727r.f18355z) {
            c1727r.f18355z = true;
            try {
                C1723n c1723n = (C1723n) c1727r.H0().f18345j.b(new C1711b(i7));
                if (c1723n != C1723n.f18348b) {
                    if (c1723n != C1723n.c) {
                        return c1723n.a(C1717h.f18335q) ? 3 : 4;
                    }
                    c1727r.f18355z = false;
                    return 2;
                }
            } finally {
                c1727r.f18355z = false;
            }
        }
        return 1;
    }

    public static final int v(C1727r c1727r, int i7) {
        g0.n nVar;
        Y y10;
        int iOrdinal = c1727r.I0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                C1727r c1727rN = n(c1727r);
                if (c1727rN != null) {
                    return t(c1727rN, i7);
                }
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new A9.k();
                }
                g0.n nVar2 = c1727r.l;
                if (!nVar2.f14695x) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                g0.n nVar3 = nVar2.f14687p;
                F fT = AbstractC0156f.t(c1727r);
                loop0: while (true) {
                    nVar = null;
                    if (fT == null) {
                        break;
                    }
                    if ((((g0.n) fT.f1469H.f1603f).o & 1024) != 0) {
                        while (nVar3 != null) {
                            if ((nVar3.f14686n & 1024) != 0) {
                                g0.n nVarF = nVar3;
                                V.d dVar = null;
                                while (nVarF != null) {
                                    if (nVarF instanceof C1727r) {
                                        nVar = nVarF;
                                        break loop0;
                                    }
                                    if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                        int i10 = 0;
                                        for (g0.n nVar4 = ((AbstractC0164n) nVarF).f1690z; nVar4 != null; nVar4 = nVar4.f14688q) {
                                            if ((nVar4.f14686n & 1024) != 0) {
                                                i10++;
                                                if (i10 == 1) {
                                                    nVarF = nVar4;
                                                } else {
                                                    if (dVar == null) {
                                                        dVar = new V.d(new g0.n[16]);
                                                    }
                                                    if (nVarF != null) {
                                                        dVar.b(nVarF);
                                                        nVarF = null;
                                                    }
                                                    dVar.b(nVar4);
                                                }
                                            }
                                        }
                                        if (i10 == 1) {
                                        }
                                    }
                                    nVarF = AbstractC0156f.f(dVar);
                                }
                            }
                            nVar3 = nVar3.f14687p;
                        }
                    }
                    fT = fT.G();
                    nVar3 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
                }
                C1727r c1727r2 = (C1727r) nVar;
                if (c1727r2 == null) {
                    return 1;
                }
                int iOrdinal2 = c1727r2.I0().ordinal();
                if (iOrdinal2 == 0) {
                    return u(c1727r2, i7);
                }
                if (iOrdinal2 == 1) {
                    return v(c1727r2, i7);
                }
                if (iOrdinal2 == 2) {
                    return 2;
                }
                if (iOrdinal2 != 3) {
                    throw new A9.k();
                }
                int iV = v(c1727r2, i7);
                int i11 = iV == 1 ? 0 : iV;
                return i11 == 0 ? u(c1727r2, i7) : i11;
            }
        }
        return 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean w(l0.C1727r r10) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC1713d.w(l0.r):boolean");
    }

    public static final boolean x(C1727r c1727r, C0022k c0022k) {
        Object[] objArrCopyOf = new C1727r[16];
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        V.d dVar = new V.d(new g0.n[16]);
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 == null) {
            AbstractC0156f.b(dVar, nVar);
        } else {
            dVar.b(nVar2);
        }
        int i7 = 0;
        while (dVar.m()) {
            g0.n nVarF = (g0.n) dVar.o(dVar.f9279n - 1);
            if ((nVarF.o & 1024) == 0) {
                AbstractC0156f.b(dVar, nVarF);
            } else {
                while (true) {
                    if (nVarF == null) {
                        break;
                    }
                    if ((nVarF.f14686n & 1024) != 0) {
                        V.d dVar2 = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r2 = (C1727r) nVarF;
                                int i10 = i7 + 1;
                                if (objArrCopyOf.length < i10) {
                                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, Math.max(i10, objArrCopyOf.length * 2));
                                    A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
                                }
                                objArrCopyOf[i7] = c1727r2;
                                i7 = i10;
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i11 = 0;
                                for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                    if ((nVar3.f14686n & 1024) != 0) {
                                        i11++;
                                        if (i11 == 1) {
                                            nVarF = nVar3;
                                        } else {
                                            if (dVar2 == null) {
                                                dVar2 = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar2.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar2.b(nVar3);
                                        }
                                    }
                                }
                                if (i11 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar2);
                        }
                    } else {
                        nVarF = nVarF.f14688q;
                    }
                }
            }
        }
        C1729t c1729t = C1729t.l;
        A7.m.f(objArrCopyOf, "<this>");
        Arrays.sort(objArrCopyOf, 0, i7, c1729t);
        if (i7 > 0) {
            int i12 = i7 - 1;
            do {
                C1727r c1727r3 = (C1727r) objArrCopyOf[i12];
                if (s(c1727r3) && a(c1727r3, c0022k)) {
                    return true;
                }
                i12--;
            } while (i12 >= 0);
        }
        return false;
    }

    public static final boolean y(C1727r c1727r, C0022k c0022k) {
        Object[] objArrCopyOf = new C1727r[16];
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        V.d dVar = new V.d(new g0.n[16]);
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 == null) {
            AbstractC0156f.b(dVar, nVar);
        } else {
            dVar.b(nVar2);
        }
        int i7 = 0;
        while (dVar.m()) {
            g0.n nVarF = (g0.n) dVar.o(dVar.f9279n - 1);
            if ((nVarF.o & 1024) == 0) {
                AbstractC0156f.b(dVar, nVarF);
            } else {
                while (true) {
                    if (nVarF == null) {
                        break;
                    }
                    if ((nVarF.f14686n & 1024) != 0) {
                        V.d dVar2 = null;
                        while (nVarF != null) {
                            if (nVarF instanceof C1727r) {
                                C1727r c1727r2 = (C1727r) nVarF;
                                int i10 = i7 + 1;
                                if (objArrCopyOf.length < i10) {
                                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, Math.max(i10, objArrCopyOf.length * 2));
                                    A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
                                }
                                objArrCopyOf[i7] = c1727r2;
                                i7 = i10;
                            } else if ((nVarF.f14686n & 1024) != 0 && (nVarF instanceof AbstractC0164n)) {
                                int i11 = 0;
                                for (g0.n nVar3 = ((AbstractC0164n) nVarF).f1690z; nVar3 != null; nVar3 = nVar3.f14688q) {
                                    if ((nVar3.f14686n & 1024) != 0) {
                                        i11++;
                                        if (i11 == 1) {
                                            nVarF = nVar3;
                                        } else {
                                            if (dVar2 == null) {
                                                dVar2 = new V.d(new g0.n[16]);
                                            }
                                            if (nVarF != null) {
                                                dVar2.b(nVarF);
                                                nVarF = null;
                                            }
                                            dVar2.b(nVar3);
                                        }
                                    }
                                }
                                if (i11 == 1) {
                                }
                            }
                            nVarF = AbstractC0156f.f(dVar2);
                        }
                    } else {
                        nVarF = nVarF.f14688q;
                    }
                }
            }
        }
        C1729t c1729t = C1729t.l;
        A7.m.f(objArrCopyOf, "<this>");
        Arrays.sort(objArrCopyOf, 0, i7, c1729t);
        if (i7 <= 0) {
            return false;
        }
        int i12 = 0;
        do {
            C1727r c1727r3 = (C1727r) objArrCopyOf[i12];
            if (s(c1727r3) && k(c1727r3, c0022k)) {
                return true;
            }
            i12++;
        } while (i12 < i7);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [V.d] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v5 */
    public static final void z(C1727r c1727r) {
        Y y10;
        g0.n nVar = c1727r.l;
        if (!nVar.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        F fT = AbstractC0156f.t(c1727r);
        g0.n nVar2 = nVar;
        while (fT != null) {
            if ((((g0.n) fT.f1469H.f1603f).o & 5120) != 0) {
                while (nVar2 != null) {
                    int i7 = nVar2.f14686n;
                    if ((i7 & 5120) != 0) {
                        if (nVar2 != nVar && (i7 & 1024) != 0) {
                            return;
                        }
                        if ((i7 & 4096) != 0) {
                            ?? F9 = nVar2;
                            ?? dVar = 0;
                            while (F9 != 0) {
                                if (F9 instanceof InterfaceC1712c) {
                                    InterfaceC1712c interfaceC1712c = (InterfaceC1712c) F9;
                                    interfaceC1712c.D(o(interfaceC1712c));
                                } else if ((F9.f14686n & 4096) != 0 && (F9 instanceof AbstractC0164n)) {
                                    g0.n nVar3 = ((AbstractC0164n) F9).f1690z;
                                    int i10 = 0;
                                    F9 = F9;
                                    dVar = dVar;
                                    while (nVar3 != null) {
                                        if ((nVar3.f14686n & 4096) != 0) {
                                            i10++;
                                            dVar = dVar;
                                            if (i10 == 1) {
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
                                    if (i10 == 1) {
                                    }
                                }
                                F9 = AbstractC0156f.f(dVar);
                            }
                        }
                    }
                    nVar2 = nVar2.f14687p;
                }
            }
            fT = fT.G();
            nVar2 = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
        }
    }
}
