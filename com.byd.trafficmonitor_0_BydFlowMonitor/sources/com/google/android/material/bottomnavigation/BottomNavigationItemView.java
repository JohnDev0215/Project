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
import b.b.p.j.i;
import b.b.p.j.n;
import b.b.q.g0;
import b.h.m.c0.c;
import b.h.m.r;
import b.h.m.t;
import c.b.a.a.d;
import c.b.a.a.e;
import c.b.a.a.f;
import c.b.a.a.h;
import c.b.a.a.j;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationItemView extends FrameLayout implements n.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f4547b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4548c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4549d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4550e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4551f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4552g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4553h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageView f4554i;
    public final TextView j;
    public final TextView k;
    public int l;
    public i m;
    public ColorStateList n;
    public Drawable o;
    public Drawable p;
    public BadgeDrawable q;

    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (BottomNavigationItemView.this.f4554i.getVisibility() == 0) {
                BottomNavigationItemView bottomNavigationItemView = BottomNavigationItemView.this;
                bottomNavigationItemView.m(bottomNavigationItemView.f4554i);
            }
        }
    }

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    public final void c(float f2, float f3) {
        this.f4549d = f2 - f3;
        this.f4550e = (f3 * 1.0f) / f2;
        this.f4551f = (f2 * 1.0f) / f3;
    }

    @Override // b.b.p.j.n.a
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.n.a
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
        g0.a(this, !TextUtils.isEmpty(iVar.getTooltipText()) ? iVar.getTooltipText() : iVar.getTitle());
        setVisibility(iVar.isVisible() ? 0 : 8);
    }

    public final FrameLayout f(View view) {
        ImageView imageView = this.f4554i;
        if (view == imageView && c.b.a.a.o.a.f4058a) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    public final boolean g() {
        return this.q != null;
    }

    public BadgeDrawable getBadge() {
        return this.q;
    }

    @Override // b.b.p.j.n.a
    public i getItemData() {
        return this.m;
    }

    public int getItemPosition() {
        return this.l;
    }

    public void h() {
        l(this.f4554i);
    }

    public final void i(View view, int i2, int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.gravity = i3;
        view.setLayoutParams(layoutParams);
    }

    public final void j(View view, float f2, float f3, int i2) {
        view.setScaleX(f2);
        view.setScaleY(f3);
        view.setVisibility(i2);
    }

    public final void k(View view) {
        if (g() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            c.b.a.a.o.a.a(this.q, view, f(view));
        }
    }

    public final void l(View view) {
        if (g()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                c.b.a.a.o.a.d(this.q, view, f(view));
            }
            this.q = null;
        }
    }

    public final void m(View view) {
        if (g()) {
            c.b.a.a.o.a.e(this.q, view, f(view));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        i iVar = this.m;
        if (iVar != null && iVar.isCheckable() && this.m.isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f4547b);
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
        c cVarF0 = c.F0(accessibilityNodeInfo);
        cVarF0.f0(c.C0044c.a(0, 1, getItemPosition(), 1, false, isSelected()));
        if (isSelected()) {
            cVarF0.d0(false);
            cVarF0.T(c.a.f2616e);
        }
        cVarF0.v0(getResources().getString(j.item_view_role_description));
    }

    public void setBadge(BadgeDrawable badgeDrawable) {
        this.q = badgeDrawable;
        ImageView imageView = this.f4554i;
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
        int i2 = this.f4552g;
        if (i2 != -1) {
            if (i2 == 0) {
                if (z) {
                    i(this.f4554i, this.f4548c, 49);
                    j(this.k, 1.0f, 1.0f, 0);
                } else {
                    i(this.f4554i, this.f4548c, 17);
                    j(this.k, 0.5f, 0.5f, 4);
                }
                this.j.setVisibility(4);
            } else if (i2 != 1) {
                if (i2 == 2) {
                    i(this.f4554i, this.f4548c, 17);
                    this.k.setVisibility(8);
                    this.j.setVisibility(8);
                }
            } else if (z) {
                i(this.f4554i, (int) (this.f4548c + this.f4549d), 49);
                j(this.k, 1.0f, 1.0f, 0);
                TextView textView = this.j;
                float f2 = this.f4550e;
                j(textView, f2, f2, 4);
            } else {
                i(this.f4554i, this.f4548c, 49);
                TextView textView2 = this.k;
                float f3 = this.f4551f;
                j(textView2, f3, f3, 4);
                j(this.j, 1.0f, 1.0f, 0);
            }
        } else if (this.f4553h) {
            if (z) {
                i(this.f4554i, this.f4548c, 49);
                j(this.k, 1.0f, 1.0f, 0);
            } else {
                i(this.f4554i, this.f4548c, 17);
                j(this.k, 0.5f, 0.5f, 4);
            }
            this.j.setVisibility(4);
        } else if (z) {
            i(this.f4554i, (int) (this.f4548c + this.f4549d), 49);
            j(this.k, 1.0f, 1.0f, 0);
            TextView textView3 = this.j;
            float f4 = this.f4550e;
            j(textView3, f4, f4, 4);
        } else {
            i(this.f4554i, this.f4548c, 49);
            TextView textView4 = this.k;
            float f5 = this.f4551f;
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
        this.f4554i.setEnabled(z);
        if (z) {
            t.z0(this, r.b(getContext(), 1002));
        } else {
            t.z0(this, null);
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
            drawable = b.h.f.l.a.r(drawable).mutate();
            this.p = drawable;
            ColorStateList colorStateList = this.n;
            if (colorStateList != null) {
                b.h.f.l.a.o(drawable, colorStateList);
            }
        }
        this.f4554i.setImageDrawable(drawable);
    }

    public void setIconSize(int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f4554i.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.f4554i.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.n = colorStateList;
        if (this.m == null || (drawable = this.p) == null) {
            return;
        }
        b.h.f.l.a.o(drawable, colorStateList);
        this.p.invalidateSelf();
    }

    public void setItemBackground(int i2) {
        setItemBackground(i2 == 0 ? null : b.h.e.a.d(getContext(), i2));
    }

    public void setItemPosition(int i2) {
        this.l = i2;
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.f4552g != i2) {
            this.f4552g = i2;
            i iVar = this.m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setShifting(boolean z) {
        if (this.f4553h != z) {
            this.f4553h = z;
            i iVar = this.m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setTextAppearanceActive(int i2) {
        b.h.n.i.q(this.k, i2);
        c(this.j.getTextSize(), this.k.getTextSize());
    }

    public void setTextAppearanceInactive(int i2) {
        b.h.n.i.q(this.j, i2);
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
        g0.a(this, charSequence);
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
        this.f4548c = resources.getDimensionPixelSize(d.design_bottom_navigation_margin);
        this.f4554i = (ImageView) findViewById(f.icon);
        TextView textView = (TextView) findViewById(f.smallLabel);
        this.j = textView;
        TextView textView2 = (TextView) findViewById(f.largeLabel);
        this.k = textView2;
        t.u0(textView, 2);
        t.u0(textView2, 2);
        setFocusable(true);
        c(textView.getTextSize(), textView2.getTextSize());
        ImageView imageView = this.f4554i;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new a());
        }
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        t.n0(this, drawable);
    }
}
