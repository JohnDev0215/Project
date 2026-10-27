package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import com.google.android.material.tabs.TabLayout;
import defpackage.bz;
import defpackage.e23;
import defpackage.oj2;
import defpackage.w43;
import defpackage.y13;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.a;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
public class IncognitoToggleTabLayout extends TabLayout implements a.InterfaceC0115a {
    public static final /* synthetic */ int m0 = 0;
    public final TabLayout.g a0;
    public final TabLayout.g b0;
    public final ChromeImageView c0;
    public final ChromeImageView d0;
    public final w43 e0;
    public final ColorStateList f0;
    public final ColorStateList g0;
    public final ColorStateList h0;
    public final ColorStateList i0;
    public y13 j0;
    public org.chromium.chrome.browser.toolbar.a k0;
    public b l0;

    public class b implements e23 {
        public b() {
        }

        @Override // defpackage.e23
        public final void g() {
            int i = IncognitoToggleTabLayout.m0;
            IncognitoToggleTabLayout incognitoToggleTabLayout = IncognitoToggleTabLayout.this;
            incognitoToggleTabLayout.e0.e(incognitoToggleTabLayout.j0.u().d(false).e.getCount(), false);
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            int i = IncognitoToggleTabLayout.m0;
            IncognitoToggleTabLayout.this.w();
        }
    }

    public IncognitoToggleTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f0 = bz.b(getContext(), R$color.default_icon_color_tint_list);
        this.h0 = bz.b(getContext(), R$color.default_icon_color_accent1_tint_list);
        this.g0 = bz.b(getContext(), R$color.white_alpha_70);
        this.i0 = bz.b(getContext(), R$color.default_control_color_active_dark);
        ChromeImageView chromeImageView = new ChromeImageView(getContext());
        this.c0 = chromeImageView;
        w43 w43VarD = w43.d(getContext(), 3);
        this.e0 = w43VarD;
        chromeImageView.setImageDrawable(w43VarD);
        chromeImageView.setContentDescription(getResources().getString(R$string.accessibility_tab_switcher_standard_stack));
        ChromeImageView chromeImageView2 = new ChromeImageView(getContext());
        this.d0 = chromeImageView2;
        chromeImageView2.setImageResource(R$drawable.incognito_small);
        chromeImageView2.setContentDescription(getResources().getString(R$string.accessibility_tab_switcher_incognito_stack));
        TabLayout.g gVarL = l();
        gVarL.f = chromeImageView;
        gVarL.e();
        this.a0 = gVarL;
        a(gVarL);
        TabLayout.g gVarL2 = l();
        gVarL2.f = chromeImageView2;
        gVarL2.e();
        this.b0 = gVarL2;
        a(gVarL2);
        addOnTabSelectedListener((TabLayout.d) new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectedModel(boolean z) {
        y13 y13Var = this.j0;
        if (y13Var == null || z == y13Var.r()) {
            return;
        }
        this.j0.c();
        this.j0.d(z);
        if (z) {
            oj2.O("Android.TabSwitcher.IncognitoClickedIsEmpty", this.k0.e == 0);
        }
        announceForAccessibility(getResources().getString(z ? R$string.accessibility_tab_switcher_incognito_stack_selected : R$string.accessibility_tab_switcher_standard_stack_selected));
    }

    @Override // org.chromium.chrome.browser.toolbar.a.InterfaceC0115a
    public final void c(int i, boolean z) {
        if (z) {
            return;
        }
        this.e0.e(i, z);
    }

    public void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
        this.k0 = aVar;
        aVar.a(this);
    }

    public void setTabModelSelector(y13 y13Var) {
        this.j0 = y13Var;
        if (y13Var == null) {
            return;
        }
        this.l0 = new b();
        w();
        if (this.j0.t()) {
            this.e0.e(this.j0.u().d(false).e.getCount(), false);
        }
    }

    public final void w() {
        y13 y13Var = this.j0;
        if (y13Var == null) {
            return;
        }
        boolean zR = y13Var.r();
        ColorStateList colorStateList = this.i0;
        ColorStateList colorStateList2 = this.h0;
        setSelectedTabIndicatorColor(zR ? colorStateList.getDefaultColor() : colorStateList2.getDefaultColor());
        ColorStateList colorStateList3 = this.g0;
        this.e0.c(zR ? colorStateList3 : colorStateList2);
        ChromeImageView chromeImageView = this.c0;
        if (zR) {
            colorStateList2 = colorStateList3;
        }
        chromeImageView.setImageTintList(colorStateList2);
        ChromeImageView chromeImageView2 = this.d0;
        if (!zR) {
            colorStateList = this.f0;
        }
        chromeImageView2.setImageTintList(colorStateList);
        if (zR) {
            TabLayout.g gVar = this.b0;
            if (!gVar.a()) {
                gVar.b();
                return;
            }
        }
        if (zR) {
            return;
        }
        TabLayout.g gVar2 = this.a0;
        if (gVar2.a()) {
            return;
        }
        gVar2.b();
    }

    public class a implements TabLayout.d {
        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(TabLayout.g gVar) {
            IncognitoToggleTabLayout incognitoToggleTabLayout = IncognitoToggleTabLayout.this;
            incognitoToggleTabLayout.setSelectedModel(incognitoToggleTabLayout.b0.a());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(TabLayout.g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(TabLayout.g gVar) {
        }
    }
}
