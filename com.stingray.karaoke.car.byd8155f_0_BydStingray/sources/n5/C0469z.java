package N5;

import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N5.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0469z extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4780m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0469z(Z z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4780m = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0469z(this.f4780m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0469z) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Z z6 = this.f4780m;
            V8.f0 f0Var = z6.f4636O;
            Object obj2 = z6.c.f4751u.f11928e;
            if (obj2 == androidx.lifecycle.J.f11925k) {
                obj2 = null;
            }
            f0Var.l(obj2);
            V8.N n10 = z6.d.d;
            C0468y c0468y = new C0468y(z6, null);
            this.l = 1;
            if (AbstractC0828q.g(n10, c0468y, this) == enumC2303a) {
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
