package N1;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0442x extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ c0 f4560m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0442x(c0 c0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4560m = c0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0442x c0442x = new C0442x(this.f4560m, interfaceC2189c);
        c0442x.l = obj;
        return c0442x;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0442x) create((c0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        c0 c0Var = (c0) this.l;
        return Boolean.valueOf((c0Var instanceof C0423d) && c0Var.f4515a <= this.f4560m.f4515a);
    }
}
