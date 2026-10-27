package com.google.android.material.internal;

import a.b.o.i.i;
import a.b.o.i.n;
import a.b.p.i0;
import a.h.k.q;
import a.h.k.x.b;
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
import b.d.a.a.d;
import b.d.a.a.e;
import b.d.a.a.h;
import b.d.a.a.z.f;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends f implements n.a {
    public static final int[] F = {R.attr.state_checked};
    public i A;
    public ColorStateList B;
    public boolean C;
    public Drawable D;
    public final a.h.k.a E;
    public int v;
    public boolean w;
    public boolean x;
    public final CheckedTextView y;
    public FrameLayout z;

    public class a extends a.h.k.a {
        public a() {
        }

        @Override // a.h.k.a
        public void d(View view, b bVar) {
            this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
            bVar.f856a.setCheckable(NavigationMenuItemView.this.x);
        }
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.E = new a();
        setOrientation(0);
        LayoutInflater.from(context).inflate(h.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(d.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(b.d.a.a.f.design_menu_item_text);
        this.y = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        q.U(this.y, this.E);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.z == null) {
                this.z = (FrameLayout) ((ViewStub) findViewById(b.d.a.a.f.design_menu_item_action_area_stub)).inflate();
            }
            this.z.removeAllViews();
            this.z.addView(view);
        }
    }

    @Override // a.b.o.i.n.a
    public boolean c() {
        return false;
    }

    @Override // a.b.o.i.n.a
    public void e(i iVar, int i) {
        i0.a aVar;
        int i2;
        StateListDrawable stateListDrawable;
        this.A = iVar;
        int i3 = iVar.f185a;
        if (i3 > 0) {
            setId(i3);
        }
        setVisibility(iVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(a.b.a.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(F, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            q.X(this, stateListDrawable);
        }
        setCheckable(iVar.isCheckable());
        setChecked(iVar.isChecked());
        setEnabled(iVar.isEnabled());
        setTitle(iVar.f189e);
        setIcon(iVar.getIcon());
        setActionView(iVar.getActionView());
        setContentDescription(iVar.q);
        setTooltipText(iVar.r);
        i iVar2 = this.A;
        if (iVar2.f189e == null && iVar2.getIcon() == null && this.A.getActionView() != null) {
            this.y.setVisibility(8);
            FrameLayout frameLayout = this.z;
            if (frameLayout == null) {
                return;
            }
            aVar = (i0.a) frameLayout.getLayoutParams();
            i2 = -1;
        } else {
            this.y.setVisibility(0);
            FrameLayout frameLayout2 = this.z;
            if (frameLayout2 == null) {
                return;
            }
            aVar = (i0.a) frameLayout2.getLayoutParams();
            i2 = -2;
        }
        ((ViewGroup.MarginLayoutParams) aVar).width = i2;
        this.z.setLayoutParams(aVar);
    }

    @Override // a.b.o.i.n.a
    public i getItemData() {
        return this.A;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        i iVar = this.A;
        if (iVar != null && iVar.isCheckable() && this.A.isChecked()) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, F);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.x != z) {
            this.x = z;
            this.E.h(this.y, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.y.setChecked(z);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, 0, i, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.C) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                drawable.setTintList(this.B);
            }
            int i = this.v;
            drawable.setBounds(0, 0, i, i);
        } else if (this.w) {
            if (this.D == null) {
                Drawable drawable2 = getResources().getDrawable(e.navigation_empty_icon, getContext().getTheme());
                this.D = drawable2;
                if (drawable2 != null) {
                    int i2 = this.v;
                    drawable2.setBounds(0, 0, i2, i2);
                }
            }
            drawable = this.D;
        }
        this.y.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.y.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.v = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.B = colorStateList;
        this.C = colorStateList != null;
        i iVar = this.A;
        if (iVar != null) {
            setIcon(iVar.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.y.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.w = z;
    }

    public void setTextAppearance(int i) {
        this.y.setTextAppearance(i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.y.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.y.setText(charSequence);
    }
}
