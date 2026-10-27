package j6;

import Q5.C0618s1;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.utils.ToastType;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2785n {
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k(2, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        k kVar = (k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        kVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        ToastType toastType = ToastType.REMOVE_FROM_FAVORITE;
        A7.m.f(toastType, "toastType");
        h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
        return m7.q.f19267a;
    }
}
