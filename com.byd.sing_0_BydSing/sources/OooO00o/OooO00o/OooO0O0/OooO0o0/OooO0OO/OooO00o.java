package OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO;

import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o;
import OooO00o.OooO00o.OooO0o0.OooO0o.OooO0OO;
import android.hardware.usb.UsbDevice;
import com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil;
import com.loostone.libtuning.inf.extern.IMicControl;
import kotlin.NotImplementedError;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o implements IMicControl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public UsbDevice f195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f196c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.a f197d = d.b(OooO0O0.f198OooO00o);

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<OooO0o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0O0 f198OooO00o = new OooO0O0();

        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO0o invoke() {
            return OooO0o.f54a.a();
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
        f194a = new byte[]{0, 0};
    }

    public OooO00o(UsbDevice usbDevice) {
        this.f195b = usbDevice;
    }

    public static /* synthetic */ String e(OooO00o oooO00o, byte[] bArr, String str, int i) {
        if ((i & 2) != 0) {
            str = "0000";
        }
        return oooO00o.f(bArr, str);
    }

    public static /* synthetic */ byte[] i(OooO00o oooO00o, byte[] bArr, byte[] bArr2, int i) {
        if ((i & 2) != 0) {
            bArr2 = f194a;
        }
        return oooO00o.k(bArr, bArr2);
    }

    public int a(byte b2, byte b3) {
        return c(new byte[]{-18, b2, b3}, true);
    }

    public final int b(int i) {
        byte[] bArrJ = j(new byte[]{-17, 26, (byte) i});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", "setHardModel:" + i + ", result=" + ((int) bArrJ[0]) + ", 1:success 0:fail");
        return bArrJ[0];
    }

    public final int c(byte[] bArr, boolean z) {
        byte[] bArrJ = j(bArr);
        if (bArrJ == null || bArrJ.length < 2) {
            return 0;
        }
        byte b2 = bArrJ[1];
        return z ? b2 == 1 ? 0 : 1 : b2;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int cancelPair(int i) {
        if (i == 1 || i == 2) {
            return l(new byte[]{-17, (byte) (i == 1 ? 13 : 14), 1}, true);
        }
        return -1;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int confirmPair(int i) {
        if (i == 1 || i == 2) {
            return l(new byte[]{-17, (byte) (i == 1 ? 13 : 14), 0}, true);
        }
        return -1;
    }

    public String d() {
        return getFirmwareVersion((byte) 1);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int enterPair(int i) {
        if (i == 1 || i == 2) {
            return l(new byte[]{-17, (byte) (i == 1 ? 13 : 14), 3}, true);
        }
        return -1;
    }

    public final String f(byte[] bArr, String str) {
        if (this.f195b == null || bArr.length < 3) {
            return str;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.f196c;
        if (jCurrentTimeMillis < j) {
            this.f196c = jCurrentTimeMillis;
            return str;
        }
        if (jCurrentTimeMillis - j < 1000) {
            return str;
        }
        synchronized (OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.n()) {
            if (!n().g()) {
                OooO0o oooO0oN = n();
                UsbDevice usbDeviceR = r();
                h.b(usbDeviceR);
                if (!oooO0oN.j(usbDeviceR)) {
                    g(null);
                    n().endHid();
                    return str;
                }
            }
            if (DongleUpdateUtil.getInstance().isRealUpdating()) {
                return str;
            }
            return n().f(bArr[0], bArr[1], bArr[2]);
        }
    }

    public final void g(UsbDevice usbDevice) {
        this.f195b = usbDevice;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getAudioEffect() {
        switch (c(new byte[]{-20, 2, 0}, false)) {
            case 1:
            default:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getBaseStatus(int i) {
        byte[] bArrJ = j(new byte[]{-20, 0, 0});
        if (bArrJ == null) {
            return 0;
        }
        byte b2 = bArrJ[1];
        byte b3 = bArrJ[2];
        return i != 0 ? i != 1 ? (i == 2 && b3 == 1) ? 1 : 0 : b2 == 1 ? 1 : 0 : (b2 == 1 || b3 == 1) ? 1 : 0;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getDongleSoundEffect() {
        byte[] bArrJ = j(new byte[]{-17, 3, 0});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", h.i("getDongleSoundEffect: ", Byte.valueOf(bArrJ[0])));
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getDongleVolume() {
        byte[] bArrJ = j(new byte[]{-17, 9, 0});
        if (bArrJ == null) {
            return 0;
        }
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getEffectVolume() {
        byte[] bArrJ = j(new byte[]{-17, 5, 0});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", h.i("getEffectVolume: ", Byte.valueOf(bArrJ[0])));
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public byte[] getFactoryMicStatus() {
        return h((byte) 15);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public String getFirmwareVersion(byte b2) {
        return e(this, new byte[]{-15, b2, 0}, null, 2);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public String getHardwareVersion(byte b2) {
        return e(this, new byte[]{-21, b2, 0}, null, 2);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public String getInteractEffect() {
        byte[] bArrJ = j(new byte[]{-17, 12, 0});
        if (bArrJ == null) {
            return null;
        }
        byte b2 = bArrJ[0];
        if (b2 == -95) {
            return "cheer";
        }
        if (b2 == -94) {
            return "applause";
        }
        if (b2 == -93) {
            return "kiss";
        }
        if (b2 == -92) {
            return "sarcasm";
        }
        if (b2 == -91) {
            return "joke";
        }
        if (b2 == -90) {
            return "awkward";
        }
        if (b2 == -89) {
            return "boos";
        }
        if (b2 == -88) {
            return "flower";
        }
        return null;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public double getMicBattery(int i) {
        byte[] bArrJ = j(new byte[]{-16, 2, 0});
        if (bArrJ == null) {
            return 0.0d;
        }
        double d2 = ((double) ((bArrJ[0] & 255) * 2)) / 100.0d;
        double d3 = ((double) ((bArrJ[1] & 255) * 2)) / 100.0d;
        if (i == 1) {
            return d2;
        }
        if (i != 2) {
            return 0.0d;
        }
        return d3;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicBatteryPercentage(int i) {
        int iA = OooO0OO.f477a.a(getMicBattery(i));
        boolean z = false;
        if (1 <= iA && iA <= 100) {
            return iA;
        }
        byte[] bArrJ = j(new byte[]{-16, 12, 0});
        if (bArrJ == null) {
            return -1;
        }
        byte b2 = i != 1 ? i != 2 ? (byte) -1 : bArrJ[1] : bArrJ[0];
        if (b2 >= 0 && b2 <= 100) {
            z = true;
        }
        if (z) {
            return b2;
        }
        return -1;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ1() {
        return c(new byte[]{-20, 32, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ2() {
        return c(new byte[]{-20, 33, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ3() {
        return c(new byte[]{-20, 34, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ4() {
        return c(new byte[]{-20, 35, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ5() {
        return c(new byte[]{-20, 36, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ6() {
        return c(new byte[]{-20, 37, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ7() {
        return c(new byte[]{-20, 38, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ8() {
        return c(new byte[]{-20, 39, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicEQ9() {
        return c(new byte[]{-20, 40, 0}, false);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public String getMicName() {
        byte[] bArrH = h((byte) 0);
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArrH) {
            sb.append((char) b2);
        }
        String string = sb.toString();
        h.c(string, "micName.toString()");
        return string;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicShiftFrequency() {
        byte[] bArrJ = j(new byte[]{-17, 19, 0});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", h.i("getMicShiftFrequency: ", Byte.valueOf(bArrJ[0])));
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getMicVolumeHW(int i) {
        byte[] bArrJ = j(new byte[]{-16, 3, 0});
        if (bArrJ == null) {
            return 0;
        }
        byte b2 = bArrJ[1];
        byte b3 = bArrJ[2];
        if (i == 1) {
            return b2;
        }
        if (i != 2) {
            return 0;
        }
        return b3;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public boolean getPremiumSpeakerState() {
        byte[] bArrJ = j(new byte[]{-17, 33, 0});
        return bArrJ != null && bArrJ[0] == 1;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int getSystemVolume() {
        byte[] bArrJ = j(new byte[]{-17, 27, 0});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", h.i("getSystemVolume: ", Byte.valueOf(bArrJ[0])));
        return bArrJ[0];
    }

    public byte[] h(byte b2) {
        return i(this, new byte[]{-16, b2, 0}, null, 2);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public boolean isHardEffect() {
        byte[] bArrJ = j(new byte[]{-17, 34, 0});
        return bArrJ != null && bArrJ[0] == 1 && bArrJ[1] == 0;
    }

    public final byte[] j(byte[] bArr) {
        if (this.f195b == null || bArr.length < 3) {
            return null;
        }
        synchronized (OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.n()) {
            if (!n().g()) {
                OooO0o oooO0oN = n();
                UsbDevice usbDeviceR = r();
                h.b(usbDeviceR);
                if (!oooO0oN.j(usbDeviceR)) {
                    g(null);
                    n().endHid();
                    return null;
                }
            }
            if (DongleUpdateUtil.getInstance().isRealUpdating()) {
                return null;
            }
            return n().a(bArr[0], bArr[1], bArr[2]);
        }
    }

    public final byte[] k(byte[] bArr, byte[] bArr2) {
        if (this.f195b == null || bArr.length < 3) {
            return bArr2;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.f196c;
        if (jCurrentTimeMillis < j) {
            this.f196c = jCurrentTimeMillis;
            return bArr2;
        }
        if (jCurrentTimeMillis - j < 1000) {
            return bArr2;
        }
        synchronized (OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.n()) {
            if (!n().g()) {
                OooO0o oooO0oN = n();
                UsbDevice usbDeviceR = r();
                h.b(usbDeviceR);
                if (!oooO0oN.j(usbDeviceR)) {
                    g(null);
                    n().endHid();
                    return bArr2;
                }
            }
            if (DongleUpdateUtil.getInstance().isRealUpdating()) {
                return bArr2;
            }
            return n().a(bArr[0], bArr[1], bArr[2]);
        }
    }

    public final int l(byte[] bArr, boolean z) {
        byte[] bArrJ;
        if (this.f195b == null || (bArrJ = j(bArr)) == null || bArrJ.length < 2) {
            return -1;
        }
        byte b2 = bArrJ[1];
        return z ? b2 == 1 ? -1 : 0 : b2;
    }

    public String m() {
        return getHardwareVersion((byte) 5);
    }

    public final OooO0o n() {
        return (OooO0o) this.f197d.getValue();
    }

    public byte[] o() {
        return i(this, new byte[]{-9, 0, 0}, null, 2);
    }

    public String p() {
        return h.i(e(this, new byte[]{-3, 0, 0}, null, 2), e(this, new byte[]{-4, 0, 0}, null, 2));
    }

    public byte[] q() {
        return i(this, new byte[]{-5, 0, 0}, null, 2);
    }

    public final UsbDevice r() {
        return this.f195b;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int rebootDongle() {
        return l(new byte[]{-17, 16, 0}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int rejectPair(int i) {
        if (i == 1 || i == 2) {
            return l(new byte[]{-17, (byte) (i == 1 ? 13 : 14), 2}, true);
        }
        return -1;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int resetDongle() {
        return l(new byte[]{-17, 35, 0}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int resetPair(int i) {
        if (i == 1 || i == 2) {
            return l(new byte[]{-17, (byte) (i == 1 ? 13 : 14), 4}, true);
        }
        return -1;
    }

    public String s() {
        return e(this, new byte[]{-2, 0, 0}, null, 2);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setAudioEffect(int i) {
        byte b2;
        switch (i) {
            case 1:
            default:
                b2 = 1;
                break;
            case 2:
                b2 = 2;
                break;
            case 3:
                b2 = 3;
                break;
            case 4:
                b2 = 4;
                break;
            case 5:
                b2 = 5;
                break;
            case 6:
                b2 = 6;
                break;
            case 7:
                b2 = 7;
                break;
            case 8:
                b2 = 8;
                break;
        }
        return c(new byte[]{-20, 1, b2}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setDongleSoundEffect(int i) {
        byte[] bArrJ = j(new byte[]{-17, 4, (byte) i});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", "setDongleSoundEffect:" + i + ", result=" + ((int) bArrJ[0]) + ", 0:success 1:fail");
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setDongleVolume(int i) {
        byte[] bArrJ = j(new byte[]{-17, 10, (byte) i});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", "setDongleVolume:" + i + ", result=" + ((int) bArrJ[0]) + ", 0:success 1:fail");
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setEffectVolume(int i) {
        byte[] bArrJ = j(new byte[]{-17, 6, (byte) i});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", "setEffectVolume:" + i + ", result=" + ((int) bArrJ[0]) + ", 0:success 1:fail");
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ1(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 16, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ2(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 17, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ3(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 18, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ4(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 19, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ5(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 20, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ6(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 21, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ7(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 22, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ8(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 23, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicEQ9(int i) {
        if (i < -12 || i > 12) {
            return 0;
        }
        return c(new byte[]{-20, 24, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public void setMicPower() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicShiftFrequency(int i) {
        byte[] bArrJ = j(new byte[]{-17, 20, (byte) i});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", "setMicShiftFrequency:" + i + ", result=" + ((int) bArrJ[0]) + ", 0:success 1:fail");
        return bArrJ[0];
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setMicSwitch(int i) {
        return c(new byte[]{-20, 48, (byte) i}, true);
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setRejectPairTime(int i) {
        if (i >= 0 && i <= 255) {
            return l(new byte[]{-17, 15, (byte) i}, true);
        }
        return -1;
    }

    @Override // com.loostone.libtuning.inf.extern.IMicControl
    public int setSystemVolume(int i) {
        byte[] bArrJ = j(new byte[]{-17, 28, (byte) i});
        if (bArrJ == null) {
            return 0;
        }
        b.f41a.b("MicInfo", "setSystemVolume:" + i + ", result=" + ((int) bArrJ[0]) + ", 0:success 1:fail");
        return bArrJ[0];
    }
}
