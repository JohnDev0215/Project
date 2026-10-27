package org.chromium.chrome.browser.toolbar.top;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bz0;
import defpackage.g90;
import defpackage.h21;
import defpackage.le0;
import defpackage.n3;
import defpackage.pr1;
import defpackage.rr1;
import defpackage.vl;
import defpackage.wm3;
import defpackage.y13;
import defpackage.yp;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.toolbar.NewTabButton;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class TabSwitcherModeTopToolbar extends OptimizedFrameLayout implements View.OnClickListener, bz0.b {
    public static final /* synthetic */ int q = 0;
    public View.OnClickListener f;
    public y13 g;
    public bz0 h;
    public View i;
    public NewTabButton j;
    public ToggleTabStackButton k;
    public MenuButton l;
    public int m;
    public int n;
    public boolean o;
    public ObjectAnimator p;

    public class a extends vl {
        public final /* synthetic */ boolean f;

        public a(boolean z) {
            this.f = z;
        }

        @Override // defpackage.vl
        public final void b() {
            TabSwitcherModeTopToolbar tabSwitcherModeTopToolbar = TabSwitcherModeTopToolbar.this;
            tabSwitcherModeTopToolbar.setAlpha(1.0f);
            if (!this.f) {
                tabSwitcherModeTopToolbar.setVisibility(8);
            }
            tabSwitcherModeTopToolbar.p = null;
        }
    }

    public TabSwitcherModeTopToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private int getToolbarColorForCurrentState() {
        if (g90.a(getContext())) {
            return yp.b(getContext(), this.o);
        }
        return 0;
    }

    private void setToggleTabStackButtonVisibility(boolean z) {
        ToggleTabStackButton toggleTabStackButton = this.k;
        if (toggleTabStackButton == null) {
            return;
        }
        toggleTabStackButton.setVisibility(z ? 0 : 8);
    }

    @Override // bz0.b
    public final void i(boolean z) {
        this.o = z;
        int toolbarColorForCurrentState = getToolbarColorForCurrentState();
        if (this.m != toolbarColorForCurrentState) {
            this.m = toolbarColorForCurrentState;
            setBackgroundColor(toolbarColorForCurrentState);
        }
        int iA = toolbarColorForCurrentState == 0 ? 3 : rr1.a(toolbarColorForCurrentState, getContext(), this.o);
        if (this.n == iA) {
            return;
        }
        this.n = iA;
        ToggleTabStackButton toggleTabStackButton = this.k;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setBrandedColorScheme(iA);
        }
        ColorStateList colorStateListY0 = n3.y0(getContext());
        View view = this.i;
        if (view != null) {
            ((ImageView) view.findViewById(R$id.new_tab_view_button)).setImageTintList(colorStateListY0);
            ((TextView) this.i.findViewById(R$id.new_tab_view_desc)).setTextColor(colorStateListY0.getDefaultColor());
        }
        MenuButton menuButton = this.l;
        if (menuButton != null) {
            menuButton.e(iA, colorStateListY0);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.j == view || this.i == view) {
            view.setEnabled(false);
            View.OnClickListener onClickListener = this.f;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.j = (NewTabButton) findViewById(R$id.new_tab_button);
        this.i = findViewById(R$id.new_tab_view);
        this.k = (ToggleTabStackButton) findViewById(R$id.tab_switcher_mode_tab_switcher_button);
        this.l = (MenuButton) findViewById(R$id.menu_button_wrapper);
        this.j.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    public void setIncognitoStateProvider(bz0 bz0Var) {
        this.h = bz0Var;
        bz0Var.a(this);
        NewTabButton newTabButton = this.j;
        if (newTabButton != null) {
            newTabButton.setIncognitoStateProvider(this.h);
        }
    }

    public void setNewTabButtonEnabled(boolean z) {
        this.i.setEnabled(z);
        this.j.setEnabled(z);
    }

    public void setNewTabButtonHighlight(boolean z) {
        NewTabButton newTabButton = this.j;
        if (newTabButton == null) {
            return;
        }
        if (z) {
            wm3.b(newTabButton, new wm3.a(1));
        } else {
            wm3.a(newTabButton);
        }
    }

    public void setOnNewTabClickHandler(View.OnClickListener onClickListener) {
        this.f = onClickListener;
    }

    public void setOnTabSwitcherClickHandler(View.OnClickListener onClickListener) {
        ToggleTabStackButton toggleTabStackButton = this.k;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setOnTabSwitcherClickHandler(onClickListener);
        }
    }

    public void setTabCountProvider(org.chromium.chrome.browser.toolbar.a aVar) {
        ToggleTabStackButton toggleTabStackButton = this.k;
        if (toggleTabStackButton != null) {
            toggleTabStackButton.setTabCountProvider(aVar);
        }
    }

    public void setTabModelSelector(y13 y13Var) {
        this.g = y13Var;
    }

    public void setTabSwitcherMode(boolean z) {
        if (z) {
            NewTabButton newTabButton = this.j;
            if (newTabButton != null) {
                newTabButton.setEnabled(true);
            }
            View view = this.i;
            if (view != null) {
                view.setEnabled(true);
            }
        }
        ObjectAnimator objectAnimator = this.p;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        setVisibility(0);
        setAlpha(z ? 0.0f : 1.0f);
        Property property = View.ALPHA;
        float[] fArr = new float[1];
        fArr[0] = z ? 1.0f : 0.0f;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<TabSwitcherModeTopToolbar, Float>) property, fArr);
        this.p = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        this.p.setInterpolator(h21.f);
        this.p.addListener(new a(z));
        if (pr1.b()) {
            this.p.addUpdateListener(new le0(2, this));
        }
        this.p.start();
        if (g90.a(getContext())) {
            this.p.end();
        }
    }

    private void setIncognitoToggleVisibility(boolean z) {
    }
}
