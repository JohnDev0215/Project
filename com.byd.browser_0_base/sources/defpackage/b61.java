package defpackage;

import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* JADX INFO: compiled from: LayoutManagerChrome.java */
/* JADX INFO: loaded from: classes.dex */
public final class b61 implements Callback<TabContentManager> {
    public final /* synthetic */ zp1 e;
    public final /* synthetic */ c61 f;

    public b61(c61 c61Var, aq1 aq1Var) {
        this.f = c61Var;
        this.e = aq1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(TabContentManager tabContentManager) {
        TabContentManager tabContentManager2 = tabContentManager;
        TabContentManager.b bVar = new TabContentManager.b() { // from class: a61
            @Override // org.chromium.chrome.browser.compositor.layouts.content.TabContentManager.b
            public final void a() {
                this.a.f.T(null);
            }
        };
        ArrayList<TabContentManager.b> arrayList = tabContentManager2.f;
        if (!arrayList.contains(bVar)) {
            arrayList.add(bVar);
        }
        c61 c61Var = this.f;
        l43 l43Var = c61Var.S;
        if (l43Var != null) {
            l43Var.F(tabContentManager2);
        }
        y51 y51Var = c61Var.U;
        if (y51Var != null) {
            y51Var.F(tabContentManager2);
        }
        or2 or2Var = c61Var.T;
        if (or2Var != null) {
            or2Var.F(tabContentManager2);
        }
        this.e.d(this);
    }
}
