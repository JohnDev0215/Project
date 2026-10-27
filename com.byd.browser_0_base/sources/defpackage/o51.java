package defpackage;

import androidx.preference.Preference;
import androidx.preference.d;
import org.chromium.chrome.browser.language.settings.LanguageItemListPreference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o51 implements Preference.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ d f;
    public final /* synthetic */ Preference g;

    public /* synthetic */ o51(d dVar, Preference preference, int i) {
        this.e = i;
        this.f = dVar;
        this.g = preference;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = this.e;
        Preference preference2 = this.g;
        d dVar = this.f;
        switch (i) {
            case 0:
                LanguageSettings languageSettings = (LanguageSettings) dVar;
                languageSettings.F0(languageSettings.h0.e(languageSettings.D(), ((LanguageItemListPreference) preference2).Y.a()));
                break;
            default:
                String[] strArr = SingleWebsiteSettings.u0;
                ((SingleWebsiteSettings) dVar).T0(preference2);
                break;
        }
        return true;
    }
}
