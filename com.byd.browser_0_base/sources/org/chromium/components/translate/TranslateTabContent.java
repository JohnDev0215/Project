package org.chromium.components.translate;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class TranslateTabContent extends FrameLayout {
    public TextView e;
    public ProgressBar f;

    public TranslateTabContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (TextView) findViewById(R$id.translate_infobar_tab_text);
        this.f = (ProgressBar) findViewById(R$id.translate_infobar_tab_progressbar);
    }

    public void setText(CharSequence charSequence) {
        this.e.setText(charSequence);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.e.setTextColor(colorStateList);
    }
}
