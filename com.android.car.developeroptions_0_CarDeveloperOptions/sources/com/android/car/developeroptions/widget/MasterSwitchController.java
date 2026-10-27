package com.android.car.developeroptions.widget;

import androidx.preference.Preference;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.RestrictedLockUtils;

/* JADX INFO: loaded from: classes.dex */
public class MasterSwitchController extends SwitchWidgetController implements Preference.OnPreferenceChangeListener {
    private final MasterSwitchPreference mPreference;

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void updateTitle(boolean z) {
    }

    public MasterSwitchController(MasterSwitchPreference masterSwitchPreference) {
        this.mPreference = masterSwitchPreference;
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void startListening() {
        this.mPreference.setOnPreferenceChangeListener(this);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void stopListening() {
        this.mPreference.setOnPreferenceChangeListener(null);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setChecked(boolean z) {
        this.mPreference.setChecked(z);
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public boolean isChecked() {
        return this.mPreference.isChecked();
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setEnabled(boolean z) {
        this.mPreference.setSwitchEnabled(z);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        SwitchWidgetController.OnSwitchChangeListener onSwitchChangeListener = this.mListener;
        if (onSwitchChangeListener != null) {
            return onSwitchChangeListener.onSwitchToggled(((Boolean) obj).booleanValue());
        }
        return false;
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController
    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mPreference.setDisabledByAdmin(enforcedAdmin);
    }
}
