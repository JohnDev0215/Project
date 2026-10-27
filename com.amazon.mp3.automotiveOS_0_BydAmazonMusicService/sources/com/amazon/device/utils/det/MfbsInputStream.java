package com.amazon.device.utils.det;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class MfbsInputStream extends InputStream {
    private ByteBuffer body;
    private UnsafeByteArrayOutputStream byteStream;
    private OutputStream compressedByteStream;
    private ByteBuffer header;
    private Writer logFileWriter;
    private BufferedReader mBufferedReader;

    private MfbsInputStream() throws IOException {
        this.byteStream = new UnsafeByteArrayOutputStream();
        this.compressedByteStream = new GZIPOutputStream(this.byteStream);
        this.logFileWriter = new BufferedWriter(new OutputStreamWriter(this.compressedByteStream));
    }

    public MfbsInputStream(BufferedReader bufferedReader) throws IOException {
        this();
        this.mBufferedReader = bufferedReader;
    }

    public Writer getLogFileWriter() {
        return this.logFileWriter;
    }

    public void pump() throws IOException {
        ByteBuffer byteBufferWrap;
        while (true) {
            try {
                try {
                    String line = this.mBufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    this.logFileWriter.write(line + "\n");
                } catch (IOException e) {
                    Log.e("pump", "Error while writing to log/crash file. File with partial information would be uploaded", e);
                    this.logFileWriter.flush();
                    this.compressedByteStream.close();
                    this.header = headerBuffer(this.byteStream.getCount(), "GZIP");
                    byteBufferWrap = ByteBuffer.wrap(this.byteStream.getBuf(), 0, this.byteStream.getCount());
                }
            } catch (Throwable th) {
                this.logFileWriter.flush();
                this.compressedByteStream.close();
                this.header = headerBuffer(this.byteStream.getCount(), "GZIP");
                this.body = ByteBuffer.wrap(this.byteStream.getBuf(), 0, this.byteStream.getCount());
                throw th;
            }
        }
        this.logFileWriter.flush();
        this.compressedByteStream.close();
        this.header = headerBuffer(this.byteStream.getCount(), "GZIP");
        byteBufferWrap = ByteBuffer.wrap(this.byteStream.getBuf(), 0, this.byteStream.getCount());
        this.body = byteBufferWrap;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.header.remaining() + this.body.remaining();
    }

    @Override // java.io.InputStream
    public int read() {
        byte b;
        if (this.header.hasRemaining()) {
            b = this.header.get();
        } else {
            if (!this.body.hasRemaining()) {
                return -1;
            }
            b = this.body.get();
        }
        return b & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int iMin = Math.min(i2, this.header.remaining());
        if (iMin > 0) {
            this.header.get(bArr, i, iMin);
        }
        int iMin2 = Math.min(i2 - iMin, this.body.remaining());
        if (iMin2 > 0) {
            this.body.get(bArr, i + iMin, iMin2);
        }
        int i3 = iMin + iMin2;
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    private static ByteBuffer headerBuffer(int i, String str) {
        return ByteBuffer.wrap(("\nFiles: messages.0\n------------------\nMFBS/1.0 1\n\nContent-Type: text/plain\nContent-Encoding: " + str + "\nContent-Length: " + i + "\nContent-Name: Content\n\n").getBytes());
    }

    private static class UnsafeByteArrayOutputStream extends ByteArrayOutputStream {
        private UnsafeByteArrayOutputStream() {
        }

        byte[] getBuf() {
            return ((ByteArrayOutputStream) this).buf;
        }

        int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }
    }
}
