package F;

import A.InterfaceC0033p0;
import E0.F;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class x extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ y l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1970m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1971n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(y yVar, int i7, int i10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = yVar;
        this.f1970m = i7;
        this.f1971n = i10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new x(this.l, this.f1970m, this.f1971n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        x xVar = (x) create((InterfaceC0033p0) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        xVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        y yVar = this.l;
        E.v vVar = yVar.f1974b;
        int i7 = vVar.f1443b.i();
        int i10 = this.f1971n;
        int i11 = this.f1970m;
        if (i7 != i11 || vVar.c.i() != i10) {
            yVar.f1981k.f();
        }
        vVar.a(i11, i10);
        vVar.f1444e = null;
        F f10 = yVar.f1978h;
        if (f10 != null) {
            f10.l();
        }
        return m7.q.f19267a;
    }
}
