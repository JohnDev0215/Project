package org.chromium.chrome.browser.toolbar.top;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.LevelListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import defpackage.ad3;
import defpackage.af1;
import defpackage.ak;
import defpackage.bc3;
import defpackage.bd3;
import defpackage.bq;
import defpackage.cc3;
import defpackage.cd3;
import defpackage.dd3;
import defpackage.ed3;
import defpackage.fd3;
import defpackage.fz2;
import defpackage.gm;
import defpackage.gs;
import defpackage.hw0;
import defpackage.in3;
import defpackage.km1;
import defpackage.m63;
import defpackage.n3;
import defpackage.om3;
import defpackage.p11;
import defpackage.pd;
import defpackage.po3;
import defpackage.qh1;
import defpackage.rh3;
import defpackage.rj3;
import defpackage.s20;
import defpackage.uh3;
import defpackage.v91;
import defpackage.x10;
import defpackage.xk1;
import defpackage.ya0;
import defpackage.yp;
import defpackage.zo1;
import java.util.HashSet;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.b;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"Instantiatable"})
public class ToolbarTablet extends ToolbarLayout implements View.OnClickListener, View.OnLongClickListener {
    public static final /* synthetic */ int k0 = 0;
    public ImageView A;
    public HomeButton B;
    public ImageButton C;
    public ImageButton D;
    public ImageButton E;
    public ImageButton F;
    public TextView G;
    public ImageButton H;
    public ImageButton I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ImageButton f85J;
    public ToggleTabStackButton K;
    public View.OnClickListener L;
    public View.OnClickListener M;
    public boolean N;
    public boolean O;
    public ImageButton[] P;
    public ImageButton[] Q;
    public ImageButton R;
    public boolean S;
    public org.chromium.chrome.browser.toolbar.top.b T;
    public Boolean U;
    public org.chromium.chrome.browser.omnibox.c V;
    public final int W;
    public final int a0;
    public AnimatorSet b0;
    public b.InterfaceC0116b c0;
    public b d0;
    public org.chromium.chrome.browser.toolbar.a e0;
    public m63 f0;
    public int g0;
    public TabModel h0;
    public fz2 i0;
    public final int j0;
    public ImageButton w;
    public MenuButton x;
    public StatusView y;
    public LocationBarTablet z;

    public class a implements Runnable {
        public final /* synthetic */ Configuration e;

        public a(Configuration configuration) {
            this.e = configuration;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ToolbarTablet.this.K(this.e);
        }
    }

    public interface b {
    }

    public ToolbarTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j0 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_height);
        getResources().getDimensionPixelSize(R$dimen.toolbar_button_width2);
        this.W = getResources().getDimensionPixelOffset(R$dimen.tablet_toolbar_start_padding);
        this.a0 = getResources().getDimensionPixelOffset(R$dimen.toolbar_edge_padding);
    }

    private ChromeActivity getChromeActivity() {
        Activity activity;
        Context context = getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                activity = null;
                break;
            }
            if (context instanceof Activity) {
                activity = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (activity instanceof ChromeActivity) {
            return (ChromeActivity) activity;
        }
        return null;
    }

    private gm getReadinessStateWithSuppression() {
        m63 m63VarN = N();
        m63 m63Var = this.f0;
        int i = m63Var == null ? 1 : !Objects.equals(m63VarN.a, m63Var.a) ? 12 : !Objects.equals(m63VarN.b, m63Var.b) ? 16 : !Objects.equals(m63VarN.c, m63Var.c) ? 17 : !Objects.equals(m63VarN.d, m63Var.d) ? 18 : m63VarN.e != m63Var.e ? 6 : !Objects.equals(m63VarN.f, m63Var.f) ? 11 : !Objects.equals(m63VarN.g, m63Var.g) ? 15 : !Objects.equals(m63VarN.h, m63Var.h) ? 4 : m63VarN.i != m63Var.i ? 3 : m63VarN.j != m63Var.j ? 10 : 0;
        return i == 0 ? gm.b(3) : new gm(2, 0, i, true);
    }

    private void setStartPaddingBasedOnButtonVisibility(boolean z) {
        int i = z || this.B.getVisibility() == 0 ? this.W : this.a0;
        int paddingTop = getPaddingTop();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, i, paddingTop, om3.e.d(this), getPaddingBottom());
    }

    private void setToolbarButtonsVisible(boolean z) {
        if (this.O == z) {
            return;
        }
        this.O = z;
        for (ImageButton imageButton : this.P) {
            imageButton.setVisibility(z ? 0 : 8);
        }
        org.chromium.chrome.browser.omnibox.g gVar = this.V.k;
        gVar.I = z;
        gVar.n0();
        setStartPaddingBasedOnButtonVisibility(z);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void B(boolean z) {
        this.N = z;
        this.K.setClickable(!z);
        int i = z ? 4 : 0;
        this.V.c(!this.N);
        if (getImportantForAccessibility() != i) {
            setImportantForAccessibility(i);
            sendAccessibilityEvent(2048);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void D(boolean z) {
        boolean z2 = z && !this.N;
        this.C.setEnabled(z2);
        this.C.setFocusable(z2);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void E(boolean z, boolean z2) {
        org.chromium.chrome.browser.omnibox.c cVar = this.V;
        if (cVar != null && TextUtils.isEmpty(cVar.b().a)) {
            this.H.setVisibility(8);
            return;
        }
        if (z) {
            int i = R$drawable.btn_star_filled;
            this.g0 = i;
            this.H.setImageResource(i);
            this.H.setImageTintList(null);
            this.H.setContentDescription(getContext().getString(R$string.edit_bookmark));
        } else {
            int i2 = R$drawable.btn_star;
            this.g0 = i2;
            this.H.setImageResource(i2);
            this.H.setImageTintList(getTint());
            this.H.setContentDescription(getContext().getString(R$string.accessibility_menu_bookmark));
        }
        this.H.setEnabled(z2);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void F() {
        this.V.k.n0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void H(boolean z) {
        boolean z2 = z && !this.N;
        this.D.setEnabled(z2);
        this.D.setFocusable(z2);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void I(ak akVar) {
        if (this.R == null) {
            this.R = (ImageButton) ((ViewStub) findViewById(R$id.optional_button_stub)).inflate();
        }
        ak.a aVarB = akVar.b();
        boolean z = aVarB.e;
        this.S = z;
        if (z) {
            this.R.setImageTintList(getTint());
        } else {
            this.R.setImageTintList(null);
        }
        hw0 hw0Var = aVarB.f;
        if (hw0Var != null) {
            hw0Var.j = this.R;
        }
        this.R.setOnClickListener(aVarB.b);
        View.OnLongClickListener onLongClickListener = aVarB.c;
        if (onLongClickListener == null) {
            this.R.setLongClickable(false);
        } else {
            this.R.setLongClickable(true);
            this.R.setOnLongClickListener(onLongClickListener);
        }
        this.R.setImageDrawable(aVarB.a);
        this.R.setContentDescription(aVarB.d);
        this.R.setVisibility(0);
        this.R.setEnabled(akVar.isEnabled());
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void J(boolean z) {
        this.y.e(z, getLocationBar());
        if (z) {
            this.E.getDrawable().setLevel(getResources().getInteger(R$integer.reload_button_level_stop));
            this.E.setContentDescription(getContext().getString(R$string.accessibility_btn_stop_loading));
        } else {
            this.E.getDrawable().setLevel(getResources().getInteger(R$integer.reload_button_level_reload));
            this.E.setContentDescription(getContext().getString(R$string.accessibility_btn_refresh));
        }
        this.E.setEnabled(!this.N);
    }

    public final void K(Configuration configuration) {
        ChromeActivity chromeActivity = getChromeActivity();
        if (chromeActivity == null) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.toolbar_button_margin);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_margin2);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_margin3);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_width);
        int dimensionPixelSize5 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_mFolder_margin_end);
        boolean zIsInMultiWindowMode = chromeActivity.isInMultiWindowMode();
        if (configuration.orientation == 2 && zIsInMultiWindowMode) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.toolbar_button_margin_m);
            dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_margin2_m);
            dimensionPixelSize3 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_margin3_m);
            dimensionPixelSize4 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_width_m);
            dimensionPixelSize5 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_mFolder_margin_end_m);
        }
        for (ImageButton imageButton : this.Q) {
            if (imageButton != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
                if (imageButton == this.K) {
                    layoutParams.setMarginStart(dimensionPixelSize2);
                    layoutParams.setMarginEnd(dimensionPixelSize);
                } else if (imageButton == this.w) {
                    layoutParams.setMarginStart(dimensionPixelSize);
                    layoutParams.setMarginEnd(dimensionPixelSize2);
                } else if (imageButton == this.E) {
                    layoutParams.setMarginStart(dimensionPixelSize);
                    layoutParams.setMarginEnd(dimensionPixelSize3);
                } else if (imageButton == this.f85J) {
                    layoutParams.setMarginStart(dimensionPixelSize3);
                    layoutParams.setMarginEnd(dimensionPixelSize5);
                } else {
                    layoutParams.setMarginStart(dimensionPixelSize);
                    layoutParams.setMarginEnd(dimensionPixelSize);
                }
                layoutParams.height = this.j0;
                layoutParams.width = dimensionPixelSize4;
                imageButton.setLayoutParams(layoutParams);
            }
        }
    }

    public final void L(Configuration configuration) {
        PostTask.b(rh3.d, new a(configuration), 50L);
        boolean z = configuration.orientation == 2 && configuration.screenWidthDp >= 800;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f85J.getLayoutParams();
        marginLayoutParams.setMarginEnd((int) (((z ? 98.0f : 7.0f) * ya0.a(getContext()).d) + 0.5f));
        this.f85J.setLayoutParams(marginLayoutParams);
    }

    public final void M(ImageButton imageButton, boolean z) {
        Tab tabD = getToolbarDataProvider().d();
        if (tabD == null || tabD.c() == null) {
            return;
        }
        Profile profileB = Profile.b(tabD.c());
        Context context = getContext();
        NavigationController navigationControllerM = tabD.c().m();
        int i = z ? 2 : 1;
        bc3 toolbarDataProvider = getToolbarDataProvider();
        Objects.requireNonNull(toolbarDataProvider);
        org.chromium.chrome.browser.toolbar.top.b bVar = new org.chromium.chrome.browser.toolbar.top.b(profileB, context, navigationControllerM, i, new p11(13, toolbarDataProvider), this.c0);
        this.T = bVar;
        if (!bVar.r) {
            Object obj = ThreadUtils.a;
            bVar.r = true;
            bVar.q = new FaviconHelper();
            HashSet hashSet = new HashSet();
            int i2 = 0;
            while (true) {
                xk1 xk1Var = bVar.i;
                if (i2 >= xk1Var.b()) {
                    break;
                }
                NavigationEntry navigationEntryA = xk1Var.a(i2);
                if (navigationEntryA.f == null) {
                    GURL gurl = navigationEntryA.b;
                    if (!hashSet.contains(gurl)) {
                        zo1 zo1Var = new zo1(bVar, 6, gurl);
                        FaviconHelper faviconHelper = bVar.q;
                        faviconHelper.getClass();
                        faviconHelper.a(bVar.e, gurl.j(), bVar.l, zo1Var);
                        hashSet.add(gurl);
                    }
                }
                i2++;
            }
        }
        ListPopupWindow listPopupWindow = bVar.g;
        if (!listPopupWindow.isShowing()) {
            n3.X0(bVar.a("Popup"));
        }
        View anchorView = listPopupWindow.getAnchorView();
        org.chromium.chrome.browser.toolbar.top.a aVar = bVar.m;
        if (anchorView != null && aVar != null) {
            listPopupWindow.getAnchorView().removeOnLayoutChangeListener(aVar);
        }
        listPopupWindow.setAnchorView(imageButton);
        if (bVar.k != 0) {
            listPopupWindow.show();
            return;
        }
        imageButton.addOnLayoutChangeListener(aVar);
        int width = (listPopupWindow.getAnchorView().getWidth() - listPopupWindow.getWidth()) / 2;
        if (width > 0) {
            listPopupWindow.setHorizontalOffset(width);
        }
        listPopupWindow.show();
    }

    public final m63 N() {
        rj3 rj3VarB = getToolbarDataProvider().b();
        int iC = getToolbarDataProvider().c(true);
        po3 po3Var = new po3(rj3VarB.b, this.V.h.f.getVisibleTextPrefixHint());
        org.chromium.chrome.browser.toolbar.a aVar = this.e0;
        return new m63(this.B, this.C, this.D, this.E, iC, po3Var, this.H, this.g0, this.R, aVar == null ? 0 : aVar.e, getWidth());
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, org.chromium.chrome.browser.theme.ThemeColorProvider.a
    public final void d(int i, boolean z) {
        setBackgroundColor(i);
        n3.x0(i, getContext(), k());
        ((org.chromium.chrome.browser.omnibox.e) this.V.f).getClass();
        this.V.p();
        setToolbarHairlineColor(i);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, defpackage.w80
    public final void destroy() {
        super.destroy();
        AnimatorSet animatorSet = this.b0;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.b0.cancel();
            this.b0 = null;
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, org.chromium.chrome.browser.theme.ThemeColorProvider.b
    public final void e(int i, ColorStateList colorStateList) {
        this.B.setImageTintList(colorStateList);
        this.C.setImageTintList(colorStateList);
        this.D.setImageTintList(colorStateList);
        this.I.setImageTintList(colorStateList);
        this.E.setImageTintList(colorStateList);
        this.f85J.setImageTintList(colorStateList);
        this.w.setImageTintList(colorStateList);
        this.K.setBrandedColorScheme(i);
        ImageButton imageButton = this.R;
        if (imageButton == null || !this.S) {
            return;
        }
        imageButton.setImageTintList(colorStateList);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public HomeButton getHomeButton() {
        return this.B;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public v91 getLocationBar() {
        return this.V;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public View getOptionalButtonViewForTesting() {
        return this.R;
    }

    public int getStartPaddingDifferenceForButtonVisibilityAnimation() {
        if (this.B.getVisibility() == 0) {
            return 0;
        }
        return this.W - this.a0;
    }

    public ImageButton[] getToolbarButtons() {
        return this.P;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void i() {
        ImageButton imageButton = this.R;
        if (imageButton == null || imageButton.getVisibility() == 8) {
            return;
        }
        this.R.setVisibility(8);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void j(LocationBarModel locationBarModel, ad3 ad3Var, af1 af1Var, pd pdVar, s20 s20Var, qh1 qh1Var) {
        super.j(locationBarModel, ad3Var, af1Var, pdVar, s20Var, qh1Var);
        this.c0 = pdVar;
        this.d0 = qh1Var;
        af1Var.c(true);
        MenuButton menuButton = this.x;
        if (menuButton != null) {
            int childCount = menuButton.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.x.getChildAt(i);
                if (childAt != null) {
                    childAt.setVisibility(8);
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final gm l() {
        return cc3.a() ? gm.b(8) : cc3.b() ? this.o ? gm.b(4) : this.N ? gm.b(10) : getReadinessStateWithSuppression() : new gm(0, 0, 0, !this.o);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void o(boolean z) {
        this.B.setVisibility(z ? 0 : 8);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ChromeActivity chromeActivity;
        Tab tab;
        Tab tab2;
        if (this.B == view) {
            z();
            return;
        }
        boolean z = false;
        if (this.C == view) {
            m();
            ad3 ad3Var = this.k;
            if (ad3Var != null && ad3Var.a()) {
                z = true;
            }
            if (z) {
                n3.X0("MobileToolbarBack");
                return;
            }
            return;
        }
        if (this.D == view) {
            m();
            ad3 ad3Var2 = this.k;
            if (ad3Var2 != null && (tab2 = ad3Var2.a.get()) != null && tab2.i()) {
                tab2.h();
                ad3Var2.f.run();
            }
            n3.X0("MobileToolbarForward");
            return;
        }
        if (this.E == view) {
            m();
            ad3 ad3Var3 = this.k;
            if (ad3Var3 == null || (tab = ad3Var3.a.get()) == null) {
                return;
            }
            if (tab.k()) {
                tab.y();
                n3.X0("MobileToolbarStop");
            } else {
                tab.a();
                n3.X0("MobileToolbarReload");
            }
            ad3Var3.f.run();
            return;
        }
        ImageButton imageButton = this.H;
        if (imageButton == view) {
            View.OnClickListener onClickListener = this.L;
            if (onClickListener != null) {
                onClickListener.onClick(imageButton);
                n3.X0("MobileToolbarToggleBookmark");
                return;
            }
            return;
        }
        if (this.I == view) {
            b bVar = this.d0;
            Context context = getContext();
            Tab tabD = getToolbarDataProvider().d();
            ((qh1) bVar).getClass();
            DownloadUtils.c(context, tabD);
            n3.X0("MobileToolbarDownloadPage");
            return;
        }
        ImageButton imageButton2 = this.f85J;
        if (imageButton2 == view) {
            View.OnClickListener onClickListener2 = this.M;
            if (onClickListener2 != null) {
                onClickListener2.onClick(imageButton2);
                return;
            }
            return;
        }
        if (this.w == view) {
            Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            Intent intent = new Intent();
            intent.setClass(context2, SettingsActivity.class);
            intent.addFlags(268435456);
            intent.addFlags(67108864);
            context2.startActivity(intent);
            return;
        }
        if (this.F == view) {
            if (this.h0 == null) {
                chromeActivity = getChromeActivity();
                if (chromeActivity == null) {
                    return;
                } else {
                    this.h0 = chromeActivity.D2();
                }
            } else {
                chromeActivity = null;
            }
            if (this.i0 == null) {
                if (chromeActivity == null && (chromeActivity = getChromeActivity()) == null) {
                    return;
                } else {
                    this.i0 = chromeActivity.p0(false);
                }
            }
            if (!this.h0.isIncognito()) {
                this.h0.c();
            }
            this.i0.f(2);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        L(configuration);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundColor(yp.a(getContext(), false));
        this.B = (HomeButton) findViewById(R$id.home_button);
        if (n3.Q0() || n3.O0()) {
            this.C = (ImageButton) findViewById(R$id.forward_button);
            this.D = (ImageButton) findViewById(R$id.back_button);
        } else {
            this.C = (ImageButton) findViewById(R$id.back_button);
            this.D = (ImageButton) findViewById(R$id.forward_button);
        }
        this.E = (ImageButton) findViewById(R$id.refresh_button);
        this.F = (ImageButton) findViewById(R$id.bt_tab_add);
        this.G = (TextView) findViewById(R$id.tv_cancel);
        this.y = (StatusView) findViewById(R$id.location_bar_status);
        LocationBarTablet locationBarTablet = (LocationBarTablet) findViewById(R$id.location_bar);
        this.z = locationBarTablet;
        this.A = (ImageView) locationBarTablet.findViewById(R$id.url_bar_search);
        if (bq.f0.a()) {
            ((ViewGroup) this.B.getParent()).removeView(this.B);
            ((LinearLayout) findViewById(R$id.toolbar_tablet_layout)).addView(this.B, 3);
        }
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        int integer = getResources().getInteger(R$integer.reload_button_level_reload);
        int integer2 = getResources().getInteger(R$integer.reload_button_level_stop);
        Context context = getContext();
        int i = R$drawable.btn_toolbar_reload;
        int i2 = R$color.default_icon_color_tint_list;
        levelListDrawable.addLevel(integer, integer, uh3.e(context, i, i2));
        levelListDrawable.addLevel(integer2, integer2, uh3.e(getContext(), R$drawable.btn_toolbar_close, i2));
        this.E.setImageDrawable(levelListDrawable);
        this.K = (ToggleTabStackButton) findViewById(R$id.tab_switcher_button);
        this.H = (ImageButton) findViewById(R$id.bookmark_button);
        this.I = (ImageButton) findViewById(R$id.save_offline_button);
        this.f85J = (ImageButton) findViewById(R$id.folder_button);
        this.w = (ImageButton) findViewById(R$id.menu_button_setting);
        this.x = (MenuButton) findViewById(R$id.menu_button_wrapper);
        this.O = true;
        ImageButton imageButton = this.C;
        ImageButton imageButton2 = this.D;
        ImageButton imageButton3 = this.E;
        this.P = new ImageButton[]{imageButton, imageButton2, imageButton3};
        this.Q = new ImageButton[]{this.K, this.F, imageButton, imageButton2, imageButton3, this.f85J, this.w};
        L(getResources().getConfiguration());
        if (n3.Q0()) {
            setLayoutDirection(1);
        } else {
            setLayoutDirection(0);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        Context context = getContext();
        Resources resources = context.getResources();
        ImageButton imageButton = this.E;
        return Toast.f(context, view, view == imageButton ? imageButton.getDrawable().getLevel() == resources.getInteger(R$integer.reload_button_level_reload) ? resources.getString(R$string.refresh) : resources.getString(R$string.menu_stop_refresh) : view == this.H ? resources.getString(R$string.menu_bookmark) : view == this.I ? resources.getString(R$string.menu_download) : null);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        org.chromium.chrome.browser.toolbar.top.b bVar;
        if (z && (bVar = this.T) != null) {
            bVar.g.dismiss();
            this.T = null;
        }
        super.onWindowFocusChanged(z);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void q() {
        super.q();
        this.B.setOnClickListener(this);
        this.B.setOnKeyListener(new bd3(this));
        this.C.setOnClickListener(this);
        this.C.setLongClickable(true);
        this.C.setOnKeyListener(new cd3(this));
        this.D.setOnClickListener(this);
        this.D.setLongClickable(true);
        this.D.setOnKeyListener(new dd3(this));
        this.E.setOnClickListener(this);
        this.E.setOnLongClickListener(this);
        this.E.setOnKeyListener(new ed3(this));
        this.H.setOnClickListener(this);
        this.H.setOnLongClickListener(this);
        getMenuButtonCoordinator().setOnKeyListener(new l(this));
        this.I.setOnClickListener(this);
        this.I.setOnLongClickListener(this);
        this.f85J.setOnClickListener(this);
        this.f85J.setOnLongClickListener(this);
        this.w.setOnClickListener(this);
        this.F.setOnClickListener(new gs(12, this));
        this.G.setOnClickListener(this);
        this.G.setOnClickListener(new fd3(this));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setBookmarkClickHandler(View.OnClickListener onClickListener) {
        this.L = onClickListener;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setFolderClickHandler(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setLocationBarCoordinator(org.chromium.chrome.browser.omnibox.c cVar) {
        this.V = cVar;
        yp.c(getContext(), R$dimen.default_elevation_2);
        ((org.chromium.chrome.browser.omnibox.e) this.V.f).getClass();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
        this.K.setOnTabSwitcherClickHandler(onClickListener);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
        this.K.setTabCountProvider(aVar);
        this.e0 = aVar;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setTextureCaptureMode(boolean z) {
        if (z) {
            this.f0 = N();
        }
    }

    public void setToolbarButtonsVisibleForTesting(boolean z) {
        this.O = z;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean showContextMenuForChild(View view) {
        ImageButton imageButton = this.C;
        if (imageButton == view) {
            M(imageButton, false);
            return true;
        }
        ImageButton imageButton2 = this.D;
        if (imageButton2 != view) {
            return super.showContextMenuForChild(view);
        }
        M(imageButton2, true);
        return true;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void v() {
        km1 km1VarG = getToolbarDataProvider().g();
        km1VarG.c(new x10(8, km1VarG));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void w() {
        boolean zK = k();
        Boolean bool = this.U;
        if (bool == null || bool.booleanValue() != zK) {
            getProgressBar().e(yp.a(getContext(), zK), k());
            this.U = Boolean.valueOf(zK);
        }
        km1 km1VarG = getToolbarDataProvider().g();
        km1VarG.c(new x10(8, km1VarG));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void y(boolean z) {
        this.o = z;
        this.K.setVisibility(z ? 8 : 0);
        this.F.setVisibility(z ? 8 : 0);
        this.C.setVisibility(z ? 8 : 0);
        this.D.setVisibility(z ? 8 : 0);
        this.E.setVisibility(z ? 8 : 0);
        this.f85J.setVisibility(z ? 8 : 0);
        this.w.setVisibility(z ? 4 : 0);
        this.G.setVisibility(z ? 0 : 8);
        this.z.setBackgroundResource(z ? R$drawable.bg_search : R$drawable.bg_url_byd);
        this.A.setVisibility(z ? 8 : 0);
    }
}
