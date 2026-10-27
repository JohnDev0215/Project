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
import o0.r;
import o0.t;
import p0.c;
import q.i;
import q.n;
import r.g0;

/* JADX INFO: loaded from: classes2.dex */
public class BottomNavigationItemView extends FrameLayout implements n.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f3615d = {R.attr.state_checked};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f3618g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3619h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3620i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3621j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ImageView f3622k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final TextView f3623l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final TextView f3624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3625n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public i f3626o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f3627p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f3628q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f3629r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public BadgeDrawable f3630s;

    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            if (BottomNavigationItemView.this.f3622k.getVisibility() == 0) {
                BottomNavigationItemView bottomNavigationItemView = BottomNavigationItemView.this;
                bottomNavigationItemView.m(bottomNavigationItemView.f3622k);
            }
        }
    }

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    public final void c(float f8, float f9) {
        this.f3617f = f8 - f9;
        this.f3618g = (f9 * 1.0f) / f8;
        this.f3619h = (f8 * 1.0f) / f9;
    }

    @Override // q.n.a
    public boolean d() {
        return false;
    }

    @Override // q.n.a
    public void e(i iVar, int i8) {
        this.f3626o = iVar;
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
        ImageView imageView = this.f3622k;
        if (view == imageView && l5.a.f6289a) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    public final boolean g() {
        return this.f3630s != null;
    }

    public BadgeDrawable getBadge() {
        return this.f3630s;
    }

    @Override // q.n.a
    public i getItemData() {
        return this.f3626o;
    }

    public int getItemPosition() {
        return this.f3625n;
    }

    public void h() {
        l(this.f3622k);
    }

    public final void i(View view, int i8, int i9) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i8;
        layoutParams.gravity = i9;
        view.setLayoutParams(layoutParams);
    }

    public final void j(View view, float f8, float f9, int i8) {
        view.setScaleX(f8);
        view.setScaleY(f9);
        view.setVisibility(i8);
    }

    public final void k(View view) {
        if (g() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            l5.a.a(this.f3630s, view, f(view));
        }
    }

    public final void l(View view) {
        if (g()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                l5.a.d(this.f3630s, view, f(view));
            }
            this.f3630s = null;
        }
    }

    public final void m(View view) {
        if (g()) {
            l5.a.e(this.f3630s, view, f(view));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i8) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + 1);
        i iVar = this.f3626o;
        if (iVar != null && iVar.isCheckable() && this.f3626o.isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f3615d);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        BadgeDrawable badgeDrawable = this.f3630s;
        if (badgeDrawable != null && badgeDrawable.isVisible()) {
            CharSequence title = this.f3626o.getTitle();
            if (!TextUtils.isEmpty(this.f3626o.getContentDescription())) {
                title = this.f3626o.getContentDescription();
            }
            accessibilityNodeInfo.setContentDescription(((Object) title) + ", " + ((Object) this.f3630s.h()));
        }
        c cVarF0 = c.F0(accessibilityNodeInfo);
        cVarF0.f0(c.C0101c.a(0, 1, getItemPosition(), 1, false, isSelected()));
        if (isSelected()) {
            cVarF0.d0(false);
            cVarF0.T(c.a.f6972e);
        }
        cVarF0.v0(getResources().getString(R$string.item_view_role_description));
    }

    public void setBadge(BadgeDrawable badgeDrawable) {
        this.f3630s = badgeDrawable;
        ImageView imageView = this.f3622k;
        if (imageView != null) {
            k(imageView);
        }
    }

    public void setCheckable(boolean z7) {
        refreshDrawableState();
    }

    public void setChecked(boolean z7) {
        this.f3624m.setPivotX(r0.getWidth() / 2);
        this.f3624m.setPivotY(r0.getBaseline());
        this.f3623l.setPivotX(r0.getWidth() / 2);
        this.f3623l.setPivotY(r0.getBaseline());
        int i8 = this.f3620i;
        if (i8 != -1) {
            if (i8 == 0) {
                if (z7) {
                    i(this.f3622k, this.f3616e, 49);
                    j(this.f3624m, 1.0f, 1.0f, 0);
                } else {
                    i(this.f3622k, this.f3616e, 17);
                    j(this.f3624m, 0.5f, 0.5f, 4);
                }
                this.f3623l.setVisibility(4);
            } else if (i8 != 1) {
                if (i8 == 2) {
                    i(this.f3622k, this.f3616e, 17);
                    this.f3624m.setVisibility(8);
                    this.f3623l.setVisibility(8);
                }
            } else if (z7) {
                i(this.f3622k, (int) (this.f3616e + this.f3617f), 49);
                j(this.f3624m, 1.0f, 1.0f, 0);
                TextView textView = this.f3623l;
                float f8 = this.f3618g;
                j(textView, f8, f8, 4);
            } else {
                i(this.f3622k, this.f3616e, 49);
                TextView textView2 = this.f3624m;
                float f9 = this.f3619h;
                j(textView2, f9, f9, 4);
                j(this.f3623l, 1.0f, 1.0f, 0);
            }
        } else if (this.f3621j) {
            if (z7) {
                i(this.f3622k, this.f3616e, 49);
                j(this.f3624m, 1.0f, 1.0f, 0);
            } else {
                i(this.f3622k, this.f3616e, 17);
                j(this.f3624m, 0.5f, 0.5f, 4);
            }
            this.f3623l.setVisibility(4);
        } else if (z7) {
            i(this.f3622k, (int) (this.f3616e + this.f3617f), 49);
            j(this.f3624m, 1.0f, 1.0f, 0);
            TextView textView3 = this.f3623l;
            float f10 = this.f3618g;
            j(textView3, f10, f10, 4);
        } else {
            i(this.f3622k, this.f3616e, 49);
            TextView textView4 = this.f3624m;
            float f11 = this.f3619h;
            j(textView4, f11, f11, 4);
            j(this.f3623l, 1.0f, 1.0f, 0);
        }
        refreshDrawableState();
        setSelected(z7);
    }

    @Override // android.view.View
    public void setEnabled(boolean z7) {
        super.setEnabled(z7);
        this.f3623l.setEnabled(z7);
        this.f3624m.setEnabled(z7);
        this.f3622k.setEnabled(z7);
        if (z7) {
            t.A0(this, r.b(getContext(), 1002));
        } else {
            t.A0(this, null);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.f3628q) {
            return;
        }
        this.f3628q = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = h0.a.r(drawable).mutate();
            this.f3629r = drawable;
            ColorStateList colorStateList = this.f3627p;
            if (colorStateList != null) {
                h0.a.o(drawable, colorStateList);
            }
        }
        this.f3622k.setImageDrawable(drawable);
    }

    public void setIconSize(int i8) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f3622k.getLayoutParams();
        layoutParams.width = i8;
        layoutParams.height = i8;
        this.f3622k.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.f3627p = colorStateList;
        if (this.f3626o == null || (drawable = this.f3629r) == null) {
            return;
        }
        h0.a.o(drawable, colorStateList);
        this.f3629r.invalidateSelf();
    }

    public void setItemBackground(int i8) {
        setItemBackground(i8 == 0 ? null : e0.a.d(getContext(), i8));
    }

    public void setItemPosition(int i8) {
        this.f3625n = i8;
    }

    public void setLabelVisibilityMode(int i8) {
        if (this.f3620i != i8) {
            this.f3620i = i8;
            i iVar = this.f3626o;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setShifting(boolean z7) {
        if (this.f3621j != z7) {
            this.f3621j = z7;
            i iVar = this.f3626o;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setTextAppearanceActive(int i8) {
        r0.i.q(this.f3624m, i8);
        c(this.f3623l.getTextSize(), this.f3624m.getTextSize());
    }

    public void setTextAppearanceInactive(int i8) {
        r0.i.q(this.f3623l, i8);
        c(this.f3623l.getTextSize(), this.f3624m.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f3623l.setTextColor(colorStateList);
            this.f3624m.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.f3623l.setText(charSequence);
        this.f3624m.setText(charSequence);
        i iVar = this.f3626o;
        if (iVar == null || TextUtils.isEmpty(iVar.getContentDescription())) {
            setContentDescription(charSequence);
        }
        i iVar2 = this.f3626o;
        if (iVar2 != null && !TextUtils.isEmpty(iVar2.getTooltipText())) {
            charSequence = this.f3626o.getTooltipText();
        }
        g0.a(this, charSequence);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3625n = -1;
        Resources resources = getResources();
        LayoutInflater.from(context).inflate(R$layout.design_bottom_navigation_item, (ViewGroup) this, true);
        setBackgroundResource(R$drawable.design_bottom_navigation_item_background);
        this.f3616e = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_margin);
        this.f3622k = (ImageView) findViewById(R$id.icon);
        TextView textView = (TextView) findViewById(R$id.smallLabel);
        this.f3623l = textView;
        TextView textView2 = (TextView) findViewById(R$id.largeLabel);
        this.f3624m = textView2;
        t.v0(textView, 2);
        t.v0(textView2, 2);
        setFocusable(true);
        c(textView.getTextSize(), textView2.getTextSize());
        ImageView imageView = this.f3622k;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new a());
        }
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        t.o0(this, drawable);
    }
}
