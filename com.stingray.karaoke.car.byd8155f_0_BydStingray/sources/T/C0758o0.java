package T;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0758o0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0758o0 c0758o0 = new C0758o0(2, interfaceC2189c);
        c0758o0.l = obj;
        return c0758o0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0758o0) create((EnumC0756n0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        return Boolean.valueOf(((EnumC0756n0) this.l) == EnumC0756n0.l);
    }
}
