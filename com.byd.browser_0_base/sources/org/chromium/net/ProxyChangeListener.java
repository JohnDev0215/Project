package org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import defpackage.d72;
import defpackage.ed2;
import defpackage.xz;
import java.util.Locale;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes3.dex */
@UsedByReflection
public class ProxyChangeListener {
    public final Looper a;
    public final Handler b;
    public long c;
    public ProxyReceiver d;
    public d72 e;

    @UsedByReflection
    public class ProxyReceiver extends BroadcastReceiver {
        public static final /* synthetic */ int b = 0;

        public ProxyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        @UsedByReflection
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ed2 ed2Var = new ed2(this, 3, intent);
                ProxyChangeListener proxyChangeListener = ProxyChangeListener.this;
                if (proxyChangeListener.a == Looper.myLooper()) {
                    ed2Var.run();
                } else {
                    proxyChangeListener.b.post(ed2Var);
                }
            }
        }
    }

    public static class a {
        public static final a e = new a("", 0, "", new String[0]);
        public final String a;
        public final int b;
        public final String c;
        public final String[] d;

        public a(String str, int i, String str2, String[] strArr) {
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = strArr;
        }

        public static a a(ProxyInfo proxyInfo) {
            if (proxyInfo == null) {
                return null;
            }
            String host = proxyInfo.getHost();
            Uri pacFileUrl = proxyInfo.getPacFileUrl();
            if (host == null) {
                host = "";
            }
            return new a(host, proxyInfo.getPort(), Uri.EMPTY.equals(pacFileUrl) ? null : pacFileUrl.toString(), proxyInfo.getExclusionList());
        }

        public final String toString() {
            String str = this.a;
            if (!str.equals("localhost") && !str.isEmpty()) {
                str = "<redacted>";
            }
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = str;
            objArr[1] = Integer.valueOf(this.b);
            objArr[2] = this.c == null ? "null" : "\"<redacted>\"";
            return String.format(locale, "ProxyConfig [mHost=\"%s\", mPort=%d, mPacUrl=%s]", objArr);
        }
    }

    public ProxyChangeListener() {
        Looper looperMyLooper = Looper.myLooper();
        this.a = looperMyLooper;
        this.b = new Handler(looperMyLooper);
    }

    @CalledByNative
    public static ProxyChangeListener create() {
        return new ProxyChangeListener();
    }

    @CalledByNative
    public static String getProperty(String str) {
        return System.getProperty(str);
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROXY_CHANGE");
        ProxyReceiver proxyReceiver = new ProxyReceiver();
        this.d = proxyReceiver;
        xz.f(xz.a, proxyReceiver, new IntentFilter());
        d72 d72Var = new d72(this);
        this.e = d72Var;
        xz.g(xz.a, d72Var, intentFilter);
    }

    @CalledByNative
    public void start(long j) {
        TraceEvent traceEventH0 = TraceEvent.H0("ProxyChangeListener.start", null);
        try {
            this.c = j;
            a();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @CalledByNative
    public void stop() {
        this.c = 0L;
        xz.a.unregisterReceiver(this.d);
        d72 d72Var = this.e;
        if (d72Var != null) {
            xz.a.unregisterReceiver(d72Var);
        }
        this.d = null;
        this.e = null;
    }
}
