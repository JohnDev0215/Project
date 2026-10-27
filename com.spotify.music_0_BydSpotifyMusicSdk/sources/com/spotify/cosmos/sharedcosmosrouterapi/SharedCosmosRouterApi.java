package com.spotify.cosmos.sharedcosmosrouterapi;

import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.cosmos.queuingrouter.QueuingRemoteNativeRouter;
import com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter;

/* JADX INFO: loaded from: classes.dex */
public interface SharedCosmosRouterApi {
    QueuingRemoteNativeRouter getLegacyQueuingRemoteNativeRouter();

    NativeRouter getNativeRouter();

    RemoteNativeRouter getRemoteNativeRouter();

    void onCoreSessionInitialized();
}
