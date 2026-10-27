package com.spotify.cosmos.queuingrouter;

import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.ResolveCallback;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.cosmos.Router;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.lc;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class QueuingResolveCallback implements ResolveCallback {
    public static final Companion Companion = new Companion(null);
    private final CosmosRequestQueue cosmosRequestQueue;
    private boolean isCosmosReady;
    private final Router router;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean parseResponseForReadiness(Response response) {
            return response.getStatus() == 200;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QueuingResolveCallback(Router router) {
        this(router, new CosmosRequestQueue());
        v03.j(router, "router");
    }

    public final void destroy() {
        setCosmosReady(false);
        this.cosmosRequestQueue.destroy();
    }

    public final boolean isCosmosReady() {
        return this.isCosmosReady;
    }

    @Override // com.spotify.cosmos.cosmos.ResolveCallback
    public void onError(Throwable th) {
        v03.j(th, "throwable");
        lc.e("Could not subscribe to cosmos session state", th);
    }

    @Override // com.spotify.cosmos.cosmos.ResolveCallback
    public void onResolved(Response response) {
        v03.j(response, "response");
        if (!Companion.parseResponseForReadiness(response)) {
            setCosmosReady(false);
        } else {
            if (this.isCosmosReady) {
                return;
            }
            setCosmosReady(true);
        }
    }

    public Lifetime queue(Request request, ResolveCallback resolveCallback) {
        v03.j(request, "request");
        v03.j(resolveCallback, "resolveCallback");
        return this.cosmosRequestQueue.queue(request, resolveCallback);
    }

    public final void replayRequests() {
        if (this.isCosmosReady) {
            this.cosmosRequestQueue.replayRequests(this.router);
        }
    }

    public final void setCosmosReady(boolean z) {
        this.isCosmosReady = z;
        if (z) {
            replayRequests();
        }
    }

    public QueuingResolveCallback(Router router, CosmosRequestQueue cosmosRequestQueue) {
        v03.j(router, "router");
        v03.j(cosmosRequestQueue, "cosmosRequestQueue");
        this.router = router;
        this.cosmosRequestQueue = cosmosRequestQueue;
    }
}
