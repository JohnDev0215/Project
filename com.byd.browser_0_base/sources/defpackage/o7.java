package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$menu;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AppMenuPropertiesDelegateImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class o7 implements m7 {
    public PropertyModel a;
    public final Context b;
    public final boolean c;
    public final z2 d;
    public final tj1 e;
    public final y13 f;
    public final g g;
    public final View h;
    public ll i;
    public final zp1<BookmarkModel> j;
    public boolean k;
    public final n3 l;
    public int m;
    public hy0 n;
    public l61 o;
    public qs1<lr2> p;
    public n7 q;
    public int r;
    public aw s;

    public o7(Context context, z2 z2Var, uj1 uj1Var, y13 y13Var, g gVar, View view, rs1 rs1Var, rs1 rs1Var2, aq1 aq1Var, rs1 rs1Var3) {
        ll llVar = new ll();
        this.i = new ll();
        this.b = context;
        this.c = DeviceFormFactor.a(context);
        this.d = z2Var;
        this.e = uj1Var;
        this.f = y13Var;
        this.g = gVar;
        this.h = view;
        if (rs1Var3 != null) {
            rs1Var3.h(llVar.b(new xr(2, this)));
        }
        if (rs1Var != null) {
            rs1Var.h(this.i.b(new so(3, this)));
        }
        if (!zb2.e(context) && rs1Var2 != null && zb2.d(context)) {
            this.p = rs1Var2;
            rs1Var2.h(this.i.b(new wb(3, this)));
        }
        this.j = aq1Var;
        this.l = new n3(0);
    }

    @Override // defpackage.m7
    public int a() {
        return 0;
    }

    @Override // defpackage.m7
    public ArrayList b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new jj3());
        arrayList.add(new dy0());
        arrayList.add(new kb0());
        return arrayList;
    }

    @Override // defpackage.m7
    public boolean f() {
        return false;
    }

    @Override // defpackage.m7
    public Bundle g(int i) {
        Bundle bundle = new Bundle();
        if (i == R$id.add_to_homescreen_id) {
            bundle.putInt("AppMenuTitleShown", this.m);
        }
        return bundle;
    }

    public int h() {
        return R$menu.main_menu;
    }

    public final boolean i() {
        if (zb2.e(this.b)) {
            l61 l61Var = this.o;
            return l61Var != null && l61Var.q(16);
        }
        qs1<lr2> qs1Var = this.p;
        return (qs1Var == null || qs1Var.get() == null || this.r != 1) ? false : true;
    }

    public final boolean j() {
        l61 l61Var = this.o;
        return (l61Var == null || !l61Var.q(2) || this.o.r() || i()) ? false : true;
    }

    public final void k(boolean z) {
        if (this.a != null) {
            Resources resources = this.b.getResources();
            ((Drawable) this.a.j(j7.h)).setLevel(z ? resources.getInteger(R$integer.reload_button_level_stop) : resources.getInteger(R$integer.reload_button_level_reload));
            this.a.p(j7.b, resources.getString(z ? R$string.accessibility_btn_stop_loading : R$string.accessibility_btn_refresh));
            this.a.p(j7.c, resources.getString(z ? R$string.menu_stop_refresh : R$string.refresh));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(android.view.Menu r13, org.chromium.chrome.browser.tab.Tab r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o7.l(android.view.Menu, org.chromium.chrome.browser.tab.Tab, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x05fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(android.view.Menu r32, defpackage.i7 r33) {
        /*
            Method dump skipped, instruction units count: 1757
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o7.m(android.view.Menu, i7):void");
    }

    public boolean n(Tab tab) {
        return false;
    }

    public final boolean o() {
        vj1 vj1Var = vj1.h;
        boolean z = (Build.VERSION.SDK_INT < 30 ? false : bq.E.a()) && vj1.p();
        boolean z2 = this.c;
        tj1 tj1Var = this.e;
        if (!z) {
            uj1 uj1Var = (uj1) tj1Var;
            uj1Var.getClass();
            vj1 vj1Var2 = vj1.h;
            vj1Var2.getClass();
            if (vj1.a(uj1Var.a)) {
                return false;
            }
            uj1Var.getClass();
            vj1Var2.getClass();
            if (!vj1.b() || !z2) {
                uj1Var.getClass();
                vj1Var2.getClass();
                if (!vj1.o(uj1Var.a)) {
                    uj1Var.getClass();
                    vj1Var2.getClass();
                    if (!vj1.n(uj1Var.a)) {
                        return false;
                    }
                }
            }
            return true;
        }
        ((uj1) tj1Var).getClass();
        if (vj1.g() >= vj1.h() || BuildInfo.a.a.m) {
            return false;
        }
        if (!z2) {
            uj1 uj1Var2 = (uj1) tj1Var;
            uj1Var2.getClass();
            vj1 vj1Var3 = vj1.h;
            vj1Var3.getClass();
            if (vj1.f(uj1Var2.a) != null) {
                return false;
            }
            uj1Var2.getClass();
            vj1Var3.getClass();
            if (!vj1.o(uj1Var2.a)) {
                uj1Var2.getClass();
                vj1Var3.getClass();
                if (!vj1.n(uj1Var2.a)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(android.view.MenuItem r9, org.chromium.chrome.browser.tab.Tab r10) {
        /*
            r8 = this;
            zp1<org.chromium.chrome.browser.bookmarks.BookmarkModel> r0 = r8.j
            boolean r1 = r0.f()
            r2 = 0
            if (r1 == 0) goto L1a
            if (r10 != 0) goto Lc
            goto L1a
        Lc:
            java.lang.Object r1 = r0.get()
            org.chromium.chrome.browser.bookmarks.BookmarkModel r1 = (org.chromium.chrome.browser.bookmarks.BookmarkModel) r1
            boolean r1 = r1.q()
            r9.setEnabled(r1)
            goto L1d
        L1a:
            r9.setEnabled(r2)
        L1d:
            android.content.Context r8 = r8.b
            if (r10 == 0) goto L5a
            boolean r1 = r0.f()
            r3 = 1
            if (r1 != 0) goto L29
            goto L45
        L29:
            java.lang.Object r0 = r0.get()
            org.chromium.chrome.browser.bookmarks.BookmarkModel r0 = (org.chromium.chrome.browser.bookmarks.BookmarkModel) r0
            r0.getClass()
            java.lang.Object r1 = org.chromium.base.ThreadUtils.a
            long r4 = r0.a
            r6 = 0
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 != 0) goto L3d
            goto L45
        L3d:
            org.chromium.components.bookmarks.BookmarkId r10 = r0.p(r10)
            if (r10 == 0) goto L45
            r10 = r3
            goto L46
        L45:
            r10 = r2
        L46:
            if (r10 == 0) goto L5a
            int r10 = org.chromium.chrome.R$drawable.btn_star_filled
            r9.setIcon(r10)
            r9.setChecked(r3)
            int r10 = org.chromium.chrome.R$string.edit_bookmark
            java.lang.String r8 = r8.getString(r10)
            r9.setTitleCondensed(r8)
            goto L6b
        L5a:
            int r10 = org.chromium.chrome.R$drawable.btn_star
            r9.setIcon(r10)
            r9.setChecked(r2)
            int r10 = org.chromium.chrome.R$string.menu_bookmark
            java.lang.String r8 = r8.getString(r10)
            r9.setTitleCondensed(r8)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o7.p(android.view.MenuItem, org.chromium.chrome.browser.tab.Tab):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q(android.view.MenuItem r8) {
        /*
            r7 = this;
            android.content.Intent r0 = defpackage.hk2.e()
            android.content.ComponentName r1 = defpackage.hk2.d()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L3c
            java.lang.String r4 = r1.getPackageName()
            r0.setPackage(r4)
            java.util.List r0 = defpackage.wt1.c(r0, r2)
            java.util.Iterator r0 = r0.iterator()
        L1b:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L3c
            java.lang.Object r4 = r0.next()
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
            android.content.pm.ActivityInfo r4 = r4.activityInfo
            android.content.ComponentName r5 = new android.content.ComponentName
            android.content.pm.ApplicationInfo r6 = r4.applicationInfo
            java.lang.String r6 = r6.packageName
            java.lang.String r4 = r4.name
            r5.<init>(r6, r4)
            boolean r4 = r1.equals(r5)
            if (r4 == 0) goto L1b
            r0 = r3
            goto L3d
        L3c:
            r0 = r2
        L3d:
            r4 = 0
            if (r0 == 0) goto L70
            android.content.Context r0 = defpackage.xz.a
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            ku2 r5 = defpackage.ku2.t()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L68
            android.graphics.drawable.Drawable r6 = r0.getActivityIcon(r1)     // Catch: java.lang.Throwable -> L5f
            android.content.pm.ActivityInfo r1 = r0.getActivityInfo(r1, r2)     // Catch: java.lang.Throwable -> L5d
            java.lang.CharSequence r4 = r1.loadLabel(r0)     // Catch: java.lang.Throwable -> L5d
            r5.close()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L65
            r1 = r3
            r0 = r4
            r4 = r6
            goto L6a
        L5d:
            r0 = move-exception
            goto L61
        L5f:
            r0 = move-exception
            r6 = r4
        L61:
            r5.close()     // Catch: java.lang.Throwable -> L64
        L64:
            throw r0     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L65
        L65:
            r0 = r4
            r4 = r6
            goto L69
        L68:
            r0 = r4
        L69:
            r1 = r2
        L6a:
            java.lang.String r5 = "Android.IsLastSharedAppInfoRetrieved"
            defpackage.oj2.O(r5, r1)
            goto L71
        L70:
            r0 = r4
        L71:
            android.util.Pair r1 = new android.util.Pair
            r1.<init>(r4, r0)
            java.lang.Object r0 = r1.first
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            java.lang.Object r1 = r1.second
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r8.setIcon(r0)
            if (r1 == 0) goto L92
            int r0 = org.chromium.chrome.R$string.accessibility_menu_share_via
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r2] = r1
            android.content.Context r7 = r7.b
            java.lang.String r7 = r7.getString(r0, r3)
            r8.setTitle(r7)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o7.q(android.view.MenuItem):void");
    }

    public final void r(Menu menu, Tab tab, boolean z, boolean z2) {
        MenuItem menuItemFindItem = menu.findItem(R$id.request_desktop_site_row_menu_id);
        MenuItem menuItemFindItem2 = menu.findItem(R$id.request_desktop_site_id);
        MenuItem menuItemFindItem3 = menu.findItem(R$id.request_desktop_site_check_id);
        boolean z3 = (tab == null || !z || (z2 && (N.Mudil8Bg("RequestDesktopSiteExceptions") || !tab.isNativePage())) || a8.M(tab.getUrl()) || tab.c() == null) ? false : true;
        menuItemFindItem.setVisible(z3);
        if (z3) {
            boolean zL = tab.c().m().l();
            if (bq.a.a()) {
                menuItemFindItem2.setTitle(zL ? R$string.menu_item_request_mobile_site : R$string.menu_item_request_desktop_site);
                menuItemFindItem2.setIcon(zL ? R$drawable.smartphone_black_24dp : R$drawable.ic_desktop_windows);
                menuItemFindItem3.setVisible(false);
            } else {
                menuItemFindItem2.setTitle(R$string.menu_request_desktop_site);
                menuItemFindItem3.setVisible(true);
                menuItemFindItem3.setChecked(zL);
                Context context = this.b;
                menuItemFindItem2.setTitleCondensed(zL ? context.getString(R$string.menu_request_desktop_site_on) : context.getString(R$string.menu_request_desktop_site_off));
            }
        }
    }

    @Override // defpackage.m7
    public void c() {
    }

    @Override // defpackage.m7
    public void e() {
    }

    @Override // defpackage.m7
    public void d(i7 i7Var, View view) {
    }
}
