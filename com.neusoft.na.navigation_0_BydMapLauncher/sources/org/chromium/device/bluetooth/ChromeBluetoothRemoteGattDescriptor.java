package org.chromium.device.bluetooth;

import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.device.bluetooth.Wrappers;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeBluetoothRemoteGattDescriptor {
    private static final String TAG = "Bluetooth";
    final ChromeBluetoothDevice mChromeDevice;
    final Wrappers.BluetoothGattDescriptorWrapper mDescriptor;
    private long mNativeBluetoothRemoteGattDescriptorAndroid;

    native void nativeOnRead(long j, int i, byte[] bArr);

    native void nativeOnWrite(long j, int i);

    private ChromeBluetoothRemoteGattDescriptor(long j, Wrappers.BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper, ChromeBluetoothDevice chromeBluetoothDevice) {
        this.mNativeBluetoothRemoteGattDescriptorAndroid = j;
        this.mDescriptor = bluetoothGattDescriptorWrapper;
        this.mChromeDevice = chromeBluetoothDevice;
        chromeBluetoothDevice.mWrapperToChromeDescriptorsMap.put(bluetoothGattDescriptorWrapper, this);
        Log.v(TAG, "ChromeBluetoothRemoteGattDescriptor created.");
    }

    private void onBluetoothRemoteGattDescriptorAndroidDestruction() {
        Log.v(TAG, "ChromeBluetoothRemoteGattDescriptor Destroyed.");
        this.mNativeBluetoothRemoteGattDescriptorAndroid = 0L;
        this.mChromeDevice.mWrapperToChromeDescriptorsMap.remove(this.mDescriptor);
    }

    void onDescriptorRead(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? ResultCodeEnum.OK : "Error";
        Log.i(TAG, "onDescriptorRead status:%d==%s", objArr);
        long j = this.mNativeBluetoothRemoteGattDescriptorAndroid;
        if (j != 0) {
            nativeOnRead(j, i, this.mDescriptor.getValue());
        }
    }

    void onDescriptorWrite(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? ResultCodeEnum.OK : "Error";
        Log.i(TAG, "onDescriptorWrite status:%d==%s", objArr);
        long j = this.mNativeBluetoothRemoteGattDescriptorAndroid;
        if (j != 0) {
            nativeOnWrite(j, i);
        }
    }

    private static ChromeBluetoothRemoteGattDescriptor create(long j, Object obj, ChromeBluetoothDevice chromeBluetoothDevice) {
        return new ChromeBluetoothRemoteGattDescriptor(j, (Wrappers.BluetoothGattDescriptorWrapper) obj, chromeBluetoothDevice);
    }

    private String getUUID() {
        return this.mDescriptor.getUuid().toString();
    }

    private boolean readRemoteDescriptor() {
        if (this.mChromeDevice.mBluetoothGatt.readDescriptor(this.mDescriptor)) {
            return true;
        }
        Log.i(TAG, "readRemoteDescriptor readDescriptor failed.", new Object[0]);
        return false;
    }

    private boolean writeRemoteDescriptor(byte[] bArr) {
        if (!this.mDescriptor.setValue(bArr)) {
            Log.i(TAG, "writeRemoteDescriptor setValue failed.", new Object[0]);
            return false;
        }
        if (this.mChromeDevice.mBluetoothGatt.writeDescriptor(this.mDescriptor)) {
            return true;
        }
        Log.i(TAG, "writeRemoteDescriptor writeDescriptor failed.", new Object[0]);
        return false;
    }
}
