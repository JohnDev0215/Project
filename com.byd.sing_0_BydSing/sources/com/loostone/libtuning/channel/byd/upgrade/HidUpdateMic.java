package com.loostone.libtuning.channel.byd.upgrade;

import android.hardware.usb.UsbInterface;

/* JADX INFO: loaded from: classes.dex */
public class HidUpdateMic extends HidBaseSubject {
    private static final String TAG = "HidUpdateMic";
    private boolean bTransfer2Byte;
    private byte mHeader;

    public HidUpdateMic(UsbInterface usbInterface) {
        super(usbInterface);
    }

    private static byte crc(byte[] bArr) {
        return crc(bArr, bArr.length);
    }

    public static boolean crcCheck(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return true;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return ((byte) i) != bArr[bArr.length - 1];
    }

    public void setHeader(byte b2) {
        this.mHeader = b2;
        this.bTransfer2Byte = 84 == b2;
    }

    public void step11() {
        byte[] bArr = {this.mHeader, 17, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step12() {
        byte[] bArr = new byte[7];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step13() {
        byte[] bArr = {this.mHeader, 19, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step14() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step21() {
        byte[] bArr = {this.mHeader, 33, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step22() {
        byte[] bArr = new byte[12];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step23(long j, long j2) {
        int i;
        int i2;
        if (this.bTransfer2Byte) {
            i = 20;
            i2 = 2048;
        } else {
            i = 19;
            i2 = 240;
        }
        byte[] bArr = new byte[i];
        bArr[0] = this.mHeader;
        bArr[1] = 35;
        bArr[2] = (byte) (i - 4);
        bArr[3] = 0;
        long jRound = Math.round(j2 / ((double) i2));
        bArr[4] = (byte) ((j & (-16777216)) >> 24);
        bArr[5] = (byte) ((j & 16711680) >> 16);
        bArr[6] = (byte) ((j & 65280) >> 8);
        bArr[7] = (byte) (j & 255);
        bArr[8] = (byte) ((j2 & (-16777216)) >> 24);
        bArr[9] = (byte) ((j2 & 16711680) >> 16);
        bArr[10] = (byte) ((j2 & 65280) >> 8);
        bArr[11] = (byte) (j2 & 255);
        bArr[12] = (byte) ((jRound & (-16777216)) >> 24);
        bArr[13] = (byte) ((16711680 & jRound) >> 16);
        bArr[14] = (byte) ((65280 & jRound) >> 8);
        bArr[15] = (byte) (jRound & 255);
        if (this.bTransfer2Byte) {
            bArr[17] = (byte) ((65280 & i2) >> 8);
            bArr[18] = (byte) (i2 & 255);
        } else {
            bArr[17] = (byte) i2;
        }
        bArr[i - 1] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step24() {
        byte[] bArr = new byte[this.bTransfer2Byte ? 11 : 10];
        if (recvMessage(this.connection, this.usbInterfaceId, bArr)) {
            return bArr;
        }
        return null;
    }

    public byte[] step26() {
        byte[] bArr = new byte[13];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step27() {
        byte[] bArr = {this.mHeader, 39, 1, 1, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step28() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step29() {
        byte[] bArr = {this.mHeader, 41, 1, 1, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step2A() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step2B() {
        byte[] bArr = {this.mHeader, 43, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step2C() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public boolean step2F(long j, int i, byte[] bArr) {
        int i2 = i + 8;
        boolean z = this.bTransfer2Byte;
        if (z) {
            i2 = i + 9;
        }
        byte[] bArr2 = new byte[i2];
        bArr2[0] = this.mHeader;
        bArr2[1] = 47;
        int i3 = 2;
        if (z) {
            int i4 = i + 4;
            bArr2[2] = (byte) ((65280 & i4) >> 8);
            i3 = 3;
            bArr2[3] = (byte) (i4 & 255);
        } else {
            bArr2[2] = (byte) (i + 4);
        }
        int i5 = i3 + 1;
        bArr2[i5] = (byte) (((-16777216) & j) >> 24);
        int i6 = i5 + 1;
        bArr2[i6] = (byte) ((16711680 & j) >> 16);
        int i7 = i6 + 1;
        bArr2[i7] = (byte) ((65280 & j) >> 8);
        int i8 = i7 + 1;
        bArr2[i8] = (byte) (j & 255);
        System.arraycopy(bArr, 0, bArr2, i8 + 1, i);
        bArr2[i2 - 1] = crc(bArr2);
        return sendCommand(this.connection, this.usbInterfaceId, bArr2);
    }

    private static byte crc(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i - 1; i3++) {
            i2 += bArr[i3];
        }
        return (byte) i2;
    }
}
