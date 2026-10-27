package com.bumptech.glide.load.data;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class c extends OutputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final OutputStream f1974b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f1975c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.n.a0.b f1976d;
    private int e;

    public c(OutputStream outputStream, com.bumptech.glide.load.n.a0.b bVar) {
        this(outputStream, bVar, 65536);
    }

    c(OutputStream outputStream, com.bumptech.glide.load.n.a0.b bVar, int i) {
        this.f1974b = outputStream;
        this.f1976d = bVar;
        this.f1975c = (byte[]) bVar.e(i, byte[].class);
    }

    private void a() throws IOException {
        int i = this.e;
        if (i > 0) {
            this.f1974b.write(this.f1975c, 0, i);
            this.e = 0;
        }
    }

    private void b() throws IOException {
        if (this.e == this.f1975c.length) {
            a();
        }
    }

    private void c() {
        byte[] bArr = this.f1975c;
        if (bArr != null) {
            this.f1976d.d(bArr);
            this.f1975c = null;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.f1974b.close();
            c();
        } catch (Throwable th) {
            this.f1974b.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        a();
        this.f1974b.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.f1975c;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr[i2] = (byte) i;
        b();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            int i4 = i2 - i3;
            int i5 = i + i3;
            int i6 = this.e;
            if (i6 == 0 && i4 >= this.f1975c.length) {
                this.f1974b.write(bArr, i5, i4);
                return;
            }
            int iMin = Math.min(i4, this.f1975c.length - i6);
            System.arraycopy(bArr, i5, this.f1975c, this.e, iMin);
            this.e += iMin;
            i3 += iMin;
            b();
        } while (i3 < i2);
    }
}
