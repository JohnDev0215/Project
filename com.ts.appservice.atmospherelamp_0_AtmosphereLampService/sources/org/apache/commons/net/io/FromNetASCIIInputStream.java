package org.apache.commons.net.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.net.SocketClient;

/* JADX INFO: loaded from: classes3.dex */
public final class FromNetASCIIInputStream extends PushbackInputStream {
    static final String _lineSeparator;
    static final byte[] _lineSeparatorBytes;
    static final boolean _noConversionRequired;
    private int length;

    static {
        String property = System.getProperty("line.separator");
        _lineSeparator = property;
        _noConversionRequired = property.equals(SocketClient.NETASCII_EOL);
        try {
            _lineSeparatorBytes = _lineSeparator.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Broken JVM - cannot find US-ASCII charset!", e);
        }
    }

    public static boolean isConversionRequired() {
        return !_noConversionRequired;
    }

    public FromNetASCIIInputStream(InputStream input) {
        super(input, _lineSeparatorBytes.length + 1);
        this.length = 0;
    }

    private int __read() throws IOException {
        int ch = super.read();
        if (ch == 13) {
            int ch2 = super.read();
            if (ch2 == 10) {
                unread(_lineSeparatorBytes);
                this.length--;
                return super.read();
            }
            if (ch2 != -1) {
                unread(ch2);
            }
            return 13;
        }
        return ch;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (_noConversionRequired) {
            return super.read();
        }
        return __read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        int offset2;
        if (_noConversionRequired) {
            return super.read(buffer, offset, length);
        }
        if (length < 1) {
            return 0;
        }
        int ch = available();
        int i = length > ch ? ch : length;
        this.length = i;
        if (i < 1) {
            this.length = 1;
        }
        int i__read = __read();
        int ch2 = i__read;
        if (i__read == -1) {
            return -1;
        }
        while (true) {
            offset2 = offset + 1;
            buffer[offset] = (byte) ch2;
            int i2 = this.length - 1;
            this.length = i2;
            if (i2 <= 0) {
                break;
            }
            int i__read2 = __read();
            ch2 = i__read2;
            if (i__read2 == -1) {
                break;
            }
            offset = offset2;
        }
        return offset2 - offset;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new IOException("Stream closed");
        }
        return (this.buf.length - this.pos) + this.in.available();
    }
}
