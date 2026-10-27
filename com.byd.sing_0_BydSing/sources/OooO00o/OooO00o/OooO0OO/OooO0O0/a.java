package OooO00o.OooO00o.OooO0OO.OooO0O0;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public UsbInterface f61a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f62b;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0OO.OooO0O0.a$a, reason: collision with other inner class name */
    public static final class C0004a {
        public C0004a() {
        }

        public /* synthetic */ C0004a(f fVar) {
            this();
        }
    }

    static {
        new C0004a(null);
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public byte[] a(byte b2, byte b3, byte b4) {
        b bVar = this.f62b;
        if (bVar == null) {
            return new byte[]{0, 0};
        }
        h.b(bVar);
        byte[] bArrA = bVar.a(b2, b3, b4);
        h.c(bArrA, "hidCommunication!!.getBytes(inputByte0, inputByte1, inputByte2)");
        return bArrA;
    }

    public final boolean b(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        if (interfaceCount > 0) {
            if (interfaceCount > 0) {
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    UsbInterface usbInterface = usbDevice.getInterface(i);
                    h.c(usbInterface, "usbDevice.getInterface(index)");
                    if (usbInterface.getInterfaceClass() == 3 && usbInterface.getEndpointCount() > 0) {
                        this.f61a = usbInterface;
                        return true;
                    }
                    if (i2 >= interfaceCount) {
                        break;
                    }
                    i = i2;
                }
            }
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy2", " ------> UsbInterface not found");
        } else {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy2", " ------> UsbInterface is null");
        }
        return false;
    }

    public final boolean c(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        if (this.f61a != null || b(usbDevice)) {
            return startHid(usbDevice);
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy2", "start hid, checkInterface false");
        return false;
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public void endHid() {
        b bVar = this.f62b;
        if (bVar != null) {
            bVar.endHid();
        }
        this.f62b = null;
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public boolean startHid(UsbDevice usbDevice) {
        if (this.f62b == null) {
            UsbInterface usbInterface = this.f61a;
            h.b(usbInterface);
            this.f62b = new OooO0OO(usbInterface);
        }
        b bVar = this.f62b;
        if (bVar == null) {
            return false;
        }
        return bVar.startHid(usbDevice);
    }
}
