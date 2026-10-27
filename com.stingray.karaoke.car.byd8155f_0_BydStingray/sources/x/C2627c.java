package x;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2627c extends s7.i implements InterfaceC2782k {
    public final /* synthetic */ C2629d l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2627c(C2629d c2629d, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.l = c2629d;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) throws Throwable {
        C2627c c2627c = (C2627c) create((InterfaceC2189c) obj);
        m7.q qVar = m7.q.f19267a;
        c2627c.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C2627c(this.l, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C2629d.b(this.l);
        return m7.q.f19267a;
    }
}
