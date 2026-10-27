package defpackage;

import J.N;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.gg;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.bookmarks.BookmarkSelectableListLayout;
import org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: BookmarkManagerCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class eg implements SelectableListToolbar.b, vb {
    public final aq1<Boolean> e;
    public final LinearLayout f;
    public final BookmarkSelectableListLayout<BookmarkId> g;
    public final ch h;
    public final gg i;
    public final ax0 j;
    public final wp2 k;
    public final pg l;
    public final BookmarkModel m;
    public final Profile n;
    public final a o;
    public ViewGroup p;

    /* JADX INFO: compiled from: BookmarkManagerCoordinator.java */
    public class a extends org.chromium.components.browser_ui.widget.selectable_list.a<BookmarkId> {
        public a() {
        }

        @Override // org.chromium.components.browser_ui.widget.selectable_list.a
        public final boolean i(BookmarkId bookmarkId) {
            BookmarkId bookmarkId2 = bookmarkId;
            eg egVar = eg.this;
            if (egVar.m.h(bookmarkId2) == null || egVar.m.h(bookmarkId2).a()) {
                return super.i(bookmarkId2);
            }
            return false;
        }
    }

    public eg(Context context, ComponentName componentName, boolean z, boolean z2, wp2 wp2Var, Profile profile) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.e = aq1Var;
        this.i = null;
        this.n = profile;
        this.j = dx0.c(profile.g(), ys0.a);
        this.k = wp2Var;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R$layout.bookmark_main, (ViewGroup) null);
        this.f = linearLayout;
        BookmarkModel bookmarkModelV = BookmarkModel.v(profile);
        this.m = bookmarkModelV;
        ng ngVar = new ng(bookmarkModelV, context, componentName);
        if (oj2.B()) {
            ShoppingService shoppingServiceY = n3.Y(profile);
            long j = shoppingServiceY.a;
            if (j != 0) {
                N.M4h0NgEA(j, shoppingServiceY);
            }
        }
        a aVar = new a();
        this.o = aVar;
        BookmarkSelectableListLayout<BookmarkId> bookmarkSelectableListLayout = (BookmarkSelectableListLayout) linearLayout.findViewById(R$id.selectable_list);
        this.g = bookmarkSelectableListLayout;
        bookmarkSelectableListLayout.setEmptyViewText(R$string.bookmarks_folder_empty);
        bookmarkSelectableListLayout.k.setOnTouchListener(new ah());
        xf xfVar = new xf(context, new nf(this), new c01(5, this));
        RecyclerView recyclerViewH = bookmarkSelectableListLayout.h(xfVar, null);
        rs1 rs1Var = new rs1();
        this.h = new ch(bookmarkSelectableListLayout, aVar, this, xfVar, z, rs1Var, bookmarkModelV, ngVar, new je0(3, this));
        LargeIconBridge largeIconBridge = new LargeIconBridge(profile);
        largeIconBridge.c = new org.chromium.components.favicon.a(Math.min((((ActivityManager) xz.a.getSystemService("activity")).getMemoryClass() / 4) * 1048576, 10485760));
        gg ggVar = new gg(context, bookmarkModelV, ngVar, bookmarkSelectableListLayout, aVar, recyclerViewH, xfVar, largeIconBridge, z2, aq1Var, profile, new org.chromium.chrome.browser.bookmarks.a(context, bookmarkModelV, wp2Var));
        this.i = ggVar;
        this.l = ggVar.u;
        rs1Var.a(ggVar);
        n3.X0("MobileBookmarkManagerOpen");
        if (!z) {
            n3.X0("MobileBookmarkManagerPageOpen");
        }
        LayoutInflater layoutInflater = (LayoutInflater) linearLayout.getContext().getSystemService("layout_inflater");
        linearLayout.getResources().getConfiguration();
        e(layoutInflater, 8);
    }

    public static View d(int i, RecyclerView recyclerView) {
        return LayoutInflater.from(recyclerView.getContext()).inflate(i, (ViewGroup) recyclerView, false);
    }

    public final boolean a() {
        gg ggVar = this.i;
        if (!ggVar.w) {
            if (!ggVar.l.a()) {
                gg.b bVar = ggVar.b;
                if (!bVar.empty()) {
                    bVar.pop();
                    if (!bVar.empty()) {
                        ggVar.A((gh) bVar.pop());
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final void e(LayoutInflater layoutInflater, int i) {
        ViewGroup viewGroup = this.p;
        LinearLayout linearLayout = this.f;
        if (viewGroup != null) {
            linearLayout.removeView(viewGroup);
        }
        Activity activityA = xz.a(linearLayout.getContext());
        boolean z = activityA == null || vh3.f(activityA);
        BookmarkSelectableListLayout<BookmarkId> bookmarkSelectableListLayout = this.g;
        if (z) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) bookmarkSelectableListLayout.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = 0;
            layoutParams.weight = 85.0f;
            bookmarkSelectableListLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(0);
            this.p = (ViewGroup) layoutInflater.inflate(R$layout.bookmark_operate_horizontal, (ViewGroup) null, false);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
            layoutParams2.setMarginStart(vh3.a(12));
            layoutParams2.setMarginEnd(vh3.a(39));
            layoutParams2.weight = 15.0f;
            this.p.setVisibility(i);
            View viewFindViewById = this.p.findViewById(R$id.divider_start);
            View viewFindViewById2 = this.p.findViewById(R$id.divider_end);
            if (n3.Q0() || n3.O0()) {
                linearLayout.addView(this.p, -1, layoutParams2);
                viewFindViewById.setVisibility(0);
                viewFindViewById2.setVisibility(8);
            } else {
                linearLayout.addView(this.p, 0, layoutParams2);
                viewFindViewById.setVisibility(8);
                viewFindViewById2.setVisibility(0);
            }
        } else {
            linearLayout.setOrientation(1);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) bookmarkSelectableListLayout.getLayoutParams();
            layoutParams3.weight = 85.0f;
            layoutParams3.width = -1;
            layoutParams3.height = 0;
            bookmarkSelectableListLayout.setLayoutParams(layoutParams3);
            this.p = (ViewGroup) layoutInflater.inflate(R$layout.bookmark_operate_vertical, (ViewGroup) null, false);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams4.setMarginStart(vh3.a(100));
            layoutParams4.setMarginEnd(vh3.a(100));
            layoutParams4.topMargin = vh3.a(8);
            layoutParams4.weight = 15.0f;
            this.p.setVisibility(i);
            linearLayout.addView(this.p, layoutParams4);
        }
        TextView textView = (TextView) this.p.findViewById(R$id.tv_create_folder);
        TextView textView2 = (TextView) this.p.findViewById(R$id.tv_move_folder);
        TextView textView3 = (TextView) this.p.findViewById(R$id.tv_delete_page);
        int iA = vh3.a(32);
        Drawable drawable = textView.getCompoundDrawables()[1];
        drawable.setBounds(0, 0, iA, iA);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        Drawable drawable2 = textView2.getCompoundDrawables()[1];
        drawable2.setBounds(0, 0, iA, iA);
        textView2.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, drawable2, (Drawable) null, (Drawable) null);
        Drawable drawable3 = textView3.getCompoundDrawables()[1];
        drawable3.setBounds(0, 0, iA, iA);
        textView3.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, drawable3, (Drawable) null, (Drawable) null);
        vt vtVar = new vt(5, this);
        textView.setOnClickListener(vtVar);
        textView2.setOnClickListener(vtVar);
        textView3.setOnClickListener(vtVar);
    }

    public final void f() {
        n3.X0("MobileBookmarkManagerClose");
        this.g.l();
        gg ggVar = this.i;
        ggVar.w = true;
        ggVar.o.C(ggVar.c);
        ggVar.j.r(ggVar.a);
        ggVar.p.a();
        PartnerBookmarksReader.j.remove(ggVar);
        ggVar.v.n();
        Iterator<fh> it = ggVar.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((fh) aVar.next()).onDestroy();
            }
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.e;
    }

    public final void h(String str) {
        gg ggVar = this.i;
        BookmarkModel bookmarkModel = ggVar.j;
        if (bookmarkModel == null) {
            return;
        }
        if (!bookmarkModel.c) {
            ggVar.x = str;
            return;
        }
        gg.b bVar = ggVar.b;
        gh ghVar = (bVar.isEmpty() || bVar.peek().a != 3) ? null : (gh) bVar.pop();
        ggVar.A("chrome-native://bookmarks/folder//shopping".equals(str) ? new gh(2, "chrome-native://bookmarks/folder//shopping", BookmarkId.c) : gh.b(Uri.parse(str), bookmarkModel));
        if (ghVar != null) {
            ggVar.A(ghVar);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
    public final void k() {
        gg ggVar = this.i;
        BookmarkSelectableListLayout<BookmarkId> bookmarkSelectableListLayout = ggVar.l;
        bookmarkSelectableListLayout.m.setItemAnimator(bookmarkSelectableListLayout.n);
        bookmarkSelectableListLayout.r();
        bookmarkSelectableListLayout.j.setVisibility(8);
        bookmarkSelectableListLayout.i.setText(bookmarkSelectableListLayout.q);
        bookmarkSelectableListLayout.k();
        gg.b bVar = ggVar.b;
        if (!bVar.isEmpty()) {
            bVar.pop();
        }
        if (bVar.isEmpty()) {
            return;
        }
        ggVar.A((gh) bVar.pop());
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
    public final void r(String str) {
        this.i.x(str);
    }

    public void setOnSelectListener(js1 js1Var) {
        this.h.a.setOnSelectListener(js1Var);
    }
}
