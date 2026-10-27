package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import org.chromium.device.usb.ChromeUsbService;

/* JADX INFO: compiled from: ChromeUsbService.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ws extends BroadcastReceiver {
    public final /* synthetic */ ChromeUsbService a;

    public ws(ChromeUsbService chromeUsbService) {
        this.a = chromeUsbService;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (q11.g(intent)) {
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            ChromeUsbService chromeUsbService = this.a;
            N.MDvFAfgT(chromeUsbService.a, chromeUsbService, usbDevice.getDeviceId(), intent.getBooleanExtra("permission", false));
        }
    }
}
