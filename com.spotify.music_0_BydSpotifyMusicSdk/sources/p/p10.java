package p;

import com.spotify.core.coreimpl.NativeApplicationScopeImpl;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterService;

/* JADX INFO: loaded from: classes.dex */
public final class p10 implements Runnable {
    public final /* synthetic */ q10 c;
    public final /* synthetic */ SharedCosmosRouterApi e;

    public p10(q10 q10Var, SharedCosmosRouterService sharedCosmosRouterService) {
        this.c = q10Var;
        this.e = sharedCosmosRouterService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NativeApplicationScopeImpl.Companion companion = NativeApplicationScopeImpl.Companion;
        q10 q10Var = this.c;
        q10Var.f = companion.create(((u10) q10Var.a).a, this.e.getNativeRouter(), ((o10) q10Var.b).a, q10Var.c, q10Var.d.getNativeLoginController(), q10Var.e);
    }
}
