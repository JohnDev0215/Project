package com.spotify.connectivity.connectivitysdkpolicyimpl;

import com.spotify.connectivity.AnalyticsDelegate;
import com.spotify.connectivity.ConnectionType;
import com.spotify.connectivity.Scheduler;
import com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager;
import com.spotify.cosmos.cosmosimpl.NativeRouter;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeConnectivityManager implements ConnectivityManager {
    public static final Companion Companion = new Companion(null);
    private boolean isNetworkAllowed;
    private boolean isNetworkAllowedIfRoaming;
    private final boolean isRoaming;
    private boolean isSyncAllowedOverCellular;
    private boolean isSyncAllowedOverWifi;
    private long nThis;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeConnectivityManager create(NativeRouter nativeRouter, Scheduler scheduler, AnalyticsDelegate analyticsDelegate, boolean z) {
            return NativeConnectivityManager.create(nativeRouter, scheduler, analyticsDelegate, z);
        }
    }

    private NativeConnectivityManager() {
    }

    public static final native NativeConnectivityManager create(NativeRouter nativeRouter, Scheduler scheduler, AnalyticsDelegate analyticsDelegate, boolean z);

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native void destroy();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native ConnectionType getConnectionType();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native boolean isNetworkAllowed();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native boolean isNetworkAllowedIfRoaming();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native boolean isRoaming();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native boolean isSyncAllowedOverCellular();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native boolean isSyncAllowedOverWifi();

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native void setConnectionType(ConnectionType connectionType, boolean z);

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native void setNetworkAllowed(boolean z);

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native void setNetworkAllowedIfRoaming(boolean z);

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native void setSyncAllowedOverCellular(boolean z);

    @Override // com.spotify.connectivity.connectivitysdkpolicy.ConnectivityManager
    public native void setSyncAllowedOverWifi(boolean z);
}
