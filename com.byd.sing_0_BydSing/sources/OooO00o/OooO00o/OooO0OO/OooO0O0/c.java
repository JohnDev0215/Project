package OooO00o.OooO00o.OooO0OO.OooO0O0;

import android.hardware.usb.UsbDeviceConnection;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public void a(UsbDeviceConnection usbDeviceConnection, int i, byte[] bArr) {
        h.d(bArr, "message");
        if (usbDeviceConnection != null && usbDeviceConnection.controlTransfer(161, 1, 261, i, bArr, bArr.length, 1000) < 0) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("TransferNew", " receive command fail");
        }
    }

    public void b(UsbDeviceConnection usbDeviceConnection, int i, byte[] bArr, byte[] bArr2) {
        h.d(bArr, "inByte");
        h.d(bArr2, "outByte");
        byte[] bArr3 = new byte[4];
        c(usbDeviceConnection, i, new byte[]{5, bArr[0], bArr[1], bArr[2]});
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a(usbDeviceConnection, i, bArr3);
        bArr2[0] = bArr3[1];
        bArr2[1] = bArr3[2];
        bArr2[2] = bArr3[3];
    }

    public void c(UsbDeviceConnection usbDeviceConnection, int i, byte[] bArr) {
        h.d(bArr, "mCmd");
        if (usbDeviceConnection != null && usbDeviceConnection.controlTransfer(33, 9, 517, i, bArr, bArr.length, 1000) < 0) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("TransferNew", " send command fail");
        }
    }
}
