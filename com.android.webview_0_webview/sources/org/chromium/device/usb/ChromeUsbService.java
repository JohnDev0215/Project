package org.chromium.device.usb;

import J.N;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import defpackage.Is;
import defpackage.PG;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeUsbService {
    public long a;
    public UsbManager b = (UsbManager) Is.a.getSystemService("usb");
    public BroadcastReceiver c = new PG(this);

    public ChromeUsbService(long j) {
        this.a = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("org.chromium.device.ACTION_USB_PERMISSION");
        Is.a.registerReceiver(this.c, intentFilter);
    }

    private void close() {
        Is.a.unregisterReceiver(this.c);
        this.c = null;
    }

    public static ChromeUsbService create(long j) {
        return new ChromeUsbService(j);
    }

    private Object[] getDevices() {
        return this.b.getDeviceList().values().toArray();
    }

    private boolean hasDevicePermission(ChromeUsbDevice chromeUsbDevice) {
        return this.b.hasPermission(chromeUsbDevice.a);
    }

    private UsbDeviceConnection openDevice(ChromeUsbDevice chromeUsbDevice) {
        return this.b.openDevice(chromeUsbDevice.a);
    }

    private void requestDevicePermission(ChromeUsbDevice chromeUsbDevice) {
        UsbDevice usbDevice = chromeUsbDevice.a;
        if (this.b.hasPermission(usbDevice)) {
            N.MDvFAfgT(this.a, this, usbDevice.getDeviceId(), true);
        } else {
            this.b.requestPermission(chromeUsbDevice.a, PendingIntent.getBroadcast(Is.a, 0, new Intent("org.chromium.device.ACTION_USB_PERMISSION"), 0));
        }
    }
}
