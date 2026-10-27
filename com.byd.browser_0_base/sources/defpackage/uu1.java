package defpackage;

import android.view.View;
import androidx.fragment.app.a;
import org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment;
import org.chromium.components.page_info.c;

/* JADX INFO: compiled from: PageInfoPreferenceSubpageController.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class uu1 implements xu1 {
    public final c e;
    public SiteSettingsPreferenceFragment f;

    public uu1(vq vqVar) {
        this.e = vqVar;
    }

    public final View e(SiteSettingsPreferenceFragment siteSettingsPreferenceFragment) {
        c cVar = this.e;
        lp0 lp0VarA = cVar.a();
        if (lp0VarA.L()) {
            return null;
        }
        this.f = siteSettingsPreferenceFragment;
        vq vqVar = (vq) cVar;
        siteSettingsPreferenceFragment.h0 = new kr(vqVar.i, vqVar.j);
        a aVar = new a(lp0VarA);
        aVar.d(0, this.f, null, 1);
        aVar.i();
        return this.f.y0();
    }

    public final void g() {
        lp0 lp0VarA = this.e.a();
        SiteSettingsPreferenceFragment siteSettingsPreferenceFragment = this.f;
        this.f = null;
        if (lp0VarA == null || lp0VarA.L()) {
            return;
        }
        a aVar = new a(lp0VarA);
        aVar.k(siteSettingsPreferenceFragment);
        aVar.i();
    }
}
