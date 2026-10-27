package com.amazon.music.widget.nowplaying;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes4.dex */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth);
    }
}
