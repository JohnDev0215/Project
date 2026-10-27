package spi;

import android.car.utils.Logs;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.annotation.NonNull;
import com.android.car.media.netObserver.NetStateChangeReceiver;

/* JADX INFO: compiled from: NetStateChangeReceiver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x7 extends ConnectivityManager.NetworkCallback {
    public x7(NetStateChangeReceiver netStateChangeReceiver) {
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        Logs.e("NetStateChangeReceiver", " NetStateChangeReceiver --> onAvailable:");
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        StringBuilder sbK = c.k(" NetStateChangeReceiver --> onCapabilitiesChanged:");
        sbK.append(networkCapabilities.hasCapability(16));
        Logs.e("NetStateChangeReceiver", sbK.toString());
        synchronized (NetStateChangeReceiver.class) {
            NetStateChangeReceiver.c.set(networkCapabilities.hasCapability(16));
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(@NonNull Network network) {
        super.onLost(network);
        Logs.e("NetStateChangeReceiver", " NetStateChangeReceiver --> onLost:");
    }
}
