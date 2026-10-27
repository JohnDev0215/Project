package Q;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0525p0 extends s7.i implements InterfaceC2785n {
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0525p0(2, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0525p0 c0525p0 = (C0525p0) create((C2722B) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0525p0.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        return m7.q.f19267a;
    }
}
