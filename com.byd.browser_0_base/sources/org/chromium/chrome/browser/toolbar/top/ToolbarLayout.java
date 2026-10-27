package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import defpackage.ad3;
import defpackage.af1;
import defpackage.ak;
import defpackage.ao3;
import defpackage.bc3;
import defpackage.bz;
import defpackage.f52;
import defpackage.gm;
import defpackage.ii2;
import defpackage.km1;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.pd;
import defpackage.pd3;
import defpackage.qh1;
import defpackage.rb2;
import defpackage.rj3;
import defpackage.s20;
import defpackage.td3;
import defpackage.u80;
import defpackage.v91;
import defpackage.vj;
import defpackage.vr1;
import defpackage.w80;
import defpackage.xe3;
import defpackage.y13;
import defpackage.yp;
import defpackage.z6;
import defpackage.z7;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.chrome.browser.toolbar.top.m;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ToolbarLayout extends FrameLayout implements w80, ThemeColorProvider.b, ThemeColorProvider.a, vr1 {
    public Callback<Runnable> e;
    public m.b f;
    public final org.chromium.base.b<m.c> g;
    public final int[] h;
    public final ColorStateList i;
    public bc3 j;
    public ad3 k;
    public ToolbarProgressBar l;
    public BooleanSupplier m;
    public boolean n;
    public boolean o;
    public long p;
    public boolean q;
    public ThemeColorProvider r;
    public af1 s;
    public z6 t;
    public pd3 u;
    public final u80 v;

    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ToolbarLayout toolbarLayout = ToolbarLayout.this;
            if (toolbarLayout.n && toolbarLayout.l.getParent() != null) {
                toolbarLayout.l.d();
            }
            toolbarLayout.removeOnLayoutChangeListener(this);
        }
    }

    public class b implements bc3 {
        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final int a() {
            return 0;
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final rj3 b() {
            return rj3.h;
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final int c(boolean z) {
            return 0;
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final Tab d() {
            return null;
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final boolean e() {
            return false;
        }

        @Override // defpackage.bc3
        public final Profile f() {
            return null;
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final km1 g() {
            return km1.a;
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final boolean h() {
            return false;
        }

        @Override // defpackage.bc3
        public final GURL i() {
            return GURL.emptyGURL();
        }

        @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
        public final boolean isIncognito() {
            return false;
        }

        @Override // defpackage.bc3
        public final boolean k() {
            return false;
        }

        @Override // defpackage.bc3
        public final boolean t() {
            return false;
        }
    }

    public ToolbarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new org.chromium.base.b<>();
        this.h = new int[2];
        this.i = bz.b(getContext(), R$color.default_icon_color_dark_tint_list);
        this.v = new u80();
        addOnLayoutChangeListener(new a());
    }

    public boolean A(boolean z) {
        return false;
    }

    public boolean C() {
        if (this.o || this.q) {
            return true;
        }
        z6 z6Var = this.t;
        return z6Var != null && z6Var.o();
    }

    @Override // defpackage.w80
    public void destroy() {
        this.v.e = true;
        ThemeColorProvider themeColorProvider = this.r;
        if (themeColorProvider != null) {
            themeColorProvider.i.c(this);
            this.r.h.c(this);
            this.r = null;
        }
        if (this.f != null) {
            this.f = null;
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarLayout.draw", null);
        try {
            super.draw(canvas);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public void f(Rect rect) {
        View viewE = getLocationBar().e();
        rect.set(viewE.getPaddingLeft(), viewE.getPaddingTop(), viewE.getWidth() - viewE.getPaddingRight(), viewE.getHeight() - viewE.getPaddingBottom());
        View viewE2 = getLocationBar().e();
        int[] iArr = this.h;
        ao3.b(this, viewE2, iArr);
        rect.offset(iArr[0], iArr[1]);
    }

    public final int g(int i) {
        Context context = getContext();
        boolean zK = k();
        Resources resources = context.getResources();
        if (i == yp.a(context, zK)) {
            return zK ? resources.getColor(R$color.divider_line_bg_color_light) : ii2.e(context);
        }
        int i2 = R$dimen.toolbar_hairline_overlay_alpha;
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        return z7.o(i, resources.getColor(R$color.toolbar_hairline_overlay_opaque) & (-16777216), resources.getFloat(i2), false);
    }

    View getCurrentTabView() {
        Tab tabD = this.j.d();
        if (tabD != null) {
            return tabD.getView();
        }
        return null;
    }

    public long getFirstDrawTime() {
        return this.p;
    }

    public HomeButton getHomeButton() {
        return null;
    }

    public abstract v91 getLocationBar();

    public af1 getMenuButtonCoordinator() {
        return this.s;
    }

    public View getOptionalButtonViewForTesting() {
        return null;
    }

    public ToolbarProgressBar getProgressBar() {
        return this.l;
    }

    public int getProgressBarHeight() {
        return getResources().getDimensionPixelSize(R$dimen.toolbar_progress_bar_height);
    }

    public int getTabStripHeight() {
        return getResources().getDimensionPixelSize(R$dimen.tab_strip_height);
    }

    public ColorStateList getTint() {
        ThemeColorProvider themeColorProvider = this.r;
        return themeColorProvider == null ? this.i : themeColorProvider.g;
    }

    public bc3 getToolbarDataProvider() {
        return this.j;
    }

    public void h(boolean z) {
        this.q = z;
    }

    public void j(LocationBarModel locationBarModel, ad3 ad3Var, af1 af1Var, pd pdVar, s20 s20Var, qh1 qh1Var) {
        this.j = locationBarModel;
        this.k = ad3Var;
        this.s = af1Var;
        this.m = s20Var;
        View viewFindViewById = findViewById(R$id.view_url_bar);
        Context context = getContext();
        int progressBarHeight = getProgressBarHeight();
        if (viewFindViewById == null) {
            viewFindViewById = this;
        }
        ToolbarProgressBar toolbarProgressBar = new ToolbarProgressBar(progressBarHeight, context, viewFindViewById);
        this.l = toolbarProgressBar;
        toolbarProgressBar.setLayoutDirection(3);
    }

    public final boolean k() {
        return this.j.isIncognito();
    }

    public abstract gm l();

    public final void m() {
        if (getLocationBar() == null || getLocationBar().i() == null) {
            return;
        }
        getLocationBar().i().s(null, 12, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new f52(15, this));
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.p == 0) {
            this.p = SystemClock.elapsedRealtime();
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.j = new b();
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int actionMasked;
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarLayout.onLayout", null);
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarLayout.onMeasure", null);
        try {
            super.onMeasure(i, i2);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public void q() {
        this.n = true;
        if (this.l.getParent() != null) {
            this.l.d();
        }
    }

    void setAppMenuButtonHelper(z6 z6Var) {
        this.t = z6Var;
    }

    public void setHairlineVisibility(boolean z) {
        ImageView imageView = (ImageView) getRootView().findViewById(R$id.toolbar_hairline);
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    public void setInvalidatorCallback(Callback<Runnable> callback) {
        this.e = callback;
    }

    public void setMenuButtonCoordinatorForTesting(af1 af1Var) {
        this.s = af1Var;
    }

    public void setOverlayCoordinator(pd3 pd3Var) {
        this.u = pd3Var;
        boolean z = getVisibility() == 0;
        td3 td3Var = pd3Var.g;
        td3Var.k = z;
        td3Var.b();
    }

    public void setThemeColorProvider(ThemeColorProvider themeColorProvider) {
        this.r = themeColorProvider;
        themeColorProvider.i.a(this);
        this.r.h.a(this);
    }

    public void setToolbarColorObserver(m.b bVar) {
        this.f = bVar;
    }

    public void setToolbarHairlineColor(int i) {
        ImageView imageView = (ImageView) getRootView().findViewById(R$id.toolbar_hairline);
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(g(i)));
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        pd3 pd3Var = this.u;
        if (pd3Var != null) {
            boolean z = i == 0;
            td3 td3Var = pd3Var.g;
            td3Var.k = z;
            td3Var.b();
        }
    }

    void y(boolean z) {
        this.o = z;
    }

    public final void z() {
        Profile profileB;
        m();
        ad3 ad3Var = this.k;
        if (ad3Var != null) {
            n3.X0("Home");
            nw2<Tab> nw2Var = ad3Var.a;
            Tab tab = nw2Var.get();
            if (tab != null && (profileB = Profile.b(tab.c())) != null) {
                oj2.U(Profile.c(profileB), 6, "Android.HomeButton.PerProfileType");
            }
            boolean zBooleanValue = ad3Var.b.get().booleanValue();
            nw2<xe3> nw2Var2 = ad3Var.c;
            if (zBooleanValue) {
                xe3 xe3Var = nw2Var2.get();
                if (xe3Var != null) {
                    xe3Var.notifyEvent("homepage_button_clicked");
                    return;
                }
                return;
            }
            Tab tab2 = nw2Var.get();
            if (tab2 == null) {
                return;
            }
            String str = ad3Var.e.get();
            boolean z = str.startsWith("about:") || str.startsWith("chrome:") || str.startsWith("chrome-native:");
            oj2.O("Navigation.Home.IsChromeInternal", z);
            if (!z) {
                n3.X0("Navigation.Home.NotChromeInternal");
            }
            Tab tab3 = nw2Var.get();
            xe3 xe3Var2 = nw2Var2.get();
            if (tab3 != null && xe3Var2 != null) {
                xe3Var2.notifyEvent("homepage_button_clicked");
            }
            tab2.b(new LoadUrlParams(str, 67108864));
        }
    }

    void F() {
    }

    void b() {
    }

    public void i() {
    }

    public void n() {
    }

    public void p() {
    }

    public void r() {
    }

    public void u() {
    }

    public void v() {
    }

    public void w() {
    }

    public void x() {
    }

    public void B(boolean z) {
    }

    public void D(boolean z) {
    }

    public void H(boolean z) {
    }

    public void I(ak akVar) {
    }

    void J(boolean z) {
    }

    public void o(boolean z) {
    }

    public void s(boolean z) {
    }

    public void setBookmarkClickHandler(View.OnClickListener onClickListener) {
    }

    public void setBrowserControlsVisibilityDelegate(vj vjVar) {
    }

    public void setCloseButtonImageResource(Drawable drawable) {
    }

    public void setContentAttached(boolean z) {
    }

    public void setCustomTabCloseClickHandler(View.OnClickListener onClickListener) {
    }

    public void setFolderClickHandler(View.OnClickListener onClickListener) {
    }

    public void setForceHideShadow(boolean z) {
    }

    public void setLayoutUpdater(Runnable runnable) {
    }

    public void setLocationBarCoordinator(org.chromium.chrome.browser.omnibox.c cVar) {
    }

    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
    }

    public void setOnTabSwitcherLongClickHandler(View.OnLongClickListener onLongClickListener) {
    }

    void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
    }

    void setTabModelSelector(y13 y13Var) {
    }

    public void setTextureCaptureMode(boolean z) {
    }

    public void setUrlBarHidden(boolean z) {
    }

    public void E(boolean z, boolean z2) {
    }

    @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.a
    public void d(int i, boolean z) {
    }

    @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.b
    public void e(int i, ColorStateList colorStateList) {
    }

    public void G(int i, Drawable drawable, String str) {
    }

    public void a(Drawable drawable, String str, View.OnClickListener onClickListener) {
    }

    public void t(boolean z, boolean z2, boolean z3) {
    }
}
