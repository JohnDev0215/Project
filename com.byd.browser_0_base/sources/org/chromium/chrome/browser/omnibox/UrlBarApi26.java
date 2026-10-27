package org.chromium.chrome.browser.omnibox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewStructure;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class UrlBarApi26 extends UrlBar {
    public UrlBarApi26(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView, android.view.View
    public int getAutofillType() {
        return 0;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        CharSequence hint = super.getHint();
        return (getId() == R$id.url_bar && TextUtils.isEmpty(hint)) ? getContext().getString(R$string.search_or_type_web_address) : hint;
    }

    @Override // android.view.View
    public final void onProvideAutofillStructure(ViewStructure viewStructure, int i) {
        this.T = true;
        super.onProvideAutofillStructure(viewStructure, i);
        this.T = false;
    }
}
