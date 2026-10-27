package com.byd.vrassistant.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkMonitor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/byd/vrassistant/utils/NetworkMonitor;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "isNetworkAvailable", "", "startNetworkCallback", "", "netCallback", "Lcom/byd/vrassistant/utils/NetStatusCallback;", "Companion", "vr-ui_8155Release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NetworkMonitor {
    public static final String TAG = "NEU_NetworkMonitor";
    private final ConnectivityManager connectivityManager;

    public NetworkMonitor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.connectivityManager = (ConnectivityManager) systemService;
    }

    public final void startNetworkCallback(final NetStatusCallback netCallback) {
        Intrinsics.checkNotNullParameter(netCallback, "netCallback");
        this.connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() { // from class: com.byd.vrassistant.utils.NetworkMonitor$startNetworkCallback$networkCallback$1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                Intrinsics.checkNotNullParameter(network, "network");
                super.onAvailable(network);
                NetworkCapabilities networkCapabilities = this.this$0.connectivityManager.getNetworkCapabilities(network);
                boolean z = networkCapabilities != null && networkCapabilities.hasCapability(12);
                Log.i(NetworkMonitor.TAG, "onAvailable: " + z);
                netCallback.onNetStatusChanged(z);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                Intrinsics.checkNotNullParameter(network, "network");
                super.onLost(network);
                netCallback.onNetStatusChanged(false);
            }
        });
    }

    public final boolean isNetworkAvailable() {
        NetworkCapabilities networkCapabilities = this.connectivityManager.getNetworkCapabilities(this.connectivityManager.getActiveNetwork());
        boolean z = networkCapabilities != null && networkCapabilities.hasCapability(12);
        Log.i(TAG, "isNetworkAvailable: " + z);
        return z;
    }
}
