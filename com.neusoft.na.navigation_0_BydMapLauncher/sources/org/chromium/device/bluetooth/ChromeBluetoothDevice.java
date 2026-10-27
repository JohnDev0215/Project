package org.chromium.device.bluetooth;

import android.content.Context;
import android.os.ParcelUuid;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.device.bluetooth.Wrappers;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeBluetoothDevice {
    private static final String TAG = "Bluetooth";
    Wrappers.BluetoothGattWrapper mBluetoothGatt;
    final Wrappers.BluetoothDeviceWrapper mDevice;
    private long mNativeBluetoothDeviceAndroid;
    private HashSet<String> mUuidsFromScan = new HashSet<>();
    private final BluetoothGattCallbackImpl mBluetoothGattCallbackImpl = new BluetoothGattCallbackImpl();
    final HashMap<Wrappers.BluetoothGattCharacteristicWrapper, ChromeBluetoothRemoteGattCharacteristic> mWrapperToChromeCharacteristicsMap = new HashMap<>();
    final HashMap<Wrappers.BluetoothGattDescriptorWrapper, ChromeBluetoothRemoteGattDescriptor> mWrapperToChromeDescriptorsMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeCreateGattRemoteService(long j, String str, Object obj);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnConnectionStateChange(long j, int i, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnGattServicesDiscovered(long j);

    private ChromeBluetoothDevice(long j, Wrappers.BluetoothDeviceWrapper bluetoothDeviceWrapper) {
        this.mNativeBluetoothDeviceAndroid = j;
        this.mDevice = bluetoothDeviceWrapper;
        Log.v(TAG, "ChromeBluetoothDevice created.");
    }

    private void onBluetoothDeviceAndroidDestruction() {
        Wrappers.BluetoothGattWrapper bluetoothGattWrapper = this.mBluetoothGatt;
        if (bluetoothGattWrapper != null) {
            bluetoothGattWrapper.close();
            this.mBluetoothGatt = null;
        }
        this.mNativeBluetoothDeviceAndroid = 0L;
    }

    private static ChromeBluetoothDevice create(long j, Object obj) {
        return new ChromeBluetoothDevice(j, (Wrappers.BluetoothDeviceWrapper) obj);
    }

    private boolean updateAdvertisedUUIDs(List<ParcelUuid> list) {
        boolean zAdd = false;
        if (list == null) {
            return false;
        }
        Iterator<ParcelUuid> it = list.iterator();
        while (it.hasNext()) {
            zAdd |= this.mUuidsFromScan.add(it.next().toString());
        }
        return zAdd;
    }

    private int getBluetoothClass() {
        return this.mDevice.getBluetoothClass_getDeviceClass();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAddress() {
        return this.mDevice.getAddress();
    }

    private boolean isPaired() {
        return this.mDevice.getBondState() == 12;
    }

    private String[] getUuids() {
        HashSet<String> hashSet = this.mUuidsFromScan;
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private void createGattConnectionImpl(Context context) {
        Log.i(TAG, "connectGatt", new Object[0]);
        Wrappers.BluetoothGattWrapper bluetoothGattWrapper = this.mBluetoothGatt;
        if (bluetoothGattWrapper != null) {
            bluetoothGattWrapper.close();
        }
        this.mBluetoothGatt = this.mDevice.connectGatt(context, false, this.mBluetoothGattCallbackImpl);
    }

    private void disconnectGatt() {
        Log.i(TAG, "BluetoothGatt.disconnect", new Object[0]);
        Wrappers.BluetoothGattWrapper bluetoothGattWrapper = this.mBluetoothGatt;
        if (bluetoothGattWrapper != null) {
            bluetoothGattWrapper.disconnect();
        }
    }

    private String getDeviceName() {
        return this.mDevice.getName();
    }

    private class BluetoothGattCallbackImpl extends Wrappers.BluetoothGattCallbackWrapper {
        private BluetoothGattCallbackImpl() {
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onConnectionStateChange(final int i, final int i2) {
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = i2 == 2 ? "Connected" : "Disconnected";
            Log.i(ChromeBluetoothDevice.TAG, "onConnectionStateChange status:%d newState:%s", objArr);
            if (i2 == 2) {
                ChromeBluetoothDevice.this.mBluetoothGatt.discoverServices();
            } else if (i2 == 0 && ChromeBluetoothDevice.this.mBluetoothGatt != null) {
                ChromeBluetoothDevice.this.mBluetoothGatt.close();
                ChromeBluetoothDevice.this.mBluetoothGatt = null;
            }
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ChromeBluetoothDevice.this.mNativeBluetoothDeviceAndroid != 0) {
                        ChromeBluetoothDevice.this.nativeOnConnectionStateChange(ChromeBluetoothDevice.this.mNativeBluetoothDeviceAndroid, i, i2 == 2);
                    }
                }
            });
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onServicesDiscovered(int i) {
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = i == 0 ? ResultCodeEnum.OK : "Error";
            Log.i(ChromeBluetoothDevice.TAG, "onServicesDiscovered status:%d==%s", objArr);
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    if (ChromeBluetoothDevice.this.mNativeBluetoothDeviceAndroid != 0) {
                        for (Wrappers.BluetoothGattServiceWrapper bluetoothGattServiceWrapper : ChromeBluetoothDevice.this.mBluetoothGatt.getServices()) {
                            ChromeBluetoothDevice.this.nativeCreateGattRemoteService(ChromeBluetoothDevice.this.mNativeBluetoothDeviceAndroid, ChromeBluetoothDevice.this.getAddress() + "/" + bluetoothGattServiceWrapper.getUuid().toString() + ListUtils.DEFAULT_JOIN_SEPARATOR + bluetoothGattServiceWrapper.getInstanceId(), bluetoothGattServiceWrapper);
                        }
                        ChromeBluetoothDevice.this.nativeOnGattServicesDiscovered(ChromeBluetoothDevice.this.mNativeBluetoothDeviceAndroid);
                    }
                }
            });
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onCharacteristicChanged(final Wrappers.BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper) {
            Log.i(ChromeBluetoothDevice.TAG, "device onCharacteristicChanged.", new Object[0]);
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = ChromeBluetoothDevice.this.mWrapperToChromeCharacteristicsMap.get(bluetoothGattCharacteristicWrapper);
                    if (chromeBluetoothRemoteGattCharacteristic == null) {
                        Log.v(ChromeBluetoothDevice.TAG, "onCharacteristicChanged when chromeCharacteristic == null.");
                    } else {
                        chromeBluetoothRemoteGattCharacteristic.onCharacteristicChanged();
                    }
                }
            });
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onCharacteristicRead(final Wrappers.BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper, final int i) {
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.4
                @Override // java.lang.Runnable
                public void run() {
                    ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = ChromeBluetoothDevice.this.mWrapperToChromeCharacteristicsMap.get(bluetoothGattCharacteristicWrapper);
                    if (chromeBluetoothRemoteGattCharacteristic == null) {
                        Log.v(ChromeBluetoothDevice.TAG, "onCharacteristicRead when chromeCharacteristic == null.");
                    } else {
                        chromeBluetoothRemoteGattCharacteristic.onCharacteristicRead(i);
                    }
                }
            });
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onCharacteristicWrite(final Wrappers.BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper, final int i) {
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = ChromeBluetoothDevice.this.mWrapperToChromeCharacteristicsMap.get(bluetoothGattCharacteristicWrapper);
                    if (chromeBluetoothRemoteGattCharacteristic == null) {
                        Log.v(ChromeBluetoothDevice.TAG, "onCharacteristicWrite when chromeCharacteristic == null.");
                    } else {
                        chromeBluetoothRemoteGattCharacteristic.onCharacteristicWrite(i);
                    }
                }
            });
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onDescriptorRead(final Wrappers.BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper, final int i) {
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    ChromeBluetoothRemoteGattDescriptor chromeBluetoothRemoteGattDescriptor = ChromeBluetoothDevice.this.mWrapperToChromeDescriptorsMap.get(bluetoothGattDescriptorWrapper);
                    if (chromeBluetoothRemoteGattDescriptor == null) {
                        Log.v(ChromeBluetoothDevice.TAG, "onDescriptorRead when chromeDescriptor == null.");
                    } else {
                        chromeBluetoothRemoteGattDescriptor.onDescriptorRead(i);
                    }
                }
            });
        }

        @Override // org.chromium.device.bluetooth.Wrappers.BluetoothGattCallbackWrapper
        public void onDescriptorWrite(final Wrappers.BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper, final int i) {
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.device.bluetooth.ChromeBluetoothDevice.BluetoothGattCallbackImpl.7
                @Override // java.lang.Runnable
                public void run() {
                    ChromeBluetoothRemoteGattDescriptor chromeBluetoothRemoteGattDescriptor = ChromeBluetoothDevice.this.mWrapperToChromeDescriptorsMap.get(bluetoothGattDescriptorWrapper);
                    if (chromeBluetoothRemoteGattDescriptor == null) {
                        Log.v(ChromeBluetoothDevice.TAG, "onDescriptorWrite when chromeDescriptor == null.");
                    } else {
                        chromeBluetoothRemoteGattDescriptor.onDescriptorWrite(i);
                    }
                }
            });
        }
    }
}
