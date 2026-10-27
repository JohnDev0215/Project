package R;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ w f7321m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7322n;
    public final /* synthetic */ C0680h o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(w wVar, Object obj, C0680h c0680h, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f7321m = wVar;
        this.f7322n = obj;
        this.o = c0680h;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((t) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new t(this.f7321m, this.f7322n, this.o, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Object obj2 = this.f7322n;
            w wVar = this.f7321m;
            wVar.g(obj2);
            C0687o c0687o = new C0687o(wVar, 1);
            s sVar = new s(this.o, wVar, null);
            this.l = 1;
            if (androidx.compose.material3.internal.a.a(c0687o, sVar, this) == enumC2303a) {
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
