package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* JADX INFO: compiled from: SingleWebsiteSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tn2 extends ph1 {
    public final /* synthetic */ SingleWebsiteSettings f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public tn2(SingleWebsiteSettings singleWebsiteSettings, mb1 mb1Var) {
        super(mb1Var);
        this.f = singleWebsiteSettings;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        SingleWebsiteSettings singleWebsiteSettings = this.f;
        zn2 zn2Var = singleWebsiteSettings.h0;
        String strH = singleWebsiteSettings.m0.e.h();
        ((kr) zn2Var).getClass();
        return N.MmfN78Q9(strH);
    }
}
