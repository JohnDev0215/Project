package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.policy.PolicyService;

/* JADX INFO: compiled from: PrivacyPreferencesManagerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v42 implements t42 {

    @SuppressLint({"StaticFieldLeak"})
    public static v42 f;
    public final Context a;
    public final SharedPreferencesManager b;
    public PolicyService c;
    public u42 d;
    public boolean e;

    public v42(Context context) {
        this.a = context;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.b = sharedPreferencesManager;
        this.e = false;
        if (sharedPreferencesManager.contains("metrics_reporting")) {
            sharedPreferencesManager.l("Chrome.Privacy.UsageAndCrashReportingPermittedByUser", sharedPreferencesManager.readBoolean("metrics_reporting", false));
            sharedPreferencesManager.removeKey("metrics_reporting");
        }
    }

    public static v42 g() {
        if (f == null) {
            f = new v42(xz.a);
        }
        return f;
    }

    @Override // defpackage.e10
    public final boolean b() {
        return this.b.readBoolean("Chrome.Privacy.UsageAndCrashReportingPermittedByPolicy", true);
    }

    @Override // defpackage.e10
    public final boolean c() {
        return this.b.readBoolean("Chrome.Privacy.UsageAndCrashReportingPermittedByUser", false);
    }

    @Override // defpackage.e10
    public final boolean d() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return !r1.isActiveNetworkMetered();
    }

    @Override // defpackage.e10
    public final boolean e() {
        return iv.e().g("force-dump-upload");
    }

    @Override // defpackage.e10
    public final boolean f() {
        return this.b.readBoolean("in_metrics_sample", true);
    }

    public final void h() {
        if (this.e) {
            this.b.l("Chrome.Privacy.UsageAndCrashReportingPermittedByPolicy", !N.M9GOn8lm());
        }
    }
}
