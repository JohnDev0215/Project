package org.chromium.device.bluetooth;

import com.bydux.api.common.tools.util.ListUtils;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.device.bluetooth.Wrappers;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeBluetoothRemoteGattService {
    private static final String TAG = "Bluetooth";
    ChromeBluetoothDevice mChromeDevice;
    final String mInstanceId;
    private long mNativeBluetoothRemoteGattServiceAndroid;
    final Wrappers.BluetoothGattServiceWrapper mService;

    private native void nativeCreateGattRemoteCharacteristic(long j, String str, Object obj, Object obj2);

    private ChromeBluetoothRemoteGattService(long j, Wrappers.BluetoothGattServiceWrapper bluetoothGattServiceWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.mNativeBluetoothRemoteGattServiceAndroid = j;
        this.mService = bluetoothGattServiceWrapper;
        this.mInstanceId = str;
        this.mChromeDevice = chromeBluetoothDevice;
        Log.v(TAG, "ChromeBluetoothRemoteGattService created.");
    }

    private void onBluetoothRemoteGattServiceAndroidDestruction() {
        this.mNativeBluetoothRemoteGattServiceAndroid = 0L;
    }

    private static ChromeBluetoothRemoteGattService create(long j, Object obj, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattService(j, (Wrappers.BluetoothGattServiceWrapper) obj, str, chromeBluetoothDevice);
    }

    private String getUUID() {
        return this.mService.getUuid().toString();
    }

    private void createCharacteristics() {
        for (Wrappers.BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper : this.mService.getCharacteristics()) {
            nativeCreateGattRemoteCharacteristic(this.mNativeBluetoothRemoteGattServiceAndroid, this.mInstanceId + "/" + bluetoothGattCharacteristicWrapper.getUuid().toString() + ListUtils.DEFAULT_JOIN_SEPARATOR + bluetoothGattCharacteristicWrapper.getInstanceId(), bluetoothGattCharacteristicWrapper, this.mChromeDevice);
        }
    }
}
