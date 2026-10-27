package defpackage;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IK extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ TK a;

    public IK(TK tk, GK gk) {
        this.a = tk;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        TK tk = this.a;
        if (tk.k) {
            tk.b();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        onAvailable(null);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        onAvailable(null);
    }
}
