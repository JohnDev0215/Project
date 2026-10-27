package R;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class q extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ w f7315m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f7316n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(v vVar, w wVar, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f7315m = wVar;
        this.f7316n = vVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((q) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new q(this.f7316n, this.f7315m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            w wVar = this.f7315m;
            C0687o c0687o = new C0687o(wVar, 0);
            C0688p c0688p = new C0688p(this.f7316n, wVar, null);
            this.l = 1;
            if (androidx.compose.material3.internal.a.a(c0687o, c0688p, this) == enumC2303a) {
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
