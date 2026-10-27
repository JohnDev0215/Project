package com.google.android.material.button;

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
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bz;
import defpackage.dc1;
import defpackage.dc2;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.yj2;
import defpackage.yn3;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, yj2 {
    public static final int[] u = {R.attr.state_checkable};
    public static final int[] v = {R.attr.state_checked};
    public static final int w = R$style.Widget_MaterialComponents_Button;
    public final dc1 h;
    public final LinkedHashSet<a> i;
    public b j;
    public PorterDuff.Mode k;
    public ColorStateList l;
    public Drawable m;
    public int n;
    public int o;
    public int p;
    public int q;
    public boolean r;
    public boolean s;
    public int t;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean g;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeInt(this.g ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            this.g = parcel.readInt() == 1;
        }
    }

    public interface a {
        void a();
    }

    public interface b {
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private String getA11yClassName() {
        dc1 dc1Var = this.h;
        return (dc1Var != null && dc1Var.q ? CompoundButton.class : Button.class).getName();
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        return textAlignment != 1 ? (textAlignment == 6 || textAlignment == 3) ? Layout.Alignment.ALIGN_OPPOSITE : textAlignment != 4 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER : getGravityTextAlignment();
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & 8388615;
        return gravity != 1 ? (gravity == 5 || gravity == 8388613) ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        int iMax = 0;
        for (int i = 0; i < lineCount; i++) {
            CharSequence charSequenceSubSequence = getText().subSequence(getLayout().getLineStart(i), getLayout().getLineEnd(i));
            TextPaint paint = getPaint();
            String string = charSequenceSubSequence.toString();
            if (getTransformationMethod() != null) {
                string = getTransformationMethod().getTransformation(string, this).toString();
            }
            iMax = Math.max(iMax, Math.min((int) paint.measureText(string), getLayout().getEllipsizedWidth()));
        }
        return iMax;
    }

    public final boolean a() {
        dc1 dc1Var = this.h;
        return (dc1Var == null || dc1Var.o) ? false : true;
    }

    public void addOnCheckedChangeListener(a aVar) {
        this.i.add(aVar);
    }

    public final void b() {
        int i = this.t;
        if (i == 1 || i == 2) {
            setCompoundDrawablesRelative(this.m, null, null, null);
            return;
        }
        if (i == 3 || i == 4) {
            setCompoundDrawablesRelative(null, null, this.m, null);
            return;
        }
        if (i == 16 || i == 32) {
            setCompoundDrawablesRelative(null, this.m, null, null);
        }
    }

    public final void c(boolean z) {
        Drawable drawable = this.m;
        boolean z2 = true;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.m = drawableMutate;
            drawableMutate.setTintList(this.l);
            PorterDuff.Mode mode = this.k;
            if (mode != null) {
                this.m.setTintMode(mode);
            }
            int intrinsicWidth = this.n;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.m.getIntrinsicWidth();
            }
            int intrinsicHeight = this.n;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.m.getIntrinsicHeight();
            }
            Drawable drawable2 = this.m;
            int i = this.o;
            int i2 = this.p;
            drawable2.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.m.setVisible(true, z);
        }
        if (z) {
            b();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        int i3 = this.t;
        if (!(i3 == 1 || i3 == 2) || drawable3 == this.m) {
            if (!(i3 == 3 || i3 == 4) || drawable5 == this.m) {
                if (!(i3 == 16 || i3 == 32) || drawable4 == this.m) {
                    z2 = false;
                }
            }
        }
        if (z2) {
            b();
        }
    }

    public final void d(int i, int i2) {
        if (this.m == null || getLayout() == null) {
            return;
        }
        int i3 = this.t;
        if (!(i3 == 1 || i3 == 2)) {
            if (!(i3 == 3 || i3 == 4)) {
                if (i3 != 16 && i3 != 32) {
                    z = false;
                }
                if (z) {
                    this.o = 0;
                    if (i3 == 16) {
                        this.p = 0;
                        c(false);
                        return;
                    }
                    int intrinsicHeight = this.n;
                    if (intrinsicHeight == 0) {
                        intrinsicHeight = this.m.getIntrinsicHeight();
                    }
                    int iMax = Math.max(0, (((((i2 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.q) - getPaddingBottom()) / 2);
                    if (this.p != iMax) {
                        this.p = iMax;
                        c(false);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        this.p = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i4 = this.t;
        if (i4 == 1 || i4 == 3 || ((i4 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i4 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.o = 0;
            c(false);
            return;
        }
        int intrinsicWidth = this.n;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.m.getIntrinsicWidth();
        }
        int textLayoutWidth = i - getTextLayoutWidth();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iD = (((textLayoutWidth - om3.e.d(this)) - intrinsicWidth) - this.q) - om3.e.e(this);
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            iD /= 2;
        }
        if ((om3.e.c(this) == 1) != (this.t == 4)) {
            iD = -iD;
        }
        if (this.o != iD) {
            this.o = iD;
            c(false);
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
        if (a()) {
            return this.h.g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.m;
    }

    public int getIconGravity() {
        return this.t;
    }

    public int getIconPadding() {
        return this.q;
    }

    public int getIconSize() {
        return this.n;
    }

    public ColorStateList getIconTint() {
        return this.l;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.k;
    }

    public int getInsetBottom() {
        return this.h.f;
    }

    public int getInsetTop() {
        return this.h.e;
    }

    public ColorStateList getRippleColor() {
        if (a()) {
            return this.h.l;
        }
        return null;
    }

    public uj2 getShapeAppearanceModel() {
        if (a()) {
            return this.h.b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (a()) {
            return this.h.k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (a()) {
            return this.h.h;
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return a() ? this.h.j : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return a() ? this.h.i : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.r;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (a()) {
            oj2.e0(this, this.h.b(false));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        dc1 dc1Var = this.h;
        if (dc1Var != null && dc1Var.q) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, u);
        }
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, v);
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
        dc1 dc1Var = this.h;
        accessibilityNodeInfo.setCheckable(dc1Var != null && dc1Var.q);
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        setChecked(savedState.g);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.g = this.r;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.m != null) {
            if (this.m.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void removeOnCheckedChangeListener(a aVar) {
        this.i.remove(aVar);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (!a()) {
            super.setBackgroundColor(i);
            return;
        }
        dc1 dc1Var = this.h;
        if (dc1Var.b(false) != null) {
            dc1Var.b(false).setTint(i);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!a()) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        if (drawable == getBackground()) {
            getBackground().setState(drawable.getState());
            return;
        }
        Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
        dc1 dc1Var = this.h;
        dc1Var.o = true;
        ColorStateList colorStateList = dc1Var.j;
        MaterialButton materialButton = dc1Var.a;
        materialButton.setSupportBackgroundTintList(colorStateList);
        materialButton.setSupportBackgroundTintMode(dc1Var.i);
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? AppCompatResources.a(getContext(), i) : null);
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
        if (a()) {
            this.h.q = z;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        dc1 dc1Var = this.h;
        if ((dc1Var != null && dc1Var.q) && isEnabled() && this.r != z) {
            this.r = z;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) getParent();
                boolean z2 = this.r;
                if (!materialButtonToggleGroup.j) {
                    materialButtonToggleGroup.b(getId(), z2);
                }
            }
            if (this.s) {
                return;
            }
            this.s = true;
            Iterator<a> it = this.i.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.s = false;
        }
    }

    public void setCornerRadius(int i) {
        if (a()) {
            dc1 dc1Var = this.h;
            if (dc1Var.p && dc1Var.g == i) {
                return;
            }
            dc1Var.g = i;
            dc1Var.p = true;
            dc1Var.c(dc1Var.b.f(i));
        }
    }

    public void setCornerRadiusResource(int i) {
        if (a()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        if (a()) {
            this.h.b(false).m(f);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.m != drawable) {
            this.m = drawable;
            c(true);
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.t != i) {
            this.t = i;
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.q != i) {
            this.q = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? AppCompatResources.a(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.n != i) {
            this.n = i;
            c(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            c(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.k != mode) {
            this.k = mode;
            c(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(bz.b(getContext(), i));
    }

    public void setInsetBottom(int i) {
        dc1 dc1Var = this.h;
        dc1Var.d(dc1Var.e, i);
    }

    public void setInsetTop(int i) {
        dc1 dc1Var = this.h;
        dc1Var.d(i, dc1Var.f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(b bVar) {
        this.j = bVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        b bVar = this.j;
        if (bVar != null) {
            MaterialButtonToggleGroup.this.invalidate();
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (a()) {
            dc1 dc1Var = this.h;
            if (dc1Var.l != colorStateList) {
                dc1Var.l = colorStateList;
                MaterialButton materialButton = dc1Var.a;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButton.getBackground()).setColor(dc2.c(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i) {
        if (a()) {
            setRippleColor(bz.b(getContext(), i));
        }
    }

    @Override // defpackage.yj2
    public void setShapeAppearanceModel(uj2 uj2Var) {
        if (!a()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.h.c(uj2Var);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (a()) {
            dc1 dc1Var = this.h;
            dc1Var.n = z;
            dc1Var.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (a()) {
            dc1 dc1Var = this.h;
            if (dc1Var.k != colorStateList) {
                dc1Var.k = colorStateList;
                dc1Var.f();
            }
        }
    }

    public void setStrokeColorResource(int i) {
        if (a()) {
            setStrokeColor(bz.b(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (a()) {
            dc1 dc1Var = this.h;
            if (dc1Var.h != i) {
                dc1Var.h = i;
                dc1Var.f();
            }
        }
    }

    public void setStrokeWidthResource(int i) {
        if (a()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (!a()) {
            super.setSupportBackgroundTintList(colorStateList);
            return;
        }
        dc1 dc1Var = this.h;
        if (dc1Var.j != colorStateList) {
            dc1Var.j = colorStateList;
            if (dc1Var.b(false) != null) {
                dc1Var.b(false).setTintList(dc1Var.j);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!a()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        dc1 dc1Var = this.h;
        if (dc1Var.i != mode) {
            dc1Var.i = mode;
            if (dc1Var.b(false) == null || dc1Var.i == null) {
                return;
            }
            dc1Var.b(false).setTintMode(dc1Var.i);
        }
    }

    @Override // android.view.View
    public void setTextAlignment(int i) {
        super.setTextAlignment(i);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.r);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = w;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.i = new LinkedHashSet<>();
        this.r = false;
        this.s = false;
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.MaterialButton, i, i2, new int[0]);
        this.q = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_iconPadding, 0);
        this.k = yn3.e(typedArrayD.getInt(R$styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.l = rc1.a(getContext(), typedArrayD, R$styleable.MaterialButton_iconTint);
        this.m = rc1.d(getContext(), typedArrayD, R$styleable.MaterialButton_icon);
        this.t = typedArrayD.getInteger(R$styleable.MaterialButton_iconGravity, 1);
        this.n = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_iconSize, 0);
        dc1 dc1Var = new dc1(this, new uj2(uj2.b(context2, attributeSet, i, i2)));
        this.h = dc1Var;
        dc1Var.c = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        dc1Var.d = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        dc1Var.e = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        dc1Var.f = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        int i3 = R$styleable.MaterialButton_cornerRadius;
        if (typedArrayD.hasValue(i3)) {
            int dimensionPixelSize = typedArrayD.getDimensionPixelSize(i3, -1);
            dc1Var.g = dimensionPixelSize;
            dc1Var.c(dc1Var.b.f(dimensionPixelSize));
            dc1Var.p = true;
        }
        dc1Var.h = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        dc1Var.i = yn3.e(typedArrayD.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        dc1Var.j = rc1.a(getContext(), typedArrayD, R$styleable.MaterialButton_backgroundTint);
        dc1Var.k = rc1.a(getContext(), typedArrayD, R$styleable.MaterialButton_strokeColor);
        dc1Var.l = rc1.a(getContext(), typedArrayD, R$styleable.MaterialButton_rippleColor);
        dc1Var.q = typedArrayD.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        dc1Var.s = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iE = om3.e.e(this);
        int paddingTop = getPaddingTop();
        int iD = om3.e.d(this);
        int paddingBottom = getPaddingBottom();
        if (typedArrayD.hasValue(R$styleable.MaterialButton_android_background)) {
            dc1Var.o = true;
            setSupportBackgroundTintList(dc1Var.j);
            setSupportBackgroundTintMode(dc1Var.i);
        } else {
            dc1Var.e();
        }
        om3.e.i(this, iE + dc1Var.c, paddingTop + dc1Var.e, iD + dc1Var.d, paddingBottom + dc1Var.f);
        typedArrayD.recycle();
        setCompoundDrawablePadding(this.q);
        c(this.m != null);
    }
}
