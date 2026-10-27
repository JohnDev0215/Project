package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
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
import androidx.appcompat.view.menu.k;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.u0;
import i0.u;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends d implements k.a {
    private static final int[] K = {R.attr.state_checked};
    private int A;
    private boolean B;
    boolean C;
    private final CheckedTextView D;
    private FrameLayout E;
    private androidx.appcompat.view.menu.g F;
    private ColorStateList G;
    private boolean H;
    private Drawable I;
    private final i0.a J;

    class a extends i0.a {
        a() {
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            cVar.N(NavigationMenuItemView.this.C);
        }
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void B() {
        if (D()) {
            this.D.setVisibility(8);
            FrameLayout frameLayout = this.E;
            if (frameLayout != null) {
                LinearLayoutCompat.a aVar = (LinearLayoutCompat.a) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) aVar).width = -1;
                this.E.setLayoutParams(aVar);
                return;
            }
            return;
        }
        this.D.setVisibility(0);
        FrameLayout frameLayout2 = this.E;
        if (frameLayout2 != null) {
            LinearLayoutCompat.a aVar2 = (LinearLayoutCompat.a) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) aVar2).width = -2;
            this.E.setLayoutParams(aVar2);
        }
    }

    private StateListDrawable C() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(e.a.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(K, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    private boolean D() {
        return this.F.getTitle() == null && this.F.getIcon() == null && this.F.getActionView() != null;
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.E == null) {
                this.E = (FrameLayout) ((ViewStub) findViewById(o2.f.design_menu_item_action_area_stub)).inflate();
            }
            this.E.removeAllViews();
            this.E.addView(view);
        }
    }

    @Override // androidx.appcompat.view.menu.k.a
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k.a
    public void e(androidx.appcompat.view.menu.g gVar, int i7) {
        this.F = gVar;
        if (gVar.getItemId() > 0) {
            setId(gVar.getItemId());
        }
        setVisibility(gVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            u.n0(this, C());
        }
        setCheckable(gVar.isCheckable());
        setChecked(gVar.isChecked());
        setEnabled(gVar.isEnabled());
        setTitle(gVar.getTitle());
        setIcon(gVar.getIcon());
        setActionView(gVar.getActionView());
        setContentDescription(gVar.getContentDescription());
        u0.a(this, gVar.getTooltipText());
        B();
    }

    @Override // androidx.appcompat.view.menu.k.a
    public androidx.appcompat.view.menu.g getItemData() {
        return this.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 1);
        androidx.appcompat.view.menu.g gVar = this.F;
        if (gVar != null && gVar.isCheckable() && this.F.isChecked()) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, K);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z6) {
        refreshDrawableState();
        if (this.C != z6) {
            this.C = z6;
            this.J.l(this.D, 2048);
        }
    }

    public void setChecked(boolean z6) {
        refreshDrawableState();
        this.D.setChecked(z6);
    }

    public void setHorizontalPadding(int i7) {
        setPadding(i7, 0, i7, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.H) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = a0.a.l(drawable).mutate();
                a0.a.i(drawable, this.G);
            }
            int i7 = this.A;
            drawable.setBounds(0, 0, i7, i7);
        } else if (this.B) {
            if (this.I == null) {
                Drawable drawableB = y.b.b(getResources(), o2.e.navigation_empty_icon, getContext().getTheme());
                this.I = drawableB;
                if (drawableB != null) {
                    int i8 = this.A;
                    drawableB.setBounds(0, 0, i8, i8);
                }
            }
            drawable = this.I;
        }
        androidx.core.widget.i.i(this.D, drawable, null, null, null);
    }

    public void setIconPadding(int i7) {
        this.D.setCompoundDrawablePadding(i7);
    }

    public void setIconSize(int i7) {
        this.A = i7;
    }

    void setIconTintList(ColorStateList colorStateList) {
        this.G = colorStateList;
        this.H = colorStateList != null;
        androidx.appcompat.view.menu.g gVar = this.F;
        if (gVar != null) {
            setIcon(gVar.getIcon());
        }
    }

    public void setMaxLines(int i7) {
        this.D.setMaxLines(i7);
    }

    public void setNeedsEmptyIcon(boolean z6) {
        this.B = z6;
    }

    public void setTextAppearance(int i7) {
        androidx.core.widget.i.n(this.D, i7);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.D.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.D.setText(charSequence);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        a aVar = new a();
        this.J = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(o2.h.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(o2.d.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(o2.f.design_menu_item_text);
        this.D = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        u.k0(checkedTextView, aVar);
    }
}
