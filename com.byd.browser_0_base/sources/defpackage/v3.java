package defpackage;

import J.N;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AddToHomescreenMostVisitedTileClickObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v3 implements h.a {
    public GURL a;

    public v3(z2 z2Var, t3 t3Var) {
        sk skVar = bq.a;
        if (N.M09VlOh_("AddToHomescreenIPH")) {
            new i20(z2Var, new u3(this, t3Var), null);
        }
    }

    @Override // org.chromium.chrome.browser.ntp.h.a
    public final void a(w93 w93Var, Tab tab) {
        if (w93Var.a.d != 0) {
            return;
        }
        this.a = tab.getUrl().f();
    }
}
