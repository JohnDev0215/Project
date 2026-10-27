package com.spotify.core.corefullimpl;

import com.spotify.appstorage.userdirectory.NativeUserDirectoryManager;
import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.connectivity.NativeAuthenticatedScope;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.connectivitysdkpolicyimpl.NativeConnectivityManager;
import com.spotify.core.corefull.FullAuthenticatedScope;
import com.spotify.core.corefull.NativeFullAuthenticatedScope;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.localfiles.localfilesimpl.NativeLocalFilesDelegate;
import com.spotify.offline.NativeForegroundProvider;
import com.spotify.offline.NativeOfflinePluginRegistry;
import com.spotify.prefs.prefsimpl.NativePrefs;
import com.spotify.remoteconfig.NativeRemoteConfig;
import com.spotify.shoreline.esperanto.java.api.NativeShorelineLogger;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeFullAuthenticatedScopeImpl implements NativeFullAuthenticatedScope, FullAuthenticatedScope {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeFullAuthenticatedScopeImpl create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativePrefs nativePrefs, NativeRemoteConfig nativeRemoteConfig, NativeShorelineLogger nativeShorelineLogger, NativeConnectivityManager nativeConnectivityManager, NativeApplicationScope nativeApplicationScope, NativeSession nativeSession, NativeAuthenticatedScope nativeAuthenticatedScope, com.spotify.core.core.NativeApplicationScope nativeApplicationScope2, NativeLocalFilesDelegate nativeLocalFilesDelegate, NativeUserDirectoryManager nativeUserDirectoryManager, FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration, NativeOfflinePluginRegistry nativeOfflinePluginRegistry, NativeForegroundProvider nativeForegroundProvider) {
            return NativeFullAuthenticatedScopeImpl.create(nativeTimerManagerThread, nativeRouter, nativePrefs, nativeRemoteConfig, nativeShorelineLogger, nativeConnectivityManager, nativeApplicationScope, nativeSession, nativeAuthenticatedScope, nativeApplicationScope2, nativeLocalFilesDelegate, nativeUserDirectoryManager, fullAuthenticatedScopeConfiguration, nativeOfflinePluginRegistry, nativeForegroundProvider);
        }
    }

    private NativeFullAuthenticatedScopeImpl() {
    }

    public static final native NativeFullAuthenticatedScopeImpl create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativePrefs nativePrefs, NativeRemoteConfig nativeRemoteConfig, NativeShorelineLogger nativeShorelineLogger, NativeConnectivityManager nativeConnectivityManager, NativeApplicationScope nativeApplicationScope, NativeSession nativeSession, NativeAuthenticatedScope nativeAuthenticatedScope, com.spotify.core.core.NativeApplicationScope nativeApplicationScope2, NativeLocalFilesDelegate nativeLocalFilesDelegate, NativeUserDirectoryManager nativeUserDirectoryManager, FullAuthenticatedScopeConfiguration fullAuthenticatedScopeConfiguration, NativeOfflinePluginRegistry nativeOfflinePluginRegistry, NativeForegroundProvider nativeForegroundProvider);

    @Override // com.spotify.core.corefull.NativeFullAuthenticatedScope
    public native void destroy();

    public final native void flushCaches();

    @Override // com.spotify.core.corefull.NativeFullAuthenticatedScope
    public long getNThis() {
        return this.nThis;
    }

    public final native void prepareForShutdown();
}
