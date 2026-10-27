package org.chromium.chrome.browser.firstrun;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes.dex */
public class FirstRunChooserView extends ScrollView {
    public View e;

    public FirstRunChooserView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ScrollView, android.view.View
    public float getTopFadingEdgeStrength() {
        return 0.0f;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = findViewById(R$id.chooser_title);
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
        if (size2 > size) {
            layoutParams.height = (size * 9) / 16;
            View view = this.e;
            view.setPadding(view.getPaddingLeft(), 0, this.e.getPaddingRight(), this.e.getPaddingBottom());
        } else {
            layoutParams.height = -2;
            View view2 = this.e;
            view2.setPadding(view2.getPaddingLeft(), getResources().getDimensionPixelOffset(R$dimen.signin_screen_top_padding), this.e.getPaddingRight(), this.e.getPaddingBottom());
        }
        this.e.setLayoutParams(layoutParams);
        super.onMeasure(i, i2);
    }
}
