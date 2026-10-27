package com.android.server.telecom.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telecom.Log;
import android.telecom.Logging.Session;
import com.android.internal.os.SomeArgs;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothStateReceiver extends BroadcastReceiver {
    public static final IntentFilter INTENT_FILTER;
    private static final String LOG_TAG = BluetoothStateReceiver.class.getSimpleName();
    private final BluetoothDeviceManager mBluetoothDeviceManager;
    private final BluetoothRouteManager mBluetoothRouteManager;
    private boolean mIsInCall = false;

    static {
        IntentFilter intentFilter = new IntentFilter();
        INTENT_FILTER = intentFilter;
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        INTENT_FILTER.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        INTENT_FILTER.addAction("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED");
        INTENT_FILTER.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        INTENT_FILTER.addAction("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.startSession("BSR.oR");
        try {
            String action = intent.getAction();
            byte b = -1;
            switch (action.hashCode()) {
                case -1435586571:
                    if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                        b = 0;
                    }
                    break;
                case -612790895:
                    if (action.equals("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 1;
                    }
                    break;
                case 17117692:
                    if (action.equals("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 4;
                    }
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 2;
                    }
                    break;
                case 1176349464:
                    if (action.equals("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        b = 3;
                    }
                    break;
            }
            if (b == 0) {
                handleAudioStateChanged(intent);
            } else if (b == 1 || b == 2) {
                handleConnectionStateChanged(intent);
            } else if (b == 3 || b == 4) {
                handleActiveDeviceChanged(intent);
            }
        } finally {
            Log.endSession();
        }
    }

    private void handleAudioStateChanged(Intent intent) {
        String str = LOG_TAG;
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            Log.w(str, "Got null device from broadcast. Ignoring.", new Object[0]);
            return;
        }
        Log.i(str, "Device %s transitioned to audio state %d", new Object[]{bluetoothDevice.getAddress(), Integer.valueOf(intExtra)});
        Session sessionCreateSubsession = Log.createSubsession();
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = sessionCreateSubsession;
        someArgsObtain.arg2 = bluetoothDevice.getAddress();
        if (intExtra == 10) {
            this.mBluetoothRouteManager.sendMessage(201, someArgsObtain);
        } else {
            if (intExtra != 12) {
                return;
            }
            if (!this.mIsInCall) {
                Log.i(str, "Ignoring BT audio on since we're not in a call", new Object[0]);
            } else {
                this.mBluetoothRouteManager.sendMessage(200, someArgsObtain);
            }
        }
    }

    private void handleConnectionStateChanged(Intent intent) {
        String str = LOG_TAG;
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            Log.w(str, "Got null device from broadcast. Ignoring.", new Object[0]);
            return;
        }
        Log.i(str, "Device %s changed state to %d", new Object[]{bluetoothDevice.getAddress(), Integer.valueOf(intExtra)});
        boolean zEquals = "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction());
        if (intExtra == 2) {
            this.mBluetoothDeviceManager.onDeviceConnected(bluetoothDevice, zEquals);
        } else if (intExtra == 0 || intExtra == 3) {
            this.mBluetoothDeviceManager.onDeviceDisconnected(bluetoothDevice, zEquals);
        }
    }

    private void handleActiveDeviceChanged(Intent intent) {
        String str = LOG_TAG;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        boolean zEquals = "android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED".equals(intent.getAction());
        Object[] objArr = new Object[2];
        objArr[0] = bluetoothDevice;
        objArr[1] = zEquals ? "hearing aid" : "HFP";
        Log.i(str, "Device %s is now the preferred BT device for %s", objArr);
        this.mBluetoothRouteManager.onActiveDeviceChanged(bluetoothDevice, zEquals);
        if (zEquals) {
            Session sessionCreateSubsession = Log.createSubsession();
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = sessionCreateSubsession;
            if (bluetoothDevice == null) {
                this.mBluetoothRouteManager.sendMessage(201, someArgsObtain);
            } else if (!this.mIsInCall) {
                Log.i(str, "Ignoring hearing aid audio on since we're not in a call", new Object[0]);
            } else {
                someArgsObtain.arg2 = bluetoothDevice.getAddress();
                this.mBluetoothRouteManager.sendMessage(200, someArgsObtain);
            }
        }
    }

    public BluetoothDeviceManager getBluetoothDeviceManager() {
        return this.mBluetoothDeviceManager;
    }

    public BluetoothStateReceiver(BluetoothDeviceManager bluetoothDeviceManager, BluetoothRouteManager bluetoothRouteManager) {
        this.mBluetoothDeviceManager = bluetoothDeviceManager;
        this.mBluetoothRouteManager = bluetoothRouteManager;
    }

    public void setIsInCall(boolean z) {
        this.mIsInCall = z;
    }
}
