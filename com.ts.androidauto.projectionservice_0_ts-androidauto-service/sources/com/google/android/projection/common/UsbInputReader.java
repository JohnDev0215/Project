package com.google.android.projection.common;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class UsbInputReader {
    private final byte[] bufferFragment;
    private int fragBufferEndPosition = 0;
    private int fragBufferStartPosition = 0;
    private final int minBufferLength;
    private final RawUsbInputReader usbInputReader;

    public interface RawUsbInputReader {
        int read(byte[] bArr, int i, int i2) throws IOException;
    }

    public UsbInputReader(RawUsbInputReader usbInputReader, int minReadLength) {
        this.usbInputReader = usbInputReader;
        this.minBufferLength = minReadLength;
        this.bufferFragment = new byte[minReadLength * 2];
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int i = this.fragBufferEndPosition;
        int i2 = this.fragBufferStartPosition;
        int leftInBuffer = i - i2;
        int leftToRead = byteCount;
        if (leftInBuffer > 0) {
            System.arraycopy(this.bufferFragment, i2, buffer, byteOffset, Math.min(byteCount, leftInBuffer));
            if (leftInBuffer >= byteCount) {
                this.fragBufferStartPosition += byteCount;
                return byteCount;
            }
            leftToRead -= leftInBuffer;
            byteOffset += leftInBuffer;
        }
        this.fragBufferEndPosition = 0;
        this.fragBufferStartPosition = 0;
        Thread currentThread = Thread.currentThread();
        while (leftToRead > this.minBufferLength) {
            if (currentThread.isInterrupted()) {
                throw new IOException("interrupted");
            }
            int read = this.usbInputReader.read(buffer, byteOffset, leftToRead);
            if (read > 0) {
                byteOffset += read;
                leftToRead -= read;
            } else if (read < 0) {
                throw new IOException("read failed " + read);
            }
        }
        while (leftToRead > this.fragBufferEndPosition) {
            if (currentThread.isInterrupted()) {
                throw new IOException("interrupted");
            }
            int read2 = this.usbInputReader.read(this.bufferFragment, this.fragBufferEndPosition, this.minBufferLength);
            if (read2 > 0) {
                this.fragBufferEndPosition += read2;
            } else if (read2 < 0) {
                throw new IOException("read failed " + read2);
            }
        }
        System.arraycopy(this.bufferFragment, 0, buffer, byteOffset, leftToRead);
        this.fragBufferStartPosition += leftToRead;
        return byteCount;
    }
}
