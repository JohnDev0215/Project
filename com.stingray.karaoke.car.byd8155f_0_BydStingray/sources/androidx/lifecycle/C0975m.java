package androidx.lifecycle;

import F0.k1;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: androidx.lifecycle.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0975m extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f11998m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ V8.d0 f11999n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0975m(V8.d0 d0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11999n = d0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0975m c0975m = new C0975m(this.f11999n, interfaceC2189c);
        c0975m.f11998m = obj;
        return c0975m;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0975m) create((I) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            k1 k1Var = new k1((I) this.f11998m, 8);
            this.l = 1;
            if (this.f11999n.b(k1Var, this) == enumC2303a) {
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
