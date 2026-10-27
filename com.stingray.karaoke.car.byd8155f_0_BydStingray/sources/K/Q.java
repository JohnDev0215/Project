package K;

import F0.AbstractC0227s0;
import S0.AbstractC0703o;
import S0.InterfaceC0702n;
import T.C0753m;
import T.C0761q;
import T.Q0;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3196m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3197n;
    public final /* synthetic */ N0.L o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(int i7, int i10, N0.L l) {
        super(3);
        this.f3196m = i7;
        this.f3197n = i10;
        this.o = l;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(408240218);
        int i7 = this.f3196m;
        int i10 = this.f3197n;
        X.w(i7, i10);
        g0.l lVar = g0.l.f14684a;
        if (i7 == 1 && i10 == Integer.MAX_VALUE) {
            c0761q.p(false);
            return lVar;
        }
        a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
        InterfaceC0702n interfaceC0702n = (InterfaceC0702n) c0761q.k(AbstractC0227s0.f2262i);
        a1.k kVar = (a1.k) c0761q.k(AbstractC0227s0.l);
        N0.L l = this.o;
        boolean zF = c0761q.f(l) | c0761q.f(kVar);
        Object objG = c0761q.G();
        T.S s6 = C0753m.f8270a;
        if (zF || objG == s6) {
            objG = P8.n.T(l, kVar);
            c0761q.a0(objG);
        }
        N0.L l10 = (N0.L) objG;
        boolean zF2 = c0761q.f(interfaceC0702n) | c0761q.f(l10);
        Object objG2 = c0761q.G();
        if (zF2 || objG2 == s6) {
            N0.D d = l10.f4358a;
            AbstractC0703o abstractC0703o = d.f4327f;
            S0.A a10 = d.c;
            if (a10 == null) {
                a10 = S0.A.f7800s;
            }
            S0.w wVar = d.d;
            int i11 = wVar != null ? wVar.f7860a : 0;
            S0.x xVar = d.f4326e;
            objG2 = ((S0.q) interfaceC0702n).b(abstractC0703o, a10, i11, xVar != null ? xVar.f7861a : 1);
            c0761q.a0(objG2);
        }
        Q0 q02 = (Q0) objG2;
        boolean zF3 = c0761q.f(q02.getValue()) | c0761q.f(bVar) | c0761q.f(interfaceC0702n) | c0761q.f(l) | c0761q.f(kVar);
        Object objG3 = c0761q.G();
        if (zF3 || objG3 == s6) {
            objG3 = Integer.valueOf((int) (s0.a(l10, bVar, interfaceC0702n, s0.f3382a, 1) & 4294967295L));
            c0761q.a0(objG3);
        }
        int iIntValue = ((Number) objG3).intValue();
        boolean zF4 = c0761q.f(l) | c0761q.f(bVar) | c0761q.f(interfaceC0702n) | c0761q.f(kVar) | c0761q.f(q02.getValue());
        Object objG4 = c0761q.G();
        if (zF4 || objG4 == s6) {
            StringBuilder sb = new StringBuilder();
            String str = s0.f3382a;
            sb.append(str);
            sb.append('\n');
            sb.append(str);
            objG4 = Integer.valueOf((int) (s0.a(l10, bVar, interfaceC0702n, sb.toString(), 2) & 4294967295L));
            c0761q.a0(objG4);
        }
        int iIntValue2 = ((Number) objG4).intValue() - iIntValue;
        Integer numValueOf = i7 == 1 ? null : Integer.valueOf(((i7 - 1) * iIntValue2) + iIntValue);
        Integer numValueOf2 = i10 != Integer.MAX_VALUE ? Integer.valueOf(((i10 - 1) * iIntValue2) + iIntValue) : null;
        g0.o oVarF = androidx.compose.foundation.layout.c.f(lVar, numValueOf != null ? bVar.p0(numValueOf.intValue()) : Float.NaN, numValueOf2 != null ? bVar.p0(numValueOf2.intValue()) : Float.NaN);
        c0761q.p(false);
        return oVarF;
    }
}
