package com.loostone.libtuning.channel.byd.tuning;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0O0.b;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import com.android.volley.BuildConfig;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TuningHelper {
    private static final String TAG = "TuningHelper";
    private static volatile TuningHelper mInstance;
    private HidTuning hidTuning;
    private boolean started;

    private TuningHelper() {
    }

    public static TuningHelper getInstance() {
        if (mInstance == null) {
            synchronized (TuningHelper.class) {
                if (mInstance == null) {
                    mInstance = new TuningHelper();
                }
            }
        }
        return mInstance;
    }

    public boolean clearConfigInfo() {
        if (!this.started) {
            return false;
        }
        this.hidTuning.step31();
        byte[] bArrStep32 = this.hidTuning.step32();
        b.f41a.e(TAG, BuildConfig.FLAVOR + Arrays.toString(bArrStep32));
        return bArrStep32[1] == 1;
    }

    public String getConfig() {
        int configSize;
        if (!this.started || (configSize = getConfigSize()) <= 0) {
            return null;
        }
        int maxSize = getMaxSize();
        int iCeil = (int) Math.ceil((((double) configSize) * 1.0d) / ((double) maxSize));
        int i = 0;
        b.f41a.e(TAG, "count:" + iCeil + ", configSize:" + configSize);
        byte[] bArr = new byte[configSize];
        while (true) {
            this.hidTuning.step17(i, maxSize);
            byte[] bArrStep18 = this.hidTuning.step18(maxSize);
            if (i < iCeil - 1) {
                b.f41a.e(TAG, "index:" + i);
                System.arraycopy(bArrStep18, 5, bArr, i * maxSize, Math.abs(bArrStep18.length - 5));
            } else {
                b.f41a.e(TAG, "index:" + i);
                int i2 = i * maxSize;
                System.arraycopy(bArrStep18, 5, bArr, i2, Math.abs(i2 - configSize));
            }
            b bVar = b.f41a;
            StringBuilder sb = new StringBuilder();
            sb.append("index:");
            int i3 = i + 1;
            sb.append(i);
            sb.append(", getConfig:");
            sb.append(Arrays.toString(bArrStep18));
            bVar.e(TAG, sb.toString());
            if (i3 >= iCeil) {
                return new String(bArr);
            }
            i = i3;
        }
    }

    public byte[] getConfigMd5() {
        byte[] bArr = new byte[32];
        if (this.started) {
            this.hidTuning.step1B();
            System.arraycopy(this.hidTuning.step1C(), 3, bArr, 0, 32);
        }
        return bArr;
    }

    public int getConfigSize() {
        if (!this.started) {
            return 0;
        }
        this.hidTuning.step13();
        byte[] bArrStep14 = this.hidTuning.step14();
        b bVar = b.f41a;
        bVar.e(TAG, "getConfigSize:" + Arrays.toString(bArrStep14));
        int i = (bArrStep14[4] & 255) | ((bArrStep14[3] << 8) & 65280);
        bVar.d(TAG, "ret:" + i);
        return i;
    }

    public int getMaxSize() {
        int i = 0;
        if (this.started) {
            this.hidTuning.step03();
            byte[] bArrStep04 = this.hidTuning.step04();
            b.f41a.e(TAG, "getMaxSize:" + Arrays.toString(bArrStep04));
            byte b2 = bArrStep04[2];
            int i2 = 3;
            while (true) {
                if (i2 >= b2 + 3) {
                    break;
                }
                i |= (bArrStep04[i2] << (((r6 - i2) - 1) * 8)) & 255;
                i2++;
            }
            b.f41a.d(TAG, "ret:" + i);
        }
        return i;
    }

    public boolean init(UsbDevice usbDevice) {
        UsbInterface usbInterfaceC;
        b.f41a.e(TAG, "init");
        if (this.started) {
            return true;
        }
        if (usbDevice == null || (usbInterfaceC = OooO00o.f18a.c(usbDevice)) == null) {
            return false;
        }
        HidTuning hidTuning = new HidTuning(usbInterfaceC);
        this.hidTuning = hidTuning;
        hidTuning.startHid(usbDevice);
        this.started = true;
        return true;
    }

    public boolean isConfigChanged() {
        if (!this.started) {
            return false;
        }
        this.hidTuning.step1D();
        byte[] bArrStep1E = this.hidTuning.step1E();
        return bArrStep1E[0] == 30 && bArrStep1E[1] == 1;
    }

    public boolean isStarted() {
        return this.started;
    }

    public int release() {
        if (this.started) {
            this.started = false;
            this.hidTuning.endHid();
            this.hidTuning = null;
        }
        return 0;
    }

    public void setConfig(String str) {
        if (!this.started) {
            return;
        }
        byte[] bytes = str.getBytes();
        int iCeil = (int) Math.ceil((((double) bytes.length) * 1.0d) / 256.0d);
        b.f41a.e(TAG, "count:" + iCeil + ", buffer.length:" + bytes.length);
        int i = 0;
        while (true) {
            byte[] bArr = new byte[256];
            int i2 = i * 256;
            if (i2 + 256 < bytes.length) {
                System.arraycopy(bytes, i2, bArr, 0, 256);
            } else {
                System.arraycopy(bytes, i2, bArr, 0, bytes.length - i2);
            }
            this.hidTuning.step15(i, bArr);
            byte[] bArrStep16 = this.hidTuning.step16();
            b bVar = b.f41a;
            StringBuilder sb = new StringBuilder();
            sb.append("index:");
            int i3 = i + 1;
            sb.append(i);
            sb.append(", setConfig:");
            sb.append(Arrays.toString(bArrStep16));
            bVar.e(TAG, sb.toString());
            if (i3 >= iCeil) {
                return;
            } else {
                i = i3;
            }
        }
    }
}
