package T;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f8194m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f8195n;
    public final /* synthetic */ X o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J0(InterfaceC2785n interfaceC2785n, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f8195n = interfaceC2785n;
        this.o = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        J0 j02 = new J0(this.f8195n, this.o, interfaceC2189c);
        j02.f8194m = obj;
        return j02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((J0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0748j0 c0748j0 = new C0748j0(this.o, ((InterfaceC0725w) this.f8194m).getCoroutineContext());
            this.l = 1;
            if (this.f8195n.invoke(c0748j0, this) == enumC2303a) {
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
