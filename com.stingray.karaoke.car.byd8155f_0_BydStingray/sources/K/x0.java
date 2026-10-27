package K;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class x0 extends s7.i implements InterfaceC2785n {
    public T.X l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T.X f3454n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C.k f3455p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(T.X x8, boolean z6, C.k kVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3454n = x8;
        this.o = z6;
        this.f3455p = kVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new x0(this.f3454n, this.o, this.f3455p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((x0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        T.X x8;
        T.X x9;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f3453m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            x8 = this.f3454n;
            C.m mVar = (C.m) x8.getValue();
            if (mVar != null) {
                C.j nVar = this.o ? new C.n(mVar) : new C.l(mVar);
                C.k kVar = this.f3455p;
                if (kVar != null) {
                    this.l = x8;
                    this.f3453m = 1;
                    if (kVar.a(nVar, this) == enumC2303a) {
                        return enumC2303a;
                    }
                    x9 = x8;
                }
                x8.setValue(null);
            }
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        x9 = this.l;
        AbstractC1386c.u(obj);
        x8 = x9;
        x8.setValue(null);
        return m7.q.f19267a;
    }
}
