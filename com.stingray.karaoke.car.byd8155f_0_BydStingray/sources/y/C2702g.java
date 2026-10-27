package y;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: y.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2702g extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ AbstractC2705j l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2702g(AbstractC2705j abstractC2705j, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = abstractC2705j;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C2702g(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C2702g c2702g = (C2702g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c2702g.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        AbstractC2705j abstractC2705j = this.l;
        if (abstractC2705j.f23936L == null) {
            C.h hVar = new C.h();
            C.k kVar = abstractC2705j.f23926A;
            if (kVar != null) {
                AbstractC0727y.w(abstractC2705j.v0(), null, null, new C2696a(kVar, hVar, null), 3);
            }
            abstractC2705j.f23936L = hVar;
        }
        return m7.q.f19267a;
    }
}
