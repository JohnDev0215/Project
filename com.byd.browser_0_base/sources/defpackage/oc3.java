package defpackage;

import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oc3 implements TemplateUrlService.c {
    public TemplateUrl e;
    public final /* synthetic */ g f;

    public oc3(g gVar) {
        this.f = gVar;
        this.e = gVar.C.c();
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        g gVar = this.f;
        TemplateUrl templateUrlC = gVar.C.c();
        TemplateUrl templateUrl = this.e;
        if (templateUrl == null && templateUrlC == null) {
            return;
        }
        if (templateUrl == null || !templateUrl.equals(templateUrlC)) {
            this.e = templateUrlC;
            gVar.k.a.n();
        }
    }
}
