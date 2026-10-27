package x;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.J, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2621J extends s7.i implements InterfaceC2785n {
    public /* synthetic */ float l;

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2621J c2621j = new C2621J(2, interfaceC2189c);
        c2621j.l = ((Number) obj).floatValue();
        return c2621j;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2621J) create(Float.valueOf(((Number) obj).floatValue()), (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        return Boolean.valueOf(this.l > 0.0f);
    }
}
