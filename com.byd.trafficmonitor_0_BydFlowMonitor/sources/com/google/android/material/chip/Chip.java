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
import b.h.m.c0.c;
import b.h.m.t;
import c.b.a.a.f0.d;
import c.b.a.a.f0.f;
import c.b.a.a.i0.i;
import c.b.a.a.i0.m;
import c.b.a.a.i0.p;
import c.b.a.a.j;
import c.b.a.a.k;
import c.b.a.a.l;
import c.b.a.a.m.h;
import c.b.a.a.u.a;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements a.InterfaceC0091a, p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f4636e = k.Widget_MaterialComponents_Chip_Action;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Rect f4637f = new Rect();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f4638g = {R.attr.state_selected};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f4639h = {R.attr.state_checkable};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c.b.a.a.u.a f4640i;
    public InsetDrawable j;
    public RippleDrawable k;
    public View.OnClickListener l;
    public CompoundButton.OnCheckedChangeListener m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public int t;
    public final c u;
    public final Rect v;
    public final RectF w;
    public final f x;

    public class a extends f {
        public a() {
        }

        @Override // c.b.a.a.f0.f
        public void a(int i2) {
        }

        @Override // c.b.a.a.f0.f
        public void b(Typeface typeface, boolean z) {
            Chip chip = Chip.this;
            chip.setText(chip.f4640i.M2() ? Chip.this.f4640i.i1() : Chip.this.getText());
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
            if (Chip.this.f4640i != null) {
                Chip.this.f4640i.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    public class c extends b.j.a.a {
        public c(Chip chip) {
            super(chip);
        }

        @Override // b.j.a.a
        public int B(float f2, float f3) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f2, f3)) ? 1 : 0;
        }

        @Override // b.j.a.a
        public void C(List<Integer> list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.l != null) {
                list.add(1);
            }
        }

        @Override // b.j.a.a
        public boolean L(int i2, int i3, Bundle bundle) {
            if (i3 != 16) {
                return false;
            }
            if (i2 == 0) {
                return Chip.this.performClick();
            }
            if (i2 == 1) {
                return Chip.this.t();
            }
            return false;
        }

        @Override // b.j.a.a
        public void O(b.h.m.c0.c cVar) {
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

        @Override // b.j.a.a
        public void P(int i2, b.h.m.c0.c cVar) {
            if (i2 != 1) {
                cVar.g0("");
                cVar.X(Chip.f4637f);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                cVar.g0(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i3 = j.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                cVar.g0(context.getString(i3, objArr).trim());
            }
            cVar.X(Chip.this.getCloseIconTouchBoundsInt());
            cVar.b(c.a.f2616e);
            cVar.h0(Chip.this.isEnabled());
        }

        @Override // b.j.a.a
        public void Q(int i2, boolean z) {
            if (i2 == 1) {
                Chip.this.q = z;
                Chip.this.refreshDrawableState();
            }
        }
    }

    public Chip(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.w.setEmpty();
        if (n()) {
            this.f4640i.b1(this.w);
        }
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.v.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.v;
    }

    private d getTextAppearance() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.j1();
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.p != z) {
            this.p = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.o != z) {
            this.o = z;
            refreshDrawableState();
        }
    }

    public final void A() {
        c.b.a.a.u.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.f4640i) == null) {
            return;
        }
        int iM0 = (int) (aVar.M0() + this.f4640i.k1() + this.f4640i.t0());
        int iR0 = (int) (this.f4640i.R0() + this.f4640i.l1() + this.f4640i.p0());
        if (this.j != null) {
            Rect rect = new Rect();
            this.j.getPadding(rect);
            iR0 += rect.left;
            iM0 += rect.right;
        }
        t.y0(this, iR0, getPaddingTop(), iM0, getPaddingBottom());
    }

    public final void B() {
        TextPaint paint = getPaint();
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        d textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.i(getContext(), paint, this.x);
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

    @Override // c.b.a.a.u.a.InterfaceC0091a
    public void a() {
        k(this.t);
        requestLayout();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return m(motionEvent) || this.u.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.u.w(keyEvent) || this.u.A() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        c.b.a.a.u.a aVar = this.f4640i;
        if ((aVar == null || !aVar.q1()) ? false : this.f4640i.m2(j())) {
            invalidate();
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.j;
        return insetDrawable == null ? this.f4640i : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.I0();
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.J0();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.K0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return Math.max(0.0f, aVar.L0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f4640i;
    }

    public float getChipEndPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.M0();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.N0();
        }
        return null;
    }

    public float getChipIconSize() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.O0();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.P0();
        }
        return null;
    }

    public float getChipMinHeight() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.Q0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.R0();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.S0();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        c.b.a.a.u.a aVar = this.f4640i;
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
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.U0();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.V0();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.W0();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.X0();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.Y0();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.a1();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.c1();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.u.A() == 1 || this.u.x() == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public h getHideMotionSpec() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.d1();
        }
        return null;
    }

    public float getIconEndPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.e1();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.f1();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.g1();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        return this.f4640i.D();
    }

    public h getShowMotionSpec() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.h1();
        }
        return null;
    }

    public float getTextEndPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.k1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            return aVar.l1();
        }
        return 0.0f;
    }

    public final void i(c.b.a.a.u.a aVar) {
        aVar.q2(this);
    }

    public final int[] j() {
        int i2 = 0;
        int i3 = isEnabled() ? 1 : 0;
        if (this.q) {
            i3++;
        }
        if (this.p) {
            i3++;
        }
        if (this.o) {
            i3++;
        }
        if (isChecked()) {
            i3++;
        }
        int[] iArr = new int[i3];
        if (isEnabled()) {
            iArr[0] = 16842910;
            i2 = 1;
        }
        if (this.q) {
            iArr[i2] = 16842908;
            i2++;
        }
        if (this.p) {
            iArr[i2] = 16843623;
            i2++;
        }
        if (this.o) {
            iArr[i2] = 16842919;
            i2++;
        }
        if (isChecked()) {
            iArr[i2] = 16842913;
        }
        return iArr;
    }

    public boolean k(int i2) {
        this.t = i2;
        if (!v()) {
            if (this.j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int iMax = Math.max(0, i2 - this.f4640i.getIntrinsicHeight());
        int iMax2 = Math.max(0, i2 - this.f4640i.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int i3 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i4 = iMax > 0 ? iMax / 2 : 0;
        if (this.j != null) {
            Rect rect = new Rect();
            this.j.getPadding(rect);
            if (rect.top == i4 && rect.bottom == i4 && rect.left == i3 && rect.right == i3) {
                y();
                return true;
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (getMinHeight() != i2) {
                setMinHeight(i2);
            }
            if (getMinWidth() != i2) {
                setMinWidth(i2);
            }
        } else {
            setMinHeight(i2);
            setMinWidth(i2);
        }
        q(i3, i4, i3, i4);
        y();
        return true;
    }

    public final void l() {
        if (getBackgroundDrawable() == this.j && this.f4640i.getCallback() == null) {
            this.f4640i.setCallback(this.j);
        }
    }

    @SuppressLint({"PrivateApi"})
    public final boolean m(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = b.j.a.a.class.getDeclaredField("p");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.u)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = b.j.a.a.class.getDeclaredMethod("X", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.u, Integer.MIN_VALUE);
                    return true;
                }
            } catch (IllegalAccessException e2) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e2);
            } catch (NoSuchFieldException e3) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e3);
            } catch (NoSuchMethodException e4) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e4);
            } catch (InvocationTargetException e5) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e5);
            }
        }
        return false;
    }

    public final boolean n() {
        c.b.a.a.u.a aVar = this.f4640i;
        return (aVar == null || aVar.U0() == null) ? false : true;
    }

    public final void o(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context, attributeSet, l.Chip, i2, f4636e, new int[0]);
        this.r = typedArrayH.getBoolean(l.Chip_ensureMinTouchTargetSize, false);
        this.t = (int) Math.ceil(typedArrayH.getDimension(l.Chip_chipMinTouchTargetSize, (float) Math.ceil(c.b.a.a.d0.p.c(getContext(), 48))));
        typedArrayH.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.f4640i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f4638g);
        }
        if (r()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f4639h);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        this.u.K(z, i2, rect);
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
            b.h.m.c0.c.F0(accessibilityNodeInfo).f0(c.C0044c.a(chipGroup.b(this), 1, chipGroup.c() ? chipGroup.n(this) : -1, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i2) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        if (this.s != i2) {
            this.s = i2;
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
            boolean r0 = r5.o
            if (r0 == 0) goto L40
            if (r1 != 0) goto L3e
            r5.setCloseIconPressed(r2)
            goto L3e
        L2b:
            boolean r0 = r5.o
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

    public final void q(int i2, int i3, int i4, int i5) {
        this.j = new InsetDrawable((Drawable) this.f4640i, i2, i3, i4, i5);
    }

    public boolean r() {
        c.b.a.a.u.a aVar = this.f4640i;
        return aVar != null && aVar.p1();
    }

    public boolean s() {
        c.b.a.a.u.a aVar = this.f4640i;
        return aVar != null && aVar.r1();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.k) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.k) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i2) {
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

    public void setCheckable(boolean z) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.y1(z);
        }
    }

    public void setCheckableResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.z1(i2);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar == null) {
            this.n = z;
            return;
        }
        if (aVar.p1()) {
            boolean zIsChecked = isChecked();
            super.setChecked(z);
            if (zIsChecked == z || (onCheckedChangeListener = this.m) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.A1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i2) {
        setCheckedIconVisible(i2);
    }

    public void setCheckedIconResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.B1(i2);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.C1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.D1(i2);
        }
    }

    public void setCheckedIconVisible(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.E1(i2);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.G1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.H1(i2);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.I1(f2);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.J1(i2);
        }
    }

    public void setChipDrawable(c.b.a.a.u.a aVar) {
        c.b.a.a.u.a aVar2 = this.f4640i;
        if (aVar2 != aVar) {
            w(aVar2);
            this.f4640i = aVar;
            aVar.B2(false);
            i(this.f4640i);
            k(this.t);
        }
    }

    public void setChipEndPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.K1(f2);
        }
    }

    public void setChipEndPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.L1(i2);
        }
    }

    public void setChipIcon(Drawable drawable) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.M1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i2) {
        setChipIconVisible(i2);
    }

    public void setChipIconResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.N1(i2);
        }
    }

    public void setChipIconSize(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.O1(f2);
        }
    }

    public void setChipIconSizeResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.P1(i2);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.Q1(colorStateList);
        }
    }

    public void setChipIconTintResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.R1(i2);
        }
    }

    public void setChipIconVisible(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.S1(i2);
        }
    }

    public void setChipMinHeight(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.U1(f2);
        }
    }

    public void setChipMinHeightResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.V1(i2);
        }
    }

    public void setChipStartPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.W1(f2);
        }
    }

    public void setChipStartPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.X1(i2);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.Y1(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.Z1(i2);
        }
    }

    public void setChipStrokeWidth(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.a2(f2);
        }
    }

    public void setChipStrokeWidthResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.b2(i2);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i2) {
        setText(getResources().getString(i2));
    }

    public void setCloseIcon(Drawable drawable) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.d2(drawable);
        }
        x();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.e2(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i2) {
        setCloseIconVisible(i2);
    }

    public void setCloseIconEndPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.f2(f2);
        }
    }

    public void setCloseIconEndPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.g2(i2);
        }
    }

    public void setCloseIconResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.h2(i2);
        }
        x();
    }

    public void setCloseIconSize(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.i2(f2);
        }
    }

    public void setCloseIconSizeResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.j2(i2);
        }
    }

    public void setCloseIconStartPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.k2(f2);
        }
    }

    public void setCloseIconStartPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.l2(i2);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.n2(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.o2(i2);
        }
    }

    public void setCloseIconVisible(int i2) {
        setCloseIconVisible(getResources().getBoolean(i2));
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
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i4 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i2, i3, i4, i5);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i4 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i2, i3, i4, i5);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.W(f2);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f4640i == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.r2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.r = z;
        k(this.t);
    }

    @Override // android.widget.TextView
    public void setGravity(int i2) {
        if (i2 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i2);
        }
    }

    public void setHideMotionSpec(h hVar) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.s2(hVar);
        }
    }

    public void setHideMotionSpecResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.t2(i2);
        }
    }

    public void setIconEndPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.u2(f2);
        }
    }

    public void setIconEndPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.v2(i2);
        }
    }

    public void setIconStartPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.w2(f2);
        }
    }

    public void setIconStartPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.x2(i2);
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i2) {
        if (this.f4640i != null && Build.VERSION.SDK_INT >= 17) {
            super.setLayoutDirection(i2);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i2);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i2);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i2) {
        super.setMaxWidth(i2);
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.y2(i2);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i2);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.m = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.l = onClickListener;
        x();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.z2(colorStateList);
        }
        if (this.f4640i.n1()) {
            return;
        }
        z();
    }

    public void setRippleColorResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.A2(i2);
            if (this.f4640i.n1()) {
                return;
            }
            z();
        }
    }

    @Override // c.b.a.a.i0.p
    public void setShapeAppearanceModel(m mVar) {
        this.f4640i.setShapeAppearanceModel(mVar);
    }

    public void setShowMotionSpec(h hVar) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.C2(hVar);
        }
    }

    public void setShowMotionSpecResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.D2(i2);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
        if (!z) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(aVar.M2() ? null : charSequence, bufferType);
        c.b.a.a.u.a aVar2 = this.f4640i;
        if (aVar2 != null) {
            aVar2.E2(charSequence);
        }
    }

    public void setTextAppearance(d dVar) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.F2(dVar);
        }
        B();
    }

    public void setTextAppearanceResource(int i2) {
        setTextAppearance(getContext(), i2);
    }

    public void setTextEndPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.H2(f2);
        }
    }

    public void setTextEndPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.I2(i2);
        }
    }

    public void setTextStartPadding(float f2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.J2(f2);
        }
    }

    public void setTextStartPaddingResource(int i2) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.K2(i2);
        }
    }

    public boolean t() {
        boolean z = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.l;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z = true;
        }
        this.u.W(1, 1);
        return z;
    }

    public final void u() {
        if (this.j != null) {
            this.j = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            y();
        }
    }

    public boolean v() {
        return this.r;
    }

    public final void w(c.b.a.a.u.a aVar) {
        if (aVar != null) {
            aVar.q2(null);
        }
    }

    public final void x() {
        if (n() && s() && this.l != null) {
            t.k0(this, this.u);
        } else {
            t.k0(this, null);
        }
    }

    public final void y() {
        if (c.b.a.a.g0.b.f3834a) {
            z();
            return;
        }
        this.f4640i.L2(true);
        t.n0(this, getBackgroundDrawable());
        A();
        l();
    }

    public final void z() {
        this.k = new RippleDrawable(c.b.a.a.g0.b.d(this.f4640i.g1()), getBackgroundDrawable(), null);
        this.f4640i.L2(false);
        t.n0(this, this.k);
        A();
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.chipStyle);
    }

    public void setCloseIconVisible(boolean z) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.p2(z);
        }
        x();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f4636e;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.v = new Rect();
        this.w = new RectF();
        this.x = new a();
        Context context2 = getContext();
        C(attributeSet);
        c.b.a.a.u.a aVarY0 = c.b.a.a.u.a.y0(context2, attributeSet, i2, i3);
        o(context2, attributeSet, i2);
        setChipDrawable(aVarY0);
        aVarY0.W(t.u(this));
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context2, attributeSet, l.Chip, i2, i3, new int[0]);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(c.b.a.a.f0.c.a(context2, typedArrayH, l.Chip_android_textColor));
        }
        boolean zHasValue = typedArrayH.hasValue(l.Chip_shapeAppearance);
        typedArrayH.recycle();
        this.u = new c(this);
        x();
        if (!zHasValue) {
            p();
        }
        setChecked(this.n);
        setText(aVarY0.i1());
        setEllipsize(aVarY0.c1());
        B();
        if (!this.f4640i.M2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        A();
        if (v()) {
            setMinHeight(this.t);
        }
        this.s = t.z(this);
    }

    public void setCheckedIconVisible(boolean z) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.F1(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.T1(z);
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
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.G2(i2);
        }
        B();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i2) {
        super.setTextAppearance(i2);
        c.b.a.a.u.a aVar = this.f4640i;
        if (aVar != null) {
            aVar.G2(i2);
        }
        B();
    }
}
