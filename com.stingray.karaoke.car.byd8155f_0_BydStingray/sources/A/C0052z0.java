package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0052z0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f303m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T f304n;
    public final /* synthetic */ S0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0052z0(T t10, S0 s02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f304n = t10;
        this.o = s02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0052z0 c0052z0 = new C0052z0(this.f304n, this.o, interfaceC2189c);
        c0052z0.f303m = obj;
        return c0052z0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0052z0) create((P0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0004b c0004b = new C0004b((P0) this.f303m, 3, this.o);
            this.l = 1;
            if (this.f304n.invoke(c0004b, this) == enumC2303a) {
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
