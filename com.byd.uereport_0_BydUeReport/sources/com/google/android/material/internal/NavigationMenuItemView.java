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
import f0.f;
import o0.t;
import p0.c;
import q.i;
import q.n;
import r.g0;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements n.a {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f3942y = {R.attr.state_checked};
    public boolean A;
    public boolean B;
    public final CheckedTextView C;
    public FrameLayout D;
    public i E;
    public ColorStateList F;
    public boolean G;
    public Drawable H;
    public final o0.a I;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3943z;

    public class a extends o0.a {
        public a() {
        }

        @Override // o0.a
        public void g(View view, c cVar) {
            super.g(view, cVar);
            cVar.a0(NavigationMenuItemView.this.B);
        }
    }

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.D == null) {
                this.D = (FrameLayout) ((ViewStub) findViewById(R$id.design_menu_item_action_area_stub)).inflate();
            }
            this.D.removeAllViews();
            this.D.addView(view);
        }
    }

    public final void B() {
        if (E()) {
            this.C.setVisibility(8);
            FrameLayout frameLayout = this.D;
            if (frameLayout != null) {
                LinearLayoutCompat.a aVar = (LinearLayoutCompat.a) frameLayout.getLayoutParams();
                ((ViewGroup.MarginLayoutParams) aVar).width = -1;
                this.D.setLayoutParams(aVar);
                return;
            }
            return;
        }
        this.C.setVisibility(0);
        FrameLayout frameLayout2 = this.D;
        if (frameLayout2 != null) {
            LinearLayoutCompat.a aVar2 = (LinearLayoutCompat.a) frameLayout2.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) aVar2).width = -2;
            this.D.setLayoutParams(aVar2);
        }
    }

    public final StateListDrawable C() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f3942y, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    public void D() {
        FrameLayout frameLayout = this.D;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        this.C.setCompoundDrawables(null, null, null, null);
    }

    public final boolean E() {
        return this.E.getTitle() == null && this.E.getIcon() == null && this.E.getActionView() != null;
    }

    @Override // q.n.a
    public boolean d() {
        return false;
    }

    @Override // q.n.a
    public void e(i iVar, int i8) {
        this.E = iVar;
        if (iVar.getItemId() > 0) {
            setId(iVar.getItemId());
        }
        setVisibility(iVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            t.o0(this, C());
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

    @Override // q.n.a
    public i getItemData() {
        return this.E;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i8) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + 1);
        i iVar = this.E;
        if (iVar != null && iVar.isCheckable() && this.E.isChecked()) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, f3942y);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z7) {
        refreshDrawableState();
        if (this.B != z7) {
            this.B = z7;
            this.I.l(this.C, 2048);
        }
    }

    public void setChecked(boolean z7) {
        refreshDrawableState();
        this.C.setChecked(z7);
    }

    public void setHorizontalPadding(int i8) {
        setPadding(i8, 0, i8, 0);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.G) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = h0.a.r(drawable).mutate();
                h0.a.o(drawable, this.F);
            }
            int i8 = this.f3943z;
            drawable.setBounds(0, 0, i8, i8);
        } else if (this.A) {
            if (this.H == null) {
                Drawable drawableA = f.a(getResources(), R$drawable.navigation_empty_icon, getContext().getTheme());
                this.H = drawableA;
                if (drawableA != null) {
                    int i9 = this.f3943z;
                    drawableA.setBounds(0, 0, i9, i9);
                }
            }
            drawable = this.H;
        }
        r0.i.l(this.C, drawable, null, null, null);
    }

    public void setIconPadding(int i8) {
        this.C.setCompoundDrawablePadding(i8);
    }

    public void setIconSize(int i8) {
        this.f3943z = i8;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.F = colorStateList;
        this.G = colorStateList != null;
        i iVar = this.E;
        if (iVar != null) {
            setIcon(iVar.getIcon());
        }
    }

    public void setMaxLines(int i8) {
        this.C.setMaxLines(i8);
    }

    public void setNeedsEmptyIcon(boolean z7) {
        this.A = z7;
    }

    public void setTextAppearance(int i8) {
        r0.i.q(this.C, i8);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.C.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.C.setText(charSequence);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        a aVar = new a();
        this.I = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R$id.design_menu_item_text);
        this.C = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        t.l0(checkedTextView, aVar);
    }
}
