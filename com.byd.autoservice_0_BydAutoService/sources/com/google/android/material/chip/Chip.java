package com.google.android.material.chip;

import a2.a;
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
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.google.android.material.R$attr;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.k;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import k2.d;
import k2.f;
import l0.t;
import m0.c;
import n2.i;
import n2.m;
import n2.p;
import s1.h;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements a.InterfaceC0002a, p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f2183e = R$style.Widget_MaterialComponents_Chip_Action;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Rect f2184f = new Rect();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f2185g = {R.attr.state_selected};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f2186h = {R.attr.state_checkable};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a2.a f2187i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public InsetDrawable f2188j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public RippleDrawable f2189k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View.OnClickListener f2190l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f2191m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2192n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2193o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2194p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2195q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2196r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2197s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2198t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final c f2199u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Rect f2200v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final RectF f2201w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final f f2202x;

    public class a extends f {
        public a() {
        }

        @Override // k2.f
        public void a(int i3) {
        }

        @Override // k2.f
        public void b(Typeface typeface, boolean z3) {
            Chip chip = Chip.this;
            chip.setText(chip.f2187i.M2() ? Chip.this.f2187i.i1() : Chip.this.getText());
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
            if (Chip.this.f2187i != null) {
                Chip.this.f2187i.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    public class c extends p0.a {
        public c(Chip chip) {
            super(chip);
        }

        @Override // p0.a
        public int B(float f3, float f4) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f3, f4)) ? 1 : 0;
        }

        @Override // p0.a
        public void C(List<Integer> list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.f2190l != null) {
                list.add(1);
            }
        }

        @Override // p0.a
        public boolean L(int i3, int i4, Bundle bundle) {
            if (i4 != 16) {
                return false;
            }
            if (i3 == 0) {
                return Chip.this.performClick();
            }
            if (i3 == 1) {
                return Chip.this.t();
            }
            return false;
        }

        @Override // p0.a
        public void O(m0.c cVar) {
            cVar.Z(Chip.this.r());
            cVar.c0(Chip.this.isClickable());
            if (Chip.this.r() || Chip.this.isClickable()) {
                cVar.b0(Chip.this.r() ? "android.widget.CompoundButton" : "android.widget.Button");
            } else {
                cVar.b0("android.view.View");
            }
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                cVar.A0(text);
            } else {
                cVar.f0(text);
            }
        }

        @Override // p0.a
        public void P(int i3, m0.c cVar) {
            if (i3 != 1) {
                cVar.f0("");
                cVar.W(Chip.f2184f);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                cVar.f0(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i4 = R$string.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                cVar.f0(context.getString(i4, objArr).trim());
            }
            cVar.W(Chip.this.getCloseIconTouchBoundsInt());
            cVar.b(c.a.f3806e);
            cVar.g0(Chip.this.isEnabled());
        }

        @Override // p0.a
        public void Q(int i3, boolean z3) {
            if (i3 == 1) {
                Chip.this.f2195q = z3;
                Chip.this.refreshDrawableState();
            }
        }
    }

    public Chip(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.f2201w.setEmpty();
        if (n()) {
            this.f2187i.b1(this.f2201w);
        }
        return this.f2201w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.f2200v.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.f2200v;
    }

    private d getTextAppearance() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.j1();
        }
        return null;
    }

    private void setCloseIconHovered(boolean z3) {
        if (this.f2194p != z3) {
            this.f2194p = z3;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z3) {
        if (this.f2193o != z3) {
            this.f2193o = z3;
            refreshDrawableState();
        }
    }

    public final void A() {
        a2.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.f2187i) == null) {
            return;
        }
        int iM0 = (int) (aVar.M0() + this.f2187i.k1() + this.f2187i.t0());
        int iR0 = (int) (this.f2187i.R0() + this.f2187i.l1() + this.f2187i.p0());
        if (this.f2188j != null) {
            Rect rect = new Rect();
            this.f2188j.getPadding(rect);
            iR0 += rect.left;
            iM0 += rect.right;
        }
        t.w0(this, iR0, getPaddingTop(), iM0, getPaddingBottom());
    }

    public final void B() {
        TextPaint paint = getPaint();
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        d textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.i(getContext(), paint, this.f2202x);
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

    @Override // a2.a.InterfaceC0002a
    public void a() {
        k(this.f2198t);
        requestLayout();
        if (Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return m(motionEvent) || this.f2199u.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.f2199u.w(keyEvent) || this.f2199u.A() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        a2.a aVar = this.f2187i;
        if ((aVar == null || !aVar.q1()) ? false : this.f2187i.m2(j())) {
            invalidate();
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f2188j;
        return insetDrawable == null ? this.f2187i : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.I0();
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.J0();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.K0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return Math.max(0.0f, aVar.L0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f2187i;
    }

    public float getChipEndPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.M0();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.N0();
        }
        return null;
    }

    public float getChipIconSize() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.O0();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.P0();
        }
        return null;
    }

    public float getChipMinHeight() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.Q0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.R0();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.S0();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        a2.a aVar = this.f2187i;
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
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.U0();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.V0();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.W0();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.X0();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.Y0();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.a1();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.c1();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.f2199u.A() == 1 || this.f2199u.x() == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public h getHideMotionSpec() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.d1();
        }
        return null;
    }

    public float getIconEndPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.e1();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.f1();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.g1();
        }
        return null;
    }

    public m getShapeAppearanceModel() {
        return this.f2187i.D();
    }

    public h getShowMotionSpec() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.h1();
        }
        return null;
    }

    public float getTextEndPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.k1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            return aVar.l1();
        }
        return 0.0f;
    }

    public final void i(a2.a aVar) {
        aVar.q2(this);
    }

    public final int[] j() {
        int i3 = 0;
        int i4 = isEnabled() ? 1 : 0;
        if (this.f2195q) {
            i4++;
        }
        if (this.f2194p) {
            i4++;
        }
        if (this.f2193o) {
            i4++;
        }
        if (isChecked()) {
            i4++;
        }
        int[] iArr = new int[i4];
        if (isEnabled()) {
            iArr[0] = 16842910;
            i3 = 1;
        }
        if (this.f2195q) {
            iArr[i3] = 16842908;
            i3++;
        }
        if (this.f2194p) {
            iArr[i3] = 16843623;
            i3++;
        }
        if (this.f2193o) {
            iArr[i3] = 16842919;
            i3++;
        }
        if (isChecked()) {
            iArr[i3] = 16842913;
        }
        return iArr;
    }

    public boolean k(int i3) {
        this.f2198t = i3;
        if (!v()) {
            if (this.f2188j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int iMax = Math.max(0, i3 - this.f2187i.getIntrinsicHeight());
        int iMax2 = Math.max(0, i3 - this.f2187i.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.f2188j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int i4 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i5 = iMax > 0 ? iMax / 2 : 0;
        if (this.f2188j != null) {
            Rect rect = new Rect();
            this.f2188j.getPadding(rect);
            if (rect.top == i5 && rect.bottom == i5 && rect.left == i4 && rect.right == i4) {
                y();
                return true;
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (getMinHeight() != i3) {
                setMinHeight(i3);
            }
            if (getMinWidth() != i3) {
                setMinWidth(i3);
            }
        } else {
            setMinHeight(i3);
            setMinWidth(i3);
        }
        q(i4, i5, i4, i5);
        y();
        return true;
    }

    public final void l() {
        if (getBackgroundDrawable() == this.f2188j && this.f2187i.getCallback() == null) {
            this.f2187i.setCallback(this.f2188j);
        }
    }

    @SuppressLint({"PrivateApi"})
    public final boolean m(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = p0.a.class.getDeclaredField("p");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.f2199u)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = p0.a.class.getDeclaredMethod("X", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.f2199u, Integer.MIN_VALUE);
                    return true;
                }
            } catch (IllegalAccessException e3) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e3);
            } catch (NoSuchFieldException e4) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e4);
            } catch (NoSuchMethodException e5) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e5);
            } catch (InvocationTargetException e6) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e6);
            }
        }
        return false;
    }

    public final boolean n() {
        a2.a aVar = this.f2187i;
        return (aVar == null || aVar.U0() == null) ? false : true;
    }

    public final void o(Context context, AttributeSet attributeSet, int i3) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Chip, i3, f2183e, new int[0]);
        this.f2196r = typedArrayH.getBoolean(R$styleable.Chip_ensureMinTouchTargetSize, false);
        this.f2198t = (int) Math.ceil(typedArrayH.getDimension(R$styleable.Chip_chipMinTouchTargetSize, (float) Math.ceil(i2.p.c(getContext(), 48))));
        typedArrayH.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.f2187i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i3) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f2185g);
        }
        if (r()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f2186h);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z3, int i3, Rect rect) {
        super.onFocusChanged(z3, i3, rect);
        this.f2199u.K(z3, i3, rect);
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
            m0.c.D0(accessibilityNodeInfo).e0(c.C0036c.a(chipGroup.b(this), 1, chipGroup.c() ? chipGroup.n(this) : -1, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i3) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i3) {
        super.onRtlPropertiesChanged(i3);
        if (this.f2197s != i3) {
            this.f2197s = i3;
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
            boolean r0 = r5.f2193o
            if (r0 == 0) goto L40
            if (r1 != 0) goto L3e
            r5.setCloseIconPressed(r2)
            goto L3e
        L2b:
            boolean r0 = r5.f2193o
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

    public final void q(int i3, int i4, int i5, int i6) {
        this.f2188j = new InsetDrawable((Drawable) this.f2187i, i3, i4, i5, i6);
    }

    public boolean r() {
        a2.a aVar = this.f2187i;
        return aVar != null && aVar.p1();
    }

    public boolean s() {
        a2.a aVar = this.f2187i;
        return aVar != null && aVar.r1();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f2189k) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i3) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f2189k) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i3) {
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

    public void setCheckable(boolean z3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.y1(z3);
        }
    }

    public void setCheckableResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.z1(i3);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z3) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        a2.a aVar = this.f2187i;
        if (aVar == null) {
            this.f2192n = z3;
            return;
        }
        if (aVar.p1()) {
            boolean zIsChecked = isChecked();
            super.setChecked(z3);
            if (zIsChecked == z3 || (onCheckedChangeListener = this.f2191m) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z3);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.A1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z3) {
        setCheckedIconVisible(z3);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i3) {
        setCheckedIconVisible(i3);
    }

    public void setCheckedIconResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.B1(i3);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.C1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.D1(i3);
        }
    }

    public void setCheckedIconVisible(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.E1(i3);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.G1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.H1(i3);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.I1(f3);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.J1(i3);
        }
    }

    public void setChipDrawable(a2.a aVar) {
        a2.a aVar2 = this.f2187i;
        if (aVar2 != aVar) {
            w(aVar2);
            this.f2187i = aVar;
            aVar.B2(false);
            i(this.f2187i);
            k(this.f2198t);
        }
    }

    public void setChipEndPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.K1(f3);
        }
    }

    public void setChipEndPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.L1(i3);
        }
    }

    public void setChipIcon(Drawable drawable) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.M1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z3) {
        setChipIconVisible(z3);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i3) {
        setChipIconVisible(i3);
    }

    public void setChipIconResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.N1(i3);
        }
    }

    public void setChipIconSize(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.O1(f3);
        }
    }

    public void setChipIconSizeResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.P1(i3);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.Q1(colorStateList);
        }
    }

    public void setChipIconTintResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.R1(i3);
        }
    }

    public void setChipIconVisible(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.S1(i3);
        }
    }

    public void setChipMinHeight(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.U1(f3);
        }
    }

    public void setChipMinHeightResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.V1(i3);
        }
    }

    public void setChipStartPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.W1(f3);
        }
    }

    public void setChipStartPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.X1(i3);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.Y1(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.Z1(i3);
        }
    }

    public void setChipStrokeWidth(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.a2(f3);
        }
    }

    public void setChipStrokeWidthResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.b2(i3);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i3) {
        setText(getResources().getString(i3));
    }

    public void setCloseIcon(Drawable drawable) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.d2(drawable);
        }
        x();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.e2(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z3) {
        setCloseIconVisible(z3);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i3) {
        setCloseIconVisible(i3);
    }

    public void setCloseIconEndPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.f2(f3);
        }
    }

    public void setCloseIconEndPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.g2(i3);
        }
    }

    public void setCloseIconResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.h2(i3);
        }
        x();
    }

    public void setCloseIconSize(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.i2(f3);
        }
    }

    public void setCloseIconSizeResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.j2(i3);
        }
    }

    public void setCloseIconStartPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.k2(f3);
        }
    }

    public void setCloseIconStartPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.l2(i3);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.n2(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.o2(i3);
        }
    }

    public void setCloseIconVisible(int i3) {
        setCloseIconVisible(getResources().getBoolean(i3));
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
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i3, int i4, int i5, int i6) {
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i5 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i3, i4, i5, i6);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i3, int i4, int i5, int i6) {
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i5 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i3, i4, i5, i6);
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.W(f3);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f2187i == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.r2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z3) {
        this.f2196r = z3;
        k(this.f2198t);
    }

    @Override // android.widget.TextView
    public void setGravity(int i3) {
        if (i3 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i3);
        }
    }

    public void setHideMotionSpec(h hVar) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.s2(hVar);
        }
    }

    public void setHideMotionSpecResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.t2(i3);
        }
    }

    public void setIconEndPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.u2(f3);
        }
    }

    public void setIconEndPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.v2(i3);
        }
    }

    public void setIconStartPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.w2(f3);
        }
    }

    public void setIconStartPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.x2(i3);
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i3) {
        if (this.f2187i != null && Build.VERSION.SDK_INT >= 17) {
            super.setLayoutDirection(i3);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i3) {
        if (i3 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i3);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i3) {
        if (i3 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i3);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i3) {
        super.setMaxWidth(i3);
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.y2(i3);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i3) {
        if (i3 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i3);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f2191m = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f2190l = onClickListener;
        x();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.z2(colorStateList);
        }
        if (this.f2187i.n1()) {
            return;
        }
        z();
    }

    public void setRippleColorResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.A2(i3);
            if (this.f2187i.n1()) {
                return;
            }
            z();
        }
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        this.f2187i.setShapeAppearanceModel(mVar);
    }

    public void setShowMotionSpec(h hVar) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.C2(hVar);
        }
    }

    public void setShowMotionSpecResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.D2(i3);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z3) {
        if (!z3) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z3);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        a2.a aVar = this.f2187i;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(aVar.M2() ? null : charSequence, bufferType);
        a2.a aVar2 = this.f2187i;
        if (aVar2 != null) {
            aVar2.E2(charSequence);
        }
    }

    public void setTextAppearance(d dVar) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.F2(dVar);
        }
        B();
    }

    public void setTextAppearanceResource(int i3) {
        setTextAppearance(getContext(), i3);
    }

    public void setTextEndPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.H2(f3);
        }
    }

    public void setTextEndPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.I2(i3);
        }
    }

    public void setTextStartPadding(float f3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.J2(f3);
        }
    }

    public void setTextStartPaddingResource(int i3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.K2(i3);
        }
    }

    public boolean t() {
        boolean z3 = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.f2190l;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z3 = true;
        }
        this.f2199u.W(1, 1);
        return z3;
    }

    public final void u() {
        if (this.f2188j != null) {
            this.f2188j = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            y();
        }
    }

    public boolean v() {
        return this.f2196r;
    }

    public final void w(a2.a aVar) {
        if (aVar != null) {
            aVar.q2(null);
        }
    }

    public final void x() {
        if (n() && s() && this.f2190l != null) {
            t.j0(this, this.f2199u);
        } else {
            t.j0(this, null);
        }
    }

    public final void y() {
        if (l2.b.f3717a) {
            z();
            return;
        }
        this.f2187i.L2(true);
        t.m0(this, getBackgroundDrawable());
        A();
        l();
    }

    public final void z() {
        this.f2189k = new RippleDrawable(l2.b.d(this.f2187i.g1()), getBackgroundDrawable(), null);
        this.f2187i.L2(false);
        t.m0(this, this.f2189k);
        A();
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipStyle);
    }

    public void setCloseIconVisible(boolean z3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.p2(z3);
        }
        x();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2183e;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2200v = new Rect();
        this.f2201w = new RectF();
        this.f2202x = new a();
        Context context2 = getContext();
        C(attributeSet);
        a2.a aVarY0 = a2.a.y0(context2, attributeSet, i3, i4);
        o(context2, attributeSet, i3);
        setChipDrawable(aVarY0);
        aVarY0.W(t.u(this));
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.Chip, i3, i4, new int[0]);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(k2.c.a(context2, typedArrayH, R$styleable.Chip_android_textColor));
        }
        boolean zHasValue = typedArrayH.hasValue(R$styleable.Chip_shapeAppearance);
        typedArrayH.recycle();
        this.f2199u = new c(this);
        x();
        if (!zHasValue) {
            p();
        }
        setChecked(this.f2192n);
        setText(aVarY0.i1());
        setEllipsize(aVarY0.c1());
        B();
        if (!this.f2187i.M2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        A();
        if (v()) {
            setMinHeight(this.f2198t);
        }
        this.f2197s = t.z(this);
    }

    public void setCheckedIconVisible(boolean z3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.F1(z3);
        }
    }

    public void setChipIconVisible(boolean z3) {
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.T1(z3);
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
    public void setTextAppearance(Context context, int i3) {
        super.setTextAppearance(context, i3);
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.G2(i3);
        }
        B();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i3) {
        super.setTextAppearance(i3);
        a2.a aVar = this.f2187i;
        if (aVar != null) {
            aVar.G2(i3);
        }
        B();
    }
}
