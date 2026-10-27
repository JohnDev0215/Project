package com.android.bluetooth.a2dp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.android.bluetooth.Utils;

/* JADX INFO: loaded from: classes.dex */
public class A2dpNativeInterface {
    private static final boolean DBG = true;
    private static final Object INSTANCE_LOCK = new Object();
    private static final String TAG = "A2dpNativeInterface";
    private static A2dpNativeInterface sInstance;
    private BluetoothAdapter mAdapter;

    private static native void classInitNative();

    private native void cleanupNative();

    private native boolean connectA2dpNative(byte[] bArr);

    private native boolean disconnectA2dpNative(byte[] bArr);

    private native void initNative(int i, BluetoothCodecConfig[] bluetoothCodecConfigArr, BluetoothCodecConfig[] bluetoothCodecConfigArr2);

    private native boolean setActiveDeviceNative(byte[] bArr);

    private native boolean setCodecConfigPreferenceNative(byte[] bArr, BluetoothCodecConfig[] bluetoothCodecConfigArr);

    private native boolean setSilenceDeviceNative(byte[] bArr, boolean z);

    static {
        classInitNative();
    }

    private A2dpNativeInterface() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            Log.wtf(TAG, "No Bluetooth Adapter Available");
        }
    }

    public static A2dpNativeInterface getInstance() {
        A2dpNativeInterface a2dpNativeInterface;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new A2dpNativeInterface();
            }
            a2dpNativeInterface = sInstance;
        }
        return a2dpNativeInterface;
    }

    public void init(int maxConnectedAudioDevices, BluetoothCodecConfig[] codecConfigPriorities, BluetoothCodecConfig[] codecConfigOffloading) {
        initNative(maxConnectedAudioDevices, codecConfigPriorities, codecConfigOffloading);
    }

    public void cleanup() {
        cleanupNative();
    }

    public boolean connectA2dp(BluetoothDevice device) {
        return connectA2dpNative(getByteAddress(device));
    }

    public boolean disconnectA2dp(BluetoothDevice device) {
        return disconnectA2dpNative(getByteAddress(device));
    }

    public boolean setSilenceDevice(BluetoothDevice device, boolean silence) {
        return setSilenceDeviceNative(getByteAddress(device), silence);
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        return setActiveDeviceNative(getByteAddress(device));
    }

    public boolean setCodecConfigPreference(BluetoothDevice device, BluetoothCodecConfig[] codecConfigArray) {
        return setCodecConfigPreferenceNative(getByteAddress(device), codecConfigArray);
    }

    private BluetoothDevice getDevice(byte[] address) {
        return this.mAdapter.getRemoteDevice(address);
    }

    private byte[] getByteAddress(BluetoothDevice device) {
        if (device == null) {
            return Utils.getBytesFromAddress("00:00:00:00:00:00");
        }
        return Utils.getBytesFromAddress(device.getAddress());
    }

    private void sendMessageToService(A2dpStackEvent event) {
        A2dpService service = A2dpService.getA2dpService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "Event ignored, service not available: " + event);
    }

    private void onConnectionStateChanged(byte[] address, int state) {
        A2dpStackEvent event = new A2dpStackEvent(1);
        event.device = getDevice(address);
        event.valueInt = state;
        Log.d(TAG, "onConnectionStateChanged: " + event);
        sendMessageToService(event);
    }

    private void onAudioStateChanged(byte[] address, int state) {
        A2dpStackEvent event = new A2dpStackEvent(2);
        event.device = getDevice(address);
        event.valueInt = state;
        Log.d(TAG, "onAudioStateChanged: " + event);
        sendMessageToService(event);
    }

    private void onCodecConfigChanged(byte[] address, BluetoothCodecConfig newCodecConfig, BluetoothCodecConfig[] codecsLocalCapabilities, BluetoothCodecConfig[] codecsSelectableCapabilities) {
        A2dpStackEvent event = new A2dpStackEvent(3);
        event.device = getDevice(address);
        event.codecStatus = new BluetoothCodecStatus(newCodecConfig, codecsLocalCapabilities, codecsSelectableCapabilities);
        Log.d(TAG, "onCodecConfigChanged: " + event);
        sendMessageToService(event);
    }

    private boolean isMandatoryCodecPreferred(byte[] address) {
        A2dpService service = A2dpService.getA2dpService();
        if (service != null) {
            int enabled = service.getOptionalCodecsEnabled(getDevice(address));
            Log.d(TAG, "isMandatoryCodecPreferred: optional preference " + enabled);
            return enabled == 0;
        }
        Log.w(TAG, "isMandatoryCodecPreferred: service not available");
        return false;
    }
}
