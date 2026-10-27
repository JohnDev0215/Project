package org.chromium.device.usb;

import J.N;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import defpackage.q11;
import defpackage.ws;
import defpackage.xs;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeUsbService {
    public final long a;
    public final UsbManager b = (UsbManager) xz.a.getSystemService("usb");
    public ws c = new ws(this);
    public xs d = new xs(this);

    public ChromeUsbService(long j) {
        this.a = j;
        Context context = xz.a;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("org.chromium.device.ACTION_USB_PERMISSION");
        xz.f(context, this.c, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        xz.g(context, this.d, intentFilter2);
    }

    @CalledByNative
    public static ChromeUsbService create(long j) {
        return new ChromeUsbService(j);
    }

    @CalledByNative
    public final void close() {
        xz.a.unregisterReceiver(this.d);
        this.d = null;
        xz.a.unregisterReceiver(this.c);
        this.c = null;
    }

    @CalledByNative
    public final Object[] getDevices() {
        return this.b.getDeviceList().values().toArray();
    }

    @CalledByNative
    public final boolean hasDevicePermission(ChromeUsbDevice chromeUsbDevice) {
        return this.b.hasPermission(chromeUsbDevice.a);
    }

    @CalledByNative
    public final UsbDeviceConnection openDevice(ChromeUsbDevice chromeUsbDevice) {
        return this.b.openDevice(chromeUsbDevice.a);
    }

    @CalledByNative
    public final void requestDevicePermission(ChromeUsbDevice chromeUsbDevice) {
        UsbDevice usbDevice = chromeUsbDevice.a;
        UsbManager usbManager = this.b;
        if (usbManager.hasPermission(usbDevice)) {
            N.MDvFAfgT(this.a, this, usbDevice.getDeviceId(), true);
            return;
        }
        Context context = xz.a;
        Intent intent = new Intent("org.chromium.device.ACTION_USB_PERMISSION");
        intent.setPackage(context.getPackageName());
        q11.a(intent);
        usbManager.requestPermission(chromeUsbDevice.a, PendingIntent.getBroadcast(context, 0, intent, q11.d(true)));
    }
}
