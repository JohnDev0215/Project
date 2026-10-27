package com.android.bluetooth.hfpclient;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import com.android.bluetooth.btservice.ProfileService;

/* JADX INFO: loaded from: classes.dex */
class HeadsetClientHandler extends Handler {
    public static final String ACTION_CUSTOM_ACTION = "android.bluetooth.headsetclient.action.CUSTOM_ACTION";
    public static final String ACTION_CUSTOM_ACTION_RESULT = "android.bluetooth.headsetclient.action.CUSTOM_ACTION_RESULT";
    public static final String CUSTOM_ACTION_RELEASE_CALL = "android.bluetooth.headsetclient.CUSTOM_ACTION_RELEASE_CALL";
    static final boolean DBG = true;
    public static final String EXTRA_CUSTOM_ACTION = "android.bluetooth.headsetclient.extra.CUSTOM_ACTION";
    public static final String EXTRA_CUSTOM_ACTION_RESULT = "android.bluetooth.headsetclient.extra.CUSTOM_ACTION_RESULT";
    public static final String HEADSET_CLIENT_ENABLE_PTS_PROPERTY = "persist.bt.headsetclient.enable_pts";
    public static final String KEY_CALL_INDEX = "index";
    public static final String KEY_COMMAND = "command";
    public static final String KEY_RESULT = "result";
    static final int MSG_CUSTOM_ACTION = 1;
    static final String TAG = "HeadsetClientHandler";
    private HeadsetClientService mService;

    HeadsetClientHandler(Context context) {
        this.mService = (HeadsetClientService) context;
    }

    HeadsetClientHandler(Builder pceHandlerbuild) {
        this.mService = (HeadsetClientService) pceHandlerbuild.context;
    }

    public static class Builder {
        private HeadsetClientStateMachine clientStateMachine;
        private Context context;
        private Looper looper;

        public Builder setLooper(Looper loop) {
            this.looper = loop;
            return this;
        }

        public Builder setClientSM(HeadsetClientStateMachine clientStateMachine) {
            this.clientStateMachine = clientStateMachine;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public HeadsetClientHandler build() {
            HeadsetClientHandler headsetClientHandler = new HeadsetClientHandler(this);
            return headsetClientHandler;
        }
    }

    public static boolean isPtsEnabled() {
        return SystemProperties.getBoolean(HEADSET_CLIENT_ENABLE_PTS_PROPERTY, false);
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        Log.d(TAG, "Handling Message = " + msg.what);
        if (msg.what == 1) {
            handleCustomAction((Bundle) msg.obj);
        } else {
            Log.w(TAG, "Received Unexpected Message");
        }
    }

    private void handleCustomAction(Bundle extras) {
        Log.d(TAG, "handleCustomAction extras=" + extras);
        if (extras == null) {
            return;
        }
        String cmd = extras.getString(KEY_COMMAND);
        BluetoothDevice device = (BluetoothDevice) extras.get("android.bluetooth.device.extra.DEVICE");
        if (CUSTOM_ACTION_RELEASE_CALL.equals(cmd)) {
            handleReleaseCall(device, extras);
            return;
        }
        Log.w(TAG, "Custom action " + cmd + " not supported.");
    }

    private void handleReleaseCall(BluetoothDevice device, Bundle extras) {
        Log.d(TAG, "handleReleaseCall");
        int index = extras.getInt(KEY_CALL_INDEX);
        boolean result = this.mService.releaseCall(device, index);
        notifyCustomActionResult(CUSTOM_ACTION_RELEASE_CALL, result ? 0 : 1);
    }

    public void notifyCustomActionResult(String cmd, int result) {
        Log.d(TAG, "notifyCustomActionResult cmd: " + cmd + ", result: " + result);
        Bundle extras = new Bundle();
        extras.putString(KEY_COMMAND, cmd);
        extras.putInt("result", result);
        notifyCustomActionResult(extras);
    }

    public void notifyCustomActionResult(Bundle extras) {
        Log.d(TAG, "notifyCustomActionResult");
        Intent intent = new Intent(ACTION_CUSTOM_ACTION_RESULT);
        intent.putExtra(EXTRA_CUSTOM_ACTION_RESULT, extras);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    public static int getLocation(BluetoothHeadsetClientCall call) {
        String number;
        if (call == null || (number = call.getNumber()) == null || number.isEmpty()) {
            return 0;
        }
        int location = Integer.parseInt(number, 10);
        return location;
    }
}
