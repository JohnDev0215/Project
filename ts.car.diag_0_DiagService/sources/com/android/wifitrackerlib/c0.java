package com.android.wifitrackerlib;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
class c0 extends WifiEntry {
    private boolean A;
    private final Object t;

    @NonNull
    @GuardedBy("mLock")
    private final List<ScanResult> u;

    @NonNull
    private final String v;

    @NonNull
    private final Context w;

    @NonNull
    private OsuProvider x;
    private String y;
    private String z;

    c0(@NonNull Context context, @NonNull Handler handler, @NonNull OsuProvider osuProvider, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        super(handler, wifiManager, wifiNetworkScoreCache, z);
        this.t = new Object();
        this.u = new ArrayList();
        this.A = false;
        androidx.core.d.e.c(osuProvider, "Cannot construct with null osuProvider!");
        this.w = context;
        this.x = osuProvider;
        this.v = R(osuProvider);
    }

    @NonNull
    static String R(@NonNull OsuProvider osuProvider) {
        androidx.core.d.e.c(osuProvider, "Cannot create key with null OsuProvider!");
        return "OsuWifiEntry:" + osuProvider.getFriendlyName() + "," + osuProvider.getServerUri().toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean A() {
        return false;
    }

    OsuProvider P() {
        return this.x;
    }

    boolean Q() {
        return this.A;
    }

    void S(boolean z) {
        this.A = z;
    }

    @WorkerThread
    void T(@Nullable List<ScanResult> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        synchronized (this.t) {
            this.u.clear();
            this.u.addAll(list);
        }
        ScanResult scanResultE = e0.e(list);
        if (scanResultE != null) {
            this.y = scanResultE.SSID;
            if (h() == 0) {
                this.f = this.f1444c.calculateSignalLevel(scanResultE.level);
            }
        } else {
            this.f = -1;
        }
        H();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean a() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean b() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean d() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    @WorkerThread
    protected boolean f(@NonNull WifiInfo wifiInfo, @NonNull NetworkInfo networkInfo) {
        return wifiInfo.isOsuAp() && TextUtils.equals(wifiInfo.getPasspointProviderFriendlyName(), this.x.getFriendlyName());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String j() {
        return this.v;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int l() {
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String o() {
        return "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int p() {
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String s(boolean z) {
        String str = this.z;
        return str != null ? str : Q() ? z ? this.w.getString(R$string.wifi_passpoint_expired) : this.w.getString(R$string.tap_to_renew_subscription_and_connect) : this.w.getString(R$string.tap_to_sign_up);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String t() {
        String friendlyName = this.x.getFriendlyName();
        if (!TextUtils.isEmpty(friendlyName)) {
            return friendlyName;
        }
        if (!TextUtils.isEmpty(this.y)) {
            return this.y;
        }
        Uri serverUri = this.x.getServerUri();
        return serverUri != null ? serverUri.toString() : "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration u() {
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean w() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean x() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean y() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean z() {
        return false;
    }
}
