package OooO00o.OooO00o.OooO0OO.OooO0O0;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0OO implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UsbInterface f48a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f49b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f50c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public UsbDeviceConnection f51d;

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<c> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0O0 f52OooO00o = new OooO0O0();

        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0OO$OooO0OO, reason: collision with other inner class name */
    public static final class C0002OooO0OO extends Lambda implements kotlin.jvm.b.a<Integer> {
        public C0002OooO0OO() {
            super(0);
        }

        public final int OooO00o() {
            return OooO0OO.this.f48a.getId();
        }

        @Override // kotlin.jvm.b.a
        public /* bridge */ /* synthetic */ Integer invoke() {
            return Integer.valueOf(OooO00o());
        }
    }

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

    public OooO0OO(UsbInterface usbInterface) {
        h.d(usbInterface, "usbInterface");
        this.f48a = usbInterface;
        this.f49b = d.b(new C0002OooO0OO());
        this.f50c = d.b(OooO0O0.f52OooO00o);
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public byte[] a(byte b2, byte b3, byte b4) {
        if (b2 == -1) {
            return d();
        }
        byte[] bArr = {b2, b3, b4};
        c(bArr);
        byte[] bArr2 = new byte[3];
        e().b(this.f51d, f(), bArr, bArr2);
        byte[] bArr3 = {bArr2[1], bArr2[2]};
        e().b(this.f51d, f(), bArr, bArr2);
        bArr3[0] = (byte) (bArr2[1] ^ bArr3[0]);
        bArr3[1] = (byte) (bArr2[2] ^ bArr3[1]);
        return bArr3;
    }

    public final void c(byte[] bArr) {
        if (bArr.length != 3) {
            return;
        }
        bArr[0] = -1;
        bArr[1] = 0;
        bArr[2] = 0;
    }

    public final byte[] d() {
        byte[] bArr = {(byte) (Math.random() * ((double) 128)), 0, 0};
        c(bArr);
        byte[] bArr2 = new byte[3];
        e().b(this.f51d, f(), bArr, bArr2);
        byte[] bArr3 = {bArr2[0], 0};
        e().b(this.f51d, f(), bArr, bArr2);
        bArr3[0] = (byte) (((byte) (bArr2[0] ^ ((byte) (bArr[0] ^ bArr3[0])))) * (-1));
        return bArr3;
    }

    public final c e() {
        return (c) this.f50c.getValue();
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public void endHid() {
        try {
            UsbDeviceConnection usbDeviceConnection = this.f51d;
            if (usbDeviceConnection != null) {
                usbDeviceConnection.releaseInterface(this.f48a);
            }
            UsbDeviceConnection usbDeviceConnection2 = this.f51d;
            if (usbDeviceConnection2 != null) {
                usbDeviceConnection2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f51d = null;
    }

    public final int f() {
        return ((Number) this.f49b.getValue()).intValue();
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public boolean startHid(UsbDevice usbDevice) {
        try {
            UsbDeviceConnection usbDeviceConnectionOpenDevice = OooO00o.OooO00o.OooO0O0.OooO0OO.INTANCE.OooO00o().openDevice(usbDevice);
            this.f51d = usbDeviceConnectionOpenDevice;
            if (usbDeviceConnectionOpenDevice != null) {
                Boolean boolValueOf = Boolean.valueOf(usbDeviceConnectionOpenDevice.claimInterface(this.f48a, true));
                h.b(boolValueOf);
                if (boolValueOf.booleanValue()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        endHid();
        return false;
    }
}
