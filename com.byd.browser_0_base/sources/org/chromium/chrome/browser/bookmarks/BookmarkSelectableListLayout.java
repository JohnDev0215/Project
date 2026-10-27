package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.aq1;
import defpackage.bh;
import defpackage.db0;
import defpackage.qh3;
import defpackage.vb;
import defpackage.vh3;
import defpackage.zp1;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkSelectableListLayout<E> extends FrameLayout implements db0, a.InterfaceC0128a<E>, vb {
    public static final /* synthetic */ int v = 0;
    public RecyclerView.e e;
    public ViewStub f;
    public View g;
    public ViewGroup h;
    public TextView i;
    public ImageView j;
    public View k;
    public LoadingView l;
    public RecyclerView m;
    public RecyclerView.j n;
    public BookMarkSelectableListToolbar<E> o;
    public FadingShadowView p;
    public int q;
    public int r;
    public boolean s;
    public final aq1<Boolean> t;
    public final a u;

    public class a extends RecyclerView.g {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void a() {
            BookmarkSelectableListLayout bookmarkSelectableListLayout = BookmarkSelectableListLayout.this;
            BookmarkSelectableListLayout.e(bookmarkSelectableListLayout);
            bookmarkSelectableListLayout.l.c();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void d(int i, int i2) {
            BookmarkSelectableListLayout bookmarkSelectableListLayout = BookmarkSelectableListLayout.this;
            BookmarkSelectableListLayout.e(bookmarkSelectableListLayout);
            bookmarkSelectableListLayout.l.c();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void f(int i, int i2) {
            BookmarkSelectableListLayout.e(BookmarkSelectableListLayout.this);
        }
    }

    public BookmarkSelectableListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        vh3.a(8);
        this.r = vh3.a(20);
        this.s = false;
        this.t = new aq1<>();
        this.u = new a();
        k();
    }

    public static void e(BookmarkSelectableListLayout bookmarkSelectableListLayout) {
        int i = bookmarkSelectableListLayout.e.k() == 0 ? 0 : 8;
        bookmarkSelectableListLayout.i.setVisibility(i);
        bookmarkSelectableListLayout.k.setVisibility(i);
        if (bookmarkSelectableListLayout.e.k() == 0) {
            bookmarkSelectableListLayout.m.setVisibility(8);
        } else {
            bookmarkSelectableListLayout.m.setVisibility(0);
        }
        bookmarkSelectableListLayout.o.setSearchEnabled(bookmarkSelectableListLayout.e.k() != 0);
    }

    public final boolean a() {
        org.chromium.components.browser_ui.widget.selectable_list.a<E> selectionDelegate = this.o.getSelectionDelegate();
        if (selectionDelegate.e()) {
            selectionDelegate.b();
            return true;
        }
        if (this.o.B() && hasFocus()) {
            this.o.y(true);
            return true;
        }
        if (this.s) {
            BookMarkSelectableListToolbar<E> bookMarkSelectableListToolbar = this.o;
            boolean z = (TextUtils.isEmpty(bookMarkSelectableListToolbar.g0.getText().toString()) || bookMarkSelectableListToolbar.g0.hasFocus()) ? false : true;
            if (z) {
                bookMarkSelectableListToolbar.g0.setText("");
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        k();
        r();
        vh3.a(!arrayList.isEmpty() ? 0 : this.r);
    }

    @Override // defpackage.vb
    public zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.t;
    }

    public int getPaddingStartItem() {
        return this.r;
    }

    public View getToolbarShadowForTests() {
        return this.p;
    }

    public qh3 getUiConfig() {
        return null;
    }

    public final RecyclerView h(RecyclerView.e eVar, RecyclerView recyclerView) {
        this.e = eVar;
        if (recyclerView == null) {
            RecyclerView recyclerView2 = (RecyclerView) findViewById(R$id.selectable_list_recycler_view);
            this.m = recyclerView2;
            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            this.m = recyclerView;
            this.h.removeView((RecyclerView) this.h.findViewById(R$id.selectable_list_recycler_view));
            this.h.addView(this.m, 0);
        }
        this.m.setAdapter(this.e);
        this.e.A(this.u);
        this.m.setHasFixedSize(true);
        this.m.addOnScrollListener(new bh(this));
        this.n = this.m.getItemAnimator();
        return this.m;
    }

    public final BookMarkSelectableListToolbar j(int i, org.chromium.components.browser_ui.widget.selectable_list.a aVar, int i2, int i3, Toolbar.OnMenuItemClickListener onMenuItemClickListener, boolean z) {
        this.f.setLayoutResource(i);
        BookMarkSelectableListToolbar<E> bookMarkSelectableListToolbar = (BookMarkSelectableListToolbar) this.f.inflate();
        this.o = bookMarkSelectableListToolbar;
        bookMarkSelectableListToolbar.z(aVar, 0, i2, i3, z);
        if (onMenuItemClickListener != null) {
            this.o.setOnMenuItemClickListener(onMenuItemClickListener);
        }
        FadingShadowView fadingShadowView = (FadingShadowView) findViewById(R$id.shadow);
        this.p = fadingShadowView;
        fadingShadowView.a(getContext().getColor(R$color.toolbar_shadow_color));
        aVar.a(this);
        r();
        return this.o;
    }

    public final void k() {
        BookMarkSelectableListToolbar<E> bookMarkSelectableListToolbar = this.o;
        aq1<Boolean> aq1Var = this.t;
        if (bookMarkSelectableListToolbar == null) {
            aq1Var.r(Boolean.FALSE);
        } else {
            aq1Var.r(Boolean.valueOf(bookMarkSelectableListToolbar.getSelectionDelegate().e() || (this.o.B() && hasFocus())));
        }
    }

    public final void l() {
        this.e.C(this.u);
        this.o.getSelectionDelegate().g(this);
        BookMarkSelectableListToolbar<E> bookMarkSelectableListToolbar = this.o;
        bookMarkSelectableListToolbar.r0 = true;
        org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar = bookMarkSelectableListToolbar.c0;
        if (aVar != null) {
            aVar.g(bookMarkSelectableListToolbar);
        }
        if (bookMarkSelectableListToolbar.g0 != null) {
            bookMarkSelectableListToolbar.x();
        }
        this.l.b();
        this.m.setAdapter(null);
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R$layout.selectable_list_layout, this);
        this.i = (TextView) findViewById(R$id.empty_view);
        this.j = (ImageView) findViewById(R$id.iv_search_hint);
        this.k = findViewById(R$id.empty_view_wrapper);
        LoadingView loadingView = (LoadingView) findViewById(R$id.loading_view);
        this.l = loadingView;
        loadingView.e();
        this.g = findViewById(R$id.action_bar_bg);
        this.h = (ViewGroup) findViewById(R$id.list_content);
        this.f = (ViewStub) findViewById(R$id.action_bar_stub);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public final void q() {
        this.m.setItemAnimator(null);
        this.p.setVisibility(0);
        this.j.setVisibility(0);
        this.i.setText(getResources().getString(R$string.no_search_result));
        k();
    }

    public final void r() {
        RecyclerView recyclerView;
        if (this.o == null || (recyclerView = this.m) == null) {
            return;
        }
        this.p.setVisibility(recyclerView.canScrollVertically(-1) ? 0 : 8);
    }

    public void setEmptyViewText(int i) {
        this.q = i;
        this.j.setVisibility(8);
        this.i.setText(this.q);
    }

    public void setPaddingStartItem(int i) {
        this.r = i;
    }

    public void setSelect(boolean z) {
        this.s = z;
    }

    @Override // defpackage.db0
    public final void d(qh3.b bVar) {
    }
}
