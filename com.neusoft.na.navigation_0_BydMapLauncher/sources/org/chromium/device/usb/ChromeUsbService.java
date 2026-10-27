package org.chromium.device.usb;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeUsbService {
    private static final String ACTION_USB_PERMISSION = "org.chromium.device.ACTION_USB_PERMISSION";
    private static final String TAG = "Usb";
    Context mContext;
    BroadcastReceiver mUsbDeviceReceiver;
    UsbManager mUsbManager;
    long mUsbServiceAndroid;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDeviceAttached(long j, UsbDevice usbDevice);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDeviceDetached(long j, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDevicePermissionRequestComplete(long j, int i, boolean z);

    private ChromeUsbService(Context context, long j) {
        this.mContext = context;
        this.mUsbServiceAndroid = j;
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
        registerForUsbDeviceIntentBroadcast();
        Log.v(TAG, "ChromeUsbService created.");
    }

    private static ChromeUsbService create(Context context, long j) {
        return new ChromeUsbService(context, j);
    }

    private Object[] getDevices() {
        return this.mUsbManager.getDeviceList().values().toArray();
    }

    private UsbDeviceConnection openDevice(ChromeUsbDevice chromeUsbDevice) {
        return this.mUsbManager.openDevice(chromeUsbDevice.getDevice());
    }

    private void requestDevicePermission(ChromeUsbDevice chromeUsbDevice, long j) {
        UsbDevice device = chromeUsbDevice.getDevice();
        if (this.mUsbManager.hasPermission(device)) {
            nativeDevicePermissionRequestComplete(this.mUsbServiceAndroid, device.getDeviceId(), true);
        } else {
            this.mUsbManager.requestPermission(chromeUsbDevice.getDevice(), PendingIntent.getBroadcast(this.mContext, 0, new Intent(ACTION_USB_PERMISSION), 0));
        }
    }

    private void close() {
        unregisterForUsbDeviceIntentBroadcast();
    }

    private void registerForUsbDeviceIntentBroadcast() {
        this.mUsbDeviceReceiver = new BroadcastReceiver() { // from class: org.chromium.device.usb.ChromeUsbService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                    ChromeUsbService chromeUsbService = ChromeUsbService.this;
                    chromeUsbService.nativeDeviceAttached(chromeUsbService.mUsbServiceAndroid, usbDevice);
                } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                    ChromeUsbService chromeUsbService2 = ChromeUsbService.this;
                    chromeUsbService2.nativeDeviceDetached(chromeUsbService2.mUsbServiceAndroid, usbDevice.getDeviceId());
                } else if (ChromeUsbService.ACTION_USB_PERMISSION.equals(intent.getAction())) {
                    ChromeUsbService chromeUsbService3 = ChromeUsbService.this;
                    chromeUsbService3.nativeDevicePermissionRequestComplete(chromeUsbService3.mUsbServiceAndroid, usbDevice.getDeviceId(), intent.getBooleanExtra("permission", false));
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction(ACTION_USB_PERMISSION);
        this.mContext.registerReceiver(this.mUsbDeviceReceiver, intentFilter);
    }

    private void unregisterForUsbDeviceIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mUsbDeviceReceiver);
        this.mUsbDeviceReceiver = null;
    }
}
