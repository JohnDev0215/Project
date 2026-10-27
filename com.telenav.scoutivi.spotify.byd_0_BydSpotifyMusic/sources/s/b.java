package s;

import java.util.ArrayList;
import r.d;
import r.e;

/* JADX INFO: compiled from: BasicMeasure.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<r.e> f9380a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f9381b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private r.f f9382c;

    /* JADX INFO: compiled from: BasicMeasure.java */
    public static class a {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static int f9383k = 0;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static int f9384l = 1;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static int f9385m = 2;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e.b f9386a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e.b f9387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f9388c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f9389d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9390e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f9391f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f9392g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f9393h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f9394i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f9395j;
    }

    /* JADX INFO: renamed from: s.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BasicMeasure.java */
    public interface InterfaceC0143b {
        void a(r.e eVar, a aVar);

        void b();
    }

    public b(r.f fVar) {
        this.f9382c = fVar;
    }

    private boolean a(InterfaceC0143b interfaceC0143b, r.e eVar, int i7) {
        this.f9381b.f9386a = eVar.C();
        this.f9381b.f9387b = eVar.V();
        this.f9381b.f9388c = eVar.Y();
        this.f9381b.f9389d = eVar.z();
        a aVar = this.f9381b;
        aVar.f9394i = false;
        aVar.f9395j = i7;
        e.b bVar = aVar.f9386a;
        e.b bVar2 = e.b.MATCH_CONSTRAINT;
        boolean z6 = bVar == bVar2;
        boolean z7 = aVar.f9387b == bVar2;
        boolean z8 = z6 && eVar.f9215f0 > 0.0f;
        boolean z9 = z7 && eVar.f9215f0 > 0.0f;
        if (z8 && eVar.f9252y[0] == 4) {
            aVar.f9386a = e.b.FIXED;
        }
        if (z9 && eVar.f9252y[1] == 4) {
            aVar.f9387b = e.b.FIXED;
        }
        interfaceC0143b.a(eVar, aVar);
        eVar.n1(this.f9381b.f9390e);
        eVar.O0(this.f9381b.f9391f);
        eVar.N0(this.f9381b.f9393h);
        eVar.D0(this.f9381b.f9392g);
        a aVar2 = this.f9381b;
        aVar2.f9395j = a.f9383k;
        return aVar2.f9394i;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0097 A[PHI: r10
  0x0097: PHI (r10v2 boolean) = (r10v1 boolean), (r10v1 boolean), (r10v1 boolean), (r10v4 boolean), (r10v4 boolean) binds: [B:32:0x0061, B:34:0x0067, B:36:0x006b, B:54:0x0094, B:52:0x008d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00a0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(r.f r13) {
        /*
            r12 = this;
            java.util.ArrayList<r.e> r0 = r13.U0
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.W1(r1)
            s.b$b r2 = r13.L1()
            r3 = 0
            r4 = r3
        L12:
            if (r4 >= r0) goto La4
            java.util.ArrayList<r.e> r5 = r13.U0
            java.lang.Object r5 = r5.get(r4)
            r.e r5 = (r.e) r5
            boolean r6 = r5 instanceof r.h
            if (r6 == 0) goto L22
            goto La0
        L22:
            boolean r6 = r5 instanceof r.a
            if (r6 == 0) goto L28
            goto La0
        L28:
            boolean r6 = r5.n0()
            if (r6 == 0) goto L30
            goto La0
        L30:
            if (r1 == 0) goto L47
            s.l r6 = r5.f9212e
            if (r6 == 0) goto L47
            s.n r7 = r5.f9214f
            if (r7 == 0) goto L47
            s.g r6 = r6.f9456e
            boolean r6 = r6.f9416j
            if (r6 == 0) goto L47
            s.g r6 = r7.f9456e
            boolean r6 = r6.f9416j
            if (r6 == 0) goto L47
            goto La0
        L47:
            r.e$b r6 = r5.w(r3)
            r7 = 1
            r.e$b r8 = r5.w(r7)
            r.e$b r9 = r.e.b.MATCH_CONSTRAINT
            if (r6 != r9) goto L60
            int r10 = r5.f9248w
            if (r10 == r7) goto L60
            if (r8 != r9) goto L60
            int r10 = r5.f9250x
            if (r10 == r7) goto L60
            r10 = r7
            goto L61
        L60:
            r10 = r3
        L61:
            if (r10 != 0) goto L97
            boolean r11 = r13.W1(r7)
            if (r11 == 0) goto L97
            boolean r11 = r5 instanceof r.m
            if (r11 != 0) goto L97
            if (r6 != r9) goto L7c
            int r11 = r5.f9248w
            if (r11 != 0) goto L7c
            if (r8 == r9) goto L7c
            boolean r11 = r5.k0()
            if (r11 != 0) goto L7c
            r10 = r7
        L7c:
            if (r8 != r9) goto L8b
            int r11 = r5.f9250x
            if (r11 != 0) goto L8b
            if (r6 == r9) goto L8b
            boolean r11 = r5.k0()
            if (r11 != 0) goto L8b
            r10 = r7
        L8b:
            if (r6 == r9) goto L8f
            if (r8 != r9) goto L97
        L8f:
            float r6 = r5.f9215f0
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L97
            goto L98
        L97:
            r7 = r10
        L98:
            if (r7 == 0) goto L9b
            goto La0
        L9b:
            int r6 = s.b.a.f9383k
            r12.a(r2, r5, r6)
        La0:
            int r4 = r4 + 1
            goto L12
        La4:
            r2.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: s.b.b(r.f):void");
    }

    private void c(r.f fVar, String str, int i7, int i8, int i9) {
        int iK = fVar.K();
        int iJ = fVar.J();
        fVar.d1(0);
        fVar.c1(0);
        fVar.n1(i8);
        fVar.O0(i9);
        fVar.d1(iK);
        fVar.c1(iJ);
        this.f9382c.a2(i7);
        this.f9382c.v1();
    }

    public long d(r.f fVar, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        boolean zK1;
        int i16;
        r.f fVar2;
        int i17;
        boolean z6;
        int i18;
        int i19;
        boolean z7;
        b bVar = this;
        InterfaceC0143b interfaceC0143bL1 = fVar.L1();
        int size = fVar.U0.size();
        int iY = fVar.Y();
        int iZ = fVar.z();
        boolean zB = r.k.b(i7, 128);
        boolean z8 = zB || r.k.b(i7, 64);
        if (z8) {
            for (int i20 = 0; i20 < size; i20++) {
                r.e eVar = fVar.U0.get(i20);
                e.b bVarC = eVar.C();
                e.b bVar2 = e.b.MATCH_CONSTRAINT;
                boolean z9 = (bVarC == bVar2) && (eVar.V() == bVar2) && eVar.x() > 0.0f;
                if ((eVar.k0() && z9) || ((eVar.m0() && z9) || (eVar instanceof r.m) || eVar.k0() || eVar.m0())) {
                    z8 = false;
                    break;
                }
            }
        }
        if (z8) {
            boolean z10 = o.d.f8569r;
        }
        boolean z11 = z8 & ((i10 == 1073741824 && i12 == 1073741824) || zB);
        int i21 = 2;
        if (z11) {
            int iMin = Math.min(fVar.I(), i11);
            int iMin2 = Math.min(fVar.H(), i13);
            if (i10 == 1073741824 && fVar.Y() != iMin) {
                fVar.n1(iMin);
                fVar.P1();
            }
            if (i12 == 1073741824 && fVar.z() != iMin2) {
                fVar.O0(iMin2);
                fVar.P1();
            }
            if (i10 == 1073741824 && i12 == 1073741824) {
                zK1 = fVar.I1(zB);
                i16 = 2;
            } else {
                boolean zJ1 = fVar.J1(zB);
                if (i10 == 1073741824) {
                    zJ1 &= fVar.K1(zB, 0);
                    i16 = 1;
                } else {
                    i16 = 0;
                }
                if (i12 == 1073741824) {
                    zK1 = fVar.K1(zB, 1) & zJ1;
                    i16++;
                } else {
                    zK1 = zJ1;
                }
            }
            if (zK1) {
                fVar.s1(i10 == 1073741824, i12 == 1073741824);
            }
        } else {
            zK1 = false;
            i16 = 0;
        }
        if (zK1 && i16 == 2) {
            return 0L;
        }
        int iM1 = fVar.M1();
        if (size > 0) {
            b(fVar);
        }
        e(fVar);
        int size2 = bVar.f9380a.size();
        if (size > 0) {
            c(fVar, "First pass", 0, iY, iZ);
        }
        if (size2 > 0) {
            e.b bVarC2 = fVar.C();
            e.b bVar3 = e.b.WRAP_CONTENT;
            boolean z12 = bVarC2 == bVar3;
            boolean z13 = fVar.V() == bVar3;
            int iMax = Math.max(fVar.Y(), bVar.f9382c.K());
            int iMax2 = Math.max(fVar.z(), bVar.f9382c.J());
            int i22 = 0;
            boolean zC1 = false;
            while (i22 < size2) {
                r.e eVar2 = bVar.f9380a.get(i22);
                if (eVar2 instanceof r.m) {
                    int iY2 = eVar2.Y();
                    int iZ2 = eVar2.z();
                    i19 = iM1;
                    boolean zA = bVar.a(interfaceC0143bL1, eVar2, a.f9384l) | zC1;
                    int iY3 = eVar2.Y();
                    int iZ3 = eVar2.z();
                    if (iY3 != iY2) {
                        eVar2.n1(iY3);
                        if (z12 && eVar2.O() > iMax) {
                            iMax = Math.max(iMax, eVar2.O() + eVar2.q(d.b.RIGHT).f());
                        }
                        z7 = true;
                    } else {
                        z7 = zA;
                    }
                    if (iZ3 != iZ2) {
                        eVar2.O0(iZ3);
                        if (z13 && eVar2.t() > iMax2) {
                            iMax2 = Math.max(iMax2, eVar2.t() + eVar2.q(d.b.BOTTOM).f());
                        }
                        z7 = true;
                    }
                    zC1 = z7 | ((r.m) eVar2).C1();
                } else {
                    i19 = iM1;
                }
                i22++;
                iM1 = i19;
                i21 = 2;
            }
            int i23 = iM1;
            int i24 = i21;
            int i25 = 0;
            while (i25 < i24) {
                int i26 = 0;
                while (i26 < size2) {
                    r.e eVar3 = bVar.f9380a.get(i26);
                    if (((eVar3 instanceof r.i) && !(eVar3 instanceof r.m)) || (eVar3 instanceof r.h) || eVar3.X() == 8 || ((z11 && eVar3.f9212e.f9456e.f9416j && eVar3.f9214f.f9456e.f9416j) || (eVar3 instanceof r.m))) {
                        z6 = z11;
                        i18 = size2;
                    } else {
                        int iY4 = eVar3.Y();
                        int iZ4 = eVar3.z();
                        z6 = z11;
                        int iR = eVar3.r();
                        int i27 = a.f9384l;
                        i18 = size2;
                        if (i25 == 1) {
                            i27 = a.f9385m;
                        }
                        boolean zA2 = bVar.a(interfaceC0143bL1, eVar3, i27) | zC1;
                        int iY5 = eVar3.Y();
                        int iZ5 = eVar3.z();
                        if (iY5 != iY4) {
                            eVar3.n1(iY5);
                            if (z12 && eVar3.O() > iMax) {
                                iMax = Math.max(iMax, eVar3.O() + eVar3.q(d.b.RIGHT).f());
                            }
                            zA2 = true;
                        }
                        if (iZ5 != iZ4) {
                            eVar3.O0(iZ5);
                            if (z13 && eVar3.t() > iMax2) {
                                iMax2 = Math.max(iMax2, eVar3.t() + eVar3.q(d.b.BOTTOM).f());
                            }
                            zA2 = true;
                        }
                        zC1 = (!eVar3.b0() || iR == eVar3.r()) ? zA2 : true;
                    }
                    i26++;
                    bVar = this;
                    z11 = z6;
                    size2 = i18;
                }
                boolean z14 = z11;
                int i28 = size2;
                if (!zC1) {
                    break;
                }
                i25++;
                c(fVar, "intermediate pass", i25, iY, iZ);
                bVar = this;
                z11 = z14;
                size2 = i28;
                i24 = 2;
                zC1 = false;
            }
            fVar2 = fVar;
            i17 = i23;
        } else {
            fVar2 = fVar;
            i17 = iM1;
        }
        fVar2.Z1(i17);
        return 0L;
    }

    public void e(r.f fVar) {
        this.f9380a.clear();
        int size = fVar.U0.size();
        for (int i7 = 0; i7 < size; i7++) {
            r.e eVar = fVar.U0.get(i7);
            e.b bVarC = eVar.C();
            e.b bVar = e.b.MATCH_CONSTRAINT;
            if (bVarC == bVar || eVar.V() == bVar) {
                this.f9380a.add(eVar);
            }
        }
        fVar.P1();
    }
}
