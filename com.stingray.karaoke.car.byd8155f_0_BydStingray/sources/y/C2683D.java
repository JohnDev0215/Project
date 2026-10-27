package y;

import K.C0358p;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: y.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2683D extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2684E f23831m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2683D(C2684E c2684e, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23831m = c2684e;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C2683D(this.f23831m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2683D) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0) {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            return m7.q.f19267a;
        }
        AbstractC1386c.u(obj);
        A7.x xVar = new A7.x();
        A7.x xVar2 = new A7.x();
        A7.x xVar3 = new A7.x();
        C2684E c2684e = this.f23831m;
        V8.S s6 = c2684e.f23833y.f832a;
        C0358p c0358p = new C0358p(xVar, xVar2, xVar3, c2684e, 2);
        this.l = 1;
        s6.b(c0358p, this);
        return enumC2303a;
    }
}
