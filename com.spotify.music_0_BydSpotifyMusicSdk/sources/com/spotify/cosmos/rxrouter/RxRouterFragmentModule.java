package com.spotify.cosmos.rxrouter;

import androidx.lifecycle.a;
import p.v03;
import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public interface RxRouterFragmentModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final RxRouter provideRouter(RxRouterProvider rxRouterProvider, ww0 ww0Var) {
            v03.j(rxRouterProvider, "provider");
            v03.j(ww0Var, "fragment");
            a aVar = ww0Var.U;
            v03.i(aVar, "fragment.lifecycle");
            return rxRouterProvider.provideWithLifecycle(aVar);
        }
    }
}
