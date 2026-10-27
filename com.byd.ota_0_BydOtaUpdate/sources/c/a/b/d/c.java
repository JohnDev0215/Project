package c.a.b.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

/* JADX INFO: compiled from: ConnectivityManagerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2332a = "c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f2333b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static c f2334c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f2335d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConnectivityManager f2336e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f2337f;

    /* JADX INFO: compiled from: ConnectivityManagerUtil.java */
    public class a extends ConnectivityManager.NetworkCallback {
        public a() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            e.c(c.f2332a, "APN network is ok");
            boolean unused = c.f2333b = true;
            if (c.this.f2337f != null) {
                c.this.f2337f.a();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            e.c(c.f2332a, "APN network is lost");
            boolean unused = c.f2333b = false;
            if (c.this.f2337f != null) {
                c.this.f2337f.b();
            }
        }
    }

    /* JADX INFO: compiled from: ConnectivityManagerUtil.java */
    public interface b {
        void a();

        void b();
    }

    public c() {
        throw new UnsupportedOperationException("ConnectivityManagerUtil: U can't new me");
    }

    public static c e(Context context) {
        if (f2334c == null) {
            synchronized (c.class) {
                if (f2334c == null) {
                    f2334c = new c(context);
                }
            }
        }
        return f2334c;
    }

    public boolean d() {
        e.c(f2332a, "getAPNState->isAPNok = " + f2333b);
        return f2333b;
    }

    public void f(b bVar) {
        this.f2337f = bVar;
        e.c(f2332a, "registerEthernetNetwork->isAPNok = " + f2333b);
        if (f2333b) {
            this.f2337f.a();
        } else {
            this.f2337f.b();
        }
    }

    public void g() {
        this.f2336e.requestNetwork(new NetworkRequest.Builder().addTransportType(3).build(), new a());
    }

    public void h(b bVar) {
        if (this.f2337f != null) {
            this.f2337f = null;
        }
    }

    public c(Context context) {
        this.f2335d = context;
        this.f2336e = (ConnectivityManager) context.getSystemService("connectivity");
    }
}
