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
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.badge.BadgeDrawable;
import l0.r;
import l0.t;
import m0.c;
import n.i;
import n.n;
import o.g0;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationItemView extends FrameLayout implements n.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f2030b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2031c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2032d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2033e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2034f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2035g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2036h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageView f2037i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final TextView f2038j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final TextView f2039k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2040l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public i f2041m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f2042n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f2043o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f2044p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public BadgeDrawable f2045q;

    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            if (BottomNavigationItemView.this.f2037i.getVisibility() == 0) {
                BottomNavigationItemView bottomNavigationItemView = BottomNavigationItemView.this;
                bottomNavigationItemView.m(bottomNavigationItemView.f2037i);
            }
        }
    }

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    public final void c(float f3, float f4) {
        this.f2032d = f3 - f4;
        this.f2033e = (f4 * 1.0f) / f3;
        this.f2034f = (f3 * 1.0f) / f4;
    }

    @Override // n.n.a
    public boolean d() {
        return false;
    }

    @Override // n.n.a
    public void e(i iVar, int i3) {
        this.f2041m = iVar;
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
        ImageView imageView = this.f2037i;
        if (view == imageView && u1.a.f5348a) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    public final boolean g() {
        return this.f2045q != null;
    }

    public BadgeDrawable getBadge() {
        return this.f2045q;
    }

    @Override // n.n.a
    public i getItemData() {
        return this.f2041m;
    }

    public int getItemPosition() {
        return this.f2040l;
    }

    public void h() {
        l(this.f2037i);
    }

    public final void i(View view, int i3, int i4) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i3;
        layoutParams.gravity = i4;
        view.setLayoutParams(layoutParams);
    }

    public final void j(View view, float f3, float f4, int i3) {
        view.setScaleX(f3);
        view.setScaleY(f4);
        view.setVisibility(i3);
    }

    public final void k(View view) {
        if (g() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            u1.a.a(this.f2045q, view, f(view));
        }
    }

    public final void l(View view) {
        if (g()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                u1.a.d(this.f2045q, view, f(view));
            }
            this.f2045q = null;
        }
    }

    public final void m(View view) {
        if (g()) {
            u1.a.e(this.f2045q, view, f(view));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i3) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + 1);
        i iVar = this.f2041m;
        if (iVar != null && iVar.isCheckable() && this.f2041m.isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f2030b);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.f2045q;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            CharSequence title = this.f2041m.getTitle();
            if (!TextUtils.isEmpty(this.f2041m.getContentDescription())) {
                title = this.f2041m.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(((Object) title) + ", " + ((Object) this.f2045q.h()));
        }
        c cVarD0 = c.D0(accessibilityNodeInfo);
        cVarD0.e0(c.C0036c.a(0, 1, getItemPosition(), 1, false, isSelected()));
        if (isSelected()) {
            cVarD0.c0(false);
            cVarD0.S(c.a.f3806e);
        }
        cVarD0.t0(getResources().getString(R$string.item_view_role_description));
    }

    public void setBadge(BadgeDrawable badgeDrawable) {
        this.f2045q = badgeDrawable;
        ImageView imageView = this.f2037i;
        if (imageView != null) {
            k(imageView);
        }
    }

    public void setCheckable(boolean z3) {
        refreshDrawableState();
    }

    public void setChecked(boolean z3) {
        this.f2039k.setPivotX(r0.getWidth() / 2);
        this.f2039k.setPivotY(r0.getBaseline());
        this.f2038j.setPivotX(r0.getWidth() / 2);
        this.f2038j.setPivotY(r0.getBaseline());
        int i3 = this.f2035g;
        if (i3 != -1) {
            if (i3 == 0) {
                if (z3) {
                    i(this.f2037i, this.f2031c, 49);
                    j(this.f2039k, 1.0f, 1.0f, 0);
                } else {
                    i(this.f2037i, this.f2031c, 17);
                    j(this.f2039k, 0.5f, 0.5f, 4);
                }
                this.f2038j.setVisibility(4);
            } else if (i3 != 1) {
                if (i3 == 2) {
                    i(this.f2037i, this.f2031c, 17);
                    this.f2039k.setVisibility(8);
                    this.f2038j.setVisibility(8);
                }
            } else if (z3) {
                i(this.f2037i, (int) (this.f2031c + this.f2032d), 49);
                j(this.f2039k, 1.0f, 1.0f, 0);
                TextView textView = this.f2038j;
                float f3 = this.f2033e;
                j(textView, f3, f3, 4);
            } else {
                i(this.f2037i, this.f2031c, 49);
                TextView textView2 = this.f2039k;
                float f4 = this.f2034f;
                j(textView2, f4, f4, 4);
                j(this.f2038j, 1.0f, 1.0f, 0);
            }
        } else if (this.f2036h) {
            if (z3) {
                i(this.f2037i, this.f2031c, 49);
                j(this.f2039k, 1.0f, 1.0f, 0);
            } else {
                i(this.f2037i, this.f2031c, 17);
                j(this.f2039k, 0.5f, 0.5f, 4);
            }
            this.f2038j.setVisibility(4);
        } else if (z3) {
            i(this.f2037i, (int) (this.f2031c + this.f2032d), 49);
            j(this.f2039k, 1.0f, 1.0f, 0);
            TextView textView3 = this.f2038j;
            float f5 = this.f2033e;
            j(textView3, f5, f5, 4);
        } else {
            i(this.f2037i, this.f2031c, 49);
            TextView textView4 = this.f2039k;
            float f6 = this.f2034f;
            j(textView4, f6, f6, 4);
            j(this.f2038j, 1.0f, 1.0f, 0);
        }
        refreshDrawableState();
        setSelected(z3);
    }

    @Override // android.view.View
    public void setEnabled(boolean z3) {
        super.setEnabled(z3);
        this.f2038j.setEnabled(z3);
        this.f2039k.setEnabled(z3);
        this.f2037i.setEnabled(z3);
        if (z3) {
            t.x0(this, r.b(getContext(), 1002));
        } else {
            t.x0(this, null);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.f2043o) {
            return;
        }
        this.f2043o = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = e0.a.r(drawable).mutate();
            this.f2044p = drawable;
            ColorStateList colorStateList = this.f2042n;
            if (colorStateList != null) {
                e0.a.o(drawable, colorStateList);
            }
        }
        this.f2037i.setImageDrawable(drawable);
    }

    public void setIconSize(int i3) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f2037i.getLayoutParams();
        layoutParams.width = i3;
        layoutParams.height = i3;
        this.f2037i.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.f2042n = colorStateList;
        if (this.f2041m == null || (drawable = this.f2044p) == null) {
            return;
        }
        e0.a.o(drawable, colorStateList);
        this.f2044p.invalidateSelf();
    }

    public void setItemBackground(int i3) {
        setItemBackground(i3 == 0 ? null : b0.a.d(getContext(), i3));
    }

    public void setItemPosition(int i3) {
        this.f2040l = i3;
    }

    public void setLabelVisibilityMode(int i3) {
        if (this.f2035g != i3) {
            this.f2035g = i3;
            i iVar = this.f2041m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setShifting(boolean z3) {
        if (this.f2036h != z3) {
            this.f2036h = z3;
            i iVar = this.f2041m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setTextAppearanceActive(int i3) {
        n0.i.q(this.f2039k, i3);
        c(this.f2038j.getTextSize(), this.f2039k.getTextSize());
    }

    public void setTextAppearanceInactive(int i3) {
        n0.i.q(this.f2038j, i3);
        c(this.f2038j.getTextSize(), this.f2039k.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f2038j.setTextColor(colorStateList);
            this.f2039k.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.f2038j.setText(charSequence);
        this.f2039k.setText(charSequence);
        i iVar = this.f2041m;
        if (iVar == null || TextUtils.isEmpty(iVar.getContentDescription())) {
            setContentDescription(charSequence);
        }
        i iVar2 = this.f2041m;
        if (iVar2 != null && !TextUtils.isEmpty(iVar2.getTooltipText())) {
            charSequence = this.f2041m.getTooltipText();
        }
        g0.a(this, charSequence);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f2040l = -1;
        Resources resources = getResources();
        LayoutInflater.from(context).inflate(R$layout.design_bottom_navigation_item, (ViewGroup) this, true);
        setBackgroundResource(R$drawable.design_bottom_navigation_item_background);
        this.f2031c = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_margin);
        this.f2037i = (ImageView) findViewById(R$id.icon);
        TextView textView = (TextView) findViewById(R$id.smallLabel);
        this.f2038j = textView;
        TextView textView2 = (TextView) findViewById(R$id.largeLabel);
        this.f2039k = textView2;
        t.t0(textView, 2);
        t.t0(textView2, 2);
        setFocusable(true);
        c(textView.getTextSize(), textView2.getTextSize());
        ImageView imageView = this.f2037i;
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
