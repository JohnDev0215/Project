package com.google.android.material.internal;

import F1.H;
import Q4.d;
import U4.e;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import q.InterfaceC2158o;
import q.MenuItemC2152i;
import r.B0;
import r.C2234W;
import x1.AbstractC2671j;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends e implements InterfaceC2158o {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final int[] f12849Q = {R.attr.state_checked};

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f12850G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f12851H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f12852I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final CheckedTextView f12853J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public FrameLayout f12854K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public MenuItemC2152i f12855L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public ColorStateList f12856M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f12857N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public Drawable f12858O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final d f12859P;

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d dVar = new d(this, 2);
        this.f12859P = dVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(com.stingray.drivemodedetector.R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(com.stingray.drivemodedetector.R.dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(com.stingray.drivemodedetector.R.id.design_menu_item_text);
        this.f12853J = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        H.g(checkedTextView, dVar);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.f12854K == null) {
                this.f12854K = (FrameLayout) ((ViewStub) findViewById(com.stingray.drivemodedetector.R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.f12854K.removeAllViews();
            this.f12854K.addView(view);
        }
    }

    @Override // q.InterfaceC2158o
    public final void b(MenuItemC2152i menuItemC2152i) {
        StateListDrawable stateListDrawable;
        this.f12855L = menuItemC2152i;
        int i7 = menuItemC2152i.f21067a;
        if (i7 > 0) {
            setId(i7);
        }
        setVisibility(menuItemC2152i.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(com.stingray.drivemodedetector.R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(f12849Q, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            int[] iArr = H.f2303a;
            setBackground(stateListDrawable);
        }
        setCheckable(menuItemC2152i.isCheckable());
        setChecked(menuItemC2152i.isChecked());
        setEnabled(menuItemC2152i.isEnabled());
        setTitle(menuItemC2152i.f21069e);
        setIcon(menuItemC2152i.getIcon());
        View view = menuItemC2152i.f21088z;
        if (view == null) {
            view = null;
        }
        setActionView(view);
        setContentDescription(menuItemC2152i.f21079q);
        B0.a(this, menuItemC2152i.f21080r);
        MenuItemC2152i menuItemC2152i2 = this.f12855L;
        CharSequence charSequence = menuItemC2152i2.f21069e;
        CheckedTextView checkedTextView = this.f12853J;
        if (charSequence == null && menuItemC2152i2.getIcon() == null) {
            View view2 = this.f12855L.f21088z;
            if ((view2 != null ? view2 : null) != null) {
                checkedTextView.setVisibility(8);
                FrameLayout frameLayout = this.f12854K;
                if (frameLayout != null) {
                    C2234W c2234w = (C2234W) frameLayout.getLayoutParams();
                    ((LinearLayout.LayoutParams) c2234w).width = -1;
                    this.f12854K.setLayoutParams(c2234w);
                    return;
                }
                return;
            }
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.f12854K;
        if (frameLayout2 != null) {
            C2234W c2234w2 = (C2234W) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) c2234w2).width = -2;
            this.f12854K.setLayoutParams(c2234w2);
        }
    }

    @Override // q.InterfaceC2158o
    public MenuItemC2152i getItemData() {
        return this.f12855L;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 1);
        MenuItemC2152i menuItemC2152i = this.f12855L;
        if (menuItemC2152i != null && menuItemC2152i.isCheckable() && this.f12855L.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f12849Q);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z6) {
        refreshDrawableState();
        if (this.f12852I != z6) {
            this.f12852I = z6;
            this.f12859P.h(this.f12853J, 2048);
        }
    }

    public void setChecked(boolean z6) {
        refreshDrawableState();
        this.f12853J.setChecked(z6);
    }

    public void setHorizontalPadding(int i7) {
        setPadding(i7, 0, i7, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.f12857N) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                drawable.setTintList(this.f12856M);
            }
            int i7 = this.f12850G;
            drawable.setBounds(0, 0, i7, i7);
        } else if (this.f12851H) {
            if (this.f12858O == null) {
                Resources resources = getResources();
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal threadLocal = AbstractC2671j.f23708a;
                Drawable drawable2 = resources.getDrawable(com.stingray.drivemodedetector.R.drawable.navigation_empty_icon, theme);
                this.f12858O = drawable2;
                if (drawable2 != null) {
                    int i10 = this.f12850G;
                    drawable2.setBounds(0, 0, i10, i10);
                }
            }
            drawable = this.f12858O;
        }
        this.f12853J.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i7) {
        this.f12853J.setCompoundDrawablePadding(i7);
    }

    public void setIconSize(int i7) {
        this.f12850G = i7;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.f12856M = colorStateList;
        this.f12857N = colorStateList != null;
        MenuItemC2152i menuItemC2152i = this.f12855L;
        if (menuItemC2152i != null) {
            setIcon(menuItemC2152i.getIcon());
        }
    }

    public void setMaxLines(int i7) {
        this.f12853J.setMaxLines(i7);
    }

    public void setNeedsEmptyIcon(boolean z6) {
        this.f12851H = z6;
    }

    public void setTextAppearance(int i7) {
        this.f12853J.setTextAppearance(i7);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.f12853J.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.f12853J.setText(charSequence);
    }
}
