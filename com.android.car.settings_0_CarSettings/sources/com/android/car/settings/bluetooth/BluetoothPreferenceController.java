package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BluetoothPreferenceController<V extends Preference> extends PreferenceController<V> implements BluetoothCallback {
    private final LocalBluetoothManager mBluetoothManager;
    private final UserManager mUserManager;

    public BluetoothPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mBluetoothManager = BluetoothUtils.getLocalBtManager(context);
        this.mUserManager = UserManager.get(context);
    }

    protected final UserManager getUserManager() {
        return this.mUserManager;
    }

    protected final LocalBluetoothManager getBluetoothManager() {
        return this.mBluetoothManager;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (!getContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth")) {
            return 2;
        }
        if (this.mUserManager.hasUserRestriction("no_bluetooth")) {
            return 3;
        }
        return BluetoothAdapter.getDefaultAdapter().isEnabled() ? 0 : 1;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mBluetoothManager.getEventManager().registerCallback(this);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mBluetoothManager.getEventManager().unregisterCallback(this);
    }

    public void onBluetoothStateChanged(int bluetoothState) {
        refreshUi();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onScanningStateChanged(boolean started) {
    }

    public void onDeviceAdded(CachedBluetoothDevice cachedDevice) {
    }

    public void onDeviceDeleted(CachedBluetoothDevice cachedDevice) {
    }

    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onConnectionStateChanged(CachedBluetoothDevice cachedDevice, int state) {
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onActiveDeviceChanged(CachedBluetoothDevice activeDevice, int bluetoothProfile) {
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public void onAudioModeChanged() {
    }
}
