package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.LruCache;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bk3;
import defpackage.ii2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.qc2;
import defpackage.s92;
import defpackage.t92;
import defpackage.u92;
import defpackage.uc2;
import defpackage.v92;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.ForeignSessionHelper;
import org.chromium.chrome.browser.ntp.j;
import org.chromium.chrome.browser.ntp.n;
import org.chromium.chrome.browser.signin.LegacySyncPromoView;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.chrome.browser.ui.signin.PersonalizedSigninPromoView;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: RecentTabsRowAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n extends BaseExpandableListAdapter {
    public final Activity a;
    public final ArrayList b;
    public final FaviconHelper.a c;
    public final j d;
    public final f e = new f();
    public final g f = new g(true);
    public final g g = new g(false);
    public final ArrayMap h;
    public final int i;
    public boolean j;
    public final uc2 k;

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public static class a {
        public final LruCache<GURL, Drawable> a = new LruCache<>(128);
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public class b extends c {
        public final ForeignSessionHelper.ForeignSession b;

        public b(ForeignSessionHelper.ForeignSession foreignSession) {
            super();
            this.b = foreignSession;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void a(int i, i iVar) {
            ForeignSessionHelper.a aVarC = c(i);
            String strJ = aVarC.a.j();
            String str = aVarC.b;
            if (TextUtils.isEmpty(str)) {
                str = strJ;
            }
            iVar.a.setText(str);
            String strB = bk3.b(strJ, false);
            if (TextUtils.isEmpty(strB)) {
                iVar.b.setText("");
                iVar.b.setVisibility(8);
            } else {
                iVar.b.setText(strB);
                iVar.b.setVisibility(0);
            }
            n.a(n.this, iVar, aVarC.a, 1);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void b(RecentTabsGroupView recentTabsGroupView, boolean z) {
            recentTabsGroupView.b(this.b, z);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int d() {
            return 1;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int f() {
            Iterator it = this.b.d.iterator();
            int size = 0;
            while (it.hasNext()) {
                size += ((ForeignSessionHelper.ForeignSessionWindow) it.next()).a.size();
            }
            return size;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int g() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final boolean i() {
            return N.MF5D$8jU(n.this.d.o.a, this.b.a);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final boolean j(int i) {
            oj2.U(2, 11, "HistoryPage.OtherDevicesMenu");
            n.this.d.a(this.b, c(i), 1);
            return true;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void k(int i, ContextMenu contextMenu) {
            final ForeignSessionHelper.a aVarC = c(i);
            contextMenu.add(R$string.contextmenu_open_in_new_tab).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: q92
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    n.b bVar = this.e;
                    n.this.d.a(bVar.b, aVarC, 4);
                    return true;
                }
            });
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void l(ContextMenu contextMenu) {
            contextMenu.add(R$string.recent_tabs_open_all_menu_option).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: o92
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    n nVar;
                    n.b bVar = this.e;
                    bVar.getClass();
                    oj2.U(8, 11, "HistoryPage.OtherDevicesMenu");
                    ForeignSessionHelper.ForeignSession foreignSession = bVar.b;
                    Iterator it = foreignSession.d.iterator();
                    ForeignSessionHelper.a aVar = null;
                    while (true) {
                        boolean zHasNext = it.hasNext();
                        nVar = n.this;
                        if (!zHasNext) {
                            break;
                        }
                        for (ForeignSessionHelper.a aVar2 : ((ForeignSessionHelper.ForeignSessionWindow) it.next()).a) {
                            if (aVar == null) {
                                aVar = aVar2;
                            } else {
                                nVar.d.a(foreignSession, aVar2, 4);
                            }
                        }
                    }
                    if (aVar != null) {
                        nVar.d.a(foreignSession, aVar, 1);
                    }
                    return true;
                }
            });
            contextMenu.add(R$string.recent_tabs_hide_menu_option).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: p92
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    n.b bVar = this.e;
                    bVar.getClass();
                    oj2.U(10, 11, "HistoryPage.OtherDevicesMenu");
                    j jVar = n.this.d;
                    if (jVar.s) {
                        return true;
                    }
                    N.MKRVXtGV(jVar.l.a, bVar.b.a);
                    return true;
                }
            });
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void m(boolean z) {
            if (z) {
                oj2.U(6, 11, "HistoryPage.OtherDevicesMenu");
            } else {
                oj2.U(7, 11, "HistoryPage.OtherDevicesMenu");
            }
            j jVar = n.this.d;
            if (jVar.s) {
                return;
            }
            N.MTY3Z1W7(jVar.o.a, this.b.a, z);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public final ForeignSessionHelper.a c(int i) {
            for (ForeignSessionHelper.ForeignSessionWindow foreignSessionWindow : this.b.d) {
                int size = foreignSessionWindow.a.size();
                ArrayList arrayList = foreignSessionWindow.a;
                if (i < size) {
                    return (ForeignSessionHelper.a) arrayList.get(i);
                }
                i -= arrayList.size();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public class d extends e {
        public final int c;

        public d(int i) {
            super();
            this.c = i;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int d() {
            return this.c;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final View e(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.sync_promo_view_recent_tabs, viewGroup, false);
            }
            j jVar = n.this.d;
            PersonalizedSigninPromoView personalizedSigninPromoView = (PersonalizedSigninPromoView) view.findViewById(R$id.signin_promo_view_container);
            jVar.u.g(jVar.t, personalizedSigninPromoView, null);
            return view;
        }
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public abstract class e extends c {
        public e() {
            super();
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void b(RecentTabsGroupView recentTabsGroupView, boolean z) {
            recentTabsGroupView.c(z);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int f() {
            return 1;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int g() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final boolean i() {
            return N.MEv1xg6U(n.this.d.o.a);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void m(boolean z) {
            j jVar = n.this.d;
            if (jVar.s) {
                return;
            }
            N.MNaMvyV8(jVar.o.a, z);
        }
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public class h extends e {
        public h(n nVar) {
            super();
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int d() {
            return 4;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final View e(int i, View view, ViewGroup viewGroup) {
            return view == null ? LegacySyncPromoView.a(16, viewGroup) : view;
        }
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public static class i {
        public TextView a;
        public TextView b;
        public ImageView c;
        public View d;
        public m e;
    }

    public n(Activity activity, j jVar) {
        ArrayMap arrayMap = new ArrayMap(2);
        this.h = arrayMap;
        this.a = activity;
        this.d = jVar;
        this.b = new ArrayList();
        arrayMap.put(0, new a());
        arrayMap.put(1, new a());
        Resources resources = activity.getResources();
        this.c = new FaviconHelper.a();
        this.i = resources.getDimensionPixelSize(R$dimen.default_favicon_size);
        this.k = org.chromium.chrome.browser.ui.favicon.a.a(activity);
        oj2.U(0, 11, "HistoryPage.OtherDevicesMenu");
    }

    public static void a(n nVar, i iVar, GURL gurl, int i2) {
        Drawable bitmapDrawable;
        Activity activity = nVar.a;
        FaviconHelper.a aVar = nVar.c;
        if (gurl == null) {
            Resources resources = activity.getResources();
            aVar.getClass();
            bitmapDrawable = new BitmapDrawable(resources, aVar.b(resources, gurl, true));
        } else {
            Drawable drawable = ((a) nVar.h.get(Integer.valueOf(i2))).a.get(gurl);
            if (drawable == null) {
                m mVar = new m(nVar, iVar, gurl, i2);
                iVar.e = mVar;
                j jVar = nVar.d;
                if (i2 == 0) {
                    FaviconHelper faviconHelper = jVar.k;
                    faviconHelper.getClass();
                    faviconHelper.a(jVar.e, gurl.j(), nVar.i, mVar);
                } else if (i2 == 1) {
                    int i3 = nVar.i;
                    FaviconHelper faviconHelper2 = jVar.k;
                    N.MNHvfXi1(faviconHelper2.a, jVar.e, gurl, i3, mVar);
                }
                Resources resources2 = activity.getResources();
                aVar.getClass();
                bitmapDrawable = new BitmapDrawable(resources2, aVar.b(resources2, gurl, true));
            } else {
                bitmapDrawable = drawable;
            }
        }
        iVar.c.setImageDrawable(bitmapDrawable);
    }

    public final void b(c cVar) {
        boolean zA = DeviceFormFactor.a(this.a);
        ArrayList arrayList = this.b;
        if (!zA) {
            arrayList.add(cVar);
            return;
        }
        int size = arrayList.size();
        g gVar = this.g;
        if (size == 0) {
            arrayList.add(gVar);
        }
        arrayList.add(cVar);
        arrayList.add(gVar);
    }

    @Override // android.widget.ExpandableListAdapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final c getGroup(int i2) {
        return (c) this.b.get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public final Object getChild(int i2, int i3) {
        return getGroup(i2).c(i3);
    }

    @Override // android.widget.ExpandableListAdapter
    public final long getChildId(int i2, int i3) {
        return i3;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public final int getChildType(int i2, int i3) {
        return ((c) this.b.get(i2)).d();
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public final int getChildTypeCount() {
        return 5;
    }

    @Override // android.widget.ExpandableListAdapter
    public final View getChildView(int i2, int i3, boolean z, View view, ViewGroup viewGroup) {
        return getGroup(i2).e(i3, view, viewGroup);
    }

    @Override // android.widget.ExpandableListAdapter
    public final int getChildrenCount(int i2) {
        return getGroup(i2).f();
    }

    @Override // android.widget.ExpandableListAdapter
    public final int getGroupCount() {
        return this.b.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public final long getGroupId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public final int getGroupType(int i2) {
        return getGroup(i2).g();
    }

    @Override // android.widget.BaseExpandableListAdapter, android.widget.HeterogeneousExpandableList
    public final int getGroupTypeCount() {
        return 3;
    }

    @Override // android.widget.ExpandableListAdapter
    public final View getGroupView(int i2, boolean z, View view, ViewGroup viewGroup) {
        return getGroup(i2).h(z, view, viewGroup);
    }

    @Override // android.widget.ExpandableListAdapter
    public final boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public final boolean isChildSelectable(int i2, int i3) {
        return true;
    }

    @Override // android.widget.BaseExpandableListAdapter
    public final void notifyDataSetChanged() {
        ArrayList arrayList = this.b;
        arrayList.clear();
        f fVar = this.e;
        b(fVar);
        j jVar = this.d;
        for (ForeignSessionHelper.ForeignSession foreignSession : jVar.m) {
            if (!this.j) {
                oj2.U(9, 11, "HistoryPage.OtherDevicesMenu");
                this.j = true;
            }
            b(new b(foreignSession));
        }
        int i2 = jVar.j;
        if (i2 != 0) {
            if (i2 == 1) {
                b(new d(2));
            } else if (i2 == 2) {
                b(new d(3));
            } else if (i2 == 3) {
                b(new h(this));
            }
        }
        int iIndexOf = arrayList.indexOf(fVar);
        if (DeviceFormFactor.a(this.a) && iIndexOf != arrayList.size() - 2) {
            arrayList.set(iIndexOf + 1, this.f);
        }
        super.notifyDataSetChanged();
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public abstract class c {
        public c() {
        }

        public abstract void b(RecentTabsGroupView recentTabsGroupView, boolean z);

        public Object c(int i) {
            return null;
        }

        public abstract int d();

        public View e(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(n.this.a).inflate(R$layout.recent_tabs_list_item, viewGroup, false);
                i iVar = new i();
                iVar.a = (TextView) view.findViewById(R$id.title_row);
                iVar.b = (TextView) view.findViewById(R$id.domain_row);
                ImageView imageView = (ImageView) view.findViewById(R$id.recent_tabs_favicon);
                iVar.c = imageView;
                imageView.setBackgroundResource(R$drawable.list_item_icon_modern_bg);
                iVar.d = view.findViewById(R$id.recent_tabs_list_item_layout);
                view.setTag(iVar);
            }
            a(i, (i) view.getTag());
            return view;
        }

        public abstract int f();

        public abstract int g();

        public View h(boolean z, View view, ViewGroup viewGroup) {
            RecentTabsGroupView recentTabsGroupView = (RecentTabsGroupView) view;
            if (recentTabsGroupView == null) {
                recentTabsGroupView = (RecentTabsGroupView) LayoutInflater.from(n.this.a).inflate(R$layout.recent_tabs_group_item, viewGroup, false);
            }
            b(recentTabsGroupView, z);
            return recentTabsGroupView;
        }

        public abstract boolean i();

        public boolean j(int i) {
            return false;
        }

        public abstract void m(boolean z);

        public void l(ContextMenu contextMenu) {
        }

        public void a(int i, i iVar) {
        }

        public void k(int i, ContextMenu contextMenu) {
        }
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public class f extends c {
        public f() {
            super();
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void a(int i, i iVar) {
            int size;
            iVar.b.setText("");
            iVar.b.setVisibility(8);
            iVar.a.setContentDescription(null);
            n nVar = n.this;
            if (i == nVar.d.n.size()) {
                iVar.a.setText(R$string.show_full_history);
                Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(nVar.a.getResources(), R$drawable.ic_watch_later_24dp);
                Activity activity = nVar.a;
                int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R$dimen.tile_view_icon_size_modern);
                qc2 qc2VarC = org.chromium.chrome.browser.ui.favicon.a.c(activity.getResources(), Bitmap.createScaledBitmap(bitmapDecodeResource, dimensionPixelSize, dimensionPixelSize, true));
                qc2VarC.setColorFilter(ii2.g(activity, R$attr.colorOnSurface), PorterDuff.Mode.SRC_IN);
                iVar.c.setImageDrawable(qc2VarC);
                iVar.d.setMinimumHeight(activity.getResources().getDimensionPixelSize(R$dimen.recent_tabs_show_history_item_size));
                return;
            }
            iVar.d.setMinimumHeight(nVar.a.getResources().getDimensionPixelSize(R$dimen.recent_tabs_foreign_session_group_item_height));
            t92 t92VarC = c(i);
            if (t92VarC instanceof v92) {
                v92 v92Var = (v92) t92VarC;
                String str = v92Var.c;
                GURL gurl = v92Var.d;
                iVar.a.setText(n3.A0(str, gurl));
                String strB = bk3.b(gurl.j(), false);
                if (!TextUtils.isEmpty(strB)) {
                    iVar.b.setText(strB);
                    iVar.b.setVisibility(0);
                }
                n.a(nVar, iVar, gurl, 0);
                return;
            }
            boolean z = t92VarC instanceof u92;
            Activity activity2 = nVar.a;
            if (z) {
                u92 u92Var = (u92) t92VarC;
                size = u92Var.d.size();
                String str2 = u92Var.c;
                if (TextUtils.isEmpty(str2)) {
                    iVar.a.setText(activity2.getResources().getString(R$string.recent_tabs_group_closure_without_title, Integer.valueOf(size)));
                    iVar.a.setContentDescription(activity2.getResources().getString(R$string.recent_tabs_group_closure_without_title_accessibility, Integer.valueOf(size)));
                } else {
                    iVar.a.setText(activity2.getResources().getString(R$string.recent_tabs_group_closure_with_title, str2));
                    iVar.a.setContentDescription(activity2.getResources().getString(R$string.recent_tabs_group_closure_with_title_accessibility, str2));
                }
            } else {
                size = 0;
            }
            if (t92VarC instanceof s92) {
                size = ((s92) t92VarC).c.size();
                iVar.a.setText(activity2.getResources().getString(R$string.recent_tabs_bulk_closure, Integer.valueOf(size)));
                iVar.a.setContentDescription(activity2.getResources().getString(R$string.recent_tabs_bulk_closure_accessibility, Integer.valueOf(size)));
            }
            if (t92VarC.b.getTime() != 0) {
                iVar.b.setText(DateFormat.getDateInstance(1, activity2.getResources().getConfiguration().getLocales().get(0)).format(t92VarC.b));
                iVar.b.setVisibility(0);
            }
            RecentTabCountDrawable recentTabCountDrawable = new RecentTabCountDrawable(activity2);
            if (size != recentTabCountDrawable.h) {
                recentTabCountDrawable.h = size;
                recentTabCountDrawable.invalidateSelf();
            }
            iVar.c.setImageDrawable(recentTabCountDrawable);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void b(RecentTabsGroupView recentTabsGroupView, boolean z) {
            recentTabsGroupView.d(z);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int d() {
            return 1;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int f() {
            return n.this.d.n.size() + 1;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int g() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final boolean i() {
            return N.MvzD6qT5(n.this.d.o.a);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final boolean j(int i) {
            n nVar = n.this;
            if (i == nVar.d.n.size()) {
                j jVar = nVar.d;
                if (!jVar.s) {
                    jVar.h.run();
                }
                return true;
            }
            t92 t92VarC = c(i);
            if (t92VarC instanceof v92) {
                nVar.d.b((v92) t92VarC, 1);
                return true;
            }
            j jVar2 = nVar.d;
            if (!jVar2.s) {
                if (t92VarC instanceof u92) {
                    jVar2.w.put(Integer.valueOf(t92VarC.a), Boolean.TRUE);
                    n3.X0("MobileRecentTabManagerRecentGroupOpened");
                } else if (t92VarC instanceof s92) {
                    jVar2.x.put(Integer.valueOf(t92VarC.a), Boolean.TRUE);
                    n3.X0("MobileRecentTabManagerRecentBulkEventOpened");
                }
                RecentlyClosedBridge recentlyClosedBridge = jVar2.p;
                TabModel tabModelJ = jVar2.i;
                if (tabModelJ == null) {
                    tabModelJ = jVar2.g.j(jVar2.f.getId());
                    jVar2.i = tabModelJ;
                }
                N.MEC13_Hg(recentlyClosedBridge.a, tabModelJ, t92VarC.a);
            }
            return true;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void k(int i, ContextMenu contextMenu) {
            final t92 t92VarC = c(i);
            if (t92VarC == null) {
                return;
            }
            MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() { // from class: r92
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    n.f fVar = this.e;
                    fVar.getClass();
                    int itemId = menuItem.getItemId();
                    n nVar = n.this;
                    if (itemId == 1) {
                        nVar.d.b((v92) t92VarC, 4);
                    } else if (itemId == 2) {
                        j jVar = nVar.d;
                        if (!jVar.s) {
                            n3.X0("MobileRecentTabManagerRecentTabsCleared");
                            N.McFSdQa7(jVar.p.a);
                        }
                    }
                    return true;
                }
            };
            if (t92VarC instanceof v92) {
                contextMenu.add(0, 1, 0, R$string.contextmenu_open_in_new_tab).setOnMenuItemClickListener(onMenuItemClickListener);
            }
            contextMenu.add(0, 2, 0, R$string.remove_all).setOnMenuItemClickListener(onMenuItemClickListener);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void m(boolean z) {
            j jVar = n.this.d;
            if (jVar.s) {
                return;
            }
            N.MJ2SUJYd(jVar.o.a, z);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
        public final t92 c(int i) {
            n nVar = n.this;
            if (i == nVar.d.n.size()) {
                return null;
            }
            return (t92) nVar.d.n.get(i);
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void l(ContextMenu contextMenu) {
        }
    }

    /* JADX INFO: compiled from: RecentTabsRowAdapter.java */
    public class g extends c {
        public final boolean b;

        public g(boolean z) {
            super();
            this.b = z;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int d() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int f() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final int g() {
            return this.b ? 1 : 2;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final View h(boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                return LayoutInflater.from(n.this.a).inflate(this.b ? R$layout.recent_tabs_group_separator_visible : R$layout.recent_tabs_group_separator_invisible, viewGroup, false);
            }
            return view;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final boolean i() {
            return false;
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void m(boolean z) {
        }

        @Override // org.chromium.chrome.browser.ntp.n.c
        public final void b(RecentTabsGroupView recentTabsGroupView, boolean z) {
        }
    }
}
