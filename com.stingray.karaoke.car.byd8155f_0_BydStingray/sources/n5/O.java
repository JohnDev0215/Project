package N5;

import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class O extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f4613m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O(Z z6, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
        this.f4613m = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new O(this.l, this.f4613m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        O o = (O) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        o.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.w(this.f4613m, ActionOwner.COMPANION);
        return m7.q.f19267a;
    }
}
