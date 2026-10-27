package com.android.wifitrackerlib;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.android.internal.annotations.VisibleForTesting;
import com.android.wifitrackerlib.WifiEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@VisibleForTesting
public class PasspointWifiEntry extends WifiEntry implements WifiEntry.c {

    @Nullable
    private PasspointConfiguration A;

    @Nullable
    private WifiConfiguration B;
    private int C;
    private c0 D;
    protected long E;
    private int F;
    private final Object t;

    @GuardedBy("mLock")
    private final List<ScanResult> u;

    @GuardedBy("mLock")
    private final List<ScanResult> v;

    @NonNull
    private final String w;

    @NonNull
    private String x;

    @NonNull
    private String y;

    @NonNull
    private final Context z;

    PasspointWifiEntry(@NonNull Context context, @NonNull Handler handler, @NonNull PasspointConfiguration passpointConfiguration, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        super(handler, wifiManager, wifiNetworkScoreCache, z);
        this.t = new Object();
        this.u = new ArrayList();
        this.v = new ArrayList();
        this.C = 3;
        this.F = 0;
        androidx.core.d.e.c(passpointConfiguration, "Cannot construct with null PasspointConfiguration!");
        this.z = context;
        this.A = passpointConfiguration;
        this.w = S(passpointConfiguration.getUniqueId());
        this.x = passpointConfiguration.getHomeSp().getFqdn();
        this.y = passpointConfiguration.getHomeSp().getFriendlyName();
        this.E = passpointConfiguration.getSubscriptionExpirationTimeMillis();
        this.F = this.A.getMeteredOverride();
    }

    private String P() {
        if (h() == 2) {
            WifiInfo wifiInfo = this.h;
            String strF = null;
            String requestingPackageName = wifiInfo != null ? wifiInfo.getRequestingPackageName() : null;
            if (!TextUtils.isEmpty(requestingPackageName)) {
                WifiConfiguration wifiConfiguration = this.B;
                if (wifiConfiguration != null) {
                    Context context = this.z;
                    strF = e0.f(context, e0.p(context, wifiConfiguration));
                }
                String strB = e0.b(this.z, requestingPackageName);
                if (!TextUtils.isEmpty(strB)) {
                    requestingPackageName = strB;
                }
                Context context2 = this.z;
                int i = R$string.connected_via_app;
                Object[] objArr = new Object[1];
                if (strF == null) {
                    strF = requestingPackageName;
                }
                objArr[0] = strF;
                return context2.getString(i, objArr);
            }
            if (this.s) {
                return this.z.getString(R$string.wifi_connected_low_quality);
            }
            String strG = e0.g(this.z, this.j);
            if (!TextUtils.isEmpty(strG)) {
                return strG;
            }
        }
        return e0.j(this.z, this.i);
    }

    @NonNull
    static String S(@NonNull String str) {
        androidx.core.d.e.c(str, "Cannot create key with null unique id!");
        return "PasspointWifiEntry:" + str;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean A() {
        WifiConfiguration wifiConfiguration = this.B;
        return wifiConfiguration != null && wifiConfiguration.fromWifiNetworkSuggestion;
    }

    public boolean Q() {
        return this.E > 0 && System.currentTimeMillis() >= this.E;
    }

    void R(c0 c0Var) {
        this.D = c0Var;
        if (c0Var != null) {
            c0Var.K(this);
        }
    }

    @WorkerThread
    void T(@Nullable PasspointConfiguration passpointConfiguration) {
        this.A = passpointConfiguration;
        if (passpointConfiguration != null) {
            this.y = passpointConfiguration.getHomeSp().getFriendlyName();
            this.E = passpointConfiguration.getSubscriptionExpirationTimeMillis();
            this.F = passpointConfiguration.getMeteredOverride();
        }
        H();
    }

    @WorkerThread
    void U(@Nullable WifiConfiguration wifiConfiguration, @Nullable List<ScanResult> list, @Nullable List<ScanResult> list2) {
        this.B = wifiConfiguration;
        synchronized (this.t) {
            this.u.clear();
            this.v.clear();
            if (list != null) {
                this.u.addAll(list);
            }
            if (list2 != null) {
                this.v.addAll(list2);
            }
        }
        if (this.B != null) {
            this.C = e0.l(wifiConfiguration);
            ArrayList arrayList = new ArrayList();
            if (list != null && !list.isEmpty()) {
                arrayList.addAll(list);
            } else if (list2 != null && !list2.isEmpty()) {
                arrayList.addAll(list2);
            }
            ScanResult scanResultE = e0.e(arrayList);
            if (scanResultE != null) {
                this.B.SSID = "\"" + scanResultE.SSID + "\"";
            }
            if (h() == 0) {
                this.f = scanResultE != null ? this.f1444c.calculateSignalLevel(scanResultE.level) : -1;
                this.g = e0.d(this.l, arrayList);
            }
        } else {
            this.f = -1;
        }
        H();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean a() {
        return (this.A == null && this.B == null) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean b() {
        return (A() || this.A == null) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry.c
    public void c() {
        H();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean d() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    @WorkerThread
    protected boolean f(@NonNull WifiInfo wifiInfo, @NonNull NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp()) {
            return TextUtils.equals(wifiInfo.getPasspointFqdn(), this.x);
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int h() {
        c0 c0Var;
        return (Q() && super.h() == 0 && (c0Var = this.D) != null) ? c0Var.h() : super.h();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String j() {
        return this.w;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int l() {
        int i = this.F;
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 2 : 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String n() {
        return e0.k(this.B);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String o() {
        return "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int p() {
        return this.C;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String s(boolean z) {
        StringJoiner stringJoiner = new StringJoiner(this.z.getString(R$string.summary_separator));
        if (Q()) {
            c0 c0Var = this.D;
            if (c0Var != null) {
                stringJoiner.add(c0Var.s(z));
            } else {
                stringJoiner.add(this.z.getString(R$string.wifi_passpoint_expired));
            }
        } else if (h() == 0) {
            String strH = e0.h(this.z, this);
            if (!TextUtils.isEmpty(strH)) {
                stringJoiner.add(strH);
            } else if (z) {
                stringJoiner.add(this.z.getString(R$string.wifi_disconnected));
            } else if (!this.mForSavedNetworksPage) {
                WifiConfiguration wifiConfiguration = this.B;
                if (wifiConfiguration == null || !wifiConfiguration.fromWifiNetworkSuggestion) {
                    stringJoiner.add(this.z.getString(R$string.wifi_remembered));
                } else {
                    Context context = this.z;
                    String strF = e0.f(context, e0.p(context, wifiConfiguration));
                    String strB = e0.b(this.z, this.B.creatorName);
                    if (TextUtils.isEmpty(strB)) {
                        strB = this.B.creatorName;
                    }
                    Context context2 = this.z;
                    int i = R$string.available_via_app;
                    Object[] objArr = new Object[1];
                    if (strF == null) {
                        strF = strB;
                    }
                    objArr[0] = strF;
                    stringJoiner.add(context2.getString(i, objArr));
                }
            }
        } else {
            String strP = P();
            if (!TextUtils.isEmpty(strP)) {
                stringJoiner.add(strP);
            }
        }
        String strN = e0.n(this.z, this);
        if (!TextUtils.isEmpty(strN)) {
            stringJoiner.add(strN);
        }
        String strC = e0.c(this.z, this);
        if (!TextUtils.isEmpty(strC)) {
            stringJoiner.add(strC);
        }
        String strI = e0.i(this.z, this);
        if (!TextUtils.isEmpty(strI)) {
            stringJoiner.add(strI);
        }
        if (!z) {
            String strQ = e0.q(this);
            if (!TextUtils.isEmpty(strQ)) {
                stringJoiner.add(strQ);
            }
        }
        return stringJoiner.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String t() {
        return this.y;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration u() {
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean w() {
        PasspointConfiguration passpointConfiguration = this.A;
        if (passpointConfiguration != null) {
            return passpointConfiguration.isAutojoinEnabled();
        }
        WifiConfiguration wifiConfiguration = this.B;
        if (wifiConfiguration != null) {
            return wifiConfiguration.allowAutojoin;
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean x() {
        if (l() == 1) {
            return true;
        }
        WifiConfiguration wifiConfiguration = this.B;
        return wifiConfiguration != null && wifiConfiguration.meteredHint;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean y() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean z() {
        return this.A != null;
    }

    PasspointWifiEntry(@NonNull Context context, @NonNull Handler handler, @NonNull WifiConfiguration wifiConfiguration, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        super(handler, wifiManager, wifiNetworkScoreCache, z);
        this.t = new Object();
        this.u = new ArrayList();
        this.v = new ArrayList();
        this.C = 3;
        this.F = 0;
        androidx.core.d.e.c(wifiConfiguration, "Cannot construct with null PasspointConfiguration!");
        if (wifiConfiguration.isPasspoint()) {
            this.z = context;
            this.B = wifiConfiguration;
            this.w = S(wifiConfiguration.getKey());
            this.x = wifiConfiguration.FQDN;
            this.y = this.B.providerFriendlyName;
            return;
        }
        throw new IllegalArgumentException("Given WifiConfiguration is not for Passpoint!");
    }
}
