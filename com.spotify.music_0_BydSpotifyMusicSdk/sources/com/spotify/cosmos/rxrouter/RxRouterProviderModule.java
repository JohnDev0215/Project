package com.spotify.cosmos.rxrouter;

import p.l91;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface RxRouterProviderModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final RxRouterProvider provideRxRouterProvider(final RxRouter rxRouter) {
            v03.j(rxRouter, "rxRouter");
            return new RxRouterProvider() { // from class: com.spotify.cosmos.rxrouter.RxRouterProviderModule$Companion$provideRxRouterProvider$1
                @Override // com.spotify.cosmos.rxrouter.RxRouterProvider
                public RxRouter provideWithLifecycle(l91 l91Var) {
                    v03.j(l91Var, "lifecycle");
                    return rxRouter;
                }
            };
        }
    }
}
