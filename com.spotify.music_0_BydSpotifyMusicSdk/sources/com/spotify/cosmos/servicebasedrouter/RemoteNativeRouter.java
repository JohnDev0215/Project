package com.spotify.cosmos.servicebasedrouter;

import android.os.Binder;
import com.spotify.base.java.logging.Logger;
import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.ResolveCallback;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.cosmos.Router;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.cosmos.observability.CosmosRequestObserver;
import com.spotify.cosmos.routercallback.ResolverCallbackReceiver;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.lc;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class RemoteNativeRouter extends Binder {
    private final Router nativeRouter;
    private boolean routerDestroyed;
    public static final Companion Companion = new Companion(null);
    private static CosmosRequestObserver sRequestObserver = CosmosRequestObserver.Companion.getNO_OP();
    private static final int INVALID_SUBSCRIPTION_ID = -1;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void logIfEmptyResponse(Response response, String str) {
            if (response == null) {
                Logger.b("Null response for %s: ", str);
                return;
            }
            if (response.getBody() == null) {
                Logger.b("Response code: %d, Null response body for %s: ", Integer.valueOf(response.getStatus()), str);
                return;
            }
            byte[] body = response.getBody();
            if (body == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (body.length == 0) {
                if (response.getStatus() < 200 || response.getStatus() > 202) {
                    Logger.b("Response code: %d, Empty response body for %s: ", Integer.valueOf(response.getStatus()), str);
                }
            }
        }

        public final int getINVALID_SUBSCRIPTION_ID() {
            return RemoteNativeRouter.INVALID_SUBSCRIPTION_ID;
        }

        public final void setObserver(CosmosRequestObserver cosmosRequestObserver) {
            v03.j(cosmosRequestObserver, "requestObserver");
            RemoteNativeRouter.sRequestObserver = cosmosRequestObserver;
        }
    }

    public final class NullResolveCallback extends SubscriptionResolveCallback {
        final /* synthetic */ RemoteNativeRouter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NullResolveCallback(RemoteNativeRouter remoteNativeRouter, String str) {
            super(remoteNativeRouter, null, str);
            v03.j(str, "requestUri");
            this.this$0 = remoteNativeRouter;
        }

        @Override // com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter.SubscriptionResolveCallback
        public int getId() {
            return RemoteNativeRouter.Companion.getINVALID_SUBSCRIPTION_ID();
        }

        @Override // com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter.SubscriptionResolveCallback, com.spotify.cosmos.cosmos.ResolveCallback
        public void onError(Throwable th) {
            v03.j(th, "throwable");
        }

        @Override // com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter.SubscriptionResolveCallback, com.spotify.cosmos.cosmos.ResolveCallback
        public void onResolved(Response response) {
            v03.j(response, "response");
        }
    }

    public class SubscriptionResolveCallback implements ResolveCallback {
        private boolean isCancelled;
        private final String requestUri;
        private final ResolverCallbackReceiver<Response> resolverCallbackReceiver;
        final /* synthetic */ RemoteNativeRouter this$0;

        public SubscriptionResolveCallback(RemoteNativeRouter remoteNativeRouter, ResolverCallbackReceiver<Response> resolverCallbackReceiver, String str) {
            v03.j(str, "requestUri");
            this.this$0 = remoteNativeRouter;
            this.resolverCallbackReceiver = resolverCallbackReceiver;
            this.requestUri = str;
        }

        public void destroy() {
            this.isCancelled = true;
        }

        public int getId() {
            ResolverCallbackReceiver<Response> resolverCallbackReceiver = this.resolverCallbackReceiver;
            if (resolverCallbackReceiver != null) {
                return resolverCallbackReceiver.getId();
            }
            throw new IllegalStateException("Required value was null.".toString());
        }

        @Override // com.spotify.cosmos.cosmos.ResolveCallback
        public void onError(Throwable th) {
            v03.j(th, "throwable");
            if (this.this$0.getRouterDestroyed() || this.isCancelled) {
                return;
            }
            ResolverCallbackReceiver<Response> resolverCallbackReceiver = this.resolverCallbackReceiver;
            if (resolverCallbackReceiver == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            resolverCallbackReceiver.sendOnError(th);
        }

        @Override // com.spotify.cosmos.cosmos.ResolveCallback
        public void onResolved(Response response) {
            v03.j(response, "response");
            if (this.this$0.getRouterDestroyed() || this.isCancelled) {
                return;
            }
            try {
                RemoteNativeRouter.Companion.logIfEmptyResponse(response, this.requestUri);
                ResolverCallbackReceiver<Response> resolverCallbackReceiver = this.resolverCallbackReceiver;
                if (resolverCallbackReceiver == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                resolverCallbackReceiver.sendOnResolved(response);
            } catch (Exception e) {
                onError(new RuntimeException(u4.m(new StringBuilder("onResolved("), this.requestUri, ") failed."), e));
            }
        }
    }

    public static final class WeakResolveCallbackWrapper implements ResolveCallback {
        private final String id;
        private final WeakReference<ResolveCallback> ref;

        public WeakResolveCallbackWrapper(String str, ResolveCallback resolveCallback) {
            v03.j(str, "id");
            v03.j(resolveCallback, "resolveCallback");
            this.id = str;
            this.ref = new WeakReference<>(resolveCallback);
        }

        @Override // com.spotify.cosmos.cosmos.ResolveCallback
        public void onError(Throwable th) {
            v03.j(th, "throwable");
            ResolveCallback resolveCallback = this.ref.get();
            RemoteNativeRouter.sRequestObserver.onError(this.id, th);
            if (resolveCallback != null) {
                resolveCallback.onError(th);
            }
        }

        @Override // com.spotify.cosmos.cosmos.ResolveCallback
        public void onResolved(Response response) {
            v03.j(response, "response");
            ResolveCallback resolveCallback = this.ref.get();
            try {
                RemoteNativeRouter.sRequestObserver.onResponse(this.id, response);
                if (resolveCallback != null) {
                    resolveCallback.onResolved(response);
                }
            } catch (Exception e) {
                lc.e("Caught an Exception in ResolveCallback.onResolved", e);
            } catch (OutOfMemoryError e2) {
                lc.e("Caught an OutOfMemoryError in ResolveCallback.onResolved", e2);
                throw new RuntimeException("OutOfMemoryError converted to RuntimeException", e2);
            }
        }
    }

    public RemoteNativeRouter(Router router) {
        v03.j(router, "nativeRouter");
        this.nativeRouter = router;
    }

    public synchronized void destroy() {
        if (this.routerDestroyed) {
            throw new IllegalStateException("Router already destroyed");
        }
        this.routerDestroyed = true;
        getNativeRouter().destroy();
    }

    public Router getNativeRouter() {
        return this.nativeRouter;
    }

    public final boolean getRouterDestroyed() {
        return this.routerDestroyed;
    }

    public Lifetime performNativeResolve(Request request, ResolveCallback resolveCallback) {
        v03.j(request, "request");
        v03.j(resolveCallback, "wrapper");
        return getNativeRouter().resolve(request, resolveCallback);
    }

    public Lifetime resolve(String str, String str2, Map<String, String> map, byte[] bArr, ResolverCallbackReceiver<Response> resolverCallbackReceiver) {
        v03.j(str, "action");
        v03.j(str2, "uri");
        return resolve(new Request(str, str2, map, bArr), resolverCallbackReceiver == null ? new NullResolveCallback(this, str2) : new SubscriptionResolveCallback(this, resolverCallbackReceiver, str2));
    }

    public final void setRouterDestroyed(boolean z) {
        this.routerDestroyed = z;
    }

    public RemoteNativeRouter() {
        this(new NativeRouter());
    }

    private final synchronized Lifetime resolve(Request request, final SubscriptionResolveCallback subscriptionResolveCallback) {
        String uri = request.getUri();
        if (this.routerDestroyed) {
            Logger.e("(Router Destroyed) Dropping request for uri = %s", uri);
            return Lifetime.UNRESOLVED;
        }
        Logger.e("Resolving router with uri = %s", uri);
        int id = subscriptionResolveCallback.getId();
        sRequestObserver.onRequest(String.valueOf(id), request);
        final Lifetime lifetimePerformNativeResolve = performNativeResolve(request, new WeakResolveCallbackWrapper(String.valueOf(id), subscriptionResolveCallback));
        return new Lifetime() { // from class: com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter.resolve.1
            @Override // com.spotify.cosmos.cosmos.Lifetime
            public void release() {
                subscriptionResolveCallback.destroy();
                Lifetime lifetime = lifetimePerformNativeResolve;
                if (lifetime == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                lifetime.release();
            }
        };
    }
}
