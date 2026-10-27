package defpackage;

import J.N;
import android.text.TextUtils;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: QueryTileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r82 {
    public static Boolean a;
    public static Boolean b;

    public static int a(int i, String str) {
        sk skVar = bq.a;
        String strMMltG$kc = N.MMltG$kc("QueryTilesSegmentation", str);
        if (TextUtils.isEmpty(strMMltG$kc)) {
            return i;
        }
        try {
            return Integer.parseInt(strMMltG$kc);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static void b(String str) {
        sk skVar = bq.a;
        if (N.M09VlOh_("QueryTilesSegmentation")) {
            if (SharedPreferencesManager.getInstance().e(-1L, "Chrome.Querytiles.NextDecisionTime") < (((long) a(7, "num_days_mv_clicks_below_threshold")) * 86400000) + System.currentTimeMillis()) {
                SharedPreferencesManager.getInstance().c(str);
            }
        }
    }

    public static boolean c() {
        Boolean bool = a;
        if (bool != null) {
            return bool.booleanValue();
        }
        sk skVar = bq.a;
        boolean z = false;
        if (((N.M09VlOh_("QueryTiles") && N.M09VlOh_("QueryTilesInNTP")) || N.M0cc_7F6()) && e()) {
            z = true;
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        a = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static boolean d() {
        Boolean bool = b;
        if (bool != null) {
            return bool.booleanValue();
        }
        sk skVar = bq.a;
        boolean z = false;
        if ((N.M09VlOh_("QueryTiles") || N.M0cc_7F6()) && e()) {
            z = true;
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        b = boolValueOf;
        return boolValueOf.booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean e() {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r82.e():boolean");
    }

    public static void f(boolean z) {
        long jA = (((long) (z ? a(28, "num_days_keep_showing_query_tiles") : a(7, "num_days_mv_clicks_below_threshold"))) * 86400000) + System.currentTimeMillis();
        SharedPreferencesManager.getInstance().l("Chrome.Querytiles.ShowOnNTP", z);
        SharedPreferencesManager.getInstance().n(jA, "Chrome.Querytiles.NextDecisionTime");
        SharedPreferencesManager.getInstance().removeKey("Chrome.Querytiles.RecentMvClicks");
        SharedPreferencesManager.getInstance().removeKey("Chrome.Querytiles.RecentQueryTileClicks");
    }
}
