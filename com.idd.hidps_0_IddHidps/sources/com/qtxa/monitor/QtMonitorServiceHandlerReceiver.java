package com.qtxa.monitor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.qtxa.monitor.b.a;
import com.qtxa.monitor.bt.BtStateReceiver;
import com.qtxa.monitor.usb.UsbStateReceiver;
import com.qtxa.monitor.wifi.WifiStateReceiver;
import com.qtxa.receiver.HandlerModuleReceiver;

/* JADX INFO: loaded from: classes.dex */
public class QtMonitorServiceHandlerReceiver extends HandlerModuleReceiver {
    private static WifiStateReceiver e;
    private static BtStateReceiver f;
    private static UsbStateReceiver g;

    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void d(Context context, Intent intent) {
        e = new WifiStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        context.getApplicationContext().registerReceiver(e, intentFilter);
        f = new BtStateReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter2.addAction("android.bluetooth.adapter.action.BLE_ACL_CONNECTED");
        intentFilter2.addAction("android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED");
        intentFilter2.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter2.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter2.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter2.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        a.b().d(context.getApplicationContext());
        com.qtxa.monitor.a.a.a().b(context.getApplicationContext());
        g = new UsbStateReceiver();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.hardware.usb.action.USB_STATE");
        intentFilter3.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter3.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter3.addAction("android.mtp.MtpClient.action.USB_PERMISSION");
        context.getApplicationContext().registerReceiver(g, intentFilter3);
    }

    @Override // com.qtxa.receiver.HandlerModuleReceiver
    public void e(Context context, Intent intent) {
        context.getApplicationContext().unregisterReceiver(e);
        context.getApplicationContext().unregisterReceiver(f);
        context.getApplicationContext().unregisterReceiver(g);
        a.b().g(context);
    }
}
