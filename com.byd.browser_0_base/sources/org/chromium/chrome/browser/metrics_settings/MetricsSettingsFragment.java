package org.chromium.chrome.browser.metrics_settings;

import android.os.Bundle;
import androidx.preference.d;
import defpackage.qj2;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsSettingsFragment extends d {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.prefs_metrics_settings);
        qj2.a(this, R$xml.metrics_preferences);
    }
}
