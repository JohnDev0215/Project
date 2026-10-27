package K;

import A.C0022k;
import E0.AbstractC0156f;
import F0.AbstractC0227s0;
import F0.W0;
import android.os.Bundle;
import e1.C1187A;
import java.util.ArrayList;
import java.util.List;
import n0.AbstractC1887p;
import n3.C1930E;
import n3.C1945j;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3366m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3367n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3368p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f3369q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f3370r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(T0.y yVar, M.f fVar, T0.m mVar, C0022k c0022k, C0365x c0365x) {
        super(1);
        this.f3366m = 1;
        this.f3367n = yVar;
        this.o = fVar;
        this.f3368p = mVar;
        this.f3369q = c0022k;
        this.f3370r = c0365x;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        List listSubList;
        m7.q qVar = m7.q.f19267a;
        Object obj2 = this.f3370r;
        Object obj3 = this.f3369q;
        Object obj4 = this.f3367n;
        Object obj5 = this.f3368p;
        Object obj6 = this.o;
        switch (this.f3366m) {
            case 0:
                E0.H h10 = (E0.H) obj;
                h10.a();
                float fI = ((M.n) obj6).f4147b.i();
                if (fI != 0.0f) {
                    int i7 = N0.K.c;
                    int iB = ((T0.s) obj5).b((int) (((T0.y) obj4).f8482b >> 32));
                    G0 g0D = ((C0331b0) obj3).d();
                    m0.d dVarC = g0D != null ? g0D.f3161a.c(iB) : new m0.d(0.0f, 0.0f, 0.0f, 0.0f);
                    float fY = h10.y(r0.f3381a);
                    float f10 = fY / 2;
                    float fR = AbstractC2673a.r(AbstractC2673a.s(dVarC.f18882a + f10, m0.f.d(h10.l.e()) - f10), f10);
                    p0.d.q(h10, (AbstractC1887p) obj2, h6.e.a(fR, dVarC.f18883b), h6.e.a(fR, dVarC.d), fY, fI, 432);
                }
                return qVar;
            case 1:
                M.z zVar = (M.z) obj;
                M.v vVar = ((M.f) obj6).f4137a;
                zVar.f4177h = (T0.y) obj4;
                zVar.f4178i = (T0.m) obj5;
                zVar.c = (C0022k) obj3;
                zVar.d = (C0365x) obj2;
                zVar.f4174e = vVar != null ? vVar.f4156z : null;
                zVar.f4175f = vVar != null ? vVar.f4154A : null;
                zVar.f4176g = vVar != null ? (W0) AbstractC0156f.i(vVar, AbstractC0227s0.f2268q) : null;
                return qVar;
            case 2:
                e1.x xVar = (e1.x) obj6;
                xVar.f13837y.addView(xVar, xVar.f13838z);
                xVar.j((InterfaceC2772a) obj5, (C1187A) obj4, (String) obj3, (a1.k) obj2);
                return new F0.V(xVar, 8);
            default:
                C1945j c1945j = (C1945j) obj;
                A7.m.f(c1945j, "entry");
                ((A7.v) obj6).l = true;
                ArrayList arrayList = (ArrayList) obj5;
                int iIndexOf = arrayList.indexOf(c1945j);
                if (iIndexOf != -1) {
                    A7.x xVar2 = (A7.x) obj4;
                    int i10 = iIndexOf + 1;
                    listSubList = arrayList.subList(xVar2.l, i10);
                    xVar2.l = i10;
                } else {
                    listSubList = n7.u.l;
                }
                ((C1930E) obj3).a(c1945j.f19865m, (Bundle) obj2, c1945j, listSubList);
                return qVar;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p0(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i7) {
        super(1);
        this.f3366m = i7;
        this.o = obj;
        this.f3368p = obj2;
        this.f3367n = obj3;
        this.f3369q = obj4;
        this.f3370r = obj5;
    }
}
