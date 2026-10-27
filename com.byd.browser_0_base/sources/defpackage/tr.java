package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tr implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeTabbedActivity f;

    public /* synthetic */ tr(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.e = i;
        this.f = chromeTabbedActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        ChromeTabbedActivity chromeTabbedActivity = this.f;
        switch (i) {
            case 0:
                n3 n3Var = ChromeTabbedActivity.S1;
                chromeTabbedActivity.y0.f.p(new os(chromeTabbedActivity, 3));
                Tab tabV2 = chromeTabbedActivity.v2();
                if (tabV2 != null && bk3.l(tabV2.getUrl())) {
                    um1.a(6);
                    um1.a(5);
                }
                boolean zIsIncognito = chromeTabbedActivity.D2().isIncognito();
                oj2.U(zIsIncognito ? 1 : 0, 6, "Bookmarks.OpenBookmarkManager.PerProfileType");
                oj2.U(zIsIncognito ? 1 : 0, 6, "Android.OpenHistoryFromMenu.PerProfileType");
                n3.X0("MobileMenuAllBookmarks");
                n3.X0("MobileMenuHistory");
                zb2.g("Chrome.StartSurface.OpenHistoryCount");
                break;
            default:
                n3 n3Var2 = ChromeTabbedActivity.S1;
                chromeTabbedActivity.v3();
                break;
        }
    }
}
