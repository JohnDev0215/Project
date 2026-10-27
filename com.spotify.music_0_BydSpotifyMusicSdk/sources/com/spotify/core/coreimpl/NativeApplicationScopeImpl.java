package com.spotify.core.coreimpl;

import com.spotify.concurrency.async.NativeTimerManagerThread;
import com.spotify.connectivity.auth.NativeLoginController;
import com.spotify.core.core.ApplicationScope;
import com.spotify.core.core.NativeApplicationScope;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import com.spotify.eventsender.api.EventSenderCoreBridge;
import com.spotify.prefs.prefsimpl.NativePrefs;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeApplicationScopeImpl implements NativeApplicationScope, ApplicationScope {
    public static final Companion Companion = new Companion(null);
    private final byte[] cacheId;
    private final byte[] cacheSalt;
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeApplicationScopeImpl create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativePrefs nativePrefs, ApplicationScopeConfiguration applicationScopeConfiguration, NativeLoginController nativeLoginController, EventSenderCoreBridge eventSenderCoreBridge) {
            return NativeApplicationScopeImpl.create(nativeTimerManagerThread, nativeRouter, nativePrefs, applicationScopeConfiguration, nativeLoginController, eventSenderCoreBridge);
        }
    }

    private NativeApplicationScopeImpl() {
    }

    public static final native NativeApplicationScopeImpl create(NativeTimerManagerThread nativeTimerManagerThread, NativeRouter nativeRouter, NativePrefs nativePrefs, ApplicationScopeConfiguration applicationScopeConfiguration, NativeLoginController nativeLoginController, EventSenderCoreBridge eventSenderCoreBridge);

    @Override // com.spotify.core.core.NativeApplicationScope
    public native void destroy();

    @Override // com.spotify.core.core.NativeApplicationScope
    public native byte[] getCacheId();

    @Override // com.spotify.core.core.NativeApplicationScope
    public native byte[] getCacheSalt();

    @Override // com.spotify.core.core.NativeApplicationScope
    public long getNThis() {
        return this.nThis;
    }

    public final native void prepareForShutdown();
}
