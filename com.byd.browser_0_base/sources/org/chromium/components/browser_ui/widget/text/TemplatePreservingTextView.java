package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes2.dex */
public class TemplatePreservingTextView extends AppCompatTextView {
    public String k;
    public CharSequence l;
    public CharSequence m;

    public TemplatePreservingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = "";
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        q((View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight(), View.MeasureSpec.getMode(i) == 0);
        super.onMeasure(i, i2);
    }

    public final void q(int i, boolean z) {
        CharSequence charSequence;
        if (this.k == null) {
            charSequence = this.l;
        } else if (getMaxLines() != 1 || z) {
            charSequence = String.format(this.k, this.l);
        } else {
            TextPaint paint = getPaint();
            charSequence = String.format(this.k, TextUtils.ellipsize(this.l, paint, Math.max(i - paint.measureText(String.format(this.k, "")), 0.0f), TextUtils.TruncateAt.END));
        }
        if (charSequence.equals(this.m)) {
            return;
        }
        this.m = charSequence;
        super.setText(charSequence, TextView.BufferType.SPANNABLE);
    }

    public void setTemplate(String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.k = str;
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.l = charSequence;
        String str = this.k;
        if (str != null) {
            charSequence = String.format(str, charSequence);
        }
        setContentDescription(charSequence);
        q(0, true);
    }
}
