package com.spotify.core.corelimitedimpl;

import com.spotify.appstorage.userdirectory.NativeUserDirectoryManager;
import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.connectivity.NativeAuthenticatedScope;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.connectivitysdkpolicyimpl.NativeConnectivityManager;
import com.spotify.core.corelimited.LimitedAuthenticatedScope;
import com.spotify.core.corelimited.NativeLimitedAuthenticatedScope;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.offline.NativeForegroundProvider;
import com.spotify.prefs.prefsimpl.NativePrefs;
import com.spotify.remoteconfig.NativeRemoteConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLimitedAuthenticatedScopeImpl implements NativeLimitedAuthenticatedScope, LimitedAuthenticatedScope {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeLimitedAuthenticatedScopeImpl create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativePrefs nativePrefs, NativeRemoteConfig nativeRemoteConfig, NativeConnectivityManager nativeConnectivityManager, NativeApplicationScope nativeApplicationScope, NativeSession nativeSession, NativeAuthenticatedScope nativeAuthenticatedScope, com.spotify.core.core.NativeApplicationScope nativeApplicationScope2, NativeUserDirectoryManager nativeUserDirectoryManager, LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration, NativeForegroundProvider nativeForegroundProvider) {
            return NativeLimitedAuthenticatedScopeImpl.create(nativeTimerManagerThread, nativeRouter, nativePrefs, nativeRemoteConfig, nativeConnectivityManager, nativeApplicationScope, nativeSession, nativeAuthenticatedScope, nativeApplicationScope2, nativeUserDirectoryManager, limitedAuthenticatedScopeConfiguration, nativeForegroundProvider);
        }
    }

    private NativeLimitedAuthenticatedScopeImpl() {
    }

    public static final native NativeLimitedAuthenticatedScopeImpl create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativePrefs nativePrefs, NativeRemoteConfig nativeRemoteConfig, NativeConnectivityManager nativeConnectivityManager, NativeApplicationScope nativeApplicationScope, NativeSession nativeSession, NativeAuthenticatedScope nativeAuthenticatedScope, com.spotify.core.core.NativeApplicationScope nativeApplicationScope2, NativeUserDirectoryManager nativeUserDirectoryManager, LimitedAuthenticatedScopeConfiguration limitedAuthenticatedScopeConfiguration, NativeForegroundProvider nativeForegroundProvider);

    @Override // com.spotify.core.corelimited.NativeLimitedAuthenticatedScope
    public native void destroy();

    public final native void flushCaches();

    @Override // com.spotify.core.corelimited.NativeLimitedAuthenticatedScope
    public long getNThis() {
        return this.nThis;
    }

    public final native void prepareForShutdown();
}
