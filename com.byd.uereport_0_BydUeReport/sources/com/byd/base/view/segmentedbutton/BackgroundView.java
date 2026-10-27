package com.byd.base.view.segmentedbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundView extends View {
    public BackgroundView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        setMeasuredDimension(View.resolveSizeAndState(Math.max(0, getSuggestedMinimumWidth()), i8, 0), View.resolveSizeAndState(Math.max(0, getSuggestedMinimumHeight()), i9, 0));
    }

    public BackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BackgroundView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
    }
}
