package defpackage;

import J.N;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: FeedFeatures.java */
/* JADX INFO: loaded from: classes.dex */
public final class zk0 {
    public static final long a = TimeUnit.DAYS.toMillis(1);
    public static boolean b = true;

    public static int a() {
        sk skVar = bq.a;
        String strMMltG$kc = N.MMltG$kc("WebFeed", "feed_tab_stickiness_logic");
        if (!"reset_upon_chrome_restart".equals(strMMltG$kc)) {
            if ("indefinitely_persisted".equals(strMMltG$kc)) {
                return b().b("feedv2.last_seen_feed_type");
            }
            e(0);
            return 0;
        }
        if (!b) {
            return b().b("feedv2.last_seen_feed_type");
        }
        b = false;
        e(0);
        return 0;
    }

    public static PrefService b() {
        return oj2.k(Profile.d());
    }

    public static boolean c(Context context) {
        if (DeviceFormFactor.a(context)) {
            sk skVar = bq.a;
            if (N.M09VlOh_("DiscoverFeedMultiColumn")) {
                return true;
            }
        }
        return false;
    }

    public static boolean d() {
        sk skVar = bq.a;
        if (N.M09VlOh_("WebFeed")) {
            z7 z7VarM = z7.m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            if (z7.D(profileD).l().b(1)) {
                Profile profileD2 = Profile.d();
                if (!N.MBL3czGJ(profileD2.b, profileD2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void e(int i) {
        b = false;
        N.MPBZLcVx(b().a, "feedv2.last_seen_feed_type", i);
    }
}
