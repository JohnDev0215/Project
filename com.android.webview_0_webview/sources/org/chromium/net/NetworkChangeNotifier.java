package org.chromium.net;

import J.N;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import defpackage.C0489aL;
import defpackage.C1185mt;
import defpackage.EK;
import defpackage.FK;
import defpackage.Is;
import defpackage.PK;
import defpackage.RK;
import defpackage.TK;
import defpackage.Ws;
import defpackage.Ys;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NetworkChangeNotifier {
    public static NetworkChangeNotifier f;
    public TK d;
    public int e = 0;
    public final ArrayList a = new ArrayList();
    public final Ys b = new Ys();
    public final ConnectivityManager c = (ConnectivityManager) Is.a.getSystemService("connectivity");

    public static void fakeConnectionSubtypeChanged(int i) {
        g(false);
        f.a(i);
    }

    public static void fakeDefaultNetwork(long j, int i) {
        g(false);
        f.b(i, j);
    }

    public static void fakeNetworkConnected(long j, int i) {
        g(false);
        f.c(j, i);
    }

    public static void fakeNetworkDisconnected(long j) {
        g(false);
        f.d(j);
    }

    public static void fakeNetworkSoonToBeDisconnected(long j) {
        g(false);
        f.e(j);
    }

    public static void fakePurgeActiveNetworkList(long[] jArr) {
        g(false);
        f.f(jArr);
    }

    public static void forceConnectivityState(boolean z) {
        g(false);
        NetworkChangeNotifier networkChangeNotifier = f;
        if ((networkChangeNotifier.e != 6) != z) {
            networkChangeNotifier.i(z ? 0 : 6);
            networkChangeNotifier.a(!z ? 1 : 0);
        }
    }

    public static void g(boolean z) {
        f.h(z, new C0489aL());
    }

    public static NetworkChangeNotifier init() {
        if (f == null) {
            f = new NetworkChangeNotifier();
        }
        return f;
    }

    public static boolean isProcessBoundToNetwork() {
        NetworkChangeNotifier networkChangeNotifier = f;
        Objects.requireNonNull(networkChangeNotifier);
        if (Build.VERSION.SDK_INT < 23) {
            if (ConnectivityManager.getProcessDefaultNetwork() != null) {
                return true;
            }
        } else if (C1185mt.c(networkChangeNotifier.c) != null) {
            return true;
        }
        return false;
    }

    public void a(int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            N.Mt26m31j(((Long) it.next()).longValue(), this, i);
        }
    }

    public void addNativeObserver(long j) {
        this.a.add(Long.valueOf(j));
    }

    public final void b(int i, long j) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            N.MbPIImnU(((Long) it.next()).longValue(), this, i, j);
        }
        Iterator it2 = this.b.iterator();
        while (true) {
            Ws ws = (Ws) it2;
            if (!ws.hasNext()) {
                return;
            } else {
                ((FK) ws.next()).a(i);
            }
        }
    }

    public void c(long j, int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            N.MBT1i5cd(((Long) it.next()).longValue(), this, j, i);
        }
    }

    public void d(long j) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            N.MDpuHJTB(((Long) it.next()).longValue(), this, j);
        }
    }

    public void e(long j) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            N.MiJIMrTb(((Long) it.next()).longValue(), this, j);
        }
    }

    public void f(long[] jArr) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            N.MpF$179U(((Long) it.next()).longValue(), this, jArr);
        }
    }

    public int getCurrentConnectionSubtype() {
        TK tk = this.d;
        if (tk == null) {
            return 0;
        }
        return tk.d().a();
    }

    public int getCurrentConnectionType() {
        return this.e;
    }

    public long getCurrentDefaultNetId() {
        Network networkB;
        TK tk = this.d;
        if (tk == null || (networkB = tk.g.b()) == null) {
            return -1L;
        }
        return TK.e(networkB);
    }

    public long[] getCurrentNetworksAndTypes() {
        TK tk = this.d;
        if (tk == null) {
            return new long[0];
        }
        Network[] networkArrC = TK.c(tk.g, null);
        long[] jArr = new long[networkArrC.length * 2];
        int i = 0;
        for (Network network : networkArrC) {
            int i2 = i + 1;
            jArr[i] = TK.e(network);
            i = i2 + 1;
            jArr[i2] = tk.g.a(r6);
        }
        return jArr;
    }

    public final void h(boolean z, RK rk) {
        if (!z) {
            TK tk = this.d;
            if (tk != null) {
                tk.e.c();
                tk.g();
                this.d = null;
                return;
            }
            return;
        }
        if (this.d == null) {
            TK tk2 = new TK(new EK(this), rk);
            this.d = tk2;
            PK pkD = tk2.d();
            i(pkD.b());
            a(pkD.a());
        }
    }

    public final void i(int i) {
        this.e = i;
        b(i, getCurrentDefaultNetId());
    }

    public boolean registerNetworkCallbackFailed() {
        TK tk = this.d;
        if (tk == null) {
            return false;
        }
        return tk.o;
    }

    public void removeNativeObserver(long j) {
        this.a.remove(Long.valueOf(j));
    }
}
