package org.chromium.chrome.browser.omnibox.suggestions.header;

import android.content.Context;
import defpackage.ee0;
import defpackage.eu0;
import defpackage.pr1;
import org.chromium.chrome.R$dimen;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class HeaderProcessor implements ee0 {
    public boolean a;
    public final Context b;

    public HeaderProcessor(Context context) {
        this.b = context;
        context.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_header_height);
    }

    @Override // defpackage.ee0
    public final void a() {
        this.a = pr1.e(this.b);
    }

    @Override // defpackage.ee0
    public final int m() {
        return 6;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(eu0.d);
    }
}
