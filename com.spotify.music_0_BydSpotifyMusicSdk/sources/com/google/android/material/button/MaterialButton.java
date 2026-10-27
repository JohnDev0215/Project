package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatButton;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;
import p.af1;
import p.bf1;
import p.bk2;
import p.ci1;
import p.df1;
import p.f60;
import p.jt2;
import p.l13;
import p.oc2;
import p.oh1;
import p.q30;
import p.v03;
import p.wg;
import p.xz1;
import p.y0;
import p.ya0;
import p.zc2;
import p.ze1;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, zc2 {
    public static final int[] y = {R.attr.state_checkable};
    public static final int[] z = {R.attr.state_checked};
    public final bf1 l;
    public final LinkedHashSet m;
    public ze1 n;
    public PorterDuff.Mode o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f30p;
    public Drawable q;
    public int r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public boolean w;
    public int x;

    public MaterialButton(Context context, AttributeSet attributeSet) {
        super(f60.S(context, attributeSet, com.spotify.music.R.attr.materialButtonStyle, com.spotify.music.R.style.Widget_MaterialComponents_Button), attributeSet, com.spotify.music.R.attr.materialButtonStyle);
        this.m = new LinkedHashSet();
        this.v = false;
        this.w = false;
        Context context2 = getContext();
        TypedArray typedArrayE = v03.E(context2, attributeSet, xz1.k, com.spotify.music.R.attr.materialButtonStyle, com.spotify.music.R.style.Widget_MaterialComponents_Button, new int[0]);
        this.u = typedArrayE.getDimensionPixelSize(12, 0);
        int i = typedArrayE.getInt(15, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.o = f60.B(i, mode);
        this.f30p = ci1.v(getContext(), typedArrayE, 14);
        this.q = ci1.x(getContext(), typedArrayE, 10);
        this.x = typedArrayE.getInteger(11, 1);
        this.r = typedArrayE.getDimensionPixelSize(13, 0);
        bf1 bf1Var = new bf1(this, oc2.b(context2, attributeSet, com.spotify.music.R.attr.materialButtonStyle, com.spotify.music.R.style.Widget_MaterialComponents_Button).a());
        this.l = bf1Var;
        bf1Var.c = typedArrayE.getDimensionPixelOffset(1, 0);
        bf1Var.d = typedArrayE.getDimensionPixelOffset(2, 0);
        bf1Var.e = typedArrayE.getDimensionPixelOffset(3, 0);
        bf1Var.f = typedArrayE.getDimensionPixelOffset(4, 0);
        if (typedArrayE.hasValue(8)) {
            int dimensionPixelSize = typedArrayE.getDimensionPixelSize(8, -1);
            bf1Var.g = dimensionPixelSize;
            float f = dimensionPixelSize;
            wg wgVarE = bf1Var.b.e();
            wgVarE.e = new y0(f);
            wgVarE.f = new y0(f);
            wgVarE.g = new y0(f);
            wgVarE.h = new y0(f);
            bf1Var.c(wgVarE.a());
            bf1Var.f106p = true;
        }
        bf1Var.h = typedArrayE.getDimensionPixelSize(20, 0);
        bf1Var.i = f60.B(typedArrayE.getInt(7, -1), mode);
        bf1Var.j = ci1.v(getContext(), typedArrayE, 6);
        bf1Var.k = ci1.v(getContext(), typedArrayE, 19);
        bf1Var.l = ci1.v(getContext(), typedArrayE, 16);
        bf1Var.q = typedArrayE.getBoolean(5, false);
        bf1Var.s = typedArrayE.getDimensionPixelSize(9, 0);
        WeakHashMap weakHashMap = zt2.a;
        int iF = jt2.f(this);
        int paddingTop = getPaddingTop();
        int iE = jt2.e(this);
        int paddingBottom = getPaddingBottom();
        if (typedArrayE.hasValue(0)) {
            bf1Var.o = true;
            setSupportBackgroundTintList(bf1Var.j);
            setSupportBackgroundTintMode(bf1Var.i);
        } else {
            bf1Var.e();
        }
        jt2.k(this, iF + bf1Var.c, paddingTop + bf1Var.e, iE + bf1Var.d, paddingBottom + bf1Var.f);
        typedArrayE.recycle();
        setCompoundDrawablePadding(this.u);
        d(this.q != null);
    }

    private String getA11yClassName() {
        return (a() ? CompoundButton.class : Button.class).getName();
    }

    private int getTextHeight() {
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextWidth() {
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        return Math.min((int) paint.measureText(string), getLayout().getEllipsizedWidth());
    }

    public final boolean a() {
        bf1 bf1Var = this.l;
        return bf1Var != null && bf1Var.q;
    }

    public final boolean b() {
        bf1 bf1Var = this.l;
        return (bf1Var == null || bf1Var.o) ? false : true;
    }

    public final void c() {
        int i = this.x;
        boolean z2 = true;
        if (i != 1 && i != 2) {
            z2 = false;
        }
        if (z2) {
            bk2.e(this, this.q, null, null, null);
            return;
        }
        if (i == 3 || i == 4) {
            bk2.e(this, null, null, this.q, null);
        } else if (i == 16 || i == 32) {
            bk2.e(this, null, this.q, null, null);
        }
    }

    public final void d(boolean z2) {
        Drawable drawable = this.q;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.q = drawableMutate;
            ya0.h(drawableMutate, this.f30p);
            PorterDuff.Mode mode = this.o;
            if (mode != null) {
                ya0.i(this.q, mode);
            }
            int intrinsicWidth = this.r;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.q.getIntrinsicWidth();
            }
            int intrinsicHeight = this.r;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.q.getIntrinsicHeight();
            }
            Drawable drawable2 = this.q;
            int i = this.s;
            int i2 = this.t;
            drawable2.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.q.setVisible(true, z2);
        }
        if (z2) {
            c();
            return;
        }
        Drawable[] drawableArrA = bk2.a(this);
        Drawable drawable3 = drawableArrA[0];
        Drawable drawable4 = drawableArrA[1];
        Drawable drawable5 = drawableArrA[2];
        int i3 = this.x;
        if (((i3 == 1 || i3 == 2) && drawable3 != this.q) || (((i3 == 3 || i3 == 4) && drawable5 != this.q) || ((i3 == 16 || i3 == 32) && drawable4 != this.q))) {
            c();
        }
    }

    public final void e(int i, int i2) {
        if (this.q == null || getLayout() == null) {
            return;
        }
        int i3 = this.x;
        if (!(i3 == 1 || i3 == 2) && i3 != 3 && i3 != 4) {
            if (i3 == 16 || i3 == 32) {
                this.s = 0;
                if (i3 == 16) {
                    this.t = 0;
                    d(false);
                    return;
                }
                int intrinsicHeight = this.r;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.q.getIntrinsicHeight();
                }
                int textHeight = (((((i2 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.u) - getPaddingBottom()) / 2;
                if (this.t != textHeight) {
                    this.t = textHeight;
                    d(false);
                    return;
                }
                return;
            }
            return;
        }
        this.t = 0;
        if (i3 == 1 || i3 == 3) {
            this.s = 0;
            d(false);
            return;
        }
        int intrinsicWidth = this.r;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.q.getIntrinsicWidth();
        }
        int textWidth = i - getTextWidth();
        WeakHashMap weakHashMap = zt2.a;
        int iE = ((((textWidth - jt2.e(this)) - intrinsicWidth) - this.u) - jt2.f(this)) / 2;
        if ((jt2.d(this) == 1) != (this.x == 4)) {
            iE = -iE;
        }
        if (this.s != iE) {
            this.s = iE;
            d(false);
        }
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (b()) {
            return this.l.g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.q;
    }

    public int getIconGravity() {
        return this.x;
    }

    public int getIconPadding() {
        return this.u;
    }

    public int getIconSize() {
        return this.r;
    }

    public ColorStateList getIconTint() {
        return this.f30p;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.o;
    }

    public int getInsetBottom() {
        return this.l.f;
    }

    public int getInsetTop() {
        return this.l.e;
    }

    public ColorStateList getRippleColor() {
        if (b()) {
            return this.l.l;
        }
        return null;
    }

    public oc2 getShapeAppearanceModel() {
        if (b()) {
            return this.l.b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (b()) {
            return this.l.k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (b()) {
            return this.l.h;
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return b() ? this.l.j : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return b() ? this.l.i : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.v;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (b()) {
            f60.I(this, this.l.b(false));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (a()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, y);
        }
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, z);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public final void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        super.onLayout(z2, i, i2, i3, i4);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof af1)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        af1 af1Var = (af1) parcelable;
        super.onRestoreInstanceState(af1Var.c);
        setChecked(af1Var.k);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        af1 af1Var = new af1(super.onSaveInstanceState());
        af1Var.k = this.v;
        return af1Var;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        e(i, i2);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.q != null) {
            if (this.q.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (!b()) {
            super.setBackgroundColor(i);
            return;
        }
        bf1 bf1Var = this.l;
        if (bf1Var.b(false) != null) {
            bf1Var.b(false).setTint(i);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!b()) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        if (drawable == getBackground()) {
            getBackground().setState(drawable.getState());
            return;
        }
        bf1 bf1Var = this.l;
        bf1Var.o = true;
        ColorStateList colorStateList = bf1Var.j;
        MaterialButton materialButton = bf1Var.a;
        materialButton.setSupportBackgroundTintList(colorStateList);
        materialButton.setSupportBackgroundTintMode(bf1Var.i);
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? l13.f(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z2) {
        if (b()) {
            this.l.q = z2;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z2) {
        if (a() && isEnabled() && this.v != z2) {
            this.v = z2;
            refreshDrawableState();
            if (this.w) {
                return;
            }
            this.w = true;
            for (df1 df1Var : this.m) {
                boolean z3 = this.v;
                MaterialButtonToggleGroup materialButtonToggleGroup = df1Var.a;
                if (!materialButtonToggleGroup.o) {
                    if (materialButtonToggleGroup.f31p) {
                        materialButtonToggleGroup.r = z3 ? getId() : -1;
                    }
                    if (materialButtonToggleGroup.d(getId(), z3)) {
                        materialButtonToggleGroup.b(getId(), isChecked());
                    }
                    materialButtonToggleGroup.invalidate();
                }
            }
            this.w = false;
        }
    }

    public void setCornerRadius(int i) {
        if (b()) {
            bf1 bf1Var = this.l;
            if (bf1Var.f106p && bf1Var.g == i) {
                return;
            }
            bf1Var.g = i;
            bf1Var.f106p = true;
            float f = i;
            wg wgVarE = bf1Var.b.e();
            wgVarE.e = new y0(f);
            wgVarE.f = new y0(f);
            wgVarE.g = new y0(f);
            wgVarE.h = new y0(f);
            bf1Var.c(wgVarE.a());
        }
    }

    public void setCornerRadiusResource(int i) {
        if (b()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        if (b()) {
            this.l.b(false).j(f);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.q != drawable) {
            this.q = drawable;
            d(true);
            e(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.x != i) {
            this.x = i;
            e(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.u != i) {
            this.u = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? l13.f(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.r != i) {
            this.r = i;
            d(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f30p != colorStateList) {
            this.f30p = colorStateList;
            d(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.o != mode) {
            this.o = mode;
            d(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(l13.d(getContext(), i));
    }

    public void setInsetBottom(int i) {
        bf1 bf1Var = this.l;
        bf1Var.d(bf1Var.e, i);
    }

    public void setInsetTop(int i) {
        bf1 bf1Var = this.l;
        bf1Var.d(i, bf1Var.f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(ze1 ze1Var) {
        this.n = ze1Var;
    }

    @Override // android.view.View
    public void setPressed(boolean z2) {
        ze1 ze1Var = this.n;
        if (ze1Var != null) {
            ((MaterialButtonToggleGroup) ((oh1) ze1Var).e).invalidate();
        }
        super.setPressed(z2);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (b()) {
            bf1 bf1Var = this.l;
            if (bf1Var.l != colorStateList) {
                bf1Var.l = colorStateList;
                MaterialButton materialButton = bf1Var.a;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButton.getBackground()).setColor(q30.t(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i) {
        if (b()) {
            setRippleColor(l13.d(getContext(), i));
        }
    }

    @Override // p.zc2
    public void setShapeAppearanceModel(oc2 oc2Var) {
        if (!b()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.l.c(oc2Var);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z2) {
        if (b()) {
            bf1 bf1Var = this.l;
            bf1Var.n = z2;
            bf1Var.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (b()) {
            bf1 bf1Var = this.l;
            if (bf1Var.k != colorStateList) {
                bf1Var.k = colorStateList;
                bf1Var.f();
            }
        }
    }

    public void setStrokeColorResource(int i) {
        if (b()) {
            setStrokeColor(l13.d(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (b()) {
            bf1 bf1Var = this.l;
            if (bf1Var.h != i) {
                bf1Var.h = i;
                bf1Var.f();
            }
        }
    }

    public void setStrokeWidthResource(int i) {
        if (b()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (!b()) {
            super.setSupportBackgroundTintList(colorStateList);
            return;
        }
        bf1 bf1Var = this.l;
        if (bf1Var.j != colorStateList) {
            bf1Var.j = colorStateList;
            if (bf1Var.b(false) != null) {
                ya0.h(bf1Var.b(false), bf1Var.j);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!b()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        bf1 bf1Var = this.l;
        if (bf1Var.i != mode) {
            bf1Var.i = mode;
            if (bf1Var.b(false) == null || bf1Var.i == null) {
                return;
            }
            ya0.i(bf1Var.b(false), bf1Var.i);
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.v);
    }
}
