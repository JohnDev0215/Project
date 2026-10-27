package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import androidx.appcompat.widget.AppCompatButton;
import androidx.customview.view.AbsSavedState;
import b.h.m.t;
import b.h.n.i;
import c.b.a.a.f0.c;
import c.b.a.a.i0.m;
import c.b.a.a.i0.p;
import c.b.a.a.k;
import c.b.a.a.l;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f4608d = {R.attr.state_checkable};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f4609e = {R.attr.state_checked};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f4610f = k.Widget_MaterialComponents_Button;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c.b.a.a.r.a f4611g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final LinkedHashSet<a> f4612h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f4613i;
    public PorterDuff.Mode j;
    public ColorStateList k;
    public Drawable l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public boolean q;
    public int r;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f4614d;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void k(Parcel parcel) {
            this.f4614d = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f4614d ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            k(parcel);
        }
    }

    public interface a {
        void a(MaterialButton materialButton, boolean z);
    }

    public interface b {
        void a(MaterialButton materialButton, boolean z);
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private String getA11yClassName() {
        return (b() ? CompoundButton.class : Button.class).getName();
    }

    public void a(a aVar) {
        this.f4612h.add(aVar);
    }

    public boolean b() {
        c.b.a.a.r.a aVar = this.f4611g;
        return aVar != null && aVar.n();
    }

    public final boolean c() {
        return t.z(this) == 1;
    }

    public final boolean d() {
        c.b.a.a.r.a aVar = this.f4611g;
        return (aVar == null || aVar.m()) ? false : true;
    }

    public void e(a aVar) {
        this.f4612h.remove(aVar);
    }

    public final void f(boolean z) {
        if (z) {
            i.l(this, this.l, null, null, null);
        } else {
            i.l(this, null, null, this.l, null);
        }
    }

    public final void g(boolean z) {
        Drawable drawable = this.l;
        boolean z2 = false;
        if (drawable != null) {
            Drawable drawableMutate = b.h.f.l.a.r(drawable).mutate();
            this.l = drawableMutate;
            b.h.f.l.a.o(drawableMutate, this.k);
            PorterDuff.Mode mode = this.j;
            if (mode != null) {
                b.h.f.l.a.p(this.l, mode);
            }
            int intrinsicWidth = this.m;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.l.getIntrinsicWidth();
            }
            int intrinsicHeight = this.m;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.l.getIntrinsicHeight();
            }
            Drawable drawable2 = this.l;
            int i2 = this.n;
            drawable2.setBounds(i2, 0, intrinsicWidth + i2, intrinsicHeight);
        }
        int i3 = this.r;
        boolean z3 = i3 == 1 || i3 == 2;
        if (z) {
            f(z3);
            return;
        }
        Drawable[] drawableArrA = i.a(this);
        Drawable drawable3 = drawableArrA[0];
        Drawable drawable4 = drawableArrA[2];
        if ((z3 && drawable3 != this.l) || (!z3 && drawable4 != this.l)) {
            z2 = true;
        }
        if (z2) {
            f(z3);
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
        if (d()) {
            return this.f4611g.b();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.l;
    }

    public int getIconGravity() {
        return this.r;
    }

    public int getIconPadding() {
        return this.o;
    }

    public int getIconSize() {
        return this.m;
    }

    public ColorStateList getIconTint() {
        return this.k;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.j;
    }

    public ColorStateList getRippleColor() {
        if (d()) {
            return this.f4611g.f();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        if (d()) {
            return this.f4611g.g();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (d()) {
            return this.f4611g.h();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (d()) {
            return this.f4611g.i();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.h.m.s
    public ColorStateList getSupportBackgroundTintList() {
        return d() ? this.f4611g.j() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.h.m.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return d() ? this.f4611g.k() : super.getSupportBackgroundTintMode();
    }

    public final void h() {
        if (this.l == null || getLayout() == null) {
            return;
        }
        int i2 = this.r;
        if (i2 == 1 || i2 == 3) {
            this.n = 0;
            g(false);
            return;
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        int iMin = Math.min((int) paint.measureText(string), getLayout().getEllipsizedWidth());
        int intrinsicWidth = this.m;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.l.getIntrinsicWidth();
        }
        int measuredWidth = (((((getMeasuredWidth() - iMin) - t.D(this)) - intrinsicWidth) - this.o) - t.E(this)) / 2;
        if (c() != (this.r == 4)) {
            measuredWidth = -measuredWidth;
        }
        if (this.n != measuredWidth) {
            this.n = measuredWidth;
            g(false);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.p;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (d()) {
            c.b.a.a.i0.i.f(this, this.f4611g.d());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (b()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f4608d);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f4609e);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(b());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        c.b.a.a.r.a aVar;
        super.onLayout(z, i2, i3, i4, i5);
        if (Build.VERSION.SDK_INT != 21 || (aVar = this.f4611g) == null) {
            return;
        }
        aVar.B(i5 - i3, i4 - i2);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        h();
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.j());
        setChecked(savedState.f4614d);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4614d = this.p;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        h();
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
    public void setBackgroundColor(int i2) {
        if (d()) {
            this.f4611g.p(i2);
        } else {
            super.setBackgroundColor(i2);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!d()) {
            super.setBackgroundDrawable(drawable);
        } else {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.w("MaterialButton", "Do not set the background; MaterialButton manages its own background drawable.");
            this.f4611g.q();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i2) {
        setBackgroundDrawable(i2 != 0 ? b.b.l.a.a.d(getContext(), i2) : null);
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
        if (d()) {
            this.f4611g.r(z);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (b() && isEnabled() && this.p != z) {
            this.p = z;
            refreshDrawableState();
            if (this.q) {
                return;
            }
            this.q = true;
            Iterator<a> it = this.f4612h.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.p);
            }
            this.q = false;
        }
    }

    public void setCornerRadius(int i2) {
        if (d()) {
            this.f4611g.s(i2);
        }
    }

    public void setCornerRadiusResource(int i2) {
        if (d()) {
            setCornerRadius(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        if (d()) {
            this.f4611g.d().W(f2);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.l != drawable) {
            this.l = drawable;
            g(true);
        }
    }

    public void setIconGravity(int i2) {
        if (this.r != i2) {
            this.r = i2;
            h();
        }
    }

    public void setIconPadding(int i2) {
        if (this.o != i2) {
            this.o = i2;
            setCompoundDrawablePadding(i2);
        }
    }

    public void setIconResource(int i2) {
        setIcon(i2 != 0 ? b.b.l.a.a.d(getContext(), i2) : null);
    }

    public void setIconSize(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.m != i2) {
            this.m = i2;
            g(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            g(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.j != mode) {
            this.j = mode;
            g(false);
        }
    }

    public void setIconTintResource(int i2) {
        setIconTint(b.b.l.a.a.c(getContext(), i2));
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(b bVar) {
        this.f4613i = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        b bVar = this.f4613i;
        if (bVar != null) {
            bVar.a(this, z);
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (d()) {
            this.f4611g.t(colorStateList);
        }
    }

    public void setRippleColorResource(int i2) {
        if (d()) {
            setRippleColor(b.b.l.a.a.c(getContext(), i2));
        }
    }

    @Override // c.b.a.a.i0.p
    public void setShapeAppearanceModel(m mVar) {
        if (!d()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f4611g.u(mVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (d()) {
            this.f4611g.v(z);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (d()) {
            this.f4611g.w(colorStateList);
        }
    }

    public void setStrokeColorResource(int i2) {
        if (d()) {
            setStrokeColor(b.b.l.a.a.c(getContext(), i2));
        }
    }

    public void setStrokeWidth(int i2) {
        if (d()) {
            this.f4611g.x(i2);
        }
    }

    public void setStrokeWidthResource(int i2) {
        if (d()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.h.m.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (d()) {
            this.f4611g.y(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.h.m.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (d()) {
            this.f4611g.z(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.p);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f4610f;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f4612h = new LinkedHashSet<>();
        this.p = false;
        this.q = false;
        Context context2 = getContext();
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context2, attributeSet, l.MaterialButton, i2, i3, new int[0]);
        this.o = typedArrayH.getDimensionPixelSize(l.MaterialButton_iconPadding, 0);
        this.j = c.b.a.a.d0.p.i(typedArrayH.getInt(l.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(getContext(), typedArrayH, l.MaterialButton_iconTint);
        this.l = c.d(getContext(), typedArrayH, l.MaterialButton_icon);
        this.r = typedArrayH.getInteger(l.MaterialButton_iconGravity, 1);
        this.m = typedArrayH.getDimensionPixelSize(l.MaterialButton_iconSize, 0);
        c.b.a.a.r.a aVar = new c.b.a.a.r.a(this, m.e(context2, attributeSet, i2, i3).m());
        this.f4611g = aVar;
        aVar.o(typedArrayH);
        typedArrayH.recycle();
        setCompoundDrawablePadding(this.o);
        g(this.l != null);
    }
}
