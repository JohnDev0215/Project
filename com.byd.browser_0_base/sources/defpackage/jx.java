package defpackage;

import J.N;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: ConnectivityDetector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jx implements NetworkChangeNotifier.a {
    public c e;
    public String j;
    public int l;
    public a n;
    public int h = 0;
    public int i = 0;
    public int k = 0;
    public final String g = "OfflineDetector";
    public final b f = new b();
    public final Handler m = new Handler();

    /* JADX INFO: compiled from: ConnectivityDetector.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            jx.this.b();
        }
    }

    /* JADX INFO: compiled from: ConnectivityDetector.java */
    public class b {
    }

    /* JADX INFO: compiled from: ConnectivityDetector.java */
    public interface c {
    }

    public jx(c cVar) {
        this.e = cVar;
        NetworkChangeNotifier.f.b.a(this);
        d(NetworkChangeNotifier.f.getCurrentConnectionType());
    }

    public final void a() {
        this.f.getClass();
        boolean z = this.k == 2;
        new ix(this, z ? "https://www.google.com/generate_204" : "http://connectivitycheck.gstatic.com/generate_204", new dm0(15, this)).c(i9.e);
    }

    public final void b() {
        int i = 1;
        this.k = 1;
        int i2 = 0;
        this.l = 0;
        SystemClock.elapsedRealtime();
        this.f.getClass();
        ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
        if (connectivityManager == null) {
            i = 0;
        } else {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            if (allNetworks.length != 0) {
                int length = allNetworks.length;
                boolean z = false;
                while (true) {
                    if (i2 < length) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(allNetworks[i2]);
                        if (networkCapabilities != null) {
                            Log.i("cr_OfflineIndicator", "Reported by system: " + networkCapabilities.toString());
                            if (networkCapabilities.hasCapability(16) && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(13)) {
                                i = 4;
                                break;
                            } else if (networkCapabilities.hasCapability(17)) {
                                z = true;
                            }
                        }
                        i2++;
                    } else {
                        i = z ? 3 : 2;
                    }
                }
            }
        }
        if (i != 0) {
            f(i);
            c();
        } else {
            if (this.j == null) {
                this.j = N.M5LbL2nl();
            }
            this.k = 2;
            a();
        }
    }

    public final void c() {
        Log.i("cr_OfflineIndicator", "processConnectivityCheckResult mConnectionState=" + this.i + " mConnectivityCheckingStage=" + this.k);
        if (this.i == 4) {
            g();
            return;
        }
        int i = this.k;
        if (i == 1) {
            e();
            return;
        }
        if (i == 2) {
            this.k = 3;
            a();
        } else {
            if (i != 3) {
                return;
            }
            this.k = 2;
            e();
        }
    }

    @Override // org.chromium.net.NetworkChangeNotifier.a
    public final void d(int i) {
        boolean z = this.h != i;
        this.h = i;
        Log.i("cr_OfflineIndicator", "onConnectionTypeChanged " + this.h);
        if (this.h == 6) {
            f(1);
            g();
        } else if (z || this.k == 0) {
            g();
            b();
        }
    }

    public final void e() {
        Log.i("cr_OfflineIndicator", "scheduleNextConnectivityCheck");
        int i = this.l;
        if (i == 0) {
            this.l = 5000;
        } else {
            this.l = i * 2;
        }
        if (this.l >= 120000) {
            this.l = 120000;
            Log.i("cr_OfflineIndicator", "No more retry after exceeding 120000ms");
            if (this.i == 0) {
                f(2);
                return;
            }
            return;
        }
        Log.i("cr_OfflineIndicator", "Retry after " + this.l + "ms");
        a aVar = new a();
        this.n = aVar;
        this.m.postDelayed(aVar, (long) this.l);
    }

    public final void f(int i) {
        Log.i("cr_OfflineIndicator", "setConnectionState connectionState=" + i);
        if (this.i == i) {
            return;
        }
        this.i = i;
        c cVar = this.e;
        if (cVar != null) {
            iq1 iq1Var = (iq1) cVar;
            boolean z = iq1Var.i;
            boolean z2 = i != 4;
            iq1Var.i = z2;
            if (iq1Var.q && z == z2) {
                return;
            }
            if (z2) {
                iq1Var.p = SystemClock.elapsedRealtime();
            }
            if ((iq1Var.q && !z) || !iq1Var.i) {
                iq1Var.r = SystemClock.elapsedRealtime();
            }
            iq1Var.q = true;
            iq1Var.a();
        }
    }

    public final void g() {
        if (this.k == 0) {
            return;
        }
        a aVar = this.n;
        if (aVar != null) {
            this.m.removeCallbacks(aVar);
            this.n = null;
        }
        this.k = 0;
    }
}
