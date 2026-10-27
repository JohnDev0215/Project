package com.loostone.libtuning.channel.byd.upgrade;

import OooO00o.OooO00o.OooO0O0.OooO0OO;
import OooO00o.OooO00o.OooO0O0.b;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import com.loostone.libtuning.channel.byd.upgrade.inf.IHidSubject;

/* JADX INFO: loaded from: classes.dex */
public abstract class HidBaseSubject implements IHidSubject {
    private final String TAG = "HidBaseSubject";
    public UsbDeviceConnection connection;
    public UsbInterface usbInterface;
    public int usbInterfaceId;

    public HidBaseSubject(UsbInterface usbInterface) {
        this.usbInterface = usbInterface;
        this.usbInterfaceId = usbInterface.getId();
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IHidSubject
    public void endHid() {
        synchronized (HidBaseSubject.class) {
            UsbDeviceConnection usbDeviceConnection = this.connection;
            if (usbDeviceConnection != null) {
                try {
                    usbDeviceConnection.releaseInterface(this.usbInterface);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    this.connection.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.connection = null;
            } else {
                this.connection = null;
            }
        }
    }

    public boolean recvMessage(UsbDeviceConnection usbDeviceConnection, int i, byte[] bArr) {
        if (usbDeviceConnection == null) {
            return false;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        if (usbDeviceConnection.controlTransfer(193, 1, 256, i, bArr2, length, 1000) < bArr.length) {
            b.f41a.d("HidBaseSubject", "recv command fail");
            return false;
        }
        System.arraycopy(bArr2, 0, bArr, 0, bArr.length);
        return true;
    }

    public boolean sendCommand(UsbDeviceConnection usbDeviceConnection, int i, byte[] bArr) {
        if (usbDeviceConnection == null) {
            return false;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        if (usbDeviceConnection.controlTransfer(65, 9, 512, i, bArr2, length, 1000) < bArr.length) {
            b.f41a.d("HidBaseSubject", "send command fail");
            return false;
        }
        System.arraycopy(bArr2, 0, bArr, 0, bArr.length);
        return true;
    }

    @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IHidSubject
    public boolean startHid(UsbDevice usbDevice) {
        synchronized (HidBaseSubject.class) {
            if (this.connection != null) {
                return true;
            }
            try {
                UsbDeviceConnection usbDeviceConnectionOpenDevice = OooO0OO.INTANCE.OooO00o().openDevice(usbDevice);
                this.connection = usbDeviceConnectionOpenDevice;
                if (usbDeviceConnectionOpenDevice != null) {
                    if (usbDeviceConnectionOpenDevice.claimInterface(this.usbInterface, true)) {
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
}
