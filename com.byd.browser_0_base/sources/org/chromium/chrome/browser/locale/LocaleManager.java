package org.chromium.chrome.browser.locale;

import android.app.Activity;
import defpackage.oj2;
import defpackage.r91;
import defpackage.t91;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: loaded from: classes2.dex */
public class LocaleManager {
    public static final LocaleManager b = new LocaleManager();
    public final t91 a = new t91();

    @CalledByNative
    public static LocaleManager getInstance() {
        return b;
    }

    public final boolean a() {
        t91 t91Var = this.a;
        t91Var.getClass();
        return !t91Var.a && SharedPreferencesManager.getInstance().d(-1, "com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN") == -1;
    }

    public final void b(Activity activity, Callback<Boolean> callback) {
        t91 t91Var = this.a;
        t91Var.getClass();
        TemplateUrlService templateUrlServiceQ = oj2.q(Profile.d());
        templateUrlServiceQ.j(new r91(t91Var, activity, templateUrlServiceQ, callback, 0));
    }

    @CalledByNative
    public String getMailRUReferralId() {
        this.a.getClass();
        return "";
    }

    @CalledByNative
    public String getYandexReferralId() {
        this.a.getClass();
        return "";
    }

    @CalledByNative
    public void recordUserTypeMetrics() {
        this.a.getClass();
    }
}
