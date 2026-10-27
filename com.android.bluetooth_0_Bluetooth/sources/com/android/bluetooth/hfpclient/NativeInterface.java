package com.android.bluetooth.hfpclient;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class NativeInterface {
    private static final boolean DBG = false;
    private static final Object INSTANCE_LOCK;
    private static final String TAG = "NativeInterface";
    private static NativeInterface sInterface;

    private static native void classInitNative();

    private native void cleanupNative();

    private static native boolean connectAudioNative(byte[] bArr);

    private static native boolean connectNative(byte[] bArr);

    private static native boolean dialMemoryNative(byte[] bArr, int i);

    private static native boolean dialNative(byte[] bArr, String str);

    private static native boolean disconnectAudioNative(byte[] bArr);

    private static native boolean disconnectNative(byte[] bArr);

    private static native boolean handleCallActionNative(byte[] bArr, int i, int i2);

    private native void initializeNative();

    private static native boolean queryCurrentCallsNative(byte[] bArr);

    private static native boolean queryCurrentOperatorNameNative(byte[] bArr);

    private static native boolean requestLastVoiceTagNumberNative(byte[] bArr);

    private static native boolean retrieveSubscriberInfoNative(byte[] bArr);

    private static native boolean sendATCmdNative(byte[] bArr, int i, int i2, int i3, String str);

    private static native boolean sendDtmfNative(byte[] bArr, byte b);

    private static native boolean setVolumeNative(byte[] bArr, int i, int i2);

    private static native boolean startVoiceRecognitionNative(byte[] bArr);

    private static native boolean stopVoiceRecognitionNative(byte[] bArr);

    static {
        classInitNative();
        INSTANCE_LOCK = new Object();
    }

    private NativeInterface() {
    }

    public static NativeInterface getInstance() {
        synchronized (INSTANCE_LOCK) {
            if (sInterface == null) {
                sInterface = new NativeInterface();
            }
        }
        return sInterface;
    }

    public void initialize() {
        initializeNative();
    }

    public void cleanup() {
        cleanupNative();
    }

    public boolean connect(byte[] address) {
        return connectNative(address);
    }

    public boolean disconnect(byte[] address) {
        return disconnectNative(address);
    }

    public boolean connectAudio(byte[] address) {
        return connectAudioNative(address);
    }

    public boolean disconnectAudio(byte[] address) {
        return disconnectAudioNative(address);
    }

    public boolean startVoiceRecognition(byte[] address) {
        return startVoiceRecognitionNative(address);
    }

    public boolean stopVoiceRecognition(byte[] address) {
        return stopVoiceRecognitionNative(address);
    }

    public boolean setVolume(byte[] address, int volumeType, int volume) {
        return setVolumeNative(address, volumeType, volume);
    }

    public boolean dial(byte[] address, String number) {
        return dialNative(address, number);
    }

    public boolean dialMemory(byte[] address, int location) {
        return dialMemoryNative(address, location);
    }

    public boolean handleCallAction(byte[] address, int action, int index) {
        return handleCallActionNative(address, action, index);
    }

    public boolean queryCurrentCalls(byte[] address) {
        return queryCurrentCallsNative(address);
    }

    public boolean queryCurrentOperatorName(byte[] address) {
        return queryCurrentOperatorNameNative(address);
    }

    public boolean retrieveSubscriberInfo(byte[] address) {
        return retrieveSubscriberInfoNative(address);
    }

    public boolean sendDtmf(byte[] address, byte code) {
        return sendDtmfNative(address, code);
    }

    public boolean requestLastVoiceTagNumber(byte[] address) {
        return requestLastVoiceTagNumberNative(address);
    }

    public boolean sendATCmd(byte[] address, int atCmd, int val1, int val2, String arg) {
        return sendATCmdNative(address, atCmd, val1, val2, arg);
    }

    private BluetoothDevice getDevice(byte[] address) {
        return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
    }

    private void onConnectionStateChanged(int state, int peerFeat, int chldFeat, byte[] address) {
        StackEvent event = new StackEvent(1);
        event.valueInt = state;
        event.valueInt2 = peerFeat;
        event.valueInt3 = chldFeat;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "Ignoring message because service not available: " + event);
    }

    private void onAudioStateChanged(int state, byte[] address) {
        StackEvent event = new StackEvent(2);
        event.valueInt = state;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onAudioStateChanged: Ignoring message because service not available: " + event);
    }

    private void onVrStateChanged(int state, byte[] address) {
        StackEvent event = new StackEvent(3);
        event.valueInt = state;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onVrStateChanged: Ignoring message because service not available: " + event);
    }

    private void onNetworkState(int state, byte[] address) {
        StackEvent event = new StackEvent(4);
        event.valueInt = state;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onNetworkStateChanged: Ignoring message because service not available: " + event);
    }

    private void onNetworkRoaming(int state, byte[] address) {
        StackEvent event = new StackEvent(5);
        event.valueInt = state;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onNetworkRoaming: Ignoring message because service not available: " + event);
    }

    private void onNetworkSignal(int signal, byte[] address) {
        StackEvent event = new StackEvent(6);
        event.valueInt = signal;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onNetworkSignal: Ignoring message because service not available: " + event);
    }

    private void onBatteryLevel(int level, byte[] address) {
        StackEvent event = new StackEvent(7);
        event.valueInt = level;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onBatteryLevel: Ignoring message because service not available: " + event);
    }

    private void onCurrentOperator(String name, byte[] address) {
        StackEvent event = new StackEvent(8);
        event.valueString = name;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCurrentOperator: Ignoring message because service not available: " + event);
    }

    private void onCall(int call, byte[] address) {
        StackEvent event = new StackEvent(9);
        event.valueInt = call;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCall: Ignoring message because service not available: " + event);
    }

    private void onCallSetup(int callsetup, byte[] address) {
        StackEvent event = new StackEvent(10);
        event.valueInt = callsetup;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCallSetup: Ignoring message because service not available: " + event);
    }

    private void onCallHeld(int callheld, byte[] address) {
        StackEvent event = new StackEvent(11);
        event.valueInt = callheld;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCallHeld: Ignoring message because service not available: " + event);
    }

    private void onRespAndHold(int respAndHold, byte[] address) {
        StackEvent event = new StackEvent(12);
        event.valueInt = respAndHold;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onRespAndHold: Ignoring message because service not available: " + event);
    }

    private void onClip(String number, byte[] address) {
        StackEvent event = new StackEvent(13);
        event.valueString = number;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onClip: Ignoring message because service not available: " + event);
    }

    private void onCallWaiting(String number, byte[] address) {
        StackEvent event = new StackEvent(14);
        event.valueString = number;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCallWaiting: Ignoring message because service not available: " + event);
    }

    private void onCurrentCalls(int index, int dir, int state, int mparty, String number, byte[] address) {
        StackEvent event = new StackEvent(15);
        event.valueInt = index;
        event.valueInt2 = dir;
        event.valueInt3 = state;
        event.valueInt4 = mparty;
        event.valueString = number;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCurrentCalls: Ignoring message because service not available: " + event);
    }

    private void onVolumeChange(int type, int volume, byte[] address) {
        StackEvent event = new StackEvent(16);
        event.valueInt = type;
        event.valueInt2 = volume;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onVolumeChange: Ignoring message because service not available: " + event);
    }

    private void onCmdResult(int type, int cme, byte[] address) {
        StackEvent event = new StackEvent(17);
        event.valueInt = type;
        event.valueInt2 = cme;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onCmdResult: Ignoring message because service not available: " + event);
    }

    private void onSubscriberInfo(String number, int type, byte[] address) {
        StackEvent event = new StackEvent(18);
        event.valueInt = type;
        event.valueString = number;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onSubscriberInfo: Ignoring message because service not available: " + event);
    }

    private void onInBandRing(int inBand, byte[] address) {
        StackEvent event = new StackEvent(19);
        event.valueInt = inBand;
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onInBandRing: Ignoring message because service not available: " + event);
    }

    private void onLastVoiceTagNumber(String number, byte[] address) {
        Log.w(TAG, "onLastVoiceTagNumber not supported");
    }

    private void onRingIndication(byte[] address) {
        StackEvent event = new StackEvent(21);
        event.device = getDevice(address);
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onRingIndication: Ignoring message because service not available: " + event);
    }

    private void onUnknownEvent(String eventString, byte[] address) {
        StackEvent event = new StackEvent(22);
        event.device = getDevice(address);
        event.valueString = eventString;
        HeadsetClientService service = HeadsetClientService.getHeadsetClientService();
        if (service != null) {
            service.messageFromNative(event);
            return;
        }
        Log.w(TAG, "onUnknowEvent: Ignoring message because service not available: " + event);
    }
}
