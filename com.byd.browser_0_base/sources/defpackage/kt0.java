package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.components.browser_ui.site_settings.GroupedWebsitesSettings;
import org.chromium.components.browser_ui.site_settings.f;

/* JADX INFO: compiled from: GroupedWebsitesSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kt0 extends ph1 {
    public final /* synthetic */ GroupedWebsitesSettings f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kt0(GroupedWebsitesSettings groupedWebsitesSettings, mb1 mb1Var) {
        super(mb1Var);
        this.f = groupedWebsitesSettings;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        GroupedWebsitesSettings groupedWebsitesSettings = this.f;
        for (f fVar : groupedWebsitesSettings.i0.f) {
            zn2 zn2Var = groupedWebsitesSettings.h0;
            String strH = fVar.e.h();
            ((kr) zn2Var).getClass();
            if (N.MmfN78Q9(strH)) {
                return true;
            }
        }
        return false;
    }
}
