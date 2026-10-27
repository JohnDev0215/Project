package defpackage;

import android.content.Intent;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: WebappIntentUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class ns3 {
    public static final String[] a = {"org.chromium.chrome.browser.webapp_id", "org.chromium.chrome.browser.webapp_url", "org.chromium.chrome.browser.webapk_force_navigation", "org.chromium.chrome.browser.webapp_source", "org.chromium.chrome.browser.webapp_scope", "org.chromium.chrome.browser.webapp_icon", "org.chromium.chrome.browser.webapp_shortcut_version", "org.chromium.chrome.browser.webapp_name", "org.chromium.chrome.browser.webapp_short_name", "org.chromium.chrome.browser.webapp_display_mode", "org.chromium.content_public.common.orientation", "org.chromium.chrome.browser.theme_color", "org.chromium.chrome.browser.background_color", "org.chromium.chrome.browser.is_icon_generated", "org.chromium.chrome.browser.webapp_icon_adaptive"};
    public static final String[] b = {"org.chromium.chrome.browser.webapp_id", "org.chromium.chrome.browser.webapp_url", "org.chromium.chrome.browser.webapk_force_navigation", "org.chromium.chrome.browser.webapp_source", "org.chromium.chrome.browser.webapk_package_name", "org.chromium.chrome.browser.webapk.splash_provided_by_webapk", "org.chromium.chrome.browser.webapk_launch_time", "org.chromium.webapk.new_style_splash_shown_time", "org.chromium.webapk.selected_share_target_activity_class_name", "android.intent.extra.SUBJECT", "android.intent.extra.TEXT", "android.intent.extra.STREAM", "com.android.browser.application_id"};

    public static void a(Intent intent, Intent intent2, String[] strArr) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }
        HashSet hashSet = new HashSet(extras.keySet());
        for (String str : strArr) {
            hashSet.remove(str);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            extras.remove((String) it.next());
        }
        intent2.putExtras(extras);
    }

    public static String b(String str) {
        return C0175b.j("webapk-", str);
    }
}
