package y;

import A.p1;
import A.r1;
import S8.AbstractC0727y;
import e0.C1183w;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: y.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2682C extends AbstractC2705j {

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public String f23828S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public InterfaceC2772a f23829T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public InterfaceC2772a f23830U;

    @Override // y.AbstractC2705j
    public final void K0(L0.j jVar) {
        if (this.f23829T != null) {
            String str = this.f23828S;
            C1183w c1183w = new C1183w(this, 17);
            H7.w[] wVarArr = L0.t.f3964a;
            jVar.l(L0.i.c, new L0.a(str, c1183w));
        }
    }

    @Override // y.AbstractC2705j
    public final Object L0(C2722B c2722b, C2704i c2704i) {
        int i7 = 1;
        boolean z6 = this.f23929E;
        InterfaceC2189c interfaceC2189c = null;
        C2681B c2681b = (!z6 || this.f23830U == null) ? null : new C2681B(this, 0);
        C2681B c2681b2 = (!z6 || this.f23829T == null) ? null : new C2681B(this, 1);
        C2718x c2718x = new C2718x(this, interfaceC2189c, i7);
        C2681B c2681b3 = new C2681B(this, 2);
        A.W w10 = r1.f263a;
        Object objH = AbstractC0727y.h(new p1(c2722b, c2718x, c2681b2, c2681b, c2681b3, null), c2704i);
        EnumC2303a enumC2303a = EnumC2303a.l;
        m7.q qVar = m7.q.f19267a;
        if (objH != enumC2303a) {
            objH = qVar;
        }
        return objH == enumC2303a ? objH : qVar;
    }
}
