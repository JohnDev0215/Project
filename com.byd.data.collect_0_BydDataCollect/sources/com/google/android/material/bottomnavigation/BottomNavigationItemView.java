package com.google.android.material.bottomnavigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.i;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.h0;
import b.g.k.c0.c;
import b.g.k.r;
import b.g.k.t;
import com.google.android.material.badge.BadgeDrawable;
import d.b.a.a.d;
import d.b.a.a.e;
import d.b.a.a.f;
import d.b.a.a.h;
import d.b.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationItemView extends FrameLayout implements n.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f3762b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3764d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3765e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f3766f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3767g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3768h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ImageView f3769i;
    private final TextView j;
    private final TextView k;
    private int l;
    private i m;
    private ColorStateList n;
    private Drawable o;
    private Drawable p;
    private BadgeDrawable q;

    class a implements View.OnLayoutChangeListener {
        a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (BottomNavigationItemView.this.f3769i.getVisibility() == 0) {
                BottomNavigationItemView bottomNavigationItemView = BottomNavigationItemView.this;
                bottomNavigationItemView.m(bottomNavigationItemView.f3769i);
            }
        }
    }

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    private void c(float f2, float f3) {
        this.f3764d = f2 - f3;
        this.f3765e = (f3 * 1.0f) / f2;
        this.f3766f = (f2 * 1.0f) / f3;
    }

    private FrameLayout f(View view) {
        ImageView imageView = this.f3769i;
        if (view == imageView && com.google.android.material.badge.a.f3714a) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    private boolean g() {
        return this.q != null;
    }

    private void i(View view, int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.gravity = i3;
        view.setLayoutParams(layoutParams);
    }

    private void j(View view, float f2, float f3, int i2) {
        view.setScaleX(f2);
        view.setScaleY(f3);
        view.setVisibility(i2);
    }

    private void k(View view) {
        if (g() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            com.google.android.material.badge.a.a(this.q, view, f(view));
        }
    }

    private void l(View view) {
        if (g()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                com.google.android.material.badge.a.d(this.q, view, f(view));
            }
            this.q = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(View view) {
        if (g()) {
            com.google.android.material.badge.a.e(this.q, view, f(view));
        }
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void e(i iVar, int i2) {
        this.m = iVar;
        setCheckable(iVar.isCheckable());
        setChecked(iVar.isChecked());
        setEnabled(iVar.isEnabled());
        setIcon(iVar.getIcon());
        setTitle(iVar.getTitle());
        setId(iVar.getItemId());
        if (!TextUtils.isEmpty(iVar.getContentDescription())) {
            setContentDescription(iVar.getContentDescription());
        }
        h0.a(this, !TextUtils.isEmpty(iVar.getTooltipText()) ? iVar.getTooltipText() : iVar.getTitle());
        setVisibility(iVar.isVisible() ? 0 : 8);
    }

    BadgeDrawable getBadge() {
        return this.q;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public i getItemData() {
        return this.m;
    }

    public int getItemPosition() {
        return this.l;
    }

    void h() {
        l(this.f3769i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        i iVar = this.m;
        if (iVar != null && iVar.isCheckable() && this.m.isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f3762b);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.q;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            CharSequence title = this.m.getTitle();
            if (!TextUtils.isEmpty(this.m.getContentDescription())) {
                title = this.m.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(((Object) title) + ", " + ((Object) this.q.h()));
        }
        c cVarC0 = c.C0(accessibilityNodeInfo);
        cVarC0.e0(c.C0047c.a(0, 1, getItemPosition(), 1, false, isSelected()));
        if (isSelected()) {
            cVarC0.c0(false);
            cVarC0.S(c.a.f2700e);
        }
        cVarC0.s0(getResources().getString(j.item_view_role_description));
    }

    void setBadge(BadgeDrawable badgeDrawable) {
        this.q = badgeDrawable;
        ImageView imageView = this.f3769i;
        if (imageView != null) {
            k(imageView);
        }
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        this.k.setPivotX(r0.getWidth() / 2);
        this.k.setPivotY(r0.getBaseline());
        this.j.setPivotX(r0.getWidth() / 2);
        this.j.setPivotY(r0.getBaseline());
        int i2 = this.f3767g;
        if (i2 != -1) {
            if (i2 == 0) {
                if (z) {
                    i(this.f3769i, this.f3763c, 49);
                    j(this.k, 1.0f, 1.0f, 0);
                } else {
                    i(this.f3769i, this.f3763c, 17);
                    j(this.k, 0.5f, 0.5f, 4);
                }
                this.j.setVisibility(4);
            } else if (i2 != 1) {
                if (i2 == 2) {
                    i(this.f3769i, this.f3763c, 17);
                    this.k.setVisibility(8);
                    this.j.setVisibility(8);
                }
            } else if (z) {
                i(this.f3769i, (int) (this.f3763c + this.f3764d), 49);
                j(this.k, 1.0f, 1.0f, 0);
                TextView textView = this.j;
                float f2 = this.f3765e;
                j(textView, f2, f2, 4);
            } else {
                i(this.f3769i, this.f3763c, 49);
                TextView textView2 = this.k;
                float f3 = this.f3766f;
                j(textView2, f3, f3, 4);
                j(this.j, 1.0f, 1.0f, 0);
            }
        } else if (this.f3768h) {
            if (z) {
                i(this.f3769i, this.f3763c, 49);
                j(this.k, 1.0f, 1.0f, 0);
            } else {
                i(this.f3769i, this.f3763c, 17);
                j(this.k, 0.5f, 0.5f, 4);
            }
            this.j.setVisibility(4);
        } else if (z) {
            i(this.f3769i, (int) (this.f3763c + this.f3764d), 49);
            j(this.k, 1.0f, 1.0f, 0);
            TextView textView3 = this.j;
            float f4 = this.f3765e;
            j(textView3, f4, f4, 4);
        } else {
            i(this.f3769i, this.f3763c, 49);
            TextView textView4 = this.k;
            float f5 = this.f3766f;
            j(textView4, f5, f5, 4);
            j(this.j, 1.0f, 1.0f, 0);
        }
        refreshDrawableState();
        setSelected(z);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.j.setEnabled(z);
        this.k.setEnabled(z);
        this.f3769i.setEnabled(z);
        if (z) {
            t.x0(this, r.b(getContext(), 1002));
        } else {
            t.x0(this, null);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.o) {
            return;
        }
        this.o = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = androidx.core.graphics.drawable.a.r(drawable).mutate();
            this.p = drawable;
            ColorStateList colorStateList = this.n;
            if (colorStateList != null) {
                androidx.core.graphics.drawable.a.o(drawable, colorStateList);
            }
        }
        this.f3769i.setImageDrawable(drawable);
    }

    public void setIconSize(int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f3769i.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.f3769i.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.n = colorStateList;
        if (this.m == null || (drawable = this.p) == null) {
            return;
        }
        androidx.core.graphics.drawable.a.o(drawable, colorStateList);
        this.p.invalidateSelf();
    }

    public void setItemBackground(int i2) {
        setItemBackground(i2 == 0 ? null : androidx.core.content.a.d(getContext(), i2));
    }

    public void setItemPosition(int i2) {
        this.l = i2;
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.f3767g != i2) {
            this.f3767g = i2;
            i iVar = this.m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setShifting(boolean z) {
        if (this.f3768h != z) {
            this.f3768h = z;
            i iVar = this.m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setTextAppearanceActive(int i2) {
        androidx.core.widget.i.q(this.k, i2);
        c(this.j.getTextSize(), this.k.getTextSize());
    }

    public void setTextAppearanceInactive(int i2) {
        androidx.core.widget.i.q(this.j, i2);
        c(this.j.getTextSize(), this.k.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.j.setTextColor(colorStateList);
            this.k.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.j.setText(charSequence);
        this.k.setText(charSequence);
        i iVar = this.m;
        if (iVar == null || TextUtils.isEmpty(iVar.getContentDescription())) {
            setContentDescription(charSequence);
        }
        i iVar2 = this.m;
        if (iVar2 != null && !TextUtils.isEmpty(iVar2.getTooltipText())) {
            charSequence = this.m.getTooltipText();
        }
        h0.a(this, charSequence);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.l = -1;
        Resources resources = getResources();
        LayoutInflater.from(context).inflate(h.design_bottom_navigation_item, (ViewGroup) this, true);
        setBackgroundResource(e.design_bottom_navigation_item_background);
        this.f3763c = resources.getDimensionPixelSize(d.design_bottom_navigation_margin);
        this.f3769i = (ImageView) findViewById(f.icon);
        TextView textView = (TextView) findViewById(f.smallLabel);
        this.j = textView;
        TextView textView2 = (TextView) findViewById(f.largeLabel);
        this.k = textView2;
        t.t0(textView, 2);
        t.t0(textView2, 2);
        setFocusable(true);
        c(textView.getTextSize(), textView2.getTextSize());
        ImageView imageView = this.f3769i;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new a());
        }
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        t.m0(this, drawable);
    }
}
