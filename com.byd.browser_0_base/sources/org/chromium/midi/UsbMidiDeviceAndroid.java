package org.chromium.midi;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Handler;
import android.util.SparseArray;
import defpackage.gk3;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class UsbMidiDeviceAndroid {
    public final UsbDeviceConnection a;
    public boolean f;
    public final UsbDevice h;
    public final SparseArray<UsbEndpoint> b = new SparseArray<>();
    public final HashMap c = new HashMap();
    public final Handler d = new Handler();
    public boolean e = false;
    public long g = 0;

    public UsbMidiDeviceAndroid(UsbManager usbManager, UsbDevice usbDevice) {
        this.a = usbManager.openDevice(usbDevice);
        this.h = usbDevice;
        this.f = false;
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                this.a.claimInterface(usbInterface, true);
                for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                    UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                    if (endpoint.getDirection() == 0) {
                        this.b.put(endpoint.getEndpointNumber(), endpoint);
                    }
                }
            }
        }
        HashMap map = new HashMap();
        for (int i3 = 0; i3 < usbDevice.getInterfaceCount(); i3++) {
            UsbInterface usbInterface2 = usbDevice.getInterface(i3);
            if (usbInterface2.getInterfaceClass() == 1 && usbInterface2.getInterfaceSubclass() == 3) {
                for (int i4 = 0; i4 < usbInterface2.getEndpointCount(); i4++) {
                    UsbEndpoint endpoint2 = usbInterface2.getEndpoint(i4);
                    if (endpoint2.getDirection() == 128) {
                        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(endpoint2.getMaxPacketSize());
                        UsbRequest usbRequest = new UsbRequest();
                        usbRequest.initialize(this.a, endpoint2);
                        usbRequest.queue(byteBufferAllocate, byteBufferAllocate.remaining());
                        map.put(endpoint2, byteBufferAllocate);
                    }
                }
            }
        }
        if (map.isEmpty()) {
            return;
        }
        this.f = true;
        new gk3(this, map).start();
    }

    @CalledByNative
    public void close() {
        this.b.clear();
        HashMap map = this.c;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((UsbRequest) it.next()).close();
        }
        map.clear();
        this.a.close();
        this.g = 0L;
        this.e = true;
    }

    @CalledByNative
    public byte[] getDescriptors() {
        UsbDeviceConnection usbDeviceConnection = this.a;
        return usbDeviceConnection == null ? new byte[0] : usbDeviceConnection.getRawDescriptors();
    }

    @CalledByNative
    public byte[] getStringDescriptor(int i) {
        UsbDeviceConnection usbDeviceConnection = this.a;
        if (usbDeviceConnection == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[255];
        int iControlTransfer = usbDeviceConnection.controlTransfer(128, 6, i | 768, 0, bArr, 255, 0);
        return iControlTransfer < 0 ? new byte[0] : Arrays.copyOf(bArr, iControlTransfer);
    }

    @CalledByNative
    public void registerSelf(long j) {
        this.g = j;
    }

    @CalledByNative
    public void send(int i, byte[] bArr) {
        UsbEndpoint usbEndpoint;
        if (this.e || (usbEndpoint = this.b.get(i)) == null) {
            return;
        }
        boolean z = this.f;
        UsbDeviceConnection usbDeviceConnection = this.a;
        if (z) {
            usbDeviceConnection.bulkTransfer(usbEndpoint, bArr, bArr.length, 100);
            return;
        }
        HashMap map = this.c;
        UsbRequest usbRequest = (UsbRequest) map.get(usbEndpoint);
        if (usbRequest == null) {
            usbRequest = new UsbRequest();
            usbRequest.initialize(usbDeviceConnection, usbEndpoint);
            map.put(usbEndpoint, usbRequest);
        }
        usbRequest.queue(ByteBuffer.wrap(bArr), bArr.length);
    }
}
