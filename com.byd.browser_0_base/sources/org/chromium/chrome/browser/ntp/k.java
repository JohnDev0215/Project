package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import defpackage.ao3;
import defpackage.fj;
import defpackage.gj2;
import defpackage.jk1;
import defpackage.kj;
import defpackage.p21;
import defpackage.wb2;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.j;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: RecentTabsPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k implements jk1, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupCollapseListener, ExpandableListView.OnGroupExpandListener, j.a, View.OnAttachStateChangeListener, View.OnCreateContextMenuListener, p21, kj.a {
    public final kj e;
    public final ExpandableListView f;
    public final String g;
    public final ViewGroup h;
    public j i;
    public n j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o;

    public k(Activity activity, j jVar, fj fjVar) {
        this.i = jVar;
        this.g = activity.getResources().getString(R$string.recent_tabs);
        this.i.r = this;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(activity).inflate(R$layout.recent_tabs_page, (ViewGroup) null);
        this.h = viewGroup;
        ExpandableListView expandableListView = (ExpandableListView) viewGroup.findViewById(R$id.odp_listview);
        this.f = expandableListView;
        n nVar = new n(activity, jVar);
        this.j = nVar;
        expandableListView.setAdapter(nVar);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupCollapseListener(this);
        expandableListView.setOnGroupExpandListener(this);
        expandableListView.setOnCreateContextMenuListener(this);
        viewGroup.addOnAttachStateChangeListener(this);
        if (DeviceFormFactor.a(activity)) {
            this.e = null;
        } else {
            this.e = fjVar;
            fjVar.o(this);
            s();
        }
        b();
    }

    public final void b() {
        this.j.notifyDataSetChanged();
        for (int i = 0; i < this.j.getGroupCount(); i++) {
            boolean zI = this.j.getGroup(i).i();
            ExpandableListView expandableListView = this.f;
            if (zI) {
                expandableListView.collapseGroup(i);
            } else {
                expandableListView.expandGroup(i);
            }
        }
        this.k = true;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        s();
    }

    @Override // defpackage.jk1
    public final void destroy() {
        j jVar = this.i;
        jVar.s = true;
        j.d(jVar.v, "Tab");
        j.d(jVar.w, "Group");
        j.d(jVar.x, "Bulk");
        jVar.q.n(jVar);
        jVar.q = null;
        jVar.t.e(jVar);
        AccountManagerFacadeProvider.getInstance().j(jVar);
        FaviconHelper faviconHelper = jVar.k;
        N.Mz5mgjYL(faviconHelper.a);
        faviconHelper.a = 0L;
        jVar.k = null;
        RecentlyClosedBridge recentlyClosedBridge = jVar.p;
        N.MN6LZLAP(recentlyClosedBridge.a);
        recentlyClosedBridge.a = 0L;
        recentlyClosedBridge.c = null;
        jVar.p = null;
        jVar.r = null;
        l lVar = jVar.o;
        N.M79sPWt6(lVar.a);
        lVar.a = 0L;
        jVar.o = null;
        Object obj = ThreadUtils.a;
        if (gj2.i == null) {
            gj2.i = new gj2(jVar.e, new wb2());
        }
        gj2 gj2Var = gj2.i;
        int i = gj2Var.h - 1;
        gj2Var.h = i;
        if (i == 0) {
            gj2Var.a(0L, false);
        }
        ForeignSessionHelper foreignSessionHelper = jVar.l;
        N.MHB2z4$M(foreignSessionHelper.a);
        foreignSessionHelper.a = 0L;
        jVar.l = null;
        this.i = null;
        this.j.notifyDataSetInvalidated();
        this.j = null;
        this.f.setAdapter((ExpandableListAdapter) null);
        this.h.removeOnAttachStateChangeListener(this);
        kj kjVar = this.e;
        if (kjVar != null) {
            kjVar.s(this);
        }
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.g;
    }

    @Override // defpackage.jk1
    public final String getUrl() {
        return "chrome-native://recent-tabs/";
    }

    @Override // defpackage.jk1
    public final View getView() {
        return this.h;
    }

    @Override // defpackage.p21
    public final boolean i() {
        ViewGroup viewGroup = this.h;
        if (viewGroup.getWidth() == 0 || viewGroup.getHeight() == 0) {
            return false;
        }
        ExpandableListView expandableListView = this.f;
        View childAt = expandableListView.getChildAt(0);
        if (!this.k && this.l == expandableListView.getFirstVisiblePosition()) {
            if (this.m == (childAt == null ? 0 : childAt.getTop()) && viewGroup.getWidth() == this.n && viewGroup.getHeight() == this.o) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.jk1
    public final String k() {
        return "recent-tabs";
    }

    @Override // kj.a
    public final void l(int i) {
        s();
    }

    @Override // defpackage.p21
    public final void m(Canvas canvas) {
        ViewGroup viewGroup = this.h;
        ao3.d(viewGroup);
        viewGroup.draw(canvas);
        this.k = false;
        ExpandableListView expandableListView = this.f;
        this.l = expandableListView.getFirstVisiblePosition();
        View childAt = expandableListView.getChildAt(0);
        this.m = childAt != null ? childAt.getTop() : 0;
        this.n = viewGroup.getWidth();
        this.o = viewGroup.getHeight();
    }

    @Override // kj.a
    public final void n() {
        s();
    }

    @Override // android.widget.ExpandableListView.OnChildClickListener
    public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        return this.j.getGroup(i).j(i2);
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        ExpandableListView.ExpandableListContextMenuInfo expandableListContextMenuInfo = (ExpandableListView.ExpandableListContextMenuInfo) contextMenuInfo;
        int packedPositionType = ExpandableListView.getPackedPositionType(expandableListContextMenuInfo.packedPosition);
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListContextMenuInfo.packedPosition);
        if (packedPositionType == 0) {
            this.j.getGroup(packedPositionGroup).l(contextMenu);
        } else if (packedPositionType == 1) {
            this.j.getGroup(packedPositionGroup).k(ExpandableListView.getPackedPositionChild(expandableListContextMenuInfo.packedPosition), contextMenu);
        }
    }

    @Override // android.widget.ExpandableListView.OnGroupCollapseListener
    public final void onGroupCollapse(int i) {
        this.j.getGroup(i).m(true);
        this.k = true;
    }

    @Override // android.widget.ExpandableListView.OnGroupExpandListener
    public final void onGroupExpand(int i) {
        this.j.getGroup(i).m(false);
        this.k = true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        ao3.e(view.getRootView(), "RecentTabsPage.onViewAttachedToWindow");
    }

    @Override // defpackage.jk1
    public final int r() {
        return -1;
    }

    public final void s() {
        View viewFindViewById = this.h.findViewById(R$id.recent_tabs_root);
        kj kjVar = this.e;
        int iL = kjVar.l();
        int iF = kjVar.f();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams();
        int i = marginLayoutParams.topMargin;
        if (iF < iL) {
            iL = i;
        }
        viewFindViewById.setTranslationY(iF - iL);
        int iG = kjVar.g();
        if (iL == marginLayoutParams.topMargin && iG == marginLayoutParams.bottomMargin) {
            return;
        }
        marginLayoutParams.topMargin = iL;
        marginLayoutParams.bottomMargin = iG;
        viewFindViewById.setLayoutParams(marginLayoutParams);
    }

    @Override // defpackage.jk1
    public final void e(String str) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
