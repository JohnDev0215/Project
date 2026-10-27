package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import org.chromium.net.ProxyChangeListener;

/* JADX INFO: compiled from: ProxyBroadcastReceiver.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d72 extends BroadcastReceiver {
    public final ProxyChangeListener a;

    public d72(ProxyChangeListener proxyChangeListener) {
        this.a = proxyChangeListener;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
            ProxyChangeListener proxyChangeListener = this.a;
            proxyChangeListener.getClass();
            ul2 ul2Var = new ul2(proxyChangeListener, 6, intent);
            if (proxyChangeListener.a == Looper.myLooper()) {
                ul2Var.run();
            } else {
                proxyChangeListener.b.post(ul2Var);
            }
        }
    }
}
