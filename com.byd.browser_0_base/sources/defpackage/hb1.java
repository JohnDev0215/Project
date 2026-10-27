package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.settings.MainSettings;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: compiled from: MainSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hb1 implements gq {
    public final /* synthetic */ MainSettings e;

    public hb1(MainSettings mainSettings) {
        this.e = mainSettings;
    }

    @Override // defpackage.mb1
    public final boolean g(Preference preference) {
        if ("search_engine".equals(preference.p)) {
            TemplateUrlService templateUrlServiceQ = oj2.q(this.e.k0);
            return N.MELaF8Vs(templateUrlServiceQ.c, templateUrlServiceQ);
        }
        if (qx1.d() && "passwords".equals(preference.p)) {
            return false;
        }
        return r(preference);
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        boolean zEquals = "search_engine".equals(preference.p);
        MainSettings mainSettings = this.e;
        if (zEquals) {
            TemplateUrlService templateUrlServiceQ = oj2.q(mainSettings.k0);
            return N.MELaF8Vs(templateUrlServiceQ.c, templateUrlServiceQ);
        }
        if (qx1.d() && "passwords".equals(preference.p)) {
            return oj2.k(mainSettings.k0).d("credentials_enable_service");
        }
        return false;
    }
}
