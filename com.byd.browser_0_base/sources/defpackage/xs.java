package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import org.chromium.device.usb.ChromeUsbService;

/* JADX INFO: compiled from: ChromeUsbService.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xs extends BroadcastReceiver {
    public final /* synthetic */ ChromeUsbService a;

    public xs(ChromeUsbService chromeUsbService) {
        this.a = chromeUsbService;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
        boolean zEquals = "android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction());
        ChromeUsbService chromeUsbService = this.a;
        if (zEquals) {
            N.MNmyS$Xi(chromeUsbService.a, chromeUsbService, usbDevice);
        } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
            N.MrBuy2sN(chromeUsbService.a, chromeUsbService, usbDevice.getDeviceId());
        }
    }
}
