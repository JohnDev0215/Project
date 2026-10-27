package com.bumptech.glide.load.data;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: BufferedOutputStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends OutputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final OutputStream f2937b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f2938c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.engine.x.b f2939d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2940e;

    public c(OutputStream outputStream, com.bumptech.glide.load.engine.x.b bVar) {
        this(outputStream, bVar, 65536);
    }

    private void j() throws IOException {
        int i = this.f2940e;
        if (i > 0) {
            this.f2937b.write(this.f2938c, 0, i);
            this.f2940e = 0;
        }
    }

    private void k() throws IOException {
        if (this.f2940e == this.f2938c.length) {
            j();
        }
    }

    private void l() {
        byte[] bArr = this.f2938c;
        if (bArr != null) {
            this.f2939d.d(bArr);
            this.f2938c = null;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.f2937b.close();
            l();
        } catch (Throwable th) {
            this.f2937b.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        j();
        this.f2937b.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.f2938c;
        int i2 = this.f2940e;
        this.f2940e = i2 + 1;
        bArr[i2] = (byte) i;
        k();
    }

    c(OutputStream outputStream, com.bumptech.glide.load.engine.x.b bVar, int i) {
        this.f2937b = outputStream;
        this.f2939d = bVar;
        this.f2938c = (byte[]) bVar.e(i, byte[].class);
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
            int i6 = this.f2940e;
            if (i6 == 0 && i4 >= this.f2938c.length) {
                this.f2937b.write(bArr, i5, i4);
                return;
            }
            int iMin = Math.min(i4, this.f2938c.length - i6);
            System.arraycopy(bArr, i5, this.f2938c, this.f2940e, iMin);
            this.f2940e += iMin;
            i3 += iMin;
            k();
        } while (i3 < i2);
    }
}
