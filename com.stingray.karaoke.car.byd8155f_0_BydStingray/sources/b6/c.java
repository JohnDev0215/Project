package b6;

import A7.v;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f12230m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(v vVar, f fVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = vVar;
        this.f12230m = fVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new c(this.l, this.f12230m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        c cVar = (c) create((String) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        cVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        v vVar = this.l;
        if (vVar.l) {
            this.f12230m.g();
        }
        vVar.l = true;
        return q.f19267a;
    }
}
