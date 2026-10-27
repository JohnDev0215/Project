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
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.k;
import java.util.Iterator;
import java.util.LinkedHashSet;
import k2.c;
import l0.t;
import n0.i;
import n2.m;
import n2.p;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f2133d = {R.attr.state_checkable};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f2134e = {R.attr.state_checked};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f2135f = R$style.Widget_MaterialComponents_Button;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final x1.a f2136g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final LinkedHashSet<a> f2137h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f2138i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public PorterDuff.Mode f2139j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f2140k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f2141l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2142m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2143n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2144o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2145p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2146q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2147r;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2148d;

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
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void g(Parcel parcel) {
            this.f2148d = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f2148d ? 1 : 0);
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
        void a(MaterialButton materialButton, boolean z3);
    }

    public interface b {
        void a(MaterialButton materialButton, boolean z3);
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private String getA11yClassName() {
        return (b() ? CompoundButton.class : Button.class).getName();
    }

    public void a(a aVar) {
        this.f2137h.add(aVar);
    }

    public boolean b() {
        x1.a aVar = this.f2136g;
        return aVar != null && aVar.n();
    }

    public final boolean c() {
        return t.z(this) == 1;
    }

    public final boolean d() {
        x1.a aVar = this.f2136g;
        return (aVar == null || aVar.m()) ? false : true;
    }

    public void e(a aVar) {
        this.f2137h.remove(aVar);
    }

    public final void f(boolean z3) {
        if (z3) {
            i.l(this, this.f2141l, null, null, null);
        } else {
            i.l(this, null, null, this.f2141l, null);
        }
    }

    public final void g(boolean z3) {
        Drawable drawable = this.f2141l;
        boolean z4 = false;
        if (drawable != null) {
            Drawable drawableMutate = e0.a.r(drawable).mutate();
            this.f2141l = drawableMutate;
            e0.a.o(drawableMutate, this.f2140k);
            PorterDuff.Mode mode = this.f2139j;
            if (mode != null) {
                e0.a.p(this.f2141l, mode);
            }
            int intrinsicWidth = this.f2142m;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.f2141l.getIntrinsicWidth();
            }
            int intrinsicHeight = this.f2142m;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.f2141l.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f2141l;
            int i3 = this.f2143n;
            drawable2.setBounds(i3, 0, intrinsicWidth + i3, intrinsicHeight);
        }
        int i4 = this.f2147r;
        boolean z5 = i4 == 1 || i4 == 2;
        if (z3) {
            f(z5);
            return;
        }
        Drawable[] drawableArrA = i.a(this);
        Drawable drawable3 = drawableArrA[0];
        Drawable drawable4 = drawableArrA[2];
        if ((z5 && drawable3 != this.f2141l) || (!z5 && drawable4 != this.f2141l)) {
            z4 = true;
        }
        if (z4) {
            f(z5);
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
            return this.f2136g.b();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f2141l;
    }

    public int getIconGravity() {
        return this.f2147r;
    }

    public int getIconPadding() {
        return this.f2144o;
    }

    public int getIconSize() {
        return this.f2142m;
    }

    public ColorStateList getIconTint() {
        return this.f2140k;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f2139j;
    }

    public ColorStateList getRippleColor() {
        if (d()) {
            return this.f2136g.f();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        if (d()) {
            return this.f2136g.g();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (d()) {
            return this.f2136g.h();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (d()) {
            return this.f2136g.i();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, l0.s
    public ColorStateList getSupportBackgroundTintList() {
        return d() ? this.f2136g.j() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return d() ? this.f2136g.k() : super.getSupportBackgroundTintMode();
    }

    public final void h() {
        if (this.f2141l == null || getLayout() == null) {
            return;
        }
        int i3 = this.f2147r;
        if (i3 == 1 || i3 == 3) {
            this.f2143n = 0;
            g(false);
            return;
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        int iMin = Math.min((int) paint.measureText(string), getLayout().getEllipsizedWidth());
        int intrinsicWidth = this.f2142m;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.f2141l.getIntrinsicWidth();
        }
        int measuredWidth = (((((getMeasuredWidth() - iMin) - t.D(this)) - intrinsicWidth) - this.f2144o) - t.E(this)) / 2;
        if (c() != (this.f2147r == 4)) {
            measuredWidth = -measuredWidth;
        }
        if (this.f2143n != measuredWidth) {
            this.f2143n = measuredWidth;
            g(false);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2145p;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (d()) {
            n2.i.f(this, this.f2136g.d());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i3) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + 2);
        if (b()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f2133d);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f2134e);
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
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        x1.a aVar;
        super.onLayout(z3, i3, i4, i5, i6);
        if (Build.VERSION.SDK_INT != 21 || (aVar = this.f2136g) == null) {
            return;
        }
        aVar.B(i6 - i4, i5 - i3);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, i4);
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
        setChecked(savedState.f2148d);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2148d = this.f2145p;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        super.onTextChanged(charSequence, i3, i4, i5);
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
    public void setBackgroundColor(int i3) {
        if (d()) {
            this.f2136g.p(i3);
        } else {
            super.setBackgroundColor(i3);
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
            this.f2136g.q();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i3) {
        setBackgroundDrawable(i3 != 0 ? j.a.d(getContext(), i3) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z3) {
        if (d()) {
            this.f2136g.r(z3);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z3) {
        if (b() && isEnabled() && this.f2145p != z3) {
            this.f2145p = z3;
            refreshDrawableState();
            if (this.f2146q) {
                return;
            }
            this.f2146q = true;
            Iterator<a> it = this.f2137h.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.f2145p);
            }
            this.f2146q = false;
        }
    }

    public void setCornerRadius(int i3) {
        if (d()) {
            this.f2136g.s(i3);
        }
    }

    public void setCornerRadiusResource(int i3) {
        if (d()) {
            setCornerRadius(getResources().getDimensionPixelSize(i3));
        }
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        if (d()) {
            this.f2136g.d().W(f3);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f2141l != drawable) {
            this.f2141l = drawable;
            g(true);
        }
    }

    public void setIconGravity(int i3) {
        if (this.f2147r != i3) {
            this.f2147r = i3;
            h();
        }
    }

    public void setIconPadding(int i3) {
        if (this.f2144o != i3) {
            this.f2144o = i3;
            setCompoundDrawablePadding(i3);
        }
    }

    public void setIconResource(int i3) {
        setIcon(i3 != 0 ? j.a.d(getContext(), i3) : null);
    }

    public void setIconSize(int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.f2142m != i3) {
            this.f2142m = i3;
            g(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f2140k != colorStateList) {
            this.f2140k = colorStateList;
            g(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f2139j != mode) {
            this.f2139j = mode;
            g(false);
        }
    }

    public void setIconTintResource(int i3) {
        setIconTint(j.a.c(getContext(), i3));
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(b bVar) {
        this.f2138i = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z3) {
        b bVar = this.f2138i;
        if (bVar != null) {
            bVar.a(this, z3);
        }
        super.setPressed(z3);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (d()) {
            this.f2136g.t(colorStateList);
        }
    }

    public void setRippleColorResource(int i3) {
        if (d()) {
            setRippleColor(j.a.c(getContext(), i3));
        }
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        if (!d()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f2136g.u(mVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z3) {
        if (d()) {
            this.f2136g.v(z3);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (d()) {
            this.f2136g.w(colorStateList);
        }
    }

    public void setStrokeColorResource(int i3) {
        if (d()) {
            setStrokeColor(j.a.c(getContext(), i3));
        }
    }

    public void setStrokeWidth(int i3) {
        if (d()) {
            this.f2136g.x(i3);
        }
    }

    public void setStrokeWidthResource(int i3) {
        if (d()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i3));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (d()) {
            this.f2136g.y(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (d()) {
            this.f2136g.z(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f2145p);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2135f;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2137h = new LinkedHashSet<>();
        this.f2145p = false;
        this.f2146q = false;
        Context context2 = getContext();
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.MaterialButton, i3, i4, new int[0]);
        this.f2144o = typedArrayH.getDimensionPixelSize(R$styleable.MaterialButton_iconPadding, 0);
        this.f2139j = i2.p.i(typedArrayH.getInt(R$styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f2140k = c.a(getContext(), typedArrayH, R$styleable.MaterialButton_iconTint);
        this.f2141l = c.d(getContext(), typedArrayH, R$styleable.MaterialButton_icon);
        this.f2147r = typedArrayH.getInteger(R$styleable.MaterialButton_iconGravity, 1);
        this.f2142m = typedArrayH.getDimensionPixelSize(R$styleable.MaterialButton_iconSize, 0);
        x1.a aVar = new x1.a(this, m.e(context2, attributeSet, i3, i4).m());
        this.f2136g = aVar;
        aVar.o(typedArrayH);
        typedArrayH.recycle();
        setCompoundDrawablePadding(this.f2144o);
        g(this.f2141l != null);
    }
}
