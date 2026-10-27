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
import androidx.appcompat.widget.LinearLayoutCompat;
import b.b.p.j.i;
import b.b.p.j.n;
import b.b.q.g0;
import b.h.m.c0.c;
import b.h.m.t;
import c.b.a.a.d;
import c.b.a.a.e;
import c.b.a.a.f;
import c.b.a.a.h;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements n.a {
    public static final int[] w = {R.attr.state_checked};
    public final CheckedTextView A;
    public FrameLayout B;
    public i C;
    public ColorStateList D;
    public boolean E;
    public Drawable F;
    public final b.h.m.a G;
    public int x;
    public boolean y;
    public boolean z;

    public class a extends b.h.m.a {
        public a() {
        }

        @Override // b.h.m.a
        public void g(View view, c cVar) {
            super.g(view, cVar);
            cVar.a0(NavigationMenuItemView.this.z);
        }
    }

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.B == null) {
                this.B = (FrameLayout) ((ViewStub) findViewById(f.design_menu_item_action_area_stub)).inflate();
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
        if (!getContext().getTheme().resolveAttribute(b.b.a.colorControlHighlight, typedValue, true)) {
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

    @Override // b.b.p.j.n.a
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.n.a
    public void e(i iVar, int i2) {
        this.C = iVar;
        if (iVar.getItemId() > 0) {
            setId(iVar.getItemId());
        }
        setVisibility(iVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            t.n0(this, C());
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

    @Override // b.b.p.j.n.a
    public i getItemData() {
        return this.C;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        i iVar = this.C;
        if (iVar != null && iVar.isCheckable() && this.C.isChecked()) {
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

    public void setHorizontalPadding(int i2) {
        setPadding(i2, 0, i2, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.E) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = b.h.f.l.a.r(drawable).mutate();
                b.h.f.l.a.o(drawable, this.D);
            }
            int i2 = this.x;
            drawable.setBounds(0, 0, i2, i2);
        } else if (this.y) {
            if (this.F == null) {
                Drawable drawableA = b.h.e.c.f.a(getResources(), e.navigation_empty_icon, getContext().getTheme());
                this.F = drawableA;
                if (drawableA != null) {
                    int i3 = this.x;
                    drawableA.setBounds(0, 0, i3, i3);
                }
            }
            drawable = this.F;
        }
        b.h.n.i.l(this.A, drawable, null, null, null);
    }

    public void setIconPadding(int i2) {
        this.A.setCompoundDrawablePadding(i2);
    }

    public void setIconSize(int i2) {
        this.x = i2;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.D = colorStateList;
        this.E = colorStateList != null;
        i iVar = this.C;
        if (iVar != null) {
            setIcon(iVar.getIcon());
        }
    }

    public void setMaxLines(int i2) {
        this.A.setMaxLines(i2);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.y = z;
    }

    public void setTextAppearance(int i2) {
        b.h.n.i.q(this.A, i2);
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

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a aVar = new a();
        this.G = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(h.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(d.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(f.design_menu_item_text);
        this.A = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        t.k0(checkedTextView, aVar);
    }
}
