package W8;

import S8.InterfaceC0725w;
import V8.InterfaceC0818g;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f9920m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0818g f9921n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(n nVar, InterfaceC0818g interfaceC0818g, Object obj, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9920m = nVar;
        this.f9921n = interfaceC0818g;
        this.o = obj;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k(this.f9920m, this.f9921n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [s7.i, z7.o] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            ?? r42 = this.f9920m.f9927p;
            this.l = 1;
            if (r42.g(this.f9921n, this.o, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
