package L5;

import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.kca.model.KaraokeItem;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ KaraokeItem f4026m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ F6.b f4027n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(i iVar, KaraokeItem karaokeItem, F6.b bVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = iVar;
        this.f4026m = karaokeItem;
        this.f4027n = bVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.l, this.f4026m, this.f4027n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        g gVar = (g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        gVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        this.l.b().g(NavigationRoutes.ARTISTS, this.f4026m.f13107a, this.f4027n);
        return q.f19267a;
    }
}
