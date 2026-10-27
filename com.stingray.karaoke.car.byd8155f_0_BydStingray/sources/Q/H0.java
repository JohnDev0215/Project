package Q;

import S8.AbstractC0727y;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1998m;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class H0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5539m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ArrayList f5540n;
    public final /* synthetic */ C0.g0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5541p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ P f5542q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f5543r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ ArrayList f5544s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ long f5545t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f5546u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f5547v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2786o f5548w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H0(int i7, ArrayList arrayList, C0.g0 g0Var, InterfaceC2785n interfaceC2785n, P p10, int i10, ArrayList arrayList2, long j3, int i11, int i12, InterfaceC2786o interfaceC2786o) {
        super(1);
        this.f5539m = i7;
        this.f5540n = arrayList;
        this.o = g0Var;
        this.f5541p = interfaceC2785n;
        this.f5542q = p10;
        this.f5543r = i10;
        this.f5544s = arrayList2;
        this.f5545t = j3;
        this.f5546u = i11;
        this.f5547v = i12;
        this.f5548w = interfaceC2786o;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C0.g0 g0Var;
        int i7;
        C0.U u4 = (C0.U) obj;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.f5540n;
        int size = arrayList2.size();
        int i10 = this.f5539m;
        int i11 = 0;
        int i12 = i10;
        while (true) {
            g0Var = this.o;
            if (i11 >= size) {
                break;
            }
            C0.V v10 = (C0.V) arrayList2.get(i11);
            C0.U.f(u4, v10, i12, 0);
            arrayList.add(new E0(g0Var.p0(i12), g0Var.p0(v10.l), ((a1.e) this.f5544s.get(i11)).l));
            i12 += v10.l;
            i11++;
        }
        List listW = g0Var.W(L0.f5573m, this.f5541p);
        int size2 = listW.size();
        int i13 = 0;
        while (true) {
            i7 = this.f5547v;
            if (i13 >= size2) {
                break;
            }
            C0.J j3 = (C0.J) listW.get(i13);
            int i14 = this.f5546u;
            C0.V vB = j3.b(a1.a.a(this.f5545t, i14, i14, 0, 0, 8));
            C0.U.f(u4, vB, 0, i7 - vB.f871m);
            i13++;
        }
        List listW2 = g0Var.W(L0.f5574n, new b0.b(1734082948, new A.L(this.f5548w, 10, arrayList), true));
        int size3 = listW2.size();
        for (int i15 = 0; i15 < size3; i15++) {
            C0.J j10 = (C0.J) listW2.get(i15);
            int i16 = this.f5546u;
            if (i16 < 0 || i7 < 0) {
                w0.c.e0("width(" + i16 + ") and height(" + i7 + ") must be >= 0");
                throw null;
            }
            C0.U.f(u4, j10.b(io.sentry.config.a.U(i16, i16, i7, i7)), 0, 0);
        }
        P p10 = this.f5542q;
        Integer num = p10.c;
        int i17 = this.f5543r;
        if (num == null || num.intValue() != i17) {
            p10.c = Integer.valueOf(i17);
            E0 e02 = (E0) AbstractC1998m.b0(i17, arrayList);
            if (e02 != null) {
                E0 e03 = (E0) AbstractC1998m.h0(arrayList);
                int iO = g0Var.O(e03.f5513a + e03.f5514b) + i10;
                y.y0 y0Var = p10.f5630a;
                int i18 = iO - y0Var.d.i();
                int iO2 = g0Var.O(e02.f5513a) - ((i18 / 2) - (g0Var.O(e02.f5514b) / 2));
                int i19 = iO - i18;
                if (i19 < 0) {
                    i19 = 0;
                }
                int iV = AbstractC2673a.v(iO2, 0, i19);
                if (y0Var.f24028a.i() != iV) {
                    AbstractC0727y.w(p10.f5631b, null, null, new O(p10, iV, null), 3);
                }
            }
        }
        return m7.q.f19267a;
    }
}
