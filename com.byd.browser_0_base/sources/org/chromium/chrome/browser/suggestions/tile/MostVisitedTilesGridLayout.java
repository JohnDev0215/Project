package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.a8;
import defpackage.il0;
import org.chromium.base.library_loader.a;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class MostVisitedTilesGridLayout extends FrameLayout {
    public final int e;
    public final int f;
    public final int g;
    public int h;
    public int i;
    public int j;
    public boolean k;

    public MostVisitedTilesGridLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = true;
        Resources resources = getResources();
        this.h = getResources().getDimensionPixelOffset(getGridMVTVerticalSpacingResourcesId());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MostVisitedTilesGridLayout);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MostVisitedTilesGridLayout_minHorizontalSpacing, resources.getDimensionPixelOffset(R$dimen.tile_grid_layout_min_horizontal_spacing));
        typedArrayObtainStyledAttributes.recycle();
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
    }

    private int getGridMVTVerticalSpacingResourcesId() {
        return (a.l.d() && this.k) ? il0.c() ? R$dimen.tile_grid_layout_vertical_spacing_push_down_large : il0.d() ? R$dimen.tile_grid_layout_vertical_spacing_push_down_small : R$dimen.tile_grid_layout_vertical_spacing : R$dimen.tile_grid_layout_vertical_spacing;
    }

    public final Pair a(int i, int i2, boolean z) {
        float fMax;
        int i3 = 0;
        if (z) {
            fMax = i / (i2 + 1);
            int iRound = Math.round(fMax);
            if (fMax < this.e) {
                return a(i, i2, false);
            }
            i3 = iRound;
        } else {
            int i4 = this.f;
            long j = ((long) i) - (((long) i4) * ((long) (i2 - 1)));
            if (j > 0) {
                i3 = (int) (j / 2);
                fMax = i4;
            } else {
                fMax = i / Math.max(1, r9);
            }
        }
        return Pair.create(Integer.valueOf(i3), Integer.valueOf(Math.round(fMax)));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int iMin = Math.min(View.MeasureSpec.getSize(i), this.g);
        int childCount = getChildCount();
        int i7 = 0;
        if (childCount == 0) {
            setMeasuredDimension(iMin, View.resolveSize(0, i2));
            return;
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            measureChild(getChildAt(i8), 0, 0);
        }
        int measuredHeight = getChildAt(0).getMeasuredHeight();
        int measuredWidth = getChildAt(0).getMeasuredWidth();
        int i9 = this.e;
        int iQ = a8.q((iMin + i9) / (i9 + measuredWidth), 1, this.j);
        Pair pairA = a(Math.max(0, iMin - (iQ * measuredWidth)), iQ, true);
        int iIntValue = ((Integer) pairA.first).intValue();
        int iIntValue2 = ((Integer) pairA.second).intValue();
        int iMin2 = Math.min(childCount, this.i * iQ);
        int i10 = ((iMin2 + iQ) - 1) / iQ;
        int paddingTop = getPaddingTop();
        boolean z = getLayoutDirection() == 1;
        int i11 = 0;
        while (i11 < iMin2) {
            View childAt = getChildAt(i11);
            childAt.setVisibility(i7);
            int i12 = (this.h + measuredHeight) * (i11 / iQ);
            int i13 = ((measuredWidth + iIntValue2) * (i11 % iQ)) + iIntValue;
            int i14 = measuredWidth;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int i15 = iQ;
            int i16 = z ? 0 : i13;
            if (z) {
                i4 = iIntValue;
                i6 = 0;
                i3 = iIntValue2;
                i5 = i13;
            } else {
                i3 = iIntValue2;
                i4 = iIntValue;
                i5 = 0;
                i6 = 0;
            }
            marginLayoutParams.setMargins(i16, i12, i5, i6);
            childAt.setLayoutParams(marginLayoutParams);
            i11++;
            i7 = i6;
            iIntValue2 = i3;
            iQ = i15;
            iIntValue = i4;
            measuredWidth = i14;
        }
        while (iMin2 < childCount) {
            getChildAt(iMin2).setVisibility(8);
            iMin2++;
        }
        setMeasuredDimension(iMin, View.resolveSize(((i10 - 1) * this.h) + (measuredHeight * i10) + getPaddingBottom() + paddingTop, i2));
    }

    public void setMaxColumns(int i) {
        this.j = i;
    }

    public void setMaxRows(int i) {
        this.i = i;
    }

    public void setSearchProviderHasLogo(boolean z) {
        if (this.k == z) {
            return;
        }
        this.k = z;
        this.h = getResources().getDimensionPixelOffset(getGridMVTVerticalSpacingResourcesId());
    }
}
