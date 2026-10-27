package Q;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0513j0 extends s7.i implements InterfaceC2786o {
    public /* synthetic */ long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0523o0 f5931m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0513j0(C0523o0 c0523o0, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f5931m = c0523o0;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws Throwable {
        long j3 = ((m0.c) obj2).f18880a;
        C0513j0 c0513j0 = new C0513j0(this.f5931m, (InterfaceC2189c) obj3);
        c0513j0.l = j3;
        m7.q qVar = m7.q.f19267a;
        c0513j0.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        long j3 = this.l;
        C0523o0 c0523o0 = this.f5931m;
        c0523o0.f6014y.j((c0523o0.f6008s ? c0523o0.f6007r.i() - m0.c.d(j3) : m0.c.d(j3)) - c0523o0.f6013x.i());
        return m7.q.f19267a;
    }
}
