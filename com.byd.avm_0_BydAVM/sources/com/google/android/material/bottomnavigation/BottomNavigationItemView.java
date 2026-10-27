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
import androidx.appcompat.widget.g0;
import b.g.k.c0.c;
import b.g.k.r;
import b.g.k.t;
import c.a.a.a.d;
import c.a.a.a.e;
import c.a.a.a.f;
import c.a.a.a.h;
import c.a.a.a.j;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationItemView extends FrameLayout implements n.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f2209b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2211d;
    private float e;
    private float f;
    private int g;
    private boolean h;
    private ImageView i;
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
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (BottomNavigationItemView.this.i.getVisibility() == 0) {
                BottomNavigationItemView bottomNavigationItemView = BottomNavigationItemView.this;
                bottomNavigationItemView.m(bottomNavigationItemView.i);
            }
        }
    }

    public BottomNavigationItemView(Context context) {
        this(context, null);
    }

    private void c(float f, float f2) {
        this.f2211d = f - f2;
        this.e = (f2 * 1.0f) / f;
        this.f = (f * 1.0f) / f2;
    }

    private FrameLayout f(View view) {
        ImageView imageView = this.i;
        if (view == imageView && com.google.android.material.badge.a.f2174a) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    private boolean g() {
        return this.q != null;
    }

    private void i(View view, int i, int i2) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.gravity = i2;
        view.setLayoutParams(layoutParams);
    }

    private void j(View view, float f, float f2, int i) {
        view.setScaleX(f);
        view.setScaleY(f2);
        view.setVisibility(i);
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
    public void e(i iVar, int i) {
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
        l(this.i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        i iVar = this.m;
        if (iVar != null && iVar.isCheckable() && this.m.isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f2209b);
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
        c cVarD0 = c.D0(accessibilityNodeInfo);
        cVarD0.e0(c.C0040c.a(0, 1, getItemPosition(), 1, false, isSelected()));
        if (isSelected()) {
            cVarD0.c0(false);
            cVarD0.S(c.a.e);
        }
        cVarD0.t0(getResources().getString(j.g));
    }

    void setBadge(BadgeDrawable badgeDrawable) {
        this.q = badgeDrawable;
        ImageView imageView = this.i;
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
        int i = this.g;
        if (i != -1) {
            if (i == 0) {
                if (z) {
                    i(this.i, this.f2210c, 49);
                    j(this.k, 1.0f, 1.0f, 0);
                } else {
                    i(this.i, this.f2210c, 17);
                    j(this.k, 0.5f, 0.5f, 4);
                }
                this.j.setVisibility(4);
            } else if (i != 1) {
                if (i == 2) {
                    i(this.i, this.f2210c, 17);
                    this.k.setVisibility(8);
                    this.j.setVisibility(8);
                }
            } else if (z) {
                i(this.i, (int) (this.f2210c + this.f2211d), 49);
                j(this.k, 1.0f, 1.0f, 0);
                TextView textView = this.j;
                float f = this.e;
                j(textView, f, f, 4);
            } else {
                i(this.i, this.f2210c, 49);
                TextView textView2 = this.k;
                float f2 = this.f;
                j(textView2, f2, f2, 4);
                j(this.j, 1.0f, 1.0f, 0);
            }
        } else if (this.h) {
            if (z) {
                i(this.i, this.f2210c, 49);
                j(this.k, 1.0f, 1.0f, 0);
            } else {
                i(this.i, this.f2210c, 17);
                j(this.k, 0.5f, 0.5f, 4);
            }
            this.j.setVisibility(4);
        } else if (z) {
            i(this.i, (int) (this.f2210c + this.f2211d), 49);
            j(this.k, 1.0f, 1.0f, 0);
            TextView textView3 = this.j;
            float f3 = this.e;
            j(textView3, f3, f3, 4);
        } else {
            i(this.i, this.f2210c, 49);
            TextView textView4 = this.k;
            float f4 = this.f;
            j(textView4, f4, f4, 4);
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
        this.i.setEnabled(z);
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
        this.i.setImageDrawable(drawable);
    }

    public void setIconSize(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.i.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        this.i.setLayoutParams(layoutParams);
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

    public void setItemBackground(int i) {
        setItemBackground(i == 0 ? null : androidx.core.content.a.d(getContext(), i));
    }

    public void setItemPosition(int i) {
        this.l = i;
    }

    public void setLabelVisibilityMode(int i) {
        if (this.g != i) {
            this.g = i;
            i iVar = this.m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setShifting(boolean z) {
        if (this.h != z) {
            this.h = z;
            i iVar = this.m;
            if (iVar != null) {
                setChecked(iVar.isChecked());
            }
        }
    }

    public void setTextAppearanceActive(int i) {
        androidx.core.widget.i.q(this.k, i);
        c(this.j.getTextSize(), this.k.getTextSize());
    }

    public void setTextAppearanceInactive(int i) {
        androidx.core.widget.i.q(this.j, i);
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

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = -1;
        Resources resources = getResources();
        LayoutInflater.from(context).inflate(h.f1993a, (ViewGroup) this, true);
        setBackgroundResource(e.f1983a);
        this.f2210c = resources.getDimensionPixelSize(d.h);
        this.i = (ImageView) findViewById(f.e);
        TextView textView = (TextView) findViewById(f.t);
        this.j = textView;
        TextView textView2 = (TextView) findViewById(f.f);
        this.k = textView2;
        t.t0(textView, 2);
        t.t0(textView2, 2);
        setFocusable(true);
        c(textView.getTextSize(), textView2.getTextSize());
        ImageView imageView = this.i;
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
