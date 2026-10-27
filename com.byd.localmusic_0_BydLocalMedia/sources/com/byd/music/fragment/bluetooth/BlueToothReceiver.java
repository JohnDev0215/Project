package com.byd.music.fragment.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class BlueToothReceiver extends BroadcastReceiver {
    private final String TAG = BlueToothReceiver.class.getName();
    private ReceiverListener receiverListener;

    public BlueToothReceiver(ReceiverListener receiverListener) {
        this.receiverListener = receiverListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            LogUtil.d(this.TAG, "蓝牙连接");
            this.receiverListener.connectMediaBrowser();
        } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            LogUtil.d(this.TAG, "蓝牙断开");
            this.receiverListener.disConnectMediaBrowser();
        }
    }
}
