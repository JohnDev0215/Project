package com.android.wifitrackerlib;

import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkUtils;
import android.net.RouteInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class WifiEntry implements Comparable<WifiEntry> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final WifiManager f1444c;
    private c d;
    protected Handler e;
    protected WifiInfo h;
    protected NetworkInfo i;
    protected NetworkCapabilities j;
    protected b k;
    protected WifiNetworkScoreCache l;
    protected ConnectCallback m;

    @VisibleForTesting(otherwise = 4)
    final boolean mForSavedNetworksPage;
    protected DisconnectCallback n;
    private boolean q;
    private boolean r;
    protected boolean s;
    protected int f = -1;
    protected int g = 0;
    protected boolean o = false;
    protected boolean p = false;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface ConnectCallback {

        /* JADX INFO: compiled from: caradapter-bug */
        @Retention(RetentionPolicy.SOURCE)
        public @interface ConnectStatus {
        }

        @MainThread
        void a(int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectedState {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface DisconnectCallback {

        /* JADX INFO: compiled from: caradapter-bug */
        @Retention(RetentionPolicy.SOURCE)
        public @interface DisconnectStatus {
        }

        @MainThread
        void a(int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Frequency {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface MeteredChoice {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Privacy {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Security {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface SignInCallback {

        /* JADX INFO: compiled from: caradapter-bug */
        @Retention(RetentionPolicy.SOURCE)
        public @interface SignInStatus {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Speed {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1445a;

        static {
            int[] iArr = new int[NetworkInfo.DetailedState.values().length];
            f1445a = iArr;
            try {
                iArr[NetworkInfo.DetailedState.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1445a[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1445a[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1445a[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1445a[NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1445a[NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1445a[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List<String> f1447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1448c;
        public String d;
        public List<String> e;
        public String f;
        public String g;

        public b() {
            new ArrayList();
            new ArrayList();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        @MainThread
        void c();
    }

    WifiEntry(@NonNull Handler handler, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        androidx.core.d.e.c(handler, "Cannot construct with null handler!");
        androidx.core.d.e.c(wifiManager, "Cannot construct with null WifiManager!");
        this.e = handler;
        this.mForSavedNetworksPage = z;
        this.f1444c = wifiManager;
        this.l = wifiNetworkScoreCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void C() {
        this.d.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void E() {
        ConnectCallback connectCallback = this.m;
        if (connectCallback != null) {
            connectCallback.a(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void G() {
        DisconnectCallback disconnectCallback = this.n;
        if (disconnectCallback != null) {
            disconnectCallback.a(0);
        }
    }

    public abstract boolean A();

    @AnyThread
    protected void H() {
        if (this.d != null) {
            this.e.post(new Runnable() { // from class: com.android.wifitrackerlib.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1478c.C();
                }
            });
        }
    }

    @WorkerThread
    void I(boolean z) {
        this.r = z;
        H();
    }

    @WorkerThread
    void J(boolean z) {
        this.s = z;
    }

    public void K(c cVar) {
        this.d = cVar;
    }

    public boolean L() {
        return (h() == 0 || (this.q && this.r) || d()) ? false : true;
    }

    @WorkerThread
    void M(@Nullable WifiInfo wifiInfo, @Nullable NetworkInfo networkInfo) {
        if (wifiInfo == null || networkInfo == null || !f(wifiInfo, networkInfo)) {
            this.i = null;
            this.j = null;
            this.k = null;
            this.q = false;
            this.r = false;
            this.s = false;
            if (this.p) {
                this.p = false;
                this.e.post(new Runnable() { // from class: com.android.wifitrackerlib.v
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1477c.G();
                    }
                });
            }
        } else {
            this.h = wifiInfo;
            this.i = networkInfo;
            int rssi = wifiInfo.getRssi();
            if (rssi != -127) {
                this.f = this.f1444c.calculateSignalLevel(rssi);
                this.g = e0.o(this.l, wifiInfo);
            }
            if (h() == 2) {
                if (this.o) {
                    this.o = false;
                    this.e.post(new Runnable() { // from class: com.android.wifitrackerlib.x
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f1479c.E();
                        }
                    });
                }
                if (this.k == null) {
                    this.k = new b();
                }
                this.k.f1446a = wifiInfo.getFrequency();
                this.k.f1448c = wifiInfo.getLinkSpeed();
            }
        }
        H();
    }

    @WorkerThread
    void N(@Nullable LinkProperties linkProperties) {
        if (linkProperties == null || h() != 2) {
            this.k = null;
            H();
            return;
        }
        if (this.k == null) {
            this.k = new b();
        }
        ArrayList arrayList = new ArrayList();
        for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
            if (linkAddress.getAddress() instanceof Inet4Address) {
                this.k.d = linkAddress.getAddress().getHostAddress();
                try {
                    this.k.g = NetworkUtils.getNetworkPart(InetAddress.getByAddress(new byte[]{-1, -1, -1, -1}), linkAddress.getPrefixLength()).getHostAddress();
                } catch (UnknownHostException unused) {
                }
            } else if (linkAddress.getAddress() instanceof Inet6Address) {
                arrayList.add(linkAddress.getAddress().getHostAddress());
            }
        }
        this.k.e = arrayList;
        Iterator<RouteInfo> it = linkProperties.getRoutes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RouteInfo next = it.next();
            if (next.isIPv4Default() && next.hasGateway()) {
                this.k.f = next.getGateway().getHostAddress();
                break;
            }
        }
        this.k.f1447b = (List) linkProperties.getDnsServers().stream().map(new Function() { // from class: com.android.wifitrackerlib.V
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InetAddress) obj).getHostAddress();
            }
        }).collect(Collectors.toList());
        H();
    }

    @WorkerThread
    void O(@Nullable NetworkCapabilities networkCapabilities) {
        this.j = networkCapabilities;
        if (this.k == null) {
            return;
        }
        this.q = networkCapabilities != null && networkCapabilities.hasCapability(16);
        H();
    }

    public abstract boolean a();

    public abstract boolean b();

    public abstract boolean d();

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull WifiEntry wifiEntry) {
        if (k() != -1 && wifiEntry.k() == -1) {
            return -1;
        }
        if (k() == -1 && wifiEntry.k() != -1) {
            return 1;
        }
        if (z() && !wifiEntry.z()) {
            return -1;
        }
        if (!z() && wifiEntry.z()) {
            return 1;
        }
        if (y() && !wifiEntry.y()) {
            return -1;
        }
        if (!y() && wifiEntry.y()) {
            return 1;
        }
        if (A() && !wifiEntry.A()) {
            return -1;
        }
        if (!A() && wifiEntry.A()) {
            return 1;
        }
        if (k() > wifiEntry.k()) {
            return -1;
        }
        if (k() < wifiEntry.k()) {
            return 1;
        }
        return t().compareTo(wifiEntry.t());
    }

    public boolean equals(Object obj) {
        if (obj instanceof WifiEntry) {
            return j().equals(((WifiEntry) obj).j());
        }
        return false;
    }

    protected abstract boolean f(@NonNull WifiInfo wifiInfo, @NonNull NetworkInfo networkInfo);

    public b g() {
        if (h() != 2) {
            return null;
        }
        return this.k;
    }

    public int h() {
        NetworkInfo networkInfo = this.i;
        if (networkInfo == null) {
            return 0;
        }
        switch (a.f1445a[networkInfo.getDetailedState().ordinal()]) {
        }
        return 0;
    }

    @Nullable
    public String i() {
        return null;
    }

    public abstract String j();

    public int k() {
        return this.f;
    }

    public abstract int l();

    String m() {
        StringBuilder sb = new StringBuilder();
        if (h() == 2) {
            sb.append("isValidated:");
            sb.append(this.q);
            sb.append(", isDefaultNetwork:");
            sb.append(this.r);
            sb.append(", isLowQuality:");
            sb.append(this.s);
        }
        return sb.toString();
    }

    String n() {
        return "";
    }

    abstract String o();

    public abstract int p();

    public int q() {
        return this.g;
    }

    public String r() {
        return s(true);
    }

    public abstract String s(boolean z);

    public abstract String t();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(j());
        sb.append(",title:");
        sb.append(t());
        sb.append(",summary:");
        sb.append(r());
        sb.append(",isSaved:");
        sb.append(y());
        sb.append(",isSubscription:");
        sb.append(z());
        sb.append(",isSuggestion:");
        sb.append(A());
        sb.append(",level:");
        sb.append(k());
        sb.append(L() ? "X" : "");
        sb.append(",security:");
        sb.append(p());
        sb.append(",connected:");
        sb.append(h() == 2 ? "true" : "false");
        sb.append(",connectedInfo:");
        sb.append(g());
        sb.append(",isValidated:");
        sb.append(this.q);
        sb.append(",isDefaultNetwork:");
        sb.append(this.r);
        return sb.toString();
    }

    public abstract WifiConfiguration u();

    String v() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (h() == 2 && this.h != null) {
            stringJoiner.add("f = " + this.h.getFrequency());
            String bssid = this.h.getBSSID();
            if (bssid != null) {
                stringJoiner.add(bssid);
            }
            stringJoiner.add("standard = " + this.h.getWifiStandard());
            stringJoiner.add("rssi = " + this.h.getRssi());
            stringJoiner.add("score = " + this.h.getScore());
            stringJoiner.add(String.format(" tx=%.1f,", Double.valueOf(this.h.getSuccessfulTxPacketsPerSecond())));
            stringJoiner.add(String.format("%.1f,", Double.valueOf(this.h.getRetriedTxPacketsPerSecond())));
            stringJoiner.add(String.format("%.1f ", Double.valueOf(this.h.getLostTxPacketsPerSecond())));
            stringJoiner.add(String.format("rx=%.1f", Double.valueOf(this.h.getSuccessfulRxPacketsPerSecond())));
        }
        return stringJoiner.toString();
    }

    public abstract boolean w();

    public abstract boolean x();

    public abstract boolean y();

    public abstract boolean z();
}
