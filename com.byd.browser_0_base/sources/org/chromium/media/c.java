package org.chromium.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import org.chromium.media.e;

/* JADX INFO: compiled from: AudioDeviceListener.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c extends BroadcastReceiver {
    public final /* synthetic */ d a;

    public c(d dVar) {
        this.a = dVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
        d dVar = this.a;
        dVar.getClass();
        if (d.c(usbDevice)) {
            boolean zEquals = "android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction());
            e.b bVar = dVar.f;
            if (zEquals) {
                bVar.a(4, true);
            } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction()) && !dVar.b()) {
                bVar.a(4, false);
            }
            e.this.e();
        }
    }
}
