package org.chromium.device.bluetooth;

import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.device.bluetooth.Wrappers;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeBluetoothRemoteGattCharacteristic {
    private static final String TAG = "Bluetooth";
    final Wrappers.BluetoothGattCharacteristicWrapper mCharacteristic;
    final ChromeBluetoothDevice mChromeDevice;
    final String mInstanceId;
    private long mNativeBluetoothRemoteGattCharacteristicAndroid;

    private native void nativeCreateGattRemoteDescriptor(long j, String str, Object obj, Object obj2);

    native void nativeOnChanged(long j, byte[] bArr);

    native void nativeOnRead(long j, int i, byte[] bArr);

    native void nativeOnWrite(long j, int i);

    private ChromeBluetoothRemoteGattCharacteristic(long j, Wrappers.BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.mNativeBluetoothRemoteGattCharacteristicAndroid = j;
        this.mCharacteristic = bluetoothGattCharacteristicWrapper;
        this.mInstanceId = str;
        this.mChromeDevice = chromeBluetoothDevice;
        chromeBluetoothDevice.mWrapperToChromeCharacteristicsMap.put(bluetoothGattCharacteristicWrapper, this);
        Log.v(TAG, "ChromeBluetoothRemoteGattCharacteristic created.");
    }

    private void onBluetoothRemoteGattCharacteristicAndroidDestruction() {
        Log.v(TAG, "ChromeBluetoothRemoteGattCharacteristic Destroyed.");
        if (this.mChromeDevice.mBluetoothGatt != null) {
            this.mChromeDevice.mBluetoothGatt.setCharacteristicNotification(this.mCharacteristic, false);
        }
        this.mNativeBluetoothRemoteGattCharacteristicAndroid = 0L;
        this.mChromeDevice.mWrapperToChromeCharacteristicsMap.remove(this.mCharacteristic);
    }

    void onCharacteristicChanged() {
        Log.i(TAG, "onCharacteristicChanged", new Object[0]);
        long j = this.mNativeBluetoothRemoteGattCharacteristicAndroid;
        if (j != 0) {
            nativeOnChanged(j, this.mCharacteristic.getValue());
        }
    }

    void onCharacteristicRead(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? ResultCodeEnum.OK : "Error";
        Log.i(TAG, "onCharacteristicRead status:%d==%s", objArr);
        long j = this.mNativeBluetoothRemoteGattCharacteristicAndroid;
        if (j != 0) {
            nativeOnRead(j, i, this.mCharacteristic.getValue());
        }
    }

    void onCharacteristicWrite(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? ResultCodeEnum.OK : "Error";
        Log.i(TAG, "onCharacteristicWrite status:%d==%s", objArr);
        long j = this.mNativeBluetoothRemoteGattCharacteristicAndroid;
        if (j != 0) {
            nativeOnWrite(j, i);
        }
    }

    private static ChromeBluetoothRemoteGattCharacteristic create(long j, Object obj, String str, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattCharacteristic(j, (Wrappers.BluetoothGattCharacteristicWrapper) obj, str, chromeBluetoothDevice);
    }

    private String getUUID() {
        return this.mCharacteristic.getUuid().toString();
    }

    private int getProperties() {
        return this.mCharacteristic.getProperties();
    }

    private boolean readRemoteCharacteristic() {
        if (this.mChromeDevice.mBluetoothGatt.readCharacteristic(this.mCharacteristic)) {
            return true;
        }
        Log.i(TAG, "readRemoteCharacteristic readCharacteristic failed.", new Object[0]);
        return false;
    }

    private boolean writeRemoteCharacteristic(byte[] bArr) {
        if (!this.mCharacteristic.setValue(bArr)) {
            Log.i(TAG, "writeRemoteCharacteristic setValue failed.", new Object[0]);
            return false;
        }
        if (this.mChromeDevice.mBluetoothGatt.writeCharacteristic(this.mCharacteristic)) {
            return true;
        }
        Log.i(TAG, "writeRemoteCharacteristic writeCharacteristic failed.", new Object[0]);
        return false;
    }

    private boolean setCharacteristicNotification(boolean z) {
        return this.mChromeDevice.mBluetoothGatt.setCharacteristicNotification(this.mCharacteristic, z);
    }

    private void createDescriptors() {
        int i = 0;
        for (Wrappers.BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper : this.mCharacteristic.getDescriptors()) {
            nativeCreateGattRemoteDescriptor(this.mNativeBluetoothRemoteGattCharacteristicAndroid, this.mInstanceId + "/" + bluetoothGattDescriptorWrapper.getUuid().toString() + ";" + i, bluetoothGattDescriptorWrapper, this.mChromeDevice);
            i++;
        }
    }
}
