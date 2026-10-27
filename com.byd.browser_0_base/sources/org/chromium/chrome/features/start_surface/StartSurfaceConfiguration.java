package org.chromium.chrome.features.start_surface;

import android.text.TextUtils;
import defpackage.bq;
import defpackage.fa1;
import defpackage.k11;
import defpackage.nu2;
import defpackage.oj2;
import defpackage.sh;
import org.chromium.base.SysUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class StartSurfaceConfiguration {
    public static final nu2 a = new nu2("StartSurfaceAndroid", "start_surface_variation", "single");
    public static final sh b = new sh("StartSurfaceAndroid", "hide_switch_when_no_incognito_tabs", true);
    public static final sh c = new sh("StartSurfaceAndroid", "show_last_active_tab_only", true);
    public static final sh d = new sh("StartSurfaceAndroid", "open_ntp_instead_of_start", true);
    public static final sh e = new sh("StartSurfaceAndroid", "open_start_as_homepage", false);
    public static final sh f = new sh("StartSurfaceAndroid", "tab_count_button_on_start_surface", true);
    public static final sh g = new sh("StartSurfaceAndroid", "show_tabs_in_mru_order", false);
    public static final nu2 h = new nu2("StartSurfaceAndroid", "behavioural_targeting", "");
    public static final k11 i = new k11(Integer.MAX_VALUE, "StartSurfaceAndroid", "user_clicks_threshold");
    public static final k11 j = new k11(7, "StartSurfaceAndroid", "num_days_keep_show_start_at_startup");
    public static final k11 k = new k11(7, "StartSurfaceAndroid", "num_days_user_click_below_threshold");
    public static final k11 l = new k11(5, "StartSurfaceAndroid", "signin_promo_NTP_count_limit");
    public static final k11 m = new k11(336, "StartSurfaceAndroid", "signin_promo_NTP_since_first_time_shown_limit_hours");
    public static final k11 n = new k11(672, "StartSurfaceAndroid", "signin_promo_NTP_reset_after_hours");
    public static final sh o = new sh("StartSurfaceAndroid", "is_doodle_supported", false);
    public static final k11 p = new k11(28800, "StartSurfaceReturnTime", "start_surface_return_time_seconds");
    public static final sh q = new sh("StartSurfaceReturnTime", "start_surface_return_time_use_model", false);

    public static boolean a() {
        return bq.R.a() && !SysUtils.isLowEndDevice();
    }

    public static void b(long j2, String str, boolean z) {
        if (j2 < 0) {
            return;
        }
        StringBuilder sb = new StringBuilder("Startup.Android.");
        sb.append(str);
        sb.append(z ? ".Instant" : ".NoInstant");
        fa1.h("StartSurfaceConfig", "Recorded %s = %d ms", sb.toString(), Long.valueOf(j2));
        StringBuilder sb2 = new StringBuilder("Startup.Android.");
        sb2.append(str);
        sb2.append(z ? ".Instant" : ".NoInstant");
        oj2.b0(j2, sb2.toString());
    }

    @CalledByNative
    public static boolean isBehaviouralTargetingEnabled() {
        return !TextUtils.isEmpty(h.c());
    }
}
