package T;

import E0.C0171v;
import N1.C0420a;
import U.C0800a;
import U.C0801b;
import U.C0802c;
import android.util.SparseArray;
import f0.AbstractC1245b;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import u.AbstractC2457E;
import u.C2453A;
import u.C2474n;
import u.C2476p;
import u.C2482v;
import u.C2484x;
import u.C2486z;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0761q {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f8290A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0759p f8291C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final A7.D f8292D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f8293E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public y0 f8294F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public z0 f8295G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public B0 f8296H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f8297I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public InterfaceC0744h0 f8298J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public C0800a f8299K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final C0801b f8300L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public C0733c f8301M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public C0802c f8302N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f8303O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f8304P;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l3.P f8305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0764s f8306b;
    public final z0 c;
    public final C2486z d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0800a f8307e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0800a f8308f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0768u f8309g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C0742g0 f8311i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8312j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8313k;
    public int l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f8315n;
    public C2474n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f8316p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8317q;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C0420a f8321u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f8322v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f8324x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f8326z;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final A7.D f8310h = new A7.D(2, false);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0171v f8314m = new C0171v();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f8318r = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0171v f8319s = new C0171v();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public InterfaceC0744h0 f8320t = b0.e.o;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0171v f8323w = new C0171v();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f8325y = -1;

    public C0761q(l3.P p10, AbstractC0764s abstractC0764s, z0 z0Var, C2486z c2486z, C0800a c0800a, C0800a c0800a2, C0768u c0768u) {
        this.f8305a = p10;
        this.f8306b = abstractC0764s;
        this.c = z0Var;
        this.d = c2486z;
        this.f8307e = c0800a;
        this.f8308f = c0800a2;
        this.f8309g = c0768u;
        this.B = abstractC0764s.e() || abstractC0764s.c();
        this.f8291C = new C0759p(this, 0);
        this.f8292D = new A7.D(2, false);
        y0 y0VarQ = z0Var.q();
        y0VarQ.c();
        this.f8294F = y0VarQ;
        z0 z0Var2 = new z0();
        if (abstractC0764s.e()) {
            z0Var2.l();
        }
        if (abstractC0764s.c()) {
            z0Var2.f8407u = new C2476p();
        }
        this.f8295G = z0Var2;
        B0 b0R = z0Var2.r();
        b0R.e(true);
        this.f8296H = b0R;
        this.f8300L = new C0801b(this, c0800a);
        y0 y0VarQ2 = this.f8295G.q();
        try {
            C0733c c0733cA = y0VarQ2.a(0);
            y0VarQ2.c();
            this.f8301M = c0733cA;
            this.f8302N = new C0802c();
        } catch (Throwable th) {
            y0VarQ2.c();
            throw th;
        }
    }

    public static final int H(C0761q c0761q, int i7, boolean z6, int i10) {
        y0 y0Var = c0761q.f8294F;
        int[] iArr = y0Var.f8390b;
        int i11 = i7 * 5;
        if (!((iArr[i11 + 1] & 134217728) != 0)) {
            if (!C0735d.j(iArr, i7)) {
                if (C0735d.o(iArr, i7)) {
                    return 1;
                }
                return C0735d.q(iArr, i7);
            }
            int i12 = iArr[i11 + 3] + i7;
            int iH = 0;
            for (int i13 = i7 + 1; i13 < i12; i13 += iArr[(i13 * 5) + 3]) {
                boolean zO = C0735d.o(iArr, i13);
                C0801b c0801b = c0761q.f8300L;
                if (zO) {
                    c0801b.c();
                    Object objI = y0Var.i(i13);
                    c0801b.c();
                    c0801b.f8948h.l.add(objI);
                }
                iH += H(c0761q, i13, zO || z6, zO ? 0 : i10 + iH);
                if (zO) {
                    c0801b.c();
                    c0801b.a();
                }
            }
            if (C0735d.o(iArr, i7)) {
                return 1;
            }
            return iH;
        }
        int i14 = iArr[i11];
        Object objJ = y0Var.j(iArr, i7);
        if (i14 != 206 || !A7.m.a(objJ, C0735d.f8239e)) {
            if (C0735d.o(iArr, i7)) {
                return 1;
            }
            return C0735d.q(iArr, i7);
        }
        Object objG = y0Var.g(i7, 0);
        C0755n c0755n = objG instanceof C0755n ? (C0755n) objG : null;
        if (c0755n != null) {
            for (C0761q c0761q2 : c0755n.l.f8283e) {
                C0801b c0801b2 = c0761q2.f8300L;
                z0 z0Var = c0761q2.c;
                if (z0Var.f8400m > 0 && C0735d.j(z0Var.l, 0)) {
                    C0800a c0800a = new C0800a();
                    c0761q2.f8299K = c0800a;
                    y0 y0VarQ = z0Var.q();
                    try {
                        c0761q2.f8294F = y0VarQ;
                        C0800a c0800a2 = c0801b2.f8944b;
                        try {
                            c0801b2.f8944b = c0800a;
                            H(c0761q2, 0, false, 0);
                            c0801b2.c();
                            c0801b2.b();
                            if (c0801b2.c) {
                                C0800a c0800a3 = c0801b2.f8944b;
                                c0800a3.getClass();
                                c0800a3.d.V(U.w.c);
                                if (c0801b2.c) {
                                    c0801b2.d(false);
                                    c0801b2.d(false);
                                    C0800a c0800a4 = c0801b2.f8944b;
                                    c0800a4.getClass();
                                    c0800a4.d.V(U.i.c);
                                    c0801b2.c = false;
                                }
                            }
                            c0801b2.f8944b = c0800a2;
                        } catch (Throwable th) {
                            c0801b2.f8944b = c0800a2;
                            throw th;
                        }
                    } finally {
                        y0VarQ.c();
                    }
                }
                c0761q.f8306b.l(c0761q2.f8309g);
            }
        }
        return C0735d.q(iArr, i7);
    }

    public final int A(int i7) {
        int iR = C0735d.r(this.f8294F.f8390b, i7) + 1;
        int i10 = 0;
        while (iR < i7) {
            if (!C0735d.n(this.f8294F.f8390b, iR)) {
                i10++;
            }
            iR += C0735d.l(this.f8294F.f8390b, iR);
        }
        return i10;
    }

    public final boolean B(C0420a c0420a) {
        U.D d = this.f8307e.d;
        if (!d.S()) {
            C0735d.y("Expected applyChanges() to have been called");
            throw null;
        }
        if (((C2484x) c0420a.l).f22645e <= 0 && this.f8318r.isEmpty()) {
            return false;
        }
        n(c0420a, null);
        return d.T();
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0212  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C() {
        /*
            Method dump skipped, instruction units count: 786
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.C():void");
    }

    public final void D() {
        H(this, this.f8294F.f8393g, false, 0);
        C0801b c0801b = this.f8300L;
        c0801b.c();
        c0801b.d(false);
        C0761q c0761q = c0801b.f8943a;
        y0 y0Var = c0761q.f8294F;
        if (y0Var.c > 0) {
            int i7 = y0Var.f8395i;
            C0171v c0171v = c0801b.d;
            int i10 = c0171v.f1706b;
            if ((i10 > 0 ? c0171v.f1705a[i10 - 1] : -2) != i7) {
                if (!c0801b.c && c0801b.f8945e) {
                    c0801b.d(false);
                    C0800a c0800a = c0801b.f8944b;
                    c0800a.getClass();
                    c0800a.d.V(U.l.c);
                    c0801b.c = true;
                }
                if (i7 > 0) {
                    C0733c c0733cA = y0Var.a(i7);
                    c0171v.b(i7);
                    c0801b.d(false);
                    C0800a c0800a2 = c0801b.f8944b;
                    c0800a2.getClass();
                    U.k kVar = U.k.c;
                    U.D d = c0800a2.d;
                    d.W(kVar);
                    com.bumptech.glide.c.C0(d, 0, c0733cA);
                    int i11 = d.f8941j;
                    int i12 = kVar.f8934a;
                    int iP = U.D.P(d, i12);
                    int i13 = kVar.f8935b;
                    if (i11 != iP || d.f8942k != U.D.P(d, i13)) {
                        StringBuilder sb = new StringBuilder();
                        int i14 = 0;
                        for (int i15 = 0; i15 < i12; i15++) {
                            if (((1 << i15) & d.f8941j) != 0) {
                                if (i14 > 0) {
                                    sb.append(", ");
                                }
                                sb.append(kVar.b(i15));
                                i14++;
                            }
                        }
                        String string = sb.toString();
                        StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
                        int i16 = 0;
                        for (int i17 = 0; i17 < i13; i17++) {
                            if (((1 << i17) & d.f8942k) != 0) {
                                if (i14 > 0) {
                                    sbK.append(", ");
                                }
                                sbK.append(kVar.c(i17));
                                i16++;
                            }
                        }
                        String string2 = sbK.toString();
                        A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
                        StringBuilder sb2 = new StringBuilder("Error while pushing ");
                        sb2.append(kVar);
                        sb2.append(". Not all arguments were provided. Missing ");
                        M5.b.p(sb2, i14, " int arguments (", string, ") and ");
                        M5.b.t(sb2, i16, " object arguments (", string2, ").");
                        throw null;
                    }
                    c0801b.c = true;
                }
            }
        }
        C0800a c0800a3 = c0801b.f8944b;
        c0800a3.getClass();
        c0800a3.d.V(U.s.c);
        int i18 = c0801b.f8946f;
        y0 y0Var2 = c0761q.f8294F;
        c0801b.f8946f = y0Var2.f8390b[(y0Var2.f8393g * 5) + 3] + i18;
    }

    public final void E(InterfaceC0744h0 interfaceC0744h0) {
        C0420a c0420a = this.f8321u;
        if (c0420a == null) {
            c0420a = new C0420a(11);
            this.f8321u = c0420a;
        }
        ((SparseArray) c0420a.l).put(this.f8294F.f8393g, interfaceC0744h0);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0004  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(int r8, int r9, int r10) {
        /*
            r7 = this;
            T.y0 r0 = r7.f8294F
            if (r8 != r9) goto L7
        L4:
            r10 = r8
            goto L71
        L7:
            if (r8 == r10) goto L71
            if (r9 != r10) goto Ld
            goto L71
        Ld:
            int[] r1 = r0.f8390b
            int r2 = r8 * 5
            int r2 = r2 + 2
            r2 = r1[r2]
            if (r2 != r9) goto L1a
            r10 = r9
            goto L71
        L1a:
            int r3 = r9 * 5
            int r3 = r3 + 2
            r3 = r1[r3]
            if (r3 != r8) goto L23
            goto L4
        L23:
            if (r2 != r3) goto L27
            r10 = r2
            goto L71
        L27:
            r2 = 0
            r3 = r8
            r4 = r2
        L2a:
            int[] r5 = r0.f8390b
            if (r3 <= 0) goto L37
            if (r3 == r10) goto L37
            int r3 = T.C0735d.r(r5, r3)
            int r4 = r4 + 1
            goto L2a
        L37:
            r3 = r9
            r6 = r2
        L39:
            if (r3 <= 0) goto L44
            if (r3 == r10) goto L44
            int r3 = T.C0735d.r(r5, r3)
            int r6 = r6 + 1
            goto L39
        L44:
            int r10 = r4 - r6
            r5 = r8
            r3 = r2
        L48:
            if (r3 >= r10) goto L53
            int r5 = r5 * 5
            int r5 = r5 + 2
            r5 = r1[r5]
            int r3 = r3 + 1
            goto L48
        L53:
            int r6 = r6 - r4
            r10 = r9
        L55:
            if (r2 >= r6) goto L60
            int r10 = r10 * 5
            int r10 = r10 + 2
            r10 = r1[r10]
            int r2 = r2 + 1
            goto L55
        L60:
            r2 = r10
            r10 = r5
        L62:
            if (r10 == r2) goto L71
            int r10 = r10 * 5
            int r10 = r10 + 2
            r10 = r1[r10]
            int r2 = r2 * 5
            int r2 = r2 + 2
            r2 = r1[r2]
            goto L62
        L71:
            if (r8 <= 0) goto L8b
            if (r8 == r10) goto L8b
            int[] r1 = r0.f8390b
            boolean r1 = T.C0735d.o(r1, r8)
            if (r1 == 0) goto L82
            U.b r1 = r7.f8300L
            r1.a()
        L82:
            int[] r1 = r0.f8390b
            int r8 = r8 * 5
            int r8 = r8 + 2
            r8 = r1[r8]
            goto L71
        L8b:
            r7.o(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.F(int, int, int):void");
    }

    public final Object G() {
        boolean z6 = this.f8303O;
        S s6 = C0753m.f8270a;
        if (!z6) {
            Object objH = this.f8294F.h();
            return (!this.f8324x || (objH instanceof C0755n)) ? objH instanceof v0 ? ((v0) objH).f8385a : objH : s6;
        }
        if (!this.f8317q) {
            return s6;
        }
        C0735d.y("A call to createNode(), emitNode() or useNode() expected");
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I() {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.I():void");
    }

    public final void J() {
        y0 y0Var = this.f8294F;
        int i7 = y0Var.f8395i;
        this.f8313k = i7 >= 0 ? C0735d.q(y0Var.f8390b, i7) : 0;
        this.f8294F.m();
    }

    public final void K() {
        if (this.f8313k != 0) {
            C0735d.y("No nodes can be emitted before calling skipAndEndGroup");
            throw null;
        }
        C0754m0 c0754m0V = v();
        if (c0754m0V != null) {
            c0754m0V.f8271a |= 16;
        }
        if (this.f8318r.isEmpty()) {
            J();
        } else {
            C();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void L(java.lang.Object r27, int r28, java.lang.Object r29, int r30) {
        /*
            Method dump skipped, instruction units count: 1172
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.L(java.lang.Object, int, java.lang.Object, int):void");
    }

    public final void M() {
        L(null, -127, null, 0);
    }

    public final void N(int i7, Y y10) {
        L(y10, i7, null, 0);
    }

    public final void O(int i7, Object obj) {
        L(obj, i7, null, 0);
    }

    public final void P(Object obj, boolean z6) {
        if (z6) {
            y0 y0Var = this.f8294F;
            if (y0Var.f8397k <= 0) {
                if (C0735d.o(y0Var.f8390b, y0Var.f8393g)) {
                    y0Var.n();
                    return;
                } else {
                    C0735d.X("Expected a node group");
                    throw null;
                }
            }
            return;
        }
        if (obj != null && this.f8294F.e() != obj) {
            C0801b c0801b = this.f8300L;
            c0801b.getClass();
            c0801b.d(false);
            C0800a c0800a = c0801b.f8944b;
            c0800a.getClass();
            U.y yVar = U.y.c;
            U.D d = c0800a.d;
            d.W(yVar);
            com.bumptech.glide.c.C0(d, 0, obj);
            int i7 = d.f8941j;
            int i10 = yVar.f8934a;
            int iP = U.D.P(d, i10);
            int i11 = yVar.f8935b;
            if (i7 != iP || d.f8942k != U.D.P(d, i11)) {
                StringBuilder sb = new StringBuilder();
                int i12 = 0;
                for (int i13 = 0; i13 < i10; i13++) {
                    if (((1 << i13) & d.f8941j) != 0) {
                        if (i12 > 0) {
                            sb.append(", ");
                        }
                        sb.append(yVar.b(i13));
                        i12++;
                    }
                }
                String string = sb.toString();
                StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
                int i14 = 0;
                for (int i15 = 0; i15 < i11; i15++) {
                    if (((1 << i15) & d.f8942k) != 0) {
                        if (i12 > 0) {
                            sbK.append(", ");
                        }
                        sbK.append(yVar.c(i15));
                        i14++;
                    }
                }
                String string2 = sbK.toString();
                A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
                StringBuilder sb2 = new StringBuilder("Error while pushing ");
                sb2.append(yVar);
                sb2.append(". Not all arguments were provided. Missing ");
                M5.b.p(sb2, i12, " int arguments (", string, ") and ");
                M5.b.t(sb2, i14, " object arguments (", string2, ").");
                throw null;
            }
        }
        this.f8294F.n();
    }

    public final void Q(int i7) {
        int i10;
        int i11;
        if (this.f8311i != null) {
            L(null, i7, null, 0);
            return;
        }
        if (this.f8317q) {
            C0735d.y("A call to createNode(), emitNode() or useNode() expected");
            throw null;
        }
        this.f8304P = this.l ^ Integer.rotateLeft(Integer.rotateLeft(this.f8304P, 3) ^ i7, 3);
        this.l++;
        y0 y0Var = this.f8294F;
        boolean z6 = this.f8303O;
        S s6 = C0753m.f8270a;
        if (z6) {
            y0Var.f8397k++;
            this.f8296H.I(i7, s6, s6, false);
            t(false, null);
            return;
        }
        if (y0Var.f() == i7 && ((i11 = y0Var.f8393g) >= y0Var.f8394h || !C0735d.n(y0Var.f8390b, i11))) {
            y0Var.n();
            t(false, null);
            return;
        }
        if (y0Var.f8397k <= 0 && (i10 = y0Var.f8393g) != y0Var.f8394h) {
            int i12 = this.f8312j;
            D();
            this.f8300L.e(i12, y0Var.l());
            C0735d.s(this.f8318r, i10, y0Var.f8393g);
        }
        y0Var.f8397k++;
        this.f8303O = true;
        this.f8298J = null;
        if (this.f8296H.f8175w) {
            B0 b0R = this.f8295G.r();
            this.f8296H = b0R;
            b0R.D();
            this.f8297I = false;
            this.f8298J = null;
        }
        B0 b02 = this.f8296H;
        b02.d();
        int i13 = b02.f8172t;
        b02.I(i7, s6, s6, false);
        this.f8301M = b02.b(i13);
        t(false, null);
    }

    public final void R(int i7) {
        L(null, i7, null, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T.C0761q S(int r5) {
        /*
            r4 = this;
            r4.Q(r5)
            boolean r5 = r4.f8303O
            T.u r0 = r4.f8309g
            A7.D r1 = r4.f8292D
            if (r5 == 0) goto L23
            T.m0 r5 = new T.m0
            r5.<init>(r0)
            java.util.ArrayList r0 = r1.l
            r0.add(r5)
            r4.b0(r5)
            int r0 = r4.f8290A
            r5.f8273e = r0
            int r0 = r5.f8271a
            r0 = r0 & (-17)
            r5.f8271a = r0
            goto L85
        L23:
            java.util.ArrayList r5 = r4.f8318r
            T.y0 r2 = r4.f8294F
            int r2 = r2.f8395i
            int r2 = T.C0735d.H(r2, r5)
            if (r2 < 0) goto L36
            java.lang.Object r5 = r5.remove(r2)
            T.L r5 = (T.L) r5
            goto L37
        L36:
            r5 = 0
        L37:
            T.y0 r2 = r4.f8294F
            java.lang.Object r2 = r2.h()
            T.S r3 = T.C0753m.f8270a
            boolean r3 = A7.m.a(r2, r3)
            if (r3 == 0) goto L4e
            T.m0 r2 = new T.m0
            r2.<init>(r0)
            r4.b0(r2)
            goto L55
        L4e:
            java.lang.String r0 = "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl"
            A7.m.d(r2, r0)
            T.m0 r2 = (T.C0754m0) r2
        L55:
            if (r5 != 0) goto L70
            int r5 = r2.f8271a
            r0 = r5 & 64
            if (r0 == 0) goto L5f
            r0 = 1
            goto L60
        L5f:
            r0 = 0
        L60:
            if (r0 == 0) goto L66
            r5 = r5 & (-65)
            r2.f8271a = r5
        L66:
            if (r0 == 0) goto L69
            goto L70
        L69:
            int r5 = r2.f8271a
            r5 = r5 & (-9)
            r2.f8271a = r5
            goto L76
        L70:
            int r5 = r2.f8271a
            r5 = r5 | 8
            r2.f8271a = r5
        L76:
            java.util.ArrayList r5 = r1.l
            r5.add(r2)
            int r5 = r4.f8290A
            r2.f8273e = r5
            int r5 = r2.f8271a
            r5 = r5 & (-17)
            r2.f8271a = r5
        L85:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.S(int):T.q");
    }

    public final void T(Object obj) {
        if (!this.f8303O && this.f8294F.f() == 207 && !A7.m.a(this.f8294F.e(), obj) && this.f8325y < 0) {
            this.f8325y = this.f8294F.f8393g;
            this.f8324x = true;
        }
        L(null, 207, obj, 0);
    }

    public final void U() {
        L(null, 125, null, 2);
        this.f8317q = true;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void V() {
        this.l = 0;
        z0 z0Var = this.c;
        this.f8294F = z0Var.q();
        L(null, 100, null, 0);
        AbstractC0764s abstractC0764s = this.f8306b;
        abstractC0764s.m();
        this.f8320t = abstractC0764s.f();
        this.f8323w.b(this.f8322v ? 1 : 0);
        this.f8322v = f(this.f8320t);
        this.f8298J = null;
        if (!this.f8316p) {
            this.f8316p = abstractC0764s.d();
        }
        if (!this.B) {
            this.B = abstractC0764s.e();
        }
        Set set = (Set) C0735d.P(this.f8320t, AbstractC1245b.f14529a);
        if (set != null) {
            set.add(z0Var);
            abstractC0764s.j(set);
        }
        L(null, abstractC0764s.g(), null, 0);
    }

    public final boolean W(C0754m0 c0754m0, Object obj) {
        C0733c c0733c = c0754m0.c;
        if (c0733c == null) {
            return false;
        }
        int iD = this.f8294F.f8389a.d(c0733c);
        if (!this.f8293E || iD < this.f8294F.f8393g) {
            return false;
        }
        ArrayList arrayList = this.f8318r;
        int iH = C0735d.H(iD, arrayList);
        if (iH < 0) {
            int i7 = -(iH + 1);
            if (!(obj instanceof D)) {
                obj = null;
            }
            arrayList.add(i7, new L(c0754m0, iD, obj));
        } else {
            L l = (L) arrayList.get(iH);
            if (obj instanceof D) {
                Object obj2 = l.c;
                if (obj2 == null) {
                    l.c = obj;
                } else if (obj2 instanceof C2453A) {
                    ((C2453A) obj2).a(obj);
                } else {
                    int i10 = AbstractC2457E.f22585a;
                    C2453A c2453a = new C2453A(2);
                    c2453a.f22580b[c2453a.d(obj2)] = obj2;
                    c2453a.f22580b[c2453a.d(obj)] = obj;
                    l.c = c2453a;
                }
            } else {
                l.c = null;
            }
        }
        return true;
    }

    public final void X(int i7, int i10) {
        if (c0(i7) != i10) {
            if (i7 < 0) {
                C2474n c2474n = this.o;
                if (c2474n == null) {
                    c2474n = new C2474n();
                    this.o = c2474n;
                }
                c2474n.g(i7, i10);
                return;
            }
            int[] iArr = this.f8315n;
            if (iArr == null) {
                int i11 = this.f8294F.c;
                int[] iArr2 = new int[i11];
                Arrays.fill(iArr2, 0, i11, -1);
                this.f8315n = iArr2;
                iArr = iArr2;
            }
            iArr[i7] = i10;
        }
    }

    public final void Y(int i7, int i10) {
        int iC0 = c0(i7);
        if (iC0 != i10) {
            int i11 = i10 - iC0;
            A7.D d = this.f8310h;
            int size = d.l.size() - 1;
            while (i7 != -1) {
                int iC02 = c0(i7) + i11;
                X(i7, iC02);
                int i12 = size;
                while (true) {
                    if (-1 < i12) {
                        C0742g0 c0742g0 = (C0742g0) d.l.get(i12);
                        if (c0742g0 != null && c0742g0.a(i7, iC02)) {
                            size = i12 - 1;
                            break;
                        }
                        i12--;
                    } else {
                        break;
                    }
                }
                if (i7 < 0) {
                    i7 = this.f8294F.f8395i;
                } else if (C0735d.o(this.f8294F.f8390b, i7)) {
                    return;
                } else {
                    i7 = C0735d.r(this.f8294F.f8390b, i7);
                }
            }
        }
    }

    public final b0.e Z(InterfaceC0744h0 interfaceC0744h0, b0.e eVar) {
        b0.e eVar2 = (b0.e) interfaceC0744h0;
        eVar2.getClass();
        b0.d dVar = new b0.d(eVar2);
        dVar.f12194r = eVar2;
        dVar.putAll(eVar);
        b0.e eVarA = dVar.a();
        N(204, C0735d.d);
        z();
        b0(eVarA);
        z();
        b0(eVar);
        p(false);
        return eVarA;
    }

    public final void a() {
        i();
        this.f8310h.l.clear();
        this.f8314m.f1706b = 0;
        this.f8319s.f1706b = 0;
        this.f8323w.f1706b = 0;
        this.f8321u = null;
        C0802c c0802c = this.f8302N;
        c0802c.f8952e.Q();
        c0802c.d.Q();
        this.f8304P = 0;
        this.f8326z = 0;
        this.f8317q = false;
        this.f8303O = false;
        this.f8324x = false;
        this.f8293E = false;
        this.f8325y = -1;
        y0 y0Var = this.f8294F;
        if (!y0Var.f8392f) {
            y0Var.c();
        }
        if (this.f8296H.f8175w) {
            return;
        }
        u();
    }

    public final void a0(Object obj) {
        int i7;
        y0 y0Var;
        int i10;
        B0 b02;
        if (obj instanceof InterfaceC0769u0) {
            C0733c c0733cA = null;
            if (this.f8303O) {
                C0800a c0800a = this.f8300L.f8944b;
                c0800a.getClass();
                U.r rVar = U.r.c;
                U.D d = c0800a.d;
                d.W(rVar);
                com.bumptech.glide.c.C0(d, 0, (InterfaceC0769u0) obj);
                int i11 = d.f8941j;
                int i12 = rVar.f8934a;
                int iP = U.D.P(d, i12);
                int i13 = rVar.f8935b;
                if (i11 != iP || d.f8942k != U.D.P(d, i13)) {
                    StringBuilder sb = new StringBuilder();
                    int i14 = 0;
                    for (int i15 = 0; i15 < i12; i15++) {
                        if (((1 << i15) & d.f8941j) != 0) {
                            if (i14 > 0) {
                                sb.append(", ");
                            }
                            sb.append(rVar.b(i15));
                            i14++;
                        }
                    }
                    String string = sb.toString();
                    StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
                    int i16 = 0;
                    for (int i17 = 0; i17 < i13; i17++) {
                        if (((1 << i17) & d.f8942k) != 0) {
                            if (i14 > 0) {
                                sbK.append(", ");
                            }
                            sbK.append(rVar.c(i17));
                            i16++;
                        }
                    }
                    String string2 = sbK.toString();
                    A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
                    StringBuilder sb2 = new StringBuilder("Error while pushing ");
                    sb2.append(rVar);
                    sb2.append(". Not all arguments were provided. Missing ");
                    M5.b.p(sb2, i14, " int arguments (", string, ") and ");
                    M5.b.t(sb2, i16, " object arguments (", string2, ").");
                    throw null;
                }
            }
            this.d.add(obj);
            InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) obj;
            if (this.f8303O) {
                B0 b03 = this.f8296H;
                int i18 = b03.f8172t;
                if (i18 > b03.f8174v + 1) {
                    int i19 = i18 - 1;
                    int iX = b03.x(b03.f8158b, i19);
                    while (true) {
                        i10 = i19;
                        i19 = iX;
                        b02 = this.f8296H;
                        if (i19 == b02.f8174v || i19 < 0) {
                            break;
                        } else {
                            iX = b02.x(b02.f8158b, i19);
                        }
                    }
                    c0733cA = b02.b(i10);
                }
            } else {
                y0 y0Var2 = this.f8294F;
                int i20 = y0Var2.f8393g;
                if (i20 > y0Var2.f8395i + 1) {
                    int i21 = i20 - 1;
                    int i22 = y0Var2.f8390b[(i21 * 5) + 2];
                    while (true) {
                        i7 = i21;
                        i21 = i22;
                        y0Var = this.f8294F;
                        if (i21 == y0Var.f8395i || i21 < 0) {
                            break;
                        } else {
                            i22 = y0Var.f8390b[(i21 * 5) + 2];
                        }
                    }
                    c0733cA = y0Var.a(i7);
                }
            }
            v0 v0Var = new v0();
            v0Var.f8385a = interfaceC0769u0;
            v0Var.f8386b = c0733cA;
            obj = v0Var;
        }
        b0(obj);
    }

    public final void b(Object obj, InterfaceC2785n interfaceC2785n) {
        int i7 = 1;
        int i10 = 0;
        if (this.f8303O) {
            C0802c c0802c = this.f8302N;
            c0802c.getClass();
            U.z zVar = U.z.c;
            U.D d = c0802c.d;
            d.W(zVar);
            com.bumptech.glide.c.C0(d, 0, obj);
            A7.m.d(interfaceC2785n, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
            A7.E.e(2, interfaceC2785n);
            com.bumptech.glide.c.C0(d, 1, interfaceC2785n);
            int i11 = d.f8941j;
            int i12 = zVar.f8934a;
            int iP = U.D.P(d, i12);
            int i13 = zVar.f8935b;
            if (i11 == iP && d.f8942k == U.D.P(d, i13)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i14 = 0;
            while (i14 < i12) {
                if (((i7 << i14) & d.f8941j) != 0) {
                    if (i10 > 0) {
                        sb.append(", ");
                    }
                    sb.append(zVar.b(i14));
                    i10++;
                }
                i14++;
                i7 = 1;
            }
            String string = sb.toString();
            StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
            int i15 = 0;
            int i16 = 0;
            while (i15 < i13) {
                int i17 = i13;
                if (((1 << i15) & d.f8942k) != 0) {
                    if (i10 > 0) {
                        sbK.append(", ");
                    }
                    sbK.append(zVar.c(i15));
                    i16++;
                }
                i15++;
                i13 = i17;
            }
            String string2 = sbK.toString();
            A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder("Error while pushing ");
            sb2.append(zVar);
            sb2.append(". Not all arguments were provided. Missing ");
            M5.b.p(sb2, i10, " int arguments (", string, ") and ");
            M5.b.t(sb2, i16, " object arguments (", string2, ").");
            throw null;
        }
        C0801b c0801b = this.f8300L;
        c0801b.b();
        C0800a c0800a = c0801b.f8944b;
        c0800a.getClass();
        U.z zVar2 = U.z.c;
        U.D d10 = c0800a.d;
        d10.W(zVar2);
        com.bumptech.glide.c.C0(d10, 0, obj);
        A7.m.d(interfaceC2785n, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        A7.E.e(2, interfaceC2785n);
        com.bumptech.glide.c.C0(d10, 1, interfaceC2785n);
        int i18 = d10.f8941j;
        int i19 = zVar2.f8934a;
        int iP2 = U.D.P(d10, i19);
        int i20 = zVar2.f8935b;
        if (i18 == iP2 && d10.f8942k == U.D.P(d10, i20)) {
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        int i21 = 0;
        for (int i22 = 0; i22 < i19; i22++) {
            if (((1 << i22) & d10.f8941j) != 0) {
                if (i21 > 0) {
                    sb3.append(", ");
                }
                sb3.append(zVar2.b(i22));
                i21++;
            }
        }
        String string3 = sb3.toString();
        StringBuilder sbK2 = M5.b.k(string3, "StringBuilder().apply(builderAction).toString()");
        int i23 = 0;
        int i24 = 0;
        while (i23 < i20) {
            int i25 = i20;
            if (((1 << i23) & d10.f8942k) != 0) {
                if (i21 > 0) {
                    sbK2.append(", ");
                }
                sbK2.append(zVar2.c(i23));
                i24++;
            }
            i23++;
            i20 = i25;
        }
        String string4 = sbK2.toString();
        A7.m.e(string4, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(zVar2);
        sb4.append(". Not all arguments were provided. Missing ");
        M5.b.p(sb4, i21, " int arguments (", string3, ") and ");
        M5.b.t(sb4, i24, " object arguments (", string4, ").");
        throw null;
    }

    public final void b0(Object obj) {
        int i7;
        int i10;
        if (this.f8303O) {
            B0 b02 = this.f8296H;
            if (b02.f8167n <= 0 || b02.f8163i == b02.f8165k) {
                b02.y(obj);
                return;
            }
            C2476p c2476p = b02.f8171s;
            if (c2476p == null) {
                c2476p = new C2476p();
            }
            b02.f8171s = c2476p;
            int i11 = b02.f8174v;
            Object objF = c2476p.f(i11);
            if (objF == null) {
                objF = new C2482v();
                c2476p.i(i11, objF);
            }
            ((C2482v) objF).a(obj);
            return;
        }
        y0 y0Var = this.f8294F;
        boolean z6 = y0Var.f8399n;
        int i12 = 1;
        C0801b c0801b = this.f8300L;
        int i13 = 0;
        if (!z6) {
            C0733c c0733cA = y0Var.a(y0Var.f8395i);
            C0800a c0800a = c0801b.f8944b;
            c0800a.getClass();
            U.e eVar = U.e.c;
            U.D d = c0800a.d;
            d.W(eVar);
            com.bumptech.glide.c.C0(d, 0, c0733cA);
            com.bumptech.glide.c.C0(d, 1, obj);
            int i14 = d.f8941j;
            int i15 = eVar.f8934a;
            int iP = U.D.P(d, i15);
            int i16 = eVar.f8935b;
            if (i14 == iP && d.f8942k == U.D.P(d, i16)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            int i17 = 0;
            while (i17 < i15) {
                if (((i12 << i17) & d.f8941j) != 0) {
                    if (i13 > 0) {
                        sb.append(", ");
                    }
                    sb.append(eVar.b(i17));
                    i13++;
                }
                i17++;
                i12 = 1;
            }
            String string = sb.toString();
            StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
            int i18 = 0;
            int i19 = 0;
            while (i18 < i16) {
                int i20 = i16;
                if (((1 << i18) & d.f8942k) != 0) {
                    if (i13 > 0) {
                        sbK.append(", ");
                    }
                    sbK.append(eVar.c(i18));
                    i19++;
                }
                i18++;
                i16 = i20;
            }
            String string2 = sbK.toString();
            A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder("Error while pushing ");
            sb2.append(eVar);
            sb2.append(". Not all arguments were provided. Missing ");
            M5.b.p(sb2, i13, " int arguments (", string, ") and ");
            M5.b.t(sb2, i19, " object arguments (", string2, ").");
            throw null;
        }
        int iT = (y0Var.l - C0735d.t(y0Var.f8390b, y0Var.f8395i)) - 1;
        if (c0801b.f8943a.f8294F.f8395i - c0801b.f8946f >= 0) {
            c0801b.d(true);
            C0800a c0800a2 = c0801b.f8944b;
            U.m mVar = U.m.f8955g;
            U.D d10 = c0800a2.d;
            d10.W(mVar);
            com.bumptech.glide.c.C0(d10, 0, obj);
            com.bumptech.glide.c.B0(d10, 0, iT);
            if (d10.f8941j == U.D.P(d10, 1) && d10.f8942k == U.D.P(d10, 1)) {
                return;
            }
            StringBuilder sb3 = new StringBuilder();
            if ((d10.f8941j & 1) != 0) {
                sb3.append(mVar.b(0));
                i7 = 1;
            } else {
                i7 = 0;
            }
            String string3 = sb3.toString();
            StringBuilder sbK2 = M5.b.k(string3, "StringBuilder().apply(builderAction).toString()");
            if ((d10.f8942k & 1) != 0) {
                if (i7 > 0) {
                    sbK2.append(", ");
                }
                sbK2.append(mVar.c(0));
            } else {
                i12 = 0;
            }
            String string4 = sbK2.toString();
            A7.m.e(string4, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb4 = new StringBuilder("Error while pushing ");
            sb4.append(mVar);
            sb4.append(". Not all arguments were provided. Missing ");
            M5.b.p(sb4, i7, " int arguments (", string3, ") and ");
            M5.b.t(sb4, i12, " object arguments (", string4, ").");
            throw null;
        }
        y0 y0Var2 = this.f8294F;
        C0733c c0733cA2 = y0Var2.a(y0Var2.f8395i);
        C0800a c0800a3 = c0801b.f8944b;
        U.m mVar2 = U.m.f8954f;
        U.D d11 = c0800a3.d;
        d11.W(mVar2);
        com.bumptech.glide.c.C0(d11, 0, obj);
        com.bumptech.glide.c.C0(d11, 1, c0733cA2);
        com.bumptech.glide.c.B0(d11, 0, iT);
        if (d11.f8941j == U.D.P(d11, 1) && d11.f8942k == U.D.P(d11, 2)) {
            return;
        }
        StringBuilder sb5 = new StringBuilder();
        if ((d11.f8941j & 1) != 0) {
            sb5.append(mVar2.b(0));
            i10 = 1;
        } else {
            i10 = 0;
        }
        String string5 = sb5.toString();
        StringBuilder sbK3 = M5.b.k(string5, "StringBuilder().apply(builderAction).toString()");
        int i21 = 0;
        for (int i22 = 2; i13 < i22; i22 = 2) {
            if (((1 << i13) & d11.f8942k) != 0) {
                if (i10 > 0) {
                    sbK3.append(", ");
                }
                sbK3.append(mVar2.c(i13));
                i21++;
            }
            i13++;
        }
        String string6 = sbK3.toString();
        A7.m.e(string6, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb6 = new StringBuilder("Error while pushing ");
        sb6.append(mVar2);
        sb6.append(". Not all arguments were provided. Missing ");
        M5.b.p(sb6, i10, " int arguments (", string5, ") and ");
        M5.b.t(sb6, i21, " object arguments (", string6, ").");
        throw null;
    }

    public final boolean c(float f10) {
        Object objZ = z();
        if ((objZ instanceof Float) && f10 == ((Number) objZ).floatValue()) {
            return false;
        }
        b0(Float.valueOf(f10));
        return true;
    }

    public final int c0(int i7) {
        int i10;
        if (i7 >= 0) {
            int[] iArr = this.f8315n;
            return (iArr == null || (i10 = iArr[i7]) < 0) ? C0735d.q(this.f8294F.f8390b, i7) : i10;
        }
        C2474n c2474n = this.o;
        if (c2474n == null || c2474n.c(i7) < 0) {
            return 0;
        }
        return c2474n.d(i7);
    }

    public final boolean d(int i7) {
        Object objZ = z();
        if ((objZ instanceof Integer) && i7 == ((Number) objZ).intValue()) {
            return false;
        }
        b0(Integer.valueOf(i7));
        return true;
    }

    public final void d0() {
        if (!this.f8317q) {
            C0735d.y("A call to createNode(), emitNode() or useNode() expected was not expected");
            throw null;
        }
        this.f8317q = false;
        if (this.f8303O) {
            C0735d.y("useNode() called while inserting");
            throw null;
        }
        y0 y0Var = this.f8294F;
        Object objI = y0Var.i(y0Var.f8395i);
        C0801b c0801b = this.f8300L;
        c0801b.c();
        c0801b.f8948h.l.add(objI);
        if (this.f8324x && (objI instanceof InterfaceC0749k)) {
            c0801b.b();
            C0800a c0800a = c0801b.f8944b;
            c0800a.getClass();
            if (objI instanceof InterfaceC0749k) {
                c0800a.d.V(U.B.c);
            }
        }
    }

    public final boolean e(long j3) {
        Object objZ = z();
        if ((objZ instanceof Long) && j3 == ((Number) objZ).longValue()) {
            return false;
        }
        b0(Long.valueOf(j3));
        return true;
    }

    public final boolean f(Object obj) {
        if (A7.m.a(z(), obj)) {
            return false;
        }
        b0(obj);
        return true;
    }

    public final boolean g(boolean z6) {
        Object objZ = z();
        if ((objZ instanceof Boolean) && z6 == ((Boolean) objZ).booleanValue()) {
            return false;
        }
        b0(Boolean.valueOf(z6));
        return true;
    }

    public final boolean h(Object obj) {
        if (z() == obj) {
            return false;
        }
        b0(obj);
        return true;
    }

    public final void i() {
        this.f8311i = null;
        this.f8312j = 0;
        this.f8313k = 0;
        this.f8304P = 0;
        this.f8317q = false;
        C0801b c0801b = this.f8300L;
        c0801b.c = false;
        c0801b.d.f1706b = 0;
        c0801b.f8946f = 0;
        this.f8292D.l.clear();
        this.f8315n = null;
        this.o = null;
    }

    public final int j(int i7, int i10, int i11, int i12) {
        int iOrdinal;
        Object objB;
        if (i7 == i11) {
            return i12;
        }
        y0 y0Var = this.f8294F;
        boolean zN = C0735d.n(y0Var.f8390b, i7);
        int[] iArr = y0Var.f8390b;
        if (zN) {
            Object objJ = y0Var.j(iArr, i7);
            iOrdinal = objJ != null ? objJ instanceof Enum ? ((Enum) objJ).ordinal() : objJ.hashCode() : 0;
        } else {
            int iHashCode = iArr[i7 * 5];
            if (iHashCode == 207 && (objB = y0Var.b(iArr, i7)) != null && !objB.equals(C0753m.f8270a)) {
                iHashCode = objB.hashCode();
            }
            iOrdinal = iHashCode;
        }
        if (iOrdinal == 126665345) {
            return iOrdinal;
        }
        int i13 = this.f8294F.f8390b[(i7 * 5) + 2];
        if (i13 != i11) {
            i12 = j(i13, A(i13), i11, i12);
        }
        if (C0735d.n(this.f8294F.f8390b, i7)) {
            i10 = 0;
        }
        return Integer.rotateLeft(Integer.rotateLeft(i12, 3) ^ iOrdinal, 3) ^ i10;
    }

    public final Object k(AbstractC0750k0 abstractC0750k0) {
        return C0735d.P(m(), abstractC0750k0);
    }

    public final void l(InterfaceC2772a interfaceC2772a) {
        int i7;
        int i10;
        if (!this.f8317q) {
            C0735d.y("A call to createNode(), emitNode() or useNode() expected was not expected");
            throw null;
        }
        int i11 = 0;
        this.f8317q = false;
        if (!this.f8303O) {
            C0735d.y("createNode() can only be called when inserting");
            throw null;
        }
        C0171v c0171v = this.f8314m;
        int i12 = c0171v.f1705a[c0171v.f1706b - 1];
        B0 b02 = this.f8296H;
        C0733c c0733cB = b02.b(b02.f8174v);
        this.f8313k++;
        C0802c c0802c = this.f8302N;
        U.m mVar = U.m.d;
        U.D d = c0802c.d;
        d.W(mVar);
        com.bumptech.glide.c.C0(d, 0, interfaceC2772a);
        com.bumptech.glide.c.B0(d, 0, i12);
        com.bumptech.glide.c.C0(d, 1, c0733cB);
        if (!(d.f8941j == U.D.P(d, 1) && d.f8942k == U.D.P(d, 2))) {
            StringBuilder sb = new StringBuilder();
            if ((d.f8941j & 1) != 0) {
                sb.append(mVar.b(0));
                i10 = 1;
            } else {
                i10 = 0;
            }
            String string = sb.toString();
            StringBuilder sbK = M5.b.k(string, "StringBuilder().apply(builderAction).toString()");
            int i13 = 0;
            while (i11 < 2) {
                if (((1 << i11) & d.f8942k) != 0) {
                    if (i10 > 0) {
                        sbK.append(", ");
                    }
                    sbK.append(mVar.c(i11));
                    i13++;
                }
                i11++;
            }
            String string2 = sbK.toString();
            A7.m.e(string2, "StringBuilder().apply(builderAction).toString()");
            StringBuilder sb2 = new StringBuilder("Error while pushing ");
            sb2.append(mVar);
            sb2.append(". Not all arguments were provided. Missing ");
            M5.b.p(sb2, i10, " int arguments (", string, ") and ");
            M5.b.t(sb2, i13, " object arguments (", string2, ").");
            throw null;
        }
        U.m mVar2 = U.m.f8953e;
        U.D d10 = c0802c.f8952e;
        d10.W(mVar2);
        com.bumptech.glide.c.B0(d10, 0, i12);
        com.bumptech.glide.c.C0(d10, 0, c0733cB);
        if (d10.f8941j == U.D.P(d10, 1) && d10.f8942k == U.D.P(d10, 1)) {
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        if ((d10.f8941j & 1) != 0) {
            sb3.append(mVar2.b(0));
            i7 = 1;
        } else {
            i7 = 0;
        }
        String string3 = sb3.toString();
        StringBuilder sbK2 = M5.b.k(string3, "StringBuilder().apply(builderAction).toString()");
        if ((d10.f8942k & 1) != 0) {
            if (i7 > 0) {
                sbK2.append(", ");
            }
            sbK2.append(mVar2.c(0));
            i11 = 1;
        }
        String string4 = sbK2.toString();
        A7.m.e(string4, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(mVar2);
        sb4.append(". Not all arguments were provided. Missing ");
        M5.b.p(sb4, i7, " int arguments (", string3, ") and ");
        M5.b.t(sb4, i11, " object arguments (", string4, ").");
        throw null;
    }

    public final InterfaceC0744h0 m() {
        InterfaceC0744h0 interfaceC0744h0;
        InterfaceC0744h0 interfaceC0744h02;
        Object obj;
        Object obj2;
        InterfaceC0744h0 interfaceC0744h03 = this.f8298J;
        if (interfaceC0744h03 != null) {
            return interfaceC0744h03;
        }
        int i7 = this.f8294F.f8395i;
        boolean z6 = this.f8303O;
        Y y10 = C0735d.c;
        if (z6 && this.f8297I) {
            int iX = this.f8296H.f8174v;
            while (iX > 0) {
                B0 b02 = this.f8296H;
                if (b02.f8158b[b02.p(iX) * 5] == 202) {
                    B0 b03 = this.f8296H;
                    int iP = b03.p(iX);
                    if (C0735d.n(b03.f8158b, iP)) {
                        Object[] objArr = b03.c;
                        int[] iArr = b03.f8158b;
                        int i10 = iP * 5;
                        obj = objArr[C0735d.A(iArr[i10 + 1] >> 30) + iArr[i10 + 4]];
                    } else {
                        obj = null;
                    }
                    if (A7.m.a(obj, y10)) {
                        B0 b04 = this.f8296H;
                        int iP2 = b04.p(iX);
                        if (C0735d.m(b04.f8158b, iP2)) {
                            Object[] objArr2 = b04.c;
                            int[] iArr2 = b04.f8158b;
                            obj2 = objArr2[C0735d.A(iArr2[(iP2 * 5) + 1] >> 29) + b04.f(iArr2, iP2)];
                        } else {
                            obj2 = C0753m.f8270a;
                        }
                        A7.m.d(obj2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        InterfaceC0744h0 interfaceC0744h04 = (InterfaceC0744h0) obj2;
                        this.f8298J = interfaceC0744h04;
                        return interfaceC0744h04;
                    }
                }
                B0 b05 = this.f8296H;
                iX = b05.x(b05.f8158b, iX);
            }
        }
        if (this.f8294F.c > 0) {
            while (i7 > 0) {
                y0 y0Var = this.f8294F;
                int i11 = i7 * 5;
                int[] iArr3 = y0Var.f8390b;
                if (iArr3[i11] == 202 && A7.m.a(y0Var.j(iArr3, i7), y10)) {
                    C0420a c0420a = this.f8321u;
                    if (c0420a == null || (interfaceC0744h02 = (InterfaceC0744h0) ((SparseArray) c0420a.l).get(i7)) == null) {
                        y0 y0Var2 = this.f8294F;
                        Object objB = y0Var2.b(y0Var2.f8390b, i7);
                        A7.m.d(objB, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        interfaceC0744h0 = (InterfaceC0744h0) objB;
                    } else {
                        interfaceC0744h0 = interfaceC0744h02;
                    }
                    this.f8298J = interfaceC0744h0;
                    return interfaceC0744h0;
                }
                i7 = this.f8294F.f8390b[i11 + 2];
            }
        }
        InterfaceC0744h0 interfaceC0744h05 = this.f8320t;
        this.f8298J = interfaceC0744h05;
        return interfaceC0744h05;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n(N1.C0420a r21, b0.b r22) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.n(N1.a, b0.b):void");
    }

    public final void o(int i7, int i10) {
        if (i7 <= 0 || i7 == i10) {
            return;
        }
        o(this.f8294F.f8390b[(i7 * 5) + 2], i10);
        if (C0735d.o(this.f8294F.f8390b, i7)) {
            Object objI = this.f8294F.i(i7);
            C0801b c0801b = this.f8300L;
            c0801b.c();
            c0801b.f8948h.l.add(objI);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0623  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x076e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(boolean r43) {
        /*
            Method dump skipped, instruction units count: 2665
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.p(boolean):void");
    }

    public final void q() {
        p(false);
        C0754m0 c0754m0V = v();
        if (c0754m0V != null) {
            int i7 = c0754m0V.f8271a;
            if ((i7 & 1) != 0) {
                c0754m0V.f8271a = i7 | 2;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0088 A[EDGE_INSN: B:78:0x0088->B:32:0x0088 BREAK  A[LOOP:0: B:19:0x0041->B:31:0x0084], EDGE_INSN: B:79:0x0088->B:32:0x0088 BREAK  A[LOOP:0: B:19:0x0041->B:31:0x0084]] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T.C0754m0 r() {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0761q.r():T.m0");
    }

    public final void s() {
        p(false);
        this.f8306b.b();
        p(false);
        C0801b c0801b = this.f8300L;
        if (c0801b.c) {
            c0801b.d(false);
            c0801b.d(false);
            C0800a c0800a = c0801b.f8944b;
            c0800a.getClass();
            c0800a.d.V(U.i.c);
            c0801b.c = false;
        }
        c0801b.b();
        if (!(c0801b.d.f1706b == 0)) {
            C0735d.y("Missed recording an endGroup()");
            throw null;
        }
        if (!this.f8310h.l.isEmpty()) {
            C0735d.y("Start/end imbalance");
            throw null;
        }
        i();
        this.f8294F.c();
        this.f8322v = this.f8323w.a() != 0;
    }

    public final void t(boolean z6, C0742g0 c0742g0) {
        this.f8310h.l.add(this.f8311i);
        this.f8311i = c0742g0;
        int i7 = this.f8313k;
        C0171v c0171v = this.f8314m;
        c0171v.b(i7);
        c0171v.b(this.l);
        c0171v.b(this.f8312j);
        if (z6) {
            this.f8312j = 0;
        }
        this.f8313k = 0;
        this.l = 0;
    }

    public final void u() {
        z0 z0Var = new z0();
        if (this.B) {
            z0Var.l();
        }
        if (this.f8306b.c()) {
            z0Var.f8407u = new C2476p();
        }
        this.f8295G = z0Var;
        B0 b0R = z0Var.r();
        b0R.e(true);
        this.f8296H = b0R;
    }

    public final C0754m0 v() {
        if (this.f8326z == 0) {
            A7.D d = this.f8292D;
            if (!d.l.isEmpty()) {
                return (C0754m0) AbstractC1347d.e(1, d.l);
            }
        }
        return null;
    }

    public final boolean w() {
        C0754m0 c0754m0V;
        return (x() && !this.f8322v && ((c0754m0V = v()) == null || (c0754m0V.f8271a & 4) == 0)) ? false : true;
    }

    public final boolean x() {
        C0754m0 c0754m0V;
        return (this.f8303O || this.f8324x || this.f8322v || (c0754m0V = v()) == null || (c0754m0V.f8271a & 8) != 0) ? false : true;
    }

    public final void y(ArrayList arrayList) {
        C0800a c0800a = this.f8308f;
        C0801b c0801b = this.f8300L;
        C0800a c0800a2 = c0801b.f8944b;
        try {
            c0801b.f8944b = c0800a;
            c0800a.d.V(U.u.c);
            if (arrayList.size() > 0) {
                m7.i iVar = (m7.i) arrayList.get(0);
                V v10 = (V) iVar.l;
                v10.getClass();
                throw null;
            }
            C0800a c0800a3 = c0801b.f8944b;
            c0800a3.getClass();
            c0800a3.d.V(U.j.c);
            c0801b.f8946f = 0;
        } finally {
            c0801b.f8944b = c0800a2;
        }
    }

    public final Object z() {
        boolean z6 = this.f8303O;
        S s6 = C0753m.f8270a;
        if (!z6) {
            Object objH = this.f8294F.h();
            return (!this.f8324x || (objH instanceof C0755n)) ? objH : s6;
        }
        if (!this.f8317q) {
            return s6;
        }
        C0735d.y("A call to createNode(), emitNode() or useNode() expected");
        throw null;
    }
}
