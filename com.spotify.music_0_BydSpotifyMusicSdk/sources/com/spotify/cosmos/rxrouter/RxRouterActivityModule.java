package com.spotify.cosmos.rxrouter;

import androidx.lifecycle.a;
import p.v03;
import p.zw0;

/* JADX INFO: loaded from: classes.dex */
public interface RxRouterActivityModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final RxRouter provideRouter(RxRouterProvider rxRouterProvider, zw0 zw0Var) {
            v03.j(rxRouterProvider, "provider");
            v03.j(zw0Var, "activity");
            a aVar = zw0Var.l;
            v03.i(aVar, "activity.lifecycle");
            return rxRouterProvider.provideWithLifecycle(aVar);
        }
    }
}
