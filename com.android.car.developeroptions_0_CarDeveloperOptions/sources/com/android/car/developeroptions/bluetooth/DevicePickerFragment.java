package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.Menu;
import android.view.MenuInflater;
import com.android.car.developeroptions.R;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DevicePickerFragment extends DeviceListPreferenceFragment {
    BluetoothProgressCategory mAvailableDevicesCategory;
    private String mLaunchClass;
    private String mLaunchPackage;
    private boolean mNeedAuth;
    private boolean mScanAllowed;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return null;
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    public String getDeviceListKey() {
        return "bt_device_list";
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DevicePickerFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 25;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.device_picker;
    }

    public DevicePickerFragment() {
        super(null);
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    void initPreferencesFromPreferenceScreen() {
        Intent intent = getActivity().getIntent();
        this.mNeedAuth = intent.getBooleanExtra("android.bluetooth.devicepicker.extra.NEED_AUTH", false);
        setFilter(intent.getIntExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 0));
        this.mLaunchPackage = intent.getStringExtra("android.bluetooth.devicepicker.extra.LAUNCH_PACKAGE");
        this.mLaunchClass = intent.getStringExtra("android.bluetooth.devicepicker.extra.DEVICE_PICKER_LAUNCH_CLASS");
        this.mAvailableDevicesCategory = (BluetoothProgressCategory) findPreference("bt_device_list");
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(getString(R.string.device_picker));
        this.mScanAllowed = !((UserManager) getSystemService("user")).hasUserRestriction("no_config_bluetooth");
        setHasOptionsMenu(true);
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        addCachedDevices();
        this.mSelectedDevice = null;
        if (this.mScanAllowed) {
            enableScanning();
            this.mAvailableDevicesCategory.setProgress(this.mBluetoothAdapter.isDiscovering());
        }
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        disableScanning();
        super.onStop();
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mSelectedDevice == null) {
            sendDevicePickedIntent(null);
        }
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    void onDevicePreferenceClick(BluetoothDevicePreference bluetoothDevicePreference) {
        disableScanning();
        LocalBluetoothPreferences.persistSelectedDeviceInPicker(getActivity(), this.mSelectedDevice.getAddress());
        if (bluetoothDevicePreference.getCachedDevice().getBondState() == 12 || !this.mNeedAuth) {
            sendDevicePickedIntent(this.mSelectedDevice);
            finish();
        } else {
            super.onDevicePreferenceClick(bluetoothDevicePreference);
        }
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment, com.android.settingslib.bluetooth.BluetoothCallback
    public void onScanningStateChanged(boolean z) {
        super.onScanningStateChanged(z);
        this.mAvailableDevicesCategory.setProgress(z | this.mScanEnabled);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (device.equals(this.mSelectedDevice)) {
            if (i == 12) {
                sendDevicePickedIntent(device);
                finish();
            } else if (i == 10) {
                enableScanning();
            }
        }
    }

    @Override // com.android.car.developeroptions.bluetooth.DeviceListPreferenceFragment
    protected void initDevicePreference(BluetoothDevicePreference bluetoothDevicePreference) {
        super.initDevicePreference(bluetoothDevicePreference);
        bluetoothDevicePreference.setNeedNotifyHierarchyChanged(true);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onBluetoothStateChanged(int i) {
        super.onBluetoothStateChanged(i);
        if (i == 12) {
            enableScanning();
        }
    }

    private void sendDevicePickedIntent(BluetoothDevice bluetoothDevice) {
        String str;
        Intent intent = new Intent("android.bluetooth.devicepicker.action.DEVICE_SELECTED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", bluetoothDevice);
        String str2 = this.mLaunchPackage;
        if (str2 != null && (str = this.mLaunchClass) != null) {
            intent.setClassName(str2, str);
        }
        getActivity().sendBroadcast(intent);
    }
}
