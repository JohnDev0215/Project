package defpackage;

import J.N;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: PriceTrackingUtilities.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k42 {
    public static final SharedPreferencesManager a = SharedPreferencesManager.getInstance();

    public static boolean a() {
        boolean zM6bsIDpc;
        if (!z7.P()) {
            return false;
        }
        if (a8.P()) {
            sk skVar = bq.a;
            zM6bsIDpc = N.M6bsIDpc("CommercePriceTracking", "implicit_subscriptions_enabled", false);
        } else {
            zM6bsIDpc = false;
        }
        if (zM6bsIDpc) {
            return a.readBoolean("Chrome.PriceTracking.PriceAlerts", z7.R());
        }
        return false;
    }

    public static boolean b() {
        if (z7.Q()) {
            if (a.readBoolean("Chrome.PriceTracking.PriceWelcome", z7.R())) {
                return true;
            }
        }
        return false;
    }

    public static boolean c() {
        if (z7.Q()) {
            if (a.readBoolean("Chrome.PriceTracking.TrackPricesOnTabs", z7.R())) {
                return true;
            }
        }
        return false;
    }
}
