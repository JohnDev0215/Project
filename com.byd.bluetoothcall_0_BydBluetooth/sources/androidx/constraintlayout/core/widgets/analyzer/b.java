package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<ConstraintWidget> f843a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f844b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private androidx.constraintlayout.core.widgets.d f845c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static int f846a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static int f847b = 1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static int f848c = 2;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f849d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ConstraintWidget.DimensionBehaviour f850e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public boolean k;
        public boolean l;
        public int m;
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.core.widgets.analyzer.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0016b {
        void a();

        void b(ConstraintWidget constraintWidget, a aVar);
    }

    public b(androidx.constraintlayout.core.widgets.d dVar) {
        this.f845c = dVar;
    }

    private boolean a(InterfaceC0016b interfaceC0016b, ConstraintWidget constraintWidget, int i) {
        this.f844b.f849d = constraintWidget.C();
        this.f844b.f850e = constraintWidget.V();
        this.f844b.f = constraintWidget.Y();
        this.f844b.g = constraintWidget.z();
        a aVar = this.f844b;
        aVar.l = false;
        aVar.m = i;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = aVar.f849d;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z = dimensionBehaviour == dimensionBehaviour2;
        boolean z2 = aVar.f850e == dimensionBehaviour2;
        boolean z3 = z && constraintWidget.g0 > 0.0f;
        boolean z4 = z2 && constraintWidget.g0 > 0.0f;
        if (z3 && constraintWidget.z[0] == 4) {
            aVar.f849d = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z4 && constraintWidget.z[1] == 4) {
            aVar.f850e = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        interfaceC0016b.b(constraintWidget, aVar);
        constraintWidget.n1(this.f844b.h);
        constraintWidget.O0(this.f844b.i);
        constraintWidget.N0(this.f844b.k);
        constraintWidget.D0(this.f844b.j);
        a aVar2 = this.f844b;
        aVar2.m = a.f846a;
        return aVar2.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0098 A[PHI: r10
  0x0098: PHI (r10v2 boolean) = (r10v1 boolean), (r10v1 boolean), (r10v1 boolean), (r10v4 boolean), (r10v4 boolean) binds: [B:32:0x0062, B:34:0x0068, B:36:0x006c, B:54:0x0095, B:52:0x008e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00a5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(androidx.constraintlayout.core.widgets.d r13) {
        /*
            r12 = this;
            java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r0 = r13.U0
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.W1(r1)
            androidx.constraintlayout.core.widgets.analyzer.b$b r2 = r13.L1()
            r3 = 0
            r4 = r3
        L12:
            if (r4 >= r0) goto Lab
            java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r5 = r13.U0
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.f
            if (r6 == 0) goto L22
            goto La5
        L22:
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.a
            if (r6 == 0) goto L28
            goto La5
        L28:
            boolean r6 = r5.n0()
            if (r6 == 0) goto L30
            goto La5
        L30:
            if (r1 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.k r6 = r5.f
            if (r6 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.m r7 = r5.g
            if (r7 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.f r6 = r6.f840e
            boolean r6 = r6.j
            if (r6 == 0) goto L48
            androidx.constraintlayout.core.widgets.analyzer.f r6 = r7.f840e
            boolean r6 = r6.j
            if (r6 == 0) goto L48
            goto La5
        L48:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.w(r3)
            r7 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.w(r7)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L61
            int r10 = r5.x
            if (r10 == r7) goto L61
            if (r8 != r9) goto L61
            int r10 = r5.y
            if (r10 == r7) goto L61
            r10 = r7
            goto L62
        L61:
            r10 = r3
        L62:
            if (r10 != 0) goto L98
            boolean r11 = r13.W1(r7)
            if (r11 == 0) goto L98
            boolean r11 = r5 instanceof androidx.constraintlayout.core.widgets.k
            if (r11 != 0) goto L98
            if (r6 != r9) goto L7d
            int r11 = r5.x
            if (r11 != 0) goto L7d
            if (r8 == r9) goto L7d
            boolean r11 = r5.k0()
            if (r11 != 0) goto L7d
            r10 = r7
        L7d:
            if (r8 != r9) goto L8c
            int r11 = r5.y
            if (r11 != 0) goto L8c
            if (r6 == r9) goto L8c
            boolean r11 = r5.k0()
            if (r11 != 0) goto L8c
            r10 = r7
        L8c:
            if (r6 == r9) goto L90
            if (r8 != r9) goto L98
        L90:
            float r6 = r5.g0
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L98
            goto L99
        L98:
            r7 = r10
        L99:
            if (r7 == 0) goto L9c
            goto La5
        L9c:
            int r6 = androidx.constraintlayout.core.widgets.analyzer.b.a.f846a
            r12.a(r2, r5, r6)
            androidx.constraintlayout.core.e r5 = r13.a1
            if (r5 != 0) goto La9
        La5:
            int r4 = r4 + 1
            goto L12
        La9:
            r13 = 0
            throw r13
        Lab:
            r2.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.b.b(androidx.constraintlayout.core.widgets.d):void");
    }

    private void c(androidx.constraintlayout.core.widgets.d dVar, String str, int i, int i2, int i3) {
        int iK = dVar.K();
        int iJ = dVar.J();
        dVar.d1(0);
        dVar.c1(0);
        dVar.n1(i2);
        dVar.O0(i3);
        dVar.d1(iK);
        dVar.c1(iJ);
        this.f845c.a2(i);
        this.f845c.v1();
    }

    public long d(androidx.constraintlayout.core.widgets.d dVar, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        boolean zK1;
        int i10;
        int i11;
        boolean z;
        int i12;
        int i13;
        int i14;
        boolean z2;
        b bVar = this;
        InterfaceC0016b interfaceC0016bL1 = dVar.L1();
        int size = dVar.U0.size();
        int iY = dVar.Y();
        int iZ = dVar.z();
        boolean zB = androidx.constraintlayout.core.widgets.i.b(i, 128);
        boolean z3 = zB || androidx.constraintlayout.core.widgets.i.b(i, 64);
        if (z3) {
            for (int i15 = 0; i15 < size; i15++) {
                ConstraintWidget constraintWidget = dVar.U0.get(i15);
                ConstraintWidget.DimensionBehaviour dimensionBehaviourC = constraintWidget.C();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z4 = (dimensionBehaviourC == dimensionBehaviour) && (constraintWidget.V() == dimensionBehaviour) && constraintWidget.x() > 0.0f;
                if ((constraintWidget.k0() && z4) || ((constraintWidget.m0() && z4) || (constraintWidget instanceof androidx.constraintlayout.core.widgets.k) || constraintWidget.k0() || constraintWidget.m0())) {
                    z3 = false;
                    break;
                }
            }
        }
        if (z3 && androidx.constraintlayout.core.d.g != null) {
            throw null;
        }
        boolean z5 = z3 & ((i4 == 1073741824 && i6 == 1073741824) || zB);
        int i16 = 2;
        if (z5) {
            int iMin = Math.min(dVar.I(), i5);
            int iMin2 = Math.min(dVar.H(), i7);
            if (i4 == 1073741824 && dVar.Y() != iMin) {
                dVar.n1(iMin);
                dVar.P1();
            }
            if (i6 == 1073741824 && dVar.z() != iMin2) {
                dVar.O0(iMin2);
                dVar.P1();
            }
            if (i4 == 1073741824 && i6 == 1073741824) {
                zK1 = dVar.I1(zB);
                i10 = 2;
            } else {
                boolean zJ1 = dVar.J1(zB);
                if (i4 == 1073741824) {
                    zJ1 &= dVar.K1(zB, 0);
                    i10 = 1;
                } else {
                    i10 = 0;
                }
                if (i6 == 1073741824) {
                    zK1 = dVar.K1(zB, 1) & zJ1;
                    i10++;
                } else {
                    zK1 = zJ1;
                }
            }
            if (zK1) {
                dVar.s1(i4 == 1073741824, i6 == 1073741824);
            }
        } else {
            zK1 = false;
            i10 = 0;
        }
        if (zK1 && i10 == 2) {
            return 0L;
        }
        int iM1 = dVar.M1();
        if (size > 0) {
            b(dVar);
        }
        e(dVar);
        int size2 = bVar.f843a.size();
        if (size > 0) {
            c(dVar, "First pass", 0, iY, iZ);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour dimensionBehaviourC2 = dVar.C();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z6 = dimensionBehaviourC2 == dimensionBehaviour2;
            boolean z7 = dVar.V() == dimensionBehaviour2;
            int iMax = Math.max(dVar.Y(), bVar.f845c.K());
            int iMax2 = Math.max(dVar.z(), bVar.f845c.J());
            int i17 = 0;
            boolean zI1 = false;
            while (i17 < size2) {
                ConstraintWidget constraintWidget2 = bVar.f843a.get(i17);
                if (constraintWidget2 instanceof androidx.constraintlayout.core.widgets.k) {
                    int iY2 = constraintWidget2.Y();
                    i13 = iM1;
                    int iZ2 = constraintWidget2.z();
                    i14 = iZ;
                    boolean zA = bVar.a(interfaceC0016bL1, constraintWidget2, a.f847b) | zI1;
                    if (dVar.a1 != null) {
                        throw null;
                    }
                    int iY3 = constraintWidget2.Y();
                    int iZ3 = constraintWidget2.z();
                    if (iY3 != iY2) {
                        constraintWidget2.n1(iY3);
                        if (z6 && constraintWidget2.O() > iMax) {
                            iMax = Math.max(iMax, constraintWidget2.O() + constraintWidget2.q(ConstraintAnchor.Type.RIGHT).f());
                        }
                        z2 = true;
                    } else {
                        z2 = zA;
                    }
                    if (iZ3 != iZ2) {
                        constraintWidget2.O0(iZ3);
                        if (z7 && constraintWidget2.t() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget2.t() + constraintWidget2.q(ConstraintAnchor.Type.BOTTOM).f());
                        }
                        z2 = true;
                    }
                    zI1 = z2 | ((androidx.constraintlayout.core.widgets.k) constraintWidget2).I1();
                } else {
                    i13 = iM1;
                    i14 = iZ;
                }
                i17++;
                iM1 = i13;
                iZ = i14;
                i16 = 2;
            }
            int i18 = iM1;
            int i19 = iZ;
            int i20 = i16;
            int i21 = 0;
            while (i21 < i20) {
                int i22 = 0;
                while (i22 < size2) {
                    ConstraintWidget constraintWidget3 = bVar.f843a.get(i22);
                    if (((constraintWidget3 instanceof androidx.constraintlayout.core.widgets.g) && !(constraintWidget3 instanceof androidx.constraintlayout.core.widgets.k)) || (constraintWidget3 instanceof androidx.constraintlayout.core.widgets.f) || constraintWidget3.X() == 8 || ((z5 && constraintWidget3.f.f840e.j && constraintWidget3.g.f840e.j) || (constraintWidget3 instanceof androidx.constraintlayout.core.widgets.k))) {
                        z = z5;
                        i12 = size2;
                    } else {
                        int iY4 = constraintWidget3.Y();
                        int iZ4 = constraintWidget3.z();
                        z = z5;
                        int iR = constraintWidget3.r();
                        int i23 = a.f847b;
                        i12 = size2;
                        if (i21 == 1) {
                            i23 = a.f848c;
                        }
                        boolean zA2 = bVar.a(interfaceC0016bL1, constraintWidget3, i23) | zI1;
                        if (dVar.a1 != null) {
                            throw null;
                        }
                        int iY5 = constraintWidget3.Y();
                        int iZ5 = constraintWidget3.z();
                        if (iY5 != iY4) {
                            constraintWidget3.n1(iY5);
                            if (z6 && constraintWidget3.O() > iMax) {
                                iMax = Math.max(iMax, constraintWidget3.O() + constraintWidget3.q(ConstraintAnchor.Type.RIGHT).f());
                            }
                            zA2 = true;
                        }
                        if (iZ5 != iZ4) {
                            constraintWidget3.O0(iZ5);
                            if (z7 && constraintWidget3.t() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget3.t() + constraintWidget3.q(ConstraintAnchor.Type.BOTTOM).f());
                            }
                            zA2 = true;
                        }
                        zI1 = (!constraintWidget3.b0() || iR == constraintWidget3.r()) ? zA2 : true;
                    }
                    i22++;
                    bVar = this;
                    z5 = z;
                    size2 = i12;
                }
                boolean z8 = z5;
                int i24 = size2;
                if (!zI1) {
                    break;
                }
                i21++;
                c(dVar, "intermediate pass", i21, iY, i19);
                bVar = this;
                z5 = z8;
                size2 = i24;
                i20 = 2;
                zI1 = false;
            }
            i11 = i18;
        } else {
            i11 = iM1;
        }
        dVar.Z1(i11);
        return 0L;
    }

    public void e(androidx.constraintlayout.core.widgets.d dVar) {
        this.f843a.clear();
        int size = dVar.U0.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = dVar.U0.get(i);
            ConstraintWidget.DimensionBehaviour dimensionBehaviourC = constraintWidget.C();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (dimensionBehaviourC == dimensionBehaviour || constraintWidget.V() == dimensionBehaviour) {
                this.f843a.add(constraintWidget);
            }
        }
        dVar.P1();
    }
}
