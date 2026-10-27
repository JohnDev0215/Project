package com.android.wifitrackerlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.android.internal.annotations.VisibleForTesting;
import com.android.wifitrackerlib.WifiEntry;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@VisibleForTesting
public class StandardWifiEntry extends WifiEntry {
    private boolean A;

    @Nullable
    private String B;
    private boolean C;
    private final Object t;

    @NonNull
    @GuardedBy("mLock")
    private final List<ScanResult> u;

    @NonNull
    private final String v;

    @NonNull
    private final String w;

    @NonNull
    private final Context x;
    private final int y;

    @Nullable
    private WifiConfiguration z;

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface EapType {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface PskType {
    }

    StandardWifiEntry(@NonNull Context context, @NonNull Handler handler, @NonNull String str, @NonNull List<ScanResult> list, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        this(context, handler, str, wifiManager, wifiNetworkScoreCache, z);
        androidx.core.d.e.c(list, "Cannot construct with null ScanResult list!");
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Cannot construct with empty ScanResult list!");
        }
        g0(list);
        f0();
    }

    private String P() {
        if (h() == 2) {
            WifiInfo wifiInfo = this.h;
            String strF = null;
            String requestingPackageName = wifiInfo != null ? wifiInfo.getRequestingPackageName() : null;
            if (!TextUtils.isEmpty(requestingPackageName)) {
                WifiConfiguration wifiConfiguration = this.z;
                if (wifiConfiguration != null) {
                    Context context = this.x;
                    strF = e0.f(context, e0.p(context, wifiConfiguration));
                }
                String strB = e0.b(this.x, requestingPackageName);
                if (!TextUtils.isEmpty(strB)) {
                    requestingPackageName = strB;
                }
                Context context2 = this.x;
                int i = R$string.connected_via_app;
                Object[] objArr = new Object[1];
                if (strF == null) {
                    strF = requestingPackageName;
                }
                objArr[0] = strF;
                return context2.getString(i, objArr);
            }
            if (!y() && !A()) {
                return !TextUtils.isEmpty(this.B) ? String.format(this.x.getString(R$string.connected_via_network_scorer), this.B) : this.x.getString(R$string.connected_via_network_scorer_default);
            }
            if (this.s) {
                return this.x.getString(R$string.wifi_connected_low_quality);
            }
            String strG = e0.g(this.x, this.j);
            if (!TextUtils.isEmpty(strG)) {
                return strG;
            }
        }
        return e0.j(this.x, this.i);
    }

    private String Q(final int i, final int i2) {
        List list;
        synchronized (this.t) {
            list = (List) this.u.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.q
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return StandardWifiEntry.U(i, i2, (ScanResult) obj);
                }
            }).sorted(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.p
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return StandardWifiEntry.V((ScanResult) obj);
                }
            })).collect(Collectors.toList());
        }
        int size = list.size();
        if (size == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(size);
        sb.append(")");
        if (size > 4) {
            int asInt = list.stream().mapToInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.r
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((ScanResult) obj).level;
                }
            }).max().getAsInt();
            sb.append("max=");
            sb.append(asInt);
            sb.append(",");
        }
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        list.forEach(new Consumer() { // from class: com.android.wifitrackerlib.o
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1469c.Y(sb, jElapsedRealtime, (ScanResult) obj);
            }
        });
        return sb.toString();
    }

    private String R(ScanResult scanResult, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n{");
        sb.append(scanResult.BSSID);
        WifiInfo wifiInfo = this.h;
        if (wifiInfo != null && scanResult.BSSID.equals(wifiInfo.getBSSID())) {
            sb.append("*");
        }
        sb.append("=");
        sb.append(scanResult.frequency);
        sb.append(",");
        sb.append(scanResult.level);
        int i = ((int) (j - (scanResult.timestamp / 1000))) / 1000;
        sb.append(",");
        sb.append(i);
        sb.append("s");
        sb.append("}");
        return sb.toString();
    }

    static /* synthetic */ boolean U(int i, int i2, ScanResult scanResult) {
        int i3 = scanResult.frequency;
        return i3 >= i && i3 <= i2;
    }

    static /* synthetic */ int V(ScanResult scanResult) {
        return scanResult.level * (-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y(StringBuilder sb, long j, ScanResult scanResult) {
        sb.append(R(scanResult, j));
    }

    @NonNull
    static String b0(@NonNull String str, int i) {
        return "StandardWifiEntry:" + str + "," + i;
    }

    private void d0(ScanResult scanResult) {
        if (scanResult.capabilities.contains("RSN-EAP")) {
            return;
        }
        scanResult.capabilities.contains("WPA-EAP");
    }

    private void e0(ScanResult scanResult) {
        if (this.y != 2) {
            return;
        }
        scanResult.capabilities.contains("WPA-PSK");
        scanResult.capabilities.contains("RSN-PSK");
    }

    private void f0() {
        NetworkScorerAppData activeScorer = ((NetworkScoreManager) this.x.getSystemService("network_score")).getActiveScorer();
        if (activeScorer != null) {
            this.B = activeScorer.getRecommendationServiceLabel();
        }
    }

    @NonNull
    static String h0(@NonNull WifiConfiguration wifiConfiguration) {
        androidx.core.d.e.c(wifiConfiguration, "Cannot create key with null config!");
        androidx.core.d.e.c(wifiConfiguration.SSID, "Cannot create key with null SSID in config!");
        return "StandardWifiEntry:" + WifiInfo.sanitizeSsid(wifiConfiguration.SSID) + "," + e0.l(wifiConfiguration);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean A() {
        WifiConfiguration wifiConfiguration = this.z;
        return wifiConfiguration != null && wifiConfiguration.fromWifiNetworkSuggestion;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    @WorkerThread
    void O(@Nullable NetworkCapabilities networkCapabilities) {
        super.O(networkCapabilities);
        if (d() && this.C) {
            this.C = false;
            a0(null);
        }
    }

    public String S() {
        return this.w;
    }

    @WorkerThread
    boolean T() {
        return this.A;
    }

    @WorkerThread
    void Z(boolean z) {
        this.A = z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean a() {
        return y() || A();
    }

    public void a0(@Nullable WifiEntry.SignInCallback signInCallback) {
        if (d()) {
            ((ConnectivityManager) this.x.getSystemService("connectivity")).startCaptivePortalApp(this.f1444c.getCurrentNetwork());
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean b() {
        return u() != null;
    }

    @WorkerThread
    void c0(@Nullable WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            if (!TextUtils.equals(this.w, WifiInfo.sanitizeSsid(wifiConfiguration.SSID))) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + this.w + ", Actual: " + WifiInfo.sanitizeSsid(wifiConfiguration.SSID) + ", Config: " + wifiConfiguration);
            }
            if (this.y != e0.l(wifiConfiguration)) {
                throw new IllegalArgumentException("Attempted to update with wrong security! Expected: " + this.y + ", Actual: " + e0.l(wifiConfiguration) + ", Config: " + wifiConfiguration);
            }
        }
        this.z = wifiConfiguration;
        H();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean d() {
        NetworkCapabilities networkCapabilities = this.j;
        return networkCapabilities != null && networkCapabilities.hasCapability(17);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    @WorkerThread
    protected boolean f(@NonNull WifiInfo wifiInfo, @NonNull NetworkInfo networkInfo) {
        WifiConfiguration wifiConfiguration;
        return (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp() || (wifiConfiguration = this.z) == null || wifiConfiguration.networkId != wifiInfo.getNetworkId()) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiEntry.b g() {
        return this.k;
    }

    @WorkerThread
    void g0(@Nullable List<ScanResult> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (ScanResult scanResult : list) {
            if (!TextUtils.equals(scanResult.SSID, this.w)) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + this.w + ", Actual: " + scanResult.SSID + ", ScanResult: " + scanResult);
            }
        }
        synchronized (this.t) {
            this.u.clear();
            this.u.addAll(list);
        }
        ScanResult scanResultE = e0.e(list);
        if (scanResultE != null) {
            d0(scanResultE);
            e0(scanResultE);
        }
        if (h() == 0) {
            this.f = scanResultE != null ? this.f1444c.calculateSignalLevel(scanResultE.level) : -1;
            synchronized (this.t) {
                this.g = e0.d(this.l, this.u);
            }
        }
        H();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String j() {
        return this.v;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int l() {
        if (u() == null) {
            return 0;
        }
        int i = u().meteredOverride;
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 2 : 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String n() {
        return e0.k(u());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String o() {
        synchronized (this.t) {
            if (this.u.size() == 0) {
                return "";
            }
            return "[" + Q(2400, 2500) + ";" + Q(4900, 5900) + ";" + Q(5925, 7125) + "]";
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int p() {
        return this.y;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String s(boolean z) {
        StringJoiner stringJoiner = new StringJoiner(this.x.getString(R$string.summary_separator));
        if (!z && this.mForSavedNetworksPage && y()) {
            String strB = e0.b(this.x, this.z.creatorName);
            if (!TextUtils.isEmpty(strB)) {
                stringJoiner.add(this.x.getString(R$string.saved_network, strB));
            }
        }
        if (h() == 0) {
            String strH = e0.h(this.x, this);
            if (!TextUtils.isEmpty(strH)) {
                stringJoiner.add(strH);
            } else if (z) {
                stringJoiner.add(this.x.getString(R$string.wifi_disconnected));
            } else if (!this.mForSavedNetworksPage) {
                if (A()) {
                    Context context = this.x;
                    String strF = e0.f(context, e0.p(context, this.z));
                    String strB2 = e0.b(this.x, this.z.creatorName);
                    if (TextUtils.isEmpty(strB2)) {
                        strB2 = this.z.creatorName;
                    }
                    Context context2 = this.x;
                    int i = R$string.available_via_app;
                    Object[] objArr = new Object[1];
                    if (strF == null) {
                        strF = strB2;
                    }
                    objArr[0] = strF;
                    stringJoiner.add(context2.getString(i, objArr));
                } else if (y()) {
                    stringJoiner.add(this.x.getString(R$string.wifi_remembered));
                }
            }
        } else {
            String strP = P();
            if (!TextUtils.isEmpty(strP)) {
                stringJoiner.add(strP);
            }
        }
        String strN = e0.n(this.x, this);
        if (!TextUtils.isEmpty(strN)) {
            stringJoiner.add(strN);
        }
        String strC = e0.c(this.x, this);
        if (!TextUtils.isEmpty(strC)) {
            stringJoiner.add(strC);
        }
        String strI = e0.i(this.x, this);
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
        return this.w;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration u() {
        if (y()) {
            return this.z;
        }
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean w() {
        WifiConfiguration wifiConfiguration = this.z;
        if (wifiConfiguration == null) {
            return false;
        }
        return wifiConfiguration.allowAutojoin;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean x() {
        if (l() == 1) {
            return true;
        }
        WifiConfiguration wifiConfiguration = this.z;
        return wifiConfiguration != null && wifiConfiguration.meteredHint;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean y() {
        WifiConfiguration wifiConfiguration = this.z;
        return (wifiConfiguration == null || wifiConfiguration.isEphemeral()) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean z() {
        return false;
    }

    StandardWifiEntry(@NonNull Context context, @NonNull Handler handler, @NonNull String str, @NonNull WifiConfiguration wifiConfiguration, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        this(context, handler, str, wifiManager, wifiNetworkScoreCache, z);
        androidx.core.d.e.c(wifiConfiguration, "Cannot construct with null config!");
        androidx.core.d.e.c(wifiConfiguration.SSID, "Supplied config must have an SSID!");
        this.z = wifiConfiguration;
        f0();
    }

    StandardWifiEntry(@NonNull Context context, @NonNull Handler handler, @NonNull String str, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        super(handler, wifiManager, wifiNetworkScoreCache, z);
        this.t = new Object();
        this.u = new ArrayList();
        this.A = false;
        this.C = false;
        this.x = context;
        this.v = str;
        try {
            int iIndexOf = str.indexOf(":");
            int iLastIndexOf = str.lastIndexOf(",");
            this.w = str.substring(iIndexOf + 1, iLastIndexOf);
            this.y = Integer.valueOf(str.substring(iLastIndexOf + 1)).intValue();
            f0();
        } catch (NumberFormatException | StringIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("Malformed key: " + str);
        }
    }
}
