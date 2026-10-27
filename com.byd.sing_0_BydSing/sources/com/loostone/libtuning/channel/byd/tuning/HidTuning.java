package com.loostone.libtuning.channel.byd.tuning;

import android.hardware.usb.UsbInterface;
import com.loostone.libtuning.channel.byd.upgrade.HidBaseSubject;

/* JADX INFO: loaded from: classes.dex */
public class HidTuning extends HidBaseSubject {
    public HidTuning(UsbInterface usbInterface) {
        super(usbInterface);
    }

    public void step03() {
        sendCommand(this.connection, this.usbInterfaceId, new byte[]{3});
    }

    public byte[] step04() {
        byte[] bArr = new byte[7];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step13() {
        sendCommand(this.connection, this.usbInterfaceId, new byte[]{19});
    }

    public byte[] step14() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step15(int i, byte[] bArr) {
        byte[] bArr2 = new byte[261];
        bArr2[0] = 21;
        bArr2[1] = (byte) ((65280 & i) >> 8);
        bArr2[2] = (byte) (i & 255);
        bArr2[3] = 1;
        bArr2[4] = 0;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        sendCommand(this.connection, this.usbInterfaceId, bArr2);
    }

    public byte[] step16() {
        byte[] bArr = new byte[2];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step17(int i, int i2) {
        sendCommand(this.connection, this.usbInterfaceId, new byte[]{23, (byte) ((i & 65280) >> 8), (byte) (i & 255), (byte) ((i2 & 65280) >> 8), (byte) (i2 & 255)});
    }

    public byte[] step18(int i) {
        byte[] bArr = new byte[i + 5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step1B() {
        sendCommand(this.connection, this.usbInterfaceId, new byte[]{27});
    }

    public byte[] step1C() {
        byte[] bArr = new byte[35];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step1D() {
        sendCommand(this.connection, this.usbInterfaceId, new byte[]{29});
    }

    public byte[] step1E() {
        byte[] bArr = new byte[2];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step31() {
        sendCommand(this.connection, this.usbInterfaceId, new byte[]{49});
    }

    public byte[] step32() {
        byte[] bArr = new byte[2];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }
}
