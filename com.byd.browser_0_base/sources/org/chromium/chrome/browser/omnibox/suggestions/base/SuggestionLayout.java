package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewGroup;
import defpackage.pr1;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes2.dex */
class SuggestionLayout extends ViewGroup {
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;

    public static class a extends ViewGroup.LayoutParams {
        public final int a;
        public final Rect b;

        public a(int i, int i2) {
            super(i, -2);
            this.b = new Rect();
            this.a = i2;
        }
    }

    public SuggestionLayout(Context context) {
        super(context);
        boolean zE = pr1.e(getContext());
        if (!zE) {
            setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_start_padding), 0, getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_end_padding), 0);
        }
        this.e = getResources().getDimensionPixelSize(zE ? R$dimen.omnibox_suggestion_icon_area_size_modern : R$dimen.omnibox_suggestion_icon_area_size);
        this.f = getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_action_button_width);
        this.g = getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_compact_content_height);
        this.h = getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_content_height);
        this.i = getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_content_padding);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingTop = getPaddingTop();
        boolean z2 = getLayoutDirection() == 1;
        int paddingRight = z2 ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                Rect rect = ((a) childAt.getLayoutParams()).b;
                if (z2) {
                    childAt.layout(paddingRight - rect.right, rect.top + paddingTop, paddingRight - rect.left, rect.bottom + paddingTop);
                } else {
                    childAt.layout(rect.left + paddingRight, rect.top + paddingTop, rect.right + paddingRight, rect.bottom + paddingTop);
                }
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int iMakeMeasureSpec;
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int i7 = this.e;
        int i8 = size - i7;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            int childCount = getChildCount();
            i3 = this.f;
            i4 = 8;
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8 && ((a) childAt.getLayoutParams()).a == 2) {
                i8 -= i3;
            }
            i10++;
        }
        int i11 = 0;
        boolean z = false;
        int measuredHeight = 0;
        while (true) {
            i5 = 1;
            if (i11 >= getChildCount()) {
                break;
            }
            View childAt2 = getChildAt(i11);
            if (childAt2.getVisibility() != 8) {
                int i12 = ((a) childAt2.getLayoutParams()).a;
                if (i12 == 0) {
                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
                    measuredHeight = childAt2.getMeasuredHeight();
                } else if (i12 == 3) {
                    z = true;
                }
            }
            i11++;
        }
        if (!z) {
            measuredHeight += this.i;
        }
        int iMax = Math.max(measuredHeight, z ? this.g : this.h);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMax, Integer.MIN_VALUE);
        for (int i13 = 0; i13 < getChildCount(); i13++) {
            View childAt3 = getChildAt(i13);
            if (childAt3.getVisibility() != 8) {
                a aVar = (a) childAt3.getLayoutParams();
                int i14 = aVar.a;
                if (i14 == 1) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                } else if (i14 == 2) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                }
                childAt3.measure(iMakeMeasureSpec, ViewGroup.getChildMeasureSpec(iMakeMeasureSpec2, 0, ((ViewGroup.LayoutParams) aVar).height));
            }
        }
        int measuredHeight2 = 0;
        for (int i15 = 0; i15 < getChildCount(); i15++) {
            View childAt4 = getChildAt(i15);
            if (childAt4.getVisibility() != 8 && ((a) childAt4.getLayoutParams()).a == 3) {
                childAt4.measure(View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(0, 0));
                measuredHeight2 += childAt4.getMeasuredHeight();
            }
        }
        int i16 = i8 + i7;
        int i17 = i16;
        int i18 = 0;
        int i19 = iMax;
        while (i18 < getChildCount()) {
            View childAt5 = getChildAt(i18);
            if (childAt5.getVisibility() == i4) {
                i6 = i9;
            } else {
                a aVar2 = (a) childAt5.getLayoutParams();
                int i20 = aVar2.a;
                Rect rect = aVar2.b;
                if (i20 == 0) {
                    i6 = 0;
                    rect.set(i7, 0, i16, iMax + 0);
                } else if (i20 != i5) {
                    if (i20 == 2) {
                        int i21 = i3 + i17;
                        rect.set(i17, i9, i21, iMax + 0);
                        i17 = i21;
                    } else if (i20 == 3) {
                        int measuredHeight3 = childAt5.getMeasuredHeight() + i19;
                        rect.set(i9, i19, size + 0, measuredHeight3);
                        i19 = measuredHeight3;
                    }
                    i6 = i9;
                } else {
                    int measuredWidth = childAt5.getMeasuredWidth();
                    int measuredHeight4 = childAt5.getMeasuredHeight();
                    int i22 = (i7 - measuredWidth) / 2;
                    int i23 = (iMax - measuredHeight4) / 2;
                    rect.set(i22, i23, measuredWidth + i22, measuredHeight4 + i23);
                    i6 = 0;
                }
            }
            i18++;
            i9 = i6;
            i4 = 8;
            i5 = 1;
        }
        setMeasuredDimension(i, View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + iMax + measuredHeight2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }
}
