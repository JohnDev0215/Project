package com.sun.xml.bind.v2.util;

import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class ByteArrayOutputStreamEx extends ByteArrayOutputStream {
    public ByteArrayOutputStreamEx() {
    }

    public ByteArrayOutputStreamEx(int i) {
        super(i);
    }

    public void set(Base64Data base64Data, String str) {
        base64Data.set(this.buf, this.count, str);
    }

    public byte[] getBuffer() {
        return this.buf;
    }

    public void readFrom(InputStream inputStream) throws IOException {
        while (true) {
            if (this.count == this.buf.length) {
                byte[] bArr = new byte[this.buf.length * 2];
                System.arraycopy(this.buf, 0, bArr, 0, this.buf.length);
                this.buf = bArr;
            }
            int i = inputStream.read(this.buf, this.count, this.buf.length - this.count);
            if (i < 0) {
                return;
            } else {
                this.count += i;
            }
        }
    }
}
