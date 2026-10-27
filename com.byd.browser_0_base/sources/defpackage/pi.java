package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.bottomsheet.j;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: BottomSheetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pi extends nh0 {
    public final /* synthetic */ j e;
    public final /* synthetic */ ui f;

    public pi(ui uiVar, j jVar) {
        this.f = uiVar;
        this.e = jVar;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        ui uiVar = this.f;
        if (uiVar.y != tab) {
            return;
        }
        uiVar.y = null;
        ((f) this.e).p();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        ((f) this.e).p();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        ((f) this.e).p();
    }
}
