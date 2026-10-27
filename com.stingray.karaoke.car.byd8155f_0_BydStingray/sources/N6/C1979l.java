package n6;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import n0.C1878g;
import o6.C2048a;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: n6.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1979l extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ C1878g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2048a f20111m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1878g f20112n;
    public final /* synthetic */ C1878g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1878g f20113p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1979l(C1878g c1878g, C2048a c2048a, C1878g c1878g2, C1878g c1878g3, C1878g c1878g4, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = c1878g;
        this.f20111m = c2048a;
        this.f20112n = c1878g2;
        this.o = c1878g3;
        this.f20113p = c1878g4;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1979l(this.l, this.f20111m, this.f20112n, this.o, this.f20113p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        C1979l c1979l = (C1979l) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c1979l.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C2048a c2048a = this.f20111m;
        this.l.e(c2048a.f20535h);
        this.f20112n.e(c2048a.f20534g);
        this.o.e(c2048a.f20532e);
        this.f20113p.e(c2048a.f20533f);
        return m7.q.f19267a;
    }
}
