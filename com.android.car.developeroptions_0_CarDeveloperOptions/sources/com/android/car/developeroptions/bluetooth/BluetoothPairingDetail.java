package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.android.car.developeroptions.R;
import com.android.settingslib.bluetooth.BluetoothDeviceFilter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.widget.FooterPreference;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPairingDetail extends DeviceListPreferenceFragment {
    static final String KEY_AVAIL_DEVICES = "available_devices";
    static final String KEY_FOOTER_PREF = "footer_preference";
    AlwaysDiscoverable mAlwaysDiscoverable;
    BluetoothProgressCategory mAvailableDevicesCategory;
    FooterPreference mFooterPreference;
    private boolean mInitialScanStarted;

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    public String getDeviceListKey() {
        return KEY_AVAIL_DEVICES;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_bluetooth;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "BluetoothPairingDetail";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1018;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_pairing_detail;
    }

    public BluetoothPairingDetail() {
        super("no_config_bluetooth");
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mInitialScanStarted = false;
        this.mAlwaysDiscoverable = new AlwaysDiscoverable(getContext());
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mLocalManager == null) {
            Log.e("BluetoothPairingDetail", "Bluetooth is not supported on this device");
        } else {
            updateBluetooth();
            this.mAvailableDevicesCategory.setProgress(this.mBluetoothAdapter.isDiscovering());
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((BluetoothDeviceRenamePreferenceController) use(BluetoothDeviceRenamePreferenceController.class)).setFragment(this);
    }

    void updateBluetooth() {
        if (this.mBluetoothAdapter.isEnabled()) {
            updateContent(this.mBluetoothAdapter.getState());
        } else {
            this.mBluetoothAdapter.enable();
        }
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mLocalManager == null) {
            Log.e("BluetoothPairingDetail", "Bluetooth is not supported on this device");
        } else {
            this.mAlwaysDiscoverable.stop();
            disableScanning();
        }
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    void initPreferencesFromPreferenceScreen() {
        this.mAvailableDevicesCategory = (BluetoothProgressCategory) findPreference(KEY_AVAIL_DEVICES);
        FooterPreference footerPreference = (FooterPreference) findPreference(KEY_FOOTER_PREF);
        this.mFooterPreference = footerPreference;
        footerPreference.setSelectable(false);
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    void enableScanning() {
        if (!this.mInitialScanStarted) {
            if (this.mAvailableDevicesCategory != null) {
                removeAllDevices();
            }
            this.mLocalManager.getCachedDeviceManager().clearNonBondedDevices();
            this.mInitialScanStarted = true;
        }
        super.enableScanning();
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    void onDevicePreferenceClick(BluetoothDevicePreference bluetoothDevicePreference) {
        disableScanning();
        super.onDevicePreferenceClick(bluetoothDevicePreference);
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.settingslib.bluetooth.BluetoothCallback
    public void onScanningStateChanged(boolean z) {
        super.onScanningStateChanged(z);
        this.mAvailableDevicesCategory.setProgress(z | this.mScanEnabled);
    }

    void updateContent(int i) {
        if (i == 10) {
            finish();
            return;
        }
        if (i != 12) {
            return;
        }
        this.mDevicePreferenceMap.clear();
        this.mBluetoothAdapter.enable();
        addDeviceCategory(this.mAvailableDevicesCategory, R.string.bluetooth_preference_found_media_devices, BluetoothDeviceFilter.ALL_FILTER, this.mInitialScanStarted);
        updateFooterPreference(this.mFooterPreference);
        this.mAlwaysDiscoverable.start();
        enableScanning();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onBluetoothStateChanged(int i) {
        super.onBluetoothStateChanged(i);
        updateContent(i);
        if (i == 12) {
            showBluetoothTurnedOnToast();
        }
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        BluetoothDevice device;
        if (i == 12) {
            finish();
            return;
        }
        if (this.mSelectedDevice == null || cachedBluetoothDevice == null || (device = cachedBluetoothDevice.getDevice()) == null || !this.mSelectedDevice.equals(device) || i != 10) {
            return;
        }
        enableScanning();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        BluetoothDevice device;
        if (this.mSelectedDevice == null || (device = cachedBluetoothDevice.getDevice()) == null || !this.mSelectedDevice.equals(device) || i != 2) {
            return;
        }
        finish();
    }

    void showBluetoothTurnedOnToast() {
        Toast.makeText(getContext(), R.string.connected_device_bluetooth_turned_on_toast, 0).show();
    }
}
