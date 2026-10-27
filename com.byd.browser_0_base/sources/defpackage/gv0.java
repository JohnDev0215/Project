package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import defpackage.dv0;
import defpackage.wp2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.bookmarks.BookmarkSelectableListLayout;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataTabsFragment;
import org.chromium.chrome.browser.history.BrowsingHistoryBridge;
import org.chromium.chrome.browser.history.HistoryItemView;
import org.chromium.chrome.browser.history.HistoryManagerToolbar;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.ui.base.Clipboard;

/* JADX INFO: compiled from: HistoryManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class gv0 implements Toolbar.OnMenuItemClickListener, a.InterfaceC0128a<ev0>, SelectableListToolbar.b, wp2.b, dv0.a, vb {
    public final Activity e;
    public final boolean f;
    public final boolean g;
    public final aq1<Boolean> h;
    public final ViewGroup i;
    public ViewGroup j;
    public final BookmarkSelectableListLayout<ev0> k;
    public final dv0 l;
    public final a<ev0> m;
    public final HistoryManagerToolbar n;
    public final wp2 o;
    public final aq1<Boolean> p;
    public final aq1<Boolean> q;
    public final aq1<Boolean> r;
    public final PrefService s;
    public final Profile t;
    public boolean u;

    public gv0(Activity activity, boolean z, wp2 wp2Var, boolean z2, nw2 nw2Var, boolean z3, String str, BrowsingHistoryBridge browsingHistoryBridge) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.h = aq1Var;
        aq1<Boolean> aq1Var2 = new aq1<>();
        this.p = aq1Var2;
        aq1<Boolean> aq1Var3 = new aq1<>();
        this.q = aq1Var3;
        aq1<Boolean> aq1Var4 = new aq1<>();
        this.r = aq1Var4;
        this.e = activity;
        this.g = z;
        this.o = wp2Var;
        this.f = z2;
        Profile profileD = Profile.d();
        this.t = profileD;
        PrefService prefServiceK = oj2.k(profileD);
        this.s = prefServiceK;
        aq1Var4.r(Boolean.FALSE);
        I("Show");
        if (z2) {
            this.k = null;
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(activity).inflate(R$layout.incognito_history_placeholder, (ViewGroup) null);
            ImageButton imageButton = (ImageButton) viewGroup.findViewById(R$id.close_history_placeholder_button);
            if (z) {
                imageButton.setOnClickListener(new vt(12, this));
            } else {
                imageButton.setVisibility(8);
            }
            viewGroup.setFocusable(true);
            viewGroup.setFocusableInTouchMode(true);
            this.i = viewGroup;
            return;
        }
        FrameLayout frameLayout = new FrameLayout(activity);
        this.i = frameLayout;
        prefServiceK.e("history_clusters.visible", false);
        boolean zD = prefServiceK.d("history_clusters.visible");
        sk skVar = bq.a;
        if (N.M09VlOh_("Journeys") && zD) {
            prefServiceK.a("history_clusters.visible");
        }
        BookmarkSelectableListLayout<ev0> bookmarkSelectableListLayout = (BookmarkSelectableListLayout) LayoutInflater.from(activity).inflate(R$layout.history_main, (ViewGroup) null);
        this.k = bookmarkSelectableListLayout;
        a<ev0> aVar = new a<>();
        this.m = aVar;
        aVar.a(this);
        boolean z4 = SharedPreferencesManager.getInstance().readBoolean("history_home_show_info", true);
        dv0 dv0Var = new dv0(activity, this, z, z2, z4, true, null, aVar, nw2Var, aq1Var, browsingHistoryBridge);
        this.l = dv0Var;
        RecyclerView recyclerView = dv0Var.p;
        org.chromium.chrome.browser.history.a aVar2 = dv0Var.o;
        bookmarkSelectableListLayout.h(aVar2, recyclerView);
        aq1Var2.r(Boolean.valueOf(z4));
        aq1Var3.r(Boolean.valueOf(oj2.k(Profile.d()).a("history.deleting_enabled")));
        HistoryManagerToolbar historyManagerToolbar = (HistoryManagerToolbar) bookmarkSelectableListLayout.j(R$layout.history_toolbar, aVar, R$id.normal_menu_group, R$id.selection_mode_menu_group, this, z);
        this.n = historyManagerToolbar;
        historyManagerToolbar.setManager(this);
        historyManagerToolbar.A(this, R$string.history_search_hint, new wt(1, this));
        historyManagerToolbar.setInfoMenuItem(R$id.info_menu_id);
        M();
        historyManagerToolbar.getMenu().removeItem(R$id.optout_menu_id);
        bookmarkSelectableListLayout.setEmptyViewText(R$string.history_manager_empty);
        bookmarkSelectableListLayout.k.setOnTouchListener(new ah());
        aVar2.y = null;
        aVar2.U();
        if (z3) {
            throw null;
        }
        this.j = bookmarkSelectableListLayout;
        D();
        frameLayout.addView(this.j);
        bookmarkSelectableListLayout.getHandleBackPressChangedSupplier().p(new xr(22, this));
        D();
    }

    public static void I(String str) {
        n3.X0("Android.HistoryPage." + str);
    }

    public static void O(cw cwVar, View view, TabLayout tabLayout, AtomicReference atomicReference) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        rect.offset(tabLayout.getLeft(), tabLayout.getTop());
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R$dimen.history_toggle_added_touch_target_height);
        rect.top -= dimensionPixelSize;
        rect.bottom += dimensionPixelSize;
        TouchDelegate touchDelegate = (TouchDelegate) atomicReference.get();
        if (touchDelegate != null) {
            cwVar.a.remove(touchDelegate);
        }
        TouchDelegate touchDelegate2 = new TouchDelegate(rect, view);
        cwVar.a.add(touchDelegate2);
        atomicReference.set(touchDelegate2);
    }

    public final boolean C() {
        return false;
    }

    public final void D() {
        this.r.r(Boolean.valueOf(this.k.getHandleBackPressChangedSupplier().get().booleanValue()));
    }

    public final void E() {
        BookmarkSelectableListLayout<ev0> bookmarkSelectableListLayout;
        if (this.f || (bookmarkSelectableListLayout = this.k) == null) {
            return;
        }
        bookmarkSelectableListLayout.l();
        this.l.d();
    }

    public final void H(ArrayList arrayList, boolean z) {
        K("OpenSelected".concat(z ? "Incognito" : ""));
        dv0 dv0Var = this.l;
        if (!dv0Var.j || arrayList.size() <= 1) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                dv0Var.e(((ev0) it.next()).c, Boolean.valueOf(z), true);
            }
            return;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size() - 1);
        for (int i = 1; i < arrayList.size(); i++) {
            arrayList2.add(((ev0) arrayList.get(i)).c.j());
        }
        Intent intentB = dv0Var.b(((ev0) arrayList.get(0)).c, Boolean.valueOf(z), true);
        intentB.putExtra("org.chromium.chrome.browser.additional_urls", arrayList2);
        b.A(null, intentB, null);
    }

    public final void K(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.u ? "Search." : "");
        sb.append(str);
        I(sb.toString());
    }

    public final void M() {
        dv0 dv0Var = this.l;
        if (((LinearLayoutManager) dv0Var.p.getLayoutManager()).b() > 0) {
            return;
        }
        org.chromium.chrome.browser.history.a aVar = dv0Var.o;
        if (!(!aVar.j.k && aVar.p) || aVar.h <= 0 || this.n.B()) {
            return;
        }
        this.m.e();
    }

    public final boolean a() {
        BookmarkSelectableListLayout<ev0> bookmarkSelectableListLayout;
        if (this.f || (bookmarkSelectableListLayout = this.k) == null) {
            return false;
        }
        if (C()) {
            throw null;
        }
        return bookmarkSelectableListLayout.a();
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        boolean zE = this.m.e();
        Iterator<HistoryItemView> it = this.l.o.k.iterator();
        while (it.hasNext()) {
            it.next().setRemoveButtonVisible(!zE);
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.r;
    }

    @Override // dv0.a
    public final void h(boolean z) {
        M();
        boolean z2 = this.l.s;
        this.n.getClass();
        if (z) {
            K("LoadMoreOnScroll");
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
    public final void k() {
        org.chromium.chrome.browser.history.a aVar = this.l.o;
        aVar.x = "";
        aVar.t = false;
        aVar.U();
        BookmarkSelectableListLayout<ev0> bookmarkSelectableListLayout = this.k;
        bookmarkSelectableListLayout.m.setItemAnimator(bookmarkSelectableListLayout.n);
        bookmarkSelectableListLayout.r();
        bookmarkSelectableListLayout.j.setVisibility(8);
        bookmarkSelectableListLayout.i.setText(bookmarkSelectableListLayout.q);
        bookmarkSelectableListLayout.k();
        this.u = false;
    }

    @Override // dv0.a
    public final void l() {
        I("ClearBrowsingData");
        oj2.U(this.f ? 1 : 0, 6, "Android.HistoryPage.ClearBrowsingData.PerProfileType");
        String name = ClearBrowsingDataTabsFragment.class.getName();
        Intent intent = new Intent();
        Activity activity = this.e;
        intent.setClass(activity, SettingsActivity.class);
        if (!(activity instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        ComponentName componentName = q11.a;
        try {
            activity.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    @SuppressLint({"StringFormatMatches"})
    public final boolean onMenuItemClick(MenuItem menuItem) {
        HistoryManagerToolbar historyManagerToolbar = this.n;
        historyManagerToolbar.n();
        int itemId = menuItem.getItemId();
        int i = R$id.close_menu_id;
        Activity activity = this.e;
        if (itemId == i && this.g) {
            activity.finish();
            return true;
        }
        int itemId2 = menuItem.getItemId();
        int i2 = R$id.selection_mode_open_in_new_tab;
        a<ev0> aVar = this.m;
        if (itemId2 == i2) {
            H(aVar.c(), false);
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_mode_copy_link) {
            K("CopyLink");
            Clipboard.getInstance().setText(((ev0) aVar.c().get(0)).c.j());
            aVar.b();
            this.o.c(tp2.a(activity.getString(R$string.copied), this, 1, 17));
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_mode_open_in_incognito) {
            H(aVar.c(), true);
            return true;
        }
        int itemId3 = menuItem.getItemId();
        int i3 = R$id.selection_mode_delete_menu_id;
        dv0 dv0Var = this.l;
        if (itemId3 == i3) {
            K("RemoveSelected");
            Iterator<ev0> it = aVar.c.iterator();
            ev0 next = null;
            int i4 = 0;
            while (it.hasNext()) {
                next = it.next();
                dv0Var.o.Q(next);
                i4++;
            }
            ((BrowsingHistoryBridge) dv0Var.o.m).a();
            aVar.b();
            RecyclerView recyclerView = dv0Var.p;
            if (i4 == 1) {
                recyclerView.announceForAccessibility(dv0Var.h.getString(R$string.delete_message, next.d));
            } else if (i4 > 1) {
                recyclerView.announceForAccessibility(activity.getString(R$string.multiple_history_items_deleted, Integer.valueOf(i4)));
            }
            return true;
        }
        if (menuItem.getItemId() == R$id.search_menu_id) {
            dv0Var.o.N();
            historyManagerToolbar.F(true);
            v();
            this.k.q();
            I("Search");
            this.u = true;
            return true;
        }
        if (menuItem.getItemId() == R$id.info_menu_id) {
            boolean z = !dv0Var.s;
            SharedPreferencesManager.getInstance().l("history_home_show_info", z);
            M();
            historyManagerToolbar.getClass();
            dv0Var.s = z;
            dv0Var.o.T();
            this.p.r(Boolean.valueOf(z));
        } else {
            int itemId4 = menuItem.getItemId();
            int i5 = R$id.optout_menu_id;
            if (itemId4 == i5) {
                PrefService prefService = this.s;
                boolean z2 = !prefService.a("history_clusters.visible");
                prefService.e("history_clusters.visible", z2);
                aq1<Boolean> aq1Var = this.h;
                if (z2) {
                    historyManagerToolbar.getMenu().findItem(i5).setTitle(R$string.history_clusters_disable_menu_item_label);
                    aq1Var.r(Boolean.TRUE);
                } else {
                    historyManagerToolbar.getMenu().findItem(i5).setTitle(R$string.history_clusters_enable_menu_item_label);
                    aq1Var.r(Boolean.FALSE);
                }
                return true;
            }
        }
        return false;
    }

    @Override // dv0.a
    public final void q() {
        K("OpenItem");
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
    public final void r(String str) {
        org.chromium.chrome.browser.history.a aVar = this.l.o;
        aVar.x = str;
        aVar.t = true;
        aVar.v = true;
        BrowsingHistoryBridge browsingHistoryBridge = (BrowsingHistoryBridge) aVar.m;
        N.ML$TCyGp(browsingHistoryBridge.b, browsingHistoryBridge, new ArrayList(), str, false);
    }

    @Override // dv0.a
    public final void s() {
        this.q.r(Boolean.valueOf(this.l.m && gq2.l("history.deleting_enabled")));
    }

    public void setOnSelectListener(js1 js1Var) {
        HistoryManagerToolbar historyManagerToolbar = this.n;
        if (historyManagerToolbar != null) {
            historyManagerToolbar.setOnSelectListener(js1Var);
        }
    }

    @Override // dv0.a
    public final void t(ev0 ev0Var) {
        K("RemoveItem");
        a<ev0> aVar = this.m;
        if (aVar.d(ev0Var)) {
            aVar.i(ev0Var);
        }
    }

    @Override // dv0.a
    public final void u() {
        M();
        dv0 dv0Var = this.l;
        boolean z = dv0Var.s;
        this.n.getClass();
        this.p.r(Boolean.valueOf(dv0Var.s));
    }

    @SuppressLint({"StringFormatInvalid"})
    public final String v() {
        TemplateUrl templateUrlC = oj2.q(this.t).c();
        String strC = templateUrlC != null ? templateUrlC.c() : null;
        Activity activity = this.e;
        return strC == null ? activity.getString(R$string.history_manager_no_results_no_dse) : activity.getString(R$string.no_search_result, strC);
    }

    @Override // dv0.a
    public final void e() {
    }

    @Override // wp2.b
    public final void d(Object obj) {
    }

    @Override // wp2.b
    public final void j(Object obj) {
    }
}
