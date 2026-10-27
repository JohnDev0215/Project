package com.google.android.material.chip;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import b6.d;
import b6.f;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.google.android.material.R$attr;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import e6.i;
import e6.m;
import e6.p;
import j5.h;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import o0.t;
import p0.c;
import r5.a;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class Chip extends AppCompatCheckBox implements a.InterfaceC0114a, p {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f3766g = R$style.Widget_MaterialComponents_Chip_Action;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Rect f3767h = new Rect();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f3768i = {R.attr.state_selected};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f3769j = {R.attr.state_checkable};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public r5.a f3770k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public InsetDrawable f3771l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public RippleDrawable f3772m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View.OnClickListener f3773n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f3774o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3775p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3776q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3777r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3778s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3779t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3780u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3781v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final c f3782w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Rect f3783x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final RectF f3784y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final f f3785z;

    public class a extends f {
        public a() {
        }

        @Override // b6.f
        public void a(int i8) {
        }

        @Override // b6.f
        public void b(Typeface typeface, boolean z7) {
            Chip chip = Chip.this;
            chip.setText(chip.f3770k.M2() ? Chip.this.f3770k.i1() : Chip.this.getText());
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        @TargetApi(21)
        public void getOutline(View view, Outline outline) {
            if (Chip.this.f3770k != null) {
                Chip.this.f3770k.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    public class c extends t0.a {
        public c(Chip chip) {
            super(chip);
        }

        @Override // t0.a
        public int B(float f8, float f9) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f8, f9)) ? 1 : 0;
        }

        @Override // t0.a
        public void C(List<Integer> list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.f3773n != null) {
                list.add(1);
            }
        }

        @Override // t0.a
        public boolean L(int i8, int i9, Bundle bundle) {
            if (i9 != 16) {
                return false;
            }
            if (i8 == 0) {
                return Chip.this.performClick();
            }
            if (i8 == 1) {
                return Chip.this.t();
            }
            return false;
        }

        @Override // t0.a
        public void O(p0.c cVar) {
            cVar.a0(Chip.this.r());
            cVar.d0(Chip.this.isClickable());
            if (Chip.this.r() || Chip.this.isClickable()) {
                cVar.c0(Chip.this.r() ? "android.widget.CompoundButton" : "android.widget.Button");
            } else {
                cVar.c0("android.view.View");
            }
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                cVar.C0(text);
            } else {
                cVar.g0(text);
            }
        }

        @Override // t0.a
        public void P(int i8, p0.c cVar) {
            if (i8 != 1) {
                cVar.g0("");
                cVar.X(Chip.f3767h);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                cVar.g0(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i9 = R$string.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                cVar.g0(context.getString(i9, objArr).trim());
            }
            cVar.X(Chip.this.getCloseIconTouchBoundsInt());
            cVar.b(c.a.f6972e);
            cVar.h0(Chip.this.isEnabled());
        }

        @Override // t0.a
        public void Q(int i8, boolean z7) {
            if (i8 == 1) {
                Chip.this.f3778s = z7;
                Chip.this.refreshDrawableState();
            }
        }
    }

    public Chip(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.f3784y.setEmpty();
        if (n()) {
            this.f3770k.b1(this.f3784y);
        }
        return this.f3784y;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.f3783x.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.f3783x;
    }

    private d getTextAppearance() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.j1();
        }
        return null;
    }

    private void setCloseIconHovered(boolean z7) {
        if (this.f3777r != z7) {
            this.f3777r = z7;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z7) {
        if (this.f3776q != z7) {
            this.f3776q = z7;
            refreshDrawableState();
        }
    }

    public final void A() {
        r5.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.f3770k) == null) {
            return;
        }
        int iM0 = (int) (aVar.M0() + this.f3770k.k1() + this.f3770k.t0());
        int iR0 = (int) (this.f3770k.R0() + this.f3770k.l1() + this.f3770k.p0());
        if (this.f3771l != null) {
            Rect rect = new Rect();
            this.f3771l.getPadding(rect);
            iR0 += rect.left;
            iM0 += rect.right;
        }
        t.z0(this, iR0, getPaddingTop(), iM0, getPaddingBottom());
    }

    public final void B() {
        TextPaint paint = getPaint();
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        d textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.i(getContext(), paint, this.f3785z);
        }
    }

    public final void C(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", AttrFactory.BACKGROUND) != null) {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        }
    }

    @Override // r5.a.InterfaceC0114a
    public void a() {
        k(this.f3781v);
        requestLayout();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return m(motionEvent) || this.f3782w.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.f3782w.w(keyEvent) || this.f3782w.A() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        r5.a aVar = this.f3770k;
        if ((aVar == null || !aVar.q1()) ? false : this.f3770k.m2(j())) {
            invalidate();
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f3771l;
        return insetDrawable == null ? this.f3770k : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.I0();
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.J0();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.K0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return Math.max(0.0f, aVar.L0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f3770k;
    }

    public float getChipEndPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.M0();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.N0();
        }
        return null;
    }

    public float getChipIconSize() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.O0();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.P0();
        }
        return null;
    }

    public float getChipMinHeight() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.Q0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.R0();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.S0();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.T0();
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.U0();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.V0();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.W0();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.X0();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.Y0();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.a1();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.c1();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.f3782w.A() == 1 || this.f3782w.x() == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public h getHideMotionSpec() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.d1();
        }
        return null;
    }

    public float getIconEndPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.e1();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.f1();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.g1();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        return this.f3770k.D();
    }

    public h getShowMotionSpec() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.h1();
        }
        return null;
    }

    public float getTextEndPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.k1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            return aVar.l1();
        }
        return 0.0f;
    }

    public final void i(r5.a aVar) {
        aVar.q2(this);
    }

    public final int[] j() {
        int i8 = 0;
        int i9 = isEnabled() ? 1 : 0;
        if (this.f3778s) {
            i9++;
        }
        if (this.f3777r) {
            i9++;
        }
        if (this.f3776q) {
            i9++;
        }
        if (isChecked()) {
            i9++;
        }
        int[] iArr = new int[i9];
        if (isEnabled()) {
            iArr[0] = 16842910;
            i8 = 1;
        }
        if (this.f3778s) {
            iArr[i8] = 16842908;
            i8++;
        }
        if (this.f3777r) {
            iArr[i8] = 16843623;
            i8++;
        }
        if (this.f3776q) {
            iArr[i8] = 16842919;
            i8++;
        }
        if (isChecked()) {
            iArr[i8] = 16842913;
        }
        return iArr;
    }

    public boolean k(int i8) {
        this.f3781v = i8;
        if (!v()) {
            if (this.f3771l != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int iMax = Math.max(0, i8 - this.f3770k.getIntrinsicHeight());
        int iMax2 = Math.max(0, i8 - this.f3770k.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.f3771l != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int i9 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i10 = iMax > 0 ? iMax / 2 : 0;
        if (this.f3771l != null) {
            Rect rect = new Rect();
            this.f3771l.getPadding(rect);
            if (rect.top == i10 && rect.bottom == i10 && rect.left == i9 && rect.right == i9) {
                y();
                return true;
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (getMinHeight() != i8) {
                setMinHeight(i8);
            }
            if (getMinWidth() != i8) {
                setMinWidth(i8);
            }
        } else {
            setMinHeight(i8);
            setMinWidth(i8);
        }
        q(i9, i10, i9, i10);
        y();
        return true;
    }

    public final void l() {
        if (getBackgroundDrawable() == this.f3771l && this.f3770k.getCallback() == null) {
            this.f3770k.setCallback(this.f3771l);
        }
    }

    @SuppressLint({"PrivateApi"})
    public final boolean m(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = t0.a.class.getDeclaredField("p");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.f3782w)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = t0.a.class.getDeclaredMethod("X", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.f3782w, Integer.MIN_VALUE);
                    return true;
                }
            } catch (IllegalAccessException e8) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e8);
            } catch (NoSuchFieldException e9) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e9);
            } catch (NoSuchMethodException e10) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e10);
            } catch (InvocationTargetException e11) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e11);
            }
        }
        return false;
    }

    public final boolean n() {
        r5.a aVar = this.f3770k;
        return (aVar == null || aVar.U0() == null) ? false : true;
    }

    public final void o(Context context, AttributeSet attributeSet, int i8) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Chip, i8, f3766g, new int[0]);
        this.f3779t = typedArrayH.getBoolean(R$styleable.Chip_ensureMinTouchTargetSize, false);
        this.f3781v = (int) Math.ceil(typedArrayH.getDimension(R$styleable.Chip_chipMinTouchTargetSize, (float) Math.ceil(z5.p.c(getContext(), 48))));
        typedArrayH.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.f3770k);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i8) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f3768i);
        }
        if (r()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f3769j);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z7, int i8, Rect rect) {
        super.onFocusChanged(z7, i8, rect);
        this.f3782w.K(z7, i8, rect);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (r() || isClickable()) {
            accessibilityNodeInfo.setClassName(r() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            accessibilityNodeInfo.setClassName("android.view.View");
        }
        accessibilityNodeInfo.setCheckable(r());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            p0.c.F0(accessibilityNodeInfo).f0(c.C0101c.a(chipGroup.b(this), 1, chipGroup.c() ? chipGroup.n(this) : -1, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i8) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i8) {
        super.onRtlPropertiesChanged(i8);
        if (this.f3780u != i8) {
            this.f3780u = i8;
            A();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L22;
     */
    @Override // android.widget.TextView, android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L39
            if (r0 == r3) goto L2b
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L34
            goto L40
        L21:
            boolean r0 = r5.f3776q
            if (r0 == 0) goto L40
            if (r1 != 0) goto L3e
            r5.setCloseIconPressed(r2)
            goto L3e
        L2b:
            boolean r0 = r5.f3776q
            if (r0 == 0) goto L34
            r5.t()
            r0 = r3
            goto L35
        L34:
            r0 = r2
        L35:
            r5.setCloseIconPressed(r2)
            goto L41
        L39:
            if (r1 == 0) goto L40
            r5.setCloseIconPressed(r3)
        L3e:
            r0 = r3
            goto L41
        L40:
            r0 = r2
        L41:
            if (r0 != 0) goto L49
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L4a
        L49:
            r2 = r3
        L4a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p() {
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new b());
        }
    }

    public final void q(int i8, int i9, int i10, int i11) {
        this.f3771l = new InsetDrawable((Drawable) this.f3770k, i8, i9, i10, i11);
    }

    public boolean r() {
        r5.a aVar = this.f3770k;
        return aVar != null && aVar.p1();
    }

    public boolean s() {
        r5.a aVar = this.f3770k;
        return aVar != null && aVar.r1();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f3772m) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i8) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f3772m) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i8) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z7) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.y1(z7);
        }
    }

    public void setCheckableResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.z1(i8);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z7) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        r5.a aVar = this.f3770k;
        if (aVar == null) {
            this.f3775p = z7;
            return;
        }
        if (aVar.p1()) {
            boolean zIsChecked = isChecked();
            super.setChecked(z7);
            if (zIsChecked == z7 || (onCheckedChangeListener = this.f3774o) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z7);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.A1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z7) {
        setCheckedIconVisible(z7);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i8) {
        setCheckedIconVisible(i8);
    }

    public void setCheckedIconResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.B1(i8);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.C1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.D1(i8);
        }
    }

    public void setCheckedIconVisible(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.E1(i8);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.G1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.H1(i8);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.I1(f8);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.J1(i8);
        }
    }

    public void setChipDrawable(r5.a aVar) {
        r5.a aVar2 = this.f3770k;
        if (aVar2 != aVar) {
            w(aVar2);
            this.f3770k = aVar;
            aVar.B2(false);
            i(this.f3770k);
            k(this.f3781v);
        }
    }

    public void setChipEndPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.K1(f8);
        }
    }

    public void setChipEndPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.L1(i8);
        }
    }

    public void setChipIcon(Drawable drawable) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.M1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z7) {
        setChipIconVisible(z7);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i8) {
        setChipIconVisible(i8);
    }

    public void setChipIconResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.N1(i8);
        }
    }

    public void setChipIconSize(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.O1(f8);
        }
    }

    public void setChipIconSizeResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.P1(i8);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.Q1(colorStateList);
        }
    }

    public void setChipIconTintResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.R1(i8);
        }
    }

    public void setChipIconVisible(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.S1(i8);
        }
    }

    public void setChipMinHeight(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.U1(f8);
        }
    }

    public void setChipMinHeightResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.V1(i8);
        }
    }

    public void setChipStartPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.W1(f8);
        }
    }

    public void setChipStartPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.X1(i8);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.Y1(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.Z1(i8);
        }
    }

    public void setChipStrokeWidth(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.a2(f8);
        }
    }

    public void setChipStrokeWidthResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.b2(i8);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i8) {
        setText(getResources().getString(i8));
    }

    public void setCloseIcon(Drawable drawable) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.d2(drawable);
        }
        x();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.e2(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z7) {
        setCloseIconVisible(z7);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i8) {
        setCloseIconVisible(i8);
    }

    public void setCloseIconEndPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.f2(f8);
        }
    }

    public void setCloseIconEndPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.g2(i8);
        }
    }

    public void setCloseIconResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.h2(i8);
        }
        x();
    }

    public void setCloseIconSize(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.i2(f8);
        }
    }

    public void setCloseIconSizeResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.j2(i8);
        }
    }

    public void setCloseIconStartPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.k2(f8);
        }
    }

    public void setCloseIconStartPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.l2(i8);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.n2(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.o2(i8);
        }
    }

    public void setCloseIconVisible(int i8) {
        setCloseIconVisible(getResources().getBoolean(i8));
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i8, int i9, int i10, int i11) {
        if (i8 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i10 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i8, i9, i10, i11);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i8, int i9, int i10, int i11) {
        if (i8 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i10 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i8, i9, i10, i11);
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.W(f8);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f3770k == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.r2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z7) {
        this.f3779t = z7;
        k(this.f3781v);
    }

    @Override // android.widget.TextView
    public void setGravity(int i8) {
        if (i8 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i8);
        }
    }

    public void setHideMotionSpec(h hVar) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.s2(hVar);
        }
    }

    public void setHideMotionSpecResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.t2(i8);
        }
    }

    public void setIconEndPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.u2(f8);
        }
    }

    public void setIconEndPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.v2(i8);
        }
    }

    public void setIconStartPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.w2(f8);
        }
    }

    public void setIconStartPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.x2(i8);
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i8) {
        if (this.f3770k != null && Build.VERSION.SDK_INT >= 17) {
            super.setLayoutDirection(i8);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i8) {
        if (i8 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i8);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i8) {
        if (i8 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i8);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i8) {
        super.setMaxWidth(i8);
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.y2(i8);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i8) {
        if (i8 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i8);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f3774o = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f3773n = onClickListener;
        x();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.z2(colorStateList);
        }
        if (this.f3770k.n1()) {
            return;
        }
        z();
    }

    public void setRippleColorResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.A2(i8);
            if (this.f3770k.n1()) {
                return;
            }
            z();
        }
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        this.f3770k.setShapeAppearanceModel(mVar);
    }

    public void setShowMotionSpec(h hVar) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.C2(hVar);
        }
    }

    public void setShowMotionSpecResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.D2(i8);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z7) {
        if (!z7) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z7);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        r5.a aVar = this.f3770k;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(aVar.M2() ? null : charSequence, bufferType);
        r5.a aVar2 = this.f3770k;
        if (aVar2 != null) {
            aVar2.E2(charSequence);
        }
    }

    public void setTextAppearance(d dVar) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.F2(dVar);
        }
        B();
    }

    public void setTextAppearanceResource(int i8) {
        setTextAppearance(getContext(), i8);
    }

    public void setTextEndPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.H2(f8);
        }
    }

    public void setTextEndPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.I2(i8);
        }
    }

    public void setTextStartPadding(float f8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.J2(f8);
        }
    }

    public void setTextStartPaddingResource(int i8) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.K2(i8);
        }
    }

    public boolean t() {
        boolean z7 = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.f3773n;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z7 = true;
        }
        this.f3782w.W(1, 1);
        return z7;
    }

    public final void u() {
        if (this.f3771l != null) {
            this.f3771l = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            y();
        }
    }

    public boolean v() {
        return this.f3779t;
    }

    public final void w(r5.a aVar) {
        if (aVar != null) {
            aVar.q2(null);
        }
    }

    public final void x() {
        if (n() && s() && this.f3773n != null) {
            t.l0(this, this.f3782w);
        } else {
            t.l0(this, null);
        }
    }

    public final void y() {
        if (c6.b.f2328a) {
            z();
            return;
        }
        this.f3770k.L2(true);
        t.o0(this, getBackgroundDrawable());
        A();
        l();
    }

    public final void z() {
        this.f3772m = new RippleDrawable(c6.b.d(this.f3770k.g1()), getBackgroundDrawable(), null);
        this.f3770k.L2(false);
        t.o0(this, this.f3772m);
        A();
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipStyle);
    }

    public void setCloseIconVisible(boolean z7) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.p2(z7);
        }
        x();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3766g;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3783x = new Rect();
        this.f3784y = new RectF();
        this.f3785z = new a();
        Context context2 = getContext();
        C(attributeSet);
        r5.a aVarY0 = r5.a.y0(context2, attributeSet, i8, i9);
        o(context2, attributeSet, i8);
        setChipDrawable(aVarY0);
        aVarY0.W(t.v(this));
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.Chip, i8, i9, new int[0]);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(b6.c.a(context2, typedArrayH, R$styleable.Chip_android_textColor));
        }
        boolean zHasValue = typedArrayH.hasValue(R$styleable.Chip_shapeAppearance);
        typedArrayH.recycle();
        this.f3782w = new c(this);
        x();
        if (!zHasValue) {
            p();
        }
        setChecked(this.f3775p);
        setText(aVarY0.i1());
        setEllipsize(aVarY0.c1());
        B();
        if (!this.f3770k.M2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        A();
        if (v()) {
            setMinHeight(this.f3781v);
        }
        this.f3780u = t.A(this);
    }

    public void setCheckedIconVisible(boolean z7) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.F1(z7);
        }
    }

    public void setChipIconVisible(boolean z7) {
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.T1(z7);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.G2(i8);
        }
        B();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i8) {
        super.setTextAppearance(i8);
        r5.a aVar = this.f3770k;
        if (aVar != null) {
            aVar.G2(i8);
        }
        B();
    }
}
