package com.spotify.cosmos.servicebasedrouter;

import com.spotify.cosmos.observability.CosmosRequestObserver;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Cosmos {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void setObserver(CosmosRequestObserver cosmosRequestObserver) {
            v03.j(cosmosRequestObserver, "observer");
            RemoteNativeRouter.Companion.setObserver(cosmosRequestObserver);
        }
    }

    public static final void setObserver(CosmosRequestObserver cosmosRequestObserver) {
        Companion.setObserver(cosmosRequestObserver);
    }
}
