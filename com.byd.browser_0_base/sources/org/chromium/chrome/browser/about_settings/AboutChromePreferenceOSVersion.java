package org.chromium.chrome.browser.about_settings;

import android.R;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.jl3;
import defpackage.n32;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes.dex */
public class AboutChromePreferenceOSVersion extends LongClickCopySummaryPreference {
    public AboutChromePreferenceOSVersion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.about_settings.LongClickCopySummaryPreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        if (Build.VERSION.SDK_INT >= jl3.a.c()) {
            return;
        }
        LayoutInflater.from(this.e).inflate(R$layout.os_version_unsupported_text, (ViewGroup) n32Var.u(R.id.summary).getParent(), true);
    }
}
