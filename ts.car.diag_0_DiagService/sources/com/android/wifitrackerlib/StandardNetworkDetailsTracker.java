package com.android.wifitrackerlib;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
class StandardNetworkDetailsTracker extends NetworkDetailsTracker {
    private final StandardWifiEntry x;
    private NetworkInfo y;

    private void q() {
        this.n.b((List) this.g.getScanResults().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.n
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1468a.w((ScanResult) obj);
            }
        }).collect(Collectors.toList()));
    }

    private void r() {
        this.x.c0((WifiConfiguration) this.g.getPrivilegedConfiguredNetworks().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.m
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f1467a.t((WifiConfiguration) obj);
            }
        }).findAny().orElse(null));
    }

    private void s(boolean z) {
        if (this.g.getWifiState() == 1) {
            this.x.g0(Collections.emptyList());
            return;
        }
        long j = this.l;
        if (z) {
            q();
        } else {
            j += this.m;
        }
        this.x.g0(this.n.a(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean t(@NonNull WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint()) {
            return false;
        }
        return TextUtils.equals(wifiConfiguration.fromWifiNetworkSpecifier ? NetworkRequestEntry.i0(wifiConfiguration) : StandardWifiEntry.h0(wifiConfiguration), this.x.j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean w(ScanResult scanResult) {
        return TextUtils.equals(scanResult.SSID, this.x.S()) && e0.m(scanResult).contains(Integer.valueOf(this.x.p()));
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    @WorkerThread
    protected void c() {
        s(true);
        r();
        WifiInfo connectionInfo = this.g.getConnectionInfo();
        Network currentNetwork = this.g.getCurrentNetwork();
        NetworkInfo networkInfo = this.h.getNetworkInfo(currentNetwork);
        this.y = networkInfo;
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
