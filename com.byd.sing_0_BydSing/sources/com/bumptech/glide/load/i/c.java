package com.bumptech.glide.load.i;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: BufferedOutputStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends OutputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final OutputStream f2922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f2923c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.engine.x.b f2924d;
    private int e;

    public c(OutputStream outputStream, com.bumptech.glide.load.engine.x.b bVar) {
        this(outputStream, bVar, 65536);
    }

    private void a() throws IOException {
        int i = this.e;
        if (i > 0) {
            this.f2922b.write(this.f2923c, 0, i);
            this.e = 0;
        }
    }

    private void b() throws IOException {
        if (this.e == this.f2923c.length) {
            a();
        }
    }

    private void c() {
        byte[] bArr = this.f2923c;
        if (bArr != null) {
            this.f2924d.put(bArr);
            this.f2923c = null;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.f2922b.close();
            c();
        } catch (Throwable th) {
            this.f2922b.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        a();
        this.f2922b.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.f2923c;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr[i2] = (byte) i;
        b();
    }

    c(OutputStream outputStream, com.bumptech.glide.load.engine.x.b bVar, int i) {
        this.f2922b = outputStream;
        this.f2924d = bVar;
        this.f2923c = (byte[]) bVar.d(i, byte[].class);
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
            if (i6 == 0 && i4 >= this.f2923c.length) {
                this.f2922b.write(bArr, i5, i4);
                return;
            }
            int iMin = Math.min(i4, this.f2923c.length - i6);
            System.arraycopy(bArr, i5, this.f2923c, this.e, iMin);
            this.e += iMin;
            i3 += iMin;
            b();
        } while (i3 < i2);
    }
}
