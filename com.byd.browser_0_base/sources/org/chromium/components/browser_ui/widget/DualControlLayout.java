package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public final class DualControlLayout extends ViewGroup {
    public final int e;
    public int f;
    public int g;
    public boolean h;
    public View i;
    public View j;

    public DualControlLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 0;
        this.e = getContext().getResources().getDimensionPixelSize(R$dimen.dual_control_margin_between_items);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.DualControlLayout, 0, 0);
            int i = R$styleable.DualControlLayout_stackedMargin;
            if (typedArrayObtainStyledAttributes.hasValue(i)) {
                setStackedMargin(typedArrayObtainStyledAttributes.getDimensionPixelSize(i, 0));
            }
            int i2 = R$styleable.DualControlLayout_primaryButtonText;
            String string = typedArrayObtainStyledAttributes.hasValue(i2) ? typedArrayObtainStyledAttributes.getString(i2) : null;
            if (!TextUtils.isEmpty(string)) {
                addView(a(getContext(), true, string, null));
            }
            int i3 = R$styleable.DualControlLayout_secondaryButtonText;
            String string2 = typedArrayObtainStyledAttributes.hasValue(i3) ? typedArrayObtainStyledAttributes.getString(i3) : null;
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                addView(a(getContext(), false, string2, null));
            }
            int i4 = R$styleable.DualControlLayout_buttonAlignment;
            if (typedArrayObtainStyledAttributes.hasValue(i4)) {
                setAlignment(typedArrayObtainStyledAttributes.getInt(i4, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static ButtonCompat a(Context context, boolean z, String str, View.OnClickListener onClickListener) {
        if (z) {
            ButtonCompat buttonCompat = new ButtonCompat(context, null, R$style.FilledButtonThemeOverlay_Flat);
            buttonCompat.setId(R$id.button_primary);
            buttonCompat.setOnClickListener(onClickListener);
            buttonCompat.setText(str);
            return buttonCompat;
        }
        ButtonCompat buttonCompat2 = new ButtonCompat(context, null, R$style.TextButtonThemeOverlay);
        buttonCompat2.setId(R$id.button_secondary);
        buttonCompat2.setOnClickListener(onClickListener);
        buttonCompat2.setText(str);
        return buttonCompat2;
    }

    public int getAlignment() {
        return this.f;
    }

    public int getStackedMargin() {
        return this.g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int measuredWidth;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i6 = i3 - i;
        boolean z2 = true;
        boolean z3 = getLayoutDirection() == 1;
        if ((!z3 || this.f != 0) && (z3 || ((i5 = this.f) != 2 && i5 != 1))) {
            z2 = false;
        }
        int measuredWidth2 = this.i.getVisibility() != 8 ? this.i.getMeasuredWidth() : 0;
        int i7 = z2 ? i6 - paddingRight : measuredWidth2 + paddingLeft;
        int i8 = i7 - measuredWidth2;
        int paddingTop = getPaddingTop();
        int measuredHeight = this.i.getMeasuredHeight() + paddingTop;
        this.i.layout(i8, paddingTop, i7, measuredHeight);
        if (this.h) {
            int i9 = measuredHeight + this.g;
            int measuredHeight2 = this.j.getMeasuredHeight() + i9;
            View view = this.j;
            view.layout(paddingLeft, i9, view.getMeasuredWidth() + paddingLeft, measuredHeight2);
            return;
        }
        View view2 = this.j;
        if (view2 != null) {
            int measuredHeight3 = view2.getMeasuredHeight();
            int i10 = ((paddingTop + measuredHeight) / 2) - (measuredHeight3 / 2);
            int i11 = measuredHeight3 + i10;
            if (this.f == 2) {
                if (!z2) {
                    paddingLeft = (i6 - paddingRight) - this.j.getMeasuredWidth();
                }
                measuredWidth = this.j.getMeasuredWidth() + paddingLeft;
            } else {
                int i12 = this.e;
                if (z2) {
                    if (measuredWidth2 > 0) {
                        i8 -= i12;
                    }
                    paddingLeft = i8 - this.j.getMeasuredWidth();
                    measuredWidth = i8;
                } else {
                    if (measuredWidth2 > 0) {
                        i7 += i12;
                    }
                    int i13 = i7;
                    measuredWidth = this.j.getMeasuredWidth() + i7;
                    paddingLeft = i13;
                }
            }
            this.j.layout(paddingLeft, i10, measuredWidth, i11);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        this.h = false;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int size = View.MeasureSpec.getMode(i) == 0 ? Integer.MAX_VALUE : View.MeasureSpec.getSize(i) - paddingRight;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        measureChild(this.i, iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredWidth = this.i.getMeasuredWidth();
        int measuredHeight = this.i.getMeasuredHeight();
        View view = this.j;
        if (view != null) {
            measureChild(view, iMakeMeasureSpec, iMakeMeasureSpec);
            int measuredWidth2 = this.j.getMeasuredWidth() + this.i.getMeasuredWidth();
            if (this.i.getMeasuredWidth() > 0 && this.j.getMeasuredWidth() > 0) {
                measuredWidth2 += this.e;
            }
            if (measuredWidth2 > size) {
                this.h = true;
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                this.i.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
                this.j.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
                measuredHeight = this.j.getMeasuredHeight() + this.i.getMeasuredHeight() + this.g;
            } else {
                measuredHeight = Math.max(measuredHeight, this.j.getMeasuredHeight());
                size = measuredWidth2;
            }
        } else {
            size = measuredWidth;
        }
        setMeasuredDimension(View.resolveSize(size + paddingRight, i), View.resolveSize(measuredHeight + paddingBottom, i2));
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.i == null) {
            this.i = view;
        } else {
            if (this.j != null) {
                throw new IllegalStateException("Too many children added to DualControlLayout");
            }
            this.j = view;
        }
    }

    public void setAlignment(int i) {
        this.f = i;
    }

    public void setStackedMargin(int i) {
        this.g = i;
    }
}
