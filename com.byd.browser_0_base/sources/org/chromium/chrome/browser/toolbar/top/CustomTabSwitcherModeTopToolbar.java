package org.chromium.chrome.browser.toolbar.top;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import defpackage.bz0;
import defpackage.g90;
import defpackage.h21;
import defpackage.n3;
import defpackage.pr1;
import defpackage.rr1;
import defpackage.ut;
import defpackage.vl;
import defpackage.y13;
import defpackage.yp;
import java.util.function.BooleanSupplier;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.a;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class CustomTabSwitcherModeTopToolbar extends OptimizedFrameLayout implements View.OnClickListener, bz0.b, a.InterfaceC0115a {
    public static final /* synthetic */ int x = 0;
    public final Context f;
    public org.chromium.chrome.browser.toolbar.a g;
    public y13 h;
    public bz0 i;
    public BooleanSupplier j;
    public IncognitoToggleTabLayout k;
    public ToggleTabStackButton l;
    public ChromeImageButton m;
    public TextView n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public ObjectAnimator s;
    public i t;
    public boolean u;
    public boolean v;
    public boolean w;

    public class a extends vl {
        public final /* synthetic */ boolean f;

        public a(boolean z) {
            this.f = z;
        }

        @Override // defpackage.vl
        public final void b() {
            CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = CustomTabSwitcherModeTopToolbar.this;
            customTabSwitcherModeTopToolbar.setAlpha(1.0f);
            if (!this.f) {
                customTabSwitcherModeTopToolbar.setVisibility(8);
            }
            IncognitoToggleTabLayout incognitoToggleTabLayout = customTabSwitcherModeTopToolbar.k;
            if (incognitoToggleTabLayout != null) {
                incognitoToggleTabLayout.setClickable(true);
            }
            customTabSwitcherModeTopToolbar.s = null;
        }
    }

    public CustomTabSwitcherModeTopToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
    }

    private int getToolbarColorForCurrentState() {
        if (g90.a(getContext()) || this.u) {
            return yp.b(getContext(), this.q);
        }
        return 0;
    }

    private void setIncognitoToggleVisibility(boolean z) {
        if (m()) {
            IncognitoToggleTabLayout incognitoToggleTabLayout = this.k;
            if (incognitoToggleTabLayout != null) {
                incognitoToggleTabLayout.setVisibility(8);
                return;
            }
            if (z) {
                IncognitoToggleTabLayout incognitoToggleTabLayout2 = (IncognitoToggleTabLayout) ((ViewStub) findViewById(R$id.incognito_tabs_stub)).inflate();
                this.k = incognitoToggleTabLayout2;
                y13 y13Var = this.h;
                if (y13Var != null) {
                    incognitoToggleTabLayout2.setTabModelSelector(y13Var);
                }
            }
        }
    }

    private void setToggleTabStackButtonVisibility(boolean z) {
        ToggleTabStackButton toggleTabStackButton = this.l;
        if (toggleTabStackButton == null) {
            return;
        }
        toggleTabStackButton.setVisibility(z ? 0 : 8);
    }

    @Override // org.chromium.chrome.browser.toolbar.a.InterfaceC0115a
    public final void c(int i, boolean z) {
        Context context;
        if (this.n == null || (context = this.f) == null) {
            return;
        }
        this.n.setText(context.getResources().getString(R$string.tv_tabs_total, Integer.valueOf(i)));
    }

    @Override // bz0.b
    public final void i(boolean z) {
        this.q = z;
        n();
    }

    public final void j(boolean z, boolean z2, boolean z3, BooleanSupplier booleanSupplier, i iVar) {
        this.u = z;
        this.v = z2;
        boolean z4 = true;
        this.w = z && z3;
        this.j = booleanSupplier;
        this.t = iVar;
        setIncognitoToggleVisibility(m());
        if (this.u && this.j.getAsBoolean()) {
            z4 = false;
        }
        setToggleTabStackButtonVisibility(z4);
    }

    public final void l(boolean z) {
        setIncognitoToggleVisibility(z);
        boolean z2 = DeviceFormFactor.a(getContext()) ? true : true ^ z;
        if (z2 == this.r) {
            return;
        }
        this.r = z2;
    }

    public final boolean m() {
        return this.u && this.j.getAsBoolean() && (!DeviceFormFactor.a(getContext()) || this.v);
    }

    public final void n() {
        int toolbarColorForCurrentState = getToolbarColorForCurrentState();
        if (this.o != toolbarColorForCurrentState) {
            this.o = toolbarColorForCurrentState;
            setBackgroundColor(toolbarColorForCurrentState);
        }
        int iA = toolbarColorForCurrentState == 0 ? 3 : rr1.a(toolbarColorForCurrentState, getContext(), this.q);
        if (this.p == iA) {
            return;
        }
        this.p = iA;
        ToggleTabStackButton toggleTabStackButton = this.l;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setBrandedColorScheme(iA);
        }
        ColorStateList colorStateListY0 = n3.y0(getContext());
        ChromeImageButton chromeImageButton = this.m;
        if (chromeImageButton != null) {
            chromeImageButton.setImageTintList(colorStateListY0);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        boolean z;
        if (this.m == null || R$id.btn_back != view.getId()) {
            return;
        }
        ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) getContext();
        TabModel tabModelD2 = chromeTabbedActivity.D2();
        if (tabModelD2 == null || tabModelD2.getCount() != 0) {
            z = false;
        } else {
            tabModelD2.c();
            chromeTabbedActivity.C2().f(19);
            z = true;
        }
        if (z) {
            return;
        }
        chromeTabbedActivity.J2();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0051  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onFinishInflate() {
        /*
            r4 = this;
            super.onFinishInflate()
            int r0 = org.chromium.chrome.R$id.tab_switcher_mode_tab_switcher_button
            android.view.View r0 = r4.findViewById(r0)
            org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton r0 = (org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton) r0
            r4.l = r0
            int r0 = org.chromium.chrome.R$id.btn_back
            android.view.View r0 = r4.findViewById(r0)
            org.chromium.ui.widget.ChromeImageButton r0 = (org.chromium.ui.widget.ChromeImageButton) r0
            r4.m = r0
            r0.setOnClickListener(r4)
            int r0 = org.chromium.chrome.R$id.tv_tab_count
            android.view.View r0 = r4.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r4.n = r0
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r0 = (android.widget.FrameLayout.LayoutParams) r0
            java.lang.String r1 = defpackage.oj2.m()
            int r2 = r1.hashCode()
            r3 = 1307376552(0x4decfba8, float:4.9698944E8)
            if (r2 == r3) goto L47
            r3 = 1418153572(0x54874e64, float:4.649086E12)
            if (r2 == r3) goto L3d
            goto L51
        L3d:
            java.lang.String r2 = "8155f_dynasty_def"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L51
            r1 = 1
            goto L52
        L47:
            java.lang.String r2 = "8155f_denza_def"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L51
            r1 = 0
            goto L52
        L51:
            r1 = -1
        L52:
            if (r1 == 0) goto L67
            r1 = 8388627(0x800013, float:1.175497E-38)
            r0.gravity = r1
            android.content.res.Resources r4 = r4.getResources()
            int r1 = org.chromium.chrome.R$dimen.dimen_100_dp
            int r4 = r4.getDimensionPixelOffset(r1)
            r0.setMarginStart(r4)
            goto L78
        L67:
            r1 = 17
            r0.gravity = r1
            android.content.res.Resources r4 = r4.getResources()
            int r1 = org.chromium.chrome.R$dimen.dimen_0_dp
            int r4 = r4.getDimensionPixelOffset(r1)
            r0.setMarginStart(r4)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.CustomTabSwitcherModeTopToolbar.onFinishInflate():void");
    }

    public void setIncognitoStateProvider(bz0 bz0Var) {
        this.i = bz0Var;
        bz0Var.a(this);
    }

    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
        ToggleTabStackButton toggleTabStackButton = this.l;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setOnTabSwitcherClickHandler(onClickListener);
        }
    }

    public void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
        this.g = aVar;
        ToggleTabStackButton toggleTabStackButton = this.l;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setTabCountProvider(aVar);
        }
        aVar.a(this);
    }

    public void setTabModelSelector(y13 y13Var) {
        this.h = y13Var;
        IncognitoToggleTabLayout incognitoToggleTabLayout = this.k;
        if (incognitoToggleTabLayout != null) {
            incognitoToggleTabLayout.setTabModelSelector(y13Var);
        }
    }

    public void setTabSwitcherMode(boolean z) {
        IncognitoToggleTabLayout incognitoToggleTabLayout;
        if (!z && (incognitoToggleTabLayout = this.k) != null) {
            incognitoToggleTabLayout.setClickable(false);
        }
        if (this.v) {
            return;
        }
        ObjectAnimator objectAnimator = this.s;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        setVisibility(0);
        setAlpha(z ? 0.0f : 1.0f);
        long j = this.w ? 150L : 200L;
        Property property = View.ALPHA;
        float[] fArr = new float[1];
        fArr[0] = z ? 1.0f : 0.0f;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<CustomTabSwitcherModeTopToolbar, Float>) property, fArr);
        this.s = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(j);
        if (this.w && z) {
            this.s.setStartDelay(j);
        }
        this.s.setInterpolator(h21.f);
        this.s.addListener(new a(z));
        if (pr1.b()) {
            this.s.addUpdateListener(new ut(3, this));
        }
        this.s.start();
        if (g90.a(getContext())) {
            this.s.end();
        }
    }

    public void setOnNewTabClickHandler(View.OnClickListener onClickListener) {
    }
}
