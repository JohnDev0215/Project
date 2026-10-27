package org.chromium.chrome.browser.payments.ui;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
public class PaymentRequestBottomBar extends ViewGroup {
    public ImageView e;
    public ImageView f;
    public View g;
    public View h;
    public View i;

    public PaymentRequestBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(R$id.logo_name);
        this.e = imageView;
        imageView.setImageResource(R$drawable.product_logo_name);
        ImageView imageView2 = (ImageView) findViewById(R$id.logo);
        this.f = imageView2;
        imageView2.setImageResource(R$drawable.fre_product_logo);
        this.g = findViewById(R$id.button_primary);
        this.h = findViewById(R$id.button_secondary);
        this.i = findViewById(R$id.space);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int paddingLeft = getPaddingLeft() + marginLayoutParams.leftMargin;
        int paddingRight = getPaddingRight() + marginLayoutParams.rightMargin;
        int paddingTop = getPaddingTop() + marginLayoutParams.topMargin;
        int i5 = i3 - i;
        int paddingBottom = ((i4 - i2) - paddingTop) - (getPaddingBottom() + marginLayoutParams.bottomMargin);
        boolean z2 = getLayoutDirection() == 1;
        if (z2) {
            paddingLeft = i5 - paddingRight;
        }
        int i6 = paddingLeft;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (z2) {
                    paddingLeft = i6 - measuredWidth;
                } else {
                    i6 = paddingLeft + measuredWidth;
                }
                int i8 = ((paddingBottom - measuredHeight) / 2) + paddingTop;
                childAt.layout(paddingLeft, i8, i6, measuredHeight + i8);
                int i9 = paddingLeft;
                paddingLeft = i6;
                i6 = i9;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        measureChild(this.e, i, i2);
        int measuredWidth = this.e.getMeasuredWidth();
        measureChild(this.f, i, i2);
        int measuredWidth2 = this.f.getMeasuredWidth();
        measureChild(this.g, i, i2);
        int measuredWidth3 = this.g.getMeasuredWidth();
        measureChild(this.h, i, i2);
        int measuredWidth4 = this.h.getMeasuredWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth3 + measuredWidth4 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        int size = View.MeasureSpec.getSize(i);
        if (paddingRight + measuredWidth <= size) {
            this.f.setVisibility(8);
            this.e.setVisibility(0);
            i3 = (size - paddingRight) - measuredWidth;
        } else if (paddingRight + measuredWidth2 <= size) {
            this.f.setVisibility(0);
            this.e.setVisibility(8);
            i3 = (size - paddingRight) - measuredWidth2;
        } else {
            this.f.setVisibility(8);
            this.e.setVisibility(8);
            i3 = size >= paddingRight ? size - paddingRight : 0;
        }
        this.i.getLayoutParams().width = i3;
        measureChild(this.i, View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
        setMeasuredDimension(i, View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + Math.max(this.e.getMeasuredHeight(), Math.max(this.g.getMeasuredHeight(), this.h.getMeasuredHeight())) + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }
}
