package com.android.settingslib.wifi;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.hotspot2.ProvisioningCallback;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.CollectionUtils;
import com.android.settingslib.R$string;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class AccessPoint implements Comparable<AccessPoint> {
    private String A;
    private boolean B;
    private boolean C;
    private boolean D;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1387c;
    private final Object d;

    @GuardedBy("mLock")
    private final ArraySet<ScanResult> e;

    @GuardedBy("mLock")
    private final ArraySet<ScanResult> f;
    private final Map<String, TimestampedScoredNetwork> g;
    private final Context h;
    private WifiManager i;
    private WifiManager.ActionListener j;
    private String k;
    private String l;
    private int m;
    private int n;
    private int o;
    private WifiConfiguration p;
    private int q;
    private WifiInfo r;
    private NetworkInfo s;
    a t;
    private int u;
    private boolean v;
    private String w;
    private String x;
    private OsuProvider y;
    private String z;

    /* JADX INFO: compiled from: caradapter-bug */
    @VisibleForTesting
    class AccessPointProvisioningCallback extends ProvisioningCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ AccessPoint f1388a;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            AccessPoint accessPoint = this.f1388a;
            a aVar = accessPoint.t;
            if (aVar != null) {
                aVar.a(accessPoint);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d() {
            AccessPoint accessPoint = this.f1388a;
            a aVar = accessPoint.t;
            if (aVar != null) {
                aVar.a(accessPoint);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f() {
            AccessPoint accessPoint = this.f1388a;
            a aVar = accessPoint.t;
            if (aVar != null) {
                aVar.a(accessPoint);
            }
        }

        public void onProvisioningComplete() {
            this.f1388a.B = true;
            this.f1388a.A = null;
            this.f1388a.z = null;
            com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1402c.b();
                }
            });
            WifiManager wifiManagerD = this.f1388a.D();
            PasspointConfiguration passpointConfiguration = (PasspointConfiguration) wifiManagerD.getMatchingPasspointConfigsForOsuProviders(Collections.singleton(this.f1388a.y)).get(this.f1388a.y);
            if (passpointConfiguration == null) {
                Log.e("SettingsLib.AccessPoint", "Missing PasspointConfiguration for newly provisioned network!");
                if (this.f1388a.j != null) {
                    this.f1388a.j.onFailure(0);
                    return;
                }
                return;
            }
            String uniqueId = passpointConfiguration.getUniqueId();
            for (Pair pair : wifiManagerD.getAllMatchingWifiConfigs(wifiManagerD.getScanResults())) {
                WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
                if (TextUtils.equals(wifiConfiguration.getKey(), uniqueId)) {
                    wifiManagerD.connect(new AccessPoint(this.f1388a.h, wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1)).m(), this.f1388a.j);
                    return;
                }
            }
            if (this.f1388a.j != null) {
                this.f1388a.j.onFailure(0);
            }
        }

        public void onProvisioningFailure(int i) {
            if (TextUtils.equals(this.f1388a.z, this.f1388a.h.getString(R$string.osu_completing_sign_up))) {
                AccessPoint accessPoint = this.f1388a;
                accessPoint.A = accessPoint.h.getString(R$string.osu_sign_up_failed);
            } else {
                AccessPoint accessPoint2 = this.f1388a;
                accessPoint2.A = accessPoint2.h.getString(R$string.osu_connect_failed);
            }
            this.f1388a.z = null;
            this.f1388a.B = false;
            com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1401c.d();
                }
            });
        }

        public void onProvisioningStatus(int i) {
            String string;
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    string = String.format(this.f1388a.h.getString(R$string.osu_opening_provider), this.f1388a.y.getFriendlyName());
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    string = this.f1388a.h.getString(R$string.osu_completing_sign_up);
                    break;
                default:
                    string = null;
                    break;
            }
            boolean zEquals = true ^ TextUtils.equals(this.f1388a.z, string);
            this.f1388a.z = string;
            this.f1388a.A = null;
            this.f1388a.B = false;
            if (zEquals) {
                com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1403c.f();
                    }
                });
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface PasspointConfigurationVersion {
        public static final int INVALID = 0;
        public static final int NO_OSU_PROVISIONED = 1;
        public static final int OSU_PROVISIONED = 2;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Speed {
        public static final int FAST = 20;
        public static final int MODERATE = 10;
        public static final int NONE = 0;
        public static final int SLOW = 5;
        public static final int VERY_FAST = 30;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(AccessPoint accessPoint);

        void b(AccessPoint accessPoint);
    }

    static {
        new AtomicInteger(0);
    }

    public AccessPoint(Context context, Bundle bundle) {
        this.d = new Object();
        this.e = new ArraySet<>();
        this.f = new ArraySet<>();
        this.g = new HashMap();
        this.n = -1;
        this.o = 0;
        this.q = Integer.MIN_VALUE;
        this.u = 0;
        this.v = false;
        this.C = false;
        this.D = false;
        this.h = context;
        if (bundle.containsKey("key_config")) {
            this.p = (WifiConfiguration) bundle.getParcelable("key_config");
        }
        WifiConfiguration wifiConfiguration = this.p;
        if (wifiConfiguration != null) {
            loadConfig(wifiConfiguration);
        }
        if (bundle.containsKey("key_ssid")) {
            this.k = bundle.getString("key_ssid");
        }
        if (bundle.containsKey("key_security")) {
            this.m = bundle.getInt("key_security");
        }
        if (bundle.containsKey("key_speed")) {
            this.u = bundle.getInt("key_speed");
        }
        if (bundle.containsKey("key_psktype")) {
            this.o = bundle.getInt("key_psktype");
        }
        if (bundle.containsKey("eap_psktype")) {
            bundle.getInt("eap_psktype");
        }
        this.r = (WifiInfo) bundle.getParcelable("key_wifiinfo");
        if (bundle.containsKey("key_networkinfo")) {
            this.s = (NetworkInfo) bundle.getParcelable("key_networkinfo");
        }
        if (bundle.containsKey("key_scanresults")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("key_scanresults");
            this.e.clear();
            for (Parcelable parcelable : parcelableArray) {
                this.e.add((ScanResult) parcelable);
            }
        }
        if (bundle.containsKey("key_scorednetworkcache")) {
            for (TimestampedScoredNetwork timestampedScoredNetwork : bundle.getParcelableArrayList("key_scorednetworkcache")) {
                this.g.put(timestampedScoredNetwork.a().networkKey.wifiKey.bssid, timestampedScoredNetwork);
            }
        }
        if (bundle.containsKey("key_passpoint_unique_id")) {
            this.w = bundle.getString("key_passpoint_unique_id");
        }
        if (bundle.containsKey("key_fqdn")) {
            bundle.getString("key_fqdn");
        }
        if (bundle.containsKey("key_provider_friendly_name")) {
            this.x = bundle.getString("key_provider_friendly_name");
        }
        if (bundle.containsKey("key_subscription_expiration_time_in_millis")) {
            bundle.getLong("key_subscription_expiration_time_in_millis");
        }
        if (bundle.containsKey("key_passpoint_configuration_version")) {
            bundle.getInt("key_passpoint_configuration_version");
        }
        if (bundle.containsKey("key_is_psk_sae_transition_mode")) {
            this.C = bundle.getBoolean("key_is_psk_sae_transition_mode");
        }
        if (bundle.containsKey("key_is_owe_transition_mode")) {
            this.D = bundle.getBoolean("key_is_owe_transition_mode");
        }
        n0(this.p, this.r, this.s);
        q0();
        p0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiManager D() {
        if (this.i == null) {
            this.i = (WifiManager) this.h.getSystemService("wifi");
        }
        return this.i;
    }

    private boolean H(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo) {
        if (wifiInfo.isOsuAp() || this.z != null) {
            return wifiInfo.isOsuAp() && this.z != null;
        }
        if (wifiInfo.isPasspointAp() || L()) {
            return wifiInfo.isPasspointAp() && L() && TextUtils.equals(wifiInfo.getPasspointFqdn(), this.p.FQDN) && TextUtils.equals(wifiInfo.getPasspointProviderFriendlyName(), this.p.providerFriendlyName);
        }
        int i = this.n;
        return i != -1 ? i == wifiInfo.getNetworkId() : wifiConfiguration != null ? f0(wifiConfiguration, wifiInfo) : TextUtils.equals(g0(wifiInfo.getSSID()), this.k);
    }

    private static boolean K(ScanResult scanResult) {
        return scanResult.capabilities.contains("OWE_TRANSITION");
    }

    private static boolean N(ScanResult scanResult) {
        return scanResult.capabilities.contains("PSK") && scanResult.capabilities.contains("SAE");
    }

    private boolean P(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        if (TextUtils.equals(this.k, scanResult.SSID)) {
            return true;
        }
        String str = scanResult.BSSID;
        return str != null && TextUtils.equals(this.l, str);
    }

    private boolean Q(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return false;
        }
        if (TextUtils.equals(this.k, g0(wifiInfo.getSSID()))) {
            return true;
        }
        return wifiInfo.getBSSID() != null && TextUtils.equals(this.l, wifiInfo.getBSSID());
    }

    private static boolean S() {
        return WifiTracker.y || Log.isLoggable("SettingsLib.AccessPoint", 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U() {
        a aVar = this.t;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W() {
        a aVar = this.t;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y() {
        a aVar = this.t;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a0() {
        a aVar = this.t;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c0() {
        a aVar = this.t;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    static /* synthetic */ void d0(long j, Iterator it, TimestampedScoredNetwork timestampedScoredNetwork) {
        if (timestampedScoredNetwork.b() < j) {
            it.remove();
        }
    }

    private boolean f0(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo) {
        if (wifiConfiguration == null || wifiInfo == null) {
            return false;
        }
        if (wifiConfiguration.isPasspoint() || Q(wifiInfo)) {
            return e0(wifiConfiguration);
        }
        return false;
    }

    static String g0(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    private static int h0(int i) {
        if (i < 5) {
            return 0;
        }
        if (i < 7) {
            return 5;
        }
        if (i < 15) {
            return 10;
        }
        return i < 25 ? 20 : 30;
    }

    public static String i0(int i, int i2) {
        return i == 1 ? "WEP" : i == 2 ? i2 == 1 ? "WPA" : i2 == 2 ? "WPA2" : i2 == 3 ? "WPA_WPA2" : "PSK" : i == 3 ? "EAP" : i == 5 ? "SAE" : i == 6 ? "SUITE_B" : i == 4 ? "OWE" : "NONE";
    }

    public static String j(String str) {
        return "\"" + str + "\"";
    }

    private int k() {
        if (this.g.isEmpty()) {
            return 0;
        }
        if (Log.isLoggable("SettingsLib.AccessPoint", 3)) {
            Log.d("SettingsLib.AccessPoint", String.format("Generating fallbackspeed for %s using cache: %s", B(), this.g));
        }
        Iterator<TimestampedScoredNetwork> it = this.g.values().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            int iCalculateBadge = it.next().a().calculateBadge(this.q);
            if (iCalculateBadge != 0) {
                i++;
                i2 += iCalculateBadge;
            }
        }
        int i3 = i == 0 ? 0 : i2 / i;
        if (S()) {
            Log.i("SettingsLib.AccessPoint", String.format("%s generated fallback speed is: %d", B(), Integer.valueOf(i3)));
        }
        return h0(i3);
    }

    private static int o(ScanResult scanResult) {
        if (scanResult.capabilities.contains("RSN-EAP")) {
            return 2;
        }
        return scanResult.capabilities.contains("WPA-EAP") ? 1 : 0;
    }

    private void p0() {
        int i;
        int i2;
        if (E()) {
            return;
        }
        ScanResult scanResult = null;
        synchronized (this.d) {
            i = Integer.MIN_VALUE;
            for (ScanResult scanResult2 : this.e) {
                if (scanResult2.level > i) {
                    i = scanResult2.level;
                    scanResult = scanResult2;
                }
            }
        }
        if (i == Integer.MIN_VALUE || (i2 = this.q) == Integer.MIN_VALUE) {
            this.q = i;
        } else {
            this.q = (i2 + i) / 2;
        }
        if (scanResult != null) {
            this.k = scanResult.SSID;
            this.l = scanResult.BSSID;
            int iY = y(this.h, scanResult);
            this.m = iY;
            if (iY == 2 || iY == 5) {
                this.o = w(scanResult);
            }
            if (this.m == 3) {
                o(scanResult);
            }
            this.C = N(scanResult);
            this.D = K(scanResult);
        }
        if (L()) {
            this.p.SSID = j(this.k);
        }
    }

    public static String q(Context context, ScanResult scanResult) {
        return u(scanResult.SSID, scanResult.BSSID, y(context, scanResult));
    }

    private void q0() {
        if (L()) {
            this.f1387c = r(this.p);
            return;
        }
        if (M()) {
            this.f1387c = t(this.w);
        } else if (J()) {
            this.f1387c = s(this.y);
        } else {
            this.f1387c = u(B(), l(), x());
        }
    }

    public static String r(WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.isPasspoint() ? t(wifiConfiguration.getKey()) : u(g0(wifiConfiguration.SSID), wifiConfiguration.BSSID, z(wifiConfiguration));
    }

    private boolean r0(WifiNetworkScoreCache wifiNetworkScoreCache) {
        WifiInfo wifiInfo;
        boolean z = this.v;
        this.v = false;
        if (!E() || (wifiInfo = this.r) == null) {
            synchronized (this.d) {
                Iterator<ScanResult> it = this.e.iterator();
                while (it.hasNext()) {
                    ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(it.next());
                    if (scoredNetwork != null) {
                        this.v = scoredNetwork.meteredHint | this.v;
                    }
                }
            }
        } else {
            ScoredNetwork scoredNetwork2 = wifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(wifiInfo));
            if (scoredNetwork2 != null) {
                this.v = scoredNetwork2.meteredHint | this.v;
            }
        }
        return z != this.v;
    }

    public static String s(OsuProvider osuProvider) {
        return "OSU:" + osuProvider.getFriendlyName() + ',' + osuProvider.getServerUri();
    }

    private boolean s0(WifiNetworkScoreCache wifiNetworkScoreCache, long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (this.d) {
            for (ScanResult scanResult : this.e) {
                ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(scanResult);
                if (scoredNetwork != null) {
                    TimestampedScoredNetwork timestampedScoredNetwork = this.g.get(scanResult.BSSID);
                    if (timestampedScoredNetwork == null) {
                        this.g.put(scanResult.BSSID, new TimestampedScoredNetwork(scoredNetwork, jElapsedRealtime));
                    } else {
                        timestampedScoredNetwork.c(scoredNetwork, jElapsedRealtime);
                    }
                }
            }
        }
        final long j2 = jElapsedRealtime - j;
        final Iterator<TimestampedScoredNetwork> it = this.g.values().iterator();
        it.forEachRemaining(new Consumer() { // from class: com.android.settingslib.wifi.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AccessPoint.d0(j2, it, (TimestampedScoredNetwork) obj);
            }
        });
        return t0();
    }

    public static String t(String str) {
        return "PASSPOINT:" + str;
    }

    private boolean t0() {
        int i = this.u;
        int iK = k();
        this.u = iK;
        boolean z = i != iK;
        if (S() && z) {
            Log.i("SettingsLib.AccessPoint", String.format("%s: Set speed to %d", this.k, Integer.valueOf(this.u)));
        }
        return z;
    }

    private static String u(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("AP:");
        if (TextUtils.isEmpty(str)) {
            sb.append(str2);
        } else {
            sb.append(str);
        }
        sb.append(',');
        sb.append(i);
        return sb.toString();
    }

    private static int w(ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WPA-PSK");
        boolean zContains2 = scanResult.capabilities.contains("RSN-PSK");
        boolean zContains3 = scanResult.capabilities.contains("RSN-SAE");
        if (zContains2 && zContains) {
            return 3;
        }
        if (zContains2) {
            return 2;
        }
        if (zContains) {
            return 1;
        }
        if (zContains3) {
            return 0;
        }
        Log.w("SettingsLib.AccessPoint", "Received abnormal flag string: " + scanResult.capabilities);
        return 0;
    }

    private static int y(Context context, ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WEP");
        boolean zContains2 = scanResult.capabilities.contains("SAE");
        boolean zContains3 = scanResult.capabilities.contains("PSK");
        boolean zContains4 = scanResult.capabilities.contains("EAP_SUITE_B_192");
        boolean zContains5 = scanResult.capabilities.contains("EAP");
        boolean zContains6 = scanResult.capabilities.contains("OWE");
        boolean zContains7 = scanResult.capabilities.contains("OWE_TRANSITION");
        if (zContains2 && zContains3) {
            return ((WifiManager) context.getSystemService("wifi")).isWpa3SaeSupported() ? 5 : 2;
        }
        if (zContains7) {
            return ((WifiManager) context.getSystemService("wifi")).isEnhancedOpenSupported() ? 4 : 0;
        }
        if (zContains) {
            return 1;
        }
        if (zContains2) {
            return 5;
        }
        if (zContains3) {
            return 2;
        }
        if (zContains4) {
            return 6;
        }
        if (zContains5) {
            return 3;
        }
        return zContains6 ? 4 : 0;
    }

    static int z(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return 4;
        }
        int i = wifiConfiguration.wepTxKeyIndex;
        if (i >= 0) {
            String[] strArr = wifiConfiguration.wepKeys;
            if (i < strArr.length && strArr[i] != null) {
                return 1;
            }
        }
        return 0;
    }

    int A() {
        return this.u;
    }

    public String B() {
        return this.k;
    }

    public String C() {
        return L() ? this.p.providerFriendlyName : M() ? this.x : J() ? this.y.getFriendlyName() : B();
    }

    public boolean E() {
        NetworkInfo networkInfo = this.s;
        return (networkInfo == null || (this.n == -1 && networkInfo.getState() == NetworkInfo.State.DISCONNECTED)) ? false : true;
    }

    public boolean F() {
        return v() != -1 && n() == null;
    }

    public boolean G() {
        NetworkInfo networkInfo;
        WifiInfo wifiInfo = this.r;
        return (wifiInfo == null || !wifiInfo.isEphemeral() || (networkInfo = this.s) == null || networkInfo.getState() == NetworkInfo.State.DISCONNECTED) ? false : true;
    }

    public boolean I() {
        return this.v || WifiConfiguration.isMetered(this.p, this.r);
    }

    public boolean J() {
        return this.y != null;
    }

    public boolean L() {
        WifiConfiguration wifiConfiguration = this.p;
        return wifiConfiguration != null && wifiConfiguration.isPasspoint();
    }

    public boolean M() {
        return this.w != null && this.p == null;
    }

    public boolean O() {
        return this.q != Integer.MIN_VALUE;
    }

    public boolean R() {
        return this.p != null;
    }

    public boolean e0(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint()) {
            return L() && wifiConfiguration.getKey().equals(this.p.getKey());
        }
        if (!this.k.equals(g0(wifiConfiguration.SSID))) {
            return false;
        }
        WifiConfiguration wifiConfiguration2 = this.p;
        if (wifiConfiguration2 != null && wifiConfiguration2.shared != wifiConfiguration.shared) {
            return false;
        }
        int iZ = z(wifiConfiguration);
        if (this.C && ((iZ == 5 && D().isWpa3SaeSupported()) || iZ == 2)) {
            return true;
        }
        return (this.D && ((iZ == 4 && D().isEnhancedOpenSupported()) || iZ == 0)) || this.m == z(wifiConfiguration);
    }

    public boolean equals(Object obj) {
        return (obj instanceof AccessPoint) && compareTo((AccessPoint) obj) == 0;
    }

    public int hashCode() {
        WifiInfo wifiInfo = this.r;
        return (wifiInfo != null ? 0 + (wifiInfo.hashCode() * 13) : 0) + (this.q * 19) + (this.n * 23) + (this.k.hashCode() * 29);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull AccessPoint accessPoint) {
        if (E() && !accessPoint.E()) {
            return -1;
        }
        if (!E() && accessPoint.E()) {
            return 1;
        }
        if (O() && !accessPoint.O()) {
            return -1;
        }
        if (!O() && accessPoint.O()) {
            return 1;
        }
        if (R() && !accessPoint.R()) {
            return -1;
        }
        if (!R() && accessPoint.R()) {
            return 1;
        }
        if (A() != accessPoint.A()) {
            return accessPoint.A() - A();
        }
        WifiManager wifiManagerD = D();
        int iCalculateSignalLevel = wifiManagerD.calculateSignalLevel(accessPoint.q) - wifiManagerD.calculateSignalLevel(this.q);
        if (iCalculateSignalLevel != 0) {
            return iCalculateSignalLevel;
        }
        int iCompareToIgnoreCase = C().compareToIgnoreCase(accessPoint.C());
        return iCompareToIgnoreCase != 0 ? iCompareToIgnoreCase : B().compareTo(accessPoint.B());
    }

    void j0(Collection<ScanResult> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            Log.d("SettingsLib.AccessPoint", "Cannot set scan results to empty list");
            return;
        }
        if (this.f1387c != null && !L() && !J()) {
            for (ScanResult scanResult : collection) {
                if (!matches(scanResult)) {
                    Log.d("SettingsLib.AccessPoint", String.format("ScanResult %s\nkey of %s did not match current AP key %s", scanResult, q(this.h, scanResult), this.f1387c));
                    return;
                }
            }
        }
        int iV = v();
        synchronized (this.d) {
            this.e.clear();
            this.e.addAll(collection);
        }
        p0();
        int iV2 = v();
        if (iV2 > 0 && iV2 != iV) {
            t0();
            com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1404c.U();
                }
            });
        }
        com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f1400c.W();
            }
        });
    }

    void k0(Collection<ScanResult> collection, Collection<ScanResult> collection2) {
        synchronized (this.d) {
            this.f.clear();
            if (!CollectionUtils.isEmpty(collection)) {
                if (!CollectionUtils.isEmpty(collection2)) {
                    this.f.addAll(collection2);
                }
                j0(collection);
            } else if (!CollectionUtils.isEmpty(collection2)) {
                j0(collection2);
            }
        }
    }

    public String l() {
        return this.l;
    }

    public void l0(Object obj) {
    }

    @VisibleForTesting
    void loadConfig(WifiConfiguration wifiConfiguration) {
        String str = wifiConfiguration.SSID;
        this.k = str == null ? "" : g0(str);
        this.l = wifiConfiguration.BSSID;
        this.m = z(wifiConfiguration);
        this.n = wifiConfiguration.networkId;
        this.p = wifiConfiguration;
    }

    public WifiConfiguration m() {
        return this.p;
    }

    void m0(WifiConfiguration wifiConfiguration) {
        this.p = wifiConfiguration;
        if (wifiConfiguration != null && !L()) {
            this.k = g0(this.p.SSID);
        }
        this.n = wifiConfiguration != null ? wifiConfiguration.networkId : -1;
        com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f1407c.c0();
            }
        });
    }

    @VisibleForTesting
    boolean matches(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        if (L() || J()) {
            throw new IllegalStateException("Should not matches a Passpoint by ScanResult");
        }
        if (!P(scanResult)) {
            return false;
        }
        if (!this.C) {
            int i = this.m;
            if ((i == 5 || i == 2) && N(scanResult)) {
                return true;
            }
        } else if ((scanResult.capabilities.contains("SAE") && D().isWpa3SaeSupported()) || scanResult.capabilities.contains("PSK")) {
            return true;
        }
        if (this.D) {
            int iY = y(this.h, scanResult);
            if ((iY == 4 && D().isEnhancedOpenSupported()) || iY == 0) {
                return true;
            }
        } else {
            int i2 = this.m;
            if ((i2 == 4 || i2 == 0) && K(scanResult)) {
                return true;
            }
        }
        return this.m == y(this.h, scanResult);
    }

    public NetworkInfo.DetailedState n() {
        NetworkInfo networkInfo = this.s;
        if (networkInfo != null) {
            return networkInfo.getDetailedState();
        }
        Log.w("SettingsLib.AccessPoint", "NetworkInfo is null, cannot return detailed state");
        return null;
    }

    public boolean n0(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo, NetworkInfo networkInfo) {
        int iV = v();
        if (wifiInfo != null && H(wifiConfiguration, wifiInfo)) {
            z = this.r == null;
            if (!L() && this.p != wifiConfiguration) {
                m0(wifiConfiguration);
            }
            if (this.q == wifiInfo.getRssi() || wifiInfo.getRssi() == -127) {
                NetworkInfo networkInfo2 = this.s;
                if (networkInfo2 != null && networkInfo != null && networkInfo2.getDetailedState() != networkInfo.getDetailedState()) {
                }
                this.r = wifiInfo;
                this.s = networkInfo;
            } else {
                this.q = wifiInfo.getRssi();
            }
            z = true;
            this.r = wifiInfo;
            this.s = networkInfo;
        } else if (this.r != null) {
            this.r = null;
            this.s = null;
            z = true;
        }
        if (z && this.t != null) {
            com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1408c.Y();
                }
            });
            if (iV != v()) {
                com.android.settingslib.utils.b.f(new Runnable() { // from class: com.android.settingslib.wifi.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f1406c.a0();
                    }
                });
            }
        }
        return z;
    }

    boolean o0(WifiNetworkScoreCache wifiNetworkScoreCache, boolean z, long j) {
        return r0(wifiNetworkScoreCache) || (z ? s0(wifiNetworkScoreCache, j) : false);
    }

    public String p() {
        return this.f1387c;
    }

    @VisibleForTesting
    void setRssi(int i) {
        this.q = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccessPoint(");
        sb.append(this.k);
        if (this.l != null) {
            sb.append(":");
            sb.append(this.l);
        }
        if (R()) {
            sb.append(',');
            sb.append("saved");
        }
        if (E()) {
            sb.append(',');
            sb.append("active");
        }
        if (G()) {
            sb.append(',');
            sb.append("ephemeral");
        }
        if (F()) {
            sb.append(',');
            sb.append("connectable");
        }
        int i = this.m;
        if (i != 0 && i != 4) {
            sb.append(',');
            sb.append(i0(this.m, this.o));
        }
        sb.append(",level=");
        sb.append(v());
        if (this.u != 0) {
            sb.append(",speed=");
            sb.append(this.u);
        }
        sb.append(",metered=");
        sb.append(I());
        if (S()) {
            sb.append(",rssi=");
            sb.append(this.q);
            synchronized (this.d) {
                sb.append(",scan cache size=");
                sb.append(this.e.size() + this.f.size());
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public int v() {
        return D().calculateSignalLevel(this.q);
    }

    public int x() {
        return this.m;
    }

    public AccessPoint(Context context, WifiConfiguration wifiConfiguration) {
        this.d = new Object();
        this.e = new ArraySet<>();
        this.f = new ArraySet<>();
        this.g = new HashMap();
        this.n = -1;
        this.o = 0;
        this.q = Integer.MIN_VALUE;
        this.u = 0;
        this.v = false;
        this.C = false;
        this.D = false;
        this.h = context;
        loadConfig(wifiConfiguration);
        q0();
    }

    public AccessPoint(@NonNull Context context, @NonNull WifiConfiguration wifiConfiguration, Collection<ScanResult> collection, Collection<ScanResult> collection2) {
        this.d = new Object();
        this.e = new ArraySet<>();
        this.f = new ArraySet<>();
        this.g = new HashMap();
        this.n = -1;
        this.o = 0;
        this.q = Integer.MIN_VALUE;
        this.u = 0;
        this.v = false;
        this.C = false;
        this.D = false;
        this.h = context;
        this.n = wifiConfiguration.networkId;
        this.p = wifiConfiguration;
        this.w = wifiConfiguration.getKey();
        String str = wifiConfiguration.FQDN;
        k0(collection, collection2);
        q0();
    }

    public AccessPoint(@NonNull Context context, @NonNull OsuProvider osuProvider, @NonNull Collection<ScanResult> collection) {
        this.d = new Object();
        this.e = new ArraySet<>();
        this.f = new ArraySet<>();
        this.g = new HashMap();
        this.n = -1;
        this.o = 0;
        this.q = Integer.MIN_VALUE;
        this.u = 0;
        this.v = false;
        this.C = false;
        this.D = false;
        this.h = context;
        this.y = osuProvider;
        j0(collection);
        q0();
    }

    AccessPoint(Context context, Collection<ScanResult> collection) {
        this.d = new Object();
        this.e = new ArraySet<>();
        this.f = new ArraySet<>();
        this.g = new HashMap();
        this.n = -1;
        this.o = 0;
        this.q = Integer.MIN_VALUE;
        this.u = 0;
        this.v = false;
        this.C = false;
        this.D = false;
        this.h = context;
        j0(collection);
        q0();
    }
}
