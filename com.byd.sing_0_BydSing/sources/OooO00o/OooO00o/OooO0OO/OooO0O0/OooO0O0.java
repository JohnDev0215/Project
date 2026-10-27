package OooO00o.OooO00o.OooO0OO.OooO0O0;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import com.loostone.libcore.jni.JniCall;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0O0 implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UsbInterface f44a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f45b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public UsbDeviceConnection f46c;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0O0$OooO0O0, reason: collision with other inner class name */
    public static final class C0001OooO0O0 extends Lambda implements kotlin.jvm.b.a<Integer> {
        public C0001OooO0O0() {
            super(0);
        }

        public final int OooO00o() {
            return OooO0O0.this.f44a.getId();
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

    public OooO0O0(UsbInterface usbInterface) {
        h.d(usbInterface, "usbInterface");
        this.f44a = usbInterface;
        this.f45b = d.b(new C0001OooO0O0());
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public byte[] a(byte b2, byte b3, byte b4) {
        byte[] bytes;
        synchronized (OooO0O0.class) {
            JniCall jniCall = JniCall.f3801OooO00o;
            UsbDeviceConnection usbDeviceConnection = this.f46c;
            h.b(usbDeviceConnection);
            bytes = jniCall.getBytes(usbDeviceConnection, b(), b2, b3, b4);
        }
        return bytes;
    }

    public final int b() {
        return ((Number) this.f45b.getValue()).intValue();
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public void endHid() {
        synchronized (OooO0O0.class) {
            try {
                UsbDeviceConnection usbDeviceConnection = this.f46c;
                if (usbDeviceConnection != null) {
                    usbDeviceConnection.releaseInterface(this.f44a);
                }
                UsbDeviceConnection usbDeviceConnection2 = this.f46c;
                if (usbDeviceConnection2 != null) {
                    usbDeviceConnection2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f46c = null;
        }
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public boolean startHid(UsbDevice usbDevice) {
        UsbDeviceConnection usbDeviceConnectionOpenDevice;
        synchronized (OooO0O0.class) {
            try {
                usbDeviceConnectionOpenDevice = OooO00o.OooO00o.OooO0O0.OooO0OO.INTANCE.OooO00o().openDevice(usbDevice);
                this.f46c = usbDeviceConnectionOpenDevice;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (usbDeviceConnectionOpenDevice == null) {
                endHid();
                return false;
            }
            Boolean boolValueOf = Boolean.valueOf(usbDeviceConnectionOpenDevice.claimInterface(this.f44a, true));
            h.b(boolValueOf);
            if (boolValueOf.booleanValue()) {
                return true;
            }
            endHid();
            return false;
        }
    }
}
