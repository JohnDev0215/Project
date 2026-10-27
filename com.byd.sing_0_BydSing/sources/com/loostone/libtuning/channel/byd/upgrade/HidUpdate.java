package com.loostone.libtuning.channel.byd.upgrade;

import OooO00o.OooO00o.OooO0O0.b;
import android.hardware.usb.UsbInterface;
import com.loostone.libtuning.channel.byd.upgrade.util.InfoConstant;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class HidUpdate extends HidBaseSubject {
    private static final String TAG = "HidUpdate";

    public HidUpdate(UsbInterface usbInterface) {
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

    public byte[] recvDeviceInfo(int i) {
        byte[] bArr = new byte[i];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public byte[] recvDeviceInfoAll() {
        byte[] bArr = new byte[128];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void sendDeviceInfo(int i, int i2) {
        int i3 = i2 + 6;
        byte[] bArr = new byte[i3];
        bArr[0] = 87;
        bArr[1] = 1;
        bArr[2] = (byte) (i2 + 2);
        bArr[3] = (byte) i;
        bArr[4] = (byte) i2;
        for (int i4 = 5; i4 < i2 + 5; i4++) {
            bArr[i4] = 0;
        }
        bArr[i3 - 1] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public void sendDeviceInfoAll() {
        byte[] bArr = {87, 1, 0, 0};
        bArr[3] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] sendHardEffect(byte[] bArr) {
        if (!sendCommand(this.connection, this.usbInterfaceId, bArr)) {
            b.f41a.b(TAG, "sendHardEffect updateSend failed");
            return null;
        }
        byte[] bArr2 = new byte[5];
        if (recvMessage(this.connection, this.usbInterfaceId, bArr2)) {
            b.f41a.b(TAG, "sendHardEffect result:" + Arrays.toString(bArr2));
        } else {
            b.f41a.b(TAG, "sendHardEffect updateRecv failed");
        }
        return bArr2;
    }

    public void step11() {
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 17, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step12() {
        byte[] bArr = new byte[7];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step13() {
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 19, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step14() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step21() {
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 33, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step22() {
        byte[] bArr = new byte[12];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step23(long j, long j2) {
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 35, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        long jRound = Math.round(j2 / 240.0d);
        bArr[4] = (byte) ((j & (-16777216)) >> 24);
        bArr[5] = (byte) ((j & 16711680) >> 16);
        bArr[6] = (byte) ((j & 65280) >> 8);
        bArr[7] = (byte) (j & 255);
        bArr[8] = (byte) ((j2 & (-16777216)) >> 24);
        bArr[9] = (byte) ((j2 & 16711680) >> 16);
        bArr[10] = (byte) ((j2 & 65280) >> 8);
        bArr[11] = (byte) (j2 & 255);
        bArr[12] = (byte) ((jRound & (-16777216)) >> 24);
        bArr[13] = (byte) ((jRound & 16711680) >> 16);
        bArr[14] = (byte) ((65280 & jRound) >> 8);
        bArr[15] = (byte) (jRound & 255);
        bArr[17] = -16;
        bArr[18] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step24() {
        byte[] bArr = new byte[10];
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
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 39, 1, 1, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step28() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step29() {
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 41, 1, 1, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step2A() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public void step2B() {
        byte[] bArr = {InfoConstant.MicUpdateType.UPDATE_BIN, 43, 1, 0, 0};
        bArr[4] = crc(bArr);
        sendCommand(this.connection, this.usbInterfaceId, bArr);
    }

    public byte[] step2C() {
        byte[] bArr = new byte[5];
        recvMessage(this.connection, this.usbInterfaceId, bArr);
        return bArr;
    }

    public boolean step2F(long j, int i, byte[] bArr) {
        byte[] bArr2 = new byte[i + 8];
        bArr2[0] = InfoConstant.MicUpdateType.UPDATE_BIN;
        bArr2[1] = 47;
        bArr2[2] = (byte) (i + 4);
        bArr2[3] = (byte) (((-16777216) & j) >> 24);
        bArr2[4] = (byte) ((16711680 & j) >> 16);
        bArr2[5] = (byte) ((65280 & j) >> 8);
        bArr2[6] = (byte) (j & 255);
        System.arraycopy(bArr, 0, bArr2, 7, i);
        bArr2[i + 7] = crc(bArr2);
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
