package com.android.car.settings.bluetooth;

import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothPairingSelectionFragment extends SettingsFragment {
    private final BluetoothCallback mCallback = new BluetoothCallback() { // from class: com.android.car.settings.bluetooth.BluetoothPairingSelectionFragment.1
        @Override // com.android.settingslib.bluetooth.BluetoothCallback
        public void onScanningStateChanged(boolean started) {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothCallback
        public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
            if (bondState == 12) {
                BluetoothPairingSelectionFragment.this.goBack();
            }
        }
    };
    private LocalBluetoothManager mManager;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_pairing_selection_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LocalBluetoothManager localBtManager = BluetoothUtils.getLocalBtManager(context);
        this.mManager = localBtManager;
        if (localBtManager == null) {
            goBack();
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mManager.setForegroundActivity(requireActivity());
        this.mManager.getEventManager().registerCallback(this.mCallback);
        getToolbar().getProgressBar().setVisible(true);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mManager.setForegroundActivity(null);
        this.mManager.getEventManager().unregisterCallback(this.mCallback);
        getToolbar().getProgressBar().setVisible(false);
    }
}
