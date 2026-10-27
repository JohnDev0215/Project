package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes2.dex */
public class CouponCardView extends FrameLayout {
    public TextView e;

    public CouponCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R$layout.coupon_card, this);
        TextView textView = (TextView) findViewById(R$id.coupon_name);
        this.e = textView;
        textView.setTextColor(getContext().getColor(R$color.price_drop_annotation_text_green));
    }

    public void setCouponString(String str) {
        this.e.setText(str);
    }
}
