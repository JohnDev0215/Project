package org.chromium.chrome.browser.autofill.prefeditor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* JADX INFO: loaded from: classes.dex */
public class ExpandableGridView extends GridView {
    public ExpandableGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onMeasure(int i, int i2) {
        if (getLayoutParams().height == -2) {
            i2 = View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
