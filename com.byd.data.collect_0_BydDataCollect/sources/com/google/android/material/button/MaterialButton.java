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
import androidx.core.widget.i;
import androidx.customview.view.AbsSavedState;
import b.g.k.t;
import com.google.android.material.internal.l;
import com.google.android.material.internal.q;
import d.b.a.a.k;
import d.b.a.a.u.c;
import d.b.a.a.x.m;
import d.b.a.a.x.p;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f3823d = {R.attr.state_checkable};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int[] f3824e = {R.attr.state_checked};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f3825f = k.Widget_MaterialComponents_Button;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final com.google.android.material.button.a f3826g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final LinkedHashSet<a> f3827h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private b f3828i;
    private PorterDuff.Mode j;
    private ColorStateList k;
    private Drawable l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f3829d;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

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

        private void c(Parcel parcel) {
            this.f3829d = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f3829d ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            c(parcel);
        }
    }

    public interface a {
        void a(MaterialButton materialButton, boolean z);
    }

    interface b {
        void a(MaterialButton materialButton, boolean z);
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private boolean c() {
        return t.z(this) == 1;
    }

    private boolean d() {
        com.google.android.material.button.a aVar = this.f3826g;
        return (aVar == null || aVar.m()) ? false : true;
    }

    private void f(boolean z) {
        if (z) {
            i.l(this, this.l, null, null, null);
        } else {
            i.l(this, null, null, this.l, null);
        }
    }

    private void g(boolean z) {
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

    private String getA11yClassName() {
        return (b() ? CompoundButton.class : Button.class).getName();
    }

    private void h() {
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

    public void a(a aVar) {
        this.f3827h.add(aVar);
    }

    public boolean b() {
        com.google.android.material.button.a aVar = this.f3826g;
        return aVar != null && aVar.n();
    }

    public void e(a aVar) {
        this.f3827h.remove(aVar);
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
            return this.f3826g.b();
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
            return this.f3826g.f();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        if (d()) {
            return this.f3826g.g();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (d()) {
            return this.f3826g.h();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (d()) {
            return this.f3826g.i();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        return d() ? this.f3826g.j() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return d() ? this.f3826g.k() : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.p;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (d()) {
            d.b.a.a.x.i.f(this, this.f3826g.d());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (b()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f3823d);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f3824e);
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
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        com.google.android.material.button.a aVar;
        super.onLayout(z, i2, i3, i4, i5);
        if (Build.VERSION.SDK_INT != 21 || (aVar = this.f3826g) == null) {
            return;
        }
        aVar.B(i5 - i3, i4 - i2);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
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
        super.onRestoreInstanceState(savedState.a());
        setChecked(savedState.f3829d);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3829d = this.p;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
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
            this.f3826g.p(i2);
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
            this.f3826g.q();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i2) {
        setBackgroundDrawable(i2 != 0 ? b.a.k.a.a.d(getContext(), i2) : null);
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
            this.f3826g.r(z);
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
            Iterator<a> it = this.f3827h.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.p);
            }
            this.q = false;
        }
    }

    public void setCornerRadius(int i2) {
        if (d()) {
            this.f3826g.s(i2);
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
            this.f3826g.d().W(f2);
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
        setIcon(i2 != 0 ? b.a.k.a.a.d(getContext(), i2) : null);
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
        setIconTint(b.a.k.a.a.c(getContext(), i2));
    }

    void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    void setOnPressedChangeListenerInternal(b bVar) {
        this.f3828i = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        b bVar = this.f3828i;
        if (bVar != null) {
            bVar.a(this, z);
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (d()) {
            this.f3826g.t(colorStateList);
        }
    }

    public void setRippleColorResource(int i2) {
        if (d()) {
            setRippleColor(b.a.k.a.a.c(getContext(), i2));
        }
    }

    @Override // d.b.a.a.x.p
    public void setShapeAppearanceModel(m mVar) {
        if (!d()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f3826g.u(mVar);
    }

    void setShouldDrawSurfaceColorStroke(boolean z) {
        if (d()) {
            this.f3826g.v(z);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (d()) {
            this.f3826g.w(colorStateList);
        }
    }

    public void setStrokeColorResource(int i2) {
        if (d()) {
            setStrokeColor(b.a.k.a.a.c(getContext(), i2));
        }
    }

    public void setStrokeWidth(int i2) {
        if (d()) {
            this.f3826g.x(i2);
        }
    }

    public void setStrokeWidthResource(int i2) {
        if (d()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (d()) {
            this.f3826g.y(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, b.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (d()) {
            this.f3826g.z(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.p);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f3825f;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f3827h = new LinkedHashSet<>();
        this.p = false;
        this.q = false;
        Context context2 = getContext();
        TypedArray typedArrayH = l.h(context2, attributeSet, d.b.a.a.l.MaterialButton, i2, i3, new int[0]);
        this.o = typedArrayH.getDimensionPixelSize(d.b.a.a.l.MaterialButton_iconPadding, 0);
        this.j = q.i(typedArrayH.getInt(d.b.a.a.l.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(getContext(), typedArrayH, d.b.a.a.l.MaterialButton_iconTint);
        this.l = c.d(getContext(), typedArrayH, d.b.a.a.l.MaterialButton_icon);
        this.r = typedArrayH.getInteger(d.b.a.a.l.MaterialButton_iconGravity, 1);
        this.m = typedArrayH.getDimensionPixelSize(d.b.a.a.l.MaterialButton_iconSize, 0);
        com.google.android.material.button.a aVar = new com.google.android.material.button.a(this, m.e(context2, attributeSet, i2, i3).m());
        this.f3826g = aVar;
        aVar.o(typedArrayH);
        typedArrayH.recycle();
        setCompoundDrawablePadding(this.o);
        g(this.l != null);
    }
}
