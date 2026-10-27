package com.google.android.material.button;

import a.b.k.l;
import a.b.p.f;
import a.h.k.q;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import b.d.a.a.e0.g;
import b.d.a.a.e0.n;
import b.d.a.a.j;
import b.d.a.a.z.k;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends f implements Checkable, n {
    public static final int[] p = {R.attr.state_checkable};
    public static final int[] q = {R.attr.state_checked};
    public static final int r = j.Widget_MaterialComponents_Button;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.d.a.a.q.a f2403c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet<a> f2404d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f2405e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PorterDuff.Mode f2406f;
    public ColorStateList g;
    public Drawable h;
    public int i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public boolean n;
    public int o;

    public interface a {
        void a(MaterialButton materialButton, boolean z);
    }

    public interface b {
    }

    public static class c extends a.j.a.a {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2407c;

        public static class a implements Parcelable.ClassLoaderCreator<c> {
            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new c(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new c[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public c createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new c(parcel, classLoader);
            }
        }

        public c(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                c.class.getClassLoader();
            }
            this.f2407c = parcel.readInt() == 1;
        }

        public c(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // a.j.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f892a, i);
            parcel.writeInt(this.f2407c ? 1 : 0);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet) {
        int i = b.d.a.a.b.materialButtonStyle;
        super(b.d.a.a.i0.a.a.a(context, attributeSet, i, r), attributeSet, i);
        this.f2404d = new LinkedHashSet<>();
        this.m = false;
        this.n = false;
        Context context2 = getContext();
        TypedArray typedArrayD = k.d(context2, attributeSet, b.d.a.a.k.MaterialButton, i, r, new int[0]);
        this.l = typedArrayD.getDimensionPixelSize(b.d.a.a.k.MaterialButton_iconPadding, 0);
        this.f2406f = l.i.e0(typedArrayD.getInt(b.d.a.a.k.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.g = l.i.y(getContext(), typedArrayD, b.d.a.a.k.MaterialButton_iconTint);
        this.h = l.i.A(getContext(), typedArrayD, b.d.a.a.k.MaterialButton_icon);
        this.o = typedArrayD.getInteger(b.d.a.a.k.MaterialButton_iconGravity, 1);
        this.i = typedArrayD.getDimensionPixelSize(b.d.a.a.k.MaterialButton_iconSize, 0);
        b.d.a.a.q.a aVar = new b.d.a.a.q.a(this, b.d.a.a.e0.j.b(context2, attributeSet, i, r).a());
        this.f2403c = aVar;
        aVar.f2046c = typedArrayD.getDimensionPixelOffset(b.d.a.a.k.MaterialButton_android_insetLeft, 0);
        aVar.f2047d = typedArrayD.getDimensionPixelOffset(b.d.a.a.k.MaterialButton_android_insetRight, 0);
        aVar.f2048e = typedArrayD.getDimensionPixelOffset(b.d.a.a.k.MaterialButton_android_insetTop, 0);
        aVar.f2049f = typedArrayD.getDimensionPixelOffset(b.d.a.a.k.MaterialButton_android_insetBottom, 0);
        if (typedArrayD.hasValue(b.d.a.a.k.MaterialButton_cornerRadius)) {
            int dimensionPixelSize = typedArrayD.getDimensionPixelSize(b.d.a.a.k.MaterialButton_cornerRadius, -1);
            aVar.g = dimensionPixelSize;
            aVar.e(aVar.f2045b.e(dimensionPixelSize));
            aVar.p = true;
        }
        aVar.h = typedArrayD.getDimensionPixelSize(b.d.a.a.k.MaterialButton_strokeWidth, 0);
        aVar.i = l.i.e0(typedArrayD.getInt(b.d.a.a.k.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        aVar.j = l.i.y(aVar.f2044a.getContext(), typedArrayD, b.d.a.a.k.MaterialButton_backgroundTint);
        aVar.k = l.i.y(aVar.f2044a.getContext(), typedArrayD, b.d.a.a.k.MaterialButton_strokeColor);
        aVar.l = l.i.y(aVar.f2044a.getContext(), typedArrayD, b.d.a.a.k.MaterialButton_rippleColor);
        aVar.q = typedArrayD.getBoolean(b.d.a.a.k.MaterialButton_android_checkable, false);
        aVar.s = typedArrayD.getDimensionPixelSize(b.d.a.a.k.MaterialButton_elevation, 0);
        int iT = q.t(aVar.f2044a);
        int paddingTop = aVar.f2044a.getPaddingTop();
        int paddingEnd = aVar.f2044a.getPaddingEnd();
        int paddingBottom = aVar.f2044a.getPaddingBottom();
        if (typedArrayD.hasValue(b.d.a.a.k.MaterialButton_android_background)) {
            aVar.o = true;
            aVar.f2044a.setSupportBackgroundTintList(aVar.j);
            aVar.f2044a.setSupportBackgroundTintMode(aVar.i);
        } else {
            aVar.g();
        }
        aVar.f2044a.setPaddingRelative(iT + aVar.f2046c, paddingTop + aVar.f2048e, paddingEnd + aVar.f2047d, paddingBottom + aVar.f2049f);
        typedArrayD.recycle();
        setCompoundDrawablePadding(this.l);
        g(this.h != null);
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

    public boolean a() {
        b.d.a.a.q.a aVar = this.f2403c;
        return aVar != null && aVar.q;
    }

    public final boolean b() {
        int i = this.o;
        return i == 3 || i == 4;
    }

    public final boolean c() {
        int i = this.o;
        return i == 1 || i == 2;
    }

    public final boolean d() {
        int i = this.o;
        return i == 16 || i == 32;
    }

    public final boolean e() {
        b.d.a.a.q.a aVar = this.f2403c;
        return (aVar == null || aVar.o) ? false : true;
    }

    public final void f() {
        if (c()) {
            setCompoundDrawablesRelative(this.h, null, null, null);
        } else if (b()) {
            setCompoundDrawablesRelative(null, null, this.h, null);
        } else if (d()) {
            setCompoundDrawablesRelative(null, this.h, null, null);
        }
    }

    public final void g(boolean z) {
        Drawable drawable = this.h;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.h = drawableMutate;
            drawableMutate.setTintList(this.g);
            PorterDuff.Mode mode = this.f2406f;
            if (mode != null) {
                this.h.setTintMode(mode);
            }
            int intrinsicWidth = this.i;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.h.getIntrinsicWidth();
            }
            int intrinsicHeight = this.i;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.h.getIntrinsicHeight();
            }
            Drawable drawable2 = this.h;
            int i = this.j;
            int i2 = this.k;
            drawable2.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
        }
        if (z) {
            f();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        boolean z2 = false;
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        if ((c() && drawable3 != this.h) || ((b() && drawable5 != this.h) || (d() && drawable4 != this.h))) {
            z2 = true;
        }
        if (z2) {
            f();
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
        if (e()) {
            return this.f2403c.g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.h;
    }

    public int getIconGravity() {
        return this.o;
    }

    public int getIconPadding() {
        return this.l;
    }

    public int getIconSize() {
        return this.i;
    }

    public ColorStateList getIconTint() {
        return this.g;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f2406f;
    }

    public int getInsetBottom() {
        return this.f2403c.f2049f;
    }

    public int getInsetTop() {
        return this.f2403c.f2048e;
    }

    public ColorStateList getRippleColor() {
        if (e()) {
            return this.f2403c.l;
        }
        return null;
    }

    public b.d.a.a.e0.j getShapeAppearanceModel() {
        if (e()) {
            return this.f2403c.f2045b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (e()) {
            return this.f2403c.k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (e()) {
            return this.f2403c.h;
        }
        return 0;
    }

    @Override // a.b.p.f
    public ColorStateList getSupportBackgroundTintList() {
        return e() ? this.f2403c.j : super.getSupportBackgroundTintList();
    }

    @Override // a.b.p.f
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return e() ? this.f2403c.i : super.getSupportBackgroundTintMode();
    }

    public final void h(int i, int i2) {
        if (this.h == null || getLayout() == null) {
            return;
        }
        if (!c() && !b()) {
            if (d()) {
                this.j = 0;
                if (this.o == 16) {
                    this.k = 0;
                    g(false);
                    return;
                }
                int intrinsicHeight = this.i;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.h.getIntrinsicHeight();
                }
                int textHeight = (((((i2 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.l) - getPaddingBottom()) / 2;
                if (this.k != textHeight) {
                    this.k = textHeight;
                    g(false);
                }
                return;
            }
            return;
        }
        this.k = 0;
        int i3 = this.o;
        if (i3 == 1 || i3 == 3) {
            this.j = 0;
            g(false);
            return;
        }
        int intrinsicWidth = this.i;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.h.getIntrinsicWidth();
        }
        int textWidth = (((((i - getTextWidth()) - q.s(this)) - intrinsicWidth) - this.l) - getPaddingStart()) / 2;
        if ((q.p(this) == 1) != (this.o == 4)) {
            textWidth = -textWidth;
        }
        if (this.j != textWidth) {
            this.j = textWidth;
            g(false);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.m;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (e()) {
            l.i.r0(this, this.f2403c.b());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (a()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, p);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, q);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // a.b.p.f, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // a.b.p.f, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // a.b.p.f, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.f892a);
        setChecked(cVar.f2407c);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.f2407c = this.m;
        return cVar;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        h(i, i2);
    }

    @Override // a.b.p.f, android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        h(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (!e()) {
            super.setBackgroundColor(i);
            return;
        }
        b.d.a.a.q.a aVar = this.f2403c;
        if (aVar.b() != null) {
            aVar.b().setTint(i);
        }
    }

    @Override // a.b.p.f, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (e()) {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            b.d.a.a.q.a aVar = this.f2403c;
            aVar.o = true;
            aVar.f2044a.setSupportBackgroundTintList(aVar.j);
            aVar.f2044a.setSupportBackgroundTintMode(aVar.i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // a.b.p.f, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? a.b.l.a.a.b(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z) {
        if (e()) {
            this.f2403c.q = z;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (a() && isEnabled() && this.m != z) {
            this.m = z;
            refreshDrawableState();
            if (this.n) {
                return;
            }
            this.n = true;
            Iterator<a> it = this.f2404d.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.m);
            }
            this.n = false;
        }
    }

    public void setCornerRadius(int i) {
        if (e()) {
            b.d.a.a.q.a aVar = this.f2403c;
            if (aVar.p && aVar.g == i) {
                return;
            }
            aVar.g = i;
            aVar.p = true;
            aVar.e(aVar.f2045b.e(i));
        }
    }

    public void setCornerRadiusResource(int i) {
        if (e()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        if (e()) {
            g gVarB = this.f2403c.b();
            g.b bVar = gVarB.f1828a;
            if (bVar.o != f2) {
                bVar.o = f2;
                gVarB.x();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.h != drawable) {
            this.h = drawable;
            g(true);
            h(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.o != i) {
            this.o = i;
            h(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.l != i) {
            this.l = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? a.b.l.a.a.b(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.i != i) {
            this.i = i;
            g(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.g != colorStateList) {
            this.g = colorStateList;
            g(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f2406f != mode) {
            this.f2406f = mode;
            g(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(a.b.l.a.a.a(getContext(), i));
    }

    public void setInsetBottom(int i) {
        b.d.a.a.q.a aVar = this.f2403c;
        aVar.f(aVar.f2048e, i);
    }

    public void setInsetTop(int i) {
        b.d.a.a.q.a aVar = this.f2403c;
        aVar.f(i, aVar.f2049f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(b bVar) {
        this.f2405e = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        b bVar = this.f2405e;
        if (bVar != null) {
            MaterialButtonToggleGroup.this.invalidate();
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (e()) {
            b.d.a.a.q.a aVar = this.f2403c;
            if (aVar.l != colorStateList) {
                aVar.l = colorStateList;
                if (aVar.f2044a.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) aVar.f2044a.getBackground()).setColor(b.d.a.a.c0.a.a(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i) {
        if (e()) {
            setRippleColor(a.b.l.a.a.a(getContext(), i));
        }
    }

    @Override // b.d.a.a.e0.n
    public void setShapeAppearanceModel(b.d.a.a.e0.j jVar) {
        if (!e()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f2403c.e(jVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (e()) {
            b.d.a.a.q.a aVar = this.f2403c;
            aVar.n = z;
            aVar.h();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (e()) {
            b.d.a.a.q.a aVar = this.f2403c;
            if (aVar.k != colorStateList) {
                aVar.k = colorStateList;
                aVar.h();
            }
        }
    }

    public void setStrokeColorResource(int i) {
        if (e()) {
            setStrokeColor(a.b.l.a.a.a(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (e()) {
            b.d.a.a.q.a aVar = this.f2403c;
            if (aVar.h != i) {
                aVar.h = i;
                aVar.h();
            }
        }
    }

    public void setStrokeWidthResource(int i) {
        if (e()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // a.b.p.f
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (!e()) {
            super.setSupportBackgroundTintList(colorStateList);
            return;
        }
        b.d.a.a.q.a aVar = this.f2403c;
        if (aVar.j != colorStateList) {
            aVar.j = colorStateList;
            if (aVar.b() != null) {
                aVar.b().setTintList(aVar.j);
            }
        }
    }

    @Override // a.b.p.f
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!e()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        b.d.a.a.q.a aVar = this.f2403c;
        if (aVar.i != mode) {
            aVar.i = mode;
            if (aVar.b() == null || aVar.i == null) {
                return;
            }
            aVar.b().setTintMode(aVar.i);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.m);
    }
}
