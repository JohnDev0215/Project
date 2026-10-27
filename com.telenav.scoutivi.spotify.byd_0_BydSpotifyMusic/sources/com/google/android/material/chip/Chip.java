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
import androidx.appcompat.widget.f;
import b3.d;
import com.google.android.material.chip.a;
import e3.n;
import i0.u;
import j0.c;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import o2.j;
import o2.k;
import o2.l;
import p2.h;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends f implements a.InterfaceC0047a, n {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.google.android.material.chip.a f4304i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private InsetDrawable f4305j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private RippleDrawable f4306k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private View.OnClickListener f4307l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private CompoundButton.OnCheckedChangeListener f4308m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f4309n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f4310o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f4311p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4312q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f4313r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f4314s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f4315t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final c f4316u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final Rect f4317v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final RectF f4318w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final b3.f f4319x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static final int f4302y = k.Widget_MaterialComponents_Chip_Action;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final Rect f4303z = new Rect();
    private static final int[] A = {R.attr.state_selected};
    private static final int[] B = {R.attr.state_checkable};

    class a extends b3.f {
        a() {
        }

        @Override // b3.f
        public void a(int i7) {
        }

        @Override // b3.f
        public void b(Typeface typeface, boolean z6) {
            Chip chip = Chip.this;
            chip.setText(chip.f4304i.J2() ? Chip.this.f4304i.f1() : Chip.this.getText());
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    }

    class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        @TargetApi(21)
        public void getOutline(View view, Outline outline) {
            if (Chip.this.f4304i != null) {
                Chip.this.f4304i.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    private class c extends m0.a {
        c(Chip chip) {
            super(chip);
        }

        @Override // m0.a
        protected int B(float f7, float f8) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f7, f8)) ? 1 : 0;
        }

        @Override // m0.a
        protected void C(List<Integer> list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.f4307l != null) {
                list.add(1);
            }
        }

        @Override // m0.a
        protected boolean J(int i7, int i8, Bundle bundle) {
            if (i8 != 16) {
                return false;
            }
            if (i7 == 0) {
                return Chip.this.performClick();
            }
            if (i7 == 1) {
                return Chip.this.t();
            }
            return false;
        }

        @Override // m0.a
        protected void M(j0.c cVar) {
            cVar.N(Chip.this.r());
            cVar.Q(Chip.this.isClickable());
            if (Chip.this.r() || Chip.this.isClickable()) {
                cVar.P(Chip.this.r() ? "android.widget.CompoundButton" : "android.widget.Button");
            } else {
                cVar.P("android.view.View");
            }
            cVar.l0(Chip.this.getText());
        }

        @Override // m0.a
        protected void N(int i7, j0.c cVar) {
            if (i7 != 1) {
                cVar.T("");
                cVar.L(Chip.f4303z);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                cVar.T(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i8 = j.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                cVar.T(context.getString(i8, objArr).trim());
            }
            cVar.L(Chip.this.getCloseIconTouchBoundsInt());
            cVar.b(c.a.f7117i);
            cVar.U(Chip.this.isEnabled());
        }

        @Override // m0.a
        protected void O(int i7, boolean z6) {
            if (i7 == 1) {
                Chip.this.f4312q = z6;
                Chip.this.refreshDrawableState();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.chipStyle);
    }

    private void A() {
        com.google.android.material.chip.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.f4304i) == null) {
            return;
        }
        int iH0 = (int) (aVar.H0() + this.f4304i.h1() + this.f4304i.o0());
        int iM0 = (int) (this.f4304i.M0() + this.f4304i.i1() + this.f4304i.k0());
        if (this.f4305j != null) {
            Rect rect = new Rect();
            this.f4305j.getPadding(rect);
            iM0 += rect.left;
            iH0 += rect.right;
        }
        u.w0(this, iM0, getPaddingTop(), iH0, getPaddingBottom());
    }

    private void B() {
        TextPaint paint = getPaint();
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        d textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.j(getContext(), paint, this.f4319x);
        }
    }

    private void C(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        this.f4318w.setEmpty();
        if (n() && this.f4307l != null) {
            this.f4304i.W0(this.f4318w);
        }
        return this.f4318w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.f4317v.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.f4317v;
    }

    private d getTextAppearance() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.g1();
        }
        return null;
    }

    private void i(com.google.android.material.chip.a aVar) {
        aVar.n2(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int[] j() {
        ?? IsEnabled = isEnabled();
        int i7 = IsEnabled;
        if (this.f4312q) {
            i7 = IsEnabled + 1;
        }
        int i8 = i7;
        if (this.f4311p) {
            i8 = i7 + 1;
        }
        int i9 = i8;
        if (this.f4310o) {
            i9 = i8 + 1;
        }
        int i10 = i9;
        if (isChecked()) {
            i10 = i9 + 1;
        }
        int[] iArr = new int[i10];
        int i11 = 0;
        if (isEnabled()) {
            iArr[0] = 16842910;
            i11 = 1;
        }
        if (this.f4312q) {
            iArr[i11] = 16842908;
            i11++;
        }
        if (this.f4311p) {
            iArr[i11] = 16843623;
            i11++;
        }
        if (this.f4310o) {
            iArr[i11] = 16842919;
            i11++;
        }
        if (isChecked()) {
            iArr[i11] = 16842913;
        }
        return iArr;
    }

    private void l() {
        if (getBackgroundDrawable() == this.f4305j && this.f4304i.getCallback() == null) {
            this.f4304i.setCallback(this.f4305j);
        }
    }

    @SuppressLint({"PrivateApi"})
    private boolean m(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = m0.a.class.getDeclaredField("m");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.f4316u)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = m0.a.class.getDeclaredMethod("V", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.f4316u, Integer.MIN_VALUE);
                    return true;
                }
            } catch (IllegalAccessException e7) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e7);
            } catch (NoSuchFieldException e8) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e8);
            } catch (NoSuchMethodException e9) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e9);
            } catch (InvocationTargetException e10) {
                Log.e("Chip", "Unable to send Accessibility Exit event", e10);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        com.google.android.material.chip.a aVar = this.f4304i;
        return (aVar == null || aVar.P0() == null) ? false : true;
    }

    private void o(Context context, AttributeSet attributeSet, int i7) {
        TypedArray typedArrayH = com.google.android.material.internal.j.h(context, attributeSet, l.Chip, i7, f4302y, new int[0]);
        this.f4313r = typedArrayH.getBoolean(l.Chip_ensureMinTouchTargetSize, false);
        this.f4315t = (int) Math.ceil(typedArrayH.getDimension(l.Chip_chipMinTouchTargetSize, (float) Math.ceil(com.google.android.material.internal.l.b(getContext(), 48))));
        typedArrayH.recycle();
    }

    private void p() {
        setOutlineProvider(new b());
    }

    private void q(int i7, int i8, int i9, int i10) {
        this.f4305j = new InsetDrawable((Drawable) this.f4304i, i7, i8, i9, i10);
    }

    private void setCloseIconHovered(boolean z6) {
        if (this.f4311p != z6) {
            this.f4311p = z6;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z6) {
        if (this.f4310o != z6) {
            this.f4310o = z6;
            refreshDrawableState();
        }
    }

    private void u() {
        if (this.f4305j != null) {
            this.f4305j = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            y();
        }
    }

    private void w(com.google.android.material.chip.a aVar) {
        if (aVar != null) {
            aVar.n2(null);
        }
    }

    private void x() {
        if (n() && s() && this.f4307l != null) {
            u.k0(this, this.f4316u);
        } else {
            u.k0(this, null);
        }
    }

    private void y() {
        if (c3.b.f3589a) {
            z();
            return;
        }
        this.f4304i.I2(true);
        u.n0(this, getBackgroundDrawable());
        A();
        l();
    }

    private void z() {
        this.f4306k = new RippleDrawable(c3.b.d(this.f4304i.d1()), getBackgroundDrawable(), null);
        this.f4304i.I2(false);
        u.n0(this, this.f4306k);
        A();
    }

    @Override // com.google.android.material.chip.a.InterfaceC0047a
    public void a() {
        k(this.f4315t);
        requestLayout();
        invalidateOutline();
    }

    @Override // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return m(motionEvent) || this.f4316u.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.f4316u.w(keyEvent) || this.f4316u.A() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.f, android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        com.google.android.material.chip.a aVar = this.f4304i;
        if ((aVar == null || !aVar.n1()) ? false : this.f4304i.j2(j())) {
            invalidate();
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f4305j;
        return insetDrawable == null ? this.f4304i : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.D0();
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.E0();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.F0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return Math.max(0.0f, aVar.G0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f4304i;
    }

    public float getChipEndPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.H0();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.I0();
        }
        return null;
    }

    public float getChipIconSize() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.J0();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.K0();
        }
        return null;
    }

    public float getChipMinHeight() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.L0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.M0();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.N0();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.O0();
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.P0();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.Q0();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.R0();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.S0();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.T0();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.V0();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.Z0();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.f4316u.A() == 1 || this.f4316u.x() == 1) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    public h getHideMotionSpec() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.a1();
        }
        return null;
    }

    public float getIconEndPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.b1();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.c1();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.d1();
        }
        return null;
    }

    public e3.k getShapeAppearanceModel() {
        return this.f4304i.C();
    }

    public h getShowMotionSpec() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.e1();
        }
        return null;
    }

    public float getTextEndPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.h1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            return aVar.i1();
        }
        return 0.0f;
    }

    public boolean k(int i7) {
        this.f4315t = i7;
        if (!v()) {
            if (this.f4305j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int iMax = Math.max(0, i7 - this.f4304i.getIntrinsicHeight());
        int iMax2 = Math.max(0, i7 - this.f4304i.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.f4305j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int i8 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i9 = iMax > 0 ? iMax / 2 : 0;
        if (this.f4305j != null) {
            Rect rect = new Rect();
            this.f4305j.getPadding(rect);
            if (rect.top == i9 && rect.bottom == i9 && rect.left == i8 && rect.right == i8) {
                y();
                return true;
            }
        }
        if (getMinHeight() != i7) {
            setMinHeight(i7);
        }
        if (getMinWidth() != i7) {
            setMinWidth(i7);
        }
        q(i8, i9, i8, i9);
        y();
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        e3.h.f(this, this.f4304i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, A);
        }
        if (r()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, B);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        this.f4316u.I(z6, i7, rect);
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
            j0.c.p0(accessibilityNodeInfo).S(c.C0090c.a(chipGroup.b(this), 1, chipGroup.c() ? chipGroup.o(this) : -1, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i7) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        if (this.f4314s != i7) {
            this.f4314s = i7;
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
            boolean r0 = r5.f4310o
            if (r0 == 0) goto L40
            if (r1 != 0) goto L3e
            r5.setCloseIconPressed(r2)
            goto L3e
        L2b:
            boolean r0 = r5.f4310o
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

    public boolean r() {
        com.google.android.material.chip.a aVar = this.f4304i;
        return aVar != null && aVar.m1();
    }

    public boolean s() {
        com.google.android.material.chip.a aVar = this.f4304i;
        return aVar != null && aVar.o1();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f4306k) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.f, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f4306k) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.f, android.view.View
    public void setBackgroundResource(int i7) {
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

    public void setCheckable(boolean z6) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.v1(z6);
        }
    }

    public void setCheckableResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.w1(i7);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z6) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar == null) {
            this.f4309n = z6;
            return;
        }
        if (aVar.m1()) {
            boolean zIsChecked = isChecked();
            super.setChecked(z6);
            if (zIsChecked == z6 || (onCheckedChangeListener = this.f4308m) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z6);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.x1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z6) {
        setCheckedIconVisible(z6);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i7) {
        setCheckedIconVisible(i7);
    }

    public void setCheckedIconResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.y1(i7);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.z1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.A1(i7);
        }
    }

    public void setCheckedIconVisible(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.B1(i7);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.D1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.E1(i7);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.F1(f7);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.G1(i7);
        }
    }

    public void setChipDrawable(com.google.android.material.chip.a aVar) {
        com.google.android.material.chip.a aVar2 = this.f4304i;
        if (aVar2 != aVar) {
            w(aVar2);
            this.f4304i = aVar;
            aVar.y2(false);
            i(this.f4304i);
            k(this.f4315t);
        }
    }

    public void setChipEndPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.H1(f7);
        }
    }

    public void setChipEndPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.I1(i7);
        }
    }

    public void setChipIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.J1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z6) {
        setChipIconVisible(z6);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i7) {
        setChipIconVisible(i7);
    }

    public void setChipIconResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.K1(i7);
        }
    }

    public void setChipIconSize(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.L1(f7);
        }
    }

    public void setChipIconSizeResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.M1(i7);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.N1(colorStateList);
        }
    }

    public void setChipIconTintResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.O1(i7);
        }
    }

    public void setChipIconVisible(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.P1(i7);
        }
    }

    public void setChipMinHeight(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.R1(f7);
        }
    }

    public void setChipMinHeightResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.S1(i7);
        }
    }

    public void setChipStartPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.T1(f7);
        }
    }

    public void setChipStartPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.U1(i7);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.V1(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.W1(i7);
        }
    }

    public void setChipStrokeWidth(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.X1(f7);
        }
    }

    public void setChipStrokeWidthResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.Y1(i7);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i7) {
        setText(getResources().getString(i7));
    }

    public void setCloseIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.a2(drawable);
        }
        x();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.b2(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z6) {
        setCloseIconVisible(z6);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i7) {
        setCloseIconVisible(i7);
    }

    public void setCloseIconEndPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.c2(f7);
        }
    }

    public void setCloseIconEndPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.d2(i7);
        }
    }

    public void setCloseIconResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.e2(i7);
        }
        x();
    }

    public void setCloseIconSize(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.f2(f7);
        }
    }

    public void setCloseIconSizeResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.g2(i7);
        }
    }

    public void setCloseIconStartPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.h2(f7);
        }
    }

    public void setCloseIconStartPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.i2(i7);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.k2(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.l2(i7);
        }
    }

    public void setCloseIconVisible(int i7) {
        setCloseIconVisible(getResources().getBoolean(i7));
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
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        if (i7 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i9 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i7, i8, i9, i10);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        if (i7 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i9 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i7, i8, i9, i10);
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.W(f7);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f4304i == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.o2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z6) {
        this.f4313r = z6;
        k(this.f4315t);
    }

    @Override // android.widget.TextView
    public void setGravity(int i7) {
        if (i7 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i7);
        }
    }

    public void setHideMotionSpec(h hVar) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.p2(hVar);
        }
    }

    public void setHideMotionSpecResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.q2(i7);
        }
    }

    public void setIconEndPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.r2(f7);
        }
    }

    public void setIconEndPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.s2(i7);
        }
    }

    public void setIconStartPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.t2(f7);
        }
    }

    public void setIconStartPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.u2(i7);
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i7) {
        if (this.f4304i == null) {
            return;
        }
        super.setLayoutDirection(i7);
    }

    @Override // android.widget.TextView
    public void setLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i7);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i7);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i7) {
        super.setMaxWidth(i7);
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.v2(i7);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i7);
    }

    void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f4308m = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f4307l = onClickListener;
        x();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.w2(colorStateList);
        }
        if (this.f4304i.k1()) {
            return;
        }
        z();
    }

    public void setRippleColorResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.x2(i7);
            if (this.f4304i.k1()) {
                return;
            }
            z();
        }
    }

    @Override // e3.n
    public void setShapeAppearanceModel(e3.k kVar) {
        this.f4304i.setShapeAppearanceModel(kVar);
    }

    public void setShowMotionSpec(h hVar) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.z2(hVar);
        }
    }

    public void setShowMotionSpecResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.A2(i7);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z6) {
        if (!z6) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z6);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(aVar.J2() ? null : charSequence, bufferType);
        com.google.android.material.chip.a aVar2 = this.f4304i;
        if (aVar2 != null) {
            aVar2.B2(charSequence);
        }
    }

    public void setTextAppearance(d dVar) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.C2(dVar);
        }
        B();
    }

    public void setTextAppearanceResource(int i7) {
        setTextAppearance(getContext(), i7);
    }

    public void setTextEndPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.E2(f7);
        }
    }

    public void setTextEndPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.F2(i7);
        }
    }

    public void setTextStartPadding(float f7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.G2(f7);
        }
    }

    public void setTextStartPaddingResource(int i7) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.H2(i7);
        }
    }

    public boolean t() {
        boolean z6 = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.f4307l;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z6 = true;
        }
        this.f4316u.U(1, 1);
        return z6;
    }

    public boolean v() {
        return this.f4313r;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i7) {
        int i8 = f4302y;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        this.f4317v = new Rect();
        this.f4318w = new RectF();
        this.f4319x = new a();
        Context context2 = getContext();
        C(attributeSet);
        com.google.android.material.chip.a aVarT0 = com.google.android.material.chip.a.t0(context2, attributeSet, i7, i8);
        o(context2, attributeSet, i7);
        setChipDrawable(aVarT0);
        aVarT0.W(u.t(this));
        TypedArray typedArrayH = com.google.android.material.internal.j.h(context2, attributeSet, l.Chip, i7, i8, new int[0]);
        boolean zHasValue = typedArrayH.hasValue(l.Chip_shapeAppearance);
        typedArrayH.recycle();
        this.f4316u = new c(this);
        x();
        if (!zHasValue) {
            p();
        }
        setChecked(this.f4309n);
        setText(aVarT0.f1());
        setEllipsize(aVarT0.Z0());
        B();
        if (!this.f4304i.J2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        A();
        if (v()) {
            setMinHeight(this.f4315t);
        }
        this.f4314s = u.y(this);
    }

    public void setCloseIconVisible(boolean z6) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.m2(z6);
        }
        x();
    }

    public void setCheckedIconVisible(boolean z6) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.C1(z6);
        }
    }

    public void setChipIconVisible(boolean z6) {
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.Q1(z6);
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
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.D2(i7);
        }
        B();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i7) {
        super.setTextAppearance(i7);
        com.google.android.material.chip.a aVar = this.f4304i;
        if (aVar != null) {
            aVar.D2(i7);
        }
        B();
    }
}
