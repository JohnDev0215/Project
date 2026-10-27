package A;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0009d0 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f159m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f160n;

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0009d0 c0009d0 = new C0009d0(interfaceC2189c);
        c0009d0.f160n = obj;
        return c0009d0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0009d0) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f159m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            y0.z zVar = (y0.z) this.f160n;
            this.f159m = 1;
            if (C7.a.v(zVar, this) == enumC2303a) {
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
