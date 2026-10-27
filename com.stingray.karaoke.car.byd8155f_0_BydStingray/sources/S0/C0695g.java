package S0;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: S0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0695g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0696h f7835m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ G f7836n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0695g(C0696h c0696h, G g7, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7835m = c0696h;
        this.f7836n = g7;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0695g(this.f7835m, this.f7836n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0695g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            P6.a aVar = this.f7835m.f7839p;
            this.l = 1;
            obj = aVar.c(this.f7836n, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
