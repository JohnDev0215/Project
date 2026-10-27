package e0;

import T.C0735d;
import java.util.ArrayList;
import java.util.HashMap;
import n7.AbstractC1998m;
import u.AbstractC2457E;
import u.C2453A;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1163c extends AbstractC1167g {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f13692n = new int[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2782k f13693e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InterfaceC2782k f13694f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f13695g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C2453A f13696h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f13697i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C1171k f13698j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f13699k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f13700m;

    public C1163c(int i7, C1171k c1171k, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        super(i7, c1171k);
        this.f13693e = interfaceC2782k;
        this.f13694f = interfaceC2782k2;
        this.f13698j = C1171k.f13713p;
        this.f13699k = f13692n;
        this.l = 1;
    }

    public void A(C2453A c2453a) {
        this.f13696h = c2453a;
    }

    public C1163c B(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        C1164d c1164d;
        if (this.c) {
            C0735d.X("Cannot use a disposed snapshot");
            throw null;
        }
        if (this.f13700m && this.d < 0) {
            C0735d.Y("Unsupported operation on a disposed or applied snapshot");
            throw null;
        }
        z(d());
        Object obj = AbstractC1174n.f13721b;
        synchronized (obj) {
            int i7 = AbstractC1174n.d;
            AbstractC1174n.d = i7 + 1;
            AbstractC1174n.c = AbstractC1174n.c.s(i7);
            C1171k c1171kE = e();
            r(c1171kE.s(i7));
            c1164d = new C1164d(i7, AbstractC1174n.e(c1171kE, d() + 1, i7), AbstractC1174n.l(interfaceC2782k, f(), true), AbstractC1174n.b(interfaceC2782k2, i()), this);
        }
        if (!this.f13700m && !this.c) {
            int iD = d();
            synchronized (obj) {
                int i10 = AbstractC1174n.d;
                AbstractC1174n.d = i10 + 1;
                q(i10);
                AbstractC1174n.c = AbstractC1174n.c.s(d());
            }
            r(AbstractC1174n.e(e(), iD + 1, d()));
        }
        return c1164d;
    }

    @Override // e0.AbstractC1167g
    public final void b() {
        AbstractC1174n.c = AbstractC1174n.c.l(d()).d(this.f13698j);
    }

    @Override // e0.AbstractC1167g
    public void c() {
        if (this.c) {
            return;
        }
        this.c = true;
        synchronized (AbstractC1174n.f13721b) {
            int i7 = this.d;
            if (i7 >= 0) {
                AbstractC1174n.u(i7);
                this.d = -1;
            }
        }
        l();
    }

    @Override // e0.AbstractC1167g
    public boolean g() {
        return false;
    }

    @Override // e0.AbstractC1167g
    public int h() {
        return this.f13695g;
    }

    @Override // e0.AbstractC1167g
    public InterfaceC2782k i() {
        return this.f13694f;
    }

    @Override // e0.AbstractC1167g
    public void k() {
        this.l++;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007d  */
    @Override // e0.AbstractC1167g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l() {
        /*
            r15 = this;
            int r0 = r15.l
            r1 = 0
            if (r0 <= 0) goto L7
            r2 = 1
            goto L8
        L7:
            r2 = r1
        L8:
            r3 = 0
            if (r2 == 0) goto L8c
            int r0 = r0 + (-1)
            r15.l = r0
            if (r0 != 0) goto L8b
            boolean r0 = r15.f13700m
            if (r0 != 0) goto L8b
            u.A r0 = r15.w()
            if (r0 == 0) goto L88
            boolean r2 = r15.f13700m
            if (r2 != 0) goto L82
            r15.A(r3)
            int r2 = r15.d()
            java.lang.Object[] r3 = r0.f22580b
            long[] r0 = r0.f22579a
            int r4 = r0.length
            int r4 = r4 + (-2)
            if (r4 < 0) goto L88
            r5 = r1
        L30:
            r6 = r0[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L7d
            int r8 = r5 - r4
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r1
        L4a:
            if (r10 >= r8) goto L7b
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L77
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r3[r11]
            e0.A r11 = (e0.InterfaceC1154A) r11
            e0.C r11 = r11.d()
        L60:
            if (r11 == 0) goto L77
            int r12 = r11.f13675a
            if (r12 == r2) goto L72
            e0.k r13 = r15.f13698j
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            boolean r12 = n7.AbstractC1998m.T(r13, r12)
            if (r12 == 0) goto L74
        L72:
            r11.f13675a = r1
        L74:
            e0.C r11 = r11.f13676b
            goto L60
        L77:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L4a
        L7b:
            if (r8 != r9) goto L88
        L7d:
            if (r5 == r4) goto L88
            int r5 = r5 + 1
            goto L30
        L82:
            java.lang.String r15 = "Unsupported operation on a snapshot that has been applied"
            T.C0735d.Y(r15)
            throw r3
        L88:
            r15.a()
        L8b:
            return
        L8c:
            java.lang.String r15 = "no pending nested snapshots"
            T.C0735d.X(r15)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1163c.l():void");
    }

    @Override // e0.AbstractC1167g
    public void m() {
        if (this.f13700m || this.c) {
            return;
        }
        u();
    }

    @Override // e0.AbstractC1167g
    public void n(InterfaceC1154A interfaceC1154A) {
        C2453A c2453aW = w();
        if (c2453aW == null) {
            int i7 = AbstractC2457E.f22585a;
            c2453aW = new C2453A();
            A(c2453aW);
        }
        c2453aW.a(interfaceC1154A);
    }

    @Override // e0.AbstractC1167g
    public final void o() {
        int length = this.f13699k.length;
        for (int i7 = 0; i7 < length; i7++) {
            AbstractC1174n.u(this.f13699k[i7]);
        }
        int i10 = this.d;
        if (i10 >= 0) {
            AbstractC1174n.u(i10);
            this.d = -1;
        }
    }

    @Override // e0.AbstractC1167g
    public void s(int i7) {
        this.f13695g = i7;
    }

    @Override // e0.AbstractC1167g
    public AbstractC1167g t(InterfaceC2782k interfaceC2782k) {
        C1165e c1165e;
        if (this.c) {
            C0735d.X("Cannot use a disposed snapshot");
            throw null;
        }
        if (this.f13700m && this.d < 0) {
            C0735d.Y("Unsupported operation on a disposed or applied snapshot");
            throw null;
        }
        int iD = d();
        z(d());
        Object obj = AbstractC1174n.f13721b;
        synchronized (obj) {
            int i7 = AbstractC1174n.d;
            AbstractC1174n.d = i7 + 1;
            AbstractC1174n.c = AbstractC1174n.c.s(i7);
            c1165e = new C1165e(i7, AbstractC1174n.e(e(), iD + 1, i7), AbstractC1174n.l(interfaceC2782k, f(), true), this);
        }
        if (!this.f13700m && !this.c) {
            int iD2 = d();
            synchronized (obj) {
                int i10 = AbstractC1174n.d;
                AbstractC1174n.d = i10 + 1;
                q(i10);
                AbstractC1174n.c = AbstractC1174n.c.s(d());
            }
            r(AbstractC1174n.e(e(), iD2 + 1, d()));
        }
        return c1165e;
    }

    public final void u() {
        z(d());
        if (this.f13700m || this.c) {
            return;
        }
        int iD = d();
        synchronized (AbstractC1174n.f13721b) {
            int i7 = AbstractC1174n.d;
            AbstractC1174n.d = i7 + 1;
            q(i7);
            AbstractC1174n.c = AbstractC1174n.c.s(d());
        }
        r(AbstractC1174n.e(e(), iD + 1, d()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011d  */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public e0.AbstractC1179s v() {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.C1163c.v():e0.s");
    }

    public C2453A w() {
        return this.f13696h;
    }

    @Override // e0.AbstractC1167g
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public InterfaceC2782k f() {
        return this.f13693e;
    }

    public final AbstractC1179s y(int i7, HashMap map, C1171k c1171k) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayListN0;
        ArrayList arrayList3;
        C1171k c1171k2;
        Object[] objArr;
        long[] jArr;
        C1171k c1171k3;
        Object[] objArr2;
        long[] jArr2;
        int i10;
        AbstractC1156C abstractC1156CS;
        AbstractC1156C abstractC1156CL;
        C1171k c1171kR = e().s(d()).r(this.f13698j);
        C2453A c2453aW = w();
        A7.m.c(c2453aW);
        Object[] objArr3 = c2453aW.f22580b;
        long[] jArr3 = c2453aW.f22579a;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i11 = 0;
            arrayList3 = null;
            arrayListN0 = null;
            while (true) {
                long j3 = jArr3[i11];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i12 = 8;
                    int i13 = 8 - ((~(i11 - length)) >>> 31);
                    int i14 = 0;
                    while (i14 < i13) {
                        if ((j3 & 255) < 128) {
                            InterfaceC1154A interfaceC1154A = (InterfaceC1154A) objArr3[(i11 << 3) + i14];
                            AbstractC1156C abstractC1156CD = interfaceC1154A.d();
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            AbstractC1156C abstractC1156CS2 = AbstractC1174n.s(abstractC1156CD, i7, c1171k);
                            if (abstractC1156CS2 == null || (abstractC1156CS = AbstractC1174n.s(abstractC1156CD, d(), c1171kR)) == null) {
                                c1171k3 = c1171kR;
                            } else {
                                c1171k3 = c1171kR;
                                if (abstractC1156CS.f13675a != 1 && !abstractC1156CS2.equals(abstractC1156CS)) {
                                    AbstractC1156C abstractC1156CS3 = AbstractC1174n.s(abstractC1156CD, d(), e());
                                    if (abstractC1156CS3 == null) {
                                        AbstractC1174n.r();
                                        throw null;
                                    }
                                    if (map == null || (abstractC1156CL = (AbstractC1156C) map.get(abstractC1156CS2)) == null) {
                                        abstractC1156CL = interfaceC1154A.l(abstractC1156CS, abstractC1156CS2, abstractC1156CS3);
                                    }
                                    if (abstractC1156CL == null) {
                                        return new C1168h();
                                    }
                                    if (!abstractC1156CL.equals(abstractC1156CS3)) {
                                        if (abstractC1156CL.equals(abstractC1156CS2)) {
                                            if (arrayList3 == null) {
                                                arrayList3 = new ArrayList();
                                            }
                                            arrayList3.add(new m7.i(interfaceC1154A, abstractC1156CS2.b()));
                                            if (arrayListN0 == null) {
                                                arrayListN0 = new ArrayList();
                                            }
                                            arrayListN0.add(interfaceC1154A);
                                        } else {
                                            if (arrayList3 == null) {
                                                arrayList3 = new ArrayList();
                                            }
                                            arrayList3.add(!abstractC1156CL.equals(abstractC1156CS) ? new m7.i(interfaceC1154A, abstractC1156CL) : new m7.i(interfaceC1154A, abstractC1156CS.b()));
                                        }
                                    }
                                }
                            }
                            i10 = 8;
                        } else {
                            c1171k3 = c1171kR;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            i10 = i12;
                        }
                        j3 >>= i10;
                        i14++;
                        i12 = i10;
                        objArr3 = objArr2;
                        jArr3 = jArr2;
                        c1171kR = c1171k3;
                    }
                    c1171k2 = c1171kR;
                    objArr = objArr3;
                    jArr = jArr3;
                    if (i13 != i12) {
                        break;
                    }
                } else {
                    c1171k2 = c1171kR;
                    objArr = objArr3;
                    jArr = jArr3;
                }
                if (i11 == length) {
                    arrayList2 = arrayList3;
                    arrayList = arrayListN0;
                    break;
                }
                i11++;
                objArr3 = objArr;
                jArr3 = jArr;
                c1171kR = c1171k2;
            }
        } else {
            arrayList = null;
            arrayList2 = null;
        }
        arrayListN0 = arrayList;
        arrayList3 = arrayList2;
        if (arrayList3 != null) {
            u();
            int size = arrayList3.size();
            for (int i15 = 0; i15 < size; i15++) {
                m7.i iVar = (m7.i) arrayList3.get(i15);
                InterfaceC1154A interfaceC1154A2 = (InterfaceC1154A) iVar.l;
                AbstractC1156C abstractC1156C = (AbstractC1156C) iVar.f19259m;
                abstractC1156C.f13675a = d();
                synchronized (AbstractC1174n.f13721b) {
                    abstractC1156C.f13676b = interfaceC1154A2.d();
                    interfaceC1154A2.q(abstractC1156C);
                }
            }
        }
        if (arrayListN0 != null) {
            int size2 = arrayListN0.size();
            for (int i16 = 0; i16 < size2; i16++) {
                c2453aW.j((InterfaceC1154A) arrayListN0.get(i16));
            }
            ArrayList arrayList4 = this.f13697i;
            if (arrayList4 != null) {
                arrayListN0 = AbstractC1998m.n0(arrayList4, arrayListN0);
            }
            this.f13697i = arrayListN0;
        }
        return C1169i.c;
    }

    public final void z(int i7) {
        synchronized (AbstractC1174n.f13721b) {
            this.f13698j = this.f13698j.s(i7);
        }
    }
}
