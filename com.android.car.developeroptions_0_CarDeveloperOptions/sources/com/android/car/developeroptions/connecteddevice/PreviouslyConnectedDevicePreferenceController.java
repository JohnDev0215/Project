package com.android.car.developeroptions.connecteddevice;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.bluetooth.BluetoothDeviceUpdater;
import com.android.car.developeroptions.bluetooth.SavedBluetoothDeviceUpdater;
import com.android.car.developeroptions.connecteddevice.dock.DockUpdater;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class PreviouslyConnectedDevicePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, DevicePreferenceCallback {
    private static final int MAX_DEVICE_NUM = 3;
    private BluetoothDeviceUpdater mBluetoothDeviceUpdater;
    private PreferenceGroup mPreferenceGroup;
    private int mPreferenceSize;
    private DockUpdater mSavedDockUpdater;

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

    public PreviouslyConnectedDevicePreferenceController(Context context, String str) {
        super(context, str);
        this.mSavedDockUpdater = FeatureFactory.getFactory(context).getDockUpdaterFeatureProvider().getSavedDockUpdater(context, this);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreferenceGroup = preferenceGroup;
        preferenceGroup.setVisible(false);
        if (isAvailable()) {
            Context context = preferenceScreen.getContext();
            this.mBluetoothDeviceUpdater.setPrefContext(context);
            this.mSavedDockUpdater.setPreferenceContext(context);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mBluetoothDeviceUpdater.registerCallback();
        this.mSavedDockUpdater.registerCallback();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mBluetoothDeviceUpdater.unregisterCallback();
        this.mSavedDockUpdater.unregisterCallback();
    }

    public void init(DashboardFragment dashboardFragment) {
        this.mBluetoothDeviceUpdater = new SavedBluetoothDeviceUpdater(dashboardFragment.getContext(), dashboardFragment, this);
    }

    @Override // com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback
    public void onDeviceAdded(Preference preference) {
        int i = this.mPreferenceSize + 1;
        this.mPreferenceSize = i;
        if (i <= 3) {
            this.mPreferenceGroup.addPreference(preference);
        }
        updatePreferenceVisiblity();
    }

    @Override // com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback
    public void onDeviceRemoved(Preference preference) {
        this.mPreferenceSize--;
        this.mPreferenceGroup.removePreference(preference);
        updatePreferenceVisiblity();
    }

    void setBluetoothDeviceUpdater(BluetoothDeviceUpdater bluetoothDeviceUpdater) {
        this.mBluetoothDeviceUpdater = bluetoothDeviceUpdater;
    }

    void setSavedDockUpdater(DockUpdater dockUpdater) {
        this.mSavedDockUpdater = dockUpdater;
    }

    void setPreferenceGroup(PreferenceGroup preferenceGroup) {
        this.mPreferenceGroup = preferenceGroup;
    }

    void updatePreferenceVisiblity() {
        this.mPreferenceGroup.setVisible(this.mPreferenceSize > 0);
    }
}
