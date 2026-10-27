package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bz;
import defpackage.ic1;
import defpackage.oj2;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.yj2;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, yj2 {
    public static final int[] p = {R.attr.state_checkable};
    public static final int[] q = {R.attr.state_checked};
    public static final int[] r = {R$attr.state_dragged};
    public static final int s = R$style.Widget_MaterialComponents_CardView;
    public final ic1 l;
    public final boolean m;
    public boolean n;
    public boolean o;

    public interface a {
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.l.c.getBounds());
        return rectF;
    }

    public final void g() {
        ic1 ic1Var = this.l;
        RippleDrawable rippleDrawable = ic1Var.o;
        if (rippleDrawable != null) {
            Rect bounds = rippleDrawable.getBounds();
            int i = bounds.bottom;
            ic1Var.o.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
            ic1Var.o.setBounds(bounds.left, bounds.top, bounds.right, i);
        }
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.l.c.e.c;
    }

    public ColorStateList getCardForegroundColor() {
        return this.l.d.e.c;
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.l.j;
    }

    public int getCheckedIconGravity() {
        return this.l.g;
    }

    public int getCheckedIconMargin() {
        return this.l.e;
    }

    public int getCheckedIconSize() {
        return this.l.f;
    }

    public ColorStateList getCheckedIconTint() {
        return this.l.l;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.l.b.bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.l.b.left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.l.b.right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.l.b.top;
    }

    public float getProgress() {
        return this.l.c.e.j;
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.l.c.j();
    }

    public ColorStateList getRippleColor() {
        return this.l.k;
    }

    public uj2 getShapeAppearanceModel() {
        return this.l.m;
    }

    @Deprecated
    public int getStrokeColor() {
        ColorStateList colorStateList = this.l.n;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.l.n;
    }

    public int getStrokeWidth() {
        return this.l.h;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.e0(this, this.l.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 3);
        ic1 ic1Var = this.l;
        if (ic1Var != null && ic1Var.s) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, p);
        }
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, q);
        }
        if (this.o) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, r);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        ic1 ic1Var = this.l;
        accessibilityNodeInfo.setCheckable(ic1Var != null && ic1Var.s);
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.l.e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.m) {
            ic1 ic1Var = this.l;
            if (!ic1Var.r) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                ic1Var.r = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.l.c.n(colorStateList);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        ic1 ic1Var = this.l;
        ic1Var.c.m(ic1Var.a.getCardElevation());
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        sc1 sc1Var = this.l.d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        sc1Var.n(colorStateList);
    }

    public void setCheckable(boolean z) {
        this.l.s = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.n != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.l.f(drawable);
    }

    public void setCheckedIconGravity(int i) {
        ic1 ic1Var = this.l;
        if (ic1Var.g != i) {
            ic1Var.g = i;
            MaterialCardView materialCardView = ic1Var.a;
            ic1Var.e(materialCardView.getMeasuredWidth(), materialCardView.getMeasuredHeight());
        }
    }

    public void setCheckedIconMargin(int i) {
        this.l.e = i;
    }

    public void setCheckedIconMarginResource(int i) {
        if (i != -1) {
            this.l.e = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconResource(int i) {
        this.l.f(AppCompatResources.a(getContext(), i));
    }

    public void setCheckedIconSize(int i) {
        this.l.f = i;
    }

    public void setCheckedIconSizeResource(int i) {
        if (i != 0) {
            this.l.f = getResources().getDimensionPixelSize(i);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        ic1 ic1Var = this.l;
        ic1Var.l = colorStateList;
        Drawable drawable = ic1Var.j;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        ic1 ic1Var = this.l;
        if (ic1Var != null) {
            Drawable drawable = ic1Var.i;
            MaterialCardView materialCardView = ic1Var.a;
            Drawable drawableC = materialCardView.isClickable() ? ic1Var.c() : ic1Var.d;
            ic1Var.i = drawableC;
            if (drawable != drawableC) {
                if (materialCardView.getForeground() instanceof InsetDrawable) {
                    ((InsetDrawable) materialCardView.getForeground()).setDrawable(drawableC);
                } else {
                    materialCardView.setForeground(ic1Var.d(drawableC));
                }
            }
        }
    }

    public void setDragged(boolean z) {
        if (this.o != z) {
            this.o = z;
            refreshDrawableState();
            g();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.l.j();
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        ic1 ic1Var = this.l;
        ic1Var.j();
        ic1Var.i();
    }

    public void setProgress(float f) {
        ic1 ic1Var = this.l;
        ic1Var.c.o(f);
        sc1 sc1Var = ic1Var.d;
        if (sc1Var != null) {
            sc1Var.o(f);
        }
        sc1 sc1Var2 = ic1Var.q;
        if (sc1Var2 != null) {
            sc1Var2.o(f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    @Override // androidx.cardview.widget.CardView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setRadius(float r2) {
        /*
            r1 = this;
            super.setRadius(r2)
            ic1 r1 = r1.l
            uj2 r0 = r1.m
            uj2 r2 = r0.f(r2)
            r1.g(r2)
            android.graphics.drawable.Drawable r2 = r1.i
            r2.invalidateSelf()
            boolean r2 = r1.h()
            if (r2 != 0) goto L2e
            com.google.android.material.card.MaterialCardView r2 = r1.a
            boolean r2 = r2.getPreventCornerOverlap()
            if (r2 == 0) goto L2b
            sc1 r2 = r1.c
            boolean r2 = r2.l()
            if (r2 != 0) goto L2b
            r2 = 1
            goto L2c
        L2b:
            r2 = 0
        L2c:
            if (r2 == 0) goto L31
        L2e:
            r1.i()
        L31:
            boolean r2 = r1.h()
            if (r2 == 0) goto L3a
            r1.j()
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.card.MaterialCardView.setRadius(float):void");
    }

    public void setRippleColor(ColorStateList colorStateList) {
        ic1 ic1Var = this.l;
        ic1Var.k = colorStateList;
        RippleDrawable rippleDrawable = ic1Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(colorStateList);
        }
    }

    public void setRippleColorResource(int i) {
        ColorStateList colorStateListB = bz.b(getContext(), i);
        ic1 ic1Var = this.l;
        ic1Var.k = colorStateListB;
        RippleDrawable rippleDrawable = ic1Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(colorStateListB);
        }
    }

    @Override // defpackage.yj2
    public void setShapeAppearanceModel(uj2 uj2Var) {
        setClipToOutline(uj2Var.e(getBoundsAsRectF()));
        this.l.g(uj2Var);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        ic1 ic1Var = this.l;
        if (ic1Var.n != colorStateList) {
            ic1Var.n = colorStateList;
            sc1 sc1Var = ic1Var.d;
            sc1Var.e.k = ic1Var.h;
            sc1Var.invalidateSelf();
            sc1Var.s(colorStateList);
        }
        invalidate();
    }

    public void setStrokeWidth(int i) {
        ic1 ic1Var = this.l;
        if (i != ic1Var.h) {
            ic1Var.h = i;
            sc1 sc1Var = ic1Var.d;
            ColorStateList colorStateList = ic1Var.n;
            sc1Var.e.k = i;
            sc1Var.invalidateSelf();
            sc1Var.s(colorStateList);
        }
        invalidate();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        ic1 ic1Var = this.l;
        ic1Var.j();
        ic1Var.i();
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        ic1 ic1Var = this.l;
        if ((ic1Var != null && ic1Var.s) && isEnabled()) {
            this.n = true ^ this.n;
            refreshDrawableState();
            g();
            boolean z = this.n;
            Drawable drawable = ic1Var.j;
            if (drawable != null) {
                drawable.setAlpha(z ? 255 : 0);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialCardViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        int i2 = s;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.n = false;
        this.o = false;
        this.m = true;
        TypedArray typedArrayD = r83.d(getContext(), attributeSet, R$styleable.MaterialCardView, i, i2, new int[0]);
        ic1 ic1Var = new ic1(this, attributeSet, i);
        this.l = ic1Var;
        ColorStateList cardBackgroundColor = super.getCardBackgroundColor();
        sc1 sc1Var = ic1Var.c;
        sc1Var.n(cardBackgroundColor);
        ic1Var.b.set(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        ic1Var.i();
        MaterialCardView materialCardView = ic1Var.a;
        ColorStateList colorStateListA = rc1.a(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_strokeColor);
        ic1Var.n = colorStateListA;
        if (colorStateListA == null) {
            ic1Var.n = ColorStateList.valueOf(-1);
        }
        ic1Var.h = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArrayD.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        ic1Var.s = z;
        materialCardView.setLongClickable(z);
        ic1Var.l = rc1.a(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_checkedIconTint);
        ic1Var.f(rc1.d(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_checkedIcon));
        ic1Var.f = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_checkedIconSize, 0);
        ic1Var.e = typedArrayD.getDimensionPixelSize(R$styleable.MaterialCardView_checkedIconMargin, 0);
        ic1Var.g = typedArrayD.getInteger(R$styleable.MaterialCardView_checkedIconGravity, 8388661);
        ColorStateList colorStateListA2 = rc1.a(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_rippleColor);
        ic1Var.k = colorStateListA2;
        if (colorStateListA2 == null) {
            ic1Var.k = ColorStateList.valueOf(oj2.p(materialCardView, R$attr.colorControlHighlight));
        }
        ColorStateList colorStateListA3 = rc1.a(materialCardView.getContext(), typedArrayD, R$styleable.MaterialCardView_cardForegroundColor);
        sc1 sc1Var2 = ic1Var.d;
        sc1Var2.n(colorStateListA3 == null ? ColorStateList.valueOf(0) : colorStateListA3);
        RippleDrawable rippleDrawable = ic1Var.o;
        if (rippleDrawable != null) {
            rippleDrawable.setColor(ic1Var.k);
        }
        sc1Var.m(materialCardView.getCardElevation());
        float f = ic1Var.h;
        ColorStateList colorStateList = ic1Var.n;
        sc1Var2.e.k = f;
        sc1Var2.invalidateSelf();
        sc1Var2.s(colorStateList);
        materialCardView.setBackgroundInternal(ic1Var.d(sc1Var));
        Drawable drawableC = materialCardView.isClickable() ? ic1Var.c() : sc1Var2;
        ic1Var.i = drawableC;
        materialCardView.setForeground(ic1Var.d(drawableC));
        typedArrayD.recycle();
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i) {
        this.l.c.n(ColorStateList.valueOf(i));
    }

    public void setStrokeColor(int i) {
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setOnCheckedChangeListener(a aVar) {
    }
}
