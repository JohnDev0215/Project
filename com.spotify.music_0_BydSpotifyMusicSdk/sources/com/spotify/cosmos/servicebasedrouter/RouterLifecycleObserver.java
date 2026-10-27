package com.spotify.cosmos.servicebasedrouter;

import com.spotify.base.java.logging.Logger;
import java.util.Arrays;
import java.util.Iterator;
import p.f60;
import p.lc;
import p.n70;
import p.s91;
import p.ux2;
import p.v03;
import p.vg2;

/* JADX INFO: loaded from: classes.dex */
public final class RouterLifecycleObserver implements n70 {
    private boolean entered;
    private final CosmosServiceRxRouter serviceRxRouter;

    public RouterLifecycleObserver(CosmosServiceRxRouter cosmosServiceRxRouter) {
        v03.j(cosmosServiceRxRouter, "serviceRxRouter");
        this.serviceRxRouter = cosmosServiceRxRouter;
    }

    private final void unsubscribeAndReportLeaks() {
        Iterator<vg2> it = this.serviceRxRouter.unsubscribeAndReturnLeaks().iterator();
        while (it.hasNext()) {
            String str = String.format("Possible leaked subscription detected. Leaked subscription detected during lifecycle stop: %s. Subscription was originally created here:", Arrays.copyOf(new Object[]{it.next().a}, 1));
            v03.i(str, "format(format, *args)");
            RuntimeException runtimeException = new RuntimeException(str);
            runtimeException.setStackTrace(ux2.e);
            lc.l(runtimeException);
        }
    }

    @Override // p.n70
    public void onCreate(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    @Override // p.n70
    public void onDestroy(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    @Override // p.n70
    public void onPause(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    @Override // p.n70
    public void onResume(s91 s91Var) {
        v03.j(s91Var, "owner");
    }

    @Override // p.n70
    public void onStart(s91 s91Var) {
        v03.j(s91Var, "owner");
        f60.h();
        if (this.entered) {
            return;
        }
        Logger.e("Starting CosmosServiceRxRouter", new Object[0]);
        this.serviceRxRouter.start();
        this.entered = true;
    }

    @Override // p.n70
    public void onStop(s91 s91Var) {
        v03.j(s91Var, "owner");
        f60.h();
        if (this.entered) {
            this.serviceRxRouter.stop();
            this.entered = false;
            unsubscribeAndReportLeaks();
        }
    }
}
