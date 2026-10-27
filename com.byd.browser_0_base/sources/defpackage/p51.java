package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.c;

/* JADX INFO: compiled from: LanguageSettings.java */
/* JADX INFO: loaded from: classes.dex */
public final class p51 implements Preference.c {
    public final /* synthetic */ ContentLanguagesPreference e;
    public final /* synthetic */ LanguageSettings f;

    public p51(LanguageSettings languageSettings, ContentLanguagesPreference contentLanguagesPreference) {
        this.f = languageSettings;
        this.e = contentLanguagesPreference;
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        this.f.M0().e("translate.enabled", zBooleanValue);
        this.e.U.I();
        c.g(zBooleanValue ? 5 : 4);
        return true;
    }
}
