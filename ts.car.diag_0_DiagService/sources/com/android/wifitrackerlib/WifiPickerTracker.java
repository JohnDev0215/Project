package com.android.wifitrackerlib;

import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class WifiPickerTracker extends BaseWifiTracker {
    private WifiEntry A;
    private NetworkRequestEntry B;
    private NetworkInfo C;
    private final Map<String, WifiConfiguration> D;
    private final Map<String, WifiConfiguration> E;
    private final Map<String, StandardWifiEntry> F;
    private final Map<String, StandardWifiEntry> G;
    private final Map<String, PasspointConfiguration> H;
    private final Map<String, PasspointWifiEntry> I;
    private final Map<String, c0> J;
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

        @MainThread
        void c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfiguration) {
        StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.f, this.j, StandardWifiEntry.h0(wifiConfiguration), wifiConfiguration, this.g, this.o, false);
        standardWifiEntry.M(wifiInfo, networkInfo);
        this.F.put(standardWifiEntry.j(), standardWifiEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean D(int i, WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.networkId == i && !this.G.containsKey(StandardWifiEntry.h0(wifiConfiguration));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void F(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfiguration) {
        StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.f, this.j, StandardWifiEntry.h0(wifiConfiguration), wifiConfiguration, this.g, this.o, false);
        standardWifiEntry.M(wifiInfo, networkInfo);
        this.G.put(standardWifiEntry.j(), standardWifiEntry);
    }

    static /* synthetic */ boolean G(String str, int i, ScanResult scanResult) {
        return TextUtils.equals(scanResult.SSID, str) && e0.m(scanResult).contains(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void I(Map map, c0 c0Var) {
        PasspointConfiguration passpointConfiguration = (PasspointConfiguration) map.get(c0Var.P());
        if (passpointConfiguration == null) {
            c0Var.S(false);
            return;
        }
        c0Var.S(true);
        PasspointWifiEntry passpointWifiEntry = this.I.get(PasspointWifiEntry.S(passpointConfiguration.getUniqueId()));
        if (passpointWifiEntry == null) {
            return;
        }
        passpointWifiEntry.R(c0Var);
    }

    static /* synthetic */ boolean J(Map.Entry entry) {
        return ((c0) entry.getValue()).k() == -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean M(Map.Entry entry) {
        PasspointWifiEntry passpointWifiEntry = (PasspointWifiEntry) entry.getValue();
        passpointWifiEntry.T(this.H.get(passpointWifiEntry.j()));
        return (passpointWifiEntry.z() || passpointWifiEntry.A()) ? false : true;
    }

    static /* synthetic */ boolean N(Set set, Map.Entry entry) {
        return ((PasspointWifiEntry) entry.getValue()).k() == -1 || (!set.contains(entry.getKey()) && ((PasspointWifiEntry) entry.getValue()).h() == 0);
    }

    static /* synthetic */ boolean O(Map map, Map.Entry entry) {
        String str = (String) entry.getKey();
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        standardWifiEntry.g0((List) map.remove(str));
        return standardWifiEntry.k() == -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean Q(Set set, Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        String str = (String) entry.getKey();
        if (!set.contains(str)) {
            standardWifiEntry.c0(this.E.get(str));
            standardWifiEntry.Z(false);
        }
        return !standardWifiEntry.A();
    }

    static /* synthetic */ boolean R(WifiConfiguration wifiConfiguration) {
        return (wifiConfiguration.isEphemeral() || wifiConfiguration.isPasspoint()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void T(Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        WifiConfiguration wifiConfiguration = this.D.get(standardWifiEntry.j());
        if (wifiConfiguration == null || !wifiConfiguration.isPasspoint()) {
            standardWifiEntry.c0(wifiConfiguration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean V(Map.Entry entry) {
        StandardWifiEntry standardWifiEntry = (StandardWifiEntry) entry.getValue();
        WifiConfiguration wifiConfiguration = this.E.get(standardWifiEntry.j());
        if (wifiConfiguration == null || wifiConfiguration.isPasspoint()) {
            return true;
        }
        standardWifiEntry.c0(wifiConfiguration);
        return false;
    }

    static /* synthetic */ boolean W(StandardWifiEntry standardWifiEntry) {
        int iH = standardWifiEntry.h();
        return iH == 2 || iH == 1;
    }

    static /* synthetic */ boolean X(StandardWifiEntry standardWifiEntry) {
        int iH = standardWifiEntry.h();
        return iH == 2 || iH == 1;
    }

    static /* synthetic */ boolean Y(PasspointWifiEntry passpointWifiEntry) {
        int iH = passpointWifiEntry.h();
        return iH == 2 || iH == 1;
    }

    static /* synthetic */ boolean Z(PasspointWifiEntry passpointWifiEntry) {
        return passpointWifiEntry.h() == 0;
    }

    static /* synthetic */ boolean a0(c0 c0Var) {
        return c0Var.h() == 0 && !c0Var.Q();
    }

    @WorkerThread
    private void b0() {
        final a aVar = this.x;
        if (aVar != null) {
            Handler handler = this.j;
            Objects.requireNonNull(aVar);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.Y
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.b();
                }
            });
        }
    }

    @WorkerThread
    private void c0() {
        final a aVar = this.x;
        if (aVar != null) {
            Handler handler = this.j;
            Objects.requireNonNull(aVar);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.b0
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.c();
                }
            });
        }
    }

    @WorkerThread
    private void d0() {
        final a aVar = this.x;
        if (aVar != null) {
            Handler handler = this.j;
            Objects.requireNonNull(aVar);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.a
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.a();
                }
            });
        }
    }

    @WorkerThread
    private void e0(@Nullable WifiInfo wifiInfo, @Nullable NetworkInfo networkInfo) {
        Iterator<StandardWifiEntry> it = this.F.values().iterator();
        while (it.hasNext()) {
            it.next().M(wifiInfo, networkInfo);
        }
        Iterator<StandardWifiEntry> it2 = this.G.values().iterator();
        while (it2.hasNext()) {
            it2.next().M(wifiInfo, networkInfo);
        }
        Iterator<PasspointWifiEntry> it3 = this.I.values().iterator();
        while (it3.hasNext()) {
            it3.next().M(wifiInfo, networkInfo);
        }
        Iterator<c0> it4 = this.J.values().iterator();
        while (it4.hasNext()) {
            it4.next().M(wifiInfo, networkInfo);
        }
        NetworkRequestEntry networkRequestEntry = this.B;
        if (networkRequestEntry != null) {
            networkRequestEntry.M(wifiInfo, networkInfo);
        }
        o(wifiInfo, networkInfo);
        p(wifiInfo, networkInfo);
        n(wifiInfo, networkInfo);
    }

    @WorkerThread
    private void f0(@Nullable WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            this.B = null;
            return;
        }
        String strI0 = NetworkRequestEntry.i0(wifiConfiguration);
        NetworkRequestEntry networkRequestEntry = this.B;
        if (networkRequestEntry == null || !TextUtils.equals(strI0, networkRequestEntry.j())) {
            this.B = new NetworkRequestEntry(this.f, this.j, strI0, this.g, this.o, false);
        }
        this.B.c0(wifiConfiguration);
    }

    @WorkerThread
    private void g0(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        NetworkRequestEntry networkRequestEntry = this.B;
        if (networkRequestEntry == null) {
            return;
        }
        final String strS = networkRequestEntry.S();
        final int iP = this.B.p();
        this.B.g0((List) list.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.Q
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.G(strS, iP, (ScanResult) obj);
            }
        }).collect(Collectors.toList()));
    }

    @WorkerThread
    private void h0(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        Map matchingOsuProviders = this.g.getMatchingOsuProviders(list);
        final Map matchingPasspointConfigsForOsuProviders = this.g.getMatchingPasspointConfigsForOsuProviders(matchingOsuProviders.keySet());
        for (c0 c0Var : this.J.values()) {
            c0Var.T((List) matchingOsuProviders.remove(c0Var.P()));
        }
        for (OsuProvider osuProvider : matchingOsuProviders.keySet()) {
            c0 c0Var2 = new c0(this.f, this.j, osuProvider, this.g, this.o, false);
            c0Var2.T((List) matchingOsuProviders.get(osuProvider));
            this.J.put(c0.R(osuProvider), c0Var2);
        }
        this.J.values().forEach(new Consumer() { // from class: com.android.wifitrackerlib.U
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1441c.I(matchingPasspointConfigsForOsuProviders, (c0) obj);
            }
        });
        this.J.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.H
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.J((Map.Entry) obj);
            }
        });
    }

    @WorkerThread
    private void i0(@NonNull List<PasspointConfiguration> list) {
        androidx.core.d.e.c(list, "Config list should not be null!");
        this.H.clear();
        this.H.putAll((Map) list.stream().collect(Collectors.toMap(new Function() { // from class: com.android.wifitrackerlib.D
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PasspointWifiEntry.S(((PasspointConfiguration) obj).getUniqueId());
            }
        }, Function.identity())));
        this.I.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.P
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1435a.M((Map.Entry) obj);
            }
        });
    }

    @WorkerThread
    private void j0(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        final TreeSet treeSet = new TreeSet();
        for (Pair pair : this.g.getAllMatchingWifiConfigs(list)) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            List<ScanResult> list2 = (List) ((Map) pair.second).get(0);
            List<ScanResult> list3 = (List) ((Map) pair.second).get(1);
            String strS = PasspointWifiEntry.S(wifiConfiguration.getKey());
            treeSet.add(strS);
            if (!this.I.containsKey(strS)) {
                if (wifiConfiguration.fromWifiNetworkSuggestion) {
                    this.I.put(strS, new PasspointWifiEntry(this.f, this.j, wifiConfiguration, this.g, this.o, false));
                } else if (this.H.containsKey(strS)) {
                    this.I.put(strS, new PasspointWifiEntry(this.f, this.j, this.H.get(strS), this.g, this.o, false));
                }
            }
            this.I.get(strS).U(wifiConfiguration, list2, list3);
        }
        this.I.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.J
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.N(treeSet, (Map.Entry) obj);
            }
        });
    }

    @WorkerThread
    private void k0(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        final Map<String, List<ScanResult>> mapU = e0.u(list, true, this.D, this.g.isWpa3SaeSupported(), this.g.isWpa3SuiteBSupported(), this.g.isEnhancedOpenSupported());
        this.F.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.z
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.O(mapU, (Map.Entry) obj);
            }
        });
        for (Map.Entry<String, List<ScanResult>> entry : mapU.entrySet()) {
            StandardWifiEntry standardWifiEntry = new StandardWifiEntry(this.f, this.j, entry.getKey(), entry.getValue(), this.g, this.o, false);
            standardWifiEntry.c0(this.D.get(standardWifiEntry.j()));
            this.F.put(standardWifiEntry.j(), standardWifiEntry);
        }
    }

    @WorkerThread
    private void l0(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        Map<String, List<ScanResult>> mapU = e0.u(list, true, this.D, this.g.isWpa3SaeSupported(), this.g.isWpa3SuiteBSupported(), this.g.isEnhancedOpenSupported());
        Map map = (Map) this.g.getWifiConfigForMatchedNetworkSuggestionsSharedWithUser(list).stream().collect(Collectors.toMap(X.f1449a, Function.identity()));
        final TreeSet treeSet = new TreeSet();
        for (String str : map.keySet()) {
            treeSet.add(str);
            if (!this.G.containsKey(str)) {
                this.G.put(str, new StandardWifiEntry(this.f, this.j, str, (WifiConfiguration) map.get(str), this.g, this.o, false));
            }
            StandardWifiEntry standardWifiEntry = this.G.get(str);
            standardWifiEntry.Z(true);
            standardWifiEntry.g0(mapU.get(str));
        }
        this.G.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.I
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1426a.Q(treeSet, (Map.Entry) obj);
            }
        });
    }

    @WorkerThread
    private void m0(@NonNull List<WifiConfiguration> list) {
        androidx.core.d.e.c(list, "Config list should not be null!");
        this.D.clear();
        this.E.clear();
        boolean z = false;
        for (WifiConfiguration wifiConfiguration : list) {
            if (wifiConfiguration.fromWifiNetworkSuggestion) {
                this.E.put(StandardWifiEntry.h0(wifiConfiguration), wifiConfiguration);
            } else if (wifiConfiguration.fromWifiNetworkSpecifier) {
                z = true;
                f0(wifiConfiguration);
            } else {
                this.D.put(StandardWifiEntry.h0(wifiConfiguration), wifiConfiguration);
            }
        }
        if (!z) {
            f0(null);
        }
        this.D.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.C
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.R((WifiConfiguration) obj);
            }
        }).count();
        this.F.entrySet().forEach(new Consumer() { // from class: com.android.wifitrackerlib.O
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1434c.T((Map.Entry) obj);
            }
        });
        this.G.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.N
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1433a.V((Map.Entry) obj);
            }
        });
    }

    @WorkerThread
    private void n(@Nullable final WifiInfo wifiInfo, @Nullable final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp()) {
            final int networkId = wifiInfo.getNetworkId();
            Stream.concat(this.D.values().stream(), this.E.values().stream()).filter(new Predicate() { // from class: com.android.wifitrackerlib.K
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f1429a.v(networkId, (WifiConfiguration) obj);
                }
            }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.L
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f1431c.x(wifiInfo, networkInfo, (WifiConfiguration) obj);
                }
            });
        }
    }

    @WorkerThread
    private void n0() {
        synchronized (this.y) {
            StandardWifiEntry standardWifiEntryOrElse = this.F.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.y
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.W((StandardWifiEntry) obj);
                }
            }).findAny().orElse(null);
            this.A = standardWifiEntryOrElse;
            if (standardWifiEntryOrElse == null) {
                this.A = this.G.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.S
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WifiPickerTracker.X((StandardWifiEntry) obj);
                    }
                }).findAny().orElse(null);
            }
            if (this.A == null) {
                this.A = this.I.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.M
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WifiPickerTracker.Y((PasspointWifiEntry) obj);
                    }
                }).findAny().orElse(null);
            }
            if (this.A == null && this.B != null && this.B.h() != 0) {
                this.A = this.B;
            }
            this.z.clear();
            for (String str : this.F.keySet()) {
                if (this.A == null || !TextUtils.equals(str, this.A.j())) {
                    StandardWifiEntry standardWifiEntry = this.F.get(str);
                    StandardWifiEntry standardWifiEntry2 = this.G.get(str);
                    if (standardWifiEntry.y() || standardWifiEntry2 == null || !standardWifiEntry2.T()) {
                        if (standardWifiEntry.h() == 0) {
                            this.z.add(standardWifiEntry);
                        }
                    } else if (standardWifiEntry2.h() == 0) {
                        this.z.add(standardWifiEntry2);
                    }
                }
            }
            this.z.addAll((Collection) this.I.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.F
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.Z((PasspointWifiEntry) obj);
                }
            }).collect(Collectors.toList()));
            this.z.addAll((Collection) this.J.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.G
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.a0((c0) obj);
                }
            }).collect(Collectors.toList()));
            Collections.sort(this.z);
            if (BaseWifiTracker.g()) {
                Log.v("WifiPickerTracker", "Connected WifiEntry: " + this.A);
                Log.v("WifiPickerTracker", "Updated WifiEntries: " + Arrays.toString(this.z.toArray()));
            }
        }
        d0();
    }

    @WorkerThread
    private void o(@Nullable final WifiInfo wifiInfo, @Nullable final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) {
            return;
        }
        final int networkId = wifiInfo.getNetworkId();
        this.D.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.B
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1415a.z(networkId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.E
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1422c.B(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    @WorkerThread
    private void p(@Nullable final WifiInfo wifiInfo, @Nullable final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) {
            return;
        }
        final int networkId = wifiInfo.getNetworkId();
        this.E.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.T
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1439a.D(networkId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.A
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1414c.F(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    @WorkerThread
    private void q(boolean z) {
        if (this.g.getWifiState() == 1) {
            k0(Collections.emptyList());
            l0(Collections.emptyList());
            j0(Collections.emptyList());
            h0(Collections.emptyList());
            g0(Collections.emptyList());
            return;
        }
        long j = this.l;
        if (z) {
            this.n.b(this.g.getScanResults());
        } else {
            j += this.m;
        }
        List<ScanResult> listA = this.n.a(j);
        k0(listA);
        l0(listA);
        j0(listA);
        h0(listA);
        g0(listA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean v(int i, WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.isPasspoint() && wifiConfiguration.networkId == i && !this.I.containsKey(PasspointWifiEntry.S(wifiConfiguration.getKey()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void x(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfiguration) {
        PasspointConfiguration passpointConfiguration = this.H.get(PasspointWifiEntry.S(wifiConfiguration.getKey()));
        PasspointWifiEntry passpointWifiEntry = passpointConfiguration != null ? new PasspointWifiEntry(this.f, this.j, passpointConfiguration, this.g, this.o, false) : new PasspointWifiEntry(this.f, this.j, wifiConfiguration, this.g, this.o, false);
        passpointWifiEntry.M(wifiInfo, networkInfo);
        this.I.put(passpointWifiEntry.j(), passpointWifiEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean z(int i, WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.networkId == i && !this.F.containsKey(StandardWifiEntry.h0(wifiConfiguration));
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    @WorkerThread
    protected void c() {
        m0(this.g.getPrivilegedConfiguredNetworks());
        i0(this.g.getPasspointConfigurations());
        this.n.b(this.g.getScanResults());
        q(true);
        WifiInfo connectionInfo = this.g.getConnectionInfo();
        Network currentNetwork = this.g.getCurrentNetwork();
        NetworkInfo networkInfo = this.h.getNetworkInfo(currentNetwork);
        this.C = networkInfo;
        e0(connectionInfo, networkInfo);
        b0();
        c0();
        n0();
        t(this.h.getNetworkCapabilities(currentNetwork));
        s(this.h.getLinkProperties(currentNetwork));
        r();
    }

    @WorkerThread
    protected void r() {
        WifiEntry wifiEntry = this.A;
        if (wifiEntry != null) {
            wifiEntry.I(this.q);
            this.A.J(this.p && this.r);
        }
    }

    @WorkerThread
    protected void s(@Nullable LinkProperties linkProperties) {
        WifiEntry wifiEntry = this.A;
        if (wifiEntry == null || wifiEntry.h() != 2) {
            return;
        }
        this.A.N(linkProperties);
    }

    @WorkerThread
    protected void t(@Nullable NetworkCapabilities networkCapabilities) {
        WifiEntry wifiEntry = this.A;
        if (wifiEntry == null || wifiEntry.h() != 2) {
            return;
        }
        this.A.O(networkCapabilities);
        this.A.J(this.p && this.r);
    }
}
