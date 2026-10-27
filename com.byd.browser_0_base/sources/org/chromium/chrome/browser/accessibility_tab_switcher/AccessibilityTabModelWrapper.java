package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.google.android.material.tabs.TabLayout;
import defpackage.bz;
import defpackage.e23;
import defpackage.ii2;
import defpackage.y13;
import defpackage.z7;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.accessibility_tab_switcher.a;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityTabModelWrapper extends LinearLayout {
    public AccessibilityTabModelListView e;
    public View f;
    public TabLayout g;
    public TabLayout.g h;
    public TabLayout.g i;
    public ChromeImageView j;
    public ChromeImageView k;
    public ColorStateList l;
    public ColorStateList m;
    public ColorStateList n;
    public ColorStateList o;
    public y13 p;
    public final a q;
    public boolean r;

    public class a implements e23 {
        public a() {
        }

        @Override // defpackage.e23
        public final void b() {
            AccessibilityTabModelWrapper accessibilityTabModelWrapper = AccessibilityTabModelWrapper.this;
            accessibilityTabModelWrapper.getAdapter().notifyDataSetChanged();
            accessibilityTabModelWrapper.e();
        }

        @Override // defpackage.e23
        public final void i(Tab tab) {
            AccessibilityTabModelWrapper.this.getAdapter().notifyDataSetChanged();
        }
    }

    public AccessibilityTabModelWrapper(Context context) {
        super(context);
        this.q = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public org.chromium.chrome.browser.accessibility_tab_switcher.a getAdapter() {
        return (org.chromium.chrome.browser.accessibility_tab_switcher.a) this.e.getAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectedModel(boolean z) {
        y13 y13Var = this.p;
        if (y13Var == null || z == y13Var.r()) {
            return;
        }
        this.p.c();
        this.p.d(z);
        d();
        announceForAccessibility(getResources().getString(z ? R$string.accessibility_tab_switcher_incognito_stack_selected : R$string.accessibility_tab_switcher_standard_stack_selected));
    }

    public final void c(int i) {
        AccessibilityTabModelListItem accessibilityTabModelListItem;
        this.e.smoothScrollToPosition(z7.G(this.p.z(), i));
        org.chromium.chrome.browser.accessibility_tab_switcher.a adapter = getAdapter();
        if (adapter.j != -1 && (accessibilityTabModelListItem = adapter.k) != null) {
            accessibilityTabModelListItem.removeOnAttachStateChangeListener(adapter.l);
            adapter.k = null;
        }
        adapter.j = i;
    }

    public final void d() {
        y13 y13Var = this.p;
        if (y13Var == null) {
            return;
        }
        boolean zR = y13Var.r();
        e();
        if (zR) {
            setBackgroundColor(getContext().getColor(R$color.default_bg_color_dark));
            this.g.setSelectedTabIndicatorColor(this.o.getDefaultColor());
            this.j.setImageTintList(this.m);
            this.k.setImageTintList(this.o);
        } else {
            setBackgroundColor(ii2.a(getContext()));
            this.g.setSelectedTabIndicatorColor(this.n.getDefaultColor());
            this.j.setImageTintList(this.n);
            this.k.setImageTintList(this.l);
        }
        if (zR && !this.i.a()) {
            this.i.b();
        } else if (!zR && !this.h.a()) {
            this.h.b();
        }
        this.e.setContentDescription(zR ? getContext().getString(R$string.accessibility_tab_switcher_incognito_stack) : getContext().getString(R$string.accessibility_tab_switcher_standard_stack));
        org.chromium.chrome.browser.accessibility_tab_switcher.a adapter = getAdapter();
        TabModel tabModelG = this.p.g(zR);
        adapter.g = tabModelG;
        adapter.f = tabModelG.k();
        adapter.notifyDataSetChanged();
    }

    public final void e() {
        this.f.setVisibility(this.p.g(true).k().getCount() > 0 ? 0 : 8);
    }

    public TabLayout.g getIncognitoTabsButton() {
        return this.i;
    }

    public TabLayout.g getStandardTabsButton() {
        return this.h;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        this.p.m(this.q);
        this.r = true;
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.r = false;
        super.onDetachedFromWindow();
    }

    public void setTabModelSelector(y13 y13Var) {
        boolean z = this.r;
        a aVar = this.q;
        if (z) {
            this.p.l(aVar);
        }
        this.p = y13Var;
        if (this.r) {
            y13Var.m(aVar);
        }
        d();
    }

    public void setup(a.c cVar) {
        this.l = bz.b(getContext(), R$color.default_icon_color_tint_list);
        this.n = ColorStateList.valueOf(ii2.b(getContext()));
        this.m = bz.b(getContext(), R$color.white_alpha_70);
        this.o = bz.b(getContext(), R$color.default_icon_color_white_tint_list);
        ChromeImageView chromeImageView = new ChromeImageView(getContext());
        this.j = chromeImageView;
        chromeImageView.setImageResource(R$drawable.btn_normal_tabs);
        this.j.setScaleY(-1.0f);
        this.j.setContentDescription(getResources().getString(R$string.accessibility_tab_switcher_standard_stack));
        ChromeImageView chromeImageView2 = new ChromeImageView(getContext());
        this.k = chromeImageView2;
        chromeImageView2.setImageResource(R$drawable.incognito_simple);
        this.k.setScaleY(-1.0f);
        this.k.setContentDescription(getResources().getString(R$string.accessibility_tab_switcher_incognito_stack));
        setDividerDrawable(null);
        int i = R$id.list_view;
        ((ListView) findViewById(i)).setDivider(null);
        this.f = findViewById(R$id.tab_wrapper);
        TabLayout tabLayout = (TabLayout) findViewById(R$id.tab_layout);
        this.g = tabLayout;
        TabLayout.g gVarL = tabLayout.l();
        gVarL.f = this.j;
        gVarL.e();
        this.h = gVarL;
        this.g.a(gVarL);
        TabLayout.g gVarL2 = this.g.l();
        gVarL2.f = this.k;
        gVarL2.e();
        this.i = gVarL2;
        this.g.a(gVarL2);
        this.g.addOnTabSelectedListener((TabLayout.d) new b());
        this.e = (AccessibilityTabModelListView) findViewById(i);
        getAdapter().h = cVar;
    }

    public AccessibilityTabModelWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = new a();
    }

    public AccessibilityTabModelWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = new a();
    }

    public class b implements TabLayout.d {
        public b() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(TabLayout.g gVar) {
            AccessibilityTabModelWrapper accessibilityTabModelWrapper = AccessibilityTabModelWrapper.this;
            accessibilityTabModelWrapper.setSelectedModel(accessibilityTabModelWrapper.i.a());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(TabLayout.g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(TabLayout.g gVar) {
        }
    }
}
