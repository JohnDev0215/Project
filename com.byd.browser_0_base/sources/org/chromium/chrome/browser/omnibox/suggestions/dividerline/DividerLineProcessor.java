package org.chromium.chrome.browser.omnibox.suggestions.dividerline;

import android.content.Context;
import defpackage.bw2;
import defpackage.ee0;
import org.chromium.chrome.R$dimen;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class DividerLineProcessor implements ee0 {
    public DividerLineProcessor(Context context) {
        context.getResources().getDimensionPixelSize(R$dimen.divider_height);
        context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_list_divider_line_padding);
    }

    @Override // defpackage.ee0
    public final int m() {
        return 9;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(bw2.d);
    }
}
