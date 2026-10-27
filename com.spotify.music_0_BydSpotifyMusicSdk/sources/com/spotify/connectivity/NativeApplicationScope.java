package com.spotify.connectivity;

import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.connectivity.auth.CredentialsStorage;
import com.spotify.connectivity.auth.Login5Configuration;
import com.spotify.connectivity.auth.NativeLogin5OAuthClient;
import com.spotify.connectivity.auth.NativeLoginController;
import com.spotify.connectivity.auth.NativeSerializableCredentialsTransferManager;
import com.spotify.connectivity.auth.SerializableCredentialsTransferManager;
import com.spotify.connectivity.auth.UnencryptedCredentials;
import com.spotify.connectivity.auth.oauth.NativeAccessToken;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.esperanto.esperanto.Transport;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeApplicationScope implements ApplicationScope {
    public static final Companion Companion = new Companion(null);
    private final NativeSerializableCredentialsTransferManager _serializableCredentialsTransferManager = new NativeSerializableCredentialsTransferManager();
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeApplicationScope create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, AnalyticsDelegate analyticsDelegate, ConnectionTypeProvider connectionTypeProvider, ConnectivityPolicyProvider connectivityPolicyProvider, CredentialsStorage credentialsStorage, ApplicationScopeConfiguration applicationScopeConfiguration) {
            return NativeApplicationScope.create(nativeTimerManagerThread, nativeRouter, analyticsDelegate, connectionTypeProvider, connectivityPolicyProvider, credentialsStorage, applicationScopeConfiguration);
        }
    }

    private NativeApplicationScope() {
    }

    public static final native NativeApplicationScope create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, AnalyticsDelegate analyticsDelegate, ConnectionTypeProvider connectionTypeProvider, ConnectivityPolicyProvider connectivityPolicyProvider, CredentialsStorage credentialsStorage, ApplicationScopeConfiguration applicationScopeConfiguration);

    public final native NativeLogin5OAuthClient createLogin5OAuthClient(Login5Configuration login5Configuration, NativeTimerManagerThread nativeTimerManagerThread, NativeAccessToken nativeAccessToken, UnencryptedCredentials unencryptedCredentials);

    public final native NativeLoginController createLoginController(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, Object obj);

    public final native void destroy();

    public final native Transport getNativeAuthStorageTransport();

    public final native Transport getNativeLogin5Transport();

    public final native Transport getNativeTransport();

    @Override // com.spotify.connectivity.ApplicationScope
    public SerializableCredentialsTransferManager getSerializableCredentialsTransferManager() {
        return this._serializableCredentialsTransferManager;
    }

    @Override // com.spotify.connectivity.ApplicationScope
    public native void prepareForShutdown();
}
