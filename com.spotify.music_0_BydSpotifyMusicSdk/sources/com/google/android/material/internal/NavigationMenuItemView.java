package com.google.android.material.internal;

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
import java.util.WeakHashMap;
import p.bk2;
import p.cf1;
import p.fw0;
import p.im2;
import p.it2;
import p.ja1;
import p.k42;
import p.pj1;
import p.s42;
import p.xi1;
import p.ya0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends fw0 implements pj1 {
    public static final int[] N = {R.attr.state_checked};
    public int D;
    public boolean E;
    public boolean F;
    public final CheckedTextView G;
    public FrameLayout H;
    public xi1 I;
    public ColorStateList J;
    public boolean K;
    public Drawable L;
    public final cf1 M;

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cf1 cf1Var = new cf1(this, 2);
        this.M = cf1Var;
        setOrientation(0);
        LayoutInflater.from(context).inflate(com.spotify.music.R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(com.spotify.music.R.dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(com.spotify.music.R.id.design_menu_item_text);
        this.G = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        zt2.k(checkedTextView, cf1Var);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.H == null) {
                this.H = (FrameLayout) ((ViewStub) findViewById(com.spotify.music.R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.H.removeAllViews();
            this.H.addView(view);
        }
    }

    @Override // p.pj1
    public final void c(xi1 xi1Var) {
        StateListDrawable stateListDrawable;
        this.I = xi1Var;
        int i = xi1Var.a;
        if (i > 0) {
            setId(i);
        }
        setVisibility(xi1Var.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(com.spotify.music.R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(N, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            WeakHashMap weakHashMap = zt2.a;
            it2.q(this, stateListDrawable);
        }
        setCheckable(xi1Var.isCheckable());
        setChecked(xi1Var.isChecked());
        setEnabled(xi1Var.isEnabled());
        setTitle(xi1Var.e);
        setIcon(xi1Var.getIcon());
        setActionView(xi1Var.getActionView());
        setContentDescription(xi1Var.q);
        im2.a(this, xi1Var.r);
        xi1 xi1Var2 = this.I;
        CharSequence charSequence = xi1Var2.e;
        CheckedTextView checkedTextView = this.G;
        if (charSequence == null && xi1Var2.getIcon() == null && this.I.getActionView() != null) {
            checkedTextView.setVisibility(8);
            FrameLayout frameLayout = this.H;
            if (frameLayout != null) {
                ja1 ja1Var = (ja1) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) ja1Var).width = -1;
                this.H.setLayoutParams(ja1Var);
                return;
            }
            return;
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.H;
        if (frameLayout2 != null) {
            ja1 ja1Var2 = (ja1) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) ja1Var2).width = -2;
            this.H.setLayoutParams(ja1Var2);
        }
    }

    @Override // p.pj1
    public xi1 getItemData() {
        return this.I;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        xi1 xi1Var = this.I;
        if (xi1Var != null && xi1Var.isCheckable() && this.I.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, N);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.F != z) {
            this.F = z;
            this.M.h(this.G, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.G.setChecked(z);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, getPaddingTop(), i, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.K) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                ya0.h(drawable, this.J);
            }
            int i = this.D;
            drawable.setBounds(0, 0, i, i);
        } else if (this.E) {
            if (this.L == null) {
                Resources resources = getResources();
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal threadLocal = s42.a;
                Drawable drawableA = k42.a(resources, com.spotify.music.R.drawable.navigation_empty_icon, theme);
                this.L = drawableA;
                if (drawableA != null) {
                    int i2 = this.D;
                    drawableA.setBounds(0, 0, i2, i2);
                }
            }
            drawable = this.L;
        }
        bk2.e(this.G, drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.G.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.D = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.J = colorStateList;
        this.K = colorStateList != null;
        xi1 xi1Var = this.I;
        if (xi1Var != null) {
            setIcon(xi1Var.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.G.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.E = z;
    }

    public void setTextAppearance(int i) {
        this.G.setTextAppearance(i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.G.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.G.setText(charSequence);
    }
}
