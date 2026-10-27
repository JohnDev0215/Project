package com.android.car.settings.bluetooth;

import android.content.Context;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDevicePickerFragment extends SettingsFragment {
    private LocalBluetoothManager mManager;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_device_picker_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LocalBluetoothManager localBtManager = BluetoothUtils.getLocalBtManager(context);
        this.mManager = localBtManager;
        if (localBtManager == null) {
            goBack();
        } else {
            ((BluetoothDevicePickerPreferenceController) use(BluetoothDevicePickerPreferenceController.class, R.string.pk_bluetooth_device_picker)).setLaunchIntent(requireActivity().getIntent());
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mManager.setForegroundActivity(requireActivity());
        getToolbar().getProgressBar().setVisible(true);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mManager.setForegroundActivity(null);
        getToolbar().getProgressBar().setVisible(false);
    }
}
