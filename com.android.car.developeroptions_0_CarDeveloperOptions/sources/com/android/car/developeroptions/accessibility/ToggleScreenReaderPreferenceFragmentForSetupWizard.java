package com.android.car.developeroptions.accessibility;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class ToggleScreenReaderPreferenceFragmentForSetupWizard extends ToggleAccessibilityServicePreferenceFragment {
    private boolean mToggleSwitchWasInitiallyChecked;

    @Override // com.android.car.developeroptions.accessibility.ToggleAccessibilityServicePreferenceFragment, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 371;
    }

    @Override // com.android.car.developeroptions.accessibility.ToggleAccessibilityServicePreferenceFragment, com.android.car.developeroptions.accessibility.ToggleFeaturePreferenceFragment
    protected void onProcessArguments(Bundle bundle) {
        super.onProcessArguments(bundle);
        this.mToggleSwitchWasInitiallyChecked = this.mToggleSwitch.isChecked();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        if (this.mToggleSwitch.isChecked() != this.mToggleSwitchWasInitiallyChecked) {
            this.mMetricsFeatureProvider.action(getContext(), 371, this.mToggleSwitch.isChecked());
        }
        super.onStop();
    }
}
