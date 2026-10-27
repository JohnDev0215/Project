package com.spotify.cosmos.queuingrouter;

import com.spotify.base.java.logging.Logger;
import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.ResolveCallback;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.cosmos.Router;
import com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.ng2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class QueuingRemoteNativeRouter extends RemoteNativeRouter {
    public static final Companion Companion = new Companion(null);
    public static final String SP_CLIENT_LOGIN5_URI = "sp://esperanto/spotify.connectivity.auth.login5.esperanto.proto.Login5/";
    public static final String SP_CLIENT_TOKEN_V1_URI = "sp://client-token/v1";
    public static final String SP_ORBIT_SESSION_STATE_URI = "sp://orbitsession/v1/state";
    public static final String SP_SESSION_V1_URI = "sp://session/v1";
    private boolean coreSessionInitialized;
    private Lifetime lifetime;
    private final QueuingResolveCallback queuingResolveCallback;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class RestartQueueCallback implements ResolveCallback {
        private final QueuingResolveCallback queuingResolveCallback;
        private final ResolveCallback resolveCallback;

        public RestartQueueCallback(ResolveCallback resolveCallback, QueuingResolveCallback queuingResolveCallback) {
            v03.j(resolveCallback, "resolveCallback");
            v03.j(queuingResolveCallback, "queuingResolveCallback");
            this.resolveCallback = resolveCallback;
            this.queuingResolveCallback = queuingResolveCallback;
        }

        @Override // com.spotify.cosmos.cosmos.ResolveCallback
        public void onError(Throwable th) {
            v03.j(th, "throwable");
            this.queuingResolveCallback.setCosmosReady(true);
            this.resolveCallback.onError(th);
        }

        @Override // com.spotify.cosmos.cosmos.ResolveCallback
        public void onResolved(Response response) {
            v03.j(response, "response");
            this.resolveCallback.onResolved(response);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QueuingRemoteNativeRouter(Router router) {
        this(router, new QueuingResolveCallback(router));
        v03.j(router, "router");
    }

    private final boolean shouldHaltQueue(Request request) {
        return Request.DELETE.equals(request.getAction()) && ng2.b0(request.getUri(), SP_SESSION_V1_URI, false);
    }

    private final boolean shouldQueueRequest(Request request) {
        Map<String, String> headers = request.getHeaders();
        if (headers != null && headers.containsKey("force-request") && ng2.b0(headers.get("force-request"), "true", false)) {
            return false;
        }
        String uri = request.getUri();
        if (uri != null) {
            return (SP_ORBIT_SESSION_STATE_URI.equals(uri) || ng2.g0(uri, SP_SESSION_V1_URI, false) || ng2.g0(uri, SP_CLIENT_TOKEN_V1_URI, false) || ng2.g0(uri, SP_CLIENT_LOGIN5_URI, false)) ? false : true;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    @Override // com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter
    public void destroy() {
        super.destroy();
        this.coreSessionInitialized = false;
        this.lifetime.release();
        this.queuingResolveCallback.destroy();
    }

    public final Lifetime getLifetime() {
        return this.lifetime;
    }

    public synchronized void onCoreSessionInitialized() {
        if (getRouterDestroyed()) {
            throw new IllegalStateException("(Router Destroyed) Unable to perform onCoreSessionInitialized");
        }
        this.coreSessionInitialized = true;
        this.queuingResolveCallback.replayRequests();
        Logger.e("onCoreSessionInitialized", new Object[0]);
    }

    public synchronized void onNativeRouterInitialized() {
        if (getRouterDestroyed()) {
            throw new IllegalStateException("(Router Destroyed) Unable to perform onNativeRouterIntialized");
        }
        Lifetime lifetimePerformNativeResolve = super.performNativeResolve(new Request(Request.SUB, SP_SESSION_V1_URI), this.queuingResolveCallback);
        if (lifetimePerformNativeResolve == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        this.lifetime = lifetimePerformNativeResolve;
        Logger.e("onNativeRouterInitialized", new Object[0]);
    }

    @Override // com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter
    public Lifetime performNativeResolve(Request request, ResolveCallback resolveCallback) {
        v03.j(request, "request");
        v03.j(resolveCallback, "wrapper");
        if (shouldHaltQueue(request) && this.queuingResolveCallback.isCosmosReady()) {
            this.queuingResolveCallback.setCosmosReady(false);
            resolveCallback = new RestartQueueCallback(resolveCallback, this.queuingResolveCallback);
        }
        return (!(this.coreSessionInitialized && this.queuingResolveCallback.isCosmosReady()) && shouldQueueRequest(request)) ? this.queuingResolveCallback.queue(request, resolveCallback) : super.performNativeResolve(request, resolveCallback);
    }

    public final void setLifetime(Lifetime lifetime) {
        v03.j(lifetime, "<set-?>");
        this.lifetime = lifetime;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QueuingRemoteNativeRouter(Router router, QueuingResolveCallback queuingResolveCallback) {
        super(router);
        v03.j(router, "router");
        v03.j(queuingResolveCallback, "queuingResolveCallback");
        this.lifetime = Lifetime.UNRESOLVED;
        this.queuingResolveCallback = queuingResolveCallback;
    }

    public QueuingRemoteNativeRouter() {
        this.lifetime = Lifetime.UNRESOLVED;
        this.queuingResolveCallback = new QueuingResolveCallback(getNativeRouter());
    }
}
