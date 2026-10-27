package defpackage;

import android.content.Intent;
import androidx.preference.Preference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.language.settings.SelectLanguageFragment;
import org.chromium.chrome.browser.language.settings.c;

/* JADX INFO: compiled from: LanguageSettings.java */
/* JADX INFO: loaded from: classes.dex */
public final class r51 implements Preference.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;
    public final /* synthetic */ LanguageSettings h;

    public r51(LanguageSettings languageSettings, int i, int i2, int i3) {
        this.h = languageSettings;
        this.e = i;
        this.f = i2;
        this.g = i3;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) throws Exception {
        c.h(this.e);
        int i = LanguageSettings.l0;
        LanguageSettings languageSettings = this.h;
        Intent intentE = languageSettings.h0.e(languageSettings.D(), SelectLanguageFragment.class.getName());
        intentE.putExtra("SelectLanguageFragment.PotentialLanguages", this.f);
        languageSettings.G0(intentE, this.g);
        return true;
    }
}
