package i6;

import A7.v;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: i6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1412a extends i implements InterfaceC2785n {
    public final /* synthetic */ v l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1415d f15754m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1412a(v vVar, C1415d c1415d, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = vVar;
        this.f15754m = c1415d;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1412a(this.l, this.f15754m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        C1412a c1412a = (C1412a) create(bool, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        c1412a.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        v vVar = this.l;
        if (vVar.l) {
            this.f15754m.i(true);
        }
        vVar.l = true;
        return q.f19267a;
    }
}
