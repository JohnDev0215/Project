package org.chromium.components.browser_ui.widget.selectable_list;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.aq1;
import defpackage.bi2;
import defpackage.db0;
import defpackage.in3;
import defpackage.om3;
import defpackage.qh3;
import defpackage.t41;
import defpackage.vb;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.chromium.components.browser_ui.widget.FadingShadowView;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$id;
import org.chromium.components.browser_ui.widget.R$layout;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class SelectableListLayout<E> extends FrameLayout implements db0, a.InterfaceC0128a<E>, vb {
    public static final /* synthetic */ int r = 0;
    public RecyclerView.e e;
    public ViewStub f;
    public TextView g;
    public View h;
    public LoadingView i;
    public RecyclerView j;
    public RecyclerView.j k;
    public SelectableListToolbar<E> l;
    public FadingShadowView m;
    public int n;
    public qh3 o;
    public final aq1<Boolean> p;
    public final a q;

    public class a extends RecyclerView.g {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void a() {
            SelectableListLayout selectableListLayout = SelectableListLayout.this;
            SelectableListLayout.e(selectableListLayout);
            selectableListLayout.i.c();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void d(int i, int i2) {
            SelectableListLayout selectableListLayout = SelectableListLayout.this;
            SelectableListLayout.e(selectableListLayout);
            selectableListLayout.i.c();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void f(int i, int i2) {
            SelectableListLayout.e(SelectableListLayout.this);
        }
    }

    public SelectableListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = new aq1<>();
        this.q = new a();
        l();
    }

    public static void e(SelectableListLayout selectableListLayout) {
        int i = selectableListLayout.e.k() == 0 ? 0 : 8;
        selectableListLayout.g.setVisibility(i);
        selectableListLayout.h.setVisibility(i);
        if (selectableListLayout.e.k() == 0) {
            selectableListLayout.j.setVisibility(8);
        } else {
            selectableListLayout.j.setVisibility(0);
        }
        selectableListLayout.l.setSearchEnabled(selectableListLayout.e.k() != 0);
    }

    public static int h(qh3.b bVar, Resources resources) {
        if (bVar.a != 2) {
            return 0;
        }
        int i = resources.getConfiguration().screenWidthDp;
        return (int) Math.max(resources.getDisplayMetrics().density * 16.0f, (int) (((i - 600) / 2.0f) * r2));
    }

    public final boolean a() {
        org.chromium.components.browser_ui.widget.selectable_list.a<E> selectionDelegate = this.l.getSelectionDelegate();
        if (selectionDelegate.e()) {
            selectionDelegate.b();
            return true;
        }
        if (!this.l.A()) {
            return false;
        }
        this.l.x();
        return true;
    }

    @Override // defpackage.db0
    public final void d(qh3.b bVar) {
        int iH = h(bVar, getResources());
        RecyclerView recyclerView = this.j;
        int paddingTop = recyclerView.getPaddingTop();
        int paddingBottom = this.j.getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(recyclerView, iH, paddingTop, iH, paddingBottom);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        l();
        r();
    }

    @Override // defpackage.vb
    public zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.p;
    }

    public View getToolbarShadowForTests() {
        return this.m;
    }

    public qh3 getUiConfig() {
        return this.o;
    }

    public final RecyclerView j(RecyclerView.e eVar, RecyclerView recyclerView) {
        this.e = eVar;
        if (recyclerView == null) {
            RecyclerView recyclerView2 = (RecyclerView) findViewById(R$id.selectable_list_recycler_view);
            this.j = recyclerView2;
            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            this.j = recyclerView;
            FrameLayout frameLayout = (FrameLayout) findViewById(R$id.list_content);
            frameLayout.removeView((RecyclerView) frameLayout.findViewById(R$id.selectable_list_recycler_view));
            frameLayout.addView(this.j, 0);
        }
        this.j.setAdapter(this.e);
        this.e.A(this.q);
        this.j.setHasFixedSize(true);
        this.j.addOnScrollListener(new bi2(this));
        this.k = this.j.getItemAnimator();
        return this.j;
    }

    public final SelectableListToolbar<E> k(int i, org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar, int i2, int i3, int i4, Toolbar.OnMenuItemClickListener onMenuItemClickListener, boolean z) {
        this.f.setLayoutResource(i);
        SelectableListToolbar<E> selectableListToolbar = (SelectableListToolbar) this.f.inflate();
        this.l = selectableListToolbar;
        selectableListToolbar.y(aVar, i2, i3, i4, z);
        if (onMenuItemClickListener != null) {
            this.l.setOnMenuItemClickListener(onMenuItemClickListener);
        }
        FadingShadowView fadingShadowView = (FadingShadowView) findViewById(R$id.shadow);
        this.m = fadingShadowView;
        fadingShadowView.a(getContext().getColor(R$color.toolbar_shadow_color));
        aVar.a(this);
        r();
        return this.l;
    }

    public final void l() {
        SelectableListToolbar<E> selectableListToolbar = this.l;
        aq1<Boolean> aq1Var = this.p;
        if (selectableListToolbar == null) {
            aq1Var.r(Boolean.FALSE);
        } else {
            aq1Var.r(Boolean.valueOf(selectableListToolbar.getSelectionDelegate().e() || this.l.A()));
        }
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        qh3 qh3Var = this.o;
        if (qh3Var != null) {
            qh3Var.c();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R$layout.selectable_list_layout, this);
        this.g = (TextView) findViewById(R$id.empty_view);
        this.h = findViewById(R$id.empty_view_wrapper);
        LoadingView loadingView = (LoadingView) findViewById(R$id.loading_view);
        this.i = loadingView;
        loadingView.e();
        this.f = (ViewStub) findViewById(R$id.action_bar_stub);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public final void q() {
        this.e.C(this.q);
        this.l.getSelectionDelegate().g(this);
        SelectableListToolbar<E> selectableListToolbar = this.l;
        selectableListToolbar.y0 = true;
        org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar = selectableListToolbar.c0;
        if (aVar != null) {
            aVar.g(selectableListToolbar);
        }
        EditText editText = selectableListToolbar.g0;
        if (editText != null) {
            t41.f.e(editText);
        }
        this.i.b();
        this.j.setAdapter(null);
    }

    public final void r() {
        RecyclerView recyclerView;
        if (this.l == null || (recyclerView = this.j) == null) {
            return;
        }
        this.m.setVisibility(recyclerView.canScrollVertically(-1) ? 0 : 8);
    }

    public void setEmptyViewText(int i) {
        this.n = i;
        this.g.setText(i);
    }
}
