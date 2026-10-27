package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
public final class SmallUpsellButton extends RelativeLayout {
    private EmberTextView text;

    public SmallUpsellButton(final Context context, final AttributeSet attrs) {
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
        View.inflate(getContext(), R$layout.small_upsell_button, this);
        this.text = (EmberTextView) findViewById(R$id.small_upsell_button_text);
    }
}
