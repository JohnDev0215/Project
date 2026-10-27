package com.android.car.developeroptions.connecteddevice;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.bluetooth.BluetoothDeviceUpdater;
import com.android.car.developeroptions.bluetooth.ConnectedBluetoothDeviceUpdater;
import com.android.car.developeroptions.connecteddevice.dock.DockUpdater;
import com.android.car.developeroptions.connecteddevice.usb.ConnectedUsbDeviceUpdater;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedDeviceGroupController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop, DevicePreferenceCallback {
    private static final String KEY = "connected_device_list";
    private BluetoothDeviceUpdater mBluetoothDeviceUpdater;
    private DockUpdater mConnectedDockUpdater;
    private ConnectedUsbDeviceUpdater mConnectedUsbDeviceUpdater;
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

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public ConnectedDeviceGroupController(Context context) {
        super(context, KEY);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mBluetoothDeviceUpdater.registerCallback();
        this.mConnectedUsbDeviceUpdater.registerCallback();
        this.mConnectedDockUpdater.registerCallback();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mConnectedUsbDeviceUpdater.unregisterCallback();
        this.mBluetoothDeviceUpdater.unregisterCallback();
        this.mConnectedDockUpdater.unregisterCallback();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(KEY);
            this.mPreferenceGroup = preferenceGroup;
            preferenceGroup.setVisible(false);
            Context context = preferenceScreen.getContext();
            this.mBluetoothDeviceUpdater.setPrefContext(context);
            this.mBluetoothDeviceUpdater.forceUpdate();
            this.mConnectedUsbDeviceUpdater.initUsbPreference(context);
            this.mConnectedDockUpdater.setPreferenceContext(context);
            this.mConnectedDockUpdater.forceUpdate();
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

    public void init(BluetoothDeviceUpdater bluetoothDeviceUpdater, ConnectedUsbDeviceUpdater connectedUsbDeviceUpdater, DockUpdater dockUpdater) {
        this.mBluetoothDeviceUpdater = bluetoothDeviceUpdater;
        this.mConnectedUsbDeviceUpdater = connectedUsbDeviceUpdater;
        this.mConnectedDockUpdater = dockUpdater;
    }

    public void init(DashboardFragment dashboardFragment) {
        Context context = dashboardFragment.getContext();
        init(new ConnectedBluetoothDeviceUpdater(context, dashboardFragment, this), new ConnectedUsbDeviceUpdater(context, dashboardFragment, this), FeatureFactory.getFactory(context).getDockUpdaterFeatureProvider().getConnectedDockUpdater(context, this));
    }
}
