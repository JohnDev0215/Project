package spi;

import android.app.Service;
import com.byd.overseaappstore.module.ability.util.ipc.RemoteOverseaAppStoreService;

/* JADX INFO: loaded from: classes.dex */
public final class f5 extends rk {
    public final g5 a;

    private f5(g5 g5Var, Service service) {
        this.a = g5Var;
    }

    public /* synthetic */ f5(g5 g5Var, Service service, e eVar) {
        this(g5Var, service);
    }

    @Override // spi.gm
    public final void a(RemoteOverseaAppStoreService remoteOverseaAppStoreService) {
        remoteOverseaAppStoreService.d = this.a.X.get();
    }
}
