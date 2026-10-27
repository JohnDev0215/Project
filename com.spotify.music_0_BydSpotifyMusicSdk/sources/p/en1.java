package p;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* JADX INFO: loaded from: classes.dex */
public final class en1 extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ en1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        switch (this.a) {
            case 1:
                c12.a((c12) this.b, network, true);
                break;
            default:
                super.onAvailable(network);
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        switch (this.a) {
            case 0:
                v03.j(network, "network");
                v03.j(networkCapabilities, "capabilities");
                vc1 vc1VarC = vc1.c();
                String str = gn1.a;
                networkCapabilities.toString();
                vc1VarC.getClass();
                fn1 fn1Var = (fn1) this.b;
                fn1Var.b(gn1.a(fn1Var.f));
                break;
            default:
                super.onCapabilitiesChanged(network, networkCapabilities);
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                v03.j(network, "network");
                vc1 vc1VarC = vc1.c();
                String str = gn1.a;
                vc1VarC.getClass();
                fn1 fn1Var = (fn1) obj;
                fn1Var.b(gn1.a(fn1Var.f));
                break;
            default:
                c12.a((c12) obj, network, false);
                break;
        }
    }
}
