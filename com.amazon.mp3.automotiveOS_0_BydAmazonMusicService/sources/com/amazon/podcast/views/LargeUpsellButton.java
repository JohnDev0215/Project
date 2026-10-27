package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
public final class LargeUpsellButton extends RelativeLayout {
    private EmberTextView text;

    public LargeUpsellButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void bindUpsellButtonText(final String upsellText) {
        this.text.setText(upsellText);
    }

    public void setUpsellButtonOnClickListener(final View.OnClickListener onClickListener) {
        this.text.setOnClickListener(onClickListener);
    }

    private void init() {
        View.inflate(getContext(), R$layout.large_upsell_button, this);
        this.text = (EmberTextView) findViewById(R$id.large_upsell_button_text);
    }
}
