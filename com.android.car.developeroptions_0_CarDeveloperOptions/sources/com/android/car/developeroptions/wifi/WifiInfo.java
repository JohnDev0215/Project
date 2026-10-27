package com.android.car.developeroptions.wifi;

import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public class WifiInfo extends SettingsPreferenceFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 89;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.testing_wifi_settings);
    }
}
