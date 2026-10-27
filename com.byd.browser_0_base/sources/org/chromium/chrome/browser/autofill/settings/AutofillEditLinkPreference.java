package org.chromium.chrome.browser.autofill.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.Preference;
import defpackage.by1;
import defpackage.n32;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes.dex */
public class AutofillEditLinkPreference extends Preference {
    public AutofillEditLinkPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        O();
        this.K = R$layout.autofill_server_data_edit_link;
        S(R$string.autofill_from_google_account_long);
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        View viewU = n32Var.u(R$id.preference_click_target);
        viewU.setClickable(true);
        viewU.setOnClickListener(new by1(3, this));
    }
}
