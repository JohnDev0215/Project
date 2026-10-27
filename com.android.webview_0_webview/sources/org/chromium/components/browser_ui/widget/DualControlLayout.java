package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import defpackage.AbstractC0299Qm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0622cn;
import defpackage.AbstractC0678dn;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DualControlLayout extends ViewGroup {
    public final int s;
    public int t;
    public int u;
    public boolean v;
    public View w;
    public View x;

    public DualControlLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = 0;
        this.s = getContext().getResources().getDimensionPixelSize(AbstractC0299Qm.E2);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, AbstractC0678dn.M, 0, 0);
            if (typedArrayObtainStyledAttributes.hasValue(5)) {
                this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
            }
            String string = typedArrayObtainStyledAttributes.hasValue(3) ? typedArrayObtainStyledAttributes.getString(3) : null;
            if (!TextUtils.isEmpty(string)) {
                addView(a(getContext(), true, string, null));
            }
            String string2 = typedArrayObtainStyledAttributes.hasValue(4) ? typedArrayObtainStyledAttributes.getString(4) : null;
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                addView(a(getContext(), false, string2, null));
            }
            if (typedArrayObtainStyledAttributes.hasValue(0)) {
                this.t = typedArrayObtainStyledAttributes.getInt(0, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static Button a(Context context, boolean z, String str, View.OnClickListener onClickListener) {
        if (z) {
            ButtonCompat buttonCompat = new ButtonCompat(context, null, AbstractC0622cn.e3);
            buttonCompat.setId(AbstractC0371Um.Y);
            buttonCompat.setOnClickListener(null);
            buttonCompat.setText(str);
            return buttonCompat;
        }
        ButtonCompat buttonCompat2 = new ButtonCompat(context, null, AbstractC0622cn.e7);
        buttonCompat2.setId(AbstractC0371Um.Z);
        buttonCompat2.setOnClickListener(null);
        buttonCompat2.setText(str);
        buttonCompat2.setTextAppearance(buttonCompat2.getContext(), AbstractC0622cn.f5);
        return buttonCompat2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int measuredWidth;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i6 = i3 - i;
        boolean z2 = false;
        boolean z3 = getLayoutDirection() == 1;
        if ((z3 && this.t == 0) || (!z3 && ((i5 = this.t) == 2 || i5 == 1))) {
            z2 = true;
        }
        int measuredWidth2 = z2 ? i6 - paddingRight : this.w.getMeasuredWidth() + paddingLeft;
        int measuredWidth3 = measuredWidth2 - this.w.getMeasuredWidth();
        int paddingTop = getPaddingTop();
        int measuredHeight = this.w.getMeasuredHeight() + paddingTop;
        this.w.layout(measuredWidth3, paddingTop, measuredWidth2, measuredHeight);
        if (this.v) {
            int i7 = measuredHeight + this.u;
            int measuredHeight2 = this.x.getMeasuredHeight() + i7;
            View view = this.x;
            view.layout(paddingLeft, i7, view.getMeasuredWidth() + paddingLeft, measuredHeight2);
            return;
        }
        View view2 = this.x;
        if (view2 != null) {
            int measuredHeight3 = view2.getMeasuredHeight();
            int i8 = ((paddingTop + measuredHeight) / 2) - (measuredHeight3 / 2);
            int i9 = measuredHeight3 + i8;
            if (this.t == 2) {
                if (!z2) {
                    paddingLeft = (i6 - paddingRight) - this.x.getMeasuredWidth();
                }
                measuredWidth = this.x.getMeasuredWidth() + paddingLeft;
            } else if (z2) {
                if (this.w.getMeasuredWidth() > 0) {
                    measuredWidth3 -= this.s;
                }
                paddingLeft = measuredWidth3 - this.x.getMeasuredWidth();
                measuredWidth = measuredWidth3;
            } else {
                if (this.w.getMeasuredWidth() > 0) {
                    measuredWidth2 += this.s;
                }
                int i10 = measuredWidth2;
                measuredWidth = this.x.getMeasuredWidth() + measuredWidth2;
                paddingLeft = i10;
            }
            this.x.layout(paddingLeft, i8, measuredWidth, i9);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        this.v = false;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int size = View.MeasureSpec.getMode(i) == 0 ? Integer.MAX_VALUE : View.MeasureSpec.getSize(i) - paddingRight;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        measureChild(this.w, iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredWidth = this.w.getMeasuredWidth();
        int measuredHeight = this.w.getMeasuredHeight();
        View view = this.x;
        if (view != null) {
            measureChild(view, iMakeMeasureSpec, iMakeMeasureSpec);
            int measuredWidth2 = this.x.getMeasuredWidth() + this.w.getMeasuredWidth();
            if (this.w.getMeasuredWidth() > 0 && this.x.getMeasuredWidth() > 0) {
                measuredWidth2 += this.s;
            }
            if (measuredWidth2 > size) {
                this.v = true;
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                this.w.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
                this.x.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
                measuredHeight = this.x.getMeasuredHeight() + this.w.getMeasuredHeight() + this.u;
            } else {
                measuredHeight = Math.max(measuredHeight, this.x.getMeasuredHeight());
                size = measuredWidth2;
            }
        } else {
            size = measuredWidth;
        }
        setMeasuredDimension(ViewGroup.resolveSize(size + paddingRight, i), ViewGroup.resolveSize(measuredHeight + paddingBottom, i2));
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.w == null) {
            this.w = view;
        } else {
            if (this.x != null) {
                throw new IllegalStateException("Too many children added to DualControlLayout");
            }
            this.x = view;
        }
    }
}
