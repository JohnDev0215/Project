package O;

import S8.InterfaceC0725w;
import T.C0735d;
import T.Q0;
import V8.C0819h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x.C2629d;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4890m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q0 f4891n;
    public final /* synthetic */ C2629d o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(Q0 q02, C2629d c2629d, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4891n = q02;
        this.o = c2629d;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        B b10 = new B(this.f4891n, this.o, interfaceC2189c);
        b10.f4890m = obj;
        return b10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((B) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f4890m;
            C0819h c0819hW = C0735d.W(new K7.K(this.f4891n, 8));
            C.f fVar = new C.f(this.o, 2, interfaceC0725w);
            this.l = 1;
            if (c0819hW.b(fVar, this) == enumC2303a) {
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
