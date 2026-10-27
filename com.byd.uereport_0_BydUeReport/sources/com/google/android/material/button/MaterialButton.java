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
import b6.c;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import e6.m;
import e6.p;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o0.t;
import r0.i;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialButton extends AppCompatButton implements Checkable, p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f3716f = {R.attr.state_checkable};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f3717g = {R.attr.state_checked};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f3718h = R$style.Widget_MaterialComponents_Button;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o5.a f3719i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final LinkedHashSet<a> f3720j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b f3721k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public PorterDuff.Mode f3722l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f3723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f3724n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3725o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3726p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3727q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3728r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3729s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3730t;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f3731f;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void g(Parcel parcel) {
            this.f3731f = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f3731f ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            g(parcel);
        }
    }

    public interface a {
        void a(MaterialButton materialButton, boolean z7);
    }

    public interface b {
        void a(MaterialButton materialButton, boolean z7);
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private String getA11yClassName() {
        return (b() ? CompoundButton.class : Button.class).getName();
    }

    public void a(a aVar) {
        this.f3720j.add(aVar);
    }

    public boolean b() {
        o5.a aVar = this.f3719i;
        return aVar != null && aVar.n();
    }

    public final boolean c() {
        return t.A(this) == 1;
    }

    public final boolean d() {
        o5.a aVar = this.f3719i;
        return (aVar == null || aVar.m()) ? false : true;
    }

    public void e(a aVar) {
        this.f3720j.remove(aVar);
    }

    public final void f(boolean z7) {
        if (z7) {
            i.l(this, this.f3724n, null, null, null);
        } else {
            i.l(this, null, null, this.f3724n, null);
        }
    }

    public final void g(boolean z7) {
        Drawable drawable = this.f3724n;
        boolean z8 = false;
        if (drawable != null) {
            Drawable drawableMutate = h0.a.r(drawable).mutate();
            this.f3724n = drawableMutate;
            h0.a.o(drawableMutate, this.f3723m);
            PorterDuff.Mode mode = this.f3722l;
            if (mode != null) {
                h0.a.p(this.f3724n, mode);
            }
            int intrinsicWidth = this.f3725o;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.f3724n.getIntrinsicWidth();
            }
            int intrinsicHeight = this.f3725o;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.f3724n.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f3724n;
            int i8 = this.f3726p;
            drawable2.setBounds(i8, 0, intrinsicWidth + i8, intrinsicHeight);
        }
        int i9 = this.f3730t;
        boolean z9 = i9 == 1 || i9 == 2;
        if (z7) {
            f(z9);
            return;
        }
        Drawable[] drawableArrA = i.a(this);
        Drawable drawable3 = drawableArrA[0];
        Drawable drawable4 = drawableArrA[2];
        if ((z9 && drawable3 != this.f3724n) || (!z9 && drawable4 != this.f3724n)) {
            z8 = true;
        }
        if (z8) {
            f(z9);
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
            return this.f3719i.b();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f3724n;
    }

    public int getIconGravity() {
        return this.f3730t;
    }

    public int getIconPadding() {
        return this.f3727q;
    }

    public int getIconSize() {
        return this.f3725o;
    }

    public ColorStateList getIconTint() {
        return this.f3723m;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f3722l;
    }

    public ColorStateList getRippleColor() {
        if (d()) {
            return this.f3719i.f();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        if (d()) {
            return this.f3719i.g();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (d()) {
            return this.f3719i.h();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (d()) {
            return this.f3719i.i();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, o0.s
    public ColorStateList getSupportBackgroundTintList() {
        return d() ? this.f3719i.j() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return d() ? this.f3719i.k() : super.getSupportBackgroundTintMode();
    }

    public final void h() {
        if (this.f3724n == null || getLayout() == null) {
            return;
        }
        int i8 = this.f3730t;
        if (i8 == 1 || i8 == 3) {
            this.f3726p = 0;
            g(false);
            return;
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        int iMin = Math.min((int) paint.measureText(string), getLayout().getEllipsizedWidth());
        int intrinsicWidth = this.f3725o;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.f3724n.getIntrinsicWidth();
        }
        int measuredWidth = (((((getMeasuredWidth() - iMin) - t.E(this)) - intrinsicWidth) - this.f3727q) - t.F(this)) / 2;
        if (c() != (this.f3730t == 4)) {
            measuredWidth = -measuredWidth;
        }
        if (this.f3726p != measuredWidth) {
            this.f3726p = measuredWidth;
            g(false);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f3728r;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (d()) {
            e6.i.f(this, this.f3719i.d());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i8) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + 2);
        if (b()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f3716f);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f3717g);
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
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        o5.a aVar;
        super.onLayout(z7, i8, i9, i10, i11);
        if (Build.VERSION.SDK_INT != 21 || (aVar = this.f3719i) == null) {
            return;
        }
        aVar.B(i11 - i9, i10 - i8);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        h();
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        setChecked(savedState.f3731f);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3731f = this.f3728r;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        super.onTextChanged(charSequence, i8, i9, i10);
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
    public void setBackgroundColor(int i8) {
        if (d()) {
            this.f3719i.p(i8);
        } else {
            super.setBackgroundColor(i8);
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
            this.f3719i.q();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i8) {
        setBackgroundDrawable(i8 != 0 ? m.a.d(getContext(), i8) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z7) {
        if (d()) {
            this.f3719i.r(z7);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z7) {
        if (b() && isEnabled() && this.f3728r != z7) {
            this.f3728r = z7;
            refreshDrawableState();
            if (this.f3729s) {
                return;
            }
            this.f3729s = true;
            Iterator<a> it = this.f3720j.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.f3728r);
            }
            this.f3729s = false;
        }
    }

    public void setCornerRadius(int i8) {
        if (d()) {
            this.f3719i.s(i8);
        }
    }

    public void setCornerRadiusResource(int i8) {
        if (d()) {
            setCornerRadius(getResources().getDimensionPixelSize(i8));
        }
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        if (d()) {
            this.f3719i.d().W(f8);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f3724n != drawable) {
            this.f3724n = drawable;
            g(true);
        }
    }

    public void setIconGravity(int i8) {
        if (this.f3730t != i8) {
            this.f3730t = i8;
            h();
        }
    }

    public void setIconPadding(int i8) {
        if (this.f3727q != i8) {
            this.f3727q = i8;
            setCompoundDrawablePadding(i8);
        }
    }

    public void setIconResource(int i8) {
        setIcon(i8 != 0 ? m.a.d(getContext(), i8) : null);
    }

    public void setIconSize(int i8) {
        if (i8 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.f3725o != i8) {
            this.f3725o = i8;
            g(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f3723m != colorStateList) {
            this.f3723m = colorStateList;
            g(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f3722l != mode) {
            this.f3722l = mode;
            g(false);
        }
    }

    public void setIconTintResource(int i8) {
        setIconTint(m.a.c(getContext(), i8));
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(b bVar) {
        this.f3721k = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z7) {
        b bVar = this.f3721k;
        if (bVar != null) {
            bVar.a(this, z7);
        }
        super.setPressed(z7);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (d()) {
            this.f3719i.t(colorStateList);
        }
    }

    public void setRippleColorResource(int i8) {
        if (d()) {
            setRippleColor(m.a.c(getContext(), i8));
        }
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        if (!d()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f3719i.u(mVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z7) {
        if (d()) {
            this.f3719i.v(z7);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (d()) {
            this.f3719i.w(colorStateList);
        }
    }

    public void setStrokeColorResource(int i8) {
        if (d()) {
            setStrokeColor(m.a.c(getContext(), i8));
        }
    }

    public void setStrokeWidth(int i8) {
        if (d()) {
            this.f3719i.x(i8);
        }
    }

    public void setStrokeWidthResource(int i8) {
        if (d()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i8));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (d()) {
            this.f3719i.y(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (d()) {
            this.f3719i.z(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f3728r);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3718h;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3720j = new LinkedHashSet<>();
        this.f3728r = false;
        this.f3729s = false;
        Context context2 = getContext();
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.MaterialButton, i8, i9, new int[0]);
        this.f3727q = typedArrayH.getDimensionPixelSize(R$styleable.MaterialButton_iconPadding, 0);
        this.f3722l = z5.p.i(typedArrayH.getInt(R$styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f3723m = c.a(getContext(), typedArrayH, R$styleable.MaterialButton_iconTint);
        this.f3724n = c.d(getContext(), typedArrayH, R$styleable.MaterialButton_icon);
        this.f3730t = typedArrayH.getInteger(R$styleable.MaterialButton_iconGravity, 1);
        this.f3725o = typedArrayH.getDimensionPixelSize(R$styleable.MaterialButton_iconSize, 0);
        o5.a aVar = new o5.a(this, m.e(context2, attributeSet, i8, i9).m());
        this.f3719i = aVar;
        aVar.o(typedArrayH);
        typedArrayH.recycle();
        setCompoundDrawablePadding(this.f3727q);
        g(this.f3724n != null);
    }
}
