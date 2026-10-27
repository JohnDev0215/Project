package com.android.car.settings.bluetooth;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDeviceProfilesPreferenceController extends BluetoothDevicePreferenceController<PreferenceGroup> {
    private final Preference.OnPreferenceChangeListener mProfileChangeListener;

    static /* synthetic */ boolean lambda$new$0(Preference preference, Object newValue) {
        boolean isChecked = ((Boolean) newValue).booleanValue();
        BluetoothDeviceProfilePreference profilePref = (BluetoothDeviceProfilePreference) preference;
        LocalBluetoothProfile profile = profilePref.getProfile();
        profile.setEnabled(profilePref.getCachedDevice().getDevice(), isChecked);
        return true;
    }

    public BluetoothDeviceProfilesPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mProfileChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDeviceProfilesPreferenceController$ZXP3GiKC6PX6h3aojbf-KBCtlkM
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return BluetoothDeviceProfilesPreferenceController.lambda$new$0(preference, obj);
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        for (LocalBluetoothProfile profile : getCachedDevice().getProfiles()) {
            if (preferenceGroup.findPreference(profile.toString()) == null) {
                Preference profilePref = new BluetoothDeviceProfilePreference(getContext(), profile, getCachedDevice());
                profilePref.setOnPreferenceChangeListener(this.mProfileChangeListener);
                preferenceGroup.addPreference(profilePref);
            }
        }
        for (LocalBluetoothProfile removedProfile : getCachedDevice().getRemovedProfiles()) {
            Preference prefToRemove = preferenceGroup.findPreference(removedProfile.toString());
            if (prefToRemove != null) {
                preferenceGroup.removePreference(prefToRemove);
            }
        }
        preferenceGroup.setVisible(preferenceGroup.getPreferenceCount() > 0);
    }
}
