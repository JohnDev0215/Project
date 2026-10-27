package org.chromium.ui.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/* JADX INFO: loaded from: classes4.dex */
public abstract class NoUnderlineClickableSpan extends ClickableSpan {
    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
    }
}
