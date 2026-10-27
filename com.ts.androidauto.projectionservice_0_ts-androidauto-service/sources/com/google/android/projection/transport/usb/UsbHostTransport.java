package com.google.android.projection.transport.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.google.android.projection.common.UsbInputReader;
import com.google.android.projection.protocol.Transport;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class UsbHostTransport extends Transport {
    private static final int SCRATCH_SIZE = 32768;
    private static final String TAG = "AHU.USB_HOST_CONNECTION";
    private static final int TIMEOUT_MILLIS = 36000000;
    private final UsbDeviceConnection mAccessoryConnection;
    private final UsbInterface mAccessoryInterface;
    private final UsbEndpoint mBulkInEndpoint;
    private final UsbEndpoint mBulkOutEndpoint;
    private boolean mIsOpened;
    private final UsbInputReader mUsbInputReader;

    public UsbHostTransport(UsbManager manager, UsbDevice device) throws IOException {
        UsbDeviceConnection usbDeviceConnectionOpenDevice = manager.openDevice(device);
        this.mAccessoryConnection = usbDeviceConnectionOpenDevice;
        if (usbDeviceConnectionOpenDevice == null) {
            throw new IOException("connection failed to open");
        }
        UsbInterface usbInterface = device.getInterface(0);
        this.mAccessoryInterface = usbInterface;
        if (usbInterface == null) {
            throw new IOException("interface not available");
        }
        if (!this.mAccessoryConnection.claimInterface(usbInterface, true)) {
            throw new IOException("interface could not be claimed");
        }
        UsbEndpoint in = null;
        UsbEndpoint out = null;
        for (int i = 0; i < this.mAccessoryInterface.getEndpointCount(); i++) {
            UsbEndpoint ep = this.mAccessoryInterface.getEndpoint(i);
            if (ep.getType() == 2) {
                if (ep.getDirection() == 128) {
                    Log.d("AHU.USB_HOST_CONNECTION", String.format("Found bulk IN endpoint at %d", Integer.valueOf(i)));
                    in = ep;
                } else if (ep.getDirection() == 0) {
                    Log.d("AHU.USB_HOST_CONNECTION", String.format("Found bulk OUT endpoint at %d", Integer.valueOf(i)));
                    out = ep;
                }
            }
        }
        if (in == null) {
            throw new IOException("Could not find USB bulk in endpoint");
        }
        if (out == null) {
            throw new IOException("Could not find USB bulk out endpoint");
        }
        this.mBulkInEndpoint = in;
        this.mBulkOutEndpoint = out;
        UsbInputReader.RawUsbInputReader rawUsbInputReader = new UsbInputReader.RawUsbInputReader() { // from class: com.google.android.projection.transport.usb.UsbHostTransport.1
            @Override // com.google.android.projection.common.UsbInputReader.RawUsbInputReader
            public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
                if (!UsbHostTransport.this.mIsOpened) {
                    throw new IOException("USB device is closed");
                }
                try {
                    int bytes = UsbHostTransport.this.mAccessoryConnection.bulkTransfer(UsbHostTransport.this.mBulkInEndpoint, buffer, byteOffset, byteCount, UsbHostTransport.TIMEOUT_MILLIS);
                    return bytes;
                } catch (NullPointerException e) {
                    throw new IOException(e);
                }
            }
        };
        this.mUsbInputReader = new UsbInputReader(rawUsbInputReader, 32768);
        this.mIsOpened = true;
    }

    @Override // com.google.android.projection.protocol.Transport
    public void stopTransport(Transport.StopReason reason) {
        this.mAccessoryConnection.releaseInterface(this.mAccessoryInterface);
        if (reason != Transport.StopReason.MD_BYEBYE) {
            try {
                this.mAccessoryConnection.getClass().getDeclaredMethod("resetDevice", new Class[0]).invoke(this.mAccessoryConnection, new Object[0]);
            } catch (Exception e) {
                Log.w("AHU.USB_HOST_CONNECTION", "Unable to reset USB - restarting projection will not work", e);
            }
        }
        this.mAccessoryConnection.close();
        this.mIsOpened = false;
    }

    @Override // com.google.android.projection.protocol.Transport
    public int read(byte[] buffer, int offset, int count) throws IOException {
        if (!this.mIsOpened) {
            throw new IOException("Connection was closed.");
        }
        return this.mUsbInputReader.read(buffer, offset, count);
    }

    @Override // com.google.android.projection.protocol.Transport
    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.mIsOpened) {
            throw new IOException("Connection was closed.");
        }
        int result = this.mAccessoryConnection.bulkTransfer(this.mBulkOutEndpoint, b, off, len, TIMEOUT_MILLIS);
        if (result < 0) {
            throw new IOException("Bulk transfer failed.");
        }
    }
}
