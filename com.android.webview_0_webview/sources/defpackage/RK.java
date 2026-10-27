package defpackage;

import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class RK {
    public TK a;

    public abstract void c();

    public abstract void d(TK tk);

    public final void e() {
        NetworkCapabilities networkCapabilitiesC;
        TK tk = this.a;
        if (tk.k) {
            tk.b();
            return;
        }
        if (tk.n) {
            tk.b();
        }
        IK ik = tk.f;
        if (ik != null) {
            try {
                tk.g.a.registerDefaultNetworkCallback(ik, tk.b);
            } catch (RuntimeException unused) {
                tk.f = null;
            }
        }
        if (tk.f == null) {
            tk.m = Is.a.registerReceiver(tk, tk.c) != null;
        }
        tk.k = true;
        OK ok = tk.i;
        if (ok != null) {
            Network[] networkArrC = TK.c(ok.b.g, null);
            ok.a = null;
            if (networkArrC.length == 1 && (networkCapabilitiesC = ok.b.g.c(networkArrC[0])) != null && networkCapabilitiesC.hasTransport(4)) {
                ok.a = networkArrC[0];
            }
            try {
                HK hk = tk.g;
                NetworkRequest networkRequest = tk.j;
                OK ok2 = tk.i;
                Handler handler = tk.b;
                if (Build.VERSION.SDK_INT >= 26) {
                    hk.a.registerNetworkCallback(networkRequest, ok2, handler);
                } else {
                    hk.a.registerNetworkCallback(networkRequest, ok2);
                }
            } catch (RuntimeException unused2) {
                tk.o = true;
                tk.i = null;
            }
            if (tk.o || !tk.n) {
                return;
            }
            Network[] networkArrC2 = TK.c(tk.g, null);
            long[] jArr = new long[networkArrC2.length];
            for (int i = 0; i < networkArrC2.length; i++) {
                jArr[i] = TK.e(networkArrC2[i]);
            }
            tk.d.b(jArr);
        }
    }
}
