package R;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: R.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0682j extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s7.i f7300m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7301n;
    public final /* synthetic */ InterfaceC0725w o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C0682j(InterfaceC2785n interfaceC2785n, Object obj, InterfaceC0725w interfaceC0725w, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7300m = (s7.i) interfaceC2785n;
        this.f7301n = obj;
        this.o = interfaceC0725w;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0682j(this.f7300m, this.f7301n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0682j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            if (this.f7300m.invoke(this.f7301n, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        AbstractC0727y.d(this.o, new C0676d());
        return m7.q.f19267a;
    }
}
