package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
import org.chromium.base.ApplicationStatus;
import org.chromium.net.AndroidNetworkLibrary;
import org.chromium.net.DnsStatus;
import org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TK extends BroadcastReceiver {
    public final Looper a;
    public final Handler b;
    public final NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter c;
    public final QK d;
    public final RK e;
    public IK f;
    public HK g;
    public SK h;
    public OK i;
    public NetworkRequest j;
    public boolean k;
    public PK l;
    public boolean m;
    public boolean n;
    public boolean o;

    /* JADX WARN: Type inference failed for: r4v10, types: [org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter] */
    public TK(QK qk, RK rk) {
        int i = Build.VERSION.SDK_INT;
        this.a = Looper.myLooper();
        this.b = new Handler(this.a);
        this.d = qk;
        this.g = new HK(Is.a);
        if (i < 23) {
            this.h = new SK(Is.a);
        }
        this.i = new OK(this, null);
        this.j = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
        this.f = i >= 28 ? new IK(this, null) : null;
        this.l = d();
        this.c = new IntentFilter() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkConnectivityIntentFilter
            {
                addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
        };
        this.m = false;
        this.n = false;
        this.e = rk;
        rk.d(this);
        this.n = true;
    }

    public static int a(int i, int i2) {
        if (i == 0) {
            switch (i2) {
                case 1:
                case 2:
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                case 7:
                case 11:
                    return 3;
                case 3:
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 4;
                case 13:
                    return 5;
            }
        }
        if (i == 1) {
            return 2;
        }
        if (i == 6) {
            return 5;
        }
        if (i == 7) {
            return 7;
        }
        if (i == 9) {
            return 1;
        }
        return 0;
    }

    public static Network[] c(HK hk, Network network) {
        NetworkCapabilities networkCapabilities;
        Network[] allNetworks = hk.a.getAllNetworks();
        if (allNetworks == null) {
            allNetworks = new Network[0];
        }
        int i = 0;
        for (Network network2 : allNetworks) {
            if (!network2.equals(network) && (networkCapabilities = hk.a.getNetworkCapabilities(network2)) != null && networkCapabilities.hasCapability(12)) {
                if (!networkCapabilities.hasTransport(4)) {
                    allNetworks[i] = network2;
                    i++;
                } else if (hk.d(network2)) {
                    return new Network[]{network2};
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworks, i);
    }

    public static long e(Network network) {
        return Build.VERSION.SDK_INT >= 23 ? C1185mt.e(network) : Integer.parseInt(network.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            r4 = this;
            PK r0 = r4.d()
            int r1 = r0.b()
            PK r2 = r4.l
            int r2 = r2.b()
            if (r1 != r2) goto L2e
            java.lang.String r1 = r0.d
            PK r2 = r4.l
            java.lang.String r2 = r2.d
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L2e
            boolean r1 = r0.e
            PK r2 = r4.l
            boolean r3 = r2.e
            if (r1 != r3) goto L2e
            java.lang.String r1 = r0.f
            java.lang.String r2 = r2.f
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L37
        L2e:
            QK r1 = r4.d
            int r2 = r0.b()
            r1.a(r2)
        L37:
            int r1 = r0.b()
            PK r2 = r4.l
            int r2 = r2.b()
            if (r1 != r2) goto L4f
            int r1 = r0.a()
            PK r2 = r4.l
            int r2 = r2.a()
            if (r1 == r2) goto L58
        L4f:
            QK r1 = r4.d
            int r2 = r0.a()
            r1.e(r2)
        L58:
            r4.l = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.TK.b():void");
    }

    public PK d() {
        NetworkInfo activeNetworkInfo;
        Network networkB;
        PK pk;
        String ssid;
        HK hk = this.g;
        SK sk = this.h;
        WifiInfo connectionInfo = null;
        if (Build.VERSION.SDK_INT >= 23) {
            networkB = hk.b();
            activeNetworkInfo = C1185mt.f(hk.a, networkB);
        } else {
            activeNetworkInfo = hk.a.getActiveNetworkInfo();
            networkB = null;
        }
        if (activeNetworkInfo == null || (!activeNetworkInfo.isConnected() && (activeNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.BLOCKED || ApplicationStatus.getStateForApplication() != 1))) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return new PK(false, -1, -1, null, false, "");
        }
        if (networkB != null) {
            DnsStatus dnsStatus = AndroidNetworkLibrary.getDnsStatus(networkB);
            if (dnsStatus != null) {
                return new PK(true, activeNetworkInfo.getType(), activeNetworkInfo.getSubtype(), String.valueOf(e(networkB)), dnsStatus.getPrivateDnsActive(), dnsStatus.getPrivateDnsServerName());
            }
            pk = new PK(true, activeNetworkInfo.getType(), activeNetworkInfo.getSubtype(), String.valueOf(e(networkB)), false, "");
        } else if (activeNetworkInfo.getType() != 1) {
            pk = new PK(true, activeNetworkInfo.getType(), activeNetworkInfo.getSubtype(), null, false, "");
        } else {
            if (activeNetworkInfo.getExtraInfo() == null || "".equals(activeNetworkInfo.getExtraInfo())) {
                int type = activeNetworkInfo.getType();
                int subtype = activeNetworkInfo.getSubtype();
                synchronized (sk.b) {
                    if (sk.a()) {
                        try {
                            try {
                                connectionInfo = sk.e.getConnectionInfo();
                            } catch (NullPointerException unused) {
                            }
                        } catch (NullPointerException unused2) {
                            connectionInfo = sk.e.getConnectionInfo();
                        }
                        ssid = connectionInfo != null ? connectionInfo.getSSID() : "";
                    } else {
                        ssid = AndroidNetworkLibrary.getWifiSSID();
                    }
                }
                return new PK(true, type, subtype, ssid, false, "");
            }
            pk = new PK(true, activeNetworkInfo.getType(), activeNetworkInfo.getSubtype(), activeNetworkInfo.getExtraInfo(), false, "");
        }
        return pk;
    }

    public final void f(Runnable runnable) {
        if (this.a == Looper.myLooper()) {
            runnable.run();
        } else {
            this.b.post(runnable);
        }
    }

    public void g() {
        if (this.k) {
            this.k = false;
            OK ok = this.i;
            if (ok != null) {
                this.g.a.unregisterNetworkCallback(ok);
            }
            IK ik = this.f;
            if (ik != null) {
                this.g.a.unregisterNetworkCallback(ik);
            } else {
                Is.a.unregisterReceiver(this);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        f(new GK(this));
    }
}
