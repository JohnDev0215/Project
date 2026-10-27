package h6;

import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r6.C2300a;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ h l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(h hVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = hVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        g gVar = (g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        gVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object value;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        LoadingInterface$LoadingState loadingInterface$LoadingState = LoadingInterface$LoadingState.Loading;
        h hVar = this.l;
        hVar.l(loadingInterface$LoadingState);
        try {
            String str = C2300a.b((C2300a) hVar.f15577m.f3095h.getValue(), AbstractC1998m.A0(J5.c.f3089i)).f13084a;
            f0 f0Var = hVar.f15578n;
            do {
                value = f0Var.getValue();
            } while (!f0Var.k(value, str));
        } catch (Exception e10) {
            A7.m.f("Failed to load FAQ data " + e10, "text");
            hVar.l(LoadingInterface$LoadingState.Done);
        }
        hVar.l(LoadingInterface$LoadingState.Done);
        return m7.q.f19267a;
    }
}
