package e0;

import G.f0;
import T.C0737e;
import T.U0;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import l3.P;
import n7.AbstractC1996k;
import u.C2453A;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1174n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final P f13720a = new P(11);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f13721b = new Object();
    public static C1171k c;
    public static int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f0 f13722e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final B5.u f13723f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Object f13724g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Object f13725h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicReference f13726i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AbstractC1167g f13727j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final C0737e f13728k;

    static {
        C1171k c1171k = C1171k.f13713p;
        c = c1171k;
        d = 2;
        f0 f0Var = new f0();
        f0Var.c = new int[16];
        f0Var.d = new int[16];
        int[] iArr = new int[16];
        int i7 = 0;
        while (i7 < 16) {
            int i10 = i7 + 1;
            iArr[i7] = i10;
            i7 = i10;
        }
        f0Var.f2559e = iArr;
        f13722e = f0Var;
        B5.u uVar = new B5.u(7, (byte) 0);
        uVar.f736n = new int[16];
        uVar.o = new U0[16];
        f13723f = uVar;
        n7.u uVar2 = n7.u.l;
        f13724g = uVar2;
        f13725h = uVar2;
        int i11 = d;
        d = i11 + 1;
        C1162b c1162b = new C1162b(i11, c1171k);
        c = c.s(c1162b.f13707b);
        AtomicReference atomicReference = new AtomicReference(c1162b);
        f13726i = atomicReference;
        f13727j = (AbstractC1167g) atomicReference.get();
        f13728k = new C0737e(0);
    }

    public static final void a() {
        f(C1172l.f13716n);
    }

    public static final InterfaceC2782k b(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        return (interfaceC2782k == null || interfaceC2782k2 == null || interfaceC2782k == interfaceC2782k2) ? interfaceC2782k == null ? interfaceC2782k2 : interfaceC2782k : new C1161a(interfaceC2782k, interfaceC2782k2, 2);
    }

    public static final HashMap c(C1163c c1163c, C1163c c1163c2, C1171k c1171k) {
        long[] jArr;
        int i7;
        C1171k c1171k2;
        long[] jArr2;
        int i10;
        C1171k c1171k3;
        C2453A c2453aW = c1163c2.w();
        int iD = c1163c.d();
        if (c2453aW != null) {
            C1171k c1171kR = c1163c2.e().s(c1163c2.d()).r(c1163c2.f13698j);
            Object[] objArr = c2453aW.f22580b;
            long[] jArr3 = c2453aW.f22579a;
            int length = jArr3.length - 2;
            if (length < 0) {
                return null;
            }
            int i11 = 0;
            HashMap map = null;
            loop0: while (true) {
                long j3 = jArr3[i11];
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i12 = 8;
                    int i13 = 8 - ((~(i11 - length)) >>> 31);
                    int i14 = 0;
                    while (i14 < i13) {
                        if ((255 & j3) < 128) {
                            InterfaceC1154A interfaceC1154A = (InterfaceC1154A) objArr[(i11 << 3) + i14];
                            AbstractC1156C abstractC1156CD = interfaceC1154A.d();
                            AbstractC1156C abstractC1156CS = s(abstractC1156CD, iD, c1171k);
                            if (abstractC1156CS == null) {
                                jArr2 = jArr3;
                            } else {
                                jArr2 = jArr3;
                                AbstractC1156C abstractC1156CS2 = s(abstractC1156CD, iD, c1171kR);
                                if (abstractC1156CS2 != null && !abstractC1156CS.equals(abstractC1156CS2)) {
                                    i10 = iD;
                                    c1171k3 = c1171kR;
                                    AbstractC1156C abstractC1156CS3 = s(abstractC1156CD, c1163c2.d(), c1163c2.e());
                                    if (abstractC1156CS3 == null) {
                                        r();
                                        throw null;
                                    }
                                    AbstractC1156C abstractC1156CL = interfaceC1154A.l(abstractC1156CS2, abstractC1156CS, abstractC1156CS3);
                                    if (abstractC1156CL == null) {
                                        break loop0;
                                    }
                                    if (map == null) {
                                        map = new HashMap();
                                    }
                                    map.put(abstractC1156CS, abstractC1156CL);
                                    map = map;
                                }
                            }
                            i10 = iD;
                            c1171k3 = c1171kR;
                        } else {
                            jArr2 = jArr3;
                            i10 = iD;
                            c1171k3 = c1171kR;
                        }
                        j3 >>= 8;
                        i14++;
                        i12 = 8;
                        jArr3 = jArr2;
                        iD = i10;
                        c1171kR = c1171k3;
                    }
                    jArr = jArr3;
                    i7 = iD;
                    c1171k2 = c1171kR;
                    if (i13 != i12) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                    i7 = iD;
                    c1171k2 = c1171kR;
                }
                if (i11 == length) {
                    break;
                }
                i11++;
                jArr3 = jArr;
                iD = i7;
                c1171kR = c1171k2;
            }
            return map;
        }
        return null;
    }

    public static final void d(AbstractC1167g abstractC1167g) {
        int i7;
        if (c.q(abstractC1167g.d())) {
            return;
        }
        StringBuilder sb = new StringBuilder("Snapshot is not open: id=");
        sb.append(abstractC1167g.d());
        sb.append(", disposed=");
        sb.append(abstractC1167g.c);
        sb.append(", applied=");
        C1163c c1163c = abstractC1167g instanceof C1163c ? (C1163c) abstractC1167g : null;
        sb.append(c1163c != null ? Boolean.valueOf(c1163c.f13700m) : "read-only");
        sb.append(", lowestPin=");
        synchronized (f13721b) {
            f0 f0Var = f13722e;
            i7 = f0Var.f2557a > 0 ? ((int[]) f0Var.c)[0] : -1;
        }
        sb.append(i7);
        throw new IllegalStateException(sb.toString().toString());
    }

    public static final C1171k e(C1171k c1171k, int i7, int i10) {
        while (i7 < i10) {
            c1171k = c1171k.s(i7);
            i7++;
        }
        return c1171k;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a1  */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object f(z7.InterfaceC2782k r15) {
        /*
            e0.g r0 = e0.AbstractC1174n.f13727j
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.snapshots.GlobalSnapshot"
            A7.m.d(r0, r1)
            e0.b r0 = (e0.C1162b) r0
            java.lang.Object r0 = e0.AbstractC1174n.f13721b
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference r1 = e0.AbstractC1174n.f13726i     // Catch: java.lang.Throwable -> L20
            java.lang.Object r1 = r1.get()     // Catch: java.lang.Throwable -> L20
            r2 = r1
            e0.b r2 = (e0.C1162b) r2     // Catch: java.lang.Throwable -> L20
            u.A r2 = r2.f13696h     // Catch: java.lang.Throwable -> L20
            if (r2 == 0) goto L23
            T.e r3 = e0.AbstractC1174n.f13728k     // Catch: java.lang.Throwable -> L20
            r4 = 1
            r3.addAndGet(r4)     // Catch: java.lang.Throwable -> L20
            goto L23
        L20:
            r15 = move-exception
            goto Laa
        L23:
            r3 = r1
            e0.g r3 = (e0.AbstractC1167g) r3     // Catch: java.lang.Throwable -> L20
            java.lang.Object r15 = v(r3, r15)     // Catch: java.lang.Throwable -> L20
            monitor-exit(r0)
            r0 = 0
            if (r2 == 0) goto L57
            r3 = -1
            java.lang.Object r4 = e0.AbstractC1174n.f13724g     // Catch: java.lang.Throwable -> L49
            int r5 = r4.size()     // Catch: java.lang.Throwable -> L49
            r6 = r0
        L36:
            if (r6 >= r5) goto L4b
            java.lang.Object r7 = r4.get(r6)     // Catch: java.lang.Throwable -> L49
            z7.n r7 = (z7.InterfaceC2785n) r7     // Catch: java.lang.Throwable -> L49
            V.f r8 = new V.f     // Catch: java.lang.Throwable -> L49
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L49
            r7.invoke(r8, r1)     // Catch: java.lang.Throwable -> L49
            int r6 = r6 + 1
            goto L36
        L49:
            r15 = move-exception
            goto L51
        L4b:
            T.e r1 = e0.AbstractC1174n.f13728k
            r1.addAndGet(r3)
            goto L57
        L51:
            T.e r0 = e0.AbstractC1174n.f13728k
            r0.addAndGet(r3)
            throw r15
        L57:
            java.lang.Object r1 = e0.AbstractC1174n.f13721b
            monitor-enter(r1)
            g()     // Catch: java.lang.Throwable -> L99
            if (r2 == 0) goto La6
            java.lang.Object[] r3 = r2.f22580b     // Catch: java.lang.Throwable -> L99
            long[] r2 = r2.f22579a     // Catch: java.lang.Throwable -> L99
            int r4 = r2.length     // Catch: java.lang.Throwable -> L99
            int r4 = r4 + (-2)
            if (r4 < 0) goto La6
            r5 = r0
        L69:
            r6 = r2[r5]     // Catch: java.lang.Throwable -> L99
            long r8 = ~r6     // Catch: java.lang.Throwable -> L99
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto La1
            int r8 = r5 - r4
            int r8 = ~r8     // Catch: java.lang.Throwable -> L99
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r0
        L83:
            if (r10 >= r8) goto L9f
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L9b
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r3[r11]     // Catch: java.lang.Throwable -> L99
            e0.A r11 = (e0.InterfaceC1154A) r11     // Catch: java.lang.Throwable -> L99
            q(r11)     // Catch: java.lang.Throwable -> L99
            goto L9b
        L99:
            r15 = move-exception
            goto La8
        L9b:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L83
        L9f:
            if (r8 != r9) goto La6
        La1:
            if (r5 == r4) goto La6
            int r5 = r5 + 1
            goto L69
        La6:
            monitor-exit(r1)
            return r15
        La8:
            monitor-exit(r1)
            throw r15
        Laa:
            monitor-exit(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.AbstractC1174n.f(z7.k):java.lang.Object");
    }

    public static final void g() {
        B5.u uVar = f13723f;
        int i7 = uVar.f735m;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i10 >= i7) {
                break;
            }
            U0 u02 = ((U0[]) uVar.o)[i10];
            Object obj = u02 != null ? u02.get() : null;
            if (obj != null && p((InterfaceC1154A) obj)) {
                if (i11 != i10) {
                    ((U0[]) uVar.o)[i11] = u02;
                    int[] iArr = (int[]) uVar.f736n;
                    iArr[i11] = iArr[i10];
                }
                i11++;
            }
            i10++;
        }
        for (int i12 = i11; i12 < i7; i12++) {
            ((U0[]) uVar.o)[i12] = null;
            ((int[]) uVar.f736n)[i12] = 0;
        }
        if (i11 != i7) {
            uVar.f735m = i11;
        }
    }

    public static final AbstractC1167g h(AbstractC1167g abstractC1167g, InterfaceC2782k interfaceC2782k, boolean z6) {
        boolean z10 = abstractC1167g instanceof C1163c;
        if (z10 || abstractC1167g == null) {
            return new C1159F(z10 ? (C1163c) abstractC1167g : null, interfaceC2782k, null, false, z6);
        }
        return new C1160G(abstractC1167g, interfaceC2782k, z6);
    }

    public static final AbstractC1156C i(AbstractC1156C abstractC1156C) {
        AbstractC1156C abstractC1156CS;
        AbstractC1167g abstractC1167gK = k();
        AbstractC1156C abstractC1156CS2 = s(abstractC1156C, abstractC1167gK.d(), abstractC1167gK.e());
        if (abstractC1156CS2 != null) {
            return abstractC1156CS2;
        }
        synchronized (f13721b) {
            AbstractC1167g abstractC1167gK2 = k();
            abstractC1156CS = s(abstractC1156C, abstractC1167gK2.d(), abstractC1167gK2.e());
        }
        if (abstractC1156CS != null) {
            return abstractC1156CS;
        }
        r();
        throw null;
    }

    public static final AbstractC1156C j(AbstractC1156C abstractC1156C, AbstractC1167g abstractC1167g) {
        AbstractC1156C abstractC1156CS = s(abstractC1156C, abstractC1167g.d(), abstractC1167g.e());
        if (abstractC1156CS != null) {
            return abstractC1156CS;
        }
        r();
        throw null;
    }

    public static final AbstractC1167g k() {
        AbstractC1167g abstractC1167g = (AbstractC1167g) f13720a.p();
        return abstractC1167g == null ? (AbstractC1167g) f13726i.get() : abstractC1167g;
    }

    public static final InterfaceC2782k l(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, boolean z6) {
        if (!z6) {
            interfaceC2782k2 = null;
        }
        return (interfaceC2782k == null || interfaceC2782k2 == null || interfaceC2782k == interfaceC2782k2) ? interfaceC2782k == null ? interfaceC2782k2 : interfaceC2782k : new C1161a(interfaceC2782k, interfaceC2782k2, 1);
    }

    public static final AbstractC1156C m(AbstractC1156C abstractC1156C, InterfaceC1154A interfaceC1154A) {
        AbstractC1156C abstractC1156CD = interfaceC1154A.d();
        int i7 = d;
        f0 f0Var = f13722e;
        if (f0Var.f2557a > 0) {
            i7 = ((int[]) f0Var.c)[0];
        }
        int i10 = i7 - 1;
        C1171k c1171k = C1171k.f13713p;
        AbstractC1156C abstractC1156C2 = null;
        AbstractC1156C abstractC1156C3 = null;
        while (true) {
            if (abstractC1156CD != null) {
                int i11 = abstractC1156CD.f13675a;
                if (i11 == 0) {
                    break;
                }
                if (i11 != 0 && i11 <= i10 && !c1171k.q(i11)) {
                    if (abstractC1156C3 == null) {
                        abstractC1156C3 = abstractC1156CD;
                    } else {
                        if (abstractC1156CD.f13675a < abstractC1156C3.f13675a) {
                            break;
                        }
                        abstractC1156C2 = abstractC1156C3;
                    }
                }
                abstractC1156CD = abstractC1156CD.f13676b;
            } else {
                break;
            }
        }
        abstractC1156C2 = abstractC1156CD;
        if (abstractC1156C2 != null) {
            abstractC1156C2.f13675a = Integer.MAX_VALUE;
            return abstractC1156C2;
        }
        AbstractC1156C abstractC1156CB = abstractC1156C.b();
        abstractC1156CB.f13675a = Integer.MAX_VALUE;
        abstractC1156CB.f13676b = interfaceC1154A.d();
        interfaceC1154A.q(abstractC1156CB);
        return abstractC1156CB;
    }

    public static final void n(AbstractC1167g abstractC1167g, InterfaceC1154A interfaceC1154A) {
        abstractC1167g.s(abstractC1167g.h() + 1);
        InterfaceC2782k interfaceC2782kI = abstractC1167g.i();
        if (interfaceC2782kI != null) {
            interfaceC2782kI.b(interfaceC1154A);
        }
    }

    public static final AbstractC1156C o(AbstractC1156C abstractC1156C, AbstractC1155B abstractC1155B, AbstractC1167g abstractC1167g, AbstractC1156C abstractC1156C2) {
        AbstractC1156C abstractC1156CM;
        if (abstractC1167g.g()) {
            abstractC1167g.n(abstractC1155B);
        }
        int iD = abstractC1167g.d();
        if (abstractC1156C2.f13675a == iD) {
            return abstractC1156C2;
        }
        synchronized (f13721b) {
            abstractC1156CM = m(abstractC1156C, abstractC1155B);
        }
        abstractC1156CM.f13675a = iD;
        if (abstractC1156C2.f13675a != 1) {
            abstractC1167g.n(abstractC1155B);
        }
        return abstractC1156CM;
    }

    public static final boolean p(InterfaceC1154A interfaceC1154A) {
        AbstractC1156C abstractC1156C;
        int i7 = d;
        f0 f0Var = f13722e;
        if (f0Var.f2557a > 0) {
            i7 = ((int[]) f0Var.c)[0];
        }
        AbstractC1156C abstractC1156C2 = null;
        AbstractC1156C abstractC1156CD = null;
        int i10 = 0;
        for (AbstractC1156C abstractC1156CD2 = interfaceC1154A.d(); abstractC1156CD2 != null; abstractC1156CD2 = abstractC1156CD2.f13676b) {
            int i11 = abstractC1156CD2.f13675a;
            if (i11 != 0) {
                if (i11 >= i7) {
                    i10++;
                } else if (abstractC1156C2 == null) {
                    i10++;
                    abstractC1156C2 = abstractC1156CD2;
                } else {
                    if (i11 < abstractC1156C2.f13675a) {
                        abstractC1156C = abstractC1156C2;
                        abstractC1156C2 = abstractC1156CD2;
                    } else {
                        abstractC1156C = abstractC1156CD2;
                    }
                    if (abstractC1156CD == null) {
                        abstractC1156CD = interfaceC1154A.d();
                        AbstractC1156C abstractC1156C3 = abstractC1156CD;
                        while (true) {
                            if (abstractC1156CD == null) {
                                abstractC1156CD = abstractC1156C3;
                                break;
                            }
                            int i12 = abstractC1156CD.f13675a;
                            if (i12 >= i7) {
                                break;
                            }
                            if (abstractC1156C3.f13675a < i12) {
                                abstractC1156C3 = abstractC1156CD;
                            }
                            abstractC1156CD = abstractC1156CD.f13676b;
                        }
                    }
                    abstractC1156C2.f13675a = 0;
                    abstractC1156C2.a(abstractC1156CD);
                    abstractC1156C2 = abstractC1156C;
                }
            }
        }
        return i10 > 1;
    }

    public static final void q(InterfaceC1154A interfaceC1154A) {
        if (p(interfaceC1154A)) {
            B5.u uVar = f13723f;
            int i7 = uVar.f735m;
            int iIdentityHashCode = System.identityHashCode(interfaceC1154A);
            int i10 = -1;
            if (i7 > 0) {
                int i11 = uVar.f735m - 1;
                int i12 = 0;
                while (true) {
                    if (i12 > i11) {
                        i10 = -(i12 + 1);
                        break;
                    }
                    int i13 = (i12 + i11) >>> 1;
                    int i14 = ((int[]) uVar.f736n)[i13];
                    if (i14 < iIdentityHashCode) {
                        i12 = i13 + 1;
                    } else if (i14 > iIdentityHashCode) {
                        i11 = i13 - 1;
                    } else {
                        U0 u02 = ((U0[]) uVar.o)[i13];
                        if (interfaceC1154A == (u02 != null ? u02.get() : null)) {
                            i10 = i13;
                        } else {
                            int i15 = i13 - 1;
                            while (-1 < i15 && ((int[]) uVar.f736n)[i15] == iIdentityHashCode) {
                                U0 u03 = ((U0[]) uVar.o)[i15];
                                if ((u03 != null ? u03.get() : null) == interfaceC1154A) {
                                    break;
                                } else {
                                    i15--;
                                }
                            }
                            int i16 = uVar.f735m;
                            i15 = i13 + 1;
                            while (true) {
                                if (i15 >= i16) {
                                    i15 = -(uVar.f735m + 1);
                                    break;
                                } else {
                                    if (((int[]) uVar.f736n)[i15] != iIdentityHashCode) {
                                        i15 = -(i15 + 1);
                                        break;
                                    }
                                    U0 u04 = ((U0[]) uVar.o)[i15];
                                    if ((u04 != null ? u04.get() : null) == interfaceC1154A) {
                                        break;
                                    } else {
                                        i15++;
                                    }
                                }
                            }
                            i10 = i15;
                        }
                    }
                }
                if (i10 >= 0) {
                    return;
                }
            }
            int i17 = -(i10 + 1);
            U0[] u0Arr = (U0[]) uVar.o;
            int length = u0Arr.length;
            if (i7 == length) {
                int i18 = length * 2;
                U0[] u0Arr2 = new U0[i18];
                int[] iArr = new int[i18];
                int i19 = i17 + 1;
                AbstractC1996k.D(i19, i17, i7, u0Arr, u0Arr2);
                AbstractC1996k.G(0, i17, 6, (U0[]) uVar.o, u0Arr2);
                AbstractC1996k.C(i19, i17, i7, (int[]) uVar.f736n, iArr);
                AbstractC1996k.F(0, i17, 6, (int[]) uVar.f736n, iArr);
                uVar.o = u0Arr2;
                uVar.f736n = iArr;
            } else {
                int i20 = i17 + 1;
                AbstractC1996k.D(i20, i17, i7, u0Arr, u0Arr);
                int[] iArr2 = (int[]) uVar.f736n;
                AbstractC1996k.C(i20, i17, i7, iArr2, iArr2);
            }
            ((U0[]) uVar.o)[i17] = new U0(interfaceC1154A);
            ((int[]) uVar.f736n)[i17] = iIdentityHashCode;
            uVar.f735m++;
        }
    }

    public static final void r() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied");
    }

    public static final AbstractC1156C s(AbstractC1156C abstractC1156C, int i7, C1171k c1171k) {
        AbstractC1156C abstractC1156C2 = null;
        while (abstractC1156C != null) {
            int i10 = abstractC1156C.f13675a;
            if (i10 != 0 && i10 <= i7 && !c1171k.q(i10) && (abstractC1156C2 == null || abstractC1156C2.f13675a < abstractC1156C.f13675a)) {
                abstractC1156C2 = abstractC1156C;
            }
            abstractC1156C = abstractC1156C.f13676b;
        }
        if (abstractC1156C2 != null) {
            return abstractC1156C2;
        }
        return null;
    }

    public static final AbstractC1156C t(AbstractC1156C abstractC1156C, InterfaceC1154A interfaceC1154A) {
        AbstractC1156C abstractC1156CS;
        AbstractC1167g abstractC1167gK = k();
        InterfaceC2782k interfaceC2782kF = abstractC1167gK.f();
        if (interfaceC2782kF != null) {
            interfaceC2782kF.b(interfaceC1154A);
        }
        AbstractC1156C abstractC1156CS2 = s(abstractC1156C, abstractC1167gK.d(), abstractC1167gK.e());
        if (abstractC1156CS2 != null) {
            return abstractC1156CS2;
        }
        synchronized (f13721b) {
            AbstractC1167g abstractC1167gK2 = k();
            AbstractC1156C abstractC1156CD = interfaceC1154A.d();
            A7.m.d(abstractC1156CD, "null cannot be cast to non-null type T of androidx.compose.runtime.snapshots.SnapshotKt.readable$lambda$9");
            abstractC1156CS = s(abstractC1156CD, abstractC1167gK2.d(), abstractC1167gK2.e());
            if (abstractC1156CS == null) {
                r();
                throw null;
            }
        }
        return abstractC1156CS;
    }

    public static final void u(int i7) {
        int i10;
        f0 f0Var = f13722e;
        int i11 = ((int[]) f0Var.f2559e)[i7];
        f0Var.b(i11, f0Var.f2557a - 1);
        f0Var.f2557a--;
        int[] iArr = (int[]) f0Var.c;
        int i12 = iArr[i11];
        int i13 = i11;
        while (i13 > 0) {
            int i14 = ((i13 + 1) >> 1) - 1;
            if (iArr[i14] <= i12) {
                break;
            }
            f0Var.b(i14, i13);
            i13 = i14;
        }
        int[] iArr2 = (int[]) f0Var.c;
        int i15 = f0Var.f2557a >> 1;
        while (i11 < i15) {
            int i16 = (i11 + 1) << 1;
            int i17 = i16 - 1;
            if (i16 < f0Var.f2557a && (i10 = iArr2[i16]) < iArr2[i17]) {
                if (i10 >= iArr2[i11]) {
                    break;
                }
                f0Var.b(i16, i11);
                i11 = i16;
            } else {
                if (iArr2[i17] >= iArr2[i11]) {
                    break;
                }
                f0Var.b(i17, i11);
                i11 = i17;
            }
        }
        ((int[]) f0Var.f2559e)[i7] = f0Var.f2558b;
        f0Var.f2558b = i7;
    }

    public static final Object v(AbstractC1167g abstractC1167g, InterfaceC2782k interfaceC2782k) {
        Object objB = interfaceC2782k.b(c.l(abstractC1167g.d()));
        synchronized (f13721b) {
            int i7 = d;
            d = i7 + 1;
            C1171k c1171kL = c.l(abstractC1167g.d());
            c = c1171kL;
            f13726i.set(new C1162b(i7, c1171kL));
            abstractC1167g.c();
            c = c.s(i7);
        }
        return objB;
    }

    public static final AbstractC1156C w(AbstractC1156C abstractC1156C, InterfaceC1154A interfaceC1154A, AbstractC1167g abstractC1167g) {
        AbstractC1156C abstractC1156CS;
        if (abstractC1167g.g()) {
            abstractC1167g.n(interfaceC1154A);
        }
        int iD = abstractC1167g.d();
        AbstractC1156C abstractC1156CS2 = s(abstractC1156C, iD, abstractC1167g.e());
        if (abstractC1156CS2 == null) {
            r();
            throw null;
        }
        if (abstractC1156CS2.f13675a == abstractC1167g.d()) {
            return abstractC1156CS2;
        }
        synchronized (f13721b) {
            abstractC1156CS = s(interfaceC1154A.d(), iD, abstractC1167g.e());
            if (abstractC1156CS == null) {
                r();
                throw null;
            }
            if (abstractC1156CS.f13675a != iD) {
                AbstractC1156C abstractC1156CM = m(abstractC1156CS, interfaceC1154A);
                abstractC1156CM.a(abstractC1156CS);
                abstractC1156CM.f13675a = abstractC1167g.d();
                abstractC1156CS = abstractC1156CM;
            }
        }
        if (abstractC1156CS2.f13675a != 1) {
            abstractC1167g.n(interfaceC1154A);
        }
        return abstractC1156CS;
    }
}
