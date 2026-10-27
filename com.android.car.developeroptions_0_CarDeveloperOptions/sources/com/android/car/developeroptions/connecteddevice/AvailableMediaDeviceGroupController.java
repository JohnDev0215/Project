package com.android.car.developeroptions.connecteddevice;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.bluetooth.AvailableMediaBluetoothDeviceUpdater;
import com.android.car.developeroptions.bluetooth.BluetoothDeviceUpdater;
import com.android.car.developeroptions.bluetooth.Utils;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class AvailableMediaDeviceGroupController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, DevicePreferenceCallback, BluetoothCallback {
    private static final String KEY = "available_device_list";
    private static final String TAG = "AvailableMediaDeviceGroupController";
    private BluetoothDeviceUpdater mBluetoothDeviceUpdater;
    LocalBluetoothManager mLocalBluetoothManager;
    PreferenceGroup mPreferenceGroup;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onAclConnectionStateChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onActiveDeviceChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onBluetoothStateChanged(int i) {
        super.onBluetoothStateChanged(i);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onConnectionStateChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        super.onDeviceAdded(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onDeviceBondStateChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        super.onDeviceDeleted(cachedBluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        super.onProfileConnectionStateChanged(cachedBluetoothDevice, i, i2);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onScanningStateChanged(boolean z) {
        super.onScanningStateChanged(z);
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AvailableMediaDeviceGroupController(Context context) {
        super(context, KEY);
        this.mLocalBluetoothManager = Utils.getLocalBtManager(this.mContext);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        if (this.mLocalBluetoothManager == null) {
            Log.e(TAG, "onStart() Bluetooth is not supported on this device");
        } else {
            this.mBluetoothDeviceUpdater.registerCallback();
            this.mLocalBluetoothManager.getEventManager().registerCallback(this);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        if (this.mLocalBluetoothManager == null) {
            Log.e(TAG, "onStop() Bluetooth is not supported on this device");
        } else {
            this.mBluetoothDeviceUpdater.unregisterCallback();
            this.mLocalBluetoothManager.getEventManager().unregisterCallback(this);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(KEY);
            this.mPreferenceGroup = preferenceGroup;
            preferenceGroup.setVisible(false);
            updateTitle();
            this.mBluetoothDeviceUpdater.setPrefContext(preferenceScreen.getContext());
            this.mBluetoothDeviceUpdater.forceUpdate();
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? 1 : 3;
    }

    @Override // com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback
    public void onDeviceAdded(Preference preference) {
        if (this.mPreferenceGroup.getPreferenceCount() == 0) {
            this.mPreferenceGroup.setVisible(true);
        }
        this.mPreferenceGroup.addPreference(preference);
    }

    @Override // com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback
    public void onDeviceRemoved(Preference preference) {
        this.mPreferenceGroup.removePreference(preference);
        if (this.mPreferenceGroup.getPreferenceCount() == 0) {
            this.mPreferenceGroup.setVisible(false);
        }
    }

    public void init(DashboardFragment dashboardFragment) {
        this.mBluetoothDeviceUpdater = new AvailableMediaBluetoothDeviceUpdater(dashboardFragment.getContext(), dashboardFragment, this);
    }

    public void setBluetoothDeviceUpdater(BluetoothDeviceUpdater bluetoothDeviceUpdater) {
        this.mBluetoothDeviceUpdater = bluetoothDeviceUpdater;
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onAudioModeChanged() {
        updateTitle();
    }

    private void updateTitle() {
        if (com.android.settingslib.Utils.isAudioModeOngoingCall(this.mContext)) {
            this.mPreferenceGroup.setTitle(this.mContext.getString(R.string.connected_device_available_call_title));
        } else {
            this.mPreferenceGroup.setTitle(this.mContext.getString(R.string.connected_device_available_media_title));
        }
    }
}
