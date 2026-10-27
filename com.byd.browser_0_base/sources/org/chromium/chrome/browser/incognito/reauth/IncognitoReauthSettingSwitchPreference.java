package org.chromium.chrome.browser.incognito.reauth;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import defpackage.ao3;
import defpackage.n32;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class IncognitoReauthSettingSwitchPreference extends ChromeSwitchPreference {
    public boolean e0;
    public Runnable f0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            IncognitoReauthSettingSwitchPreference.this.f0.run();
        }
    }

    public IncognitoReauthSettingSwitchPreference(Context context) {
        super(context);
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeSwitchPreference, androidx.preference.SwitchPreferenceCompat, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        ao3.g(n32Var.e, this.e0);
        if (this.e0) {
            return;
        }
        TextView textView = (TextView) n32Var.u(R.id.summary);
        textView.setEnabled(true);
        textView.setOnClickListener(new a());
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeSwitchPreference, androidx.preference.TwoStatePreference, androidx.preference.Preference
    public final void z() {
        if (this.e0) {
            super.z();
        }
    }

    public IncognitoReauthSettingSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
