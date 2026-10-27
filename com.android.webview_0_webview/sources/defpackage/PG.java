package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import org.chromium.device.usb.ChromeUsbService;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PG extends BroadcastReceiver {
    public final /* synthetic */ ChromeUsbService a;

    public PG(ChromeUsbService chromeUsbService) {
        this.a = chromeUsbService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
            ChromeUsbService chromeUsbService = this.a;
            N.MNmyS$Xi(chromeUsbService.a, chromeUsbService, usbDevice);
        } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
            ChromeUsbService chromeUsbService2 = this.a;
            N.MrBuy2sN(chromeUsbService2.a, chromeUsbService2, usbDevice.getDeviceId());
        } else if ("org.chromium.device.ACTION_USB_PERMISSION".equals(intent.getAction())) {
            ChromeUsbService chromeUsbService3 = this.a;
            N.MDvFAfgT(chromeUsbService3.a, chromeUsbService3, usbDevice.getDeviceId(), intent.getBooleanExtra("permission", false));
        }
    }
}
