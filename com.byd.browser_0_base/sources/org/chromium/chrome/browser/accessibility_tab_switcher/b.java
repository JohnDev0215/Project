package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.fj;
import defpackage.h61;
import defpackage.j61;
import defpackage.kj;
import defpackage.l23;
import defpackage.st1;
import defpackage.y13;
import defpackage.y51;
import defpackage.z7;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.accessibility_tab_switcher.a;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.compositor.layouts.eventfilter.BlackHoleEventFilter;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: OverviewListLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends y51 implements a.c, l23.a {
    public AccessibilityTabModelWrapper t;
    public final float u;
    public final BlackHoleEventFilter v;
    public final SceneLayer w;
    public final kj x;
    public final st1 y;

    public b(Context context, h61 h61Var, j61 j61Var, fj fjVar) {
        super(context, h61Var, j61Var);
        this.v = new BlackHoleEventFilter(context);
        this.u = context.getResources().getDisplayMetrics().density;
        this.w = new SceneLayer();
        this.x = fjVar;
        this.y = new st1(this);
    }

    @Override // defpackage.y51
    public final void A() {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null) {
            return;
        }
        accessibilityTabModelWrapper.d();
    }

    @Override // defpackage.y51
    public final void B(long j) {
        H(j, false);
    }

    @Override // defpackage.y51
    public final void C(boolean z) {
        if (z) {
            this.k.d(!z);
        }
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null) {
            return;
        }
        accessibilityTabModelWrapper.d();
    }

    @Override // defpackage.y51
    public final void G(y13 y13Var, TabContentManager tabContentManager) {
        super.G(y13Var, tabContentManager);
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null) {
            return;
        }
        accessibilityTabModelWrapper.setTabModelSelector(y13Var);
    }

    @Override // defpackage.y51
    public final void H(long j, boolean z) {
        super.H(j, z);
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null) {
            return;
        }
        accessibilityTabModelWrapper.d();
        g();
        this.x.o(this.y);
        P();
        Tab tabP = z7.p(this.k.z());
        this.t.c(tabP == null ? -1 : tabP.getId());
    }

    @Override // defpackage.y51
    public final void I(int i, boolean z) {
        this.x.s(this.y);
        super.I(i, z);
        f();
    }

    public final void P() {
        FrameLayout.LayoutParams layoutParams;
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null || (layoutParams = (FrameLayout.LayoutParams) accessibilityTabModelWrapper.getLayoutParams()) == null) {
            return;
        }
        layoutParams.bottomMargin = (int) (this.h * this.u);
        layoutParams.topMargin = this.x.f();
        this.t.setLayoutParams(layoutParams);
    }

    @Override // defpackage.y51
    public final void a(ViewGroup viewGroup) {
        if (this.t == null) {
            AccessibilityTabModelWrapper accessibilityTabModelWrapper = (AccessibilityTabModelWrapper) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.accessibility_tab_switcher, (ViewGroup) null);
            this.t = accessibilityTabModelWrapper;
            accessibilityTabModelWrapper.setup(this);
            this.t.setTabModelSelector(this.k);
            P();
        }
        if (viewGroup == null || this.t.getParent() != null) {
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R$id.overview_list_layout_holder);
        viewGroup2.setVisibility(0);
        viewGroup2.addView(this.t);
    }

    @Override // defpackage.y51
    public final boolean b() {
        return DeviceFormFactor.a(this.i);
    }

    @Override // defpackage.y51
    public final void d() {
        kj kjVar = this.x;
        if (kjVar != null) {
            kjVar.s(this.y);
        }
    }

    @Override // defpackage.y51
    public final void e() {
        ViewGroup viewGroup;
        y13 y13Var = this.k;
        if (y13Var != null) {
            y13Var.c();
        }
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null || (viewGroup = (ViewGroup) accessibilityTabModelWrapper.getParent()) == null) {
            return;
        }
        viewGroup.setVisibility(8);
        viewGroup.removeView(this.t);
    }

    @Override // defpackage.y51
    public final EventFilter k() {
        return this.v;
    }

    @Override // defpackage.y51
    public final int m() {
        return 2;
    }

    @Override // defpackage.y51
    public final SceneLayer n() {
        return this.w;
    }

    @Override // defpackage.y51
    public final int o() {
        return 0;
    }

    @Override // defpackage.y51
    public final boolean q() {
        return true;
    }

    @Override // l23.a
    public final void r(boolean z, boolean z2) {
        AccessibilityTabModelWrapper accessibilityTabModelWrapper = this.t;
        if (accessibilityTabModelWrapper == null) {
            return;
        }
        int i = !z ? 0 : 4;
        if (accessibilityTabModelWrapper.getImportantForAccessibility() != i) {
            this.t.setImportantForAccessibility(i);
            this.t.sendAccessibilityEvent(2048);
        }
    }

    @Override // defpackage.y51
    public final void s() {
        P();
    }

    @Override // defpackage.y51
    public final void x() {
        this.t.d();
    }

    @Override // defpackage.y51
    public final void y(int i, int i2, boolean z, boolean z2, float f, float f2) {
        I(i, false);
    }

    @Override // defpackage.y51
    public final void z(int i) {
        I(i, false);
    }
}
