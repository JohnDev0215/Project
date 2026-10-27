package com.loostone.libtuning.channel.byd.upgrade;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0O0.b;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import com.blankj.utilcode.util.h;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class UpdateHelperMic implements IUpdateFirmware<UpdateHelperMic> {
    private static final String TAG = "UpdateHelperMic";
    private IUpdateFirmware.Callback callback;
    private byte[] fileBuffer;
    private int fileSize;
    private HidUpdateMic hidUpdateMic;
    private int perLen;
    private boolean started;
    private boolean updating;

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public void cancelUpdate() {
        this.updating = false;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public boolean init(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return false;
        }
        UsbInterface usbInterfaceC = OooO00o.f18a.c(usbDevice);
        if (usbInterfaceC == null) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(11);
            }
            return false;
        }
        HidUpdateMic hidUpdateMic = new HidUpdateMic(usbInterfaceC);
        this.hidUpdateMic = hidUpdateMic;
        if (hidUpdateMic.startHid(usbDevice)) {
            this.started = true;
        }
        return true;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public boolean queryCheckResult() {
        if (!this.started) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(-1);
            }
            return false;
        }
        this.hidUpdateMic.step27();
        byte[] bArrStep28 = this.hidUpdateMic.step28();
        b.f41a.f(TAG, "run: 28 " + Arrays.toString(bArrStep28));
        if (!HidUpdateMic.crcCheck(bArrStep28)) {
            return bArrStep28[3] == 1;
        }
        IUpdateFirmware.Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.error(3);
        }
        return false;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public int queryDeviceStatus() {
        if (!this.started) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(-1);
            }
            return 0;
        }
        this.hidUpdateMic.step13();
        byte[] bArrStep14 = this.hidUpdateMic.step14();
        b.f41a.b(TAG, "run: 14 " + Arrays.toString(bArrStep14));
        if (!HidUpdateMic.crcCheck(bArrStep14)) {
            return bArrStep14[3];
        }
        IUpdateFirmware.Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.error(3);
        }
        return 0;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public ArrayList<byte[]> queryDeviceVersionCode() {
        if (!this.started) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(-1);
            }
            return null;
        }
        this.hidUpdateMic.step21();
        byte[] bArrStep22 = this.hidUpdateMic.step22();
        b.f41a.b(TAG, "run: 22 " + Arrays.toString(bArrStep22));
        if (HidUpdateMic.crcCheck(bArrStep22)) {
            IUpdateFirmware.Callback callback2 = this.callback;
            if (callback2 != null) {
                callback2.error(3);
            }
            return null;
        }
        byte[] bArr = {bArrStep22[3], bArrStep22[4], bArrStep22[5], bArrStep22[6]};
        byte[] bArr2 = {bArrStep22[7], bArrStep22[8], bArrStep22[9], bArrStep22[10]};
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(bArr);
        arrayList.add(bArr2);
        return arrayList;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public long queryHidVersionCode() {
        if (!this.started) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(-1);
            }
            return 0L;
        }
        this.hidUpdateMic.step11();
        byte[] bArrStep12 = this.hidUpdateMic.step12();
        b.f41a.b(TAG, "run: 12 " + Arrays.toString(bArrStep12));
        if (!HidUpdateMic.crcCheck(bArrStep12)) {
            return 0 | ((long) (bArrStep12[3] << 16)) | ((long) (bArrStep12[4] << 8)) | ((long) bArrStep12[5]);
        }
        IUpdateFirmware.Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.error(3);
        }
        return 0L;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public boolean queryUpdateResult() {
        if (!this.started) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(-1);
            }
            return false;
        }
        this.hidUpdateMic.step2B();
        byte[] bArrStep2C = this.hidUpdateMic.step2C();
        b.f41a.f(TAG, "run: 2C " + Arrays.toString(bArrStep2C));
        if (!HidUpdateMic.crcCheck(bArrStep2C)) {
            return bArrStep2C[3] == 1;
        }
        IUpdateFirmware.Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.error(3);
        }
        return false;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public int release() {
        if (this.started) {
            this.started = false;
            this.hidUpdateMic.endHid();
            this.hidUpdateMic = null;
        }
        this.callback = null;
        this.fileSize = 0;
        this.fileBuffer = null;
        this.perLen = 0;
        this.updating = false;
        return 0;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public boolean requestUpdate(long j, String str) {
        byte[] bArrB = h.b(str);
        this.fileBuffer = bArrB;
        if (bArrB == null) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(21);
            }
            return false;
        }
        this.fileSize = bArrB.length;
        b.f41a.b(TAG, "file size: " + this.fileSize);
        int i = this.fileSize;
        if (i > 10485760) {
            IUpdateFirmware.Callback callback2 = this.callback;
            if (callback2 != null) {
                callback2.error(22);
            }
            return false;
        }
        if (!this.started) {
            IUpdateFirmware.Callback callback3 = this.callback;
            if (callback3 != null) {
                callback3.error(-1);
            }
            return false;
        }
        this.hidUpdateMic.step23(j, i);
        byte[] bArrStep24 = this.hidUpdateMic.step24();
        int i2 = 0;
        while (bArrStep24 == null) {
            bArrStep24 = this.hidUpdateMic.step24();
            i2++;
            if (i2 > 3) {
                IUpdateFirmware.Callback callback4 = this.callback;
                if (callback4 != null) {
                    callback4.error(1);
                }
                return false;
            }
        }
        b.f41a.b(TAG, "run: 24 " + Arrays.toString(bArrStep24));
        if (HidUpdateMic.crcCheck(bArrStep24)) {
            IUpdateFirmware.Callback callback5 = this.callback;
            if (callback5 != null) {
                callback5.error(3);
            }
            return false;
        }
        if (bArrStep24.length == 11) {
            this.perLen = ((bArrStep24[8] & 255) << 8) | (bArrStep24[9] & 255);
        } else {
            this.perLen = bArrStep24[8] & 255;
        }
        return bArrStep24[3] == 1;
    }

    public void setHeader(byte b2) {
        HidUpdateMic hidUpdateMic = this.hidUpdateMic;
        if (hidUpdateMic != null) {
            hidUpdateMic.setHeader(b2);
        }
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public boolean startUpdate() {
        if (!this.started) {
            IUpdateFirmware.Callback callback = this.callback;
            if (callback != null) {
                callback.error(-1);
            }
            return false;
        }
        this.hidUpdateMic.step29();
        byte[] bArrStep2A = this.hidUpdateMic.step2A();
        b.f41a.f(TAG, "run: 2A " + Arrays.toString(bArrStep2A));
        if (!HidUpdateMic.crcCheck(bArrStep2A)) {
            return bArrStep2A[3] == 1;
        }
        IUpdateFirmware.Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.error(3);
        }
        return false;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public boolean updateProcess() {
        int i;
        byte b2;
        IUpdateFirmware.Callback callback;
        int i2;
        if (this.fileBuffer == null || (i = this.perLen) <= 0) {
            IUpdateFirmware.Callback callback2 = this.callback;
            if (callback2 != null) {
                callback2.error(0);
            }
            return false;
        }
        if (this.started) {
            this.updating = true;
            int i3 = this.fileSize;
            byte[] bArr = new byte[i];
            int i4 = 0;
            int i5 = 0;
            long j = 1;
            while (i3 > 0 && this.updating) {
                int iMin = Math.min(this.fileSize - i4, this.perLen);
                int i6 = 0;
                do {
                    System.arraycopy(this.fileBuffer, i4, bArr, 0, iMin);
                    boolean zStep2F = this.hidUpdateMic.step2F(j, iMin, bArr);
                    b bVar = b.f41a;
                    bVar.f(TAG, "run: 2F " + zStep2F);
                    byte[] bArrStep26 = this.hidUpdateMic.step26();
                    bVar.f(TAG, "run: 26 " + Arrays.toString(bArrStep26));
                    if (85 == bArrStep26[0] || 84 == bArrStep26[0]) {
                        if (HidUpdate.crcCheck(bArrStep26) && (callback = this.callback) != null) {
                            callback.error(3);
                        }
                        b2 = bArrStep26[11];
                    } else {
                        b2 = 0;
                    }
                    i6++;
                    if (i6 > 3) {
                        IUpdateFirmware.Callback callback3 = this.callback;
                        if (callback3 != null) {
                            callback3.error(4);
                        }
                        return false;
                    }
                } while (b2 != 1);
                i4 += iMin;
                j++;
                i3 -= iMin;
                IUpdateFirmware.Callback callback4 = this.callback;
                if (callback4 != null && (i2 = (i4 * 100) / this.fileSize) != i5) {
                    callback4.process(i2);
                    i5 = i2;
                }
            }
            IUpdateFirmware.Callback callback5 = this.callback;
            if (callback5 != null) {
                if (this.updating) {
                    callback5.finish();
                } else {
                    callback5.error(5);
                }
            }
        } else {
            IUpdateFirmware.Callback callback6 = this.callback;
            if (callback6 != null) {
                callback6.error(-1);
            }
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware
    public UpdateHelperMic setCallback(IUpdateFirmware.Callback callback) {
        this.callback = callback;
        return this;
    }
}
