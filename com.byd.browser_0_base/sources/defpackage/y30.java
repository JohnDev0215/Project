package defpackage;

import androidx.browser.customtabs.CustomTabsSessionToken;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: CustomTabNavigationEventObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class y30 extends nh0 {
    public final CustomTabsSessionToken e;
    public final CustomTabsConnection f;

    public y30(oj ojVar, CustomTabsConnection customTabsConnection) {
        this.e = ojVar.z();
        this.f = customTabsConnection;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void J0(int i, Tab tab) {
        this.f.n(this.e, i == -3 ? 4 : 3);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        this.f.n(this.e, 6);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        this.f.n(this.e, 2);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        this.f.n(this.e, 1);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        this.f.n(this.e, 5);
    }
}
