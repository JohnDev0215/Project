package defpackage;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OK extends ConnectivityManager.NetworkCallback {
    public Network a;
    public final /* synthetic */ TK b;

    public OK(TK tk, GK gk) {
        this.b = tk;
    }

    public final boolean a(Network network, NetworkCapabilities networkCapabilities) {
        Network network2 = this.a;
        if (!((network2 == null || network2.equals(network)) ? false : true)) {
            if (networkCapabilities == null) {
                networkCapabilities = this.b.g.a.getNetworkCapabilities(network);
            }
            if (!(networkCapabilities == null || (networkCapabilities.hasTransport(4) && !this.b.g.d(network)))) {
                return false;
            }
        }
        return true;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        NetworkCapabilities networkCapabilities = this.b.g.a.getNetworkCapabilities(network);
        if (a(network, networkCapabilities)) {
            return;
        }
        boolean zHasTransport = networkCapabilities.hasTransport(4);
        if (zHasTransport) {
            this.a = network;
        }
        this.b.f(new JK(this, TK.e(network), this.b.g.a(network), zHasTransport));
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        if (a(network, networkCapabilities)) {
            return;
        }
        this.b.f(new KK(this, TK.e(network), this.b.g.a(network)));
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLosing(Network network, int i) {
        if (a(network, null)) {
            return;
        }
        this.b.f(new LK(this, TK.e(network)));
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        Network network2 = this.a;
        if ((network2 == null || network2.equals(network)) ? false : true) {
            return;
        }
        this.b.f(new MK(this, network));
        if (this.a != null) {
            this.a = null;
            for (Network network3 : TK.c(this.b.g, network)) {
                onAvailable(network3);
            }
            this.b.f(new NK(this, this.b.d().b()));
        }
    }
}
