package Z5;

import A7.m;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b extends i implements InterfaceC2785n {
    public final /* synthetic */ c l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = cVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new b(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        b bVar = (b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        bVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        LoadingInterface$LoadingState loadingInterface$LoadingState = LoadingInterface$LoadingState.Loading;
        c cVar = this.l;
        cVar.l(loadingInterface$LoadingState);
        boolean zB = cVar.l.b();
        q qVar = q.f19267a;
        if (!zB) {
            cVar.l(LoadingInterface$LoadingState.Error);
            return qVar;
        }
        try {
            cVar.f10902m.c();
            cVar.l(LoadingInterface$LoadingState.Done);
            return qVar;
        } catch (Exception e10) {
            m.f("Splash init error: " + e10.getMessage(), "text");
            cVar.l(LoadingInterface$LoadingState.Error);
            return qVar;
        }
    }
}
