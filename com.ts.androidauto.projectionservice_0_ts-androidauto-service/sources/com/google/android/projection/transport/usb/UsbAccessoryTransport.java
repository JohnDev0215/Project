package com.google.android.projection.transport.usb;

import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.projection.common.UsbInputReader;
import com.google.android.projection.protocol.Transport;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class UsbAccessoryTransport extends Transport {
    private static final String TAG = "AHU.USB_ACC_CONNECTION";
    private static final int USB_ACCESSORY_BUFFER_SIZE = 16384;
    private final InputStream mInputStream;
    private boolean mIsOpened;
    private final OutputStream mOutputStream;
    private final ParcelFileDescriptor mParcelAccessoryFd;
    private final UsbInputReader mUsbInputReader;

    public UsbAccessoryTransport(UsbManager manager, UsbAccessory accessory) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorOpenAccessory = manager.openAccessory(accessory);
        this.mParcelAccessoryFd = parcelFileDescriptorOpenAccessory;
        if (parcelFileDescriptorOpenAccessory == null) {
            throw new IOException("Unable to open USB accessory");
        }
        FileDescriptor accessoryFd = parcelFileDescriptorOpenAccessory.getFileDescriptor();
        this.mOutputStream = new FileOutputStream(accessoryFd);
        this.mInputStream = new FileInputStream(accessoryFd);
        UsbInputReader.RawUsbInputReader rawUsbInputReader = new UsbInputReader.RawUsbInputReader() { // from class: com.google.android.projection.transport.usb.UsbAccessoryTransport.1
            @Override // com.google.android.projection.common.UsbInputReader.RawUsbInputReader
            public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
                return UsbAccessoryTransport.this.mInputStream.read(buffer, byteOffset, byteCount);
            }
        };
        this.mUsbInputReader = new UsbInputReader(rawUsbInputReader, 16384);
        this.mIsOpened = true;
    }

    @Override // com.google.android.projection.protocol.Transport
    public void stopTransport(Transport.StopReason reason) {
        try {
            this.mInputStream.close();
            this.mOutputStream.close();
            this.mParcelAccessoryFd.close();
        } catch (IOException e) {
            Log.d("AHU.USB_ACC_CONNECTION", "Can't close USB accessory: " + e);
        }
        this.mIsOpened = false;
    }

    @Override // com.google.android.projection.protocol.Transport
    public int read(byte[] b, int off, int len) throws IOException {
        if (!this.mIsOpened) {
            throw new IOException("Connection was closed.");
        }
        return this.mUsbInputReader.read(b, off, len);
    }

    @Override // com.google.android.projection.protocol.Transport
    public void write(byte[] b, int off, int len) throws IOException {
        if (!this.mIsOpened) {
            throw new IOException("Connection was closed.");
        }
        this.mOutputStream.write(b, off, len);
    }
}
