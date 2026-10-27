package org.chromium.chrome.browser.toolbar.top;

import android.R;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import defpackage.C0173a;
import defpackage.af1;
import defpackage.ak;
import defpackage.ao3;
import defpackage.bk3;
import defpackage.bq;
import defpackage.bt1;
import defpackage.cc3;
import defpackage.ct1;
import defpackage.df1;
import defpackage.dr;
import defpackage.et2;
import defpackage.g90;
import defpackage.gm;
import defpackage.gv;
import defpackage.h21;
import defpackage.ii2;
import defpackage.jb1;
import defpackage.jg2;
import defpackage.km1;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pc;
import defpackage.pk3;
import defpackage.po3;
import defpackage.pr1;
import defpackage.rj3;
import defpackage.rr1;
import defpackage.u02;
import defpackage.v91;
import defpackage.vj;
import defpackage.vl;
import defpackage.w43;
import defpackage.wc3;
import defpackage.wt2;
import defpackage.x10;
import defpackage.xc3;
import defpackage.xt2;
import defpackage.yp;
import defpackage.z7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.toolbar.HomeButton;
import org.chromium.chrome.browser.toolbar.a;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.chrome.browser.toolbar.top.m;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ToolbarPhone extends ToolbarLayout implements View.OnClickListener, a.InterfaceC0115a {
    public static final /* synthetic */ int I0 = 0;
    public HomeButton A;
    public int A0;
    public TextView B;
    public float B0;
    public View C;
    public int C0;
    public ImageView D;
    public int D0;
    public org.chromium.chrome.browser.toolbar.optional_button.b E;
    public boolean E0;
    public boolean F;
    public int F0;
    public final boolean G;
    public boolean G0;

    @ViewDebug.ExportedProperty(category = "chrome")
    public int H;
    public final a H0;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    @ViewDebug.ExportedProperty(category = "chrome")
    public boolean f84J;
    public boolean K;
    public vj L;
    public int M;
    public w43 N;

    @ViewDebug.ExportedProperty(category = "chrome")
    public boolean O;

    @ViewDebug.ExportedProperty(category = "chrome")
    public float P;

    @ViewDebug.ExportedProperty(category = "chrome")
    public float Q;
    public AnimatorSet R;
    public boolean S;
    public boolean T;
    public boolean U;
    public int V;
    public int W;
    public boolean a0;
    public int b0;
    public float c0;
    public float d0;
    public ColorDrawable e0;
    public GradientDrawable f0;
    public Drawable g0;
    public boolean h0;
    public final Rect i0;
    public final Rect j0;
    public final Rect k0;
    public float l0;
    public float m0;
    public final Rect n0;
    public final Point o0;
    public final int p0;
    public final int q0;
    public ValueAnimator r0;
    public boolean s0;
    public boolean t0;
    public Runnable u0;
    public int v0;
    public org.chromium.chrome.browser.toolbar.a w;
    public int w0;
    public org.chromium.chrome.browser.omnibox.c x;
    public u02 x0;
    public ViewGroup y;
    public ak y0;
    public ToggleTabStackButton z;
    public boolean z0;

    public class a extends FloatProperty<ToolbarPhone> {
        public a() {
            super("");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((ToolbarPhone) obj).P);
        }

        @Override // android.util.FloatProperty
        public final void setValue(ToolbarPhone toolbarPhone, float f) {
            ToolbarPhone.this.setUrlFocusChangeFraction(f);
        }
    }

    public class b extends vl {
        public final /* synthetic */ boolean f;
        public final /* synthetic */ boolean g;

        public b(boolean z, boolean z2) {
            this.f = z;
            this.g = z2;
        }

        @Override // defpackage.vl
        public final void a() {
            boolean z = this.f;
            ToolbarPhone toolbarPhone = ToolbarPhone.this;
            if (!z) {
                toolbarPhone.S = false;
            }
            toolbarPhone.O = false;
        }

        @Override // defpackage.vl
        public final void b() {
            ToolbarPhone toolbarPhone = ToolbarPhone.this;
            boolean z = this.f;
            if (!z) {
                toolbarPhone.S = false;
                toolbarPhone.T = false;
                ao3.e(toolbarPhone, "ToolbarPhone.triggerUrlFocusAnimation.CancelAwareAnimatorListener.onEnd");
            }
            org.chromium.chrome.browser.omnibox.c cVar = toolbarPhone.x;
            boolean zK = toolbarPhone.getToolbarDataProvider().k();
            cVar.k.Q(z, this.g);
            if (zK) {
                cVar.j.f();
            }
            toolbarPhone.O = false;
        }

        @Override // defpackage.vl
        public final void c() {
            boolean z = this.f;
            ToolbarPhone toolbarPhone = ToolbarPhone.this;
            if (!z) {
                toolbarPhone.S = true;
            } else {
                toolbarPhone.T = true;
                ao3.e(toolbarPhone, "ToolbarPhone.triggerUrlFocusAnimation.CancelAwareAnimatorListener.onStart");
            }
        }
    }

    public class c implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ boolean e;
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;
        public final /* synthetic */ int h;
        public final /* synthetic */ int i;
        public final /* synthetic */ int j;
        public final /* synthetic */ int k;

        public c(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.e = z;
            this.f = i;
            this.g = i2;
            this.h = i3;
            this.i = i4;
            this.j = i5;
            this.k = i6;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            boolean z = this.e;
            ToolbarPhone toolbarPhone = ToolbarPhone.this;
            if (z) {
                float f = this.f;
                toolbarPhone.b0 = (int) C0173a.f(this.g, f, animatedFraction, f);
            }
            int iO = z7.o(this.h, this.i, animatedFraction, false);
            int i = ToolbarPhone.I0;
            toolbarPhone.e0(iO);
            toolbarPhone.Z(z7.o(this.j, this.k, animatedFraction, false));
        }
    }

    public class d extends vl {
        public d() {
        }

        @Override // defpackage.vl
        public final void b() {
            ToolbarPhone toolbarPhone = ToolbarPhone.this;
            toolbarPhone.s0 = false;
            toolbarPhone.i0();
        }
    }

    public static class e extends DrawableWrapperCompat {
        public final Drawable.Callback f;
        public int g;
        public int h;
        public int i;
        public int j;
        public boolean k;
        public boolean l;

        public e(Context context, Drawable.Callback callback) {
            super(context.getDrawable(R$drawable.ntp_search_box));
            this.f = callback;
            setCallback(callback);
        }

        @Override // android.graphics.drawable.Drawable
        public final Drawable.Callback getCallback() {
            return this.l ? super.getCallback() : this.f;
        }

        @Override // android.graphics.drawable.Drawable
        public final void setBounds(int i, int i2, int i3, int i4) {
            super.setBounds(i, i2, i3, i4);
            if (this.k) {
                this.l = false;
            } else {
                this.g = i;
                this.h = i2;
                this.i = i3;
                this.j = i4;
                this.l = true;
            }
            this.k = false;
        }

        @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
        public final boolean setVisible(boolean z, boolean z2) {
            return false;
        }
    }

    public ToolbarPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.M = -1;
        this.b0 = 255;
        this.c0 = -1.0f;
        this.d0 = -1.0f;
        this.i0 = new Rect();
        this.j0 = new Rect();
        this.k0 = new Rect();
        this.n0 = new Rect();
        this.o0 = new Point();
        int dimensionPixelSize = 0;
        this.A0 = 0;
        this.B0 = 1.0f;
        this.H0 = new a();
        boolean zE = pr1.e(context);
        this.G = zE;
        this.p0 = getResources().getDimensionPixelOffset(zE ? R$dimen.toolbar_edge_padding_modern : R$dimen.toolbar_edge_padding);
        if (zE) {
            dimensionPixelSize = getResources().getDimensionPixelSize(pr1.d() ? R$dimen.toolbar_url_focus_height_increase_active_color : R$dimen.toolbar_url_focus_height_increase_no_active_color);
        }
        this.q0 = dimensionPixelSize;
    }

    private int getAdditionalOffsetForNTP() {
        return getResources().getDimensionPixelSize(this.G ? R$dimen.location_bar_start_padding_modern : R$dimen.location_bar_start_padding) - getResources().getDimensionPixelSize(R$dimen.fake_search_box_lateral_padding);
    }

    private int getBoundsAfterAccountingForLeftButton() {
        if (this.A.getVisibility() == 8) {
            if (!(getToolbarDataProvider().e() && this.d0 != 1.0f)) {
                return this.p0;
            }
        }
        return this.A.getMeasuredWidth();
    }

    private int getBoundsAfterAccountingForRightButtons() {
        float f = this.d0;
        int i = this.p0;
        if (f == 1.0f) {
            return i;
        }
        int measuredWidth = this.y.getMeasuredWidth();
        if (this.E0) {
            measuredWidth = this.y.getWidth();
        }
        return Math.max(i, measuredWidth);
    }

    private int getFocusedLeftPositionOfLocationBarBackground() {
        return this.p0;
    }

    private int getFocusedRightPositionOfLocationBarBackground() {
        return getWidth() - this.p0;
    }

    private int getProgressBarColor() {
        return getToolbarDataProvider().a();
    }

    private gm getReadinessStateWithSuppression() {
        int i = 4;
        if (this.o) {
            return gm.b(4);
        }
        if (this.O) {
            return gm.b(5);
        }
        if (this.E0) {
            return gm.b(6);
        }
        et2 et2Var = this.x.j;
        if (et2Var != null) {
            StatusView statusView = et2Var.e;
            if (statusView.o || statusView.p || statusView.q) {
                return gm.b(7);
            }
        }
        if ((this.H != 0) || this.z0) {
            return gm.b(10);
        }
        u02 u02VarM = M();
        u02 u02Var = this.x0;
        if (u02Var == null) {
            i = 1;
        } else if (u02VarM.a != u02Var.a) {
            i = 2;
        } else if (u02VarM.b != u02Var.b) {
            i = 3;
        } else if (Objects.equals(u02VarM.c, u02Var.c)) {
            i = u02VarM.d != u02Var.d ? 5 : u02VarM.f != u02Var.f ? 6 : u02VarM.h != u02Var.h ? 7 : u02VarM.i != u02Var.i ? 8 : u02VarM.j != u02Var.j ? 10 : !Objects.equals(u02VarM.e, u02Var.e) ? 11 : u02VarM.g.getDefaultColor() != u02Var.g.getDefaultColor() ? 12 : 0;
        }
        return i == 0 ? gm.b(3) : new gm(2, 0, i, true);
    }

    private int getTabThemeColor() {
        return getToolbarDataProvider() != null ? getToolbarDataProvider().a() : P(k() ? 1 : 0);
    }

    private int getToolbarButtonVisibility() {
        return (this.Q == 1.0f || getToolbarDataProvider().e()) ? 4 : 0;
    }

    private int getToolbarDefaultColor() {
        if (!this.G || !((org.chromium.chrome.browser.omnibox.d) this.x.f).a.hasFocus()) {
            return yp.a(getContext(), k());
        }
        if (this.G0) {
            return k() ? getContext().getColor(bq.d.a() ? R$color.default_bg_color_dark_elev_2_gm3_baseline : R$color.default_bg_color_dark_elev_2_baseline) : yp.c(getContext(), R$dimen.toolbar_text_box_elevation);
        }
        org.chromium.chrome.browser.omnibox.c cVar = this.x;
        return k() ? cVar.t : cVar.s;
    }

    private void setAncestorsShouldClipChildren(boolean z) {
        if (T()) {
            ao3.f(this, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUrlFocusChangeFraction(float f) {
        this.P = f;
        h0();
        g0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final boolean A(boolean z) {
        if (!z) {
            this.K = false;
            return false;
        }
        if (this.x0 == null) {
            this.x0 = M();
        }
        boolean z2 = this.x0.a != getTint().getDefaultColor();
        this.K = z2;
        w43 w43Var = this.N;
        if (w43Var != null && this.z != null) {
            this.K = z2 || this.x0.b != w43Var.f;
        }
        return this.K;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void B(boolean z) {
        int i;
        int i2;
        if (getToolbarDataProvider().k()) {
            this.z.setClickable(false);
            return;
        }
        if (z && ((i2 = this.H) == 1 || i2 == 2)) {
            return;
        }
        if (z || !((i = this.H) == 0 || i == 3)) {
            this.z.setClickable(false);
            this.H = z ? 2 : 3;
            ao3.e(this, "ToolbarPhone.setTabSwitcherMode");
            this.x.c(false);
            c0();
            if (z) {
                AnimatorSet animatorSet = this.R;
                if (animatorSet != null && animatorSet.isRunning()) {
                    this.R.end();
                    this.R = null;
                    int measuredWidth = getMeasuredWidth();
                    TraceEvent.q("ToolbarPhone.layoutLocationBar", null);
                    if (U(measuredWidth)) {
                        Y();
                    }
                    TraceEvent.C("ToolbarPhone.layoutLocationBar");
                }
                setVisibility(this.H == 1 ? 4 : 0);
                c0();
                d0();
                ToggleTabStackButton toggleTabStackButton = this.z;
                if (toggleTabStackButton != null && (this.H == 2 || this.z0)) {
                    Drawable background = toggleTabStackButton.getBackground();
                    if (background instanceof RippleDrawable) {
                        ((RippleDrawable) background).jumpToCurrentState();
                    }
                }
            }
            float fMin = this.H == 0 ? Math.min(this.o0.y, 0) : 0;
            this.y.setTranslationY(fMin);
            this.A.setTranslationY(fMin);
            if (g90.a(getContext())) {
                c0();
            }
            postInvalidateOnAnimation();
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final boolean C() {
        return super.C() || this.Q > 0.0f || ((float) this.o0.y) < 0.0f;
    }

    @Override // defpackage.vr1
    public final void D0() {
        if (this.G) {
            this.G0 = false;
            wt2 wt2Var = this.x.j.f;
            wt2Var.getClass();
            wt2Var.e.l(xt2.f, false);
            int toolbarDefaultColor = getToolbarDefaultColor();
            e0(toolbarDefaultColor);
            Z(O(toolbarDefaultColor));
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void F() {
        if (!this.t0 || getToolbarDataProvider().e()) {
            this.A.setVisibility(8);
        } else {
            this.A.setVisibility(this.o ? 4 : 0);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void I(ak akVar) {
        ViewStub viewStub;
        this.y0 = akVar;
        ak.a aVarB = akVar.b();
        org.chromium.chrome.browser.toolbar.optional_button.b bVar = this.E;
        if (bVar == null && bVar == null && (viewStub = (ViewStub) findViewById(R$id.optional_button_stub)) != null) {
            viewStub.setLayoutResource(R$layout.optional_button_layout);
            org.chromium.chrome.browser.toolbar.optional_button.b bVar2 = new org.chromium.chrome.browser.toolbar.optional_button.b(viewStub.inflate(), new pk3((Activity) getContext(), new Handler()), this.y, new xc3(this), oj2.t(Profile.d()));
            this.E = bVar2;
            bVar2.a.a.n(ct1.h, this.w0);
            this.E.a.a.p(ct1.e, new wc3(this, 0));
            this.E.a.a.p(ct1.c, new jb1(15, this));
            this.E.e = new dr(20, this);
        }
        this.F = aVarB.e;
        this.E.b(akVar);
        if (!this.F) {
            this.E.a.a.p(ct1.g, null);
        } else {
            this.E.a.a.p(ct1.g, getTint());
        }
    }

    public final boolean L(Canvas canvas, long j) {
        TraceEvent.q("ToolbarPhone.drawLocationBar", null);
        boolean z = false;
        if (this.f0 != null && (this.H == 0 || this.f84J)) {
            canvas.save();
            if ((((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getAlpha() > 0.0f || this.h0) && !this.f84J) {
                z = true;
            }
            Rect rect = this.k0;
            Rect rect2 = this.i0;
            if (z) {
                Drawable drawable = this.g0;
                if (drawable instanceof e) {
                    ((e) drawable).k = true;
                }
                drawable.setBounds(rect2.left + rect.left, rect2.top + rect.top, rect2.right + rect.right, rect2.bottom + rect.bottom);
                this.g0.draw(canvas);
            }
            float f = rect2.left + rect.left;
            float f2 = rect2.right + rect.right;
            float f3 = rect2.top + rect.top;
            float f4 = rect2.bottom + rect.bottom;
            int paddingStart = ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getPaddingStart();
            int paddingEnd = ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getPaddingEnd();
            int layoutDirection = ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getLayoutDirection();
            if (this.Q != 1.0f && !this.E0) {
                int iQ = this.W - Q(this.A0);
                int iR = (R(this.A0) - this.W) - this.V;
                float f5 = 1.0f - this.Q;
                f += iQ * f5;
                f2 -= iR * f5;
                if (layoutDirection == 1) {
                    f += paddingStart * f5;
                } else {
                    f2 -= paddingEnd * f5;
                }
            }
            if (this.E0) {
                if (layoutDirection == 1) {
                    f += paddingStart;
                } else {
                    f2 -= paddingEnd;
                }
            }
            jg2 jg2VarA = jg2.a();
            boolean zK = k();
            jg2VarA.getClass();
            if ((!zK) && T() && this.o && this.O) {
                float f6 = paddingStart;
                if (layoutDirection == 1) {
                    f2 -= f6;
                } else {
                    f += f6;
                }
            }
            canvas.clipRect(f, f3, f2, f4);
            z = true;
        }
        boolean zDrawChild = super.drawChild(canvas, ((org.chromium.chrome.browser.omnibox.d) this.x.f).a, j);
        if (z) {
            canvas.restore();
        }
        TraceEvent.C("ToolbarPhone.drawLocationBar");
        return zDrawChild;
    }

    public final u02 M() {
        rj3 rj3VarB;
        int iC;
        if (cc3.b()) {
            rj3VarB = this.x.b();
            if (rj3VarB == null) {
                rj3VarB = getToolbarDataProvider().b();
            }
            et2 et2Var = this.x.j;
            iC = et2Var == null ? getToolbarDataProvider().c(false) : et2Var.f.u;
        } else {
            rj3VarB = getToolbarDataProvider().b();
            iC = getToolbarDataProvider().c(false);
        }
        int i = iC;
        po3 po3Var = new po3(rj3VarB.b, this.x.h.f.getVisibleTextPrefixHint());
        int defaultColor = getTint().getDefaultColor();
        int i2 = this.w.e;
        ak akVar = this.y0;
        int i3 = this.A0;
        ColorStateList imageTintList = this.A.getImageTintList();
        boolean z = ((df1.a) getMenuButtonCoordinator().b.j(df1.h)).a;
        boolean zH = getToolbarDataProvider().h();
        getProgressBar().getProgress();
        return new u02(defaultColor, i2, akVar, i3, po3Var, i, imageTintList, z, zH, this.V);
    }

    public final int N(int i) {
        return n3.x0(i, getContext(), k());
    }

    public final int O(int i) {
        if (!this.G || !((org.chromium.chrome.browser.omnibox.d) this.x.f).a.hasFocus()) {
            return N(i);
        }
        if (!pr1.d()) {
            return getToolbarDefaultColor();
        }
        org.chromium.chrome.browser.omnibox.c cVar = this.x;
        return k() ? cVar.v : cVar.u;
    }

    public final int P(int i) {
        getResources();
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? ii2.a(getContext()) : !getToolbarDataProvider().g().a() ? yp.a(getContext(), false) : gv.g(yp.a(getContext(), false), Math.round(this.Q * 255.0f)) : getToolbarDataProvider().a() : yp.a(getContext(), true) : yp.a(getContext(), false);
    }

    public final int Q(int i) {
        return (i == 3 && this.H == 0) ? this.p0 : getLayoutDirection() == 1 ? getBoundsAfterAccountingForRightButtons() : getBoundsAfterAccountingForLeftButton();
    }

    public final int R(int i) {
        int measuredWidth;
        int boundsAfterAccountingForRightButtons;
        if (i == 3 && this.H == 0) {
            measuredWidth = getMeasuredWidth();
            boundsAfterAccountingForRightButtons = this.p0;
        } else if (getLayoutDirection() == 1) {
            measuredWidth = getMeasuredWidth();
            boundsAfterAccountingForRightButtons = getBoundsAfterAccountingForLeftButton();
        } else {
            measuredWidth = getMeasuredWidth();
            boundsAfterAccountingForRightButtons = getBoundsAfterAccountingForRightButtons();
        }
        return measuredWidth - boundsAfterAccountingForRightButtons;
    }

    public final boolean S() {
        return (getToolbarDataProvider() == null || getToolbarDataProvider().d() == null || !getToolbarDataProvider().d().m()) ? false : true;
    }

    public final boolean T() {
        return getToolbarDataProvider().g().j();
    }

    @SuppressLint({"RtlHardcoded"})
    public final boolean U(int i) {
        int i2;
        int i3;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLocationBar().e().getLayoutParams();
        layoutParams.gravity = 51;
        boolean zF0 = f0();
        if (this.T || (this.A0 == 3 && this.H == 0)) {
            int iA = ((org.chromium.chrome.browser.omnibox.d) this.x.f).a();
            int i4 = this.p0;
            i2 = (i - (i4 * 2)) + iA;
            if (((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getLayoutDirection() != 1) {
                i4 -= iA;
            }
            i3 = i4;
        } else {
            i2 = this.V;
            i3 = this.W;
        }
        if (this.U) {
            float locationBarWidthOffsetForOptionalButton = getLocationBarWidthOffsetForOptionalButton();
            if (getLayoutDirection() == 1) {
                i3 -= (int) locationBarWidthOffsetForOptionalButton;
            }
            i2 += (int) locationBarWidthOffsetForOptionalButton;
        }
        boolean z = (i2 != layoutParams.width) | zF0;
        layoutParams.width = i2;
        boolean z2 = z | (i3 != layoutParams.leftMargin);
        layoutParams.leftMargin = i3;
        return z2;
    }

    public final void V() {
        ImageView imageView;
        this.k0.setEmpty();
        this.g0 = this.f0;
        this.o0.set(0, 0);
        ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.setTranslationY(0.0f);
        if (!this.O) {
            this.y.setTranslationY(0.0f);
            this.A.setTranslationY(0.0f);
        }
        if (!this.O && (imageView = this.D) != null) {
            imageView.setAlpha(this.B.hasFocus() ? 0.0f : 1.0f);
        }
        ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.setAlpha(1.0f);
        this.h0 = false;
        this.b0 = 255;
        if (k() || (this.a0 && !this.O && !((org.chromium.chrome.browser.omnibox.d) this.x.f).a.hasFocus())) {
            this.b0 = 51;
        }
        setAncestorsShouldClipChildren(true);
        this.c0 = -1.0f;
        h0();
    }

    public final void W(boolean z) {
        ImageView imageView;
        boolean z2 = this.o;
        TraceEvent.q("ToolbarPhone.triggerUrlFocusAnimation", null);
        AnimatorSet animatorSet = this.R;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.R.cancel();
            this.R = null;
        }
        if (this.E0) {
            bt1 bt1Var = this.E.a;
            bt1Var.getClass();
            bt1Var.a.l(ct1.j, true);
        }
        ArrayList arrayList = new ArrayList();
        a aVar = this.H0;
        if (z) {
            TraceEvent.q("ToolbarPhone.populateUrlFocusingAnimatorSet", null);
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, aVar, 1.0f);
            objectAnimatorOfFloat.setDuration(225L);
            pc pcVar = pc.c;
            objectAnimatorOfFloat.setInterpolator(pcVar);
            arrayList.add(objectAnimatorOfFloat);
            ((org.chromium.chrome.browser.omnibox.d) this.x.f).b(arrayList, 0L, 225L, 0.0f);
            float f = getContext().getResources().getDisplayMetrics().density;
            boolean z3 = getLayoutDirection() == 1;
            int i = this.F0;
            if (z3) {
                i = -i;
            }
            float f2 = i * f;
            AnimatorSet animatorSetB = getMenuButtonCoordinator().b(true);
            animatorSetB.setDuration(100L);
            pc pcVar2 = pc.d;
            animatorSetB.setInterpolator(pcVar2);
            arrayList.add(animatorSetB);
            HomeButton homeButton = this.A;
            Property property = FrameLayout.TRANSLATION_X;
            float[] fArr = new float[1];
            float f3 = (-homeButton.getWidth()) * f;
            if (z3) {
                f3 = -f3;
            }
            fArr[0] = f3;
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(homeButton, (Property<HomeButton, Float>) property, fArr);
            objectAnimatorOfFloat2.setDuration(100L);
            objectAnimatorOfFloat2.setInterpolator(pcVar2);
            arrayList.add(objectAnimatorOfFloat2);
            ToggleTabStackButton toggleTabStackButton = this.z;
            if (toggleTabStackButton != null) {
                ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(toggleTabStackButton, (Property<ToggleTabStackButton, Float>) FrameLayout.TRANSLATION_X, f2);
                objectAnimatorOfFloat3.setDuration(100L);
                objectAnimatorOfFloat3.setInterpolator(pcVar2);
                arrayList.add(objectAnimatorOfFloat3);
                ObjectAnimator objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(this.z, (Property<ToggleTabStackButton, Float>) FrameLayout.ALPHA, 0.0f);
                objectAnimatorOfFloat4.setDuration(100L);
                objectAnimatorOfFloat4.setInterpolator(pcVar2);
                arrayList.add(objectAnimatorOfFloat4);
            }
            ImageView imageView2 = this.D;
            if (imageView2 != null) {
                Property property2 = FrameLayout.ALPHA;
                float[] fArr2 = new float[1];
                fArr2[0] = this.o ? 0.0f : 1.0f;
                ObjectAnimator objectAnimatorOfFloat5 = ObjectAnimator.ofFloat(imageView2, (Property<ImageView, Float>) property2, fArr2);
                objectAnimatorOfFloat5.setDuration(225L);
                objectAnimatorOfFloat5.setInterpolator(pcVar);
                arrayList.add(objectAnimatorOfFloat5);
            }
            TraceEvent.C("ToolbarPhone.populateUrlFocusingAnimatorSet");
        } else {
            ObjectAnimator objectAnimatorOfFloat6 = ObjectAnimator.ofFloat(this, aVar, 0.0f);
            objectAnimatorOfFloat6.setDuration(225L);
            pc pcVar3 = pc.c;
            objectAnimatorOfFloat6.setInterpolator(pcVar3);
            arrayList.add(objectAnimatorOfFloat6);
            AnimatorSet animatorSetB2 = getMenuButtonCoordinator().b(false);
            animatorSetB2.setDuration(100L);
            pc pcVar4 = pc.d;
            animatorSetB2.setInterpolator(pcVar4);
            arrayList.add(animatorSetB2);
            ObjectAnimator objectAnimatorOfFloat7 = ObjectAnimator.ofFloat(this.A, (Property<HomeButton, Float>) FrameLayout.TRANSLATION_X, 0.0f);
            objectAnimatorOfFloat7.setDuration(100L);
            objectAnimatorOfFloat7.setInterpolator(pcVar4);
            arrayList.add(objectAnimatorOfFloat7);
            ToggleTabStackButton toggleTabStackButton2 = this.z;
            if (toggleTabStackButton2 != null) {
                ObjectAnimator objectAnimatorOfFloat8 = ObjectAnimator.ofFloat(toggleTabStackButton2, (Property<ToggleTabStackButton, Float>) FrameLayout.TRANSLATION_X, 0.0f);
                objectAnimatorOfFloat8.setDuration(100L);
                objectAnimatorOfFloat8.setStartDelay(200L);
                objectAnimatorOfFloat8.setInterpolator(pcVar3);
                arrayList.add(objectAnimatorOfFloat8);
                ObjectAnimator objectAnimatorOfFloat9 = ObjectAnimator.ofFloat(this.z, (Property<ToggleTabStackButton, Float>) FrameLayout.ALPHA, 1.0f);
                objectAnimatorOfFloat9.setDuration(100L);
                objectAnimatorOfFloat9.setStartDelay(200L);
                objectAnimatorOfFloat9.setInterpolator(pcVar3);
                arrayList.add(objectAnimatorOfFloat9);
            }
            ((org.chromium.chrome.browser.omnibox.d) this.x.f).b(arrayList, 100L, 250L, 1.0f);
            if ((!T() || this.c0 != 0.0f) && (imageView = this.D) != null) {
                ObjectAnimator objectAnimatorOfFloat10 = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) FrameLayout.ALPHA, 1.0f);
                objectAnimatorOfFloat10.setDuration(225L);
                objectAnimatorOfFloat10.setInterpolator(pcVar3);
                arrayList.add(objectAnimatorOfFloat10);
            }
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.R = animatorSet2;
        animatorSet2.playTogether(arrayList);
        this.O = true;
        this.R.addListener(new b(z, z2));
        this.R.start();
        TraceEvent.C("ToolbarPhone.triggerUrlFocusAnimation");
    }

    public final void X(int i, Rect rect) {
        float f = (i == 3 && this.H == 0) ? 1.0f : this.Q;
        float fQ = Q(i);
        int iF = (int) C0173a.f(getFocusedLeftPositionOfLocationBarBackground(), fQ, f, fQ);
        float f2 = (i == 3 && this.H == 0) ? 1.0f : this.Q;
        float fR = R(i);
        int iF2 = (int) C0173a.f(getFocusedRightPositionOfLocationBarBackground(), fR, f2, fR);
        int i2 = this.v0 - ((int) ((this.q0 * this.P) / 2.0f));
        rect.set(iF, ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getTop() + i2, iF2, ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getBottom() - i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Y() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.ToolbarPhone.Y():void");
    }

    public final void Z(int i) {
        if (this.w0 == i) {
            return;
        }
        this.w0 = i;
        this.f0.setTint(i);
        org.chromium.chrome.browser.toolbar.optional_button.b bVar = this.E;
        if (bVar != null) {
            bVar.a.a.n(ct1.h, i);
        }
    }

    public final void a0() {
        km1 km1VarG = getToolbarDataProvider().g();
        boolean zG = km1VarG.g();
        float f = this.c0;
        V();
        km1VarG.c(new x10(7, this));
        if (km1VarG.j()) {
            e eVar = new e(getContext(), this);
            getToolbarDataProvider().g().h(eVar);
            this.g0 = eVar;
            ao3.e(this, "ToolbarPhone.updateNtpAnimationState showing LocationBar");
            return;
        }
        if (zG) {
            if (this.H == 0 && f > 0.0f) {
                this.P = Math.max(f, this.P);
                W(false);
            }
            ao3.e(this, "ToolbarPhone.updateNtpAnimationState showing ntp");
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void b() {
        c0();
    }

    public final void b0() {
        int i = this.H;
        if (i == 1 || i == 2) {
            return;
        }
        float f = 0.0f;
        boolean z = this.Q > 0.0f;
        setAncestorsShouldClipChildren(!z);
        if (!this.O) {
            if (!this.B.hasFocus() && this.c0 == 1.0f) {
                f = 1.0f;
            }
            this.D.setAlpha(f);
        }
        km1 km1VarG = getToolbarDataProvider().g();
        if (km1VarG.a()) {
            Point point = this.o0;
            Rect rect = this.n0;
            km1VarG.d(rect, point);
            int iMax = Math.max(0, rect.top - ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getTop());
            ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.setTranslationY(iMax);
            float fMin = this.H == 0 ? Math.min(point.y, 0) : 0;
            this.y.setTranslationY(fMin);
            this.A.setTranslationY(fMin);
            float interpolation = 1.0f - h21.e.getInterpolation(this.Q);
            int i2 = rect.left;
            Rect rect2 = this.i0;
            int i3 = i2 - rect2.left;
            int i4 = rect.right - rect2.right;
            int dimensionPixelSize = (int) ((1.0f - this.Q) * getResources().getDimensionPixelSize(R$dimen.ntp_search_box_bounds_vertical_inset_modern));
            float f2 = i3 * interpolation;
            int iRound = Math.round(f2);
            float f3 = i4 * interpolation;
            int iRound2 = Math.round(f3);
            Rect rect3 = this.k0;
            rect3.set(iRound, iMax, iRound2, iMax);
            rect3.inset(0, dimensionPixelSize);
            this.l0 = f2;
            this.m0 = f3;
        }
        int i5 = z ? 255 : 0;
        this.b0 = i5;
        this.h0 = i5 > 0;
        float f4 = i5 / 255.0f;
        ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.setAlpha(f4);
        km1VarG.k(1.0f - f4);
        if (!this.h0) {
            Drawable drawable = this.g0;
            if (drawable instanceof e) {
                e eVar = (e) drawable;
                eVar.setBounds(eVar.g, eVar.h, eVar.i, eVar.j);
            }
        }
        e0(P(this.A0));
    }

    @Override // org.chromium.chrome.browser.toolbar.a.InterfaceC0115a
    public final void c(int i, boolean z) {
        this.A.setEnabled(true);
        if (this.z == null) {
            return;
        }
        int iA = rr1.a(getTabThemeColor(), getContext(), z);
        if (this.N == null || this.D0 != iA) {
            w43 w43VarD = w43.d(getContext(), iA);
            this.N = w43VarD;
            w43VarD.setState(new int[]{R.attr.state_enabled});
            this.D0 = iA;
        }
        w43 w43Var = this.N;
        if (w43Var != null) {
            w43Var.e(i, z);
        }
    }

    public final void c0() {
        getProgressBar().setVisibility((this.H != 0 || getToolbarDataProvider().e() || this.z0) ? 4 : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d0() {
        /*
            r3 = this;
            int r0 = r3.H
            r1 = 0
            if (r0 != 0) goto L34
            boolean r0 = r3.I
            if (r0 != 0) goto L34
            boolean r0 = r3.k()
            r2 = 1
            if (r0 == 0) goto L20
            bc3 r0 = r3.getToolbarDataProvider()
            org.chromium.url.GURL r0 = r0.i()
            boolean r0 = defpackage.bk3.l(r0)
            if (r0 == 0) goto L20
            r0 = r2
            goto L21
        L20:
            r0 = r1
        L21:
            if (r0 != 0) goto L34
            boolean r0 = r3.S()
            if (r0 != 0) goto L34
            int r0 = r3.getVisibility()
            if (r0 != 0) goto L34
            boolean r0 = r3.z0
            if (r0 != 0) goto L34
            goto L35
        L34:
            r2 = r1
        L35:
            if (r2 == 0) goto L38
            goto L39
        L38:
            r1 = 4
        L39:
            android.widget.ImageView r0 = r3.D
            if (r0 == 0) goto L48
            int r0 = r0.getVisibility()
            if (r0 == r1) goto L48
            android.widget.ImageView r3 = r3.D
            r3.setVisibility(r1)
        L48:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.ToolbarPhone.d0():void");
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, defpackage.w80
    public final void destroy() {
        AnimatorSet animatorSet = this.R;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.R.cancel();
        }
        ValueAnimator valueAnimator = this.r0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.r0.cancel();
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.destroy();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (!this.f84J && this.e0.getColor() != 0) {
            this.e0.setBounds(0, 0, getWidth(), getHeight());
            this.e0.draw(canvas);
        }
        if (this.f0 != null && (((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getVisibility() == 0 || this.f84J)) {
            X(this.A0, this.i0);
        }
        if (!this.f84J) {
            super.dispatchDraw(canvas);
            return;
        }
        if (this.n) {
            canvas.save();
            canvas.clipRect(this.j0);
            if (this.A.getVisibility() != 8) {
                float alpha = this.A.getAlpha();
                this.A.setAlpha(alpha * 1.0f);
                drawChild(canvas, this.A, SystemClock.uptimeMillis());
                this.A.setAlpha(alpha);
            }
            float alpha2 = ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getAlpha();
            ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.setAlpha(alpha2 * 1.0f);
            if (((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getAlpha() != 0.0f) {
                if (!T() || this.Q > 0.0f) {
                    L(canvas, SystemClock.uptimeMillis());
                }
            }
            ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.setAlpha(alpha2);
            ao3.h(this, this.y, canvas);
            org.chromium.chrome.browser.toolbar.optional_button.b bVar = this.E;
            if (bVar != null && bVar.b.getVisibility() != 8) {
                canvas.save();
                ao3.h(this.y, this.E.b, canvas);
                this.E.b.draw(canvas);
                canvas.restore();
            }
            if (this.N != null && this.z != null && this.Q != 1.0f) {
                canvas.save();
                ao3.h(this.y, this.z, canvas);
                canvas.translate(this.z.getPaddingLeft() + ((((this.z.getWidth() - this.z.getPaddingLeft()) - this.z.getPaddingRight()) - this.z.getDrawable().getIntrinsicWidth()) / 2), this.z.getPaddingTop() + ((((this.z.getHeight() - this.z.getPaddingTop()) - this.z.getPaddingBottom()) - this.z.getDrawable().getIntrinsicHeight()) / 2));
                this.N.setBounds(this.z.getDrawable().getBounds());
                this.N.setAlpha(255);
                this.N.draw(canvas);
                canvas.restore();
            }
            af1 menuButtonCoordinator = getMenuButtonCoordinator();
            if (menuButtonCoordinator != null) {
                ViewGroup viewGroup = this.y;
                canvas.save();
                ao3.h(viewGroup, menuButtonCoordinator.e, canvas);
                Drawable tabSwitcherAnimationDrawable = menuButtonCoordinator.e.getTabSwitcherAnimationDrawable();
                tabSwitcherAnimationDrawable.setAlpha(255);
                tabSwitcherAnimationDrawable.draw(canvas);
                canvas.restore();
            }
            canvas.restore();
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.view.View
    public final void draw(Canvas canvas) {
        if (this.v.e) {
            return;
        }
        super.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        org.chromium.chrome.browser.omnibox.c cVar = this.x;
        if (cVar != null && view == ((org.chromium.chrome.browser.omnibox.d) cVar.f).a) {
            return L(canvas, j);
        }
        if (this.f0 != null && cVar != null) {
            canvas.save();
            int translationY = (int) ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getTranslationY();
            Rect rect = this.i0;
            int top = rect.top + translationY;
            if (this.Q != 0.0f && top < view.getBottom()) {
                boolean zIsLayoutRtl = (view == this.A) ^ LocalizationUtils.isLayoutRtl();
                int i = rect.bottom + translationY;
                if (translationY > 0.0f) {
                    top = view.getTop();
                    i = top;
                    z = true;
                } else {
                    z = false;
                }
                if (zIsLayoutRtl) {
                    canvas.clipRect(0, top, z ? view.getMeasuredWidth() : rect.left, i);
                } else {
                    canvas.clipRect(z ? 0 : rect.right, top, getMeasuredWidth(), i);
                }
            }
            i = 1;
        }
        boolean zDrawChild = super.drawChild(canvas, view, j);
        if (i != 0) {
            canvas.restore();
        }
        return zDrawChild;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, org.chromium.chrome.browser.theme.ThemeColorProvider.b
    public final void e(int i, ColorStateList colorStateList) {
        this.A.setImageTintList(colorStateList);
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setBrandedColorScheme(i);
            w43 w43Var = this.N;
            if (w43Var != null) {
                w43Var.c(colorStateList);
            }
        }
        org.chromium.chrome.browser.toolbar.optional_button.b bVar = this.E;
        if (bVar != null && this.F) {
            bVar.a.a.p(ct1.g, colorStateList);
        }
        org.chromium.chrome.browser.omnibox.c cVar = this.x;
        if (cVar != null) {
            cVar.p();
        }
        Runnable runnable = this.u0;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void e0(int i) {
        if (this.e0.getColor() == i) {
            return;
        }
        this.e0.setColor(i);
        setToolbarHairlineColor(i);
        invalidate();
        m.b bVar = this.f;
        if (bVar != null) {
            bVar.c(i);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void f(Rect rect) {
        X(0, rect);
    }

    public final boolean f0() {
        int iQ = Q(this.A0);
        int iR = R(this.A0);
        this.W = iQ;
        int i = iR - iQ;
        if (this.V == i) {
            return false;
        }
        this.V = i;
        this.x.k.f.setUnfocusedWidth(i);
        return true;
    }

    public final void g0() {
        if (this.H != 0) {
            return;
        }
        int toolbarButtonVisibility = getToolbarButtonVisibility();
        this.y.setVisibility(toolbarButtonVisibility);
        if (this.A.getVisibility() != 8) {
            this.A.setVisibility(toolbarButtonVisibility);
        }
        Y();
    }

    public ColorDrawable getBackgroundDrawable() {
        return this.e0;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public HomeButton getHomeButton() {
        return this.A;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public v91 getLocationBar() {
        return this.x;
    }

    public float getLocationBarWidthOffsetForOptionalButton() {
        float width = this.E.b.getWidth();
        MenuButton menuButton = getMenuButtonCoordinator().e;
        return !(menuButton != null && menuButton.getVisibility() == 0) ? width - this.p0 : width;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public View getOptionalButtonViewForTesting() {
        org.chromium.chrome.browser.toolbar.optional_button.b bVar = this.E;
        if (bVar != null) {
            return bVar.a();
        }
        return null;
    }

    public int getToolbarBackgroundColor() {
        return this.e0.getColor();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void h(boolean z) {
        setVisibility(z ? 8 : this.H == 0 ? 0 : 4);
    }

    public final void h0() {
        this.Q = Math.max(Math.max(this.c0, this.d0), this.P);
        Iterator<m.c> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((m.c) aVar.next()).b();
            }
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void i() {
        this.y0 = null;
        org.chromium.chrome.browser.toolbar.optional_button.b bVar = this.E;
        if (bVar == null || bVar.b.getVisibility() == 8 || this.U) {
            return;
        }
        org.chromium.chrome.browser.toolbar.optional_button.b bVar2 = this.E;
        bVar2.f = null;
        bt1 bt1Var = bVar2.a;
        bt1Var.getClass();
        bt1Var.a.p(ct1.a, null);
    }

    public final void i0() {
        Runnable runnable;
        HomeButton homeButton;
        TraceEvent.q("ToolbarPhone.updateVisualsForLocationBarState", null);
        int i = this.H;
        boolean z = i == 0 || i == 3;
        int i2 = T() ? 3 : k() ? 1 : getToolbarDataProvider().t() ? 2 : 0;
        if (i2 != 3 || (homeButton = this.A) == null) {
            this.A.setAccessibilityTraversalBefore(-1);
        } else {
            homeButton.setAccessibilityTraversalBefore(R$id.toolbar_buttons);
        }
        if (this.s0) {
            int i3 = this.A0;
            if (i3 == 0 || i3 == 2) {
                if (i2 == 0 || i2 == 2) {
                    TraceEvent.C("ToolbarPhone.updateVisualsForLocationBarState");
                    return;
                }
            }
        }
        ValueAnimator valueAnimator = this.r0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.r0.end();
        }
        boolean z2 = this.A0 != i2;
        int iA = getToolbarDataProvider().a();
        int progressBarColor = getProgressBarColor();
        if (getToolbarDataProvider() != null && getToolbarDataProvider().d() != null && getToolbarDataProvider().d().isNativePage()) {
            progressBarColor = P(k() ? 1 : 0);
        }
        boolean z3 = z2;
        z3 = z2;
        if (this.A0 == 2 && !z2) {
            if ((!z7.i0(iA)) != this.a0) {
                z3 = true;
            } else {
                e0(P(2));
                getProgressBar().e(progressBarColor, k());
                z3 = z2;
            }
        }
        this.A0 = i2;
        if ((i2 == 2 || z3) && (runnable = this.u0) != null) {
            runnable.run();
        }
        d0();
        g0();
        if (this.H != 3) {
            e0(P(this.A0));
        }
        if (!z3) {
            if (this.A0 == 3) {
                b0();
            } else {
                V();
            }
            TraceEvent.C("ToolbarPhone.updateVisualsForLocationBarState");
            return;
        }
        this.a0 = false;
        this.b0 = 255;
        getProgressBar().e(progressBarColor, k());
        if (k()) {
            this.b0 = 51;
        } else if (this.A0 == 2) {
            boolean z4 = !z7.i0(iA);
            this.a0 = z4;
            this.b0 = z4 ? 51 : 255;
        }
        Z(N(iA));
        this.x.p();
        if (T() && z) {
            b0();
        }
        getMenuButtonCoordinator().c(true);
        TraceEvent.C("ToolbarPhone.updateVisualsForLocationBarState");
    }

    @Override // defpackage.vr1
    public final void k0() {
        if (this.G) {
            this.G0 = true;
            wt2 wt2Var = this.x.j.f;
            wt2Var.getClass();
            wt2Var.e.l(xt2.f, true);
            int toolbarDefaultColor = getToolbarDefaultColor();
            e0(toolbarDefaultColor);
            Z(O(toolbarDefaultColor));
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final gm l() {
        if (cc3.a()) {
            return gm.b(8);
        }
        if (this.K) {
            return new gm(1, 0, 0, true);
        }
        if (cc3.b()) {
            return getReadinessStateWithSuppression();
        }
        return new gm(0, 0, 0, (this.o || this.O) ? false : true);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void n() {
        Handler handler = getHandler();
        if (handler == null) {
            return;
        }
        handler.post(new wc3(this, 1));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void o(boolean z) {
        this.t0 = z;
        F();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.D = (ImageView) getRootView().findViewById(R$id.toolbar_hairline);
        d0();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        org.chromium.chrome.browser.omnibox.c cVar = this.x;
        if ((cVar == null || !((org.chromium.chrome.browser.omnibox.d) cVar.f).a.hasFocus()) && this.A == view) {
            z();
            if (this.n && this.m.getAsBoolean()) {
                oj2.t(getToolbarDataProvider().f()).notifyEvent("partner_home_page_button_pressed");
            }
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.view.View
    public final void onFinishInflate() {
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarPhone.onFinishInflate", null);
        try {
            super.onFinishInflate();
            this.y = (ViewGroup) findViewById(R$id.toolbar_buttons);
            this.A = (HomeButton) findViewById(R$id.home_button);
            this.B = (TextView) findViewById(R$id.url_bar);
            this.C = findViewById(R$id.url_action_container);
            this.e0 = new ColorDrawable(P(0));
            setLayoutTransition(null);
            if (getMenuButtonCoordinator() != null) {
                getMenuButtonCoordinator().c(true);
            }
            ToggleTabStackButton toggleTabStackButton = (ToggleTabStackButton) findViewById(R$id.tab_switcher_button);
            this.z = toggleTabStackButton;
            toggleTabStackButton.setClickable(false);
            setWillNotDraw(false);
            this.F0 = getResources().getDimensionPixelSize(R$dimen.toolbar_url_focus_translation_x);
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

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f = this.c0;
        if (f == 0.0f || f == 1.0f || f == -1.0f) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.v.e) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.S) {
            f0();
        } else {
            super.onMeasure(i, i2);
            boolean zU = U(View.MeasureSpec.getSize(i));
            g0();
            if (!zU) {
                return;
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        this.j0.set(0, 0, i, i2);
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return (this.o0.y >= 0 || ((org.chromium.chrome.browser.omnibox.d) this.x.f).a.getTranslationY() <= 0.0f) ? super.onTouchEvent(motionEvent) : getToolbarDataProvider().g().dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        F();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void q() {
        super.q();
        this.z.setOnKeyListener(new j(this));
        this.A.setOnClickListener(this);
        getMenuButtonCoordinator().setOnKeyListener(new k(this));
        i0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void s(boolean z) {
        if (this.s0) {
            this.r0.end();
        }
        int color = this.e0.getColor();
        int iA = getToolbarDataProvider().a();
        if (color == iA) {
            return;
        }
        int iN = N(color);
        int iN2 = N(iA);
        int i = this.A0;
        if (i == 0 || i == 2) {
            if (!z) {
                e0(iA);
                return;
            }
            boolean zI0 = z7.i0(iA);
            int i2 = this.b0;
            int i3 = zI0 ? 255 : 51;
            boolean z2 = i2 != i3;
            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(250L);
            this.r0 = duration;
            duration.setInterpolator(pc.c);
            this.r0.addUpdateListener(new c(z2, i2, i3, color, iA, iN, iN2));
            this.r0.addListener(new d());
            this.r0.start();
            this.s0 = true;
            Runnable runnable = this.u0;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setBrowserControlsVisibilityDelegate(vj vjVar) {
        this.L = vjVar;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setContentAttached(boolean z) {
        i0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setForceHideShadow(boolean z) {
        this.I = z;
        d0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setLayoutUpdater(Runnable runnable) {
        this.u0 = runnable;
    }

    public void setLocationBarBackgroundDrawableForTesting(GradientDrawable gradientDrawable) {
        this.f0 = gradientDrawable;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setLocationBarCoordinator(org.chromium.chrome.browser.omnibox.c cVar) {
        this.x = cVar;
        this.v0 = getResources().getDimensionPixelSize(R$dimen.location_bar_vertical_margin);
        Context context = getContext();
        GradientDrawable gradientDrawable = (GradientDrawable) context.getDrawable(R$drawable.modern_toolbar_text_box_background_with_primary_color);
        gradientDrawable.mutate();
        gradientDrawable.setTint(yp.c(context, R$dimen.toolbar_text_box_elevation));
        this.f0 = gradientDrawable;
        this.g0 = gradientDrawable;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setOnTabSwitcherClickHandler(onClickListener);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setOnTabSwitcherLongClickHandler(View.OnLongClickListener onLongClickListener) {
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setOnTabSwitcherLongClickHandler(onLongClickListener);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
        this.w = aVar;
        aVar.a.a(this);
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setTabCountProvider(aVar);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public void setTextureCaptureMode(boolean z) {
        this.f84J = z;
        if (!z) {
            setAlpha(this.B0);
            setVisibility(this.C0);
            d0();
            this.B0 = 1.0f;
            this.x0 = M();
            return;
        }
        if (!(k() && bk3.l(getToolbarDataProvider().i())) && !S()) {
            if (!(!k() && bk3.l(getToolbarDataProvider().i()) && this.c0 < 1.0f) && !this.I) {
                this.D.setVisibility(0);
            }
        }
        this.B0 = getAlpha();
        this.C0 = getVisibility();
        setAlpha(1.0f);
        setVisibility(0);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void t(boolean z, boolean z2, boolean z3) {
        org.chromium.chrome.browser.omnibox.c cVar = this.x;
        if (cVar == null || cVar.q) {
            return;
        }
        this.z0 = z3;
        setVisibility(z ? 0 : 8);
        c0();
        d0();
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null && (this.H == 2 || this.z0)) {
            Drawable background = toggleTabStackButton.getBackground();
            if (background instanceof RippleDrawable) {
                ((RippleDrawable) background).jumpToCurrentState();
            }
        }
        this.x.c(true);
        float f = (z && z2) ? 1.0f : 0.0f;
        if (this.d0 != f) {
            this.d0 = f;
            h0();
        }
        i0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void u() {
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setClickable(true);
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void v() {
        a0();
        i0();
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.g0;
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void w() {
        a0();
        i0();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void x() {
        setAlpha(1.0f);
        if (this.H == 3) {
            this.x.c(true);
            this.H = 0;
            i0();
        }
        if (this.H == 2) {
            this.H = 1;
            this.z.setClickable(false);
        }
        if (this.H == 0) {
            this.z.setClickable(true);
        }
        ao3.e(this, "ToolbarPhone.onTabSwitcherTransitionFinished");
        c0();
        i0();
        setVisibility(this.H == 1 ? 4 : 0);
        c0();
        d0();
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton == null) {
            return;
        }
        if (this.H == 2 || this.z0) {
            Drawable background = toggleTabStackButton.getBackground();
            if (background instanceof RippleDrawable) {
                ((RippleDrawable) background).jumpToCurrentState();
            }
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void y(boolean z) {
        this.o = z;
        if (z) {
            this.G0 = false;
            this.g0 = this.f0;
        } else if (T()) {
            e eVar = new e(getContext(), this);
            getToolbarDataProvider().g().h(eVar);
            this.g0 = eVar;
        }
        if (!z && this.G && !pr1.d()) {
            wt2 wt2Var = this.x.j.f;
            wt2Var.getClass();
            wt2Var.e.l(xt2.f, false);
        }
        ToggleTabStackButton toggleTabStackButton = this.z;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setClickable(!z);
        }
        W(z);
    }

    @Override // org.chromium.chrome.browser.toolbar.top.ToolbarLayout
    public final void p() {
    }
}
