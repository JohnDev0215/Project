package com.google.android.material.button;

import a.g.k.t;
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
import androidx.appcompat.widget.f;
import b.a.a.a.b0.h;
import b.a.a.a.b0.n;
import b.a.a.a.k;
import b.a.a.a.l;
import com.google.android.material.internal.i;
import com.google.android.material.internal.j;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends f implements Checkable, n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f3022d = {R.attr.state_checkable};
    private static final int[] e = {R.attr.state_checked};
    private static final int f = k.m;
    private final com.google.android.material.button.a g;
    private final LinkedHashSet<a> h;
    private b i;
    private PorterDuff.Mode j;
    private ColorStateList k;
    private Drawable l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;

    public interface a {
        void a(MaterialButton materialButton, boolean z);
    }

    interface b {
        void a(MaterialButton materialButton, boolean z);
    }

    static class c extends a.i.a.a {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3023d;

        static class a implements Parcelable.ClassLoaderCreator<c> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new c(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public c[] newArray(int i) {
                return new c[i];
            }
        }

        public c(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                c.class.getClassLoader();
            }
            v(parcel);
        }

        public c(Parcelable parcelable) {
            super(parcelable);
        }

        private void v(Parcel parcel) {
            this.f3023d = parcel.readInt() == 1;
        }

        @Override // a.i.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3023d ? 1 : 0);
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.a.a.a.b.q);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = f;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        this.h = new LinkedHashSet<>();
        this.p = false;
        this.q = false;
        Context context2 = getContext();
        TypedArray typedArrayH = i.h(context2, attributeSet, l.x1, i, i2, new int[0]);
        this.o = typedArrayH.getDimensionPixelSize(l.K1, 0);
        this.j = j.e(typedArrayH.getInt(l.N1, -1), PorterDuff.Mode.SRC_IN);
        this.k = b.a.a.a.y.c.a(getContext(), typedArrayH, l.M1);
        this.l = b.a.a.a.y.c.d(getContext(), typedArrayH, l.I1);
        this.r = typedArrayH.getInteger(l.J1, 1);
        this.m = typedArrayH.getDimensionPixelSize(l.L1, 0);
        com.google.android.material.button.a aVar = new com.google.android.material.button.a(this, b.a.a.a.b0.k.e(context2, attributeSet, i, i2).m());
        this.g = aVar;
        aVar.o(typedArrayH);
        typedArrayH.recycle();
        setCompoundDrawablePadding(this.o);
        e(this.l != null);
    }

    private boolean b() {
        return t.y(this) == 1;
    }

    private boolean c() {
        com.google.android.material.button.a aVar = this.g;
        return (aVar == null || aVar.m()) ? false : true;
    }

    private void d(boolean z) {
        if (z) {
            androidx.core.widget.i.l(this, this.l, null, null, null);
        } else {
            androidx.core.widget.i.l(this, null, null, this.l, null);
        }
    }

    private void e(boolean z) {
        Drawable drawable = this.l;
        boolean z2 = false;
        if (drawable != null) {
            Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawable).mutate();
            this.l = drawableMutate;
            androidx.core.graphics.drawable.a.o(drawableMutate, this.k);
            PorterDuff.Mode mode = this.j;
            if (mode != null) {
                androidx.core.graphics.drawable.a.p(this.l, mode);
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
            int i = this.n;
            drawable2.setBounds(i, 0, intrinsicWidth + i, intrinsicHeight);
        }
        int i2 = this.r;
        boolean z3 = i2 == 1 || i2 == 2;
        if (z) {
            d(z3);
            return;
        }
        Drawable[] drawableArrA = androidx.core.widget.i.a(this);
        Drawable drawable3 = drawableArrA[0];
        Drawable drawable4 = drawableArrA[2];
        if ((z3 && drawable3 != this.l) || (!z3 && drawable4 != this.l)) {
            z2 = true;
        }
        if (z2) {
            d(z3);
        }
    }

    private void f() {
        if (this.l == null || getLayout() == null) {
            return;
        }
        int i = this.r;
        if (i == 1 || i == 3) {
            this.n = 0;
            e(false);
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
        int measuredWidth = (((((getMeasuredWidth() - iMin) - t.C(this)) - intrinsicWidth) - this.o) - t.D(this)) / 2;
        if (b() != (this.r == 4)) {
            measuredWidth = -measuredWidth;
        }
        if (this.n != measuredWidth) {
            this.n = measuredWidth;
            e(false);
        }
    }

    private String getA11yClassName() {
        return (a() ? CompoundButton.class : Button.class).getName();
    }

    public boolean a() {
        com.google.android.material.button.a aVar = this.g;
        return aVar != null && aVar.n();
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
        if (c()) {
            return this.g.b();
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
        if (c()) {
            return this.g.f();
        }
        return null;
    }

    public b.a.a.a.b0.k getShapeAppearanceModel() {
        if (c()) {
            return this.g.g();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (c()) {
            return this.g.h();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (c()) {
            return this.g.i();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.f, a.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        return c() ? this.g.j() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.f, a.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return c() ? this.g.k() : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.p;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (c()) {
            h.f(this, this.g.d());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (a()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f3022d);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, e);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.f, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.f, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.f, android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        com.google.android.material.button.a aVar;
        super.onLayout(z, i, i2, i3, i4);
        if (Build.VERSION.SDK_INT != 21 || (aVar = this.g) == null) {
            return;
        }
        aVar.B(i4 - i2, i3 - i);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        f();
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.u());
        setChecked(cVar.f3023d);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.f3023d = this.p;
        return cVar;
    }

    @Override // androidx.appcompat.widget.f, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        f();
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
        if (c()) {
            this.g.p(i);
        } else {
            super.setBackgroundColor(i);
        }
    }

    @Override // androidx.appcompat.widget.f, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (c()) {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            } else {
                Log.w("MaterialButton", "Do not set the background; MaterialButton manages its own background drawable.");
                this.g.q();
            }
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.f, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? a.a.k.a.a.d(getContext(), i) : null);
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
        if (c()) {
            this.g.r(z);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (a() && isEnabled() && this.p != z) {
            this.p = z;
            refreshDrawableState();
            if (this.q) {
                return;
            }
            this.q = true;
            Iterator<a> it = this.h.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.p);
            }
            this.q = false;
        }
    }

    public void setCornerRadius(int i) {
        if (c()) {
            this.g.s(i);
        }
    }

    public void setCornerRadiusResource(int i) {
        if (c()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        if (c()) {
            this.g.d().V(f2);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.l != drawable) {
            this.l = drawable;
            e(true);
        }
    }

    public void setIconGravity(int i) {
        if (this.r != i) {
            this.r = i;
            f();
        }
    }

    public void setIconPadding(int i) {
        if (this.o != i) {
            this.o = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? a.a.k.a.a.d(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.m != i) {
            this.m = i;
            e(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            e(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.j != mode) {
            this.j = mode;
            e(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(a.a.k.a.a.c(getContext(), i));
    }

    void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    void setOnPressedChangeListenerInternal(b bVar) {
        this.i = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        b bVar = this.i;
        if (bVar != null) {
            bVar.a(this, z);
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (c()) {
            this.g.t(colorStateList);
        }
    }

    public void setRippleColorResource(int i) {
        if (c()) {
            setRippleColor(a.a.k.a.a.c(getContext(), i));
        }
    }

    @Override // b.a.a.a.b0.n
    public void setShapeAppearanceModel(b.a.a.a.b0.k kVar) {
        if (!c()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.g.u(kVar);
    }

    void setShouldDrawSurfaceColorStroke(boolean z) {
        if (c()) {
            this.g.v(z);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (c()) {
            this.g.w(colorStateList);
        }
    }

    public void setStrokeColorResource(int i) {
        if (c()) {
            setStrokeColor(a.a.k.a.a.c(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (c()) {
            this.g.x(i);
        }
    }

    public void setStrokeWidthResource(int i) {
        if (c()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // androidx.appcompat.widget.f, a.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (c()) {
            this.g.y(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.f, a.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (c()) {
            this.g.z(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.p);
    }
}
