package N1;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class I extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s7.i f4470m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0423d f4471n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public I(InterfaceC2785n interfaceC2785n, C0423d c0423d, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4470m = (s7.i) interfaceC2785n;
        this.f4471n = c0423d;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new I(this.f4470m, this.f4471n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((I) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Object obj2 = this.f4471n.f4516b;
            this.l = 1;
            obj = this.f4470m.invoke(obj2, this);
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
