package com.sun.istack;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;

/* JADX INFO: loaded from: classes3.dex */
public final class ByteArrayDataSource implements DataSource {
    private final byte[] buf;
    private final String contentType;
    private final int len;

    @Override // javax.activation.DataSource
    public String getName() {
        return null;
    }

    public ByteArrayDataSource(byte[] bArr, String str) {
        this(bArr, bArr.length, str);
    }

    public ByteArrayDataSource(byte[] bArr, int i, String str) {
        this.buf = bArr;
        this.len = i;
        this.contentType = str;
    }

    @Override // javax.activation.DataSource
    public String getContentType() {
        String str = this.contentType;
        return str == null ? "application/octet-stream" : str;
    }

    @Override // javax.activation.DataSource
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.buf, 0, this.len);
    }

    @Override // javax.activation.DataSource
    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException();
    }
}
