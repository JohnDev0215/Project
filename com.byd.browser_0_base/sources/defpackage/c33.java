package defpackage;

import android.util.Log;
import java.util.ArrayList;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabReparentingController.java */
/* JADX INFO: loaded from: classes.dex */
public final class c33 {
    public final a a;
    public final f9 b;

    /* JADX INFO: compiled from: TabReparentingController.java */
    public interface a {
    }

    public c33(ab2 ab2Var, g9 g9Var) {
        this.a = ab2Var;
        this.b = g9Var;
    }

    public final void a() {
        ab2 ab2Var = (ab2) this.a;
        y13 y13Var = ab2Var.a;
        y13Var.g(false).c();
        y13Var.g(true).c();
        ArrayList arrayList = new ArrayList(y13Var.p());
        w03 w03VarK = y13Var.g(false).k();
        for (int i = 0; i < w03VarK.getCount(); i++) {
            arrayList.add(w03VarK.getTabAt(i));
        }
        w03 w03VarK2 = y13Var.g(true).k();
        for (int i2 = 0; i2 < w03VarK2.getCount(); i2++) {
            arrayList.add(w03VarK2.getTabAt(i2));
        }
        ab2Var.a.n();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            Tab tab = (Tab) arrayList.get(i5);
            if (tab.k()) {
                tab.y();
                tab.c().m().c();
                i4++;
            }
            int id = tab.getId();
            f9 f9Var = this.b;
            if (f9Var.a(id)) {
                i3++;
            } else {
                GURL url = tab.getUrl();
                ab2Var.getClass();
                if (!bk3.l(url)) {
                    f9Var.c(tab.getId(), new d33(tab, null));
                    bb2.b(tab).a();
                    i3++;
                }
            }
        }
        Log.i("cr_org.chromium.chrome.browser.app.tab_activity_glue.TabReparentingController", "#prepareTabsForReparenting, num tabs awaiting reparenting: " + i3 + ", num tabs still loading: " + i4);
    }
}
