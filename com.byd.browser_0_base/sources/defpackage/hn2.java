package defpackage;

import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* JADX INFO: compiled from: SingleCategorySettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hn2 extends ph1 {
    public final /* synthetic */ SingleCategorySettings f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public hn2(SingleCategorySettings singleCategorySettings, mb1 mb1Var) {
        super(mb1Var);
        this.f = singleCategorySettings;
    }

    @Override // defpackage.mb1
    public final boolean g(Preference preference) {
        return false;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        String str;
        wy wyVarE = ((dt3) preference).c0.e(this.f.k0.f());
        if (wyVarE == null || (str = wyVarE.i) == null) {
            return false;
        }
        return str.equals("policy");
    }
}
