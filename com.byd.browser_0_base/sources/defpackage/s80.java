package defpackage;

import defpackage.z2;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: DesktopSiteSettingsIPHController.java */
/* JADX INFO: loaded from: classes.dex */
public final class s80 extends z2.b {
    public final /* synthetic */ Callback h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s80(z2 z2Var, Callback callback) {
        super(z2Var, false);
        this.h = callback;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        if (tab == null) {
            return;
        }
        this.h.onResult(tab);
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        if (tab == null) {
            return;
        }
        this.h.onResult(tab);
    }
}
