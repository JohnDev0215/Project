package com.google.android.material.button;

import F1.H;
import N1.C0420a;
import Q4.a;
import Q4.b;
import Q4.c;
import Q4.e;
import U4.k;
import Y4.j;
import Y4.u;
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
import b5.AbstractC0997a;
import com.bumptech.glide.d;
import h6.f;
import h6.n;
import java.util.LinkedHashSet;
import r.AbstractC2255n;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AbstractC2255n implements Checkable, u {
    public static final int[] B = {R.attr.state_checkable};

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int[] f12795C = {R.attr.state_checked};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f12796A;
    public final c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final LinkedHashSet f12797p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public a f12798q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public PorterDuff.Mode f12799r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f12800s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Drawable f12801t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f12802u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f12803v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f12804w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f12805x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f12806y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f12807z;

    public MaterialButton(Context context, AttributeSet attributeSet) {
        super(AbstractC0997a.a(context, attributeSet, com.stingray.drivemodedetector.R.attr.materialButtonStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Button), attributeSet);
        this.f12797p = new LinkedHashSet();
        this.f12806y = false;
        this.f12807z = false;
        Context context2 = getContext();
        TypedArray typedArrayE = k.e(context2, attributeSet, K4.a.f3592j, com.stingray.drivemodedetector.R.attr.materialButtonStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Button, new int[0]);
        this.f12805x = typedArrayE.getDimensionPixelSize(12, 0);
        int i7 = typedArrayE.getInt(15, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.f12799r = k.f(i7, mode);
        this.f12800s = com.bumptech.glide.c.Z(getContext(), typedArrayE, 14);
        this.f12801t = com.bumptech.glide.c.c0(getContext(), typedArrayE, 10);
        this.f12796A = typedArrayE.getInteger(11, 1);
        this.f12802u = typedArrayE.getDimensionPixelSize(13, 0);
        c cVar = new c(this, Y4.k.a(context2, attributeSet, com.stingray.drivemodedetector.R.attr.materialButtonStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Button).a());
        this.o = cVar;
        cVar.c = typedArrayE.getDimensionPixelOffset(1, 0);
        cVar.d = typedArrayE.getDimensionPixelOffset(2, 0);
        cVar.f6225e = typedArrayE.getDimensionPixelOffset(3, 0);
        cVar.f6226f = typedArrayE.getDimensionPixelOffset(4, 0);
        if (typedArrayE.hasValue(8)) {
            int dimensionPixelSize = typedArrayE.getDimensionPixelSize(8, -1);
            cVar.f6227g = dimensionPixelSize;
            float f10 = dimensionPixelSize;
            j jVarD = cVar.f6224b.d();
            jVarD.f10741e = new Y4.a(f10);
            jVarD.f10742f = new Y4.a(f10);
            jVarD.f10743g = new Y4.a(f10);
            jVarD.f10744h = new Y4.a(f10);
            cVar.c(jVarD.a());
            cVar.f6234p = true;
        }
        cVar.f6228h = typedArrayE.getDimensionPixelSize(20, 0);
        cVar.f6229i = k.f(typedArrayE.getInt(7, -1), mode);
        cVar.f6230j = com.bumptech.glide.c.Z(getContext(), typedArrayE, 6);
        cVar.f6231k = com.bumptech.glide.c.Z(getContext(), typedArrayE, 19);
        cVar.l = com.bumptech.glide.c.Z(getContext(), typedArrayE, 16);
        cVar.f6235q = typedArrayE.getBoolean(5, false);
        cVar.f6237s = typedArrayE.getDimensionPixelSize(9, 0);
        int[] iArr = H.f2303a;
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        if (typedArrayE.hasValue(0)) {
            cVar.o = true;
            setSupportBackgroundTintList(cVar.f6230j);
            setSupportBackgroundTintMode(cVar.f6229i);
        } else {
            cVar.e();
        }
        setPaddingRelative(paddingStart + cVar.c, paddingTop + cVar.f6225e, paddingEnd + cVar.d, paddingBottom + cVar.f6226f);
        typedArrayE.recycle();
        setCompoundDrawablePadding(this.f12805x);
        d(this.f12801t != null);
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
        c cVar = this.o;
        return cVar != null && cVar.f6235q;
    }

    public final boolean b() {
        c cVar = this.o;
        return (cVar == null || cVar.o) ? false : true;
    }

    public final void c() {
        int i7 = this.f12796A;
        boolean z6 = true;
        if (i7 != 1 && i7 != 2) {
            z6 = false;
        }
        if (z6) {
            setCompoundDrawablesRelative(this.f12801t, null, null, null);
            return;
        }
        if (i7 == 3 || i7 == 4) {
            setCompoundDrawablesRelative(null, null, this.f12801t, null);
        } else if (i7 == 16 || i7 == 32) {
            setCompoundDrawablesRelative(null, this.f12801t, null, null);
        }
    }

    public final void d(boolean z6) {
        Drawable drawable = this.f12801t;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.f12801t = drawableMutate;
            drawableMutate.setTintList(this.f12800s);
            PorterDuff.Mode mode = this.f12799r;
            if (mode != null) {
                this.f12801t.setTintMode(mode);
            }
            int intrinsicWidth = this.f12802u;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.f12801t.getIntrinsicWidth();
            }
            int intrinsicHeight = this.f12802u;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.f12801t.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f12801t;
            int i7 = this.f12803v;
            int i10 = this.f12804w;
            drawable2.setBounds(i7, i10, intrinsicWidth + i7, intrinsicHeight + i10);
        }
        if (z6) {
            c();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        int i11 = this.f12796A;
        if (((i11 == 1 || i11 == 2) && drawable3 != this.f12801t) || (((i11 == 3 || i11 == 4) && drawable5 != this.f12801t) || ((i11 == 16 || i11 == 32) && drawable4 != this.f12801t))) {
            c();
        }
    }

    public final void e(int i7, int i10) {
        if (this.f12801t == null || getLayout() == null) {
            return;
        }
        int i11 = this.f12796A;
        if (!(i11 == 1 || i11 == 2) && i11 != 3 && i11 != 4) {
            if (i11 == 16 || i11 == 32) {
                this.f12803v = 0;
                if (i11 == 16) {
                    this.f12804w = 0;
                    d(false);
                    return;
                }
                int intrinsicHeight = this.f12802u;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.f12801t.getIntrinsicHeight();
                }
                int textHeight = (((((i10 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.f12805x) - getPaddingBottom()) / 2;
                if (this.f12804w != textHeight) {
                    this.f12804w = textHeight;
                    d(false);
                    return;
                }
                return;
            }
            return;
        }
        this.f12804w = 0;
        if (i11 == 1 || i11 == 3) {
            this.f12803v = 0;
            d(false);
            return;
        }
        int intrinsicWidth = this.f12802u;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.f12801t.getIntrinsicWidth();
        }
        int textWidth = i7 - getTextWidth();
        int[] iArr = H.f2303a;
        int paddingEnd = ((((textWidth - getPaddingEnd()) - intrinsicWidth) - this.f12805x) - getPaddingStart()) / 2;
        if ((getLayoutDirection() == 1) != (this.f12796A == 4)) {
            paddingEnd = -paddingEnd;
        }
        if (this.f12803v != paddingEnd) {
            this.f12803v = paddingEnd;
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
            return this.o.f6227g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f12801t;
    }

    public int getIconGravity() {
        return this.f12796A;
    }

    public int getIconPadding() {
        return this.f12805x;
    }

    public int getIconSize() {
        return this.f12802u;
    }

    public ColorStateList getIconTint() {
        return this.f12800s;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f12799r;
    }

    public int getInsetBottom() {
        return this.o.f6226f;
    }

    public int getInsetTop() {
        return this.o.f6225e;
    }

    public ColorStateList getRippleColor() {
        if (b()) {
            return this.o.l;
        }
        return null;
    }

    public Y4.k getShapeAppearanceModel() {
        if (b()) {
            return this.o.f6224b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (b()) {
            return this.o.f6231k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (b()) {
            return this.o.f6228h;
        }
        return 0;
    }

    @Override // r.AbstractC2255n
    public ColorStateList getSupportBackgroundTintList() {
        return b() ? this.o.f6230j : super.getSupportBackgroundTintList();
    }

    @Override // r.AbstractC2255n
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return b() ? this.o.f6229i : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f12806y;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (b()) {
            d.L(this, this.o.b(false));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (a()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, B);
        }
        if (this.f12806y) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f12795C);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // r.AbstractC2255n, android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(this.f12806y);
    }

    @Override // r.AbstractC2255n, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(this.f12806y);
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.l);
        setChecked(bVar.f6222n);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f6222n = this.f12806y;
        return bVar;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        e(i7, i10);
    }

    @Override // r.AbstractC2255n, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        super.onTextChanged(charSequence, i7, i10, i11);
        e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        if (!b()) {
            super.setBackgroundColor(i7);
            return;
        }
        c cVar = this.o;
        if (cVar.b(false) != null) {
            cVar.b(false).setTint(i7);
        }
    }

    @Override // r.AbstractC2255n, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!b()) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        if (drawable == getBackground()) {
            getBackground().setState(drawable.getState());
            return;
        }
        n.t("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
        c cVar = this.o;
        cVar.o = true;
        ColorStateList colorStateList = cVar.f6230j;
        MaterialButton materialButton = cVar.f6223a;
        materialButton.setSupportBackgroundTintList(colorStateList);
        materialButton.setSupportBackgroundTintMode(cVar.f6229i);
        super.setBackgroundDrawable(drawable);
    }

    @Override // r.AbstractC2255n, android.view.View
    public void setBackgroundResource(int i7) {
        setBackgroundDrawable(i7 != 0 ? f.k(getContext(), i7) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z6) {
        if (b()) {
            this.o.f6235q = z6;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (a() && isEnabled() && this.f12806y != z6) {
            this.f12806y = z6;
            refreshDrawableState();
            if (this.f12807z) {
                return;
            }
            this.f12807z = true;
            for (e eVar : this.f12797p) {
                boolean z10 = this.f12806y;
                MaterialButtonToggleGroup materialButtonToggleGroup = eVar.f6239a;
                if (!materialButtonToggleGroup.f12813r) {
                    if (materialButtonToggleGroup.f12814s) {
                        materialButtonToggleGroup.f12816u = z10 ? getId() : -1;
                    }
                    if (materialButtonToggleGroup.d(getId(), z10)) {
                        materialButtonToggleGroup.b(getId(), this.f12806y);
                    }
                    materialButtonToggleGroup.invalidate();
                }
            }
            this.f12807z = false;
        }
    }

    public void setCornerRadius(int i7) {
        if (b()) {
            c cVar = this.o;
            if (cVar.f6234p && cVar.f6227g == i7) {
                return;
            }
            cVar.f6227g = i7;
            cVar.f6234p = true;
            float f10 = i7;
            j jVarD = cVar.f6224b.d();
            jVarD.f10741e = new Y4.a(f10);
            jVarD.f10742f = new Y4.a(f10);
            jVarD.f10743g = new Y4.a(f10);
            jVarD.f10744h = new Y4.a(f10);
            cVar.c(jVarD.a());
        }
    }

    public void setCornerRadiusResource(int i7) {
        if (b()) {
            setCornerRadius(getResources().getDimensionPixelSize(i7));
        }
    }

    @Override // android.view.View
    public void setElevation(float f10) {
        super.setElevation(f10);
        if (b()) {
            this.o.b(false).g(f10);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f12801t != drawable) {
            this.f12801t = drawable;
            d(true);
            e(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i7) {
        if (this.f12796A != i7) {
            this.f12796A = i7;
            e(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i7) {
        if (this.f12805x != i7) {
            this.f12805x = i7;
            setCompoundDrawablePadding(i7);
        }
    }

    public void setIconResource(int i7) {
        setIcon(i7 != 0 ? f.k(getContext(), i7) : null);
    }

    public void setIconSize(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.f12802u != i7) {
            this.f12802u = i7;
            d(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f12800s != colorStateList) {
            this.f12800s = colorStateList;
            d(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f12799r != mode) {
            this.f12799r = mode;
            d(false);
        }
    }

    public void setIconTintResource(int i7) {
        setIconTint(f.h(getContext(), i7));
    }

    public void setInsetBottom(int i7) {
        c cVar = this.o;
        cVar.d(cVar.f6225e, i7);
    }

    public void setInsetTop(int i7) {
        c cVar = this.o;
        cVar.d(i7, cVar.f6226f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(a aVar) {
        this.f12798q = aVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        a aVar = this.f12798q;
        if (aVar != null) {
            ((MaterialButtonToggleGroup) ((C0420a) aVar).l).invalidate();
        }
        super.setPressed(z6);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (b()) {
            c cVar = this.o;
            if (cVar.l != colorStateList) {
                cVar.l = colorStateList;
                MaterialButton materialButton = cVar.f6223a;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    RippleDrawable rippleDrawable = (RippleDrawable) materialButton.getBackground();
                    if (colorStateList == null) {
                        colorStateList = ColorStateList.valueOf(0);
                    }
                    rippleDrawable.setColor(colorStateList);
                }
            }
        }
    }

    public void setRippleColorResource(int i7) {
        if (b()) {
            setRippleColor(f.h(getContext(), i7));
        }
    }

    @Override // Y4.u
    public void setShapeAppearanceModel(Y4.k kVar) {
        if (!b()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.o.c(kVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z6) {
        if (b()) {
            c cVar = this.o;
            cVar.f6233n = z6;
            cVar.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (b()) {
            c cVar = this.o;
            if (cVar.f6231k != colorStateList) {
                cVar.f6231k = colorStateList;
                cVar.f();
            }
        }
    }

    public void setStrokeColorResource(int i7) {
        if (b()) {
            setStrokeColor(f.h(getContext(), i7));
        }
    }

    public void setStrokeWidth(int i7) {
        if (b()) {
            c cVar = this.o;
            if (cVar.f6228h != i7) {
                cVar.f6228h = i7;
                cVar.f();
            }
        }
    }

    public void setStrokeWidthResource(int i7) {
        if (b()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i7));
        }
    }

    @Override // r.AbstractC2255n
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (!b()) {
            super.setSupportBackgroundTintList(colorStateList);
            return;
        }
        c cVar = this.o;
        if (cVar.f6230j != colorStateList) {
            cVar.f6230j = colorStateList;
            if (cVar.b(false) != null) {
                cVar.b(false).setTintList(cVar.f6230j);
            }
        }
    }

    @Override // r.AbstractC2255n
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!b()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        c cVar = this.o;
        if (cVar.f6229i != mode) {
            cVar.f6229i = mode;
            if (cVar.b(false) == null || cVar.f6229i == null) {
                return;
            }
            cVar.b(false).setTintMode(cVar.f6229i);
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.f12806y);
    }
}
