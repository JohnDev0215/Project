package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.text.TextUtils;
import java.util.HashMap;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.PowerBroadcastReceiver;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: ChromeActivitySessionTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class lp {

    @SuppressLint({"StaticFieldLeak"})
    public static lp f;
    public final PowerBroadcastReceiver a = new PowerBroadcastReceiver();
    public final HashMap b = new HashMap();
    public final cl3 c;
    public boolean d;
    public boolean e;

    public lp() {
        AppHooks.get().getClass();
        this.c = new cl3();
    }

    public static lp a() {
        Object obj = ThreadUtils.a;
        if (f == null) {
            f = new lp();
        }
        return f;
    }

    public static void b() {
        int i;
        String defaultLocaleListString = LocaleUtils.getDefaultLocaleListString();
        String strG = SharedPreferencesManager.getInstance().g("locale", null);
        boolean z = xs0.d.c;
        if (TextUtils.isEmpty(strG) || TextUtils.isEmpty(defaultLocaleListString)) {
            i = 5;
        } else if (TextUtils.equals(strG, defaultLocaleListString)) {
            i = 0;
        } else {
            String[] strArrSplit = strG.split(",");
            String[] strArrSplit2 = defaultLocaleListString.split(",");
            i = z ? TextUtils.equals(strArrSplit[0], strArrSplit2[0]) ? 2 : 1 : TextUtils.equals(strArrSplit[0], strArrSplit2[0]) ? 4 : 3;
        }
        oj2.U(i, 6, "LanguageUsage.UI.Android.IsLocaleUpdated");
        if (TextUtils.equals(strG, defaultLocaleListString)) {
            return;
        }
        SharedPreferencesManager.getInstance().o("locale", defaultLocaleListString);
        N.Mo7FLW6m(defaultLocaleListString);
        if (strG != null) {
            BrowsingDataBridge.b().a(null, new int[]{1}, 4);
        }
    }
}
