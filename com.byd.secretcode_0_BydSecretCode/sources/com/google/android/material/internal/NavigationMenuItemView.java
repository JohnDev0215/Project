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
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import defpackage.a9;
import defpackage.ea;
import defpackage.i3;
import defpackage.l7;
import defpackage.qa;
import defpackage.r1;
import defpackage.t9;
import defpackage.w1;
import defpackage.y7;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements w1.a {
    public static final int[] w = {R.attr.state_checked};
    public final CheckedTextView A;
    public FrameLayout B;
    public r1 C;
    public ColorStateList D;
    public boolean E;
    public Drawable F;
    public final a9 G;
    public int x;
    public boolean y;
    public boolean z;

    public class a extends a9 {
        public a() {
        }

        @Override // defpackage.a9
        public void g(View view, ea eaVar) {
            super.g(view, eaVar);
            eaVar.Z(NavigationMenuItemView.this.z);
        }
    }

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.B == null) {
                this.B = (FrameLayout) ((ViewStub) findViewById(R$id.design_menu_item_action_area_stub)).inflate();
            }
            this.B.removeAllViews();
            this.B.addView(view);
        }
    }

    public final void B() {
        if (E()) {
            this.A.setVisibility(8);
            FrameLayout frameLayout = this.B;
            if (frameLayout != null) {
                LinearLayoutCompat.a aVar = (LinearLayoutCompat.a) frameLayout.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) aVar).width = -1;
                this.B.setLayoutParams(aVar);
                return;
            }
            return;
        }
        this.A.setVisibility(0);
        FrameLayout frameLayout2 = this.B;
        if (frameLayout2 != null) {
            LinearLayoutCompat.a aVar2 = (LinearLayoutCompat.a) frameLayout2.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) aVar2).width = -2;
            this.B.setLayoutParams(aVar2);
        }
    }

    public final StateListDrawable C() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(w, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    public void D() {
        FrameLayout frameLayout = this.B;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        this.A.setCompoundDrawables(null, null, null, null);
    }

    public final boolean E() {
        return this.C.getTitle() == null && this.C.getIcon() == null && this.C.getActionView() != null;
    }

    @Override // w1.a
    public boolean d() {
        return false;
    }

    @Override // w1.a
    public void e(r1 r1Var, int i) {
        this.C = r1Var;
        if (r1Var.getItemId() > 0) {
            setId(r1Var.getItemId());
        }
        setVisibility(r1Var.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            t9.m0(this, C());
        }
        setCheckable(r1Var.isCheckable());
        setChecked(r1Var.isChecked());
        setEnabled(r1Var.isEnabled());
        setTitle(r1Var.getTitle());
        setIcon(r1Var.getIcon());
        setActionView(r1Var.getActionView());
        setContentDescription(r1Var.getContentDescription());
        i3.a(this, r1Var.getTooltipText());
        B();
    }

    @Override // w1.a
    public r1 getItemData() {
        return this.C;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        r1 r1Var = this.C;
        if (r1Var != null && r1Var.isCheckable() && this.C.isChecked()) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, w);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.z != z) {
            this.z = z;
            this.G.l(this.A, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.A.setChecked(z);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, 0, i, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.E) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = y7.r(drawable).mutate();
                y7.o(drawable, this.D);
            }
            int i = this.x;
            drawable.setBounds(0, 0, i, i);
        } else if (this.y) {
            if (this.F == null) {
                Drawable drawableA = l7.a(getResources(), R$drawable.navigation_empty_icon, getContext().getTheme());
                this.F = drawableA;
                if (drawableA != null) {
                    int i2 = this.x;
                    drawableA.setBounds(0, 0, i2, i2);
                }
            }
            drawable = this.F;
        }
        qa.l(this.A, drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.A.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.x = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.D = colorStateList;
        this.E = colorStateList != null;
        r1 r1Var = this.C;
        if (r1Var != null) {
            setIcon(r1Var.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.A.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.y = z;
    }

    public void setTextAppearance(int i) {
        qa.q(this.A, i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.A.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.A.setText(charSequence);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a aVar = new a();
        this.G = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R$id.design_menu_item_text);
        this.A = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        t9.j0(checkedTextView, aVar);
    }
}
