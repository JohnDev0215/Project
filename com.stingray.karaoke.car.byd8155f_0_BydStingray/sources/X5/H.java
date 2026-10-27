package X5;

import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import h8.AbstractC1386c;
import n3.C1930E;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ SideMenuType l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1930E f10173m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(SideMenuType sideMenuType, C1930E c1930e, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = sideMenuType;
        this.f10173m = c1930e;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new H(this.l, this.f10173m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        H h10 = (H) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        h10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if (this.l == null) {
            C1930E c1930e = this.f10173m;
            if (c1930e.h() != null) {
                c1930e.n(PlayerSideMenuRoute.SongQueue.getValue(), new A7.F(c1930e, 9));
            }
        }
        return m7.q.f19267a;
    }
}
