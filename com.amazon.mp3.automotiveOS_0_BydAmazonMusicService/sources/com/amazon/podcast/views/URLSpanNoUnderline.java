package com.amazon.podcast.views;

import android.content.res.Resources;
import android.text.TextPaint;
import android.text.style.URLSpan;
import com.amazon.podcast.R$color;

/* JADX INFO: loaded from: classes5.dex */
public final class URLSpanNoUnderline extends URLSpan {
    private final Resources resources;

    public URLSpanNoUnderline(final String url, final Resources resources) {
        super(url);
        this.resources = resources;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public final void updateDrawState(final TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
        textPaint.setColor(this.resources.getColor(R$color.primary));
    }
}
