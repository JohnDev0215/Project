package E0;

import C0.AbstractC0096c;
import C0.C0107n;
import java.util.HashMap;
import java.util.Map;
import n0.C1863M;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0.V f1494a;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1496e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1497f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1498g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public InterfaceC0151a f1499h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1501j;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1495b = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashMap f1500i = new HashMap();

    /* JADX WARN: Multi-variable type inference failed */
    public G(InterfaceC0151a interfaceC0151a, int i7) {
        this.f1501j = i7;
        this.f1494a = (C0.V) interfaceC0151a;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [A7.j, z7.n] */
    /* JADX WARN: Type inference failed for: r8v5, types: [C0.V, E0.a] */
    public static final void a(G g7, C0107n c0107n, int i7, d0 d0Var) {
        g7.getClass();
        float f10 = i7;
        long jA = h6.e.a(f10, f10);
        while (true) {
            switch (g7.f1501j) {
                case 0:
                    C1863M c1863m = d0.f1628Q;
                    jA = d0Var.i1(jA, true);
                    break;
                default:
                    S sO0 = d0Var.O0();
                    A7.m.c(sO0);
                    long j3 = sO0.f1583x;
                    jA = m0.c.h(h6.e.a((int) (j3 >> 32), (int) (j3 & 4294967295L)), jA);
                    break;
            }
            d0Var = d0Var.f1650y;
            A7.m.c(d0Var);
            if (d0Var.equals(g7.f1494a.n())) {
                int iRound = Math.round(c0107n instanceof C0107n ? m0.c.e(jA) : m0.c.d(jA));
                HashMap map = g7.f1500i;
                if (map.containsKey(c0107n)) {
                    int iIntValue = ((Number) n7.z.o(c0107n, map)).intValue();
                    C0107n c0107n2 = AbstractC0096c.f889a;
                    iRound = ((Number) c0107n.f908a.invoke(Integer.valueOf(iIntValue), Integer.valueOf(iRound))).intValue();
                }
                map.put(c0107n, Integer.valueOf(iRound));
                return;
            }
            if (g7.b(d0Var).containsKey(c0107n)) {
                float fC = g7.c(d0Var, c0107n);
                jA = h6.e.a(fC, fC);
            }
        }
    }

    public final Map b(d0 d0Var) {
        switch (this.f1501j) {
            case 0:
                return d0Var.z0().a();
            default:
                S sO0 = d0Var.O0();
                A7.m.c(sO0);
                return sO0.z0().a();
        }
    }

    public final int c(d0 d0Var, C0107n c0107n) {
        switch (this.f1501j) {
            case 0:
                return d0Var.e0(c0107n);
            default:
                S sO0 = d0Var.O0();
                A7.m.c(sO0);
                return sO0.e0(c0107n);
        }
    }

    public final boolean d() {
        return this.c || this.f1496e || this.f1497f || this.f1498g;
    }

    public final boolean e() {
        h();
        return this.f1499h != null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [C0.V, E0.a] */
    public final void f() {
        this.f1495b = true;
        ?? r02 = this.f1494a;
        InterfaceC0151a interfaceC0151aR = r02.r();
        if (interfaceC0151aR == null) {
            return;
        }
        if (this.c) {
            interfaceC0151aR.R();
        } else if (this.f1496e || this.d) {
            interfaceC0151aR.requestLayout();
        }
        if (this.f1497f) {
            r02.R();
        }
        if (this.f1498g) {
            r02.requestLayout();
        }
        interfaceC0151aR.a().f();
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [C0.V, E0.a] */
    public final void g() {
        HashMap map = this.f1500i;
        map.clear();
        A.F f10 = new A.F(this, 7);
        ?? r22 = this.f1494a;
        r22.l(f10);
        map.putAll(b(r22.n()));
        this.f1495b = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    /* JADX WARN: Type inference failed for: r1v0, types: [C0.V, E0.a] */
    /* JADX WARN: Type inference failed for: r1v1, types: [E0.a] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            r2 = this;
            boolean r0 = r2.d()
            C0.V r1 = r2.f1494a
            if (r0 == 0) goto L9
            goto L51
        L9:
            E0.a r0 = r1.r()
            if (r0 != 0) goto L10
            return
        L10:
            E0.G r0 = r0.a()
            E0.a r1 = r0.f1499h
            if (r1 == 0) goto L23
            E0.G r0 = r1.a()
            boolean r0 = r0.d()
            if (r0 == 0) goto L23
            goto L51
        L23:
            E0.a r0 = r2.f1499h
            if (r0 == 0) goto L53
            E0.G r1 = r0.a()
            boolean r1 = r1.d()
            if (r1 == 0) goto L32
            goto L53
        L32:
            E0.a r1 = r0.r()
            if (r1 == 0) goto L41
            E0.G r1 = r1.a()
            if (r1 == 0) goto L41
            r1.h()
        L41:
            E0.a r0 = r0.r()
            if (r0 == 0) goto L50
            E0.G r0 = r0.a()
            if (r0 == 0) goto L50
            E0.a r1 = r0.f1499h
            goto L51
        L50:
            r1 = 0
        L51:
            r2.f1499h = r1
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.G.h():void");
    }
}
