package com.android.settingslib.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkRequest;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.android.settingslib.R$string;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class WifiTracker implements com.android.settingslib.core.lifecycle.a, com.android.settingslib.core.lifecycle.b.i, com.android.settingslib.core.lifecycle.b.j, com.android.settingslib.core.lifecycle.b.d {

    @VisibleForTesting
    static final long MAX_SCAN_RESULT_AGE_MILLIS = 15000;
    public static boolean y;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1391c;
    private final WifiManager d;
    private final IntentFilter e;
    private final ConnectivityManager f;
    private final NetworkRequest g;
    private final WifiListenerExecutor i;
    private HandlerThread j;
    private d k;

    @GuardedBy("mLock")
    @VisibleForTesting
    Scanner mScanner;

    @VisibleForTesting
    Handler mWorkHandler;
    private boolean r;
    private NetworkInfo s;
    private WifiInfo t;
    private final NetworkScoreManager u;
    private WifiNetworkScoreCache v;
    private boolean w;
    private long x;
    private final AtomicBoolean h = new AtomicBoolean(false);
    private final Object l = new Object();

    @GuardedBy("mLock")
    private final List<AccessPoint> m = new ArrayList();

    @GuardedBy("mLock")
    private final Set<NetworkKey> n = new ArraySet();
    private boolean o = true;
    private boolean p = true;
    private final HashMap<String, ScanResult> q = new HashMap<>();

    @VisibleForTesting
    final BroadcastReceiver mReceiver = new b();

    /* JADX INFO: compiled from: caradapter-bug */
    @VisibleForTesting
    class Scanner extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1392a = 0;

        Scanner() {
        }

        void a() {
            if (WifiTracker.G()) {
                Log.d("WifiTracker", "Scanner pause");
            }
            this.f1392a = 0;
            removeMessages(0);
        }

        void b() {
            if (WifiTracker.G()) {
                Log.d("WifiTracker", "Scanner resume");
            }
            if (hasMessages(0)) {
                return;
            }
            sendEmptyMessage(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            if (WifiTracker.this.d.startScan()) {
                this.f1392a = 0;
            } else {
                int i = this.f1392a + 1;
                this.f1392a = i;
                if (i >= 3) {
                    this.f1392a = 0;
                    if (WifiTracker.this.f1391c != null) {
                        Toast.makeText(WifiTracker.this.f1391c, R$string.wifi_fail_to_scan, 1).show();
                        return;
                    }
                    return;
                }
            }
            sendEmptyMessageDelayed(0, 10000L);
        }

        @VisibleForTesting
        boolean isScanning() {
            return hasMessages(0);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @VisibleForTesting
    class WifiListenerExecutor implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c f1394a;

        public WifiListenerExecutor(c cVar) {
            this.f1394a = cVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void e(int i) {
            this.f1394a.a(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void g(String str, Runnable runnable) {
            if (WifiTracker.this.r) {
                if (WifiTracker.G()) {
                    Log.i("WifiTracker", str);
                }
                runnable.run();
            }
        }

        private void h(final Runnable runnable, final String str) {
            com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1410c.g(str, runnable);
                }
            });
        }

        @Override // com.android.settingslib.wifi.WifiTracker.c
        public void a(final int i) {
            h(new Runnable() { // from class: com.android.settingslib.wifi.m
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1411c.e(i);
                }
            }, String.format("Invoking onWifiStateChanged callback with state %d", Integer.valueOf(i)));
        }

        @Override // com.android.settingslib.wifi.WifiTracker.c
        public void b() {
            final c cVar = this.f1394a;
            Objects.requireNonNull(cVar);
            h(new Runnable() { // from class: com.android.settingslib.wifi.o
                @Override // java.lang.Runnable
                public final void run() {
                    cVar.b();
                }
            }, "Invoking onAccessPointsChanged callback");
        }

        @Override // com.android.settingslib.wifi.WifiTracker.c
        public void c() {
            final c cVar = this.f1394a;
            Objects.requireNonNull(cVar);
            h(new Runnable() { // from class: com.android.settingslib.wifi.a
                @Override // java.lang.Runnable
                public final void run() {
                    cVar.c();
                }
            }, "Invoking onConnectedChanged callback");
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends WifiNetworkScoreCache.CacheListener {
        a(Handler handler) {
            super(handler);
        }

        public void networkCacheUpdated(List<ScoredNetwork> list) {
            if (WifiTracker.this.r) {
                if (Log.isLoggable("WifiTracker", 2)) {
                    Log.v("WifiTracker", "Score cache was updated with networks: " + list);
                }
                WifiTracker.this.R();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                WifiTracker.this.T(intent.getIntExtra("wifi_state", 4));
                return;
            }
            if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                WifiTracker.this.o = false;
                WifiTracker.this.p = intent.getBooleanExtra("resultsUpdated", true);
                WifiTracker.this.y();
                return;
            }
            if ("android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action) || "android.net.wifi.LINK_CONFIGURATION_CHANGED".equals(action)) {
                WifiTracker.this.y();
                return;
            }
            if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                WifiTracker.this.Q((NetworkInfo) intent.getParcelableExtra("networkInfo"));
                WifiTracker.this.y();
            } else if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
                WifiTracker.this.Q(null);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface c {
        void a(int i);

        void b();

        void c();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class d extends ConnectivityManager.NetworkCallback {
        private d() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (network.equals(WifiTracker.this.d.getCurrentNetwork())) {
                WifiTracker.this.Q(null);
            }
        }

        /* synthetic */ d(WifiTracker wifiTracker, a aVar) {
            this();
        }
    }

    @VisibleForTesting
    WifiTracker(Context context, c cVar, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, IntentFilter intentFilter) {
        boolean z = false;
        this.f1391c = context;
        this.d = wifiManager;
        this.i = new WifiListenerExecutor(cVar);
        this.f = connectivityManager;
        WifiManager wifiManager2 = this.d;
        if (wifiManager2 != null && wifiManager2.isVerboseLoggingEnabled()) {
            z = true;
        }
        y = z;
        this.e = intentFilter;
        this.g = new NetworkRequest.Builder().clearCapabilities().addCapability(15).addTransportType(1).build();
        this.u = networkScoreManager;
        HandlerThread handlerThread = new HandlerThread("WifiTracker{" + Integer.toHexString(System.identityHashCode(this)) + "}", 10);
        handlerThread.start();
        setWorkThread(handlerThread);
    }

    private AccessPoint A(List<AccessPoint> list, String str) {
        ListIterator<AccessPoint> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            AccessPoint next = listIterator.next();
            if (next.p().equals(str)) {
                listIterator.remove();
                return next;
            }
        }
        return null;
    }

    private AccessPoint B(List<ScanResult> list, List<AccessPoint> list2) {
        AccessPoint accessPointA = A(list2, AccessPoint.q(this.f1391c, list.get(0)));
        if (accessPointA == null) {
            return new AccessPoint(this.f1391c, list);
        }
        accessPointA.j0(list);
        return accessPointA;
    }

    private AccessPoint C(OsuProvider osuProvider, List<ScanResult> list, List<AccessPoint> list2) {
        AccessPoint accessPointA = A(list2, AccessPoint.s(osuProvider));
        if (accessPointA == null) {
            return new AccessPoint(this.f1391c, osuProvider, list);
        }
        accessPointA.j0(list);
        return accessPointA;
    }

    private AccessPoint D(WifiConfiguration wifiConfiguration, List<ScanResult> list, List<ScanResult> list2, List<AccessPoint> list3) {
        AccessPoint accessPointA = A(list3, AccessPoint.r(wifiConfiguration));
        if (accessPointA == null) {
            return new AccessPoint(this.f1391c, wifiConfiguration, list, list2);
        }
        accessPointA.m0(wifiConfiguration);
        accessPointA.k0(list, list2);
        return accessPointA;
    }

    private WifiConfiguration E(int i, List<WifiConfiguration> list) {
        if (list == null) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : list) {
            if (this.t != null && i == wifiConfiguration.networkId) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean F(WifiConfiguration wifiConfiguration) {
        int iZ = AccessPoint.z(wifiConfiguration);
        return iZ == 5 || iZ == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean G() {
        return y || Log.isLoggable("WifiTracker", 2);
    }

    private void K() {
        synchronized (this.l) {
            if (this.mScanner != null) {
                this.mScanner.a();
                this.mScanner = null;
            }
        }
        this.o = true;
    }

    private void L() {
        this.u.registerNetworkScoreCache(1, this.v, 2);
    }

    private void M(Collection<NetworkKey> collection) {
        if (collection.isEmpty()) {
            return;
        }
        if (a()) {
            Log.d("WifiTracker", "Requesting scores for Network Keys: " + collection);
        }
        this.u.requestScores((NetworkKey[]) collection.toArray(new NetworkKey[collection.size()]));
        synchronized (this.l) {
            this.n.addAll(collection);
        }
    }

    private void O() {
        this.u.unregisterNetworkScoreCache(1, this.v);
        synchronized (this.l) {
            this.n.clear();
        }
    }

    private void P(List<ScanResult> list, List<WifiConfiguration> list2) {
        boolean z;
        WifiInfo wifiInfo = this.t;
        WifiConfiguration wifiConfigurationE = wifiInfo != null ? E(wifiInfo.getNetworkId(), list2) : null;
        synchronized (this.l) {
            ArrayMap<String, List<ScanResult>> arrayMapS = S(list);
            List<AccessPoint> arrayList = new ArrayList<>(this.m);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Map.Entry<String, List<ScanResult>> entry : arrayMapS.entrySet()) {
                Iterator<ScanResult> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    NetworkKey networkKeyCreateFromScanResult = NetworkKey.createFromScanResult(it.next());
                    if (networkKeyCreateFromScanResult != null && !this.n.contains(networkKeyCreateFromScanResult)) {
                        arrayList3.add(networkKeyCreateFromScanResult);
                    }
                }
                final AccessPoint accessPointB = B(entry.getValue(), arrayList);
                List list3 = (List) list2.stream().filter(new Predicate() { // from class: com.android.settingslib.wifi.k
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return accessPointB.e0((WifiConfiguration) obj);
                    }
                }).collect(Collectors.toList());
                int size = list3.size();
                if (size == 0) {
                    accessPointB.m0(null);
                } else if (size == 1) {
                    accessPointB.m0((WifiConfiguration) list3.get(0));
                } else {
                    Optional optionalFindFirst = list3.stream().filter(new Predicate() { // from class: com.android.settingslib.wifi.n
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return WifiTracker.F((WifiConfiguration) obj);
                        }
                    }).findFirst();
                    if (optionalFindFirst.isPresent()) {
                        accessPointB.m0((WifiConfiguration) optionalFindFirst.get());
                    } else {
                        accessPointB.m0((WifiConfiguration) list3.get(0));
                    }
                }
                arrayList2.add(accessPointB);
            }
            ArrayList arrayList4 = new ArrayList(this.q.values());
            arrayList2.addAll(updatePasspointAccessPoints(this.d.getAllMatchingWifiConfigs(arrayList4), arrayList));
            arrayList2.addAll(updateOsuAccessPoints(this.d.getMatchingOsuProviders(arrayList4), arrayList));
            if (this.t != null && this.s != null) {
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ((AccessPoint) it2.next()).n0(wifiConfigurationE, this.t, this.s);
                }
            }
            if (arrayList2.isEmpty() && wifiConfigurationE != null) {
                AccessPoint accessPoint = new AccessPoint(this.f1391c, wifiConfigurationE);
                accessPoint.n0(wifiConfigurationE, this.t, this.s);
                arrayList2.add(accessPoint);
                arrayList3.add(NetworkKey.createFromWifiInfo(this.t));
            }
            M(arrayList3);
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                ((AccessPoint) it3.next()).o0(this.v, this.w, this.x);
            }
            Collections.sort(arrayList2);
            if (a()) {
                Log.d("WifiTracker", "------ Dumping AccessPoints that were not seen on this scan ------");
                Iterator<AccessPoint> it4 = this.m.iterator();
                while (it4.hasNext()) {
                    String strC = it4.next().C();
                    Iterator it5 = arrayList2.iterator();
                    while (true) {
                        if (!it5.hasNext()) {
                            z = false;
                            break;
                        }
                        AccessPoint accessPoint2 = (AccessPoint) it5.next();
                        if (accessPoint2.C() != null && accessPoint2.C().equals(strC)) {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        Log.d("WifiTracker", "Did not find " + strC + " in this scan");
                    }
                }
                Log.d("WifiTracker", "---- Done dumping AccessPoints that were not seen on this scan ----");
            }
            this.m.clear();
            this.m.addAll(arrayList2);
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q(NetworkInfo networkInfo) {
        if (!H()) {
            v();
            return;
        }
        if (networkInfo != null) {
            this.s = networkInfo;
            if (a()) {
                Log.d("WifiTracker", "mLastNetworkInfo set: " + this.s);
            }
            if (networkInfo.isConnected() != this.h.getAndSet(networkInfo.isConnected())) {
                this.i.c();
            }
        }
        this.t = this.d.getConnectionInfo();
        if (a()) {
            Log.d("WifiTracker", "mLastInfo set as: " + this.t);
        }
        WifiInfo wifiInfo = this.t;
        WifiConfiguration wifiConfigurationE = wifiInfo != null ? E(wifiInfo.getNetworkId(), this.d.getConfiguredNetworks()) : null;
        synchronized (this.l) {
            boolean z = false;
            boolean z2 = false;
            for (int size = this.m.size() - 1; size >= 0; size--) {
                AccessPoint accessPoint = this.m.get(size);
                boolean zE = accessPoint.E();
                if (accessPoint.n0(wifiConfigurationE, this.t, this.s)) {
                    if (zE != accessPoint.E()) {
                        z = true;
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (accessPoint.o0(this.v, this.w, this.x)) {
                    z = true;
                    z2 = true;
                }
            }
            if (z) {
                Collections.sort(this.m);
            }
            if (z2) {
                w();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        synchronized (this.l) {
            boolean z = false;
            for (int i = 0; i < this.m.size(); i++) {
                if (this.m.get(i).o0(this.v, this.w, this.x)) {
                    z = true;
                }
            }
            if (z) {
                Collections.sort(this.m);
                w();
            }
        }
    }

    private ArrayMap<String, List<ScanResult>> S(List<ScanResult> list) {
        List<ScanResult> list2;
        for (ScanResult scanResult : list) {
            String str = scanResult.SSID;
            if (str != null && !str.isEmpty()) {
                this.q.put(scanResult.BSSID, scanResult);
            }
        }
        x();
        ArrayMap<String, List<ScanResult>> arrayMap = new ArrayMap<>();
        for (ScanResult scanResult2 : this.q.values()) {
            String str2 = scanResult2.SSID;
            if (str2 != null && str2.length() != 0 && !scanResult2.capabilities.contains("[IBSS]")) {
                String strQ = AccessPoint.q(this.f1391c, scanResult2);
                if (arrayMap.containsKey(strQ)) {
                    list2 = arrayMap.get(strQ);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayMap.put(strQ, arrayList);
                    list2 = arrayList;
                }
                list2.add(scanResult2);
            }
        }
        return arrayMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(int i) {
        if (G()) {
            Log.d("WifiTracker", "updateWifiState: " + i);
        }
        if (i == 3) {
            synchronized (this.l) {
                if (this.mScanner != null) {
                    this.mScanner.b();
                }
            }
        } else {
            v();
            this.t = null;
            this.s = null;
            synchronized (this.l) {
                if (this.mScanner != null) {
                    this.mScanner.a();
                }
            }
            this.o = true;
        }
        this.i.a(i);
    }

    private static final boolean a() {
        return Log.isLoggable("WifiTracker", 3);
    }

    private void v() {
        synchronized (this.l) {
            if (!this.m.isEmpty()) {
                this.m.clear();
                w();
            }
        }
    }

    private void w() {
        if (this.o) {
            return;
        }
        this.i.b();
    }

    private void x() {
        long j = this.p ? MAX_SCAN_RESULT_AGE_MILLIS : 30000L;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Iterator<ScanResult> it = this.q.values().iterator();
        while (it.hasNext()) {
            if (jElapsedRealtime - (it.next().timestamp / 1000) > j) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        List<ScanResult> listZ = z(this.d.getScanResults());
        if (G()) {
            Log.i("WifiTracker", "Fetched scan results: " + listZ);
        }
        P(listZ, this.d.getConfiguredNetworks());
    }

    private List<ScanResult> z(List<ScanResult> list) {
        if (list == null) {
            return null;
        }
        boolean zIsEnhancedOpenSupported = this.d.isEnhancedOpenSupported();
        boolean zIsWpa3SaeSupported = this.d.isWpa3SaeSupported();
        boolean zIsWpa3SuiteBSupported = this.d.isWpa3SuiteBSupported();
        ArrayList arrayList = new ArrayList();
        for (ScanResult scanResult : list) {
            if (scanResult.capabilities.contains("PSK")) {
                arrayList.add(scanResult);
            } else if ((!scanResult.capabilities.contains("SUITE_B_192") || zIsWpa3SuiteBSupported) && ((!scanResult.capabilities.contains("SAE") || zIsWpa3SaeSupported) && (!scanResult.capabilities.contains("OWE") || zIsEnhancedOpenSupported))) {
                arrayList.add(scanResult);
            } else if (G()) {
                Log.v("WifiTracker", "filterScanResultsByCapabilities: Filtering SSID " + scanResult.SSID + " with capabilities: " + scanResult.capabilities);
            }
        }
        return arrayList;
    }

    public boolean H() {
        WifiManager wifiManager = this.d;
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    public void N() {
        synchronized (this.l) {
            if (this.mScanner == null) {
                this.mScanner = new Scanner();
            }
            if (H()) {
                this.mScanner.b();
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.b.i
    public void c() {
        forceUpdate();
        L();
        this.w = Settings.Global.getInt(this.f1391c.getContentResolver(), "network_scoring_ui_enabled", 0) == 1;
        this.x = Settings.Global.getLong(this.f1391c.getContentResolver(), "speed_label_cache_eviction_age_millis", 1200000L);
        N();
        if (this.r) {
            return;
        }
        this.f1391c.registerReceiver(this.mReceiver, this.e, null, this.mWorkHandler);
        d dVar = new d(this, null);
        this.k = dVar;
        this.f.registerNetworkCallback(this.g, dVar, this.mWorkHandler);
        this.r = true;
    }

    @VisibleForTesting
    void forceUpdate() {
        this.t = this.d.getConnectionInfo();
        this.s = this.f.getNetworkInfo(this.d.getCurrentNetwork());
        y();
    }

    @Override // com.android.settingslib.core.lifecycle.b.j
    public void g() {
        if (this.r) {
            this.f1391c.unregisterReceiver(this.mReceiver);
            this.f.unregisterNetworkCallback(this.k);
            this.r = false;
        }
        O();
        K();
        this.mWorkHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.android.settingslib.core.lifecycle.b.d
    public void l() {
        this.j.quit();
    }

    @VisibleForTesting
    void setWorkThread(HandlerThread handlerThread) {
        this.j = handlerThread;
        this.mWorkHandler = new Handler(handlerThread.getLooper());
        this.v = new WifiNetworkScoreCache(this.f1391c, new a(this.mWorkHandler));
    }

    @VisibleForTesting
    List<AccessPoint> updateOsuAccessPoints(Map<OsuProvider, List<ScanResult>> map, List<AccessPoint> list) {
        ArrayList arrayList = new ArrayList();
        Set setKeySet = this.d.getMatchingPasspointConfigsForOsuProviders(map.keySet()).keySet();
        for (OsuProvider osuProvider : map.keySet()) {
            if (!setKeySet.contains(osuProvider)) {
                arrayList.add(C(osuProvider, map.get(osuProvider), list));
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    List<AccessPoint> updatePasspointAccessPoints(List<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> list, List<AccessPoint> list2) {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        for (Pair<WifiConfiguration, Map<Integer, List<ScanResult>>> pair : list) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            if (arraySet.add(wifiConfiguration.FQDN)) {
                arrayList.add(D(wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1), list2));
            }
        }
        return arrayList;
    }
}
