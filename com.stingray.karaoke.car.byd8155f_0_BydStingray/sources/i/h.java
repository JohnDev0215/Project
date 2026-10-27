package i;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends i implements InterfaceC2785n {
    public final /* synthetic */ g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f15647m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(g gVar, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = gVar;
        this.f15647m = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new h(this.l, this.f15647m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        h hVar = (h) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        hVar.invokeSuspend(qVar);
        return qVar;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [A7.j, z7.a] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        t4.e eVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        g gVar = this.l;
        boolean z6 = this.f15647m;
        if (!z6 && gVar.f15131a && (eVar = gVar.f15646f) != null) {
            eVar.e();
        }
        gVar.f15131a = z6;
        ?? r12 = gVar.c;
        if (r12 != 0) {
            r12.a();
        }
        return q.f19267a;
    }
}
