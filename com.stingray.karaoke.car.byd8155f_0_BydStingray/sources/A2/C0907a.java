package a2;

import S8.InterfaceC0725w;
import T.C0748j0;
import T.L0;
import V8.d0;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: a2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0907a extends i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ d0 f11045m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0748j0 f11046n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0907a(d0 d0Var, C0748j0 c0748j0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f11045m = d0Var;
        this.f11046n = c0748j0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0907a(this.f11045m, this.f11046n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0907a) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            L0 l02 = new L0(this.f11046n, 3);
            this.l = 1;
            if (this.f11045m.b(l02, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
