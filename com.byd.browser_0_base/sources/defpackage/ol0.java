package defpackage;

import J.N;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.language.settings.LanguageItemPickerPreference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ol0 implements PrefChangeRegistrar.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ol0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
    public final void f() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ((e) obj).n();
                break;
            default:
                int i2 = LanguageSettings.l0;
                ((LanguageItemPickerPreference) obj).Z(LocaleUtils.a(N.MMKf4EpW()));
                break;
        }
    }
}
