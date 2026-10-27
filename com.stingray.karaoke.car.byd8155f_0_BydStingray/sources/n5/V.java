package N5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import k3.C1672v;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class V extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new V(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        V v10 = (V) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        v10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        C1672v c1672v = z6.f4667p;
        if (c1672v == null) {
            A7.m.j("mediaController");
            throw null;
        }
        long jS0 = c1672v.s0();
        V8.f0 f0Var = z6.f4630I;
        if (jS0 != -9223372036854775807L) {
            Long l = new Long(jS0);
            f0Var.getClass();
            f0Var.m(null, l);
            z6.f4672u = jS0;
        } else if (!z6.l()) {
            Long l10 = new Long(0L);
            f0Var.getClass();
            f0Var.m(null, l10);
        }
        return m7.q.f19267a;
    }
}
