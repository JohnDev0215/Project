package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.android.bluetooth.Utils;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetNativeInterface {
    private static final Object INSTANCE_LOCK;
    private static final String TAG = "HeadsetNativeInterface";
    private static HeadsetNativeInterface sInterface;
    private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    private native boolean atResponseCodeNative(int i, int i2, byte[] bArr);

    private native boolean atResponseStringNative(String str, byte[] bArr);

    private native boolean cindResponseNative(int i, int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr);

    private static native void classInitNative();

    private native boolean clccResponseNative(int i, int i2, int i3, int i4, boolean z, String str, int i5, byte[] bArr);

    private native void cleanupNative();

    private native boolean connectAudioNative(byte[] bArr);

    private native boolean connectHfpNative(byte[] bArr);

    private native boolean copsResponseNative(String str, byte[] bArr);

    private native boolean disconnectAudioNative(byte[] bArr);

    private native boolean disconnectHfpNative(byte[] bArr);

    private native void initializeNative(int i, boolean z);

    private native boolean notifyDeviceStatusNative(int i, int i2, int i3, int i4, byte[] bArr);

    private native boolean phoneStateChangeNative(int i, int i2, int i3, String str, int i4, String str2, byte[] bArr);

    private native boolean sendBsirNative(boolean z, byte[] bArr);

    private native boolean setActiveDeviceNative(byte[] bArr);

    private native boolean setScoAllowedNative(boolean z);

    private native boolean setVolumeNative(int i, int i2, byte[] bArr);

    private native boolean startVoiceRecognitionNative(byte[] bArr);

    private native boolean stopVoiceRecognitionNative(byte[] bArr);

    static {
        classInitNative();
        INSTANCE_LOCK = new Object();
    }

    private HeadsetNativeInterface() {
    }

    public static HeadsetNativeInterface getInstance() {
        synchronized (INSTANCE_LOCK) {
            if (sInterface == null) {
                sInterface = new HeadsetNativeInterface();
            }
        }
        return sInterface;
    }

    private void sendMessageToService(HeadsetStackEvent event) {
        HeadsetService service = HeadsetService.getHeadsetService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.wtf(TAG, "FATAL: Stack sent event while service is not available: " + event);
    }

    private BluetoothDevice getDevice(byte[] address) {
        return this.mAdapter.getRemoteDevice(Utils.getAddressStringFromByte(address));
    }

    void onConnectionStateChanged(int state, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(1, state, getDevice(address));
        sendMessageToService(event);
    }

    private void onAudioStateChanged(int state, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(2, state, getDevice(address));
        sendMessageToService(event);
    }

    private void onVrStateChanged(int state, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(3, state, getDevice(address));
        sendMessageToService(event);
    }

    private void onAnswerCall(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(4, getDevice(address));
        sendMessageToService(event);
    }

    private void onHangupCall(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(5, getDevice(address));
        sendMessageToService(event);
    }

    private void onVolumeChanged(int type, int volume, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(6, type, volume, getDevice(address));
        sendMessageToService(event);
    }

    private void onDialCall(String number, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(7, number, getDevice(address));
        sendMessageToService(event);
    }

    private void onSendDtmf(int dtmf, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(8, dtmf, getDevice(address));
        sendMessageToService(event);
    }

    private void onNoiseReductionEnable(boolean z, byte[] bArr) {
        sendMessageToService(new HeadsetStackEvent(9, z ? 1 : 0, getDevice(bArr)));
    }

    private void onWBS(int codec, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(17, codec, getDevice(address));
        sendMessageToService(event);
    }

    private void onAtChld(int chld, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(10, chld, getDevice(address));
        sendMessageToService(event);
    }

    private void onAtCnum(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(11, getDevice(address));
        sendMessageToService(event);
    }

    private void onAtCind(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(12, getDevice(address));
        sendMessageToService(event);
    }

    private void onAtCops(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(13, getDevice(address));
        sendMessageToService(event);
    }

    private void onAtClcc(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(14, getDevice(address));
        sendMessageToService(event);
    }

    private void onUnknownAt(String atString, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(15, atString, getDevice(address));
        sendMessageToService(event);
    }

    private void onKeyPressed(byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(16, getDevice(address));
        sendMessageToService(event);
    }

    private void onATBind(String atString, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(18, atString, getDevice(address));
        sendMessageToService(event);
    }

    private void onATBiev(int indId, int indValue, byte[] address) {
        HeadsetStackEvent event = new HeadsetStackEvent(19, indId, indValue, getDevice(address));
        sendMessageToService(event);
    }

    private void onAtBia(boolean service, boolean roam, boolean signal, boolean battery, byte[] address) {
        HeadsetAgIndicatorEnableState agIndicatorEnableState = new HeadsetAgIndicatorEnableState(service, roam, signal, battery);
        HeadsetStackEvent event = new HeadsetStackEvent(20, agIndicatorEnableState, getDevice(address));
        sendMessageToService(event);
    }

    public void init(int maxHfClients, boolean inbandRingingEnabled) {
        initializeNative(maxHfClients, inbandRingingEnabled);
    }

    public void cleanup() {
        cleanupNative();
    }

    public boolean atResponseCode(BluetoothDevice device, int responseCode, int errorCode) {
        return atResponseCodeNative(responseCode, errorCode, Utils.getByteAddress(device));
    }

    public boolean atResponseString(BluetoothDevice device, String responseString) {
        return atResponseStringNative(responseString, Utils.getByteAddress(device));
    }

    public boolean connectHfp(BluetoothDevice device) {
        return connectHfpNative(Utils.getByteAddress(device));
    }

    public boolean disconnectHfp(BluetoothDevice device) {
        return disconnectHfpNative(Utils.getByteAddress(device));
    }

    public boolean connectAudio(BluetoothDevice device) {
        return connectAudioNative(Utils.getByteAddress(device));
    }

    public boolean disconnectAudio(BluetoothDevice device) {
        return disconnectAudioNative(Utils.getByteAddress(device));
    }

    public boolean startVoiceRecognition(BluetoothDevice device) {
        return startVoiceRecognitionNative(Utils.getByteAddress(device));
    }

    public boolean stopVoiceRecognition(BluetoothDevice device) {
        return stopVoiceRecognitionNative(Utils.getByteAddress(device));
    }

    public boolean setVolume(BluetoothDevice device, int volumeType, int volume) {
        return setVolumeNative(volumeType, volume, Utils.getByteAddress(device));
    }

    public boolean cindResponse(BluetoothDevice device, int service, int numActive, int numHeld, int callState, int signal, int roam, int batteryCharge) {
        return cindResponseNative(service, numActive, numHeld, callState, signal, roam, batteryCharge, Utils.getByteAddress(device));
    }

    public boolean notifyDeviceStatus(BluetoothDevice device, HeadsetDeviceState deviceState) {
        return notifyDeviceStatusNative(deviceState.mService, deviceState.mRoam, deviceState.mSignal, deviceState.mBatteryCharge, Utils.getByteAddress(device));
    }

    public boolean clccResponse(BluetoothDevice device, int index, int dir, int status, int mode, boolean mpty, String number, int type) {
        return clccResponseNative(index, dir, status, mode, mpty, number, type, Utils.getByteAddress(device));
    }

    public boolean copsResponse(BluetoothDevice device, String operatorName) {
        return copsResponseNative(operatorName, Utils.getByteAddress(device));
    }

    public boolean phoneStateChange(BluetoothDevice device, HeadsetCallState callState) {
        return phoneStateChangeNative(callState.mNumActive, callState.mNumHeld, callState.mCallState, callState.mNumber, callState.mType, callState.mName, Utils.getByteAddress(device));
    }

    public boolean setScoAllowed(boolean value) {
        return setScoAllowedNative(value);
    }

    public boolean sendBsir(BluetoothDevice device, boolean value) {
        return sendBsirNative(value, Utils.getByteAddress(device));
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        return setActiveDeviceNative(Utils.getByteAddress(device));
    }
}
