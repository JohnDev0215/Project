package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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
import androidx.appcompat.widget.e;
import androidx.core.widget.i;
import androidx.customview.view.AbsSavedState;
import b3.c;
import com.google.android.material.internal.j;
import e3.h;
import e3.n;
import i0.u;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends e implements Checkable, n {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static final int[] f4236u = {R.attr.state_checkable};

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static final int[] f4237v = {R.attr.state_checked};

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static final int f4238w = k.Widget_MaterialComponents_Button;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final com.google.android.material.button.a f4239h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final LinkedHashSet<a> f4240i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private b f4241j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private PorterDuff.Mode f4242k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ColorStateList f4243l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Drawable f4244m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f4245n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f4246o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f4247p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f4248q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f4249r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f4250s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f4251t;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f4252h;

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
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void c(Parcel parcel) {
            this.f4252h = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f4252h ? 1 : 0);
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
        void a(MaterialButton materialButton, boolean z6);
    }

    interface b {
        void a(MaterialButton materialButton, boolean z6);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.materialButtonStyle);
    }

    private boolean c() {
        int i7 = this.f4251t;
        return i7 == 3 || i7 == 4;
    }

    private boolean d() {
        int i7 = this.f4251t;
        return i7 == 1 || i7 == 2;
    }

    private boolean e() {
        int i7 = this.f4251t;
        return i7 == 16 || i7 == 32;
    }

    private boolean f() {
        return u.y(this) == 1;
    }

    private boolean g() {
        com.google.android.material.button.a aVar = this.f4239h;
        return (aVar == null || aVar.o()) ? false : true;
    }

    private String getA11yClassName() {
        return (b() ? CompoundButton.class : Button.class).getName();
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

    private void i() {
        if (d()) {
            i.i(this, this.f4244m, null, null, null);
        } else if (c()) {
            i.i(this, null, null, this.f4244m, null);
        } else if (e()) {
            i.i(this, null, this.f4244m, null, null);
        }
    }

    private void j(boolean z6) {
        Drawable drawable = this.f4244m;
        if (drawable != null) {
            Drawable drawableMutate = a0.a.l(drawable).mutate();
            this.f4244m = drawableMutate;
            a0.a.i(drawableMutate, this.f4243l);
            PorterDuff.Mode mode = this.f4242k;
            if (mode != null) {
                a0.a.j(this.f4244m, mode);
            }
            int intrinsicWidth = this.f4245n;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.f4244m.getIntrinsicWidth();
            }
            int intrinsicHeight = this.f4245n;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.f4244m.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f4244m;
            int i7 = this.f4246o;
            int i8 = this.f4247p;
            drawable2.setBounds(i7, i8, intrinsicWidth + i7, intrinsicHeight + i8);
        }
        if (z6) {
            i();
            return;
        }
        Drawable[] drawableArrA = i.a(this);
        boolean z7 = false;
        Drawable drawable3 = drawableArrA[0];
        Drawable drawable4 = drawableArrA[1];
        Drawable drawable5 = drawableArrA[2];
        if ((d() && drawable3 != this.f4244m) || ((c() && drawable5 != this.f4244m) || (e() && drawable4 != this.f4244m))) {
            z7 = true;
        }
        if (z7) {
            i();
        }
    }

    private void k(int i7, int i8) {
        if (this.f4244m == null || getLayout() == null) {
            return;
        }
        if (!d() && !c()) {
            if (e()) {
                this.f4246o = 0;
                if (this.f4251t == 16) {
                    this.f4247p = 0;
                    j(false);
                    return;
                }
                int intrinsicHeight = this.f4245n;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.f4244m.getIntrinsicHeight();
                }
                int textHeight = (((((i8 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.f4248q) - getPaddingBottom()) / 2;
                if (this.f4247p != textHeight) {
                    this.f4247p = textHeight;
                    j(false);
                    return;
                }
                return;
            }
            return;
        }
        this.f4247p = 0;
        int i9 = this.f4251t;
        if (i9 == 1 || i9 == 3) {
            this.f4246o = 0;
            j(false);
            return;
        }
        int intrinsicWidth = this.f4245n;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.f4244m.getIntrinsicWidth();
        }
        int textWidth = (((((i7 - getTextWidth()) - u.D(this)) - intrinsicWidth) - this.f4248q) - u.E(this)) / 2;
        if (f() != (this.f4251t == 4)) {
            textWidth = -textWidth;
        }
        if (this.f4246o != textWidth) {
            this.f4246o = textWidth;
            j(false);
        }
    }

    public void a(a aVar) {
        this.f4240i.add(aVar);
    }

    public boolean b() {
        com.google.android.material.button.a aVar = this.f4239h;
        return aVar != null && aVar.p();
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
        if (g()) {
            return this.f4239h.b();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f4244m;
    }

    public int getIconGravity() {
        return this.f4251t;
    }

    public int getIconPadding() {
        return this.f4248q;
    }

    public int getIconSize() {
        return this.f4245n;
    }

    public ColorStateList getIconTint() {
        return this.f4243l;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f4242k;
    }

    public int getInsetBottom() {
        return this.f4239h.c();
    }

    public int getInsetTop() {
        return this.f4239h.d();
    }

    public ColorStateList getRippleColor() {
        if (g()) {
            return this.f4239h.h();
        }
        return null;
    }

    public e3.k getShapeAppearanceModel() {
        if (g()) {
            return this.f4239h.i();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (g()) {
            return this.f4239h.j();
        }
        return null;
    }

    public int getStrokeWidth() {
        if (g()) {
            return this.f4239h.k();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.e
    public ColorStateList getSupportBackgroundTintList() {
        return g() ? this.f4239h.l() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.e
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return g() ? this.f4239h.m() : super.getSupportBackgroundTintMode();
    }

    public void h(a aVar) {
        this.f4240i.remove(aVar);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f4249r;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (g()) {
            h.f(this, this.f4239h.f());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (b()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f4236u);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f4237v);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.e, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.e, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(b());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.e, android.widget.TextView, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b());
        setChecked(savedState.f4252h);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4252h = this.f4249r;
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        k(i7, i8);
    }

    @Override // androidx.appcompat.widget.e, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        super.onTextChanged(charSequence, i7, i8, i9);
        k(getMeasuredWidth(), getMeasuredHeight());
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
    public void setBackgroundColor(int i7) {
        if (g()) {
            this.f4239h.r(i7);
        } else {
            super.setBackgroundColor(i7);
        }
    }

    @Override // androidx.appcompat.widget.e, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!g()) {
            super.setBackgroundDrawable(drawable);
        } else {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            this.f4239h.s();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.e, android.view.View
    public void setBackgroundResource(int i7) {
        setBackgroundDrawable(i7 != 0 ? f.a.b(getContext(), i7) : null);
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
        if (g()) {
            this.f4239h.t(z6);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (b() && isEnabled() && this.f4249r != z6) {
            this.f4249r = z6;
            refreshDrawableState();
            if (this.f4250s) {
                return;
            }
            this.f4250s = true;
            Iterator<a> it = this.f4240i.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.f4249r);
            }
            this.f4250s = false;
        }
    }

    public void setCornerRadius(int i7) {
        if (g()) {
            this.f4239h.u(i7);
        }
    }

    public void setCornerRadiusResource(int i7) {
        if (g()) {
            setCornerRadius(getResources().getDimensionPixelSize(i7));
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        if (g()) {
            this.f4239h.f().W(f7);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f4244m != drawable) {
            this.f4244m = drawable;
            j(true);
            k(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i7) {
        if (this.f4251t != i7) {
            this.f4251t = i7;
            k(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i7) {
        if (this.f4248q != i7) {
            this.f4248q = i7;
            setCompoundDrawablePadding(i7);
        }
    }

    public void setIconResource(int i7) {
        setIcon(i7 != 0 ? f.a.b(getContext(), i7) : null);
    }

    public void setIconSize(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.f4245n != i7) {
            this.f4245n = i7;
            j(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f4243l != colorStateList) {
            this.f4243l = colorStateList;
            j(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f4242k != mode) {
            this.f4242k = mode;
            j(false);
        }
    }

    public void setIconTintResource(int i7) {
        setIconTint(f.a.a(getContext(), i7));
    }

    public void setInsetBottom(int i7) {
        this.f4239h.v(i7);
    }

    public void setInsetTop(int i7) {
        this.f4239h.w(i7);
    }

    void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    void setOnPressedChangeListenerInternal(b bVar) {
        this.f4241j = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        b bVar = this.f4241j;
        if (bVar != null) {
            bVar.a(this, z6);
        }
        super.setPressed(z6);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (g()) {
            this.f4239h.x(colorStateList);
        }
    }

    public void setRippleColorResource(int i7) {
        if (g()) {
            setRippleColor(f.a.a(getContext(), i7));
        }
    }

    @Override // e3.n
    public void setShapeAppearanceModel(e3.k kVar) {
        if (!g()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f4239h.y(kVar);
    }

    void setShouldDrawSurfaceColorStroke(boolean z6) {
        if (g()) {
            this.f4239h.z(z6);
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (g()) {
            this.f4239h.A(colorStateList);
        }
    }

    public void setStrokeColorResource(int i7) {
        if (g()) {
            setStrokeColor(f.a.a(getContext(), i7));
        }
    }

    public void setStrokeWidth(int i7) {
        if (g()) {
            this.f4239h.B(i7);
        }
    }

    public void setStrokeWidthResource(int i7) {
        if (g()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i7));
        }
    }

    @Override // androidx.appcompat.widget.e
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (g()) {
            this.f4239h.C(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.e
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (g()) {
            this.f4239h.D(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f4249r);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i7) {
        int i8 = f4238w;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        this.f4240i = new LinkedHashSet<>();
        this.f4249r = false;
        this.f4250s = false;
        Context context2 = getContext();
        TypedArray typedArrayH = j.h(context2, attributeSet, l.MaterialButton, i7, i8, new int[0]);
        this.f4248q = typedArrayH.getDimensionPixelSize(l.MaterialButton_iconPadding, 0);
        this.f4242k = com.google.android.material.internal.l.e(typedArrayH.getInt(l.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f4243l = c.a(getContext(), typedArrayH, l.MaterialButton_iconTint);
        this.f4244m = c.d(getContext(), typedArrayH, l.MaterialButton_icon);
        this.f4251t = typedArrayH.getInteger(l.MaterialButton_iconGravity, 1);
        this.f4245n = typedArrayH.getDimensionPixelSize(l.MaterialButton_iconSize, 0);
        com.google.android.material.button.a aVar = new com.google.android.material.button.a(this, e3.k.e(context2, attributeSet, i7, i8).m());
        this.f4239h = aVar;
        aVar.q(typedArrayH);
        typedArrayH.recycle();
        setCompoundDrawablePadding(this.f4248q);
        j(this.f4244m != null);
    }
}
