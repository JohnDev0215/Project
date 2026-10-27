package com.android.wifitrackerlib;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
class PasspointNetworkDetailsTracker extends NetworkDetailsTracker {
    private WifiConfiguration A;
    private final PasspointWifiEntry x;
    private c0 y;
    private NetworkInfo z;

    private void q() {
        this.n.b(this.g.getScanResults());
    }

    private void r() {
        this.g.getPasspointConfigurations().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.f
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1460a.u((PasspointConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1461c.w((PasspointConfiguration) obj);
            }
        });
    }

    private void s(boolean z) {
        if (this.g.getWifiState() == 1) {
            this.x.U(this.A, null, null);
            return;
        }
        long j = this.l;
        if (z) {
            q();
        } else {
            j += this.m;
        }
        List<ScanResult> listA = this.n.a(j);
        y(listA);
        x(listA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean u(PasspointConfiguration passpointConfiguration) {
        return TextUtils.equals(PasspointWifiEntry.S(passpointConfiguration.getUniqueId()), this.x.j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void w(PasspointConfiguration passpointConfiguration) {
        this.x.T(passpointConfiguration);
    }

    @WorkerThread
    private void x(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        Map matchingOsuProviders = this.g.getMatchingOsuProviders(list);
        Map matchingPasspointConfigsForOsuProviders = this.g.getMatchingPasspointConfigsForOsuProviders(matchingOsuProviders.keySet());
        c0 c0Var = this.y;
        if (c0Var != null) {
            c0Var.T((List) matchingOsuProviders.get(c0Var.P()));
        } else {
            for (OsuProvider osuProvider : matchingOsuProviders.keySet()) {
                PasspointConfiguration passpointConfiguration = (PasspointConfiguration) matchingPasspointConfigsForOsuProviders.get(osuProvider);
                if (passpointConfiguration != null && TextUtils.equals(this.x.j(), PasspointWifiEntry.S(passpointConfiguration.getUniqueId()))) {
                    c0 c0Var2 = new c0(this.f, this.j, osuProvider, this.g, this.o, false);
                    this.y = c0Var2;
                    c0Var2.T((List) matchingOsuProviders.get(osuProvider));
                    this.y.S(true);
                    this.x.R(this.y);
                    return;
                }
            }
        }
        c0 c0Var3 = this.y;
        if (c0Var3 == null || c0Var3.k() != -1) {
            return;
        }
        this.x.R(null);
        this.y = null;
    }

    @WorkerThread
    private void y(@NonNull List<ScanResult> list) {
        androidx.core.d.e.c(list, "Scan Result list should not be null!");
        for (Pair pair : this.g.getAllMatchingWifiConfigs(list)) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            if (TextUtils.equals(PasspointWifiEntry.S(wifiConfiguration.getKey()), this.x.j())) {
                this.A = wifiConfiguration;
                this.x.U(wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1));
                return;
            }
        }
        this.x.U(this.A, null, null);
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    @WorkerThread
    protected void c() {
        s(true);
        r();
        WifiInfo connectionInfo = this.g.getConnectionInfo();
        Network currentNetwork = this.g.getCurrentNetwork();
        NetworkInfo networkInfo = this.h.getNetworkInfo(currentNetwork);
        this.z = networkInfo;
        this.x.M(connectionInfo, networkInfo);
        p(this.h.getNetworkCapabilities(currentNetwork));
        o(this.h.getLinkProperties(currentNetwork));
        this.x.I(this.q);
        this.x.J(this.p && this.r);
    }

    @Override // com.android.wifitrackerlib.NetworkDetailsTracker
    @NonNull
    @AnyThread
    public WifiEntry n() {
        return this.x;
    }
}
