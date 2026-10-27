package N5;

import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4608m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
        this.f4608m = actionOwner;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new J(this.l, this.f4608m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        J j3 = (J) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        j3.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        z6.f4654e.d(z6.b(false), z6.a(null), this.f4608m);
        return m7.q.f19267a;
    }
}
