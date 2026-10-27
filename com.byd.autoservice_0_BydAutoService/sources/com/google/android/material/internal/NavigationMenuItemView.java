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
import c0.f;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import l0.t;
import m0.c;
import n.i;
import n.n;
import o.g0;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements n.a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final int[] f2359w = {R.attr.state_checked};
    public final CheckedTextView A;
    public FrameLayout B;
    public i C;
    public ColorStateList D;
    public boolean E;
    public Drawable F;
    public final l0.a G;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2360x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f2361y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2362z;

    public class a extends l0.a {
        public a() {
        }

        @Override // l0.a
        public void g(View view, c cVar) {
            super.g(view, cVar);
            cVar.Z(NavigationMenuItemView.this.f2362z);
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
        stateListDrawable.addState(f2359w, new ColorDrawable(typedValue.data));
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

    @Override // n.n.a
    public boolean d() {
        return false;
    }

    @Override // n.n.a
    public void e(i iVar, int i3) {
        this.C = iVar;
        if (iVar.getItemId() > 0) {
            setId(iVar.getItemId());
        }
        setVisibility(iVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            t.m0(this, C());
        }
        setCheckable(iVar.isCheckable());
        setChecked(iVar.isChecked());
        setEnabled(iVar.isEnabled());
        setTitle(iVar.getTitle());
        setIcon(iVar.getIcon());
        setActionView(iVar.getActionView());
        setContentDescription(iVar.getContentDescription());
        g0.a(this, iVar.getTooltipText());
        B();
    }

    @Override // n.n.a
    public i getItemData() {
        return this.C;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i3) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + 1);
        i iVar = this.C;
        if (iVar != null && iVar.isCheckable() && this.C.isChecked()) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, f2359w);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z3) {
        refreshDrawableState();
        if (this.f2362z != z3) {
            this.f2362z = z3;
            this.G.l(this.A, 2048);
        }
    }

    public void setChecked(boolean z3) {
        refreshDrawableState();
        this.A.setChecked(z3);
    }

    public void setHorizontalPadding(int i3) {
        setPadding(i3, 0, i3, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.E) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = e0.a.r(drawable).mutate();
                e0.a.o(drawable, this.D);
            }
            int i3 = this.f2360x;
            drawable.setBounds(0, 0, i3, i3);
        } else if (this.f2361y) {
            if (this.F == null) {
                Drawable drawableA = f.a(getResources(), R$drawable.navigation_empty_icon, getContext().getTheme());
                this.F = drawableA;
                if (drawableA != null) {
                    int i4 = this.f2360x;
                    drawableA.setBounds(0, 0, i4, i4);
                }
            }
            drawable = this.F;
        }
        n0.i.l(this.A, drawable, null, null, null);
    }

    public void setIconPadding(int i3) {
        this.A.setCompoundDrawablePadding(i3);
    }

    public void setIconSize(int i3) {
        this.f2360x = i3;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.D = colorStateList;
        this.E = colorStateList != null;
        i iVar = this.C;
        if (iVar != null) {
            setIcon(iVar.getIcon());
        }
    }

    public void setMaxLines(int i3) {
        this.A.setMaxLines(i3);
    }

    public void setNeedsEmptyIcon(boolean z3) {
        this.f2361y = z3;
    }

    public void setTextAppearance(int i3) {
        n0.i.q(this.A, i3);
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

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        a aVar = new a();
        this.G = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R$id.design_menu_item_text);
        this.A = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        t.j0(checkedTextView, aVar);
    }
}
