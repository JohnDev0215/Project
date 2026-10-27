package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class WrapWidthTextView extends TextView {
    public WrapWidthTextView(Context context) {
        super(context);
    }

    public WrapWidthTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WrapWidthTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public WrapWidthTextView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getLayout() != null) {
            setMeasuredDimension(((int) Math.ceil(getMaxLineWidth(r1))) + getCompoundPaddingLeft() + getCompoundPaddingRight(), getMeasuredHeight());
        }
    }

    private float getMaxLineWidth(Layout layout) {
        int lineCount = layout.getLineCount();
        float lineWidth = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            if (layout.getLineWidth(i) > lineWidth) {
                lineWidth = layout.getLineWidth(i);
            }
        }
        return lineWidth;
    }
}
