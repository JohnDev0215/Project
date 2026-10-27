package X5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import io.sentry.C1489n1;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Q0 extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V0 f10196m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q0(V0 v02, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = str;
        this.f10196m = v02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new Q0(this.f10196m, this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        Q0 q02 = (Q0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        q02.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        String str = this.l;
        if (str != null) {
            V0 v02 = this.f10196m;
            J5.k kVar = v02.f10207b;
            kVar.getClass();
            N0.q qVar = new N0.q();
            qVar.T(str);
            kVar.f3109a.a(qVar.r()).e(new C1489n1(v02, 14, str));
        }
        return m7.q.f19267a;
    }
}
