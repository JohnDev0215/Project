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
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.google.android.material.R$attr;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.ea;
import defpackage.fl;
import defpackage.ih;
import defpackage.ik;
import defpackage.jj;
import defpackage.mk;
import defpackage.oj;
import defpackage.pk;
import defpackage.sj;
import defpackage.t9;
import defpackage.tj;
import defpackage.vj;
import defpackage.xa;
import defpackage.xh;
import defpackage.xj;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements xh.a, pk {
    public static final int e = R$style.Widget_MaterialComponents_Chip_Action;
    public static final Rect f = new Rect();
    public static final int[] g = {R.attr.state_selected};
    public static final int[] h = {R.attr.state_checkable};
    public xh i;
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
    public final vj x;

    public class a extends vj {
        public a() {
        }

        @Override // defpackage.vj
        public void a(int i) {
        }

        @Override // defpackage.vj
        public void b(Typeface typeface, boolean z) {
            Chip chip = Chip.this;
            chip.setText(chip.i.M2() ? Chip.this.i.i1() : Chip.this.getText());
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
            if (Chip.this.i != null) {
                Chip.this.i.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    public class c extends xa {
        public c(Chip chip) {
            super(chip);
        }

        @Override // defpackage.xa
        public int B(float f, float f2) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f, f2)) ? 1 : 0;
        }

        @Override // defpackage.xa
        public void C(List<Integer> list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.l != null) {
                list.add(1);
            }
        }

        @Override // defpackage.xa
        public boolean L(int i, int i2, Bundle bundle) {
            if (i2 != 16) {
                return false;
            }
            if (i == 0) {
                return Chip.this.performClick();
            }
            if (i == 1) {
                return Chip.this.t();
            }
            return false;
        }

        @Override // defpackage.xa
        public void O(ea eaVar) {
            eaVar.Z(Chip.this.r());
            eaVar.c0(Chip.this.isClickable());
            if (Chip.this.r() || Chip.this.isClickable()) {
                eaVar.b0(Chip.this.r() ? "android.widget.CompoundButton" : "android.widget.Button");
            } else {
                eaVar.b0("android.view.View");
            }
            CharSequence text = Chip.this.getText();
            if (Build.VERSION.SDK_INT >= 23) {
                eaVar.A0(text);
            } else {
                eaVar.f0(text);
            }
        }

        @Override // defpackage.xa
        public void P(int i, ea eaVar) {
            if (i != 1) {
                eaVar.f0("");
                eaVar.W(Chip.f);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                eaVar.f0(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                Context context = Chip.this.getContext();
                int i2 = R$string.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                eaVar.f0(context.getString(i2, objArr).trim());
            }
            eaVar.W(Chip.this.getCloseIconTouchBoundsInt());
            eaVar.b(ea.a.e);
            eaVar.g0(Chip.this.isEnabled());
        }

        @Override // defpackage.xa
        public void Q(int i, boolean z) {
            if (i == 1) {
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
            this.i.b1(this.w);
        }
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.v.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.v;
    }

    private tj getTextAppearance() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.j1();
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
        xh xhVar;
        if (TextUtils.isEmpty(getText()) || (xhVar = this.i) == null) {
            return;
        }
        int iM0 = (int) (xhVar.M0() + this.i.k1() + this.i.t0());
        int iR0 = (int) (this.i.R0() + this.i.l1() + this.i.p0());
        if (this.j != null) {
            Rect rect = new Rect();
            this.j.getPadding(rect);
            iR0 += rect.left;
            iM0 += rect.right;
        }
        t9.w0(this, iR0, getPaddingTop(), iM0, getPaddingBottom());
    }

    public final void B() {
        TextPaint paint = getPaint();
        xh xhVar = this.i;
        if (xhVar != null) {
            paint.drawableState = xhVar.getState();
        }
        tj textAppearance = getTextAppearance();
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

    @Override // xh.a
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
        xh xhVar = this.i;
        if ((xhVar == null || !xhVar.q1()) ? false : this.i.m2(j())) {
            invalidate();
        }
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.j;
        return insetDrawable == null ? this.i : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.I0();
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.J0();
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.K0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return Math.max(0.0f, xhVar.L0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.i;
    }

    public float getChipEndPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.M0();
        }
        return 0.0f;
    }

    public Drawable getChipIcon() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.N0();
        }
        return null;
    }

    public float getChipIconSize() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.O0();
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.P0();
        }
        return null;
    }

    public float getChipMinHeight() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.Q0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.R0();
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.S0();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.T0();
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.U0();
        }
        return null;
    }

    public CharSequence getCloseIconContentDescription() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.V0();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.W0();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.X0();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.Y0();
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.a1();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.c1();
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

    public ih getHideMotionSpec() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.d1();
        }
        return null;
    }

    public float getIconEndPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.e1();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.f1();
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.g1();
        }
        return null;
    }

    public mk getShapeAppearanceModel() {
        return this.i.D();
    }

    public ih getShowMotionSpec() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.h1();
        }
        return null;
    }

    public float getTextEndPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.k1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        xh xhVar = this.i;
        if (xhVar != null) {
            return xhVar.l1();
        }
        return 0.0f;
    }

    public final void i(xh xhVar) {
        xhVar.q2(this);
    }

    public final int[] j() {
        int i = 0;
        int i2 = isEnabled() ? 1 : 0;
        if (this.q) {
            i2++;
        }
        if (this.p) {
            i2++;
        }
        if (this.o) {
            i2++;
        }
        if (isChecked()) {
            i2++;
        }
        int[] iArr = new int[i2];
        if (isEnabled()) {
            iArr[0] = 16842910;
            i = 1;
        }
        if (this.q) {
            iArr[i] = 16842908;
            i++;
        }
        if (this.p) {
            iArr[i] = 16843623;
            i++;
        }
        if (this.o) {
            iArr[i] = 16842919;
            i++;
        }
        if (isChecked()) {
            iArr[i] = 16842913;
        }
        return iArr;
    }

    public boolean k(int i) {
        this.t = i;
        if (!v()) {
            if (this.j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int iMax = Math.max(0, i - this.i.getIntrinsicHeight());
        int iMax2 = Math.max(0, i - this.i.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.j != null) {
                u();
            } else {
                y();
            }
            return false;
        }
        int i2 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i3 = iMax > 0 ? iMax / 2 : 0;
        if (this.j != null) {
            Rect rect = new Rect();
            this.j.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                y();
                return true;
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (getMinHeight() != i) {
                setMinHeight(i);
            }
            if (getMinWidth() != i) {
                setMinWidth(i);
            }
        } else {
            setMinHeight(i);
            setMinWidth(i);
        }
        q(i2, i3, i2, i3);
        y();
        return true;
    }

    public final void l() {
        if (getBackgroundDrawable() == this.j && this.i.getCallback() == null) {
            this.i.setCallback(this.j);
        }
    }

    @SuppressLint({"PrivateApi"})
    public final boolean m(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 10) {
            try {
                Field declaredField = xa.class.getDeclaredField("p");
                declaredField.setAccessible(true);
                if (((Integer) declaredField.get(this.u)).intValue() != Integer.MIN_VALUE) {
                    Method declaredMethod = xa.class.getDeclaredMethod("X", Integer.TYPE);
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
        xh xhVar = this.i;
        return (xhVar == null || xhVar.U0() == null) ? false : true;
    }

    public final void o(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayH = jj.h(context, attributeSet, R$styleable.Chip, i, e, new int[0]);
        this.r = typedArrayH.getBoolean(R$styleable.Chip_ensureMinTouchTargetSize, false);
        this.t = (int) Math.ceil(typedArrayH.getDimension(R$styleable.Chip_chipMinTouchTargetSize, (float) Math.ceil(oj.c(getContext(), 48))));
        typedArrayH.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ik.f(this, this.i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, g);
        }
        if (r()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, h);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        this.u.K(z, i, rect);
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
            ea.D0(accessibilityNodeInfo).e0(ea.c.a(chipGroup.b(this), 1, chipGroup.c() ? chipGroup.n(this) : -1, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.s != i) {
            this.s = i;
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

    public final void q(int i, int i2, int i3, int i4) {
        this.j = new InsetDrawable((Drawable) this.i, i, i2, i3, i4);
    }

    public boolean r() {
        xh xhVar = this.i;
        return xhVar != null && xhVar.p1();
    }

    public boolean s() {
        xh xhVar = this.i;
        return xhVar != null && xhVar.r1();
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
    public void setBackgroundColor(int i) {
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
    public void setBackgroundResource(int i) {
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
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.y1(z);
        }
    }

    public void setCheckableResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.z1(i);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        xh xhVar = this.i;
        if (xhVar == null) {
            this.n = z;
            return;
        }
        if (xhVar.p1()) {
            boolean zIsChecked = isChecked();
            super.setChecked(z);
            if (zIsChecked == z || (onCheckedChangeListener = this.m) == null) {
                return;
            }
            onCheckedChangeListener.onCheckedChanged(this, z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.A1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i) {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.B1(i);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.C1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.D1(i);
        }
    }

    public void setCheckedIconVisible(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.E1(i);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.G1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.H1(i);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.I1(f2);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.J1(i);
        }
    }

    public void setChipDrawable(xh xhVar) {
        xh xhVar2 = this.i;
        if (xhVar2 != xhVar) {
            w(xhVar2);
            this.i = xhVar;
            xhVar.B2(false);
            i(this.i);
            k(this.t);
        }
    }

    public void setChipEndPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.K1(f2);
        }
    }

    public void setChipEndPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.L1(i);
        }
    }

    public void setChipIcon(Drawable drawable) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.M1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i) {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.N1(i);
        }
    }

    public void setChipIconSize(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.O1(f2);
        }
    }

    public void setChipIconSizeResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.P1(i);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.Q1(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.R1(i);
        }
    }

    public void setChipIconVisible(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.S1(i);
        }
    }

    public void setChipMinHeight(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.U1(f2);
        }
    }

    public void setChipMinHeightResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.V1(i);
        }
    }

    public void setChipStartPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.W1(f2);
        }
    }

    public void setChipStartPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.X1(i);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.Y1(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.Z1(i);
        }
    }

    public void setChipStrokeWidth(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.a2(f2);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.b2(i);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i) {
        setText(getResources().getString(i));
    }

    public void setCloseIcon(Drawable drawable) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.d2(drawable);
        }
        x();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.e2(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.f2(f2);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.g2(i);
        }
    }

    public void setCloseIconResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.h2(i);
        }
        x();
    }

    public void setCloseIconSize(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.i2(f2);
        }
    }

    public void setCloseIconSizeResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.j2(i);
        }
    }

    public void setCloseIconStartPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.k2(f2);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.l2(i);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.n2(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.o2(i);
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
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
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.W(f2);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.i == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.r2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.r = z;
        k(this.t);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(ih ihVar) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.s2(ihVar);
        }
    }

    public void setHideMotionSpecResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.t2(i);
        }
    }

    public void setIconEndPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.u2(f2);
        }
    }

    public void setIconEndPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.v2(i);
        }
    }

    public void setIconStartPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.w2(f2);
        }
    }

    public void setIconStartPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.x2(i);
        }
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.i != null && Build.VERSION.SDK_INT >= 17) {
            super.setLayoutDirection(i);
        }
    }

    @Override // android.widget.TextView
    public void setLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i) {
        super.setMaxWidth(i);
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.y2(i);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i);
    }

    public void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.m = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.l = onClickListener;
        x();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.z2(colorStateList);
        }
        if (this.i.n1()) {
            return;
        }
        z();
    }

    public void setRippleColorResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.A2(i);
            if (this.i.n1()) {
                return;
            }
            z();
        }
    }

    @Override // defpackage.pk
    public void setShapeAppearanceModel(mk mkVar) {
        this.i.setShapeAppearanceModel(mkVar);
    }

    public void setShowMotionSpec(ih ihVar) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.C2(ihVar);
        }
    }

    public void setShowMotionSpecResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.D2(i);
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
        xh xhVar = this.i;
        if (xhVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(xhVar.M2() ? null : charSequence, bufferType);
        xh xhVar2 = this.i;
        if (xhVar2 != null) {
            xhVar2.E2(charSequence);
        }
    }

    public void setTextAppearance(tj tjVar) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.F2(tjVar);
        }
        B();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.H2(f2);
        }
    }

    public void setTextEndPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.I2(i);
        }
    }

    public void setTextStartPadding(float f2) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.J2(f2);
        }
    }

    public void setTextStartPaddingResource(int i) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.K2(i);
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

    public final void w(xh xhVar) {
        if (xhVar != null) {
            xhVar.q2(null);
        }
    }

    public final void x() {
        if (n() && s() && this.l != null) {
            t9.j0(this, this.u);
        } else {
            t9.j0(this, null);
        }
    }

    public final void y() {
        if (xj.a) {
            z();
            return;
        }
        this.i.L2(true);
        t9.m0(this, getBackgroundDrawable());
        A();
        l();
    }

    public final void z() {
        this.k = new RippleDrawable(xj.d(this.i.g1()), getBackgroundDrawable(), null);
        this.i.L2(false);
        t9.m0(this, this.k);
        A();
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipStyle);
    }

    public void setCloseIconVisible(boolean z) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.p2(z);
        }
        x();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i) {
        int i2 = e;
        super(fl.c(context, attributeSet, i, i2), attributeSet, i);
        this.v = new Rect();
        this.w = new RectF();
        this.x = new a();
        Context context2 = getContext();
        C(attributeSet);
        xh xhVarY0 = xh.y0(context2, attributeSet, i, i2);
        o(context2, attributeSet, i);
        setChipDrawable(xhVarY0);
        xhVarY0.W(t9.u(this));
        TypedArray typedArrayH = jj.h(context2, attributeSet, R$styleable.Chip, i, i2, new int[0]);
        if (Build.VERSION.SDK_INT < 23) {
            setTextColor(sj.a(context2, typedArrayH, R$styleable.Chip_android_textColor));
        }
        boolean zHasValue = typedArrayH.hasValue(R$styleable.Chip_shapeAppearance);
        typedArrayH.recycle();
        this.u = new c(this);
        x();
        if (!zHasValue) {
            p();
        }
        setChecked(this.n);
        setText(xhVarY0.i1());
        setEllipsize(xhVarY0.c1());
        B();
        if (!this.i.M2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        A();
        if (v()) {
            setMinHeight(this.t);
        }
        this.s = t9.z(this);
    }

    public void setCheckedIconVisible(boolean z) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.F1(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.T1(z);
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
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.G2(i);
        }
        B();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        xh xhVar = this.i;
        if (xhVar != null) {
            xhVar.G2(i);
        }
        B();
    }
}
