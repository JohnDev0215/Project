package com.android.bluetooth.hearingaid;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.android.bluetooth.Utils;

/* JADX INFO: loaded from: classes.dex */
public class HearingAidNativeInterface {
    private static final boolean DBG = true;
    private static final Object INSTANCE_LOCK = new Object();
    private static final String TAG = "HearingAidNativeInterface";
    private static HearingAidNativeInterface sInstance;
    private BluetoothAdapter mAdapter;

    private native boolean addToWhiteListNative(byte[] bArr);

    private static native void classInitNative();

    private native void cleanupNative();

    private native boolean connectHearingAidNative(byte[] bArr);

    private native boolean disconnectHearingAidNative(byte[] bArr);

    private native void initNative();

    private native void setVolumeNative(int i);

    static {
        classInitNative();
    }

    private HearingAidNativeInterface() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            Log.wtf(TAG, "No Bluetooth Adapter Available");
        }
    }

    public static HearingAidNativeInterface getInstance() {
        HearingAidNativeInterface hearingAidNativeInterface;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new HearingAidNativeInterface();
            }
            hearingAidNativeInterface = sInstance;
        }
        return hearingAidNativeInterface;
    }

    public void init() {
        initNative();
    }

    public void cleanup() {
        cleanupNative();
    }

    public boolean connectHearingAid(BluetoothDevice device) {
        return connectHearingAidNative(getByteAddress(device));
    }

    public boolean disconnectHearingAid(BluetoothDevice device) {
        return disconnectHearingAidNative(getByteAddress(device));
    }

    public boolean addToWhiteList(BluetoothDevice device) {
        return addToWhiteListNative(getByteAddress(device));
    }

    public void setVolume(int volume) {
        setVolumeNative(volume);
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

    private void sendMessageToService(HearingAidStackEvent event) {
        HearingAidService service = HearingAidService.getHearingAidService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.e(TAG, "Event ignored, service not available: " + event);
    }

    private void onConnectionStateChanged(int state, byte[] address) {
        HearingAidStackEvent event = new HearingAidStackEvent(1);
        event.device = getDevice(address);
        event.valueInt1 = state;
        Log.d(TAG, "onConnectionStateChanged: " + event);
        sendMessageToService(event);
    }

    private void onDeviceAvailable(byte capabilities, long hiSyncId, byte[] address) {
        HearingAidStackEvent event = new HearingAidStackEvent(2);
        event.device = getDevice(address);
        event.valueInt1 = capabilities;
        event.valueLong2 = hiSyncId;
        Log.d(TAG, "onDeviceAvailable: " + event);
        sendMessageToService(event);
    }
}
