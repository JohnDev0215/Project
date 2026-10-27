package p;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

/* JADX INFO: loaded from: classes.dex */
public final class c12 implements cn1 {
    public final ConnectivityManager c;
    public final bn1 e;
    public final en1 k;

    public c12(ConnectivityManager connectivityManager, bn1 bn1Var) {
        this.c = connectivityManager;
        this.e = bn1Var;
        en1 en1Var = new en1(1, this);
        this.k = en1Var;
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), en1Var);
    }

    public static final void a(c12 c12Var, Network network, boolean z) {
        lq2 lq2Var;
        boolean z2 = false;
        for (Network network2 : c12Var.c.getAllNetworks()) {
            if (!v03.b(network2, network)) {
                NetworkCapabilities networkCapabilities = c12Var.c.getNetworkCapabilities(network2);
                if (networkCapabilities != null && networkCapabilities.hasCapability(12)) {
                    z2 = true;
                    break;
                }
            } else {
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        ai2 ai2Var = (ai2) c12Var.e;
        if (((coil.b) ai2Var.e.get()) != null) {
            ai2Var.l = z2;
            lq2Var = lq2.a;
        } else {
            lq2Var = null;
        }
        if (lq2Var == null) {
            ai2Var.a();
        }
    }

    @Override // p.cn1
    public final boolean isOnline() {
        ConnectivityManager connectivityManager = this.c;
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities != null && networkCapabilities.hasCapability(12)) {
                return true;
            }
        }
        return false;
    }

    @Override // p.cn1
    public final void shutdown() {
        this.c.unregisterNetworkCallback(this.k);
    }
}
