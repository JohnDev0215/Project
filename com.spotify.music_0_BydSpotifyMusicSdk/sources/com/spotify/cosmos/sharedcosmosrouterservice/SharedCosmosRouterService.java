package com.spotify.cosmos.sharedcosmosrouterservice;

import com.spotify.cosmos.cosmos.Router;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.cosmos.queuingrouter.QueuingRemoteNativeRouter;
import com.spotify.cosmos.scheduling.CoreThreadCosmosScheduler;
import com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import p.r10;
import p.sb2;
import p.u10;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SharedCosmosRouterService implements SharedCosmosRouterApi, sb2 {
    private final QueuingRemoteNativeRouter legacyQueuingRemoteNativeRouter;
    private final NativeRouter nativeRouter;
    private final RemoteNativeRouter remoteNativeRouter;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SharedCosmosRouterService(r10 r10Var, RemoteRouterFactory remoteRouterFactory) {
        this(r10Var, remoteRouterFactory.createRemoteNativeRouter());
        v03.j(r10Var, "coreThreadingApi");
        v03.j(remoteRouterFactory, "remoteRouterFactory");
    }

    /* JADX INFO: renamed from: getApi, reason: merged with bridge method [inline-methods] */
    public SharedCosmosRouterApi m195getApi() {
        return this;
    }

    @Override // com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi
    public QueuingRemoteNativeRouter getLegacyQueuingRemoteNativeRouter() {
        return this.legacyQueuingRemoteNativeRouter;
    }

    @Override // com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi
    public NativeRouter getNativeRouter() {
        return this.nativeRouter;
    }

    @Override // com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi
    public RemoteNativeRouter getRemoteNativeRouter() {
        return this.remoteNativeRouter;
    }

    @Override // com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi
    public void onCoreSessionInitialized() {
        if (getRemoteNativeRouter() instanceof QueuingRemoteNativeRouter) {
            ((QueuingRemoteNativeRouter) getRemoteNativeRouter()).onCoreSessionInitialized();
        }
    }

    @Override // p.sb2
    public void shutdown() {
        getNativeRouter().deinitializeScheduling();
        getRemoteNativeRouter().destroy();
    }

    public SharedCosmosRouterService(r10 r10Var, RemoteNativeRouter remoteNativeRouter) {
        v03.j(r10Var, "coreThreadingApi");
        v03.j(remoteNativeRouter, "remoteNativeRouter");
        this.remoteNativeRouter = remoteNativeRouter;
        this.legacyQueuingRemoteNativeRouter = remoteNativeRouter instanceof QueuingRemoteNativeRouter ? (QueuingRemoteNativeRouter) remoteNativeRouter : new QueuingRemoteNativeRouter();
        Router nativeRouter = remoteNativeRouter.getNativeRouter();
        v03.g(nativeRouter, "null cannot be cast to non-null type com.spotify.cosmos.cosmosimpl.NativeRouter");
        this.nativeRouter = (NativeRouter) nativeRouter;
        getNativeRouter().initializeScheduling(new CoreThreadCosmosScheduler(((u10) r10Var).a));
    }
}
