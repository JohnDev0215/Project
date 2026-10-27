package com.byd.base.view.segmentedbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundView extends View {
    public BackgroundView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(View.resolveSizeAndState(Math.max(0, getSuggestedMinimumWidth()), i, 0), View.resolveSizeAndState(Math.max(0, getSuggestedMinimumHeight()), i2, 0));
    }

    public BackgroundView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BackgroundView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
