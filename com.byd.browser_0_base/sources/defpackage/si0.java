package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: ExploreSurfaceNavigationDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class si0 implements nk1 {
    public final nw2<Tab> a;

    public si0(nw2<Tab> nw2Var) {
        this.a = nw2Var;
    }

    @Override // defpackage.nk1
    public final boolean a() {
        return false;
    }

    @Override // defpackage.nk1
    public final Tab b(int i, LoadUrlParams loadUrlParams) {
        Tab tabB = zb2.b(loadUrlParams, false, false, this.a.get());
        n3.X0("ContentSuggestions.Feed.CardAction.Open.StartSurface");
        return tabB;
    }
}
