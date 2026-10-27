package H;

import A.C0024l;
import A7.o;
import E0.d0;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i f2758m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ d0 f2759n;
    public final /* synthetic */ o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C0024l f2760p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public h(i iVar, d0 d0Var, InterfaceC2772a interfaceC2772a, C0024l c0024l, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2758m = iVar;
        this.f2759n = d0Var;
        this.o = (o) interfaceC2772a;
        this.f2760p = c0024l;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [A7.o, z7.a] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        ?? r32 = this.o;
        C0024l c0024l = this.f2760p;
        h hVar = new h(this.f2758m, this.f2759n, r32, c0024l, interfaceC2189c);
        hVar.l = obj;
        return hVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((h) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [A7.o, z7.a] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.l;
        d0 d0Var = this.f2759n;
        ?? r22 = this.o;
        i iVar = this.f2758m;
        AbstractC0727y.w(interfaceC0725w, null, null, new f(iVar, d0Var, r22, null), 3);
        return AbstractC0727y.w(interfaceC0725w, null, null, new g(iVar, this.f2760p, null), 3);
    }
}
