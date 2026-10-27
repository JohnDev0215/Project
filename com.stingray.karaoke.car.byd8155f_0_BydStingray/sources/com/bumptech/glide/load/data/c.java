package com.bumptech.glide.load.data;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class c extends OutputStream {
    public final io.sentry.instrumentation.file.g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte[] f12721m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final F.l f12722n;
    public int o;

    public c(io.sentry.instrumentation.file.g gVar, F.l lVar) {
        this.l = gVar;
        this.f12722n = lVar;
        this.f12721m = (byte[]) lVar.d(65536, byte[].class);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.instrumentation.file.g gVar = this.l;
        try {
            flush();
            gVar.close();
            byte[] bArr = this.f12721m;
            if (bArr != null) {
                this.f12722n.i(bArr);
                this.f12721m = null;
            }
        } catch (Throwable th) {
            gVar.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        int i7 = this.o;
        io.sentry.instrumentation.file.g gVar = this.l;
        if (i7 > 0) {
            gVar.write(this.f12721m, 0, i7);
            this.o = 0;
        }
        gVar.flush();
    }

    @Override // java.io.OutputStream
    public final void write(int i7) {
        byte[] bArr = this.f12721m;
        int i10 = this.o;
        int i11 = i10 + 1;
        this.o = i11;
        bArr[i10] = (byte) i7;
        if (i11 != bArr.length || i11 <= 0) {
            return;
        }
        this.l.write(bArr, 0, i11);
        this.o = 0;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i7, int i10) {
        int i11 = 0;
        do {
            int i12 = i10 - i11;
            int i13 = i7 + i11;
            int i14 = this.o;
            io.sentry.instrumentation.file.g gVar = this.l;
            if (i14 == 0 && i12 >= this.f12721m.length) {
                gVar.write(bArr, i13, i12);
                return;
            }
            int iMin = Math.min(i12, this.f12721m.length - i14);
            System.arraycopy(bArr, i13, this.f12721m, this.o, iMin);
            int i15 = this.o + iMin;
            this.o = i15;
            i11 += iMin;
            byte[] bArr2 = this.f12721m;
            if (i15 == bArr2.length && i15 > 0) {
                gVar.write(bArr2, 0, i15);
                this.o = 0;
            }
        } while (i11 < i10);
    }
}
