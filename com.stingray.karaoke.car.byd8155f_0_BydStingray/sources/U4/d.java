package U4;

import F1.H;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.ChipGroup;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends ViewGroup {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9055m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9056n;
    public int o;

    public int getItemSpacing() {
        return this.f9055m;
    }

    public int getLineSpacing() {
        return this.l;
    }

    public int getRowCount() {
        return this.o;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        int marginEnd;
        int marginStart;
        if (getChildCount() == 0) {
            this.o = 0;
            return;
        }
        this.o = 1;
        int[] iArr = H.f2303a;
        boolean z10 = getLayoutDirection() == 1;
        int paddingRight = z10 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z10 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i13 = (i11 - i7) - paddingLeft;
        int measuredWidth = paddingRight;
        int i14 = paddingTop;
        for (int i15 = 0; i15 < getChildCount(); i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() == 8) {
                childAt.setTag(R.id.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginStart = marginLayoutParams.getMarginStart();
                    marginEnd = marginLayoutParams.getMarginEnd();
                } else {
                    marginEnd = 0;
                    marginStart = 0;
                }
                int measuredWidth2 = childAt.getMeasuredWidth() + measuredWidth + marginStart;
                if (!this.f9056n && measuredWidth2 > i13) {
                    i14 = this.l + paddingTop;
                    this.o++;
                    measuredWidth = paddingRight;
                }
                childAt.setTag(R.id.row_index_key, Integer.valueOf(this.o - 1));
                int i16 = measuredWidth + marginStart;
                int measuredWidth3 = childAt.getMeasuredWidth() + i16;
                int measuredHeight = childAt.getMeasuredHeight() + i14;
                if (z10) {
                    childAt.layout(i13 - measuredWidth3, i14, (i13 - measuredWidth) - marginStart, measuredHeight);
                } else {
                    childAt.layout(i16, i14, measuredWidth3, measuredHeight);
                }
                measuredWidth += childAt.getMeasuredWidth() + marginStart + marginEnd + this.f9055m;
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        int i11;
        int i12;
        int i13;
        int paddingLeft;
        int size = View.MeasureSpec.getSize(i7);
        int mode = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i10);
        int mode2 = View.MeasureSpec.getMode(i10);
        int i14 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i14 - getPaddingRight();
        int i15 = paddingTop;
        int i16 = 0;
        for (int i17 = 0; i17 < getChildCount(); i17++) {
            View childAt = getChildAt(i17);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i7, i10);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i13 = marginLayoutParams.leftMargin;
                    i12 = marginLayoutParams.rightMargin;
                } else {
                    i12 = 0;
                    i13 = 0;
                }
                int i18 = paddingLeft2;
                if (childAt.getMeasuredWidth() + paddingLeft2 + i13 <= paddingRight || ((ChipGroup) this).f9056n) {
                    paddingLeft = i18;
                } else {
                    paddingLeft = getPaddingLeft();
                    i15 = this.l + paddingTop;
                }
                int measuredWidth = childAt.getMeasuredWidth() + paddingLeft + i13;
                int measuredHeight = childAt.getMeasuredHeight() + i15;
                if (measuredWidth > i16) {
                    i16 = measuredWidth;
                }
                int measuredWidth2 = childAt.getMeasuredWidth() + i13 + i12 + this.f9055m + paddingLeft;
                if (i17 == getChildCount() - 1) {
                    i16 += i12;
                }
                paddingLeft2 = measuredWidth2;
                paddingTop = measuredHeight;
            }
        }
        int paddingRight2 = getPaddingRight() + i16;
        int paddingBottom = getPaddingBottom() + paddingTop;
        if (mode != Integer.MIN_VALUE) {
            i11 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            if (mode != 1073741824) {
                size = paddingRight2;
            }
        } else {
            i11 = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
            size = Math.min(paddingRight2, size);
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(paddingBottom, size2);
        } else if (mode2 != i11) {
            size2 = paddingBottom;
        }
        setMeasuredDimension(size, size2);
    }

    public void setItemSpacing(int i7) {
        this.f9055m = i7;
    }

    public void setLineSpacing(int i7) {
        this.l = i7;
    }

    public void setSingleLine(boolean z6) {
        this.f9056n = z6;
    }
}
