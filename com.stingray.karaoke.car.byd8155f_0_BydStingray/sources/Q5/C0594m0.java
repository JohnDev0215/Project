package Q5;

import S8.InterfaceC0725w;
import T.C0735d;
import V8.C0819h;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0594m0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ T.Q0 f6956m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6957n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0594m0(T.Q0 q02, InterfaceC2772a interfaceC2772a, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6956m = q02;
        this.f6957n = interfaceC2772a;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0594m0(this.f6956m, this.f6957n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0594m0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0819h c0819hW = C0735d.W(new A3.g(this.f6956m, 4));
            F0.k1 k1Var = new F0.k1(this.f6957n, 4);
            this.l = 1;
            if (c0819hW.b(k1Var, this) == enumC2303a) {
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
