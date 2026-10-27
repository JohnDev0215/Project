package N1;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class C extends s7.i implements InterfaceC2782k {
    public Throwable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4451m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q f4452n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(Q q4, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4452n = q4;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C(this.f4452n, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Throwable th;
        c0 v10;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f4451m;
        Q q4 = this.f4452n;
        try {
        } catch (Throwable th2) {
            b0 b0VarF = q4.f();
            this.l = th2;
            this.f4451m = 2;
            Integer numA = b0VarF.a();
            if (numA == enumC2303a) {
                return enumC2303a;
            }
            obj = numA;
            th = th2;
        }
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.f4451m = 1;
            obj = Q.e(q4, true, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                th = this.l;
                AbstractC1386c.u(obj);
                v10 = new V(th, ((Number) obj).intValue());
                return new m7.i(v10, Boolean.TRUE);
            }
            AbstractC1386c.u(obj);
        }
        v10 = (c0) obj;
        return new m7.i(v10, Boolean.TRUE);
    }
}
