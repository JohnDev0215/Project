package Q;

import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.C0772x;
import java.util.ArrayList;
import java.util.List;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class I0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5551m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f5552n;
    public final /* synthetic */ b0.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5553p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2786o f5554q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f5555r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f5556s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I0(float f10, b0.b bVar, InterfaceC2785n interfaceC2785n, P p10, int i7, InterfaceC2786o interfaceC2786o) {
        super(2);
        this.f5552n = f10;
        this.o = bVar;
        this.f5553p = interfaceC2785n;
        this.f5556s = p10;
        this.f5555r = i7;
        this.f5554q = interfaceC2786o;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5551m) {
            case 0:
                C0.g0 g0Var = (C0.g0) obj;
                long j3 = ((a1.a) obj2).f11027a;
                int iO = g0Var.O(K0.f5570a);
                int iO2 = g0Var.O(this.f5552n);
                List listW = g0Var.W(L0.l, this.o);
                Integer numValueOf = 0;
                int size = listW.size();
                for (int i7 = 0; i7 < size; i7++) {
                    numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), ((C0.J) listW.get(i7)).c(Integer.MAX_VALUE)));
                }
                int iIntValue = numValueOf.intValue();
                long jA = a1.a.a(j3, iO, 0, iIntValue, iIntValue, 2);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int size2 = listW.size();
                int i10 = 0;
                while (i10 < size2) {
                    C0.J j10 = (C0.J) listW.get(i10);
                    C0.V vB = j10.b(jA);
                    float fP0 = g0Var.p0(Math.min(j10.V(vB.f871m), vB.l)) - (D0.f5510a * 2);
                    arrayList.add(vB);
                    arrayList2.add(new a1.e(fP0));
                    i10++;
                    listW = listW;
                }
                Integer numValueOf2 = Integer.valueOf(iO2 * 2);
                int size3 = arrayList.size();
                for (int i11 = 0; i11 < size3; i11++) {
                    numValueOf2 = Integer.valueOf(numValueOf2.intValue() + ((C0.V) arrayList.get(i11)).l);
                }
                int iIntValue2 = numValueOf2.intValue();
                return g0Var.m0(iIntValue2, iIntValue, n7.v.l, new H0(iO2, arrayList, g0Var, this.f5553p, (P) this.f5556s, this.f5555r, arrayList2, j3, iIntValue2, iIntValue, this.f5554q));
            default:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    Object objG = c0761q.G();
                    T.S s6 = C0753m.f8270a;
                    if (objG == s6) {
                        C0772x c0772x = new C0772x(C0735d.B(c0761q));
                        c0761q.a0(c0772x);
                        objG = c0772x;
                    }
                    X8.c cVar = ((C0772x) objG).l;
                    y.y0 y0Var = (y.y0) this.f5556s;
                    boolean zF = c0761q.f(y0Var) | c0761q.f(cVar);
                    Object objG2 = c0761q.G();
                    if (zF || objG2 == s6) {
                        objG2 = new P(y0Var, cVar);
                        c0761q.a0(objG2);
                    }
                    P p10 = (P) objG2;
                    g0.o oVarN = h6.q.n(L0.k.a(g0.a.b(androidx.compose.foundation.layout.c.s(androidx.compose.foundation.layout.c.d(g0.l.f14684a, 1.0f), g0.b.o, 2), new androidx.compose.foundation.e((y.y0) this.f5556s, false, null, true, false)), false, I.a.f2899m));
                    boolean zC = c0761q.c(this.f5552n);
                    b0.b bVar = this.o;
                    boolean zF2 = zC | c0761q.f(bVar) | c0761q.f(this.f5553p) | c0761q.f(this.f5554q) | c0761q.h(p10) | c0761q.d(this.f5555r);
                    Object objG3 = c0761q.G();
                    if (zF2 || objG3 == s6) {
                        objG3 = new I0(this.f5552n, bVar, this.f5553p, p10, this.f5555r, this.f5554q);
                        c0761q.a0(objG3);
                    }
                    C0.c0.d(oVarN, (InterfaceC2785n) objG3, c0761q, 0, 0);
                }
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I0(y.y0 y0Var, float f10, b0.b bVar, InterfaceC2785n interfaceC2785n, InterfaceC2786o interfaceC2786o, int i7) {
        super(2);
        this.f5556s = y0Var;
        this.f5552n = f10;
        this.o = bVar;
        this.f5553p = interfaceC2785n;
        this.f5554q = interfaceC2786o;
        this.f5555r = i7;
    }
}
