package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class LongSummaryTextMessagePreference extends TextMessagePreference {
    public LongSummaryTextMessagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.long_summary_text_message_preference;
    }

    @Override // org.chromium.components.browser_ui.settings.TextMessagePreference, org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        ((TextView) n32Var.u(R.id.summary)).setMaxLines(100);
    }
}
