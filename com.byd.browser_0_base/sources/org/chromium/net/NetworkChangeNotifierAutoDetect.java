package org.chromium.net;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import defpackage.xz;
import defpackage.y70;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"NewApi"})
public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver {
    public static final /* synthetic */ int o = 0;
    public final Looper a;
    public final Handler b;
    public final NetworkConnectivityIntentFilter c;
    public final g d;
    public final h e;
    public ConnectivityManager.NetworkCallback f;
    public final c g;
    public e h;
    public final NetworkRequest i;
    public boolean j;
    public f k;
    public boolean l;
    public final boolean m;
    public boolean n;

    public static class NetworkConnectivityIntentFilter extends IntentFilter {
        public NetworkConnectivityIntentFilter() {
            addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            if (networkChangeNotifierAutoDetect.l) {
                networkChangeNotifierAutoDetect.l = false;
            } else {
                networkChangeNotifierAutoDetect.b(networkChangeNotifierAutoDetect.d());
            }
        }
    }

    public class b extends ConnectivityManager.NetworkCallback {
        public LinkProperties a;
        public NetworkCapabilities b;

        public b() {
        }

        public final f a(Network network) {
            int i;
            int subtype;
            int type;
            if (!this.b.hasTransport(1) && !this.b.hasTransport(5)) {
                boolean zHasTransport = this.b.hasTransport(0);
                NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
                if (zHasTransport) {
                    NetworkInfo networkInfoE = networkChangeNotifierAutoDetect.g.e(network);
                    subtype = networkInfoE != null ? networkInfoE.getSubtype() : -1;
                    i = 0;
                } else {
                    if (this.b.hasTransport(3)) {
                        type = 9;
                    } else if (this.b.hasTransport(2)) {
                        type = 7;
                    } else if (this.b.hasTransport(4)) {
                        NetworkInfo networkInfoD = networkChangeNotifierAutoDetect.g.d(network);
                        type = networkInfoD != null ? networkInfoD.getType() : 17;
                    } else {
                        i = -1;
                        subtype = -1;
                    }
                    i = type;
                }
                boolean z = !this.b.hasCapability(11);
                int i2 = NetworkChangeNotifierAutoDetect.o;
                return new f(true, i, subtype, z, String.valueOf(network.getNetworkHandle()), this.a.isPrivateDnsActive(), this.a.getPrivateDnsServerName());
            }
            i = 1;
            subtype = -1;
            boolean z2 = !this.b.hasCapability(11);
            int i22 = NetworkChangeNotifierAutoDetect.o;
            return new f(true, i, subtype, z2, String.valueOf(network.getNetworkHandle()), this.a.isPrivateDnsActive(), this.a.getPrivateDnsServerName());
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            this.a = null;
            this.b = null;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            this.b = networkCapabilities;
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            if (!networkChangeNotifierAutoDetect.j || this.a == null || networkCapabilities == null) {
                return;
            }
            networkChangeNotifierAutoDetect.b(a(network));
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            this.a = linkProperties;
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            if (!networkChangeNotifierAutoDetect.j || linkProperties == null || this.b == null) {
                return;
            }
            networkChangeNotifierAutoDetect.b(a(network));
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            this.a = null;
            this.b = null;
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            if (networkChangeNotifierAutoDetect.j) {
                networkChangeNotifierAutoDetect.b(new f(false, -1, -1, false, null, false, ""));
            }
        }
    }

    public static class c {
        public final ConnectivityManager a;

        public c(Context context) {
            this.a = (ConnectivityManager) context.getSystemService("connectivity");
        }

        public static boolean f(Network network) {
            Socket socket = new Socket();
            try {
                try {
                    StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
                    StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
                    try {
                        network.bindSocket(socket);
                        if (vmPolicy != null) {
                            StrictMode.setVmPolicy(vmPolicy);
                        }
                        try {
                            socket.close();
                            return true;
                        } catch (IOException unused) {
                            return true;
                        }
                    } catch (Throwable th) {
                        if (vmPolicy != null) {
                            try {
                                StrictMode.setVmPolicy(vmPolicy);
                            } catch (Throwable unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                    return false;
                }
            } catch (IOException unused4) {
                socket.close();
                return false;
            } catch (Throwable th2) {
                try {
                    socket.close();
                } catch (IOException unused5) {
                }
                throw th2;
            }
        }

        public final int a(Network network) {
            NetworkInfo networkInfoD = d(network);
            if (networkInfoD == null || !networkInfoD.isConnected()) {
                return 6;
            }
            return NetworkChangeNotifierAutoDetect.a(networkInfoD.getType(), networkInfoD.getSubtype());
        }

        public final Network b() {
            ConnectivityManager connectivityManager = this.a;
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null) {
                return activeNetwork;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            for (Network network : NetworkChangeNotifierAutoDetect.c(this, null)) {
                NetworkInfo networkInfoE = e(network);
                if (networkInfoE != null && (networkInfoE.getType() == activeNetworkInfo.getType() || networkInfoE.getType() == 17)) {
                    if (activeNetwork == null) {
                        activeNetwork = network;
                    } else if (networkInfoE.getDetailedState() != NetworkInfo.DetailedState.CONNECTING) {
                        NetworkInfo networkInfoE2 = e(activeNetwork);
                        if (networkInfoE2 != null) {
                            networkInfoE2.getDetailedState();
                            NetworkInfo.DetailedState detailedState = NetworkInfo.DetailedState.CONNECTING;
                        }
                        activeNetwork = network;
                    }
                }
            }
            return activeNetwork;
        }

        public final NetworkCapabilities c(Network network) {
            for (int i = 0; i < 2; i++) {
                try {
                    return this.a.getNetworkCapabilities(network);
                } catch (SecurityException unused) {
                }
            }
            return null;
        }

        public final NetworkInfo d(Network network) {
            NetworkInfo networkInfoE = e(network);
            return (networkInfoE == null || networkInfoE.getType() != 17) ? networkInfoE : this.a.getActiveNetworkInfo();
        }

        public final NetworkInfo e(Network network) {
            ConnectivityManager connectivityManager = this.a;
            try {
                try {
                    return connectivityManager.getNetworkInfo(network);
                } catch (NullPointerException unused) {
                    return null;
                }
            } catch (NullPointerException unused2) {
                return connectivityManager.getNetworkInfo(network);
            }
        }
    }

    public class d extends ConnectivityManager.NetworkCallback {
        public d() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            if (networkChangeNotifierAutoDetect.j) {
                networkChangeNotifierAutoDetect.b(networkChangeNotifierAutoDetect.d());
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            onAvailable(null);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            onAvailable(null);
        }
    }

    public class e extends ConnectivityManager.NetworkCallback {
        public Network a;

        public class a implements Runnable {
            public final /* synthetic */ long e;
            public final /* synthetic */ int f;
            public final /* synthetic */ boolean g;

            public a(long j, int i, boolean z) {
                this.e = j;
                this.f = i;
                this.g = z;
            }

            @Override // java.lang.Runnable
            public final void run() {
                e eVar = e.this;
                g gVar = NetworkChangeNotifierAutoDetect.this.d;
                int i = this.f;
                long j = this.e;
                gVar.c(i, j);
                if (this.g) {
                    NetworkChangeNotifierAutoDetect.this.d.d(i);
                    NetworkChangeNotifierAutoDetect.this.d.f(new long[]{j});
                }
            }
        }

        public class b implements Runnable {
            public final /* synthetic */ long e;
            public final /* synthetic */ int f;

            public b(long j, int i) {
                this.e = j;
                this.f = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                NetworkChangeNotifierAutoDetect.this.d.c(this.f, this.e);
            }
        }

        public class c implements Runnable {
            public final /* synthetic */ long e;

            public c(long j) {
                this.e = j;
            }

            @Override // java.lang.Runnable
            public final void run() {
                NetworkChangeNotifierAutoDetect.this.d.b(this.e);
            }
        }

        public class d implements Runnable {
            public final /* synthetic */ Network e;

            public d(Network network) {
                this.e = network;
            }

            @Override // java.lang.Runnable
            public final void run() {
                NetworkChangeNotifierAutoDetect.this.d.g(this.e.getNetworkHandle());
            }
        }

        /* JADX INFO: renamed from: org.chromium.net.NetworkChangeNotifierAutoDetect$e$e, reason: collision with other inner class name */
        public class RunnableC0156e implements Runnable {
            public final /* synthetic */ int e;

            public RunnableC0156e(int i) {
                this.e = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                NetworkChangeNotifierAutoDetect.this.d.d(this.e);
            }
        }

        public e() {
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean a(android.net.Network r4, android.net.NetworkCapabilities r5) {
            /*
                r3 = this;
                android.net.Network r0 = r3.a
                r1 = 1
                r2 = 0
                if (r0 == 0) goto Le
                boolean r0 = r0.equals(r4)
                if (r0 != 0) goto Le
                r0 = r1
                goto Lf
            Le:
                r0 = r2
            Lf:
                if (r0 != 0) goto L37
                org.chromium.net.NetworkChangeNotifierAutoDetect r3 = org.chromium.net.NetworkChangeNotifierAutoDetect.this
                if (r5 != 0) goto L1b
                org.chromium.net.NetworkChangeNotifierAutoDetect$c r5 = r3.g
                android.net.NetworkCapabilities r5 = r5.c(r4)
            L1b:
                if (r5 == 0) goto L32
                r0 = 4
                boolean r5 = r5.hasTransport(r0)
                if (r5 == 0) goto L30
                org.chromium.net.NetworkChangeNotifierAutoDetect$c r3 = r3.g
                r3.getClass()
                boolean r3 = org.chromium.net.NetworkChangeNotifierAutoDetect.c.f(r4)
                if (r3 != 0) goto L30
                goto L32
            L30:
                r3 = r2
                goto L33
            L32:
                r3 = r1
            L33:
                if (r3 == 0) goto L36
                goto L37
            L36:
                r1 = r2
            L37:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.NetworkChangeNotifierAutoDetect.e.a(android.net.Network, android.net.NetworkCapabilities):boolean");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            Network network2;
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            TraceEvent traceEventH0 = TraceEvent.H0("NetworkChangeNotifierCallback::onAvailable", null);
            try {
                NetworkCapabilities networkCapabilitiesC = networkChangeNotifierAutoDetect.g.c(network);
                if (a(network, networkCapabilitiesC)) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                }
                boolean z = networkCapabilitiesC.hasTransport(4) && ((network2 = this.a) == null || !network.equals(network2));
                if (z) {
                    this.a = network;
                }
                networkChangeNotifierAutoDetect.e(new a(network.getNetworkHandle(), networkChangeNotifierAutoDetect.g.a(network), z));
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

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            TraceEvent traceEventH0 = TraceEvent.H0("NetworkChangeNotifierCallback::onCapabilitiesChanged", null);
            try {
                if (a(network, networkCapabilities)) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                } else {
                    int i = NetworkChangeNotifierAutoDetect.o;
                    networkChangeNotifierAutoDetect.e(new b(network.getNetworkHandle(), networkChangeNotifierAutoDetect.g.a(network)));
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
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

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLosing(Network network, int i) {
            TraceEvent traceEventH0 = TraceEvent.H0("NetworkChangeNotifierCallback::onLosing", null);
            try {
                if (a(network, null)) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                } else {
                    int i2 = NetworkChangeNotifierAutoDetect.o;
                    NetworkChangeNotifierAutoDetect.this.e(new c(network.getNetworkHandle()));
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
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

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = NetworkChangeNotifierAutoDetect.this;
            TraceEvent traceEventH0 = TraceEvent.H0("NetworkChangeNotifierCallback::onLost", null);
            try {
                Network network2 = this.a;
                if ((network2 == null || network2.equals(network)) ? false : true) {
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                        return;
                    }
                    return;
                }
                d dVar = new d(network);
                int i = NetworkChangeNotifierAutoDetect.o;
                networkChangeNotifierAutoDetect.e(dVar);
                if (this.a != null) {
                    this.a = null;
                    for (Network network3 : NetworkChangeNotifierAutoDetect.c(networkChangeNotifierAutoDetect.g, network)) {
                        onAvailable(network3);
                    }
                    networkChangeNotifierAutoDetect.e(new RunnableC0156e(networkChangeNotifierAutoDetect.d().b()));
                }
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
    }

    public static class f {
        public final boolean a;
        public final int b;
        public final int c;
        public final boolean d;
        public final String e;
        public final boolean f;
        public final String g;

        public f(boolean z, int i, int i2, boolean z2, String str, boolean z3, String str2) {
            this.a = z;
            this.b = i;
            this.c = i2;
            this.d = z2;
            this.e = str == null ? "" : str;
            this.f = z3;
            this.g = str2 == null ? "" : str2;
        }

        public final int a() {
            if (!this.a) {
                return 1;
            }
            int i = this.b;
            if (i != 0 && i != 4 && i != 5) {
                return 0;
            }
            switch (this.c) {
            }
            return 0;
        }

        public final int b() {
            if (this.a) {
                return NetworkChangeNotifierAutoDetect.a(this.b, this.c);
            }
            return 6;
        }
    }

    public interface g {
        void a(int i);

        void b(long j);

        void c(int i, long j);

        void d(int i);

        void e(int i);

        void f(long[] jArr);

        void g(long j);
    }

    public static abstract class h {
        public NetworkChangeNotifierAutoDetect e;

        public abstract void a();

        public abstract void b(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect);

        public final void c() {
            NetworkCapabilities networkCapabilitiesC;
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.e;
            if (networkChangeNotifierAutoDetect.j) {
                networkChangeNotifierAutoDetect.b(networkChangeNotifierAutoDetect.d());
                return;
            }
            boolean z = networkChangeNotifierAutoDetect.m;
            if (z) {
                networkChangeNotifierAutoDetect.b(networkChangeNotifierAutoDetect.d());
            }
            ConnectivityManager.NetworkCallback networkCallback = networkChangeNotifierAutoDetect.f;
            Handler handler = networkChangeNotifierAutoDetect.b;
            c cVar = networkChangeNotifierAutoDetect.g;
            if (networkCallback != null) {
                try {
                    cVar.a.registerDefaultNetworkCallback(networkCallback, handler);
                } catch (RuntimeException unused) {
                    networkChangeNotifierAutoDetect.f = null;
                }
            }
            if (networkChangeNotifierAutoDetect.f == null) {
                networkChangeNotifierAutoDetect.l = xz.g(xz.a, networkChangeNotifierAutoDetect, networkChangeNotifierAutoDetect.c) != null;
            }
            networkChangeNotifierAutoDetect.j = true;
            e eVar = networkChangeNotifierAutoDetect.h;
            if (eVar != null) {
                NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect2 = NetworkChangeNotifierAutoDetect.this;
                Network[] networkArrC = NetworkChangeNotifierAutoDetect.c(networkChangeNotifierAutoDetect2.g, null);
                eVar.a = null;
                if (networkArrC.length == 1 && (networkCapabilitiesC = networkChangeNotifierAutoDetect2.g.c(networkArrC[0])) != null && networkCapabilitiesC.hasTransport(4)) {
                    eVar.a = networkArrC[0];
                }
                try {
                    cVar.a.registerNetworkCallback(networkChangeNotifierAutoDetect.i, networkChangeNotifierAutoDetect.h, handler);
                } catch (RuntimeException unused2) {
                    networkChangeNotifierAutoDetect.n = true;
                    networkChangeNotifierAutoDetect.h = null;
                }
                if (networkChangeNotifierAutoDetect.n || !z) {
                    return;
                }
                Network[] networkArrC2 = NetworkChangeNotifierAutoDetect.c(cVar, null);
                long[] jArr = new long[networkArrC2.length];
                for (int i = 0; i < networkArrC2.length; i++) {
                    jArr[i] = networkArrC2[i].getNetworkHandle();
                }
                networkChangeNotifierAutoDetect.d.f(jArr);
            }
        }
    }

    public NetworkChangeNotifierAutoDetect(g gVar, h hVar) {
        Looper looperMyLooper = Looper.myLooper();
        this.a = looperMyLooper;
        this.b = new Handler(looperMyLooper);
        this.d = gVar;
        this.g = new c(xz.a);
        int i = Build.VERSION.SDK_INT;
        this.h = new e();
        this.i = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
        if (i >= 30) {
            this.f = new b();
        } else {
            this.f = new d();
        }
        this.k = d();
        this.c = new NetworkConnectivityIntentFilter();
        this.l = false;
        this.m = false;
        this.e = hVar;
        hVar.b(this);
        this.m = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(int r4, int r5) {
        /*
            r0 = 0
            r1 = 4
            r2 = 5
            if (r4 == 0) goto L1d
            r3 = 1
            if (r4 == r3) goto L1b
            if (r4 == r1) goto L1d
            if (r4 == r2) goto L1d
            r5 = 6
            if (r4 == r5) goto L25
            r5 = 7
            if (r4 == r5) goto L19
            r5 = 9
            if (r4 == r5) goto L17
            goto L2d
        L17:
            r0 = r3
            goto L2d
        L19:
            r0 = r5
            goto L2d
        L1b:
            r0 = 2
            goto L2d
        L1d:
            r4 = 20
            if (r5 == r4) goto L2b
            switch(r5) {
                case 1: goto L29;
                case 2: goto L29;
                case 3: goto L27;
                case 4: goto L29;
                case 5: goto L27;
                case 6: goto L27;
                case 7: goto L29;
                case 8: goto L27;
                case 9: goto L27;
                case 10: goto L27;
                case 11: goto L29;
                case 12: goto L27;
                case 13: goto L25;
                case 14: goto L27;
                case 15: goto L27;
                default: goto L24;
            }
        L24:
            goto L2d
        L25:
            r0 = r2
            goto L2d
        L27:
            r0 = r1
            goto L2d
        L29:
            r0 = 3
            goto L2d
        L2b:
            r0 = 8
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.NetworkChangeNotifierAutoDetect.a(int, int):int");
    }

    public static Network[] c(c cVar, Network network) {
        NetworkCapabilities networkCapabilitiesC;
        Network[] allNetworks = cVar.a.getAllNetworks();
        if (allNetworks == null) {
            allNetworks = new Network[0];
        }
        int i = 0;
        for (Network network2 : allNetworks) {
            if (!network2.equals(network) && (networkCapabilitiesC = cVar.c(network2)) != null && networkCapabilitiesC.hasCapability(12)) {
                if (!networkCapabilitiesC.hasTransport(4)) {
                    allNetworks[i] = network2;
                    i++;
                } else if (c.f(network2)) {
                    return new Network[]{network2};
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworks, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(org.chromium.net.NetworkChangeNotifierAutoDetect.f r7) {
        /*
            r6 = this;
            int r0 = r7.b()
            org.chromium.net.NetworkChangeNotifierAutoDetect$f r1 = r6.k
            int r1 = r1.b()
            org.chromium.net.NetworkChangeNotifierAutoDetect$g r2 = r6.d
            if (r0 != r1) goto L2c
            org.chromium.net.NetworkChangeNotifierAutoDetect$f r0 = r6.k
            java.lang.String r0 = r0.e
            java.lang.String r1 = r7.e
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L2c
            org.chromium.net.NetworkChangeNotifierAutoDetect$f r0 = r6.k
            boolean r1 = r0.f
            boolean r3 = r7.f
            if (r3 != r1) goto L2c
            java.lang.String r0 = r0.g
            java.lang.String r1 = r7.g
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L33
        L2c:
            int r0 = r7.b()
            r2.d(r0)
        L33:
            int r0 = r7.b()
            org.chromium.net.NetworkChangeNotifierAutoDetect$f r1 = r6.k
            int r1 = r1.b()
            if (r0 != r1) goto L4b
            int r0 = r7.a()
            org.chromium.net.NetworkChangeNotifierAutoDetect$f r1 = r6.k
            int r1 = r1.a()
            if (r0 == r1) goto L52
        L4b:
            int r0 = r7.a()
            r2.e(r0)
        L52:
            r0 = 2
            r1 = 1
            boolean r3 = r7.d
            if (r3 == 0) goto L5a
            r4 = r0
            goto L5b
        L5a:
            r4 = r1
        L5b:
            org.chromium.net.NetworkChangeNotifierAutoDetect$f r5 = r6.k
            boolean r5 = r5.d
            if (r5 == 0) goto L63
            r5 = r0
            goto L64
        L63:
            r5 = r1
        L64:
            if (r4 == r5) goto L6d
            if (r3 == 0) goto L69
            goto L6a
        L69:
            r0 = r1
        L6a:
            r2.a(r0)
        L6d:
            r6.k = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.NetworkChangeNotifierAutoDetect.b(org.chromium.net.NetworkChangeNotifierAutoDetect$f):void");
    }

    public final f d() {
        c cVar = this.g;
        Network networkB = cVar.b();
        NetworkInfo networkInfoD = cVar.d(networkB);
        if (networkInfoD == null || (!networkInfoD.isConnected() && (networkInfoD.getDetailedState() != NetworkInfo.DetailedState.BLOCKED || ApplicationStatus.getStateForApplication() != 1))) {
            networkInfoD = null;
        }
        if (networkInfoD == null) {
            return new f(false, -1, -1, false, null, false, "");
        }
        if (networkB != null) {
            NetworkCapabilities networkCapabilitiesC = cVar.c(networkB);
            boolean z = (networkCapabilitiesC == null || networkCapabilitiesC.hasCapability(11)) ? false : true;
            DnsStatus dnsStatusA = AndroidNetworkLibrary.a(networkB);
            return dnsStatusA == null ? new f(true, networkInfoD.getType(), networkInfoD.getSubtype(), z, String.valueOf(networkB.getNetworkHandle()), false, "") : new f(true, networkInfoD.getType(), networkInfoD.getSubtype(), z, String.valueOf(networkB.getNetworkHandle()), dnsStatusA.getPrivateDnsActive(), dnsStatusA.getPrivateDnsServerName());
        }
        if (networkInfoD.getType() != 1) {
            return new f(true, networkInfoD.getType(), networkInfoD.getSubtype(), false, null, false, "");
        }
        if (networkInfoD.getExtraInfo() != null && !"".equals(networkInfoD.getExtraInfo())) {
            return new f(true, networkInfoD.getType(), networkInfoD.getSubtype(), false, networkInfoD.getExtraInfo(), false, "");
        }
        networkInfoD.getType();
        networkInfoD.getSubtype();
        throw null;
    }

    public final void e(Runnable runnable) {
        if (this.a == Looper.myLooper()) {
            runnable.run();
        } else {
            this.b.post(new y70(this, 26, runnable));
        }
    }

    public final void f() {
        if (this.j) {
            this.j = false;
            e eVar = this.h;
            c cVar = this.g;
            if (eVar != null) {
                cVar.a.unregisterNetworkCallback(eVar);
            }
            ConnectivityManager.NetworkCallback networkCallback = this.f;
            if (networkCallback != null) {
                cVar.a.unregisterNetworkCallback(networkCallback);
            } else {
                xz.a.unregisterReceiver(this);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        e(new a());
    }
}
