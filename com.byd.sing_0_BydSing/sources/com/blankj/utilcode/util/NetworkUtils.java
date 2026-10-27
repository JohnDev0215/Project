package com.blankj.utilcode.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.blankj.utilcode.util.w;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<w.b<?>> f2558a = new CopyOnWriteArraySet();

    public static final class NetworkChangedReceiver extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private NetworkType f2559a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Set<a> f2560b = new HashSet();

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ a f2561b;

            a(a aVar) {
                this.f2561b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                int size = NetworkChangedReceiver.this.f2560b.size();
                NetworkChangedReceiver.this.f2560b.add(this.f2561b);
                if (size == 0 && NetworkChangedReceiver.this.f2560b.size() == 1) {
                    NetworkChangedReceiver.this.f2559a = NetworkUtils.b();
                    w.a().registerReceiver(NetworkChangedReceiver.e(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        }

        class b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ a f2563b;

            b(a aVar) {
                this.f2563b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                int size = NetworkChangedReceiver.this.f2560b.size();
                NetworkChangedReceiver.this.f2560b.remove(this.f2563b);
                if (size == 1 && NetworkChangedReceiver.this.f2560b.size() == 0) {
                    w.a().unregisterReceiver(NetworkChangedReceiver.e());
                }
            }
        }

        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                NetworkType networkTypeB = NetworkUtils.b();
                if (NetworkChangedReceiver.this.f2559a == networkTypeB) {
                    return;
                }
                NetworkChangedReceiver.this.f2559a = networkTypeB;
                if (networkTypeB == NetworkType.NETWORK_NO) {
                    Iterator it = NetworkChangedReceiver.this.f2560b.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).a();
                    }
                } else {
                    Iterator it2 = NetworkChangedReceiver.this.f2560b.iterator();
                    while (it2.hasNext()) {
                        ((a) it2.next()).b(networkTypeB);
                    }
                }
            }
        }

        private static class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private static final NetworkChangedReceiver f2566a = new NetworkChangedReceiver();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static NetworkChangedReceiver e() {
            return d.f2566a;
        }

        void f(a aVar) {
            if (aVar == null) {
                return;
            }
            y.C(new a(aVar));
        }

        void g(a aVar) {
            if (aVar == null) {
                return;
            }
            y.C(new b(aVar));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                y.D(new c(), 1000L);
            }
        }
    }

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    public interface a {
        void a();

        void b(NetworkType networkType);
    }

    private static NetworkInfo a() {
        ConnectivityManager connectivityManager = (ConnectivityManager) w.a().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static NetworkType b() {
        if (d()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo networkInfoA = a();
        if (networkInfoA == null || !networkInfoA.isAvailable()) {
            return NetworkType.NETWORK_NO;
        }
        if (networkInfoA.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (networkInfoA.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (networkInfoA.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            case 19:
            default:
                String subtypeName = networkInfoA.getSubtypeName();
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? NetworkType.NETWORK_3G : NetworkType.NETWORK_UNKNOWN;
            case 20:
                return NetworkType.NETWORK_5G;
        }
    }

    public static boolean c() {
        NetworkInfo networkInfoA = a();
        return networkInfoA != null && networkInfoA.isConnected();
    }

    private static boolean d() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) w.a().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    public static void e(a aVar) {
        NetworkChangedReceiver.e().f(aVar);
    }

    public static void f(a aVar) {
        NetworkChangedReceiver.e().g(aVar);
    }
}
