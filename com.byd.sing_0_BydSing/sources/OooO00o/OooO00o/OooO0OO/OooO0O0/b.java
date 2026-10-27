package OooO00o.OooO00o.OooO0OO.OooO0O0;

import android.hardware.usb.UsbDevice;

/* JADX INFO: loaded from: classes.dex */
public interface b {
    byte[] a(byte b2, byte b3, byte b4);

    void endHid();

    boolean startHid(UsbDevice usbDevice);
}
