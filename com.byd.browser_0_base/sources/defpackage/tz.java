package defpackage;

import J.N;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import defpackage.aa3;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ContextMenuManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tz implements WindowAndroid.d {
    public final nk1 a;
    public final yd3 b;
    public final Runnable c;
    public final String d = "Suggestions";
    public View e;

    /* JADX INFO: compiled from: ContextMenuManager.java */
    public interface a {
    }

    /* JADX INFO: compiled from: ContextMenuManager.java */
    public class b implements MenuItem.OnMenuItemClickListener {
        public final a e;

        public b(a aVar) {
            this.e = aVar;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            String str = tz.this.d;
            a aVar = this.e;
            if (itemId == 1) {
                ((aa3.e) aVar).a(4);
                n3.X0(str + ".ContextMenu.OpenItemInNewTab");
                return true;
            }
            if (itemId == 2) {
                aa3.e eVar = (aa3.e) aVar;
                aa3 aa3Var = aa3.this;
                w93 w93VarA = aa3Var.a(eVar.e);
                if (w93VarA != null) {
                    ca3 ca3Var = (ca3) aa3Var.c;
                    ca3.d(4, ca3Var.g);
                    String strJ = w93VarA.a.b.j();
                    ca3Var.e(w93VarA);
                    ca3Var.c.c(strJ, 4, true);
                    r82.b("Chrome.Querytiles.RecentMvClicks");
                }
                n3.X0(str + ".ContextMenu.OpenItemInNewTabInGroup");
                return true;
            }
            if (itemId == 3) {
                ((aa3.e) aVar).a(8);
                n3.X0(str + ".ContextMenu.OpenItemInIncognitoTab");
                return true;
            }
            if (itemId == 4) {
                ((aa3.e) aVar).a(6);
                n3.X0(str + ".ContextMenu.OpenItemInNewWindow");
                return true;
            }
            if (itemId == 5) {
                ((aa3.e) aVar).a(7);
                n3.X0(str + ".ContextMenu.DownloadItem");
                return true;
            }
            if (itemId != 7) {
                return false;
            }
            aa3.e eVar2 = (aa3.e) aVar;
            aa3 aa3Var2 = aa3.this;
            ao2 ao2Var = eVar2.e;
            w93 w93VarA2 = aa3Var2.a(ao2Var);
            if (w93VarA2 != null) {
                Runnable runnable = eVar2.g;
                if (runnable != null) {
                    runnable.run();
                }
                aa3Var2.j = ao2Var.b;
                lj1 lj1Var = new lj1(26, eVar2);
                ca3 ca3Var2 = (ca3) aa3Var2.c;
                MostVisitedSitesBridge mostVisitedSitesBridge = ca3Var2.d;
                ao2 ao2Var2 = w93VarA2.a;
                GURL gurl = ao2Var2.b;
                long j = mostVisitedSitesBridge.a;
                if (j != 0) {
                    N.MQm3a0t7(j, mostVisitedSitesBridge, gurl, true);
                }
                if (ca3Var2.f == null) {
                    ca3Var2.f = new ba3(ca3Var2, lj1Var);
                }
                int i = R$string.most_visited_item_removed;
                Context context = ca3Var2.a;
                tp2 tp2VarA = tp2.a(context.getString(i), ca3Var2.f, 0, 2);
                tp2VarA.d = context.getString(R$string.undo);
                tp2VarA.e = ao2Var2.b;
                ca3Var2.b.c(tp2VarA);
            }
            n3.X0(str + ".ContextMenu.RemoveItem");
            return true;
        }
    }

    public tz(hw2 hw2Var, yd3 yd3Var, Runnable runnable) {
        this.a = hw2Var;
        this.b = yd3Var;
        this.c = runnable;
    }

    @Override // org.chromium.ui.base.WindowAndroid.d
    public final void a() {
        if (this.e == null) {
            return;
        }
        this.e = null;
        this.b.c(true);
    }

    public final int b(Context context, int i) {
        if (i != 1) {
            if (i == 2) {
                return R$string.contextmenu_open_in_new_tab_group;
            }
            if (i == 3) {
                return R$string.contextmenu_open_in_incognito_tab;
            }
            if (i == 4) {
                return R$string.contextmenu_open_in_other_window;
            }
            if (i == 5) {
                return R$string.contextmenu_save_link;
            }
            if (i != 7) {
                return 0;
            }
            return R$string.remove;
        }
        if (oj2.D(context)) {
            if (CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:enable_tab_group_auto_creation"), true)) {
                this.a.getClass();
                if (!(r2 instanceof ri1)) {
                    return R$string.contextmenu_open_in_new_tab_group;
                }
            }
        }
        return R$string.contextmenu_open_in_new_tab;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0017 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c(int r5, tz.a r6) {
        /*
            r4 = this;
            r0 = r6
            aa3$e r0 = (aa3.e) r0
            r1 = 7
            r2 = 0
            r3 = 1
            if (r5 == r1) goto Lc
            r0.getClass()
            goto L12
        Lc:
            ao2 r0 = r0.e
            int r0 = r0.e
            if (r0 != r3) goto L14
        L12:
            r0 = r3
            goto L15
        L14:
            r0 = r2
        L15:
            if (r0 != 0) goto L18
            return r2
        L18:
            if (r5 == r3) goto L77
            r0 = 2
            nk1 r4 = r4.a
            if (r5 == r0) goto L49
            r0 = 3
            if (r5 == r0) goto L41
            r0 = 4
            if (r5 == r0) goto L3c
            r4 = 5
            if (r5 == r4) goto L2c
            if (r5 == r1) goto L2b
            return r2
        L2b:
            return r3
        L2c:
            aa3$e r6 = (aa3.e) r6
            ao2 r4 = r6.e
            org.chromium.url.GURL r4 = r4.b
            if (r4 == 0) goto L3b
            boolean r4 = J.N.MXyz2Okt(r4)
            if (r4 == 0) goto L3b
            r2 = r3
        L3b:
            return r2
        L3c:
            boolean r4 = r4.a()
            return r4
        L41:
            r4.getClass()
            boolean r4 = J.N.M$3vpOHw()
            return r4
        L49:
            i41 r5 = defpackage.ar.d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "TabGridLayoutAndroid"
            r6.append(r0)
            java.lang.String r0 = ":"
            r6.append(r0)
            java.lang.String r0 = "enable_tab_group_auto_creation"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.String r5 = r5.b(r6)
            boolean r5 = org.chromium.chrome.browser.flags.CachedFeatureFlags.b(r5, r3)
            if (r5 != 0) goto L76
            r4.getClass()
            boolean r4 = r4 instanceof defpackage.ri1
            r4 = r4 ^ r3
            if (r4 == 0) goto L76
            r2 = r3
        L76:
            return r2
        L77:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tz.c(int, tz$a):boolean");
    }
}
