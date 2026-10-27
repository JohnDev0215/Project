package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BluetoothScanningDevicesGroupPreferenceController extends BluetoothDevicesGroupPreferenceController {
    private static final Logger LOG = new Logger((Class<?>) BluetoothScanningDevicesGroupPreferenceController.class);
    private final AlwaysDiscoverable mAlwaysDiscoverable;
    private final BluetoothAdapter mBluetoothAdapter;
    private boolean mIsScanningEnabled;

    protected abstract void onDeviceClickedInternal(CachedBluetoothDevice cachedBluetoothDevice);

    public BluetoothScanningDevicesGroupPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAlwaysDiscoverable = new AlwaysDiscoverable(context, this.mBluetoothAdapter);
    }

    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController
    protected final void onDeviceClicked(CachedBluetoothDevice cachedDevice) {
        LOG.d("onDeviceClicked: " + cachedDevice);
        disableScanning();
        onDeviceClickedInternal(cachedDevice);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        super.onStopInternal();
        disableScanning();
        getBluetoothManager().getCachedDeviceManager().clearNonBondedDevices();
        getPreferenceMap().clear();
        ((PreferenceGroup) getPreference()).removeAll();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        super.updateState(preferenceGroup);
        if (shouldEnableScanning()) {
            enableScanning();
        } else {
            disableScanning();
        }
    }

    private boolean shouldEnableScanning() {
        for (CachedBluetoothDevice device : getPreferenceMap().keySet()) {
            if (device.getBondState() == 11) {
                return false;
            }
        }
        return !getUserManager().hasUserRestriction("no_config_bluetooth");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void enableScanning() {
        this.mIsScanningEnabled = true;
        if (!this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.startDiscovery();
        }
        this.mAlwaysDiscoverable.start();
        ((PreferenceGroup) getPreference()).setEnabled(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void disableScanning() {
        this.mIsScanningEnabled = false;
        ((PreferenceGroup) getPreference()).setEnabled(false);
        this.mAlwaysDiscoverable.stop();
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public void onScanningStateChanged(boolean started) {
        LOG.d("onScanningStateChanged started: " + started + " mIsScanningEnabled: " + this.mIsScanningEnabled);
        if (!started && this.mIsScanningEnabled) {
            enableScanning();
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
        LOG.d("onDeviceBondStateChanged device: " + cachedDevice + " state: " + bondState);
        refreshUi();
    }

    private static final class AlwaysDiscoverable extends BroadcastReceiver {
        private final BluetoothAdapter mAdapter;
        private final Context mContext;
        private final IntentFilter mIntentFilter = new IntentFilter("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
        private boolean mStarted;

        AlwaysDiscoverable(Context context, BluetoothAdapter adapter) {
            this.mContext = context;
            this.mAdapter = adapter;
        }

        void start() {
            if (this.mStarted) {
                return;
            }
            this.mContext.registerReceiver(this, this.mIntentFilter);
            this.mStarted = true;
            setDiscoverable();
        }

        void stop() {
            if (!this.mStarted) {
                return;
            }
            this.mContext.unregisterReceiver(this);
            this.mStarted = false;
            this.mAdapter.setScanMode(21);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            setDiscoverable();
        }

        private void setDiscoverable() {
            if (this.mAdapter.getScanMode() != 23) {
                this.mAdapter.setScanMode(23);
            }
        }
    }
}
