package org.chromium.ui.widget;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class TextViewWithTightWrap extends TextView {
    public TextViewWithTightWrap(Context context) {
        super(context);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Layout layout = getLayout();
        if (layout == null || layout.getLineCount() <= 1) {
            return;
        }
        int lineCount = layout.getLineCount();
        float fMax = 0.0f;
        for (int i3 = 0; i3 < lineCount; i3++) {
            fMax = Math.max(fMax, layout.getLineWidth(i3));
        }
        int compoundPaddingRight = getCompoundPaddingRight() + getCompoundPaddingLeft() + ((int) Math.ceil(fMax));
        getMeasuredHeight();
        if (compoundPaddingRight < getMeasuredWidth()) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(compoundPaddingRight, Integer.MIN_VALUE), i2);
        }
    }

    public TextViewWithTightWrap(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TextViewWithTightWrap(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
