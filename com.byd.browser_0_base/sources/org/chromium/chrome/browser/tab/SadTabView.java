package org.chromium.chrome.browser.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public class SadTabView extends ScrollView {
    public final int e;

    public SadTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = (int) (context.getResources().getDisplayMetrics().density * 620.0f);
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ButtonCompat buttonCompat = (ButtonCompat) findViewById(R$id.sad_tab_button);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) buttonCompat.getLayoutParams();
        if ((size > size2 || size > this.e) && buttonCompat.getWidth() <= size) {
            layoutParams.width = -2;
            layoutParams.gravity = 8388613;
        } else {
            layoutParams.width = -1;
            layoutParams.gravity = 7;
        }
        buttonCompat.setLayoutParams(layoutParams);
        super.onMeasure(i, i2);
    }
}
