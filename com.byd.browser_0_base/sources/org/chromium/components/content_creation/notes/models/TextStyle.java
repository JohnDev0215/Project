package org.chromium.components.content_creation.notes.models;

import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.widget.TextView;
import defpackage.gq2;
import defpackage.h83;
import org.chromium.chrome.browser.content_creation.notes.LineLimitedTextView;

/* JADX INFO: loaded from: classes2.dex */
public final class TextStyle {
    public final String a;
    public final int b;
    public final int c;
    public final boolean d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;

    public TextStyle(String str, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7) {
        this.a = str;
        this.b = i2;
        this.c = i;
        this.d = z;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
        this.i = i7;
    }

    public final void a(LineLimitedTextView lineLimitedTextView, String str) {
        lineLimitedTextView.setTextColor(this.c);
        lineLimitedTextView.setAllCaps(this.d);
        int i = this.e;
        int iM = gq2.m(i);
        lineLimitedTextView.setGravity((iM == 0 || iM == 1) ? 8388627 : iM != 2 ? iM != 3 ? 8388611 : 8388629 : 17);
        lineLimitedTextView.setAutoSizeTextTypeUniformWithConfiguration(this.f, this.g, 1, 2);
        int i2 = this.i;
        int i3 = this.h;
        if (!((i3 == 0 || i2 == 1) ? false : true)) {
            lineLimitedTextView.setText(str);
            return;
        }
        int length = str.length();
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new h83(i2, i3, i, lineLimitedTextView.getLayoutDirection() == 1), 0, length, 33);
        spannableString.setSpan(new LeadingMarginSpan.Standard(10), 0, length, 33);
        lineLimitedTextView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}
