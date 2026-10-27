package com.bumptech.glide.o;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: MarkEnforcingInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends FilterInputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3464b;

    public h(InputStream inputStream) {
        super(inputStream);
        this.f3464b = Integer.MIN_VALUE;
    }

    private long j(long j) {
        int i = this.f3464b;
        if (i == 0) {
            return -1L;
        }
        return (i == Integer.MIN_VALUE || j <= ((long) i)) ? j : i;
    }

    private void k(long j) {
        int i = this.f3464b;
        if (i == Integer.MIN_VALUE || j == -1) {
            return;
        }
        this.f3464b = (int) (((long) i) - j);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int i = this.f3464b;
        return i == Integer.MIN_VALUE ? super.available() : Math.min(i, super.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        super.mark(i);
        this.f3464b = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (j(1L) == -1) {
            return -1;
        }
        int i = super.read();
        k(1L);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        this.f3464b = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = j(j);
        if (j2 == -1) {
            return 0L;
        }
        long jSkip = super.skip(j2);
        k(jSkip);
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int iJ = (int) j(i2);
        if (iJ == -1) {
            return -1;
        }
        int i3 = super.read(bArr, i, iJ);
        k(i3);
        return i3;
    }
}
