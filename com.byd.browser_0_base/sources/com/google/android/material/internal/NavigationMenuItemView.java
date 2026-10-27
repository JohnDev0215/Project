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
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import defpackage.C0214v;
import defpackage.in3;
import defpackage.om3;
import defpackage.rb2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements MenuView.ItemView {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final int[] f32J = {R.attr.state_checked};
    public boolean A;
    public boolean B;
    public final CheckedTextView C;
    public FrameLayout D;
    public MenuItemImpl E;
    public ColorStateList F;
    public boolean G;
    public Drawable H;
    public final a I;
    public int z;

    public class a extends C0214v {
        public a() {
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View.AccessibilityDelegate accessibilityDelegate = this.a;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setCheckable(NavigationMenuItemView.this.B);
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

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public final void c(MenuItemImpl menuItemImpl) {
        StateListDrawable stateListDrawable;
        this.E = menuItemImpl;
        int i = menuItemImpl.a;
        if (i > 0) {
            setId(i);
        }
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(f32J, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(this, stateListDrawable);
        }
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setTitle(menuItemImpl.e);
        setIcon(menuItemImpl.getIcon());
        setActionView(menuItemImpl.getActionView());
        setContentDescription(menuItemImpl.q);
        setTooltipText(menuItemImpl.r);
        MenuItemImpl menuItemImpl2 = this.E;
        boolean z = menuItemImpl2.e == null && menuItemImpl2.getIcon() == null && this.E.getActionView() != null;
        CheckedTextView checkedTextView = this.C;
        if (z) {
            checkedTextView.setVisibility(8);
            FrameLayout frameLayout = this.D;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).width = -1;
                this.D.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.D;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) layoutParams2).width = -2;
            this.D.setLayoutParams(layoutParams2);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.E;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.E;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.E.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f32J);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.B != z) {
            this.B = z;
            this.I.h(this.C, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.C.setChecked(z);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, getPaddingTop(), i, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.G) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                drawable.setTintList(this.F);
            }
            int i = this.z;
            drawable.setBounds(0, 0, i, i);
        } else if (this.A) {
            if (this.H == null) {
                Resources resources = getResources();
                int i2 = R$drawable.navigation_empty_icon;
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal<TypedValue> threadLocal = rb2.a;
                Drawable drawable2 = resources.getDrawable(i2, theme);
                this.H = drawable2;
                if (drawable2 != null) {
                    int i3 = this.z;
                    drawable2.setBounds(0, 0, i3, i3);
                }
            }
            drawable = this.H;
        }
        this.C.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.C.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.z = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.F = colorStateList;
        this.G = colorStateList != null;
        MenuItemImpl menuItemImpl = this.E;
        if (menuItemImpl != null) {
            setIcon(menuItemImpl.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.C.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.A = z;
    }

    public void setTextAppearance(int i) {
        this.C.setTextAppearance(i);
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

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a aVar = new a();
        this.I = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R$id.design_menu_item_text);
        this.C = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        om3.k(checkedTextView, aVar);
    }
}
