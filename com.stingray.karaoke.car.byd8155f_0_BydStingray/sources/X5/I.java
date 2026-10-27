package X5;

import com.stingray.karaoke.car.ui.player.SideMenuType;
import n3.C1930E;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class I extends A7.j implements InterfaceC2772a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ C1930E f10176t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f10177u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(C1930E c1930e, InterfaceC2782k interfaceC2782k) {
        super(0, A7.l.class, "navigateBack", "invoke$lambda$17$navigateBack(Landroidx/navigation/NavHostController;Lkotlin/jvm/functions/Function1;)V", 0);
        this.f10176t = c1930e;
        this.f10177u = interfaceC2782k;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        io.sentry.hints.i iVar = k6.o.f18284a;
        if (!io.sentry.hints.i.h()) {
            C1930E c1930e = this.f10176t;
            n3.y yVarI = c1930e.i();
            if (A7.m.a(yVarI != null ? yVarI.f19918r : null, c1930e.j().f19793x)) {
                this.f10177u.b(SideMenuType.SONG_QUEUE);
            } else {
                c1930e.q();
            }
        }
        return m7.q.f19267a;
    }
}
