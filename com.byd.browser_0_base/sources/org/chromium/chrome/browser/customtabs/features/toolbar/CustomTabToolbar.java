package org.chromium.chrome.browser.customtabs.features.toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.C0173a;
import defpackage.ao3;
import defpackage.b50;
import defpackage.bi1;
import defpackage.bk3;
import defpackage.bq;
import defpackage.bz;
import defpackage.cc3;
import defpackage.ew1;
import defpackage.gm;
import defpackage.h30;
import defpackage.ii2;
import defpackage.iq2;
import defpackage.jk1;
import defpackage.kw1;
import defpackage.ll;
import defpackage.lw1;
import defpackage.n3;
import defpackage.n40;
import defpackage.nw2;
import defpackage.o40;
import defpackage.p40;
import defpackage.pc;
import defpackage.pq2;
import defpackage.q40;
import defpackage.r40;
import defpackage.rh3;
import defpackage.rj3;
import defpackage.rr1;
import defpackage.sb3;
import defpackage.sr1;
import defpackage.uh3;
import defpackage.v91;
import defpackage.vj;
import defpackage.vt;
import defpackage.yp;
import defpackage.za3;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.omnibox.i;
import org.chromium.chrome.browser.omnibox.j;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.ToolbarProgressBar;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarLayout;
import org.chromium.components.browser_ui.styles.R$color;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class CustomTabToolbar extends ToolbarLayout implements View.OnLongClickListener {
    public static final Object M = new Object();
    public Drawable A;
    public int B;
    public ColorStateList C;
    public ValueAnimator D;
    public boolean E;
    public GURL F;
    public final b G;
    public LocationBarModel H;
    public vj I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public h30 f65J;
    public c K;
    public int L;
    public ImageView w;
    public LinearLayout x;
    public ImageButton y;
    public MenuButton z;

    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ ColorDrawable e;

        public a(ColorDrawable colorDrawable) {
            this.e = colorDrawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            customTabToolbar.E = false;
            CustomTabToolbar.N(customTabToolbar, this.e.getColor());
        }
    }

    public interface c {
    }

    public interface d {
    }

    public CustomTabToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = new b();
        Context context2 = getContext();
        String str = yp.a;
        this.C = bz.b(context2, R$color.default_icon_color_tint_list);
    }

    public static void K(CustomTabToolbar customTabToolbar, int i, int i2, ColorDrawable colorDrawable, ValueAnimator valueAnimator) {
        customTabToolbar.getClass();
        float animatedFraction = valueAnimator.getAnimatedFraction();
        float fRed = Color.red(i);
        int iF = (int) C0173a.f(Color.red(i2), fRed, animatedFraction, fRed);
        float fBlue = Color.blue(i);
        int iF2 = (int) C0173a.f(Color.blue(i2), fBlue, animatedFraction, fBlue);
        float fGreen = Color.green(i);
        int iRgb = Color.rgb(iF, (int) C0173a.f(Color.green(i2), fGreen, animatedFraction, fGreen), iF2);
        colorDrawable.setColor(iRgb);
        customTabToolbar.setHandleViewBackgroundColor(iRgb);
    }

    public static void L(CustomTabToolbar customTabToolbar, d dVar) {
        customTabToolbar.getClass();
        lw1 lw1Var = (lw1) ((pq2) dVar).f;
        lw1.a aVar = lw1.L;
        customTabToolbar.setMaximizeButtonDrawable(lw1Var.L(true));
    }

    public static void N(CustomTabToolbar customTabToolbar, int i) {
        int iA = rr1.a(i, customTabToolbar.getContext(), customTabToolbar.k());
        if (customTabToolbar.B == iA) {
            return;
        }
        customTabToolbar.B = iA;
        customTabToolbar.C = n3.y0(customTabToolbar.getContext());
        customTabToolbar.G.A();
        customTabToolbar.setToolbarHairlineColor(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Tab getCurrentTab() {
        return getToolbarDataProvider().d();
    }

    private int getLocationBarFrameLayoutIndex() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == this.G.l) {
                return i;
            }
        }
        return -1;
    }

    private void setHandleViewBackgroundColor(int i) {
        Drawable drawable = this.A;
        if (drawable == null) {
            return;
        }
        ((GradientDrawable) drawable.mutate()).setColor(i);
    }

    private void setMaximizeButtonDrawable(boolean z) {
        int i = z ? R$drawable.ic_fullscreen_exit : R$drawable.ic_fullscreen_enter;
        int i2 = z ? R$string.custom_tab_side_sheet_minimize : R$string.custom_tab_side_sheet_maximize;
        ImageButton imageButton = (ImageButton) findViewById(R$id.custom_tabs_sidepanel_maximize);
        Drawable drawableF = uh3.f(getContext(), i, this.C);
        R(imageButton, drawableF, null);
        imageButton.setImageDrawable(drawableF);
        imageButton.setContentDescription(getResources().getString(i2));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void G(int i, Drawable drawable, String str) {
        R((ImageButton) this.x.getChildAt((r0.getChildCount() - 1) - i), drawable, str);
        S();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.h30 O() {
        /*
            r8 = this;
            org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar$b r0 = r8.G
            android.widget.TextView r1 = r0.j
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r5 = r1.toString()
            android.widget.TextView r1 = r0.k
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r6 = r1.toString()
            h30 r1 = new h30
            android.graphics.drawable.ColorDrawable r8 = r8.getBackground()
            int r3 = r8.getColor()
            r40 r8 = r0.o
            int r4 = r8.g
            boolean r0 = r8.h
            r2 = 1
            if (r0 != 0) goto L48
            cj r0 = r8.b
            boolean r0 = r0.a
            if (r0 != 0) goto L48
            oh2 r8 = r8.a
            android.animation.AnimatorSet r0 = r8.d
            boolean r0 = r0.isStarted()
            r7 = 0
            if (r0 != 0) goto L45
            android.animation.AnimatorSet r8 = r8.c
            boolean r8 = r8.isStarted()
            if (r8 == 0) goto L43
            goto L45
        L43:
            r8 = r7
            goto L46
        L45:
            r8 = r2
        L46:
            if (r8 == 0) goto L49
        L48:
            r7 = r2
        L49:
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar.O():h30");
    }

    public final void P(boolean z, pq2 pq2Var) {
        if (bq.o.a()) {
            int i = R$id.custom_tabs_sidepanel_maximize;
            ImageButton imageButton = (ImageButton) findViewById(i);
            int i2 = 0;
            if (imageButton != null) {
                imageButton.setVisibility(0);
            } else {
                ((ViewStub) findViewById(R$id.maximize_button_stub)).inflate();
                imageButton = (ImageButton) findViewById(i);
            }
            setMaximizeButtonDrawable(z);
            imageButton.setOnClickListener(new n40(this, i2, pq2Var));
        }
    }

    public final void Q(ImageButton imageButton) {
        Drawable drawable = imageButton.getDrawable();
        if (drawable instanceof za3) {
            ((za3) drawable).c(this.C);
        }
    }

    public final void R(ImageButton imageButton, Drawable drawable, String str) {
        Resources resources = getResources();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.toolbar_icon_height);
        int iMax = Math.max(((dimensionPixelSize * 2) - ((drawable.getIntrinsicWidth() * dimensionPixelSize) / intrinsicHeight)) / 2, resources.getDimensionPixelSize(R$dimen.min_toolbar_icon_side_padding));
        imageButton.setPadding(iMax, imageButton.getPaddingTop(), iMax, imageButton.getPaddingBottom());
        imageButton.setImageDrawable(drawable);
        Q(imageButton);
        imageButton.setContentDescription(str);
    }

    public final void S() {
        ImageButton imageButton = (ImageButton) findViewById(R$id.custom_tabs_sidepanel_maximize);
        if (imageButton != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageButton.getLayoutParams();
            int dimensionPixelSize = (this.L == 2 ? this.y : this.z).getVisibility() == 8 ? 0 : getResources().getDimensionPixelSize(R$dimen.toolbar_button_width);
            LinearLayout linearLayout = this.x;
            if (linearLayout != null) {
                dimensionPixelSize += linearLayout.getWidth();
            }
            layoutParams.setMarginEnd(dimensionPixelSize);
            imageButton.setLayoutParams(layoutParams);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void a(Drawable drawable, String str, View.OnClickListener onClickListener) {
        ImageButton imageButton = (ImageButton) LayoutInflater.from(getContext()).inflate(R$layout.custom_tabs_toolbar_button, (ViewGroup) this.x, false);
        imageButton.setOnLongClickListener(this);
        imageButton.setOnClickListener(onClickListener);
        imageButton.setVisibility(0);
        R(imageButton, drawable, str);
        this.x.addView(imageButton, 0);
        S();
    }

    public sb3 getBrandingDelegate() {
        return this.G;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public v91 getLocationBar() {
        return this.G;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public int getTabStripHeight() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final gm l() {
        if (cc3.a()) {
            return gm.b(8);
        }
        if (!cc3.b()) {
            return new gm(0, 0, 0, true);
        }
        h30 h30VarO = O();
        h30 h30Var = this.f65J;
        int i = h30Var == null ? 1 : !Objects.equals(h30VarO.a, h30Var.a) ? 11 : !Objects.equals(h30VarO.b, h30Var.b) ? 13 : h30VarO.c != h30Var.c ? 2 : h30VarO.d != h30Var.d ? 6 : !Objects.equals(h30VarO.e, h30Var.e) ? 14 : 0;
        return i == 0 ? gm.b(3) : new gm(2, 0, i, true);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.H.y();
        this.H.z();
        this.H.w();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setBackground(new ColorDrawable(yp.a(getContext(), false)));
        this.B = 3;
        this.w = (ImageView) findViewById(R$id.incognito_cct_logo_image_view);
        this.x = (LinearLayout) findViewById(R$id.action_buttons);
        ImageButton imageButton = (ImageButton) findViewById(R$id.close_button);
        this.y = imageButton;
        imageButton.setOnLongClickListener(this);
        this.z = (MenuButton) findViewById(R$id.menu_button_wrapper);
        b bVar = this.G;
        bVar.getClass();
        TextView textView = (TextView) findViewById(R$id.url_bar);
        bVar.j = textView;
        textView.setHint("");
        bVar.j.setEnabled(false);
        bVar.k = (TextView) findViewById(R$id.title_bar);
        bVar.l = findViewById(R$id.location_bar_frame_layout);
        View viewFindViewById = findViewById(R$id.title_url_container);
        bVar.m = viewFindViewById;
        viewFindViewById.setOnLongClickListener(bVar);
        ImageButton imageButton2 = (ImageButton) findViewById(R$id.security_button);
        bVar.n = imageButton2;
        bVar.o = new r40(imageButton2, bVar.m, R$dimen.location_bar_icon_width);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        c cVar = this.K;
        if (cVar == null) {
            return false;
        }
        ew1 ew1Var = (ew1) cVar;
        if (ew1Var.g.getAsBoolean()) {
            return false;
        }
        return ew1Var.a.onTouchEvent(motionEvent);
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        if (view == this.y || view.getParent() == this.x) {
            return Toast.f(getContext(), view, view.getContentDescription());
        }
        return false;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.L == 2) {
            View viewFindViewById = findViewById(R$id.close_button);
            int iIndexOfChild = indexOfChild(viewFindViewById);
            ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
            View viewFindViewById2 = findViewById(R$id.menu_button_wrapper);
            int iIndexOfChild2 = indexOfChild(viewFindViewById2);
            ViewGroup.LayoutParams layoutParams2 = viewFindViewById2.getLayoutParams();
            removeViewAt(iIndexOfChild2);
            addView(viewFindViewById2, iIndexOfChild, layoutParams2);
            removeView(viewFindViewById);
            addView(viewFindViewById, iIndexOfChild2, layoutParams);
        }
        this.w.setVisibility(getToolbarDataProvider().isIncognito() ? 0 : 8);
        int dimensionPixelSize = (this.L == 2 ? this.z : this.y).getVisibility() == 8 ? getResources().getDimensionPixelSize(R$dimen.custom_tabs_toolbar_horizontal_margin_no_start) : 0;
        int locationBarFrameLayoutIndex = getLocationBarFrameLayoutIndex();
        for (int i3 = 0; i3 < locationBarFrameLayoutIndex; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                if (layoutParams3.getMarginStart() != dimensionPixelSize) {
                    layoutParams3.setMarginStart(dimensionPixelSize);
                    childAt.setLayoutParams(layoutParams3);
                }
                FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int i4 = layoutParams4.width;
                int iMakeMeasureSpec = i4 == -2 ? View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE) : i4 == -1 ? View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                int i5 = layoutParams4.height;
                childAt.measure(iMakeMeasureSpec, i5 == -2 ? View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE) : i5 == -1 ? View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(i5, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                dimensionPixelSize = childAt.getMeasuredWidth() + dimensionPixelSize;
            }
        }
        View childAt2 = getChildAt(getLocationBarFrameLayoutIndex());
        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) childAt2.getLayoutParams();
        if (layoutParams5.getMarginStart() != dimensionPixelSize) {
            layoutParams5.setMarginStart(dimensionPixelSize);
            childAt2.setLayoutParams(layoutParams5);
        }
        int locationBarFrameLayoutIndex2 = getLocationBarFrameLayoutIndex();
        int measuredWidth = 0;
        while (true) {
            locationBarFrameLayoutIndex2++;
            if (locationBarFrameLayoutIndex2 >= getChildCount()) {
                break;
            }
            View childAt3 = getChildAt(locationBarFrameLayoutIndex2);
            if (childAt3.getVisibility() != 8) {
                measuredWidth += childAt3.getMeasuredWidth();
            }
        }
        b bVar = this.G;
        FrameLayout.LayoutParams layoutParams6 = (FrameLayout.LayoutParams) bVar.l.getLayoutParams();
        if (layoutParams6.getMarginEnd() != measuredWidth) {
            layoutParams6.setMarginEnd(measuredWidth);
            bVar.l.setLayoutParams(layoutParams6);
        }
        int measuredWidth2 = bVar.n.getMeasuredWidth();
        FrameLayout.LayoutParams layoutParams7 = (FrameLayout.LayoutParams) bVar.m.getLayoutParams();
        if (bVar.n.getVisibility() == 8) {
            measuredWidth2 -= bVar.n.getMeasuredWidth();
        }
        layoutParams7.leftMargin = measuredWidth2;
        bVar.m.setLayoutParams(layoutParams7);
        if (this.L == 2) {
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.toolbar_button_width);
            FrameLayout.LayoutParams layoutParams8 = (FrameLayout.LayoutParams) this.z.getLayoutParams();
            layoutParams8.width = dimensionPixelSize2;
            layoutParams8.gravity = 8388627;
            this.z.setLayoutParams(layoutParams8);
            this.z.setPaddingRelative(0, 0, 0, 0);
            ((FrameLayout.LayoutParams) this.y.getLayoutParams()).gravity = 8388629;
            FrameLayout.LayoutParams layoutParams9 = (FrameLayout.LayoutParams) this.x.getLayoutParams();
            layoutParams9.setMarginEnd(dimensionPixelSize2);
            this.x.setLayoutParams(layoutParams9);
        }
        S();
        super.onMeasure(i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0075  */
    @Override // android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar$c r7 = r7.K
            r0 = 0
            if (r7 == 0) goto L9e
            ew1 r7 = (defpackage.ew1) r7
            nw2<java.lang.Integer> r1 = r7.h
            java.lang.Object r1 = r1.get()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r2 = 1
            r3 = 2
            if (r1 != r3) goto L19
            goto L9d
        L19:
            java.util.function.BooleanSupplier r1 = r7.g
            boolean r1 = r1.getAsBoolean()
            if (r1 == 0) goto L23
            goto L9d
        L23:
            float r1 = r8.getRawY()
            int r4 = r8.getActionMasked()
            ew1$a r5 = r7.i
            android.view.VelocityTracker r6 = r7.e
            if (r4 == 0) goto L75
            if (r4 == r2) goto L39
            if (r4 == r3) goto L75
            r8 = 3
            if (r4 == r8) goto L39
            goto L9d
        L39:
            boolean r8 = r7.d
            if (r8 == 0) goto L9d
            r8 = 1000(0x3e8, float:1.401E-42)
            r6.computeCurrentVelocity(r8)
            float r8 = r6.getYVelocity()
            float r8 = java.lang.Math.abs(r8)
            float r1 = java.lang.Math.abs(r8)
            r3 = 1120403456(0x42c80000, float:100.0)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L56
            r8 = r0
            goto L5d
        L56:
            r1 = 1129971712(0x435a0000, float:218.0)
            float r8 = r8 * r1
            r1 = 1157234688(0x44fa0000, float:2000.0)
            float r8 = r8 / r1
            int r8 = (int) r8
        L5d:
            float r1 = r7.c
            float r1 = java.lang.Math.signum(r1)
            int r1 = (int) r1
            int r8 = r8 * r1
            aw1 r5 = (defpackage.aw1) r5
            boolean r8 = r5.P(r8)
            if (r8 != 0) goto L72
            java.lang.Runnable r8 = r7.f
            r8.run()
        L72:
            r7.d = r0
            goto L9d
        L75:
            boolean r0 = r7.d
            if (r0 != 0) goto L87
            r7.d = r2
            r6.clear()
            r7.b = r1
            int r8 = (int) r1
            aw1 r5 = (defpackage.aw1) r5
            r5.Q(r8)
            goto L95
        L87:
            r6.addMovement(r8)
            int r8 = (int) r1
            aw1 r5 = (defpackage.aw1) r5
            float r8 = (float) r8
            float r0 = r5.R
            float r8 = r8 + r0
            int r8 = (int) r8
            r5.S(r8, r2)
        L95:
            float r8 = r7.b
            float r8 = r1 - r8
            r7.c = r8
            r7.b = r1
        L9d:
            return r2
        L9e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void p() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.x.getLayoutParams();
        marginLayoutParams.setMarginEnd(0);
        this.x.setLayoutParams(marginLayoutParams);
        S();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void q() {
        super.q();
        b bVar = this.G;
        bVar.n.setOnClickListener(new vt(7, bVar));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void r() {
        this.H.y();
        if (this.G.e == 1) {
            GURL gurl = this.F;
            if (gurl == null || gurl.k()) {
                this.F = getToolbarDataProvider().d().getUrl();
            } else if (this.F.equals(getToolbarDataProvider().d().getUrl())) {
                return;
            } else {
                setUrlBarHidden(false);
            }
        }
        this.H.x();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void s(boolean z) {
        if (this.E) {
            this.D.cancel();
        }
        final ColorDrawable background = getBackground();
        final int color = background.getColor();
        final int iA = getToolbarDataProvider().a();
        if (background.getColor() == iA) {
            return;
        }
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(250L);
        this.D = duration;
        duration.setInterpolator(pc.c);
        this.D.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: m40
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                CustomTabToolbar.K(this.e, color, iA, background, valueAnimator);
            }
        });
        this.D.addListener(new a(background));
        this.D.start();
        this.E = true;
        if (z) {
            return;
        }
        this.D.end();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setCloseButtonImageResource(Drawable drawable) {
        this.y.setVisibility(drawable != null ? 0 : 8);
        this.y.setImageDrawable(drawable);
        if (drawable != null) {
            Q(this.y);
        }
    }

    public void setCloseButtonPosition(int i) {
        this.L = i;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setCustomTabCloseClickHandler(View.OnClickListener onClickListener) {
        this.y.setOnClickListener(onClickListener);
    }

    public void setHandleBackground(Drawable drawable) {
        this.A = drawable;
        setHandleViewBackgroundColor(getBackground().getColor());
    }

    public void setHandleStrategy(c cVar) {
        if (!CustomTabsConnection.e().i("CCTBrandTransparency")) {
            b bVar = this.G;
            bVar.s = true;
            bVar.r = true;
            int i = bVar.e;
            int i2 = 0;
            boolean z = i != 0;
            boolean z2 = i != 1;
            if (z) {
                bVar.t(false);
            }
            if (!z2) {
                bVar.x(false);
            }
            bVar.y();
            PostTask.b(rh3.a, bVar.u.c(new p40(bVar, i2, z, z2)), 1800L);
        }
        if (cVar == null) {
            this.K = null;
            return;
        }
        this.K = cVar;
        ImageButton imageButton = this.y;
        Objects.requireNonNull(imageButton);
        ((ew1) cVar).f = new androidx.appcompat.widget.a(28, imageButton);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setTextureCaptureMode(boolean z) {
        if (z) {
            this.f65J = O();
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setUrlBarHidden(boolean z) {
        this.G.w(z);
    }

    @Override // android.view.View
    public ColorDrawable getBackground() {
        return (ColorDrawable) super.getBackground();
    }

    public class b implements v91, UrlBar.c, f.a, View.OnLongClickListener, sb3 {
        public f f;
        public nw2<EphemeralTabCoordinator> g;
        public nw2<bi1> h;
        public h i;
        public TextView j;
        public TextView k;
        public View l;
        public View m;
        public ImageButton n;
        public r40 o;
        public boolean r;
        public boolean s;
        public Integer v;
        public int e = 0;
        public final a p = new a();
        public final Runnable[] q = new Runnable[2];
        public boolean t = true;
        public ll u = new ll();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                b bVar = b.this;
                r40 r40Var = bVar.o;
                Context context = CustomTabToolbar.this.getContext();
                if (r40Var.e) {
                    r40Var.e = false;
                    r40Var.d.setVisibility(0);
                    r40Var.d.setAlpha(0.0f);
                    float dimension = context.getResources().getDimension(R$dimen.custom_tabs_url_text_size);
                    float textSize = r40Var.c.getTextSize();
                    r40Var.c.setTextSize(0, dimension);
                    float textSize2 = textSize / r40Var.c.getTextSize();
                    int[] iArr = new int[2];
                    r40Var.c.getLocationInWindow(iArr);
                    ao3.e(r40Var.c, "CustomTabToolbarAnimationDelegate.startTitleAnimation");
                    r40Var.c.addOnLayoutChangeListener(new q40(r40Var, textSize2, iArr));
                }
            }
        }

        public b() {
        }

        public final void A() {
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            customTabToolbar.Q(customTabToolbar.y);
            int childCount = customTabToolbar.x.getChildCount();
            for (int i = 0; i < childCount; i++) {
                customTabToolbar.Q((ImageButton) customTabToolbar.x.getChildAt(i));
            }
            ImageButton imageButton = (ImageButton) customTabToolbar.findViewById(R$id.custom_tabs_sidepanel_maximize);
            if (imageButton != null) {
                customTabToolbar.Q(imageButton);
            }
            customTabToolbar.Q(customTabToolbar.G.n);
            h hVar = this.i;
            int i2 = customTabToolbar.B;
            i iVar = hVar.i;
            iVar.getClass();
            PropertyModel.p pVar = j.l;
            PropertyModel propertyModel = iVar.e;
            int iH = propertyModel.h(pVar);
            propertyModel.n(pVar, i2);
            if (iH != i2) {
                F();
            }
            this.k.setTextColor(rr1.b(customTabToolbar.getContext(), customTabToolbar.B));
        }

        public final void B() {
            Object obj = CustomTabToolbar.M;
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            ToolbarProgressBar progressBar = customTabToolbar.getProgressBar();
            if (progressBar == null) {
                return;
            }
            Context context = customTabToolbar.getContext();
            int color = customTabToolbar.getBackground().getColor();
            if (!(color == yp.a(context, false))) {
                progressBar.e(color, false);
            } else {
                progressBar.setBackgroundColor(context.getColor(org.chromium.chrome.R$color.progress_bar_bg_color_list));
                progressBar.setForegroundColor(ii2.b(context));
            }
        }

        public final void C() {
            if (this.e == 1 || this.r) {
                return;
            }
            f fVar = this.f;
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            int iC = fVar.c(DeviceFormFactor.a(customTabToolbar.getContext()));
            if (iC != 0) {
                this.n.setImageTintList(bz.b(customTabToolbar.getContext(), this.f.m()));
            }
            this.o.a(iC, this.t);
            this.n.setContentDescription(customTabToolbar.getContext().getString(this.f.p()));
        }

        @Override // org.chromium.chrome.browser.omnibox.f.a
        public final void D() {
            F();
        }

        public final void E() {
            if (this.r) {
                return;
            }
            String title = this.f.getTitle();
            if (!this.f.q() || TextUtils.isEmpty(title) || "about:blank".equals(a())) {
                this.k.setText("");
                return;
            }
            int i = this.e;
            if ((i == 2 || i == 1) && !title.equals(this.f.n()) && !title.equals("about:blank")) {
                PostTask.b(rh3.a, this.p, this.s ? 0L : 800L);
            }
            this.k.setText(title);
        }

        public final void F() {
            int length;
            int spanStart;
            CharSequence charSequence;
            if (this.r) {
                return;
            }
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            Tab currentTab = customTabToolbar.getCurrentTab();
            if (currentTab == null) {
                this.i.g(rj3.h, 0, 0);
                return;
            }
            if (this.e == 1 && !TextUtils.isEmpty(this.f.getTitle())) {
                E();
            }
            String strD = TrustedCdn.d(currentTab);
            String strA = a();
            if (jk1.p(strA, customTabToolbar.getCurrentTab().isIncognito())) {
                this.i.g(rj3.h, 0, 0);
                return;
            }
            if (strD != null) {
                String string = customTabToolbar.getContext().getString(R$string.custom_tab_amp_publisher_url, bk3.a(strD));
                Object obj = CustomTabToolbar.M;
                SpannableString spannableStringA = iq2.a(string, new iq2.a(obj, "<pub>", "</pub>"), new iq2.a(new ForegroundColorSpan(customTabToolbar.C.getDefaultColor()), "<bg>", "</bg>"));
                spanStart = spannableStringA.getSpanStart(obj);
                length = spannableStringA.getSpanEnd(obj);
                spannableStringA.removeSpan(obj);
                charSequence = spannableStringA;
            } else {
                rj3 rj3VarB = this.f.b();
                CharSequence charSequenceSubSequence = rj3VarB.b.subSequence(rj3VarB.d, rj3VarB.e);
                length = charSequenceSubSequence.length();
                spanStart = 0;
                charSequence = charSequenceSubSequence;
            }
            this.i.g(rj3.a(strA, charSequence, spanStart, length, strA), 1, 0);
        }

        @Override // org.chromium.chrome.browser.omnibox.f.a
        public final void I() {
            A();
            C();
            B();
        }

        public final String a() {
            Tab currentTab = CustomTabToolbar.this.getCurrentTab();
            if (currentTab == null) {
                return "";
            }
            String strD = TrustedCdn.d(currentTab);
            return strD != null ? strD : currentTab.getUrl().j().trim();
        }

        @Override // defpackage.v91
        public final rj3 b() {
            return this.i.i.h;
        }

        public final void c() {
            F();
            CustomTabToolbar.this.H.x();
            int i = 0;
            while (true) {
                Runnable[] runnableArr = this.q;
                if (i >= runnableArr.length) {
                    return;
                }
                Runnable runnable = runnableArr[i];
                if (runnable != null) {
                    runnable.run();
                    runnableArr[i] = null;
                }
                i++;
            }
        }

        @Override // defpackage.v91
        public final void destroy() {
            ll llVar = this.u;
            if (llVar != null) {
                llVar.a();
                this.u = null;
            }
            f fVar = this.f;
            if (fVar != null) {
                fVar.o(this);
                this.f = null;
            }
        }

        @Override // defpackage.v91
        public final View e() {
            return CustomTabToolbar.this;
        }

        @Override // org.chromium.chrome.browser.omnibox.f.a
        public final void f() {
            C();
        }

        @Override // defpackage.v91
        public final sr1 i() {
            return null;
        }

        @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
        public final View l() {
            Tab currentTab = CustomTabToolbar.this.getCurrentTab();
            if (currentTab == null) {
                return null;
            }
            return currentTab.getView();
        }

        @Override // defpackage.v91
        public final void m(boolean z) {
            if (!this.r) {
                t(z);
            } else {
                this.q[0] = new kw1(this, 1, z);
            }
        }

        @Override // defpackage.v91
        public final View o() {
            return this.n;
        }

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            Tab currentTab;
            if (view != this.m || (currentTab = CustomTabToolbar.this.getCurrentTab()) == null) {
                return false;
            }
            Clipboard.getInstance().b(currentTab.v());
            return true;
        }

        @Override // defpackage.v91
        public final void p() {
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            CustomTabToolbar.N(customTabToolbar, customTabToolbar.getBackground().getColor());
            C();
            B();
            F();
        }

        @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
        public final boolean q() {
            Object obj = CustomTabToolbar.M;
            return !CustomTabToolbar.this.k();
        }

        public final void t(boolean z) {
            if (z) {
                if (this.e == 3) {
                    this.e = 1;
                } else {
                    this.e = 2;
                }
                r40 r40Var = this.o;
                TextView textView = this.j;
                r40Var.d = this.k;
                r40Var.c = textView;
                textView.setPivotX(0.0f);
                r40Var.c.setPivotY(0.0f);
                r40Var.e = true;
            } else {
                this.e = 0;
                this.k.setVisibility(8);
            }
            CustomTabToolbar.this.H.y();
        }

        @Override // org.chromium.chrome.browser.omnibox.f.a
        public final void u() {
            E();
        }

        public final void w(boolean z) {
            if (!this.r) {
                x(z);
            } else {
                this.q[1] = new o40(this, 0, z);
            }
        }

        public final void x(boolean z) {
            int i = this.e;
            if (i == 0) {
                return;
            }
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            if (z && i == 2) {
                this.e = 1;
                this.o.e = false;
                this.j.setVisibility(8);
                this.k.setVisibility(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.k.getLayoutParams();
                layoutParams.bottomMargin = 0;
                this.k.setLayoutParams(layoutParams);
                this.k.setTextSize(0, customTabToolbar.getResources().getDimension(R$dimen.location_bar_url_text_size));
                return;
            }
            if (!z && i == 1) {
                this.e = 2;
                this.k.setVisibility(0);
                this.j.setTextSize(0, customTabToolbar.getResources().getDimension(R$dimen.custom_tabs_url_text_size));
                this.j.setVisibility(0);
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.k.getLayoutParams();
                layoutParams2.bottomMargin = customTabToolbar.getResources().getDimensionPixelSize(R$dimen.custom_tabs_toolbar_vertical_padding);
                this.k.setLayoutParams(layoutParams2);
                this.k.setTextSize(0, customTabToolbar.getResources().getDimension(R$dimen.custom_tabs_title_text_size));
                F();
                customTabToolbar.H.x();
                return;
            }
            if (CustomTabsConnection.e().i("CCTBrandTransparency")) {
                int i2 = this.e;
                if (i2 == 3) {
                    if (z) {
                        return;
                    }
                    this.e = 0;
                    this.j.setVisibility(0);
                    return;
                }
                if (i2 == 1 && z) {
                    this.o.e = false;
                    this.j.setVisibility(8);
                    this.k.setVisibility(0);
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) this.k.getLayoutParams();
                    layoutParams3.bottomMargin = 0;
                    this.k.setLayoutParams(layoutParams3);
                    this.k.setTextSize(0, customTabToolbar.getResources().getDimension(R$dimen.location_bar_url_text_size));
                }
            }
        }

        public final void y() {
            CustomTabToolbar customTabToolbar = CustomTabToolbar.this;
            this.n.setImageTintList(bz.b(customTabToolbar.getContext(), this.f.m()));
            this.o.a(R$drawable.chromelogo16, this.t);
            this.i.g(rj3.b(customTabToolbar.getContext().getString(R$string.twa_running_in_chrome)), 0, 0);
        }

        public final void z() {
            this.r = false;
            int i = 2;
            boolean z = this.v.intValue() == 1 || this.v.intValue() == 2;
            boolean z2 = this.v.intValue() == 1;
            this.v = null;
            w(z2);
            m(z);
            c();
            this.o.f = false;
            PostTask.b(rh3.c, new b50(CustomTabToolbar.this.I.v(), i, this), 3000L);
        }

        @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
        public final void P() {
        }

        @Override // defpackage.v91
        public final void g() {
        }

        @Override // defpackage.v91
        public final void n() {
        }

        @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
        public final void v(boolean z) {
        }
    }
}
