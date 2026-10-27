package com.android.bluetooth.btservice;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.hearingaid.HearingAidService;
import com.android.bluetooth.hfp.HeadsetService;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class ActiveDeviceManager {
    private static final boolean DBG = true;
    private static final int MESSAGE_A2DP_ACTION_ACTIVE_DEVICE_CHANGED = 3;
    private static final int MESSAGE_A2DP_ACTION_CONNECTION_STATE_CHANGED = 2;
    private static final int MESSAGE_ADAPTER_ACTION_STATE_CHANGED = 1;
    private static final int MESSAGE_HEARING_AID_ACTION_ACTIVE_DEVICE_CHANGED = 6;
    private static final int MESSAGE_HFP_ACTION_ACTIVE_DEVICE_CHANGED = 5;
    private static final int MESSAGE_HFP_ACTION_CONNECTION_STATE_CHANGED = 4;
    private static final String TAG = "BluetoothActiveDeviceManager";
    private final AdapterService mAdapterService;
    private final AudioManager mAudioManager;
    private final ServiceFactory mFactory;
    private HandlerThread mHandlerThread = null;
    private Handler mHandler = null;
    private final List<BluetoothDevice> mA2dpConnectedDevices = new LinkedList();
    private final List<BluetoothDevice> mHfpConnectedDevices = new LinkedList();
    private BluetoothDevice mA2dpActiveDevice = null;
    private BluetoothDevice mHfpActiveDevice = null;
    private BluetoothDevice mHearingAidActiveDevice = null;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.ActiveDeviceManager.1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.e(ActiveDeviceManager.TAG, "Received intent with null action");
                return;
            }
            byte b = -1;
            switch (action.hashCode()) {
                case -1530327060:
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        b = 0;
                    }
                    break;
                case 17117692:
                    if (action.equals("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 4;
                    }
                    break;
                case 487423555:
                    if (action.equals("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 2;
                    }
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 3;
                    }
                    break;
                case 1176349464:
                    if (action.equals("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 5;
                    }
                    break;
                case 1244161670:
                    if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 1;
                    }
                    break;
            }
            if (b == 0) {
                ActiveDeviceManager.this.mHandler.obtainMessage(1, intent).sendToTarget();
                return;
            }
            if (b == 1) {
                ActiveDeviceManager.this.mHandler.obtainMessage(2, intent).sendToTarget();
                return;
            }
            if (b == 2) {
                ActiveDeviceManager.this.mHandler.obtainMessage(3, intent).sendToTarget();
                return;
            }
            if (b == 3) {
                ActiveDeviceManager.this.mHandler.obtainMessage(4, intent).sendToTarget();
                return;
            }
            if (b == 4) {
                ActiveDeviceManager.this.mHandler.obtainMessage(5, intent).sendToTarget();
                return;
            }
            if (b == 5) {
                ActiveDeviceManager.this.mHandler.obtainMessage(6, intent).sendToTarget();
                return;
            }
            Log.e(ActiveDeviceManager.TAG, "Received unexpected intent, action=" + action);
        }
    };
    private final AudioManagerAudioDeviceCallback mAudioManagerAudioDeviceCallback = new AudioManagerAudioDeviceCallback();

    class ActiveDeviceManagerHandler extends Handler {
        ActiveDeviceManagerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int newState = ((Intent) msg.obj).getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                    Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_ADAPTER_ACTION_STATE_CHANGED): newState=" + newState);
                    if (newState == 12) {
                        ActiveDeviceManager.this.resetState();
                    }
                    break;
                case 2:
                    Intent intent = (Intent) msg.obj;
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    int nextState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    if (prevState != nextState) {
                        if (nextState == 2) {
                            Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_A2DP_ACTION_CONNECTION_STATE_CHANGED): device " + device + " connected");
                            if (!ActiveDeviceManager.this.mA2dpConnectedDevices.contains(device)) {
                                ActiveDeviceManager.this.mA2dpConnectedDevices.add(device);
                                if (ActiveDeviceManager.this.mHearingAidActiveDevice == null) {
                                    ActiveDeviceManager.this.setA2dpActiveDevice(device);
                                }
                                break;
                            }
                        } else {
                            if (prevState == 2) {
                                Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_A2DP_ACTION_CONNECTION_STATE_CHANGED): device " + device + " disconnected");
                                ActiveDeviceManager.this.mA2dpConnectedDevices.remove(device);
                                if (Objects.equals(ActiveDeviceManager.this.mA2dpActiveDevice, device)) {
                                    ActiveDeviceManager.this.setA2dpActiveDevice(null);
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    BluetoothDevice device2 = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_A2DP_ACTION_ACTIVE_DEVICE_CHANGED): device= " + device2);
                    if (device2 != null && !Objects.equals(ActiveDeviceManager.this.mA2dpActiveDevice, device2)) {
                        ActiveDeviceManager.this.setHearingAidActiveDevice(null);
                    }
                    ActiveDeviceManager.this.mA2dpActiveDevice = device2;
                    break;
                case 4:
                    Intent intent2 = (Intent) msg.obj;
                    BluetoothDevice device3 = (BluetoothDevice) intent2.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int prevState2 = intent2.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    int nextState2 = intent2.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    if (prevState2 != nextState2) {
                        if (nextState2 == 2) {
                            Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_HFP_ACTION_CONNECTION_STATE_CHANGED): device " + device3 + " connected");
                            if (!ActiveDeviceManager.this.mHfpConnectedDevices.contains(device3)) {
                                ActiveDeviceManager.this.mHfpConnectedDevices.add(device3);
                                if (ActiveDeviceManager.this.mHearingAidActiveDevice == null) {
                                    ActiveDeviceManager.this.setHfpActiveDevice(device3);
                                }
                                break;
                            }
                        } else {
                            if (prevState2 == 2) {
                                Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_HFP_ACTION_CONNECTION_STATE_CHANGED): device " + device3 + " disconnected");
                                ActiveDeviceManager.this.mHfpConnectedDevices.remove(device3);
                                if (Objects.equals(ActiveDeviceManager.this.mHfpActiveDevice, device3)) {
                                    ActiveDeviceManager.this.setHfpActiveDevice(null);
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 5:
                    BluetoothDevice device4 = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_HFP_ACTION_ACTIVE_DEVICE_CHANGED): device= " + device4);
                    if (device4 != null && !Objects.equals(ActiveDeviceManager.this.mHfpActiveDevice, device4)) {
                        ActiveDeviceManager.this.setHearingAidActiveDevice(null);
                    }
                    ActiveDeviceManager.this.mHfpActiveDevice = device4;
                    break;
                case 6:
                    BluetoothDevice device5 = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    Log.d(ActiveDeviceManager.TAG, "handleMessage(MESSAGE_HA_ACTION_ACTIVE_DEVICE_CHANGED): device= " + device5);
                    ActiveDeviceManager.this.mHearingAidActiveDevice = device5;
                    if (device5 != null) {
                        ActiveDeviceManager.this.setA2dpActiveDevice(null);
                        ActiveDeviceManager.this.setHfpActiveDevice(null);
                    }
                    break;
            }
        }
    }

    private class AudioManagerAudioDeviceCallback extends AudioDeviceCallback {
        private AudioManagerAudioDeviceCallback() {
        }

        private boolean isWiredAudioHeadset(AudioDeviceInfo deviceInfo) {
            int type = deviceInfo.getType();
            if (type == 3 || type == 4 || type == 22) {
                return true;
            }
            return false;
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
            Log.d(ActiveDeviceManager.TAG, "onAudioDevicesAdded");
            boolean hasAddedWiredDevice = false;
            int length = addedDevices.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                AudioDeviceInfo deviceInfo = addedDevices[i];
                Log.d(ActiveDeviceManager.TAG, "Audio device added: " + ((Object) deviceInfo.getProductName()) + " type: " + deviceInfo.getType());
                if (!isWiredAudioHeadset(deviceInfo)) {
                    i++;
                } else {
                    hasAddedWiredDevice = true;
                    break;
                }
            }
            if (hasAddedWiredDevice) {
                ActiveDeviceManager.this.wiredAudioDeviceConnected();
            }
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {
        }
    }

    ActiveDeviceManager(AdapterService service, ServiceFactory factory) {
        this.mAdapterService = service;
        this.mFactory = factory;
        this.mAudioManager = (AudioManager) service.getSystemService("audio");
    }

    void start() {
        Log.d(TAG, "start()");
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new ActiveDeviceManagerHandler(this.mHandlerThread.getLooper());
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED");
        filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED");
        filter.addAction("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED");
        this.mAdapterService.registerReceiver(this.mReceiver, filter);
        this.mAudioManager.registerAudioDeviceCallback(this.mAudioManagerAudioDeviceCallback, this.mHandler);
    }

    void cleanup() {
        Log.d(TAG, "cleanup()");
        this.mAudioManager.unregisterAudioDeviceCallback(this.mAudioManagerAudioDeviceCallback);
        this.mAdapterService.unregisterReceiver(this.mReceiver);
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
        resetState();
    }

    public Looper getHandlerLooper() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread == null) {
            return null;
        }
        return handlerThread.getLooper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setA2dpActiveDevice(BluetoothDevice device) {
        Log.d(TAG, "setA2dpActiveDevice(" + device + ")");
        A2dpService a2dpService = this.mFactory.getA2dpService();
        if (a2dpService == null || !a2dpService.setActiveDevice(device)) {
            return;
        }
        this.mA2dpActiveDevice = device;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHfpActiveDevice(BluetoothDevice device) {
        Log.d(TAG, "setHfpActiveDevice(" + device + ")");
        HeadsetService headsetService = this.mFactory.getHeadsetService();
        if (headsetService == null || !headsetService.setActiveDevice(device)) {
            return;
        }
        this.mHfpActiveDevice = device;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHearingAidActiveDevice(BluetoothDevice device) {
        Log.d(TAG, "setHearingAidActiveDevice(" + device + ")");
        HearingAidService hearingAidService = this.mFactory.getHearingAidService();
        if (hearingAidService == null || !hearingAidService.setActiveDevice(device)) {
            return;
        }
        this.mHearingAidActiveDevice = device;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetState() {
        this.mA2dpConnectedDevices.clear();
        this.mA2dpActiveDevice = null;
        this.mHfpConnectedDevices.clear();
        this.mHfpActiveDevice = null;
        this.mHearingAidActiveDevice = null;
    }

    BroadcastReceiver getBroadcastReceiver() {
        return this.mReceiver;
    }

    BluetoothDevice getA2dpActiveDevice() {
        return this.mA2dpActiveDevice;
    }

    BluetoothDevice getHfpActiveDevice() {
        return this.mHfpActiveDevice;
    }

    BluetoothDevice getHearingAidActiveDevice() {
        return this.mHearingAidActiveDevice;
    }

    void wiredAudioDeviceConnected() {
        Log.d(TAG, "wiredAudioDeviceConnected");
        setA2dpActiveDevice(null);
        setHfpActiveDevice(null);
        setHearingAidActiveDevice(null);
    }
}
