package a.a.d.c;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import android.hardware.usb.UsbDevice;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: a.a.d.c.a$a, reason: collision with other inner class name */
    public static final class C0027a {
        public static void a(a aVar, UsbDevice usbDevice, boolean z) {
            h.d(aVar, "this");
            h.d(usbDevice, "usbDevice");
            OooO00o.OooO00o.OooO0O0.b.f41a.e("DongleCallback", h.i("onMicConnectState, status = ", Boolean.valueOf(z)));
        }
    }

    void onDeviceAddedWithHid(OooO00o oooO00o);

    void onDeviceDeniedByHid(OooO00o oooO00o);

    void onDeviceRemoved(OooO00o oooO00o);

    void onDonglePermissionDenied();

    void onDonglePermissionGranted(UsbDevice usbDevice);

    void onMicConnectState(UsbDevice usbDevice, boolean z);
}
