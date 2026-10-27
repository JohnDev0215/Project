package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mc3 implements Callback<Profile> {
    public final /* synthetic */ zp1 e;
    public final /* synthetic */ g f;

    public mc3(g gVar, zp1 zp1Var) {
        this.f = gVar;
        this.e = zp1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Profile profile) {
        TemplateUrlService templateUrlServiceQ = oj2.q(profile);
        g gVar = this.f;
        gVar.C = templateUrlServiceQ;
        templateUrlServiceQ.j(new d92(13, gVar));
        this.e.d(this);
    }
}
