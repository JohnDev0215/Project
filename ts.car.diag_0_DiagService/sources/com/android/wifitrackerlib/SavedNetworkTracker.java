package com.android.wifitrackerlib;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class SavedNetworkTracker extends BaseWifiTracker {

    @GuardedBy("mLock")
    private final List<WifiEntry> A;
    private final Map<String, StandardWifiEntry> B;
    private final Map<String, PasspointWifiEntry> C;
    private final a x;
    private final Object y;

    @GuardedBy("mLock")
    private final List<WifiEntry> z;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        @MainThread
        void a();

        @MainThread
        void b();
    }

    @WorkerThread
    private void n(boolean z) {
        if (this.g.getWifiState() == 1) {
            y(Collections.emptyList());
            v(Collections.emptyList());
            return;
        }
        long j = this.l;
        if (z) {
            this.n.b(this.g.getScanResults());
        } else {
            j += this.m;
        }
        y(this.n.a(j));
        v(this.n.a(j));
    }

    static /* synthetic */ boolean p(Map map, Map.Entry entry) {
        PasspointWifiEntry passpointWifiEntry = (PasspointWifiEntry) entry.getValue();
        PasspointConfiguration passpointConfiguration = (PasspointConfiguration) map.remove(passpointWifiEntry.j());
        if (passpointConfiguration == null) {
            return true;
        }
        passpointWifiEntry.T(passpointConfiguration);
        return false;
    }

    static /* synthetic */ boolean q(Map map, Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        standardWifiEntry.c0((WifiConfiguration) map.remove(standardWifiEntry.j()));
        return !standardWifiEntry.y();
    }

    @WorkerThread
    private void s() {
        final a aVar = this.x;
        if (aVar != null) {
            Handler handler = this.j;
            Objects.requireNonNull(aVar);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.a0
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.b();
                }
            });
        }
    }

    @WorkerThread
    private void t() {
        final a aVar = this.x;
        if (aVar != null) {
            Handler handler = this.j;
            Objects.requireNonNull(aVar);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.W
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.a();
                }
            });
        }
    }

    @WorkerThread
    private void u(@NonNull List<PasspointConfiguration> list) {
        androidx.core.d.e.c(list, "Config list should not be null!");
        final Map map = (Map) list.stream().collect(Collectors.toMap(new Function() { // from class: com.android.wifitrackerlib.i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PasspointWifiEntry.S(((PasspointConfiguration) obj).getUniqueId());
            }
        }, Function.identity()));
        this.C.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.l
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SavedNetworkTracker.p(map, (Map.Entry) obj);
            }
        });
        for (String str : map.keySet()) {
            this.C.put(str, new PasspointWifiEntry(this.f, this.j, (PasspointConfiguration) map.get(str), this.g, this.o, true));
        }
    }

    private void v(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        TreeSet treeSet = new TreeSet();
        for (Pair pair : this.g.getAllMatchingWifiConfigs(list)) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            String strS = PasspointWifiEntry.S(wifiConfiguration.getKey());
            treeSet.add(strS);
            if (this.C.containsKey(strS)) {
                this.C.get(strS).U(wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1));
            }
        }
        for (PasspointWifiEntry passpointWifiEntry : this.C.values()) {
            if (!treeSet.contains(passpointWifiEntry.j())) {
                passpointWifiEntry.U(null, null, null);
            }
        }
    }

    private void w() {
        synchronized (this.y) {
            this.z.clear();
            this.z.addAll(this.B.values());
            Collections.sort(this.z);
            if (BaseWifiTracker.g()) {
                Log.v("SavedNetworkTracker", "Updated SavedWifiEntries: " + Arrays.toString(this.z.toArray()));
            }
        }
        s();
    }

    private void x(@NonNull List<WifiConfiguration> list) {
        androidx.core.d.e.c(list, "Config list should not be null!");
        final Map map = (Map) list.stream().collect(Collectors.toMap(X.f1449a, Function.identity()));
        this.B.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.k
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SavedNetworkTracker.q(map, (Map.Entry) obj);
            }
        });
        for (String str : map.keySet()) {
            this.B.put(str, new StandardWifiEntry(this.f, this.j, str, (WifiConfiguration) map.get(str), this.g, this.o, true));
        }
    }

    private void y(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        final Map<String, List<ScanResult>> mapU = e0.u(list, false, null, this.g.isWpa3SaeSupported(), this.g.isWpa3SuiteBSupported(), this.g.isEnhancedOpenSupported());
        this.B.entrySet().forEach(new Consumer() { // from class: com.android.wifitrackerlib.j
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map.Entry entry = (Map.Entry) obj;
                ((StandardWifiEntry) entry.getValue()).g0((List) mapU.get((String) entry.getKey()));
            }
        });
    }

    private void z() {
        synchronized (this.y) {
            this.A.clear();
            this.A.addAll(this.C.values());
            Collections.sort(this.A);
            if (BaseWifiTracker.g()) {
                Log.v("SavedNetworkTracker", "Updated SubscriptionWifiEntries: " + Arrays.toString(this.A.toArray()));
            }
        }
        t();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    @WorkerThread
    protected void c() {
        x(this.g.getConfiguredNetworks());
        u(this.g.getPasspointConfigurations());
        n(true);
        w();
        z();
    }
}
