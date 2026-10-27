package com.amazon.identity.auth.device;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class eb extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f300a = new Object[0];
    public final ByteArrayOutputStream b = new ByteArrayOutputStream();
    public final URLConnection c;
    public OutputStream d;

    public eb(URLConnection uRLConnection) {
        this.c = uRLConnection;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this.d;
        if (outputStream != null) {
            outputStream.close();
        }
        super.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.d;
        if (outputStream != null) {
            outputStream.flush();
        }
        super.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        synchronized (this.f300a) {
            OutputStream outputStream = this.d;
            if (outputStream != null) {
                outputStream.write(i);
            } else {
                this.b.write(i);
            }
        }
    }
}
