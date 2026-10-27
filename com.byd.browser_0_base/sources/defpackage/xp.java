package defpackage;

import android.app.NotificationChannel;
import defpackage.ym;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.chromium.chrome.browser.notifications.R$string;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: ChromeChannelDefinitions.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xp extends ym {
    public static final String[] a = {"sites", "permission_requests", "permission_requests_high"};

    /* JADX INFO: compiled from: ChromeChannelDefinitions.java */
    public static class a {
        public static final Map<String, ym.b> a;

        static {
            HashMap map = new HashMap();
            map.put("general", new ym.b("general", R$string.notification_category_group_general));
            map.put("sites", new ym.b("sites", R$string.notification_category_group_sites));
            a = Collections.unmodifiableMap(map);
        }
    }

    /* JADX INFO: compiled from: ChromeChannelDefinitions.java */
    public static class b {
        public static final Map<String, ym.a> a;
        public static final Set<String> b;

        static {
            HashMap map = new HashMap();
            HashSet hashSet = new HashSet();
            map.put("browser", ym.a.a(R$string.notification_category_browser, 2, "browser"));
            hashSet.add("browser");
            map.put("downloads", ym.a.a(R$string.notification_category_downloads, 2, "downloads"));
            hashSet.add("downloads");
            map.put("incognito", ym.a.a(R$string.notification_category_incognito, 2, "incognito"));
            hashSet.add("incognito");
            map.put("media", ym.a.a(R$string.notification_category_media_playback, 2, "media"));
            hashSet.add("media");
            map.put("webrtc_cam_and_mic", ym.a.a(R$string.notification_category_webrtc_cam_and_mic, 2, "webrtc_cam_and_mic"));
            map.put("screen_capture", ym.a.a(R$string.notification_category_screen_capture, 4, "screen_capture"));
            map.put("sharing", ym.a.a(R$string.notification_category_sharing, 4, "sharing"));
            map.put("sites", ym.a.a(R$string.notification_category_sites, 3, "sites"));
            map.put("content_suggestions", ym.a.a(R$string.notification_category_content_suggestions, 2, "content_suggestions"));
            map.put("webapp_actions", ym.a.a(R$string.notification_category_fullscreen_controls, 1, "webapp_actions"));
            map.put("vr", ym.a.a(R$string.notification_category_vr, 4, "vr"));
            map.put("updates", ym.a.a(R$string.notification_category_updates, 4, "updates"));
            map.put("completed_downloads", new ym.a("completed_downloads", R$string.notification_category_completed_downloads, 2, true, false));
            map.put("announcement", new ym.a("announcement", R$string.notification_category_announcement, 2, true, false));
            map.put("twa_disclosure_initial", new ym.a("twa_disclosure_initial", R$string.notification_category_webapps, 5, false, true));
            map.put("twa_disclosure_subsequent", ym.a.a(R$string.notification_category_webapps_quiet, 1, "twa_disclosure_subsequent"));
            int i = R$string.notification_category_price_drop;
            map.put("shopping_price_drop_alerts", ym.a.a(i, 3, "shopping_price_drop_alerts"));
            NotificationManagerProxyImpl notificationManagerProxyImpl = new NotificationManagerProxyImpl(xz.a);
            NotificationChannel notificationChannelG = notificationManagerProxyImpl.g("shopping_price_drop_alerts");
            if (notificationChannelG != null) {
                hashSet.add("shopping_price_drop_alerts_default");
                importance = notificationChannelG.getImportance() != 2 ? notificationChannelG.getImportance() : 3;
                notificationManagerProxyImpl.a("shopping_price_drop_alerts");
            }
            map.put("shopping_price_drop_alerts_default", ym.a.a(i, importance, "shopping_price_drop_alerts_default"));
            map.put("security_key", ym.a.a(R$string.notification_category_security_key, 4, "security_key"));
            map.put("chrome_tips", ym.a.a(R$string.notification_category_feature_guide, 4, "chrome_tips"));
            map.put("bluetooth", ym.a.a(R$string.notification_category_bluetooth, 2, "bluetooth"));
            map.put("usb", ym.a.a(R$string.notification_category_usb, 2, "usb"));
            a = Collections.unmodifiableMap(map);
            b = Collections.unmodifiableSet(hashSet);
        }
    }
}
