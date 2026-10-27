package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.settingslib.bluetooth.BluetoothDeviceFilter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDevicePickerPreferenceController extends BluetoothScanningDevicesGroupPreferenceController {
    private static final Logger LOG = new Logger((Class<?>) BluetoothDevicePickerPreferenceController.class);
    private BluetoothDeviceFilter.Filter mFilter;
    private String mLaunchClass;
    private String mLaunchPackage;
    private boolean mNeedAuth;
    private CachedBluetoothDevice mSelectedDevice;

    public BluetoothDevicePickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    public void setLaunchIntent(Intent intent) {
        this.mNeedAuth = intent.getBooleanExtra("android.bluetooth.devicepicker.extra.NEED_AUTH", false);
        this.mFilter = BluetoothDeviceFilter.getFilter(intent.getIntExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 0));
        this.mLaunchPackage = intent.getStringExtra("android.bluetooth.devicepicker.extra.LAUNCH_PACKAGE");
        this.mLaunchClass = intent.getStringExtra("android.bluetooth.devicepicker.extra.DEVICE_PICKER_LAUNCH_CLASS");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mFilter == null) {
            throw new IllegalStateException("launch intent must be set");
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController
    protected BluetoothDeviceFilter.Filter getDeviceFilter() {
        return this.mFilter;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothScanningDevicesGroupPreferenceController
    protected void onDeviceClickedInternal(CachedBluetoothDevice cachedDevice) {
        this.mSelectedDevice = cachedDevice;
        BluetoothUtils.persistSelectedDeviceInPicker(getContext(), cachedDevice.getAddress());
        if (cachedDevice.getBondState() == 12 || !this.mNeedAuth) {
            sendDevicePickedIntent(cachedDevice.getDevice());
            getFragmentController().goBack();
        } else if (cachedDevice.startPairing()) {
            LOG.d("startPairing");
        } else {
            BluetoothUtils.showError(getContext(), cachedDevice.getName(), R.string.bluetooth_pairing_error_message);
            refreshUi();
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        super.onStartInternal();
        this.mSelectedDevice = null;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        super.onDestroyInternal();
        if (this.mSelectedDevice == null) {
            sendDevicePickedIntent(null);
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothScanningDevicesGroupPreferenceController, com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
        super.onDeviceBondStateChanged(cachedDevice, bondState);
        if (bondState == 12 && cachedDevice.equals(this.mSelectedDevice)) {
            sendDevicePickedIntent(this.mSelectedDevice.getDevice());
            getFragmentController().goBack();
        }
    }

    private void sendDevicePickedIntent(BluetoothDevice device) {
        String str;
        LOG.d("sendDevicePickedIntent device: " + device + " package: " + this.mLaunchPackage + " class: " + this.mLaunchClass);
        Intent intent = new Intent("android.bluetooth.devicepicker.action.DEVICE_SELECTED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        String str2 = this.mLaunchPackage;
        if (str2 != null && (str = this.mLaunchClass) != null) {
            intent.setClassName(str2, str);
        }
        getContext().sendBroadcast(intent);
    }
}
