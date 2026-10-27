package y;

import E0.AbstractC0164n;
import E0.InterfaceC0163m;
import S8.AbstractC0727y;
import android.view.KeyEvent;
import e0.C1183w;
import java.util.Iterator;
import java.util.LinkedHashMap;
import l0.EnumC1726q;
import l0.InterfaceC1712c;
import y0.AbstractC2737o;
import y0.C2722B;
import y0.C2730h;
import y0.EnumC2731i;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: y.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2705j extends AbstractC0164n implements E0.n0, w0.d, InterfaceC1712c, E0.p0, E0.s0 {

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final o0 f23925R = new o0(3);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C.k f23926A;
    public e0 B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f23927C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public L0.g f23928D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f23929E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public InterfaceC2772a f23930F;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final C2692M f23932H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public C2722B f23933I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public InterfaceC0163m f23934J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public C.m f23935K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public C.h f23936L;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public C.k f23939O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f23940P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final o0 f23941Q;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final C2688I f23931G = new C2688I();

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final LinkedHashMap f23937M = new LinkedHashMap();

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public long f23938N = 0;

    public AbstractC2705j(C.k kVar, e0 e0Var, boolean z6, String str, L0.g gVar, InterfaceC2772a interfaceC2772a) {
        this.f23926A = kVar;
        this.B = e0Var;
        this.f23927C = str;
        this.f23928D = gVar;
        this.f23929E = z6;
        this.f23930F = interfaceC2772a;
        this.f23932H = new C2692M(kVar);
        C.k kVar2 = this.f23926A;
        this.f23939O = kVar2;
        this.f23940P = kVar2 == null && this.B != null;
        this.f23941Q = f23925R;
    }

    @Override // g0.n
    public final void A0() {
        M0();
        if (this.f23939O == null) {
            this.f23926A = null;
        }
        InterfaceC0163m interfaceC0163m = this.f23934J;
        if (interfaceC0163m != null) {
            I0(interfaceC0163m);
        }
        this.f23934J = null;
    }

    @Override // l0.InterfaceC1712c
    public final void D(EnumC1726q enumC1726q) {
        if (enumC1726q.a()) {
            N0();
        }
        if (this.f23929E) {
            this.f23932H.D(enumC1726q);
        }
    }

    @Override // E0.p0
    public final void H(L0.j jVar) {
        L0.g gVar = this.f23928D;
        if (gVar != null) {
            L0.t.e(jVar, gVar.f3883a);
        }
        String str = this.f23927C;
        C1183w c1183w = new C1183w(this, 16);
        H7.w[] wVarArr = L0.t.f3964a;
        jVar.l(L0.i.f3888b, new L0.a(str, c1183w));
        if (this.f23929E) {
            this.f23932H.H(jVar);
        } else {
            jVar.l(L0.r.f3947i, m7.q.f19267a);
        }
        K0(jVar);
    }

    public void K0(L0.j jVar) {
    }

    public abstract Object L0(C2722B c2722b, C2704i c2704i);

    public final void M0() {
        C.k kVar = this.f23926A;
        LinkedHashMap linkedHashMap = this.f23937M;
        if (kVar != null) {
            C.m mVar = this.f23935K;
            if (mVar != null) {
                kVar.b(new C.l(mVar));
            }
            C.h hVar = this.f23936L;
            if (hVar != null) {
                kVar.b(new C.i(hVar));
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                kVar.b(new C.l((C.m) it.next()));
            }
        }
        this.f23935K = null;
        this.f23936L = null;
        linkedHashMap.clear();
    }

    public final void N0() {
        e0 e0Var;
        if (this.f23934J == null && (e0Var = this.B) != null) {
            if (this.f23926A == null) {
                this.f23926A = new C.k();
            }
            this.f23932H.K0(this.f23926A);
            C.k kVar = this.f23926A;
            A7.m.c(kVar);
            InterfaceC0163m interfaceC0163mB = e0Var.b(kVar);
            H0(interfaceC0163mB);
            this.f23934J = interfaceC0163mB;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void O0(C.k r4, y.e0 r5, boolean r6, java.lang.String r7, L0.g r8, z7.InterfaceC2772a r9) {
        /*
            r3 = this;
            C.k r0 = r3.f23939O
            boolean r0 = A7.m.a(r0, r4)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L13
            r3.M0()
            r3.f23939O = r4
            r3.f23926A = r4
            r4 = r2
            goto L14
        L13:
            r4 = r1
        L14:
            y.e0 r0 = r3.B
            boolean r0 = A7.m.a(r0, r5)
            if (r0 != 0) goto L1f
            r3.B = r5
            r4 = r2
        L1f:
            boolean r5 = r3.f23929E
            y.M r0 = r3.f23932H
            if (r5 == r6) goto L3e
            y.I r5 = r3.f23931G
            if (r6 == 0) goto L30
            r3.H0(r5)
            r3.H0(r0)
            goto L39
        L30:
            r3.I0(r5)
            r3.I0(r0)
            r3.M0()
        L39:
            E0.AbstractC0156f.o(r3)
            r3.f23929E = r6
        L3e:
            java.lang.String r5 = r3.f23927C
            boolean r5 = A7.m.a(r5, r7)
            if (r5 != 0) goto L4b
            r3.f23927C = r7
            E0.AbstractC0156f.o(r3)
        L4b:
            L0.g r5 = r3.f23928D
            boolean r5 = A7.m.a(r5, r8)
            if (r5 != 0) goto L58
            r3.f23928D = r8
            E0.AbstractC0156f.o(r3)
        L58:
            r3.f23930F = r9
            boolean r5 = r3.f23940P
            C.k r6 = r3.f23939O
            if (r6 != 0) goto L66
            y.e0 r7 = r3.B
            if (r7 == 0) goto L66
            r7 = r2
            goto L67
        L66:
            r7 = r1
        L67:
            if (r5 == r7) goto L79
            if (r6 != 0) goto L70
            y.e0 r5 = r3.B
            if (r5 == 0) goto L70
            r1 = r2
        L70:
            r3.f23940P = r1
            if (r1 != 0) goto L79
            E0.m r5 = r3.f23934J
            if (r5 != 0) goto L79
            goto L7a
        L79:
            r2 = r4
        L7a:
            if (r2 == 0) goto L8f
            E0.m r4 = r3.f23934J
            if (r4 != 0) goto L84
            boolean r5 = r3.f23940P
            if (r5 != 0) goto L8f
        L84:
            if (r4 == 0) goto L89
            r3.I0(r4)
        L89:
            r4 = 0
            r3.f23934J = r4
            r3.N0()
        L8f:
            C.k r3 = r3.f23926A
            r0.K0(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: y.AbstractC2705j.O0(C.k, y.e0, boolean, java.lang.String, L0.g, z7.a):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006b  */
    @Override // w0.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean P(android.view.KeyEvent r13) {
        /*
            r12 = this;
            r12.N0()
            boolean r0 = r12.f23929E
            java.util.LinkedHashMap r1 = r12.f23937M
            r2 = 3
            r3 = 160(0xa0, float:2.24E-43)
            r4 = 66
            r5 = 23
            r6 = 32
            r7 = 0
            r8 = 1
            r9 = 0
            if (r0 == 0) goto L6b
            int r0 = y.AbstractC2720z.f24035b
            int r0 = w0.c.S(r13)
            r10 = 2
            boolean r0 = h6.q.p(r0, r10)
            if (r0 == 0) goto L6b
            long r10 = w0.c.R(r13)
            long r10 = r10 >> r6
            int r0 = (int) r10
            if (r0 == r5) goto L2f
            if (r0 == r4) goto L2f
            if (r0 == r3) goto L2f
            goto L6b
        L2f:
            int r0 = r13.getKeyCode()
            long r3 = h6.r.a(r0)
            w0.a r0 = new w0.a
            r0.<init>(r3)
            boolean r0 = r1.containsKey(r0)
            if (r0 != 0) goto Lb3
            C.m r0 = new C.m
            long r3 = r12.f23938N
            r0.<init>(r3)
            int r13 = r13.getKeyCode()
            long r3 = h6.r.a(r13)
            w0.a r13 = new w0.a
            r13.<init>(r3)
            r1.put(r13, r0)
            C.k r13 = r12.f23926A
            if (r13 == 0) goto L69
            S8.w r13 = r12.v0()
            y.e r1 = new y.e
            r1.<init>(r12, r0, r9)
            S8.AbstractC0727y.w(r13, r9, r9, r1, r2)
        L69:
            r7 = r8
            goto Lb3
        L6b:
            boolean r0 = r12.f23929E
            if (r0 == 0) goto Lb3
            int r0 = y.AbstractC2720z.f24035b
            int r0 = w0.c.S(r13)
            boolean r0 = h6.q.p(r0, r8)
            if (r0 == 0) goto Lb3
            long r10 = w0.c.R(r13)
            long r10 = r10 >> r6
            int r0 = (int) r10
            if (r0 == r5) goto L88
            if (r0 == r4) goto L88
            if (r0 == r3) goto L88
            goto Lb3
        L88:
            int r13 = r13.getKeyCode()
            long r3 = h6.r.a(r13)
            w0.a r13 = new w0.a
            r13.<init>(r3)
            java.lang.Object r13 = r1.remove(r13)
            C.m r13 = (C.m) r13
            if (r13 == 0) goto Lad
            C.k r0 = r12.f23926A
            if (r0 == 0) goto Lad
            S8.w r0 = r12.v0()
            y.f r1 = new y.f
            r1.<init>(r12, r13, r9)
            S8.AbstractC0727y.w(r0, r9, r9, r1, r2)
        Lad:
            z7.a r12 = r12.f23930F
            r12.a()
            goto L69
        Lb3:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: y.AbstractC2705j.P(android.view.KeyEvent):boolean");
    }

    @Override // E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        long j10 = ((j3 >> 33) << 32) | (((j3 << 32) >> 33) & 4294967295L);
        this.f23938N = h6.e.a((int) (j10 >> 32), (int) (j10 & 4294967295L));
        N0();
        if (this.f23929E && enumC2731i == EnumC2731i.f24067m) {
            int i7 = c2730h.d;
            if (AbstractC2737o.d(i7, 4)) {
                AbstractC0727y.w(v0(), null, null, new C2702g(this, null), 3);
            } else if (AbstractC2737o.d(i7, 5)) {
                AbstractC0727y.w(v0(), null, null, new C2703h(this, null), 3);
            }
        }
        if (this.f23933I == null) {
            C2704i c2704i = new C2704i(this, null);
            C2730h c2730h2 = y0.v.f24101a;
            C2722B c2722b = new C2722B(null, null, null, c2704i);
            H0(c2722b);
            this.f23933I = c2722b;
        }
        C2722B c2722b2 = this.f23933I;
        if (c2722b2 != null) {
            c2722b2.R(c2730h, enumC2731i, j3);
        }
    }

    @Override // E0.n0
    public final void e0() {
        C.h hVar;
        C.k kVar = this.f23926A;
        if (kVar != null && (hVar = this.f23936L) != null) {
            kVar.b(new C.i(hVar));
        }
        this.f23936L = null;
        C2722B c2722b = this.f23933I;
        if (c2722b != null) {
            c2722b.e0();
        }
    }

    @Override // E0.p0
    public final boolean i0() {
        return true;
    }

    @Override // w0.d
    public final boolean l(KeyEvent keyEvent) {
        return false;
    }

    @Override // E0.s0
    public final Object r() {
        return this.f23941Q;
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }

    @Override // g0.n
    public final void z0() {
        if (!this.f23940P) {
            N0();
        }
        if (this.f23929E) {
            H0(this.f23931G);
            H0(this.f23932H);
        }
    }
}
