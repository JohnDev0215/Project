package com.android.bluetooth.btservice;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.hfp.HeadsetService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SilenceDeviceManager {
    private static final boolean DBG = true;
    private static final int DISABLE_SILENCE = 1;
    private static final int ENABLE_SILENCE = 0;
    private static final int MSG_A2DP_ACTIVE_DEIVCE_CHANGED = 20;
    private static final int MSG_A2DP_CONNECTION_STATE_CHANGED = 10;
    private static final int MSG_HFP_ACTIVE_DEVICE_CHANGED = 21;
    private static final int MSG_HFP_CONNECTION_STATE_CHANGED = 11;
    private static final int MSG_SILENCE_DEVICE_STATE_CHANGED = 1;
    private static final String TAG = "SilenceDeviceManager";
    private static final boolean VERBOSE = false;
    private final AdapterService mAdapterService;
    private final ServiceFactory mFactory;
    private Looper mLooper;
    private Handler mHandler = null;
    private final Map<BluetoothDevice, Boolean> mSilenceDevices = new HashMap();
    private final List<BluetoothDevice> mA2dpConnectedDevices = new ArrayList();
    private final List<BluetoothDevice> mHfpConnectedDevices = new ArrayList();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.SilenceDeviceManager.1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.e(SilenceDeviceManager.TAG, "Received intent with null action");
                return;
            }
            byte b = -1;
            switch (action.hashCode()) {
                case 17117692:
                    if (action.equals("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 3;
                    }
                    break;
                case 487423555:
                    if (action.equals("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 2;
                    }
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 1;
                    }
                    break;
                case 1244161670:
                    if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                SilenceDeviceManager.this.mHandler.obtainMessage(10, intent).sendToTarget();
                return;
            }
            if (b == 1) {
                SilenceDeviceManager.this.mHandler.obtainMessage(11, intent).sendToTarget();
                return;
            }
            if (b == 2) {
                SilenceDeviceManager.this.mHandler.obtainMessage(20, intent).sendToTarget();
                return;
            }
            if (b == 3) {
                SilenceDeviceManager.this.mHandler.obtainMessage(21, intent).sendToTarget();
                return;
            }
            Log.e(SilenceDeviceManager.TAG, "Received unexpected intent, action=" + action);
        }
    };

    class SilenceDeviceManagerHandler extends Handler {
        SilenceDeviceManagerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                BluetoothDevice device = (BluetoothDevice) msg.obj;
                boolean state = msg.arg1 == 0;
                SilenceDeviceManager.this.handleSilenceDeviceStateChanged(device, state);
                return;
            }
            if (i == 10) {
                Intent intent = (Intent) msg.obj;
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                int nextState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (nextState == 2) {
                    SilenceDeviceManager.this.addConnectedDevice(device2, 2);
                    if (!SilenceDeviceManager.this.mSilenceDevices.containsKey(device2)) {
                        SilenceDeviceManager.this.mSilenceDevices.put(device2, false);
                        return;
                    }
                    return;
                }
                if (prevState == 2) {
                    SilenceDeviceManager.this.removeConnectedDevice(device2, 2);
                    if (!SilenceDeviceManager.this.isBluetoothAudioConnected(device2)) {
                        SilenceDeviceManager.this.handleSilenceDeviceStateChanged(device2, false);
                        SilenceDeviceManager.this.mSilenceDevices.remove(device2);
                        return;
                    }
                    return;
                }
                return;
            }
            if (i != 11) {
                if (i == 20) {
                    BluetoothDevice a2dpActiveDevice = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (SilenceDeviceManager.this.getSilenceMode(a2dpActiveDevice)) {
                        SilenceDeviceManager.this.setSilenceMode(a2dpActiveDevice, false);
                        return;
                    }
                    return;
                }
                if (i == 21) {
                    BluetoothDevice hfpActiveDevice = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (SilenceDeviceManager.this.getSilenceMode(hfpActiveDevice)) {
                        SilenceDeviceManager.this.setSilenceMode(hfpActiveDevice, false);
                        return;
                    }
                    return;
                }
                Log.e(SilenceDeviceManager.TAG, "Unknown message: " + msg.what);
                return;
            }
            Intent intent2 = (Intent) msg.obj;
            BluetoothDevice device3 = (BluetoothDevice) intent2.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int prevState2 = intent2.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
            int nextState2 = intent2.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            if (nextState2 == 2) {
                SilenceDeviceManager.this.addConnectedDevice(device3, 1);
                if (!SilenceDeviceManager.this.mSilenceDevices.containsKey(device3)) {
                    SilenceDeviceManager.this.mSilenceDevices.put(device3, false);
                    return;
                }
                return;
            }
            if (prevState2 == 2) {
                SilenceDeviceManager.this.removeConnectedDevice(device3, 1);
                if (!SilenceDeviceManager.this.isBluetoothAudioConnected(device3)) {
                    SilenceDeviceManager.this.handleSilenceDeviceStateChanged(device3, false);
                    SilenceDeviceManager.this.mSilenceDevices.remove(device3);
                }
            }
        }
    }

    SilenceDeviceManager(AdapterService service, ServiceFactory factory, Looper looper) {
        this.mLooper = null;
        this.mAdapterService = service;
        this.mFactory = factory;
        this.mLooper = looper;
    }

    void start() {
        this.mHandler = new SilenceDeviceManagerHandler(this.mLooper);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED");
        filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED");
        this.mAdapterService.registerReceiver(this.mReceiver, filter);
    }

    void cleanup() {
        this.mSilenceDevices.clear();
        this.mAdapterService.unregisterReceiver(this.mReceiver);
    }

    boolean setSilenceMode(BluetoothDevice bluetoothDevice, boolean z) {
        if (this.mHandler == null) {
            Log.e(TAG, "setSilenceMode() mHandler is null!");
            return false;
        }
        Log.d(TAG, "setSilenceMode: " + bluetoothDevice.getAddress() + ", " + z);
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, !z ? 1 : 0, 0, bluetoothDevice));
        return true;
    }

    void handleSilenceDeviceStateChanged(BluetoothDevice device, boolean state) {
        boolean oldState = getSilenceMode(device);
        if (oldState == state) {
            return;
        }
        if (!isBluetoothAudioConnected(device)) {
            if (oldState) {
                state = false;
            } else {
                Log.d(TAG, "Deivce is not connected to any Bluetooth audio.");
                return;
            }
        }
        this.mSilenceDevices.replace(device, Boolean.valueOf(state));
        A2dpService a2dpService = this.mFactory.getA2dpService();
        if (a2dpService != null) {
            a2dpService.setSilenceMode(device, state);
        }
        HeadsetService headsetService = this.mFactory.getHeadsetService();
        if (headsetService != null) {
            headsetService.setSilenceMode(device, state);
        }
        Log.i(TAG, "Silence mode change " + device.getAddress() + ": " + oldState + " -> " + state);
        broadcastSilenceStateChange(device, state);
    }

    void broadcastSilenceStateChange(BluetoothDevice device, boolean state) {
        Intent intent = new Intent("android.bluetooth.device.action.SILENCE_MODE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        this.mAdapterService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
    }

    boolean getSilenceMode(BluetoothDevice device) {
        if (!this.mSilenceDevices.containsKey(device)) {
            return false;
        }
        boolean state = this.mSilenceDevices.get(device).booleanValue();
        return state;
    }

    void addConnectedDevice(BluetoothDevice device, int profile) {
        if (profile != 1) {
            if (profile == 2 && !this.mA2dpConnectedDevices.contains(device)) {
                this.mA2dpConnectedDevices.add(device);
                return;
            }
            return;
        }
        if (!this.mHfpConnectedDevices.contains(device)) {
            this.mHfpConnectedDevices.add(device);
        }
    }

    void removeConnectedDevice(BluetoothDevice device, int profile) {
        if (profile != 1) {
            if (profile == 2 && this.mA2dpConnectedDevices.contains(device)) {
                this.mA2dpConnectedDevices.remove(device);
                return;
            }
            return;
        }
        if (this.mHfpConnectedDevices.contains(device)) {
            this.mHfpConnectedDevices.remove(device);
        }
    }

    boolean isBluetoothAudioConnected(BluetoothDevice device) {
        return this.mA2dpConnectedDevices.contains(device) || this.mHfpConnectedDevices.contains(device);
    }

    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.println("\nSilenceDeviceManager:");
        writer.println("  Address            | Is silenced?");
        for (BluetoothDevice device : this.mSilenceDevices.keySet()) {
            writer.println("  " + device.getAddress() + "  | " + getSilenceMode(device));
        }
    }

    BroadcastReceiver getBroadcastReceiver() {
        return this.mReceiver;
    }
}
