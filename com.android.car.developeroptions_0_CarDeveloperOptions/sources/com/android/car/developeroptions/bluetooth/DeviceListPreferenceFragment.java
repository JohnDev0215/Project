package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.BidiFormatter;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.RestrictedDashboardFragment;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.BluetoothDeviceFilter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeviceListPreferenceFragment extends RestrictedDashboardFragment implements BluetoothCallback {
    BluetoothAdapter mBluetoothAdapter;
    PreferenceGroup mDeviceListGroup;
    final HashMap<CachedBluetoothDevice, BluetoothDevicePreference> mDevicePreferenceMap;
    private BluetoothDeviceFilter.Filter mFilter;
    LocalBluetoothManager mLocalManager;
    boolean mScanEnabled;
    BluetoothDevice mSelectedDevice;
    boolean mShowDevicesWithoutNames;

    public abstract String getDeviceListKey();

    protected void initDevicePreference(BluetoothDevicePreference bluetoothDevicePreference) {
    }

    abstract void initPreferencesFromPreferenceScreen();

    DeviceListPreferenceFragment(String str) {
        super(str);
        this.mDevicePreferenceMap = new HashMap<>();
        this.mFilter = BluetoothDeviceFilter.ALL_FILTER;
    }

    final void setFilter(BluetoothDeviceFilter.Filter filter) {
        this.mFilter = filter;
    }

    final void setFilter(int i) {
        this.mFilter = BluetoothDeviceFilter.getFilter(i);
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LocalBluetoothManager localBtManager = Utils.getLocalBtManager(getActivity());
        this.mLocalManager = localBtManager;
        if (localBtManager == null) {
            Log.e("DeviceListPreferenceFragment", "Bluetooth is not supported on this device");
            return;
        }
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mShowDevicesWithoutNames = SystemProperties.getBoolean("persist.bluetooth.showdeviceswithoutnames", false);
        initPreferencesFromPreferenceScreen();
        this.mDeviceListGroup = (PreferenceCategory) findPreference(getDeviceListKey());
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mLocalManager == null || isUiRestricted()) {
            return;
        }
        this.mLocalManager.setForegroundActivity(getActivity());
        this.mLocalManager.getEventManager().registerCallback(this);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mLocalManager == null || isUiRestricted()) {
            return;
        }
        removeAllDevices();
        this.mLocalManager.setForegroundActivity(null);
        this.mLocalManager.getEventManager().unregisterCallback(this);
    }

    void removeAllDevices() {
        this.mDevicePreferenceMap.clear();
        this.mDeviceListGroup.removeAll();
    }

    void addCachedDevices() {
        Iterator<CachedBluetoothDevice> it = this.mLocalManager.getCachedDeviceManager().getCachedDevicesCopy().iterator();
        while (it.hasNext()) {
            onDeviceAdded(it.next());
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if ("bt_scan".equals(preference.getKey())) {
            startScanning();
            return true;
        }
        if (preference instanceof BluetoothDevicePreference) {
            BluetoothDevicePreference bluetoothDevicePreference = (BluetoothDevicePreference) preference;
            this.mSelectedDevice = bluetoothDevicePreference.getCachedDevice().getDevice();
            onDevicePreferenceClick(bluetoothDevicePreference);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    void onDevicePreferenceClick(BluetoothDevicePreference bluetoothDevicePreference) {
        bluetoothDevicePreference.onClicked();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        if (this.mDevicePreferenceMap.get(cachedBluetoothDevice) == null && this.mBluetoothAdapter.getState() == 12 && this.mFilter.matches(cachedBluetoothDevice.getDevice())) {
            createDevicePreference(cachedBluetoothDevice);
        }
    }

    void createDevicePreference(CachedBluetoothDevice cachedBluetoothDevice) {
        if (this.mDeviceListGroup == null) {
            Log.w("DeviceListPreferenceFragment", "Trying to create a device preference before the list group/category exists!");
            return;
        }
        String address = cachedBluetoothDevice.getDevice().getAddress();
        BluetoothDevicePreference bluetoothDevicePreference = (BluetoothDevicePreference) getCachedPreference(address);
        if (bluetoothDevicePreference == null) {
            bluetoothDevicePreference = new BluetoothDevicePreference(getPrefContext(), cachedBluetoothDevice, this.mShowDevicesWithoutNames);
            bluetoothDevicePreference.setKey(address);
            bluetoothDevicePreference.hideSecondTarget(true);
            this.mDeviceListGroup.addPreference(bluetoothDevicePreference);
        }
        initDevicePreference(bluetoothDevicePreference);
        this.mDevicePreferenceMap.put(cachedBluetoothDevice, bluetoothDevicePreference);
    }

    void updateFooterPreference(Preference preference) {
        preference.setTitle(getString(R.string.bluetooth_footer_mac_message, BidiFormatter.getInstance().unicodeWrap(this.mBluetoothAdapter.getAddress())));
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        BluetoothDevicePreference bluetoothDevicePreferenceRemove = this.mDevicePreferenceMap.remove(cachedBluetoothDevice);
        if (bluetoothDevicePreferenceRemove != null) {
            this.mDeviceListGroup.removePreference(bluetoothDevicePreferenceRemove);
        }
    }

    void enableScanning() {
        startScanning();
        this.mScanEnabled = true;
    }

    void disableScanning() {
        stopScanning();
        this.mScanEnabled = false;
    }

    public void onScanningStateChanged(boolean z) {
        if (z || !this.mScanEnabled) {
            return;
        }
        startScanning();
    }

    public void addDeviceCategory(PreferenceGroup preferenceGroup, int i, BluetoothDeviceFilter.Filter filter, boolean z) {
        cacheRemoveAllPrefs(preferenceGroup);
        preferenceGroup.setTitle(i);
        this.mDeviceListGroup = preferenceGroup;
        if (z) {
            setFilter(BluetoothDeviceFilter.UNBONDED_DEVICE_FILTER);
            addCachedDevices();
        }
        setFilter(filter);
        preferenceGroup.setEnabled(true);
        removeCachedPrefs(preferenceGroup);
    }

    void startScanning() {
        if (this.mBluetoothAdapter.isDiscovering()) {
            return;
        }
        this.mBluetoothAdapter.startDiscovery();
    }

    void stopScanning() {
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
    }
}
