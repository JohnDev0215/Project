package org.chromium.chrome.browser.omnibox.suggestions.header;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import defpackage.yp;
import org.chromium.components.browser_ui.styles.R$style;

/* JADX INFO: loaded from: classes2.dex */
public class HeaderView extends TextView {
    public HeaderView(Context context) {
        super(context);
        setMaxLines(1);
        setEllipsize(TextUtils.TruncateAt.END);
        String str = yp.a;
        setTextAppearance(R$style.TextAppearance_TextMediumThick_Secondary);
        setGravity(16);
        setTextAlignment(5);
    }
}
