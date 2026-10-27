package OooO00o.OooO00o.OooO0OO.OooO0O0;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import com.loostone.libcore.jni.JniCall;
import java.util.Locale;
import java.util.Objects;
import kotlin.LazyThreadSafetyMode;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0o implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f54a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.a<OooO0o> f55b = d.a(LazyThreadSafetyMode.SYNCHRONIZED, OooO0O0.f59OooO00o);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f56c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public UsbInterface f57d;
    public b e;

    public enum OooO00o {
        SO(1),
        KO(2);

        public final int OooO0Oo;

        OooO00o(int i) {
            this.OooO0Oo = i;
        }

        public final int OooO0O0() {
            return this.OooO0Oo;
        }
    }

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<OooO0o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0O0 f59OooO00o = new OooO0O0();

        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO0o invoke() {
            return new OooO0o(null);
        }
    }

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o$OooO0o, reason: collision with other inner class name */
    public enum EnumC0003OooO0o {
        K(1),
        C(2);

        public final int OooO0Oo;

        EnumC0003OooO0o(int i) {
            this.OooO0Oo = i;
        }

        public final int OooO0O0() {
            return this.OooO0Oo;
        }
    }

    public static final class a {
        static {
            j.d(new PropertyReference1Impl(j.a(a.class), "instance", "getInstance()Lcom/loostone/libcore/hid/HidProxy;"));
        }

        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }

        public final OooO0o a() {
            return (OooO0o) OooO0o.f55b.getValue();
        }
    }

    public OooO0o() {
    }

    public /* synthetic */ OooO0o(f fVar) {
        this();
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public byte[] a(byte b2, byte b3, byte b4) {
        b bVar = this.e;
        if (bVar != null) {
            h.b(bVar);
            byte[] bArrA = bVar.a(b2, b3, b4);
            h.c(bArrA, "{\n            hidCommunication!!.getBytes(inputByte0, inputByte1, inputByte2)\n        }");
            return bArrA;
        }
        try {
            i();
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", " ------> hidProxy release error");
            e.printStackTrace();
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e("HidProxy", " ------> hidProxy byteArray default");
        return new byte[]{0, 0};
    }

    public final int b(String str, String str2, String str3) {
        EnumC0003OooO0o enumC0003OooO0o;
        h.d(str, "vendorName");
        h.d(str2, "productName");
        h.d(str3, "serialNumber");
        if (o.k(str2, "byd-mic ls", true)) {
            enumC0003OooO0o = EnumC0003OooO0o.K;
        } else {
            int iW = StringsKt__StringsKt.w(str2, "-", 0, false, 6, null);
            if (iW > 0) {
                String strSubstring = str2.substring(iW + 1);
                h.c(strSubstring, "(this as java.lang.String).substring(startIndex)");
                Locale locale = Locale.ROOT;
                h.c(locale, "ROOT");
                Objects.requireNonNull(strSubstring, "null cannot be cast to non-null type java.lang.String");
                String lowerCase = strSubstring.toLowerCase(locale);
                h.c(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                enumC0003OooO0o = StringsKt__StringsKt.o(lowerCase, "k", false, 2, null) ? EnumC0003OooO0o.K : EnumC0003OooO0o.C;
            } else {
                enumC0003OooO0o = EnumC0003OooO0o.C;
            }
        }
        return JniCall.f3801OooO00o.setUsbDeviceParam(str, str2, str3, enumC0003OooO0o.OooO0O0());
    }

    public final boolean d(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o, int i) {
        h.d(oooO00o, "cachedDevice");
        UsbDevice usbDeviceW = oooO00o.w();
        if (usbDeviceW == null) {
            return false;
        }
        if (!h(usbDeviceW)) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", "check no permission");
            return false;
        }
        if (!e(usbDeviceW)) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", "check ------> check interface failure");
        } else {
            if (startHid(usbDeviceW)) {
                byte bOooO0O0 = (byte) (i == 1 ? OooO00o.SO : OooO00o.KO).OooO0O0();
                if (oooO00o.B()) {
                    a((byte) -17, (byte) 29, (byte) 1);
                } else {
                    a((byte) -17, (byte) 29, (byte) 0);
                }
                String strF = f((byte) -1, bOooO0O0, (byte) 0);
                OooO00o.OooO00o.OooO0O0.b.f41a.b("HidProxy", h.i("check ------> ", strF));
                endHid();
                return h.a("0001", strF);
            }
            endHid();
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", "check ------> start hid failure");
        }
        return false;
    }

    public final boolean e(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        if (interfaceCount <= 0) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", " ------> UsbInterface is null");
            return false;
        }
        this.f57d = null;
        if (interfaceCount > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                UsbInterface usbInterface = usbDevice.getInterface(i);
                h.c(usbInterface, "usbDevice.getInterface(index)");
                if (usbInterface.getInterfaceClass() == 3) {
                    this.f57d = usbInterface;
                    OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o oooO00o = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a;
                    if ((!oooO00o.t(usbDevice.getProductName()) && !oooO00o.u(usbDevice.getProductName())) || usbInterface.getEndpointCount() == 0) {
                        break;
                    }
                }
                if (i2 >= interfaceCount) {
                    break;
                }
                i = i2;
            }
            return true;
        }
        boolean z = this.f57d != null;
        OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", h.i(" ------> UsbInterface found :: ", Boolean.valueOf(z)));
        return z;
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public void endHid() {
        this.f56c = false;
        b bVar = this.e;
        if (bVar != null) {
            bVar.endHid();
        }
        this.e = null;
    }

    public final String f(byte b2, byte b3, byte b4) {
        byte[] bArrA = a(b2, b3, b4);
        String hexString = Integer.toHexString((((bArrA[1] << 8) & 65280) | (bArrA[0] & 255)) & 65535);
        String strI = h.i("0000", hexString);
        int length = hexString.length();
        int length2 = hexString.length() + 4;
        Objects.requireNonNull(strI, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = strI.substring(length, length2);
        h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final boolean g() {
        return this.f56c;
    }

    public final boolean h(UsbDevice usbDevice) {
        return OooO00o.OooO00o.OooO0O0.OooO0OO.INTANCE.OooO00o().hasPermission(usbDevice);
    }

    public final void i() {
        this.f56c = false;
        b bVar = this.e;
        if (bVar != null) {
            bVar.endHid();
        }
        this.f57d = null;
        this.e = null;
        OooO00o.OooO00o.OooO0O0.b.f41a.e("HidProxy", " ------> hidProxy release");
    }

    public final boolean j(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        if (this.f57d != null || e(usbDevice)) {
            return startHid(usbDevice);
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.d("HidProxy", "start hid, checkInterface false");
        return false;
    }

    @Override // OooO00o.OooO00o.OooO0OO.OooO0O0.b
    public boolean startHid(UsbDevice usbDevice) {
        if (this.e == null) {
            UsbInterface usbInterface = this.f57d;
            h.b(usbInterface);
            this.e = new OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0O0(usbInterface);
        } else if (this.f56c) {
            return true;
        }
        b bVar = this.e;
        boolean zStartHid = bVar == null ? false : bVar.startHid(usbDevice);
        this.f56c = zStartHid;
        return zStartHid;
    }
}
