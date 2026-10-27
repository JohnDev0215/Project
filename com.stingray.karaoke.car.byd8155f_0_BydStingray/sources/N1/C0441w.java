package N1;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0441w extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0441w c0441w = new C0441w(2, interfaceC2189c);
        c0441w.l = obj;
        return c0441w;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0441w) create((c0) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        return Boolean.valueOf(!(((c0) this.l) instanceof T));
    }
}
