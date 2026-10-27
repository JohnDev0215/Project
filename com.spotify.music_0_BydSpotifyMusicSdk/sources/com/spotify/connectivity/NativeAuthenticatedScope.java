package com.spotify.connectivity;

import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.connectivity.auth.NativeSession;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeAuthenticatedScope implements AuthenticatedScope {
    public static final Companion Companion = new Companion(null);
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeAuthenticatedScope create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativeApplicationScope nativeApplicationScope, NativeSession nativeSession, AnalyticsDelegate analyticsDelegate, RCSSnapshotIdProvider rCSSnapshotIdProvider, AuthenticatedScopeConfiguration authenticatedScopeConfiguration) {
            return NativeAuthenticatedScope.create(nativeTimerManagerThread, nativeRouter, nativeApplicationScope, nativeSession, analyticsDelegate, rCSSnapshotIdProvider, authenticatedScopeConfiguration);
        }
    }

    private NativeAuthenticatedScope() {
    }

    public static final native NativeAuthenticatedScope create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativeApplicationScope nativeApplicationScope, NativeSession nativeSession, AnalyticsDelegate analyticsDelegate, RCSSnapshotIdProvider rCSSnapshotIdProvider, AuthenticatedScopeConfiguration authenticatedScopeConfiguration);

    public final native void destroy();

    @Override // com.spotify.connectivity.AuthenticatedScope
    public native void prepareForShutdown();
}
