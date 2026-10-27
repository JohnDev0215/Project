package com.android.car.developeroptions.accessibility;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class ToggleScreenMagnificationPreferenceFragmentForSetupWizard extends ToggleScreenMagnificationPreferenceFragment {
    @Override // com.android.car.developeroptions.accessibility.ToggleScreenMagnificationPreferenceFragment, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 368;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("checked") && this.mToggleSwitch.isChecked() != arguments.getBoolean("checked")) {
            this.mMetricsFeatureProvider.action(getContext(), 368, this.mToggleSwitch.isChecked());
        }
        super.onStop();
    }
}
