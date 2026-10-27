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
public class PriceCardView extends FrameLayout {
    public TextView e;
    public TextView f;

    public PriceCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R$layout.price_card, this);
        this.e = (TextView) findViewById(R$id.current_price);
        TextView textView = (TextView) findViewById(R$id.previous_price);
        this.f = textView;
        textView.setPaintFlags(textView.getPaintFlags() | 16);
        this.e.setTextColor(getContext().getColor(R$color.price_drop_annotation_text_green));
        this.f.setTextColor(getContext().getColor(R$color.chip_text_color_secondary_list));
    }
}
