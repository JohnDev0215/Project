package com.android.car.developeroptions.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class BottomLabelLayout extends LinearLayout {
    public BottomLabelLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        int iMakeMeasureSpec;
        int size = View.MeasureSpec.getSize(i);
        boolean zIsStacked = isStacked();
        boolean z2 = true;
        if (zIsStacked || View.MeasureSpec.getMode(i) != 1073741824) {
            z = false;
            iMakeMeasureSpec = i;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z = true;
        }
        super.onMeasure(iMakeMeasureSpec, i2);
        if (zIsStacked || (getMeasuredWidthAndState() & (-16777216)) != 16777216) {
            z2 = z;
        } else {
            setStacked(true);
        }
        if (z2) {
            super.onMeasure(i, i2);
        }
    }

    void setStacked(boolean z) {
        setOrientation(z ? 1 : 0);
        setGravity(z ? 8388611 : 80);
        View viewFindViewById = findViewById(R.id.spacer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z ? 8 : 0);
        }
    }

    private boolean isStacked() {
        return getOrientation() == 1;
    }
}
