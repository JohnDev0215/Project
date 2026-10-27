package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.ProxyInfo;
import android.os.Build;
import java.util.Objects;
import org.chromium.net.ProxyChangeListener;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class UK extends BroadcastReceiver {
    public final ProxyChangeListener a;

    public UK(ProxyChangeListener proxyChangeListener) {
        this.a = proxyChangeListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, final Intent intent) {
        if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
            final ProxyChangeListener proxyChangeListener = this.a;
            proxyChangeListener.c(new Runnable(proxyChangeListener, intent) { // from class: VK
                public final ProxyChangeListener s;
                public final Intent t;

                {
                    this.s = proxyChangeListener;
                    this.t = intent;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ProxyChangeListener proxyChangeListener2 = this.s;
                    Intent intent2 = this.t;
                    Objects.requireNonNull(proxyChangeListener2);
                    ProxyInfo defaultProxy = ((ConnectivityManager) Is.a.getSystemService("connectivity")).getDefaultProxy();
                    proxyChangeListener2.b(defaultProxy == null ? XK.e : (Build.VERSION.SDK_INT == 29 && defaultProxy.getHost().equals("localhost") && defaultProxy.getPort() == -1) ? ProxyChangeListener.a(intent2) : XK.a(defaultProxy));
                }
            });
        }
    }
}
