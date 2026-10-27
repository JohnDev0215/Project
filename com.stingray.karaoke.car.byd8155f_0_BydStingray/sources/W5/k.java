package W5;

import S8.InterfaceC0725w;
import T.X;
import V8.f0;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import h8.AbstractC1386c;
import n3.C1945j;
import n3.y;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ X l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f6.h f9838m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(X x8, f6.h hVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = x8;
        this.f9838m = hVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k(this.l, this.f9838m, interfaceC2189c);
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
        y yVar;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        C1945j c1945j = (C1945j) this.l.getValue();
        boolean zA = A7.m.a((c1945j == null || (yVar = c1945j.f19865m) == null) ? null : yVar.f19918r, NavigationRoutes.DEV_SETTINGS.getValue());
        f6.h hVar = this.f9838m;
        if (zA) {
            f0 f0Var = hVar.f14623t;
            Boolean bool = Boolean.FALSE;
            f0Var.getClass();
            f0Var.m(null, bool);
        } else {
            f0 f0Var2 = hVar.f14623t;
            Boolean bool2 = Boolean.TRUE;
            f0Var2.getClass();
            f0Var2.m(null, bool2);
        }
        return m7.q.f19267a;
    }
}
