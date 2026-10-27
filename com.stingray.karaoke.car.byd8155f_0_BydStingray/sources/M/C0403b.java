package M;

import F0.k1;
import F8.C0285u;
import S8.InterfaceC0725w;
import T.C0735d;
import V8.K;
import V8.S;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: M.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0403b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f4127m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t f4128n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0403b(f fVar, t tVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4127m = fVar;
        this.f4128n = tVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0403b(this.f4127m, this.f4128n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0403b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            C0402a c0402a = C0402a.f4124n;
            this.l = 1;
            if (C0735d.I(getContext()).o(new C0285u(1, c0402a), this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
                throw new A9.k();
            }
            AbstractC1386c.u(obj);
        }
        K kI = this.f4127m.i();
        if (kI == null) {
            return m7.q.f19267a;
        }
        k1 k1Var = new k1(this.f4128n, 1);
        this.l = 2;
        S.m((S) kI, k1Var, this);
        return enumC2303a;
    }
}
