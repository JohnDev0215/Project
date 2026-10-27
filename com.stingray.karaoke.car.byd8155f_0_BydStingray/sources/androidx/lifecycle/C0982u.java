package androidx.lifecycle;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: androidx.lifecycle.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0982u extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0983v f12009m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0982u(C0983v c0983v, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f12009m = c0983v;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0982u c0982u = new C0982u(this.f12009m, interfaceC2189c);
        c0982u.l = obj;
        return c0982u;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C0982u c0982u = (C0982u) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0982u.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.l;
        C0983v c0983v = this.f12009m;
        A9.b bVar = c0983v.l;
        if (bVar.O0().compareTo(EnumC0981t.f12005m) >= 0) {
            bVar.M0(c0983v);
        } else {
            AbstractC0727y.e(interfaceC0725w.getCoroutineContext(), null);
        }
        return m7.q.f19267a;
    }
}
