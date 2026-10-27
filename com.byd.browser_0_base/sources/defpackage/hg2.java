package defpackage;

import J.N;
import android.content.Context;
import defpackage.wp2;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_engines.settings.SearchEngineSettings;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: compiled from: SearchEngineChoiceNotification.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hg2 implements wp2.b {
    public final Context e;
    public final SettingsLauncher f;

    public hg2(Context context) {
        n3 n3Var = ChromeTabbedActivity.S1;
        this.e = context;
        this.f = n3Var;
    }

    @Override // wp2.b
    public final void j(Object obj) {
        int iMJpD6RKI;
        this.f.g(this.e, SearchEngineSettings.class);
        oj2.U(1, 3, "Android.SearchEngineChoice.Events");
        TemplateUrlService templateUrlServiceQ = oj2.q(Profile.d());
        TemplateUrl templateUrlC = templateUrlServiceQ.c();
        if (templateUrlC == null) {
            iMJpD6RKI = -1;
        } else {
            iMJpD6RKI = N.MJpD6RKI(templateUrlServiceQ.c, templateUrlServiceQ, templateUrlC.b());
        }
        oj2.U(iMJpD6RKI, 64, "Android.SearchEngineChoice.SearchEngineBeforeChoicePrompt");
        SharedPreferencesManager.getInstance().m(iMJpD6RKI, "search_engine_choice_default_type_before");
    }
}
