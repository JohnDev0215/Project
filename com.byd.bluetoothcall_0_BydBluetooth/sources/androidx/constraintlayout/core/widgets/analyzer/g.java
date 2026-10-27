package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.b;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: Direct.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b.a f856a = new b.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f857b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f858c = 0;

    private static boolean a(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviourC = constraintWidget.C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourV = constraintWidget.V();
        androidx.constraintlayout.core.widgets.d dVar = constraintWidget.M() != null ? (androidx.constraintlayout.core.widgets.d) constraintWidget.M() : null;
        if (dVar != null) {
            dVar.C();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (dVar != null) {
            dVar.V();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        boolean z = dimensionBehaviourC == dimensionBehaviour5 || constraintWidget.p0() || dimensionBehaviourC == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviourC == (dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.x == 0 && constraintWidget.g0 == 0.0f && constraintWidget.c0(0)) || (dimensionBehaviourC == dimensionBehaviour2 && constraintWidget.x == 1 && constraintWidget.f0(0, constraintWidget.Y()));
        boolean z2 = dimensionBehaviourV == dimensionBehaviour5 || constraintWidget.q0() || dimensionBehaviourV == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviourV == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.y == 0 && constraintWidget.g0 == 0.0f && constraintWidget.c0(1)) || (dimensionBehaviourV == dimensionBehaviour && constraintWidget.y == 1 && constraintWidget.f0(1, constraintWidget.z()));
        if (constraintWidget.g0 <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }

    private static void b(int i, ConstraintWidget constraintWidget, b.InterfaceC0016b interfaceC0016b, boolean z) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        if (constraintWidget.i0()) {
            return;
        }
        boolean z2 = true;
        f857b++;
        if (!(constraintWidget instanceof androidx.constraintlayout.core.widgets.d) && constraintWidget.o0()) {
            int i2 = i + 1;
            if (a(i2, constraintWidget)) {
                androidx.constraintlayout.core.widgets.d.V1(i2, constraintWidget, interfaceC0016b, new b.a(), b.a.f846a);
            }
        }
        ConstraintAnchor constraintAnchorQ = constraintWidget.q(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor constraintAnchorQ2 = constraintWidget.q(ConstraintAnchor.Type.RIGHT);
        int iE = constraintAnchorQ.e();
        int iE2 = constraintAnchorQ2.e();
        if (constraintAnchorQ.d() != null && constraintAnchorQ.n()) {
            Iterator<ConstraintAnchor> it = constraintAnchorQ.d().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.f818d;
                int i3 = i + 1;
                boolean zA = a(i3, constraintWidget2);
                if (constraintWidget2.o0() && zA) {
                    androidx.constraintlayout.core.widgets.d.V1(i3, constraintWidget2, interfaceC0016b, new b.a(), b.a.f846a);
                }
                boolean z3 = ((next == constraintWidget2.R && (constraintAnchor4 = constraintWidget2.T.f) != null && constraintAnchor4.n()) || (next == constraintWidget2.T && (constraintAnchor3 = constraintWidget2.R.f) != null && constraintAnchor3.n())) ? z2 : false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviourC = constraintWidget2.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourC != dimensionBehaviour || zA) {
                    if (!constraintWidget2.o0()) {
                        ConstraintAnchor constraintAnchor5 = constraintWidget2.R;
                        if (next == constraintAnchor5 && constraintWidget2.T.f == null) {
                            int iF = constraintAnchor5.f() + iE;
                            constraintWidget2.I0(iF, constraintWidget2.Y() + iF);
                            b(i3, constraintWidget2, interfaceC0016b, z);
                        } else {
                            ConstraintAnchor constraintAnchor6 = constraintWidget2.T;
                            if (next == constraintAnchor6 && constraintAnchor5.f == null) {
                                int iF2 = iE - constraintAnchor6.f();
                                constraintWidget2.I0(iF2 - constraintWidget2.Y(), iF2);
                                b(i3, constraintWidget2, interfaceC0016b, z);
                            } else if (z3 && !constraintWidget2.k0()) {
                                d(i3, interfaceC0016b, constraintWidget2, z);
                            }
                        }
                    }
                } else if (constraintWidget2.C() == dimensionBehaviour && constraintWidget2.B >= 0 && constraintWidget2.A >= 0 && ((constraintWidget2.X() == 8 || (constraintWidget2.x == 0 && constraintWidget2.x() == 0.0f)) && !constraintWidget2.k0() && !constraintWidget2.n0() && z3 && !constraintWidget2.k0())) {
                    e(i3, constraintWidget, interfaceC0016b, constraintWidget2, z);
                }
                z2 = true;
            }
        }
        if (constraintWidget instanceof androidx.constraintlayout.core.widgets.f) {
            return;
        }
        if (constraintAnchorQ2.d() != null && constraintAnchorQ2.n()) {
            Iterator<ConstraintAnchor> it2 = constraintAnchorQ2.d().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.f818d;
                int i4 = i + 1;
                boolean zA2 = a(i4, constraintWidget3);
                if (constraintWidget3.o0() && zA2) {
                    androidx.constraintlayout.core.widgets.d.V1(i4, constraintWidget3, interfaceC0016b, new b.a(), b.a.f846a);
                }
                boolean z4 = (next2 == constraintWidget3.R && (constraintAnchor2 = constraintWidget3.T.f) != null && constraintAnchor2.n()) || (next2 == constraintWidget3.T && (constraintAnchor = constraintWidget3.R.f) != null && constraintAnchor.n());
                ConstraintWidget.DimensionBehaviour dimensionBehaviourC2 = constraintWidget3.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourC2 != dimensionBehaviour2 || zA2) {
                    if (!constraintWidget3.o0()) {
                        ConstraintAnchor constraintAnchor7 = constraintWidget3.R;
                        if (next2 == constraintAnchor7 && constraintWidget3.T.f == null) {
                            int iF3 = constraintAnchor7.f() + iE2;
                            constraintWidget3.I0(iF3, constraintWidget3.Y() + iF3);
                            b(i4, constraintWidget3, interfaceC0016b, z);
                        } else {
                            ConstraintAnchor constraintAnchor8 = constraintWidget3.T;
                            if (next2 == constraintAnchor8 && constraintAnchor7.f == null) {
                                int iF4 = iE2 - constraintAnchor8.f();
                                constraintWidget3.I0(iF4 - constraintWidget3.Y(), iF4);
                                b(i4, constraintWidget3, interfaceC0016b, z);
                            } else if (z4 && !constraintWidget3.k0()) {
                                d(i4, interfaceC0016b, constraintWidget3, z);
                            }
                        }
                    }
                } else if (constraintWidget3.C() == dimensionBehaviour2 && constraintWidget3.B >= 0 && constraintWidget3.A >= 0 && (constraintWidget3.X() == 8 || (constraintWidget3.x == 0 && constraintWidget3.x() == 0.0f))) {
                    if (!constraintWidget3.k0() && !constraintWidget3.n0() && z4 && !constraintWidget3.k0()) {
                        e(i4, constraintWidget, interfaceC0016b, constraintWidget3, z);
                    }
                }
            }
        }
        constraintWidget.s0();
    }

    private static void c(int i, androidx.constraintlayout.core.widgets.a aVar, b.InterfaceC0016b interfaceC0016b, int i2, boolean z) {
        if (aVar.w1()) {
            if (i2 == 0) {
                b(i + 1, aVar, interfaceC0016b, z);
            } else {
                i(i + 1, aVar, interfaceC0016b);
            }
        }
    }

    private static void d(int i, b.InterfaceC0016b interfaceC0016b, ConstraintWidget constraintWidget, boolean z) {
        float fA = constraintWidget.A();
        int iE = constraintWidget.R.f.e();
        int iE2 = constraintWidget.T.f.e();
        int iF = constraintWidget.R.f() + iE;
        int iF2 = iE2 - constraintWidget.T.f();
        if (iE == iE2) {
            fA = 0.5f;
        } else {
            iE = iF;
            iE2 = iF2;
        }
        int iY = constraintWidget.Y();
        int i2 = (iE2 - iE) - iY;
        if (iE > iE2) {
            i2 = (iE - iE2) - iY;
        }
        int i3 = ((int) (i2 > 0 ? (fA * i2) + 0.5f : fA * i2)) + iE;
        int i4 = i3 + iY;
        if (iE > iE2) {
            i4 = i3 - iY;
        }
        constraintWidget.I0(i3, i4);
        b(i + 1, constraintWidget, interfaceC0016b, z);
    }

    private static void e(int i, ConstraintWidget constraintWidget, b.InterfaceC0016b interfaceC0016b, ConstraintWidget constraintWidget2, boolean z) {
        float fA = constraintWidget2.A();
        int iE = constraintWidget2.R.f.e() + constraintWidget2.R.f();
        int iE2 = constraintWidget2.T.f.e() - constraintWidget2.T.f();
        if (iE2 >= iE) {
            int iY = constraintWidget2.Y();
            if (constraintWidget2.X() != 8) {
                int i2 = constraintWidget2.x;
                if (i2 == 2) {
                    iY = (int) (constraintWidget2.A() * 0.5f * (constraintWidget instanceof androidx.constraintlayout.core.widgets.d ? constraintWidget.Y() : constraintWidget.M().Y()));
                } else if (i2 == 0) {
                    iY = iE2 - iE;
                }
                iY = Math.max(constraintWidget2.A, iY);
                int i3 = constraintWidget2.B;
                if (i3 > 0) {
                    iY = Math.min(i3, iY);
                }
            }
            int i4 = iE + ((int) ((fA * ((iE2 - iE) - iY)) + 0.5f));
            constraintWidget2.I0(i4, iY + i4);
            b(i + 1, constraintWidget2, interfaceC0016b, z);
        }
    }

    private static void f(int i, b.InterfaceC0016b interfaceC0016b, ConstraintWidget constraintWidget) {
        float fT = constraintWidget.T();
        int iE = constraintWidget.S.f.e();
        int iE2 = constraintWidget.U.f.e();
        int iF = constraintWidget.S.f() + iE;
        int iF2 = iE2 - constraintWidget.U.f();
        if (iE == iE2) {
            fT = 0.5f;
        } else {
            iE = iF;
            iE2 = iF2;
        }
        int iZ = constraintWidget.z();
        int i2 = (iE2 - iE) - iZ;
        if (iE > iE2) {
            i2 = (iE - iE2) - iZ;
        }
        int i3 = (int) (i2 > 0 ? (fT * i2) + 0.5f : fT * i2);
        int i4 = iE + i3;
        int i5 = i4 + iZ;
        if (iE > iE2) {
            i4 = iE - i3;
            i5 = i4 - iZ;
        }
        constraintWidget.L0(i4, i5);
        i(i + 1, constraintWidget, interfaceC0016b);
    }

    private static void g(int i, ConstraintWidget constraintWidget, b.InterfaceC0016b interfaceC0016b, ConstraintWidget constraintWidget2) {
        float fT = constraintWidget2.T();
        int iE = constraintWidget2.S.f.e() + constraintWidget2.S.f();
        int iE2 = constraintWidget2.U.f.e() - constraintWidget2.U.f();
        if (iE2 >= iE) {
            int iZ = constraintWidget2.z();
            if (constraintWidget2.X() != 8) {
                int i2 = constraintWidget2.y;
                if (i2 == 2) {
                    iZ = (int) (fT * 0.5f * (constraintWidget instanceof androidx.constraintlayout.core.widgets.d ? constraintWidget.z() : constraintWidget.M().z()));
                } else if (i2 == 0) {
                    iZ = iE2 - iE;
                }
                iZ = Math.max(constraintWidget2.D, iZ);
                int i3 = constraintWidget2.E;
                if (i3 > 0) {
                    iZ = Math.min(i3, iZ);
                }
            }
            int i4 = iE + ((int) ((fT * ((iE2 - iE) - iZ)) + 0.5f));
            constraintWidget2.L0(i4, iZ + i4);
            i(i + 1, constraintWidget2, interfaceC0016b);
        }
    }

    public static void h(androidx.constraintlayout.core.widgets.d dVar, b.InterfaceC0016b interfaceC0016b) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviourC = dVar.C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviourV = dVar.V();
        f857b = 0;
        f858c = 0;
        dVar.y0();
        ArrayList<ConstraintWidget> arrayListU1 = dVar.u1();
        int size = arrayListU1.size();
        for (int i = 0; i < size; i++) {
            arrayListU1.get(i).y0();
        }
        boolean zS1 = dVar.S1();
        if (dimensionBehaviourC == ConstraintWidget.DimensionBehaviour.FIXED) {
            dVar.I0(0, dVar.Y());
        } else {
            dVar.J0(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = arrayListU1.get(i2);
            if (constraintWidget instanceof androidx.constraintlayout.core.widgets.f) {
                androidx.constraintlayout.core.widgets.f fVar = (androidx.constraintlayout.core.widgets.f) constraintWidget;
                if (fVar.v1() == 1) {
                    if (fVar.w1() != -1) {
                        fVar.z1(fVar.w1());
                    } else if (fVar.x1() != -1 && dVar.p0()) {
                        fVar.z1(dVar.Y() - fVar.x1());
                    } else if (dVar.p0()) {
                        fVar.z1((int) ((fVar.y1() * dVar.Y()) + 0.5f));
                    }
                    z = true;
                }
            } else if ((constraintWidget instanceof androidx.constraintlayout.core.widgets.a) && ((androidx.constraintlayout.core.widgets.a) constraintWidget).A1() == 0) {
                z2 = true;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = arrayListU1.get(i3);
                if (constraintWidget2 instanceof androidx.constraintlayout.core.widgets.f) {
                    androidx.constraintlayout.core.widgets.f fVar2 = (androidx.constraintlayout.core.widgets.f) constraintWidget2;
                    if (fVar2.v1() == 1) {
                        b(0, fVar2, interfaceC0016b, zS1);
                    }
                }
            }
        }
        b(0, dVar, interfaceC0016b, zS1);
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget3 = arrayListU1.get(i4);
                if (constraintWidget3 instanceof androidx.constraintlayout.core.widgets.a) {
                    androidx.constraintlayout.core.widgets.a aVar = (androidx.constraintlayout.core.widgets.a) constraintWidget3;
                    if (aVar.A1() == 0) {
                        c(0, aVar, interfaceC0016b, 0, zS1);
                    }
                }
            }
        }
        if (dimensionBehaviourV == ConstraintWidget.DimensionBehaviour.FIXED) {
            dVar.L0(0, dVar.z());
        } else {
            dVar.K0(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = arrayListU1.get(i5);
            if (constraintWidget4 instanceof androidx.constraintlayout.core.widgets.f) {
                androidx.constraintlayout.core.widgets.f fVar3 = (androidx.constraintlayout.core.widgets.f) constraintWidget4;
                if (fVar3.v1() == 0) {
                    if (fVar3.w1() != -1) {
                        fVar3.z1(fVar3.w1());
                    } else if (fVar3.x1() != -1 && dVar.q0()) {
                        fVar3.z1(dVar.z() - fVar3.x1());
                    } else if (dVar.q0()) {
                        fVar3.z1((int) ((fVar3.y1() * dVar.z()) + 0.5f));
                    }
                    z3 = true;
                }
            } else if ((constraintWidget4 instanceof androidx.constraintlayout.core.widgets.a) && ((androidx.constraintlayout.core.widgets.a) constraintWidget4).A1() == 1) {
                z4 = true;
            }
        }
        if (z3) {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget5 = arrayListU1.get(i6);
                if (constraintWidget5 instanceof androidx.constraintlayout.core.widgets.f) {
                    androidx.constraintlayout.core.widgets.f fVar4 = (androidx.constraintlayout.core.widgets.f) constraintWidget5;
                    if (fVar4.v1() == 0) {
                        i(1, fVar4, interfaceC0016b);
                    }
                }
            }
        }
        i(0, dVar, interfaceC0016b);
        if (z4) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget6 = arrayListU1.get(i7);
                if (constraintWidget6 instanceof androidx.constraintlayout.core.widgets.a) {
                    androidx.constraintlayout.core.widgets.a aVar2 = (androidx.constraintlayout.core.widgets.a) constraintWidget6;
                    if (aVar2.A1() == 1) {
                        c(0, aVar2, interfaceC0016b, 1, zS1);
                    }
                }
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget7 = arrayListU1.get(i8);
            if (constraintWidget7.o0() && a(0, constraintWidget7)) {
                androidx.constraintlayout.core.widgets.d.V1(0, constraintWidget7, interfaceC0016b, f856a, b.a.f846a);
                if (!(constraintWidget7 instanceof androidx.constraintlayout.core.widgets.f)) {
                    b(0, constraintWidget7, interfaceC0016b, zS1);
                    i(0, constraintWidget7, interfaceC0016b);
                } else if (((androidx.constraintlayout.core.widgets.f) constraintWidget7).v1() == 0) {
                    i(0, constraintWidget7, interfaceC0016b);
                } else {
                    b(0, constraintWidget7, interfaceC0016b, zS1);
                }
            }
        }
    }

    private static void i(int i, ConstraintWidget constraintWidget, b.InterfaceC0016b interfaceC0016b) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        if (constraintWidget.r0()) {
            return;
        }
        f858c++;
        if (!(constraintWidget instanceof androidx.constraintlayout.core.widgets.d) && constraintWidget.o0()) {
            int i2 = i + 1;
            if (a(i2, constraintWidget)) {
                androidx.constraintlayout.core.widgets.d.V1(i2, constraintWidget, interfaceC0016b, new b.a(), b.a.f846a);
            }
        }
        ConstraintAnchor constraintAnchorQ = constraintWidget.q(ConstraintAnchor.Type.TOP);
        ConstraintAnchor constraintAnchorQ2 = constraintWidget.q(ConstraintAnchor.Type.BOTTOM);
        int iE = constraintAnchorQ.e();
        int iE2 = constraintAnchorQ2.e();
        if (constraintAnchorQ.d() != null && constraintAnchorQ.n()) {
            Iterator<ConstraintAnchor> it = constraintAnchorQ.d().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.f818d;
                int i3 = i + 1;
                boolean zA = a(i3, constraintWidget2);
                if (constraintWidget2.o0() && zA) {
                    androidx.constraintlayout.core.widgets.d.V1(i3, constraintWidget2, interfaceC0016b, new b.a(), b.a.f846a);
                }
                boolean z = (next == constraintWidget2.S && (constraintAnchor4 = constraintWidget2.U.f) != null && constraintAnchor4.n()) || (next == constraintWidget2.U && (constraintAnchor3 = constraintWidget2.S.f) != null && constraintAnchor3.n());
                ConstraintWidget.DimensionBehaviour dimensionBehaviourV = constraintWidget2.V();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourV != dimensionBehaviour || zA) {
                    if (!constraintWidget2.o0()) {
                        ConstraintAnchor constraintAnchor5 = constraintWidget2.S;
                        if (next == constraintAnchor5 && constraintWidget2.U.f == null) {
                            int iF = constraintAnchor5.f() + iE;
                            constraintWidget2.L0(iF, constraintWidget2.z() + iF);
                            i(i3, constraintWidget2, interfaceC0016b);
                        } else {
                            ConstraintAnchor constraintAnchor6 = constraintWidget2.U;
                            if (next == constraintAnchor6 && constraintAnchor5.f == null) {
                                int iF2 = iE - constraintAnchor6.f();
                                constraintWidget2.L0(iF2 - constraintWidget2.z(), iF2);
                                i(i3, constraintWidget2, interfaceC0016b);
                            } else if (z && !constraintWidget2.m0()) {
                                f(i3, interfaceC0016b, constraintWidget2);
                            }
                        }
                    }
                } else if (constraintWidget2.V() == dimensionBehaviour && constraintWidget2.E >= 0 && constraintWidget2.D >= 0 && (constraintWidget2.X() == 8 || (constraintWidget2.y == 0 && constraintWidget2.x() == 0.0f))) {
                    if (!constraintWidget2.m0() && !constraintWidget2.n0() && z && !constraintWidget2.m0()) {
                        g(i3, constraintWidget, interfaceC0016b, constraintWidget2);
                    }
                }
            }
        }
        if (constraintWidget instanceof androidx.constraintlayout.core.widgets.f) {
            return;
        }
        if (constraintAnchorQ2.d() != null && constraintAnchorQ2.n()) {
            Iterator<ConstraintAnchor> it2 = constraintAnchorQ2.d().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.f818d;
                int i4 = i + 1;
                boolean zA2 = a(i4, constraintWidget3);
                if (constraintWidget3.o0() && zA2) {
                    androidx.constraintlayout.core.widgets.d.V1(i4, constraintWidget3, interfaceC0016b, new b.a(), b.a.f846a);
                }
                boolean z2 = (next2 == constraintWidget3.S && (constraintAnchor2 = constraintWidget3.U.f) != null && constraintAnchor2.n()) || (next2 == constraintWidget3.U && (constraintAnchor = constraintWidget3.S.f) != null && constraintAnchor.n());
                ConstraintWidget.DimensionBehaviour dimensionBehaviourV2 = constraintWidget3.V();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviourV2 != dimensionBehaviour2 || zA2) {
                    if (!constraintWidget3.o0()) {
                        ConstraintAnchor constraintAnchor7 = constraintWidget3.S;
                        if (next2 == constraintAnchor7 && constraintWidget3.U.f == null) {
                            int iF3 = constraintAnchor7.f() + iE2;
                            constraintWidget3.L0(iF3, constraintWidget3.z() + iF3);
                            i(i4, constraintWidget3, interfaceC0016b);
                        } else {
                            ConstraintAnchor constraintAnchor8 = constraintWidget3.U;
                            if (next2 == constraintAnchor8 && constraintAnchor7.f == null) {
                                int iF4 = iE2 - constraintAnchor8.f();
                                constraintWidget3.L0(iF4 - constraintWidget3.z(), iF4);
                                i(i4, constraintWidget3, interfaceC0016b);
                            } else if (z2 && !constraintWidget3.m0()) {
                                f(i4, interfaceC0016b, constraintWidget3);
                            }
                        }
                    }
                } else if (constraintWidget3.V() == dimensionBehaviour2 && constraintWidget3.E >= 0 && constraintWidget3.D >= 0 && (constraintWidget3.X() == 8 || (constraintWidget3.y == 0 && constraintWidget3.x() == 0.0f))) {
                    if (!constraintWidget3.m0() && !constraintWidget3.n0() && z2 && !constraintWidget3.m0()) {
                        g(i4, constraintWidget, interfaceC0016b, constraintWidget3);
                    }
                }
            }
        }
        ConstraintAnchor constraintAnchorQ3 = constraintWidget.q(ConstraintAnchor.Type.BASELINE);
        if (constraintAnchorQ3.d() != null && constraintAnchorQ3.n()) {
            int iE3 = constraintAnchorQ3.e();
            for (ConstraintAnchor constraintAnchor9 : constraintAnchorQ3.d()) {
                ConstraintWidget constraintWidget4 = constraintAnchor9.f818d;
                int i5 = i + 1;
                boolean zA3 = a(i5, constraintWidget4);
                if (constraintWidget4.o0() && zA3) {
                    androidx.constraintlayout.core.widgets.d.V1(i5, constraintWidget4, interfaceC0016b, new b.a(), b.a.f846a);
                }
                if (constraintWidget4.V() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || zA3) {
                    if (!constraintWidget4.o0() && constraintAnchor9 == constraintWidget4.V) {
                        constraintWidget4.H0(constraintAnchor9.f() + iE3);
                        i(i5, constraintWidget4, interfaceC0016b);
                    }
                }
            }
        }
        constraintWidget.t0();
    }
}
