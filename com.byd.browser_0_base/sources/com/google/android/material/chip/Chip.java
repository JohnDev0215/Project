package com.google.android.material.chip;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
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
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.chip.a;
import defpackage.bz;
import defpackage.c83;
import defpackage.dc2;
import defpackage.fj1;
import defpackage.go;
import defpackage.in3;
import defpackage.jc1;
import defpackage.me;
import defpackage.oj2;
import defpackage.om3;
import defpackage.pi0;
import defpackage.r83;
import defpackage.rc1;
import defpackage.s73;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.vd1;
import defpackage.wg;
import defpackage.xt3;
import defpackage.yj2;
import defpackage.yn3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Chip extends AppCompatCheckBox implements a.InterfaceC0033a, yj2, jc1<Chip> {
    public static final int B = R$style.Widget_MaterialComponents_Chip_Action;
    public static final Rect C = new Rect();
    public static final int[] D = {R.attr.state_selected};
    public static final int[] E = {R.attr.state_checkable};
    public final a A;
    public com.google.android.material.chip.a i;
    public InsetDrawable j;
    public RippleDrawable k;
    public View.OnClickListener l;
    public CompoundButton.OnCheckedChangeListener m;
    public jc1.a<Chip> n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public int t;
    public int u;
    public CharSequence v;
    public final b w;
    public boolean x;
    public final Rect y;
    public final RectF z;

    public class b extends pi0 {
        public b(Chip chip) {
            super(chip);
        }

        @Override // defpackage.pi0
        public final int n(float f, float f2) {
            int i = Chip.B;
            Chip chip = Chip.this;
            return (chip.f() && chip.getCloseIconTouchBounds().contains(f, f2)) ? 1 : 0;
        }

        @Override // defpackage.pi0
        public final void o(ArrayList arrayList) {
            boolean z = false;
            arrayList.add(0);
            int i = Chip.B;
            Chip chip = Chip.this;
            if (chip.f()) {
                com.google.android.material.chip.a aVar = chip.i;
                if (aVar != null && aVar.O) {
                    z = true;
                }
                if (!z || chip.l == null) {
                    return;
                }
                arrayList.add(1);
            }
        }

        @Override // defpackage.pi0
        public final boolean s(int i, int i2, Bundle bundle) {
            boolean z = false;
            if (i2 == 16) {
                Chip chip = Chip.this;
                if (i == 0) {
                    return chip.performClick();
                }
                if (i == 1) {
                    chip.playSoundEffect(0);
                    View.OnClickListener onClickListener = chip.l;
                    if (onClickListener != null) {
                        onClickListener.onClick(chip);
                        z = true;
                    }
                    if (chip.x) {
                        chip.w.y(1, 1);
                    }
                }
            }
            return z;
        }

        @Override // defpackage.pi0
        public final void u(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Chip chip = Chip.this;
            com.google.android.material.chip.a aVar = chip.i;
            accessibilityNodeInfoCompat.a.setCheckable(aVar != null && aVar.U);
            accessibilityNodeInfoCompat.o(chip.isClickable());
            accessibilityNodeInfoCompat.n(chip.getAccessibilityClassName());
            accessibilityNodeInfoCompat.t(chip.getText());
        }

        @Override // defpackage.pi0
        public final void v(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (i != 1) {
                accessibilityNodeInfoCompat.r("");
                accessibilityNodeInfoCompat.l(Chip.C);
                return;
            }
            Chip chip = Chip.this;
            CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                accessibilityNodeInfoCompat.r(closeIconContentDescription);
            } else {
                CharSequence text = chip.getText();
                Context context = chip.getContext();
                int i2 = R$string.mtrl_chip_close_icon_content_description;
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(text) ? "" : text;
                accessibilityNodeInfoCompat.r(context.getString(i2, objArr).trim());
            }
            accessibilityNodeInfoCompat.l(chip.getCloseIconTouchBoundsInt());
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.g);
            accessibilityNodeInfoCompat.a.setEnabled(chip.isEnabled());
        }

        @Override // defpackage.pi0
        public final void w(int i, boolean z) {
            if (i == 1) {
                Chip chip = Chip.this;
                chip.r = z;
                chip.refreshDrawableState();
            }
        }
    }

    public Chip(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        RectF rectF = this.z;
        rectF.setEmpty();
        if (f() && this.l != null) {
            com.google.android.material.chip.a aVar = this.i;
            Rect bounds = aVar.getBounds();
            rectF.setEmpty();
            if (aVar.b0()) {
                float f = aVar.h0 + aVar.g0 + aVar.S + aVar.f0 + aVar.e0;
                if (aVar.getLayoutDirection() == 0) {
                    float f2 = bounds.right;
                    rectF.right = f2;
                    rectF.left = f2 - f;
                } else {
                    float f3 = bounds.left;
                    rectF.left = f3;
                    rectF.right = f3 + f;
                }
                rectF.top = bounds.top;
                rectF.bottom = bounds.bottom;
            }
        }
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        int i = (int) closeIconTouchBounds.left;
        int i2 = (int) closeIconTouchBounds.top;
        int i3 = (int) closeIconTouchBounds.right;
        int i4 = (int) closeIconTouchBounds.bottom;
        Rect rect = this.y;
        rect.set(i, i2, i3, i4);
        return rect;
    }

    private s73 getTextAppearance() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.o0.f;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z) {
        if (this.q != z) {
            this.q = z;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if (this.p != z) {
            this.p = z;
            refreshDrawableState();
        }
    }

    @Override // com.google.android.material.chip.a.InterfaceC0033a
    public final void a() {
        d(this.u);
        requestLayout();
        invalidateOutline();
    }

    public final void d(int i) {
        this.u = i;
        if (!this.s) {
            InsetDrawable insetDrawable = this.j;
            if (insetDrawable == null) {
                h();
                return;
            } else {
                if (insetDrawable != null) {
                    this.j = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    h();
                    return;
                }
                return;
            }
        }
        int iMax = Math.max(0, i - ((int) this.i.D));
        int iMax2 = Math.max(0, i - this.i.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            InsetDrawable insetDrawable2 = this.j;
            if (insetDrawable2 == null) {
                h();
                return;
            } else {
                if (insetDrawable2 != null) {
                    this.j = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    h();
                    return;
                }
                return;
            }
        }
        int i2 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i3 = iMax > 0 ? iMax / 2 : 0;
        if (this.j != null) {
            Rect rect = new Rect();
            this.j.getPadding(rect);
            if (rect.top == i3 && rect.bottom == i3 && rect.left == i2 && rect.right == i2) {
                h();
                return;
            }
        }
        if (getMinHeight() != i) {
            setMinHeight(i);
        }
        if (getMinWidth() != i) {
            setMinWidth(i);
        }
        this.j = new InsetDrawable((Drawable) this.i, i2, i3, i2, i3);
        h();
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return !this.x ? super.dispatchHoverEvent(motionEvent) : this.w.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0057  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            boolean r0 = r9.x
            if (r0 != 0) goto L9
            boolean r9 = super.dispatchKeyEvent(r10)
            return r9
        L9:
            com.google.android.material.chip.Chip$b r0 = r9.w
            r0.getClass()
            int r1 = r10.getAction()
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1
            r4 = 0
            if (r1 == r3) goto L84
            int r1 = r10.getKeyCode()
            r5 = 61
            r6 = 0
            if (r1 == r5) goto L6e
            r5 = 66
            if (r1 == r5) goto L57
            switch(r1) {
                case 19: goto L29;
                case 20: goto L29;
                case 21: goto L29;
                case 22: goto L29;
                case 23: goto L57;
                default: goto L28;
            }
        L28:
            goto L84
        L29:
            boolean r7 = r10.hasNoModifiers()
            if (r7 == 0) goto L84
            r7 = 19
            if (r1 == r7) goto L41
            r7 = 21
            if (r1 == r7) goto L3e
            r7 = 22
            if (r1 == r7) goto L43
            r5 = 130(0x82, float:1.82E-43)
            goto L43
        L3e:
            r5 = 17
            goto L43
        L41:
            r5 = 33
        L43:
            int r1 = r10.getRepeatCount()
            int r1 = r1 + r3
            r7 = r4
        L49:
            if (r4 >= r1) goto L55
            boolean r8 = r0.q(r5, r6)
            if (r8 == 0) goto L55
            int r4 = r4 + 1
            r7 = r3
            goto L49
        L55:
            r4 = r7
            goto L84
        L57:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L84
            int r1 = r10.getRepeatCount()
            if (r1 != 0) goto L84
            int r1 = r0.l
            if (r1 == r2) goto L6c
            r4 = 16
            r0.s(r1, r4, r6)
        L6c:
            r4 = r3
            goto L84
        L6e:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L7a
            r1 = 2
            boolean r4 = r0.q(r1, r6)
            goto L84
        L7a:
            boolean r1 = r10.hasModifiers(r3)
            if (r1 == 0) goto L84
            boolean r4 = r0.q(r3, r6)
        L84:
            if (r4 == 0) goto L8b
            int r0 = r0.l
            if (r0 == r2) goto L8b
            return r3
        L8b:
            boolean r9 = super.dispatchKeyEvent(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean, int] */
    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        int i;
        super.drawableStateChanged();
        com.google.android.material.chip.a aVar = this.i;
        boolean zE = false;
        if (aVar != null && com.google.android.material.chip.a.C(aVar.P)) {
            com.google.android.material.chip.a aVar2 = this.i;
            ?? IsEnabled = isEnabled();
            int i2 = IsEnabled;
            if (this.r) {
                i2 = IsEnabled + 1;
            }
            int i3 = i2;
            if (this.q) {
                i3 = i2 + 1;
            }
            int i4 = i3;
            if (this.p) {
                i4 = i3 + 1;
            }
            int i5 = i4;
            if (isChecked()) {
                i5 = i4 + 1;
            }
            int[] iArr = new int[i5];
            if (isEnabled()) {
                iArr[0] = 16842910;
                i = 1;
            } else {
                i = 0;
            }
            if (this.r) {
                iArr[i] = 16842908;
                i++;
            }
            if (this.q) {
                iArr[i] = 16843623;
                i++;
            }
            if (this.p) {
                iArr[i] = 16842919;
                i++;
            }
            if (isChecked()) {
                iArr[i] = 16842913;
            }
            if (!Arrays.equals(aVar2.C0, iArr)) {
                aVar2.C0 = iArr;
                if (aVar2.b0()) {
                    zE = aVar2.E(aVar2.getState(), iArr);
                }
            }
        }
        if (zE) {
            invalidate();
        }
    }

    public final boolean f() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            Object objB = aVar.P;
            if (objB == null) {
                objB = null;
            } else if (objB instanceof xt3) {
                objB = ((xt3) objB).b();
            }
            if (objB != null) {
                return true;
            }
        }
        return false;
    }

    public final void g() {
        if (f()) {
            com.google.android.material.chip.a aVar = this.i;
            if ((aVar != null && aVar.O) && this.l != null) {
                om3.k(this, this.w);
                this.x = true;
                return;
            }
        }
        om3.k(this, null);
        this.x = false;
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.v)) {
            return this.v;
        }
        com.google.android.material.chip.a aVar = this.i;
        if (!(aVar != null && aVar.U)) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        ViewParent parent = getParent();
        return ((parent instanceof ChipGroup) && ((ChipGroup) parent).l.d) ? "android.widget.RadioButton" : "android.widget.Button";
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.j;
        return insetDrawable == null ? this.i : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.W;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.X;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.C;
        }
        return null;
    }

    public float getChipCornerRadius() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return Math.max(0.0f, aVar.A());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.i;
    }

    public float getChipEndPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.h0;
        }
        return 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    public Drawable getChipIcon() {
        Object objB;
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || (objB = aVar.K) == null) {
            return null;
        }
        if (objB instanceof xt3) {
            objB = ((xt3) objB).b();
        }
        return objB;
    }

    public float getChipIconSize() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.M;
        }
        return 0.0f;
    }

    public ColorStateList getChipIconTint() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.L;
        }
        return null;
    }

    public float getChipMinHeight() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.D;
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.a0;
        }
        return 0.0f;
    }

    public ColorStateList getChipStrokeColor() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.F;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.G;
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    public Drawable getCloseIcon() {
        Object objB;
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || (objB = aVar.P) == null) {
            return null;
        }
        if (objB instanceof xt3) {
            objB = ((xt3) objB).b();
        }
        return objB;
    }

    public CharSequence getCloseIconContentDescription() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.T;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.g0;
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.S;
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.f0;
        }
        return 0.0f;
    }

    public ColorStateList getCloseIconTint() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.R;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.G0;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void getFocusedRect(Rect rect) {
        if (this.x) {
            b bVar = this.w;
            if (bVar.l == 1 || bVar.k == 1) {
                rect.set(getCloseIconTouchBoundsInt());
                return;
            }
        }
        super.getFocusedRect(rect);
    }

    public fj1 getHideMotionSpec() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.Z;
        }
        return null;
    }

    public float getIconEndPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.c0;
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.b0;
        }
        return 0.0f;
    }

    public ColorStateList getRippleColor() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.H;
        }
        return null;
    }

    public uj2 getShapeAppearanceModel() {
        return this.i.e.a;
    }

    public fj1 getShowMotionSpec() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.Y;
        }
        return null;
    }

    public float getTextEndPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.e0;
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            return aVar.d0;
        }
        return 0.0f;
    }

    public final void h() {
        this.k = new RippleDrawable(dc2.c(this.i.H), getBackgroundDrawable(), null);
        com.google.android.material.chip.a aVar = this.i;
        if (aVar.D0) {
            aVar.D0 = false;
            aVar.E0 = null;
            aVar.onStateChange(aVar.getState());
        }
        RippleDrawable rippleDrawable = this.k;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(this, rippleDrawable);
        i();
    }

    public final void i() {
        com.google.android.material.chip.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.i) == null) {
            return;
        }
        int iZ = (int) (aVar.z() + aVar.h0 + aVar.e0);
        com.google.android.material.chip.a aVar2 = this.i;
        int iY = (int) (aVar2.y() + aVar2.a0 + aVar2.d0);
        if (this.j != null) {
            Rect rect = new Rect();
            this.j.getPadding(rect);
            iY += rect.left;
            iZ += rect.right;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, iY, paddingTop, iZ, paddingBottom);
    }

    public final void j() {
        TextPaint paint = getPaint();
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        s73 textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.A);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.e0(this, this.i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, D);
        }
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null && aVar.U) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, E);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (this.x) {
            b bVar = this.w;
            int i2 = bVar.l;
            if (i2 != Integer.MIN_VALUE) {
                bVar.j(i2);
            }
            if (z) {
                bVar.q(i, rect);
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        com.google.android.material.chip.a aVar = this.i;
        int i2 = 0;
        accessibilityNodeInfo.setCheckable(aVar != null && aVar.U);
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
            if (chipGroup.g) {
                int i3 = 0;
                while (true) {
                    if (i2 >= chipGroup.getChildCount()) {
                        i3 = -1;
                        break;
                    }
                    if (chipGroup.getChildAt(i2) instanceof Chip) {
                        if (((Chip) chipGroup.getChildAt(i2)) == this) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                    i2++;
                }
                i = i3;
            } else {
                i = -1;
            }
            Object tag = getTag(R$id.row_index_key);
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i, 1, false, isChecked()));
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        if (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    @TargetApi(17)
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.t != i) {
            this.t = i;
            i();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L28;
     */
    @Override // android.widget.TextView, android.view.View
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L49
            if (r0 == r2) goto L2b
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L44
            goto L50
        L21:
            boolean r0 = r5.p
            if (r0 == 0) goto L50
            if (r1 != 0) goto L4e
            r5.setCloseIconPressed(r3)
            goto L4e
        L2b:
            boolean r0 = r5.p
            if (r0 == 0) goto L44
            r5.playSoundEffect(r3)
            android.view.View$OnClickListener r0 = r5.l
            if (r0 == 0) goto L39
            r0.onClick(r5)
        L39:
            boolean r0 = r5.x
            if (r0 == 0) goto L42
            com.google.android.material.chip.Chip$b r0 = r5.w
            r0.y(r2, r2)
        L42:
            r0 = r2
            goto L45
        L44:
            r0 = r3
        L45:
            r5.setCloseIconPressed(r3)
            goto L51
        L49:
            if (r1 == 0) goto L50
            r5.setCloseIconPressed(r2)
        L4e:
            r0 = r2
            goto L51
        L50:
            r0 = r3
        L51:
            if (r0 != 0) goto L5b
            boolean r5 = super.onTouchEvent(r6)
            if (r5 == 0) goto L5a
            goto L5b
        L5a:
            r2 = r3
        L5b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setAccessibilityClassName(CharSequence charSequence) {
        this.v = charSequence;
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
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.F(z);
        }
    }

    public void setCheckableResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.F(aVar.i0.getResources().getBoolean(i));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null) {
            this.o = z;
        } else if (aVar.U) {
            super.setChecked(z);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.G(drawable);
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
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.G(AppCompatResources.a(aVar.i0, i));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.H(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.H(bz.b(aVar.i0, i));
        }
    }

    public void setCheckedIconVisible(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.I(aVar.i0.getResources().getBoolean(i));
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.C == colorStateList) {
            return;
        }
        aVar.C = colorStateList;
        aVar.onStateChange(aVar.getState());
    }

    public void setChipBackgroundColorResource(int i) {
        ColorStateList colorStateListB;
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.C == (colorStateListB = bz.b(aVar.i0, i))) {
            return;
        }
        aVar.C = colorStateListB;
        aVar.onStateChange(aVar.getState());
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.J(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.J(aVar.i0.getResources().getDimension(i));
        }
    }

    public void setChipDrawable(com.google.android.material.chip.a aVar) {
        com.google.android.material.chip.a aVar2 = this.i;
        if (aVar2 != aVar) {
            if (aVar2 != null) {
                aVar2.F0 = new WeakReference<>(null);
            }
            this.i = aVar;
            aVar.H0 = false;
            aVar.F0 = new WeakReference<>(this);
            d(this.u);
        }
    }

    public void setChipEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.h0 == f) {
            return;
        }
        aVar.h0 = f;
        aVar.invalidateSelf();
        aVar.D();
    }

    public void setChipEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            float dimension = aVar.i0.getResources().getDimension(i);
            if (aVar.h0 != dimension) {
                aVar.h0 = dimension;
                aVar.invalidateSelf();
                aVar.D();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.K(drawable);
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
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.K(AppCompatResources.a(aVar.i0, i));
        }
    }

    public void setChipIconSize(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.L(f);
        }
    }

    public void setChipIconSizeResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.L(aVar.i0.getResources().getDimension(i));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.M(colorStateList);
        }
    }

    public void setChipIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.M(bz.b(aVar.i0, i));
        }
    }

    public void setChipIconVisible(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.N(aVar.i0.getResources().getBoolean(i));
        }
    }

    public void setChipMinHeight(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.D == f) {
            return;
        }
        aVar.D = f;
        aVar.invalidateSelf();
        aVar.D();
    }

    public void setChipMinHeightResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            float dimension = aVar.i0.getResources().getDimension(i);
            if (aVar.D != dimension) {
                aVar.D = dimension;
                aVar.invalidateSelf();
                aVar.D();
            }
        }
    }

    public void setChipStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.a0 == f) {
            return;
        }
        aVar.a0 = f;
        aVar.invalidateSelf();
        aVar.D();
    }

    public void setChipStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            float dimension = aVar.i0.getResources().getDimension(i);
            if (aVar.a0 != dimension) {
                aVar.a0 = dimension;
                aVar.invalidateSelf();
                aVar.D();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.O(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.O(bz.b(aVar.i0, i));
        }
    }

    public void setChipStrokeWidth(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.P(f);
        }
    }

    public void setChipStrokeWidthResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.P(aVar.i0.getResources().getDimension(i));
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
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Q(drawable);
        }
        g();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.T == charSequence) {
            return;
        }
        me meVarC = me.c();
        aVar.T = meVarC.d(charSequence, meVarC.c);
        aVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i) {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.R(f);
        }
    }

    public void setCloseIconEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.R(aVar.i0.getResources().getDimension(i));
        }
    }

    public void setCloseIconResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Q(AppCompatResources.a(aVar.i0, i));
        }
        g();
    }

    public void setCloseIconSize(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.S(f);
        }
    }

    public void setCloseIconSizeResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.S(aVar.i0.getResources().getDimension(i));
        }
    }

    public void setCloseIconStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.T(f);
        }
    }

    public void setCloseIconStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.T(aVar.i0.getResources().getDimension(i));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.U(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.U(bz.b(aVar.i0, i));
        }
    }

    public void setCloseIconVisible(int i) {
        setCloseIconVisible(getResources().getBoolean(i));
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        if (i != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i3 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.m(f);
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
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.G0 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.s = z;
        d(this.u);
    }

    @Override // android.widget.TextView
    public void setGravity(int i) {
        if (i != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i);
        }
    }

    public void setHideMotionSpec(fj1 fj1Var) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Z = fj1Var;
        }
    }

    public void setHideMotionSpecResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Z = fj1.b(aVar.i0, i);
        }
    }

    public void setIconEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.W(f);
        }
    }

    public void setIconEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.W(aVar.i0.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.X(f);
        }
    }

    public void setIconStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.X(aVar.i0.getResources().getDimension(i));
        }
    }

    @Override // defpackage.jc1
    public void setInternalOnCheckedChangeListener(jc1.a<Chip> aVar) {
        this.n = aVar;
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        if (this.i == null) {
            return;
        }
        super.setLayoutDirection(i);
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
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.I0 = i;
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i) {
        if (i > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.m = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.l = onClickListener;
        g();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Y(colorStateList);
        }
        if (this.i.D0) {
            return;
        }
        h();
    }

    public void setRippleColorResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Y(bz.b(aVar.i0, i));
            if (this.i.D0) {
                return;
            }
            h();
        }
    }

    @Override // defpackage.yj2
    public void setShapeAppearanceModel(uj2 uj2Var) {
        this.i.setShapeAppearanceModel(uj2Var);
    }

    public void setShowMotionSpec(fj1 fj1Var) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Y = fj1Var;
        }
    }

    public void setShowMotionSpecResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.Y = fj1.b(aVar.i0, i);
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
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(aVar.H0 ? null : charSequence, bufferType);
        com.google.android.material.chip.a aVar2 = this.i;
        if (aVar2 == null || TextUtils.equals(aVar2.I, charSequence)) {
            return;
        }
        aVar2.I = charSequence;
        aVar2.o0.d = true;
        aVar2.invalidateSelf();
        aVar2.D();
    }

    public void setTextAppearance(s73 s73Var) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.o0.b(s73Var, aVar.i0);
        }
        j();
    }

    public void setTextAppearanceResource(int i) {
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.e0 == f) {
            return;
        }
        aVar.e0 = f;
        aVar.invalidateSelf();
        aVar.D();
    }

    public void setTextEndPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            float dimension = aVar.i0.getResources().getDimension(i);
            if (aVar.e0 != dimension) {
                aVar.e0 = dimension;
                aVar.invalidateSelf();
                aVar.D();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            float fApplyDimension = TypedValue.applyDimension(i, f, getResources().getDisplayMetrics());
            c83 c83Var = aVar.o0;
            s73 s73Var = c83Var.f;
            if (s73Var != null) {
                s73Var.k = fApplyDimension;
                c83Var.a.setTextSize(fApplyDimension);
                aVar.a();
            }
        }
        j();
    }

    public void setTextStartPadding(float f) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar == null || aVar.d0 == f) {
            return;
        }
        aVar.d0 = f;
        aVar.invalidateSelf();
        aVar.D();
    }

    public void setTextStartPaddingResource(int i) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            float dimension = aVar.i0.getResources().getDimension(i);
            if (aVar.d0 != dimension) {
                aVar.d0 = dimension;
                aVar.invalidateSelf();
                aVar.D();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipStyle);
    }

    public void setCloseIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.V(z);
        }
        g();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i) {
        int resourceId;
        int i2 = B;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.y = new Rect();
        this.z = new RectF();
        this.A = new a();
        Context context2 = getContext();
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
                Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
            }
            if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") == null) {
                if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") == null) {
                    if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") == null) {
                        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") == null) {
                            if (attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) == 1 && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) == 1 && attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) == 1) {
                                if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                                    Log.w("Chip", "Chip text must be vertically center and start aligned");
                                }
                            } else {
                                throw new UnsupportedOperationException("Chip does not support multi-line text");
                            }
                        } else {
                            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                        }
                    } else {
                        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                    }
                } else {
                    throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
                }
            } else {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            }
        }
        com.google.android.material.chip.a aVar = new com.google.android.material.chip.a(context2, attributeSet, i);
        Context context3 = aVar.i0;
        int[] iArr = R$styleable.Chip;
        TypedArray typedArrayD = r83.d(context3, attributeSet, iArr, i, i2, new int[0]);
        int i3 = R$styleable.Chip_shapeAppearance;
        aVar.J0 = typedArrayD.hasValue(i3);
        int i4 = R$styleable.Chip_chipSurfaceColor;
        Context context4 = aVar.i0;
        ColorStateList colorStateListA = rc1.a(context4, typedArrayD, i4);
        if (aVar.B != colorStateListA) {
            aVar.B = colorStateListA;
            aVar.onStateChange(aVar.getState());
        }
        ColorStateList colorStateListA2 = rc1.a(context4, typedArrayD, R$styleable.Chip_chipBackgroundColor);
        if (aVar.C != colorStateListA2) {
            aVar.C = colorStateListA2;
            aVar.onStateChange(aVar.getState());
        }
        float dimension = typedArrayD.getDimension(R$styleable.Chip_chipMinHeight, 0.0f);
        if (aVar.D != dimension) {
            aVar.D = dimension;
            aVar.invalidateSelf();
            aVar.D();
        }
        int i5 = R$styleable.Chip_chipCornerRadius;
        if (typedArrayD.hasValue(i5)) {
            aVar.J(typedArrayD.getDimension(i5, 0.0f));
        }
        aVar.O(rc1.a(context4, typedArrayD, R$styleable.Chip_chipStrokeColor));
        aVar.P(typedArrayD.getDimension(R$styleable.Chip_chipStrokeWidth, 0.0f));
        aVar.Y(rc1.a(context4, typedArrayD, R$styleable.Chip_rippleColor));
        String text = typedArrayD.getText(R$styleable.Chip_android_text);
        text = text == null ? "" : text;
        boolean zEquals = TextUtils.equals(aVar.I, text);
        c83 c83Var = aVar.o0;
        if (!zEquals) {
            aVar.I = text;
            c83Var.d = true;
            aVar.invalidateSelf();
            aVar.D();
        }
        int i6 = R$styleable.Chip_android_textAppearance;
        s73 s73Var = (!typedArrayD.hasValue(i6) || (resourceId = typedArrayD.getResourceId(i6, 0)) == 0) ? null : new s73(context4, resourceId);
        s73Var.k = typedArrayD.getDimension(R$styleable.Chip_android_textSize, s73Var.k);
        c83Var.b(s73Var, context4);
        int i7 = typedArrayD.getInt(R$styleable.Chip_android_ellipsize, 0);
        if (i7 == 1) {
            aVar.G0 = TextUtils.TruncateAt.START;
        } else if (i7 == 2) {
            aVar.G0 = TextUtils.TruncateAt.MIDDLE;
        } else if (i7 == 3) {
            aVar.G0 = TextUtils.TruncateAt.END;
        }
        aVar.N(typedArrayD.getBoolean(R$styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            aVar.N(typedArrayD.getBoolean(R$styleable.Chip_chipIconEnabled, false));
        }
        aVar.K(rc1.d(context4, typedArrayD, R$styleable.Chip_chipIcon));
        int i8 = R$styleable.Chip_chipIconTint;
        if (typedArrayD.hasValue(i8)) {
            aVar.M(rc1.a(context4, typedArrayD, i8));
        }
        aVar.L(typedArrayD.getDimension(R$styleable.Chip_chipIconSize, -1.0f));
        aVar.V(typedArrayD.getBoolean(R$styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            aVar.V(typedArrayD.getBoolean(R$styleable.Chip_closeIconEnabled, false));
        }
        aVar.Q(rc1.d(context4, typedArrayD, R$styleable.Chip_closeIcon));
        aVar.U(rc1.a(context4, typedArrayD, R$styleable.Chip_closeIconTint));
        aVar.S(typedArrayD.getDimension(R$styleable.Chip_closeIconSize, 0.0f));
        aVar.F(typedArrayD.getBoolean(R$styleable.Chip_android_checkable, false));
        aVar.I(typedArrayD.getBoolean(R$styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            aVar.I(typedArrayD.getBoolean(R$styleable.Chip_checkedIconEnabled, false));
        }
        aVar.G(rc1.d(context4, typedArrayD, R$styleable.Chip_checkedIcon));
        int i9 = R$styleable.Chip_checkedIconTint;
        if (typedArrayD.hasValue(i9)) {
            aVar.H(rc1.a(context4, typedArrayD, i9));
        }
        aVar.Y = fj1.a(context4, typedArrayD, R$styleable.Chip_showMotionSpec);
        aVar.Z = fj1.a(context4, typedArrayD, R$styleable.Chip_hideMotionSpec);
        float dimension2 = typedArrayD.getDimension(R$styleable.Chip_chipStartPadding, 0.0f);
        if (aVar.a0 != dimension2) {
            aVar.a0 = dimension2;
            aVar.invalidateSelf();
            aVar.D();
        }
        aVar.X(typedArrayD.getDimension(R$styleable.Chip_iconStartPadding, 0.0f));
        aVar.W(typedArrayD.getDimension(R$styleable.Chip_iconEndPadding, 0.0f));
        float dimension3 = typedArrayD.getDimension(R$styleable.Chip_textStartPadding, 0.0f);
        if (aVar.d0 != dimension3) {
            aVar.d0 = dimension3;
            aVar.invalidateSelf();
            aVar.D();
        }
        float dimension4 = typedArrayD.getDimension(R$styleable.Chip_textEndPadding, 0.0f);
        if (aVar.e0 != dimension4) {
            aVar.e0 = dimension4;
            aVar.invalidateSelf();
            aVar.D();
        }
        aVar.T(typedArrayD.getDimension(R$styleable.Chip_closeIconStartPadding, 0.0f));
        aVar.R(typedArrayD.getDimension(R$styleable.Chip_closeIconEndPadding, 0.0f));
        float dimension5 = typedArrayD.getDimension(R$styleable.Chip_chipEndPadding, 0.0f);
        if (aVar.h0 != dimension5) {
            aVar.h0 = dimension5;
            aVar.invalidateSelf();
            aVar.D();
        }
        aVar.I0 = typedArrayD.getDimensionPixelSize(R$styleable.Chip_android_maxWidth, Integer.MAX_VALUE);
        typedArrayD.recycle();
        TypedArray typedArrayD2 = r83.d(context2, attributeSet, iArr, i, i2, new int[0]);
        this.s = typedArrayD2.getBoolean(R$styleable.Chip_ensureMinTouchTargetSize, false);
        this.u = (int) Math.ceil(typedArrayD2.getDimension(R$styleable.Chip_chipMinTouchTargetSize, (float) Math.ceil(yn3.b(getContext(), 48))));
        typedArrayD2.recycle();
        setChipDrawable(aVar);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        aVar.m(om3.i.e(this));
        TypedArray typedArrayD3 = r83.d(context2, attributeSet, iArr, i, i2, new int[0]);
        boolean zHasValue = typedArrayD3.hasValue(i3);
        typedArrayD3.recycle();
        this.w = new b(this);
        g();
        if (!zHasValue) {
            setOutlineProvider(new go(this));
        }
        setChecked(this.o);
        setText(aVar.I);
        setEllipsize(aVar.G0);
        j();
        if (!this.i.H0) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        i();
        if (this.s) {
            setMinHeight(this.u);
        }
        this.t = om3.e.c(this);
        super.setOnCheckedChangeListener(new wg(2, this));
    }

    public void setCheckedIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.I(z);
        }
    }

    public void setChipIconVisible(boolean z) {
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            aVar.N(z);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
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
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
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
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            Context context2 = aVar.i0;
            aVar.o0.b(new s73(context2, i), context2);
        }
        j();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        super.setTextAppearance(i);
        com.google.android.material.chip.a aVar = this.i;
        if (aVar != null) {
            Context context = aVar.i0;
            aVar.o0.b(new s73(context, i), context);
        }
        j();
    }

    public class a extends vd1 {
        public a() {
        }

        @Override // defpackage.vd1
        public final void w(Typeface typeface, boolean z) {
            Chip chip = Chip.this;
            com.google.android.material.chip.a aVar = chip.i;
            chip.setText(aVar.H0 ? aVar.I : chip.getText());
            chip.requestLayout();
            chip.invalidate();
        }

        @Override // defpackage.vd1
        public final void s(int i) {
        }
    }
}
