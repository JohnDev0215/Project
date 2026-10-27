package org.chromium.net;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ProxyInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import defpackage.Is;
import defpackage.UK;
import defpackage.WK;
import defpackage.XK;
import org.chromium.net.ProxyChangeListener;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ProxyChangeListener {
    public final Looper a = Looper.myLooper();
    public final Handler b = new Handler(this.a);
    public long c;
    public ProxyReceiver d;
    public BroadcastReceiver e;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class ProxyReceiver extends BroadcastReceiver {
        public ProxyReceiver(WK wk) {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ProxyChangeListener.this.c(new Runnable(this, intent) { // from class: YK
                    public final ProxyChangeListener.ProxyReceiver s;
                    public final Intent t;

                    {
                        this.s = this;
                        this.t = intent;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ProxyChangeListener.this.b(ProxyChangeListener.a(this.t));
                    }
                });
            }
        }
    }

    public static XK a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        return XK.a((ProxyInfo) extras.get("android.intent.extra.PROXY_INFO"));
    }

    public static ProxyChangeListener create() {
        return new ProxyChangeListener();
    }

    public static String getProperty(String str) {
        return System.getProperty(str);
    }

    public final void b(XK xk) {
        long j = this.c;
        if (j == 0) {
            return;
        }
        if (xk != null) {
            N.MyoFZt$2(j, this, xk.a, xk.b, xk.c, xk.d);
        } else {
            N.MCIk73GZ(j, this);
        }
    }

    public final void c(Runnable runnable) {
        if (this.a == Looper.myLooper()) {
            runnable.run();
        } else {
            this.b.post(runnable);
        }
    }

    public void start(long j) {
        this.c = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROXY_CHANGE");
        ProxyReceiver proxyReceiver = new ProxyReceiver(null);
        this.d = proxyReceiver;
        if (Build.VERSION.SDK_INT < 23) {
            Is.a.registerReceiver(proxyReceiver, intentFilter);
            return;
        }
        Is.a.registerReceiver(proxyReceiver, new IntentFilter());
        UK uk = new UK(this);
        this.e = uk;
        Is.a.registerReceiver(uk, intentFilter);
    }

    public void stop() {
        this.c = 0L;
        Is.a.unregisterReceiver(this.d);
        BroadcastReceiver broadcastReceiver = this.e;
        if (broadcastReceiver != null) {
            Is.a.unregisterReceiver(broadcastReceiver);
        }
        this.d = null;
        this.e = null;
    }
}
