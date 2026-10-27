package defpackage;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.c;

/* JADX INFO: compiled from: LanguageSettings.java */
/* JADX INFO: loaded from: classes.dex */
public final class q51 implements Preference.c {
    public final /* synthetic */ ContentLanguagesPreference e;
    public final /* synthetic */ PreferenceCategory f;
    public final /* synthetic */ LanguageSettings g;

    public q51(LanguageSettings languageSettings, ContentLanguagesPreference contentLanguagesPreference, PreferenceCategory preferenceCategory) {
        this.g = languageSettings;
        this.e = contentLanguagesPreference;
        this.f = preferenceCategory;
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        this.g.M0().e("translate.enabled", zBooleanValue);
        this.e.U.I();
        this.f.U(zBooleanValue);
        c.g(zBooleanValue ? 5 : 4);
        return true;
    }
}
