package org.chromium.net;

import J.N;
import android.annotation.SuppressLint;
import android.net.Network;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkChangeNotifier {

    @SuppressLint({"StaticFieldLeak"})
    public static NetworkChangeNotifier f;
    public NetworkChangeNotifierAutoDetect c;
    public int d = 0;
    public int e = 0;
    public final ArrayList<Long> a = new ArrayList<>();
    public final org.chromium.base.b<a> b = new org.chromium.base.b<>();

    public interface a {
        void d(int i);
    }

    public static boolean a() {
        return f.getCurrentConnectionType() != 6;
    }

    public static void f(a aVar) {
        f.b.c(aVar);
    }

    @CalledByNative
    public static void fakeConnectionCostChanged(int i) {
        setAutoDetectConnectivityState(false);
        f.b(i);
    }

    @CalledByNative
    public static void fakeConnectionSubtypeChanged(int i) {
        setAutoDetectConnectivityState(false);
        f.c(i);
    }

    @CalledByNative
    public static void fakeDefaultNetwork(long j, int i) {
        setAutoDetectConnectivityState(false);
        f.d(i, j);
    }

    @CalledByNative
    public static void fakeNetworkConnected(long j, int i) {
        setAutoDetectConnectivityState(false);
        f.e(i, j);
    }

    @CalledByNative
    public static void fakeNetworkDisconnected(long j) {
        setAutoDetectConnectivityState(false);
        NetworkChangeNotifier networkChangeNotifier = f;
        Iterator<Long> it = networkChangeNotifier.a.iterator();
        while (it.hasNext()) {
            N.MDpuHJTB(it.next().longValue(), networkChangeNotifier, j);
        }
    }

    @CalledByNative
    public static void fakeNetworkSoonToBeDisconnected(long j) {
        setAutoDetectConnectivityState(false);
        NetworkChangeNotifier networkChangeNotifier = f;
        Iterator<Long> it = networkChangeNotifier.a.iterator();
        while (it.hasNext()) {
            N.MiJIMrTb(it.next().longValue(), networkChangeNotifier, j);
        }
    }

    @CalledByNative
    public static void fakePurgeActiveNetworkList(long[] jArr) {
        setAutoDetectConnectivityState(false);
        NetworkChangeNotifier networkChangeNotifier = f;
        Iterator<Long> it = networkChangeNotifier.a.iterator();
        while (it.hasNext()) {
            N.MpF$179U(it.next().longValue(), networkChangeNotifier, jArr);
        }
    }

    @CalledByNative
    public static void forceConnectivityState(boolean z) {
        setAutoDetectConnectivityState(false);
        NetworkChangeNotifier networkChangeNotifier = f;
        if ((networkChangeNotifier.d != 6) != z) {
            int i = z ? 0 : 6;
            networkChangeNotifier.d = i;
            networkChangeNotifier.d(i, networkChangeNotifier.getCurrentDefaultNetId());
            networkChangeNotifier.c(!z ? 1 : 0);
        }
    }

    @CalledByNative
    public static NetworkChangeNotifier init() {
        if (f == null) {
            f = new NetworkChangeNotifier();
        }
        return f;
    }

    @CalledByNative
    public static void setAutoDetectConnectivityState(boolean z) {
        NetworkChangeNotifier networkChangeNotifier = f;
        c cVar = new c();
        if (!z) {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = networkChangeNotifier.c;
            if (networkChangeNotifierAutoDetect != null) {
                networkChangeNotifierAutoDetect.e.a();
                networkChangeNotifierAutoDetect.f();
                networkChangeNotifier.c = null;
                return;
            }
            return;
        }
        if (networkChangeNotifier.c == null) {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect2 = new NetworkChangeNotifierAutoDetect(new org.chromium.net.a(networkChangeNotifier), cVar);
            networkChangeNotifier.c = networkChangeNotifierAutoDetect2;
            NetworkChangeNotifierAutoDetect.f fVarD = networkChangeNotifierAutoDetect2.d();
            int iB = fVarD.b();
            networkChangeNotifier.d = iB;
            networkChangeNotifier.d(iB, networkChangeNotifier.getCurrentDefaultNetId());
            int i = fVarD.d ? 2 : 1;
            networkChangeNotifier.e = i;
            networkChangeNotifier.b(i);
            networkChangeNotifier.c(fVarD.a());
        }
    }

    @CalledByNative
    public void addNativeObserver(long j) {
        this.a.add(Long.valueOf(j));
    }

    public final void b(int i) {
        Iterator<Long> it = this.a.iterator();
        while (it.hasNext()) {
            N.Mg0W7eRL(it.next().longValue(), this, i);
        }
    }

    public final void c(int i) {
        Iterator<Long> it = this.a.iterator();
        while (it.hasNext()) {
            N.Mt26m31j(it.next().longValue(), this, i);
        }
    }

    public final void d(int i, long j) {
        Iterator<Long> it = this.a.iterator();
        while (it.hasNext()) {
            N.MbPIImnU(it.next().longValue(), this, i, j);
        }
        Iterator<a> it2 = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it2;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).d(i);
            }
        }
    }

    public final void e(int i, long j) {
        Iterator<Long> it = this.a.iterator();
        while (it.hasNext()) {
            N.MBT1i5cd(it.next().longValue(), this, j, i);
        }
    }

    @CalledByNative
    public int getCurrentConnectionCost() {
        return this.e;
    }

    @CalledByNative
    public int getCurrentConnectionSubtype() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.c;
        if (networkChangeNotifierAutoDetect == null) {
            return 0;
        }
        return networkChangeNotifierAutoDetect.d().a();
    }

    @CalledByNative
    public int getCurrentConnectionType() {
        return this.d;
    }

    @CalledByNative
    public long getCurrentDefaultNetId() {
        Network networkB;
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.c;
        if (networkChangeNotifierAutoDetect == null || (networkB = networkChangeNotifierAutoDetect.g.b()) == null) {
            return -1L;
        }
        return networkB.getNetworkHandle();
    }

    @CalledByNative
    public long[] getCurrentNetworksAndTypes() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.c;
        if (networkChangeNotifierAutoDetect == null) {
            return new long[0];
        }
        Network[] networkArrC = NetworkChangeNotifierAutoDetect.c(networkChangeNotifierAutoDetect.g, null);
        long[] jArr = new long[networkArrC.length * 2];
        int i = 0;
        for (Network network : networkArrC) {
            int i2 = i + 1;
            jArr[i] = network.getNetworkHandle();
            i = i2 + 1;
            jArr[i2] = r9.a(r5);
        }
        return jArr;
    }

    @CalledByNative
    public boolean registerNetworkCallbackFailed() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.c;
        if (networkChangeNotifierAutoDetect == null) {
            return false;
        }
        return networkChangeNotifierAutoDetect.n;
    }

    @CalledByNative
    public void removeNativeObserver(long j) {
        this.a.remove(Long.valueOf(j));
    }
}
