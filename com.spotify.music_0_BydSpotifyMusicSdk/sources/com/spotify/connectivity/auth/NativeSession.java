package com.spotify.connectivity.auth;

import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.ConnectionTypeProvider;
import com.spotify.connectivity.ConnectivityPolicyProvider;
import com.spotify.connectivity.NativeApplicationScope;
import com.spotify.esperanto.esperanto.Transport;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeSession implements Session {
    public static final Companion Companion = new Companion(null);
    private final String canonicalUsername;
    private final Transport internalTransportToNative;
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeSession createSession(NativeLoginControllerConfiguration nativeLoginControllerConfiguration, NativeTimerManagerThread nativeTimerManagerThread, NativeApplicationScope nativeApplicationScope, AnalyticsDelegate analyticsDelegate, ConnectivityPolicyProvider connectivityPolicyProvider, ConnectionTypeProvider connectionTypeProvider, CredentialsStorage credentialsStorage, UnencryptedCredentials unencryptedCredentials, NativeLogin5OAuthClient nativeLogin5OAuthClient, Map<String, String> map, NativeLoginOptions nativeLoginOptions, String str) {
            return NativeSession.createSession(nativeLoginControllerConfiguration, nativeTimerManagerThread, nativeApplicationScope, analyticsDelegate, connectivityPolicyProvider, connectionTypeProvider, credentialsStorage, unencryptedCredentials, nativeLogin5OAuthClient, map, nativeLoginOptions, str);
        }
    }

    private NativeSession() {
    }

    public static final native NativeSession createSession(NativeLoginControllerConfiguration nativeLoginControllerConfiguration, NativeTimerManagerThread nativeTimerManagerThread, NativeApplicationScope nativeApplicationScope, AnalyticsDelegate analyticsDelegate, ConnectivityPolicyProvider connectivityPolicyProvider, ConnectionTypeProvider connectionTypeProvider, CredentialsStorage credentialsStorage, UnencryptedCredentials unencryptedCredentials, NativeLogin5OAuthClient nativeLogin5OAuthClient, Map<String, String> map, NativeLoginOptions nativeLoginOptions, String str);

    public final native void blockingLogout();

    public final native void destroy();

    public final native String getCanonicalUsername();

    public final native Transport getInternalTransportToNative();

    public final native void prepareForShutdown();

    @Override // com.spotify.connectivity.auth.Session
    public native void tryReconnectNow(boolean z);
}
