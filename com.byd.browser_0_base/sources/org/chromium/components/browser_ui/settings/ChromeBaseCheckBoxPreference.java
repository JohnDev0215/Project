package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.CheckBoxPreference;
import defpackage.n32;
import defpackage.nb1;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeBaseCheckBoxPreference extends CheckBoxPreference {
    public ChromeBaseCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        ((TextView) n32Var.u(R.id.title)).setSingleLine(false);
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public final void z() {
        if (nb1.e(null, this)) {
            return;
        }
        super.z();
    }
}
