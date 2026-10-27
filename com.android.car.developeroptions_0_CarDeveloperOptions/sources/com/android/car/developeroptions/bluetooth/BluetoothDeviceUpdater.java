package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class BluetoothDeviceUpdater implements BluetoothCallback, LocalBluetoothProfileManager.ServiceListener {
    protected final DevicePreferenceCallback mDevicePreferenceCallback;
    final GearPreference.OnGearClickListener mDeviceProfilesListener;
    protected DashboardFragment mFragment;
    protected LocalBluetoothManager mLocalManager;
    protected Context mPrefContext;
    protected final Map<BluetoothDevice, Preference> mPreferenceMap;

    public abstract boolean isFilterMatched(CachedBluetoothDevice cachedBluetoothDevice);

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.ServiceListener
    public void onServiceDisconnected() {
    }

    public BluetoothDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        this(dashboardFragment, devicePreferenceCallback, Utils.getLocalBtManager(context));
    }

    BluetoothDeviceUpdater(DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback, LocalBluetoothManager localBluetoothManager) {
        this.mDeviceProfilesListener = new GearPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$BluetoothDeviceUpdater$jia2gvN-rnxx0RlRU4ryfqBlIGE
            @Override // com.android.car.developeroptions.widget.GearPreference.OnGearClickListener
            public final void onGearClick(GearPreference gearPreference) {
                this.f$0.lambda$new$0$BluetoothDeviceUpdater(gearPreference);
            }
        };
        this.mFragment = dashboardFragment;
        this.mDevicePreferenceCallback = devicePreferenceCallback;
        this.mPreferenceMap = new HashMap();
        this.mLocalManager = localBluetoothManager;
    }

    public void registerCallback() {
        LocalBluetoothManager localBluetoothManager = this.mLocalManager;
        if (localBluetoothManager == null) {
            Log.e("BluetoothDeviceUpdater", "registerCallback() Bluetooth is not supported on this device");
            return;
        }
        localBluetoothManager.setForegroundActivity(this.mFragment.getContext());
        this.mLocalManager.getEventManager().registerCallback(this);
        this.mLocalManager.getProfileManager().addServiceListener(this);
        forceUpdate();
    }

    public void unregisterCallback() {
        LocalBluetoothManager localBluetoothManager = this.mLocalManager;
        if (localBluetoothManager == null) {
            Log.e("BluetoothDeviceUpdater", "unregisterCallback() Bluetooth is not supported on this device");
            return;
        }
        localBluetoothManager.setForegroundActivity(null);
        this.mLocalManager.getEventManager().unregisterCallback(this);
        this.mLocalManager.getProfileManager().removeServiceListener(this);
    }

    public void forceUpdate() {
        if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            Iterator<CachedBluetoothDevice> it = this.mLocalManager.getCachedDeviceManager().getCachedDevicesCopy().iterator();
            while (it.hasNext()) {
                update(it.next());
            }
        }
    }

    public void removeAllDevicesFromPreference() {
        Iterator<CachedBluetoothDevice> it = this.mLocalManager.getCachedDeviceManager().getCachedDevicesCopy().iterator();
        while (it.hasNext()) {
            removePreference(it.next());
        }
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onBluetoothStateChanged(int i) {
        if (12 == i) {
            forceUpdate();
        } else if (10 == i) {
            removeAllDevicesFromPreference();
        }
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        update(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        removePreference(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        update(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        Log.d("BluetoothDeviceUpdater", "onProfileConnectionStateChanged() device: " + cachedBluetoothDevice.getName() + ", state: " + i + ", bluetoothProfile: " + i2);
        update(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        Log.d("BluetoothDeviceUpdater", "onAclConnectionStateChanged() device: " + cachedBluetoothDevice.getName() + ", state: " + i);
        update(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.ServiceListener
    public void onServiceConnected() {
        forceUpdate();
    }

    public void setPrefContext(Context context) {
        this.mPrefContext = context;
    }

    protected void update(CachedBluetoothDevice cachedBluetoothDevice) {
        if (isFilterMatched(cachedBluetoothDevice)) {
            addPreference(cachedBluetoothDevice);
        } else {
            removePreference(cachedBluetoothDevice);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void addPreference(CachedBluetoothDevice cachedBluetoothDevice) {
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (this.mPreferenceMap.containsKey(device)) {
            return;
        }
        BluetoothDevicePreference bluetoothDevicePreference = new BluetoothDevicePreference(this.mPrefContext, cachedBluetoothDevice, true);
        bluetoothDevicePreference.setOnGearClickListener(this.mDeviceProfilesListener);
        if (this instanceof Preference.OnPreferenceClickListener) {
            bluetoothDevicePreference.setOnPreferenceClickListener((Preference.OnPreferenceClickListener) this);
        }
        this.mPreferenceMap.put(device, bluetoothDevicePreference);
        this.mDevicePreferenceCallback.onDeviceAdded(bluetoothDevicePreference);
    }

    protected void removePreference(CachedBluetoothDevice cachedBluetoothDevice) {
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        CachedBluetoothDevice subDevice = cachedBluetoothDevice.getSubDevice();
        if (this.mPreferenceMap.containsKey(device)) {
            this.mDevicePreferenceCallback.onDeviceRemoved(this.mPreferenceMap.get(device));
            this.mPreferenceMap.remove(device);
        } else if (subDevice != null) {
            BluetoothDevice device2 = subDevice.getDevice();
            if (this.mPreferenceMap.containsKey(device2)) {
                this.mDevicePreferenceCallback.onDeviceRemoved(this.mPreferenceMap.get(device2));
                this.mPreferenceMap.remove(device2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: launchDeviceDetails, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$BluetoothDeviceUpdater(Preference preference) {
        CachedBluetoothDevice bluetoothDevice = ((BluetoothDevicePreference) preference).getBluetoothDevice();
        if (bluetoothDevice == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("device_address", bluetoothDevice.getDevice().getAddress());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mFragment.getContext());
        subSettingLauncher.setDestination(BluetoothDeviceDetailsFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.device_details_title);
        subSettingLauncher.setSourceMetricsCategory(this.mFragment.getMetricsCategory());
        subSettingLauncher.launch();
    }

    public boolean isDeviceConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice == null) {
            return false;
        }
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        Log.d("BluetoothDeviceUpdater", "isDeviceConnected() device name : " + cachedBluetoothDevice.getName() + ", is connected : " + device.isConnected() + " , is profile connected : " + cachedBluetoothDevice.isConnected());
        return device.getBondState() == 12 && device.isConnected();
    }
}
