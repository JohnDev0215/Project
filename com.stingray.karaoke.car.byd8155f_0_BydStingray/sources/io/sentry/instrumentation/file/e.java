package io.sentry.instrumentation.file;

import B8.t;
import io.sentry.C1458d0;
import io.sentry.F1;
import io.sentry.U;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class e extends FileInputStream {
    public final FileInputStream l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f16565m;

    public e(t tVar, FileDescriptor fileDescriptor) {
        super(fileDescriptor);
        this.f16565m = new b((U) tVar.o, (File) tVar.f808n, (F1) tVar.f807m);
        this.l = (FileInputStream) tVar.f809p;
    }

    @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16565m.a(this.l);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final int read() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.f16565m.c(new C1458d0(this, 5, atomicInteger));
        return atomicInteger.get();
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final long skip(long j3) {
        return ((Long) this.f16565m.c(new c(j3, this))).longValue();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public e(t tVar) throws FileNotFoundException {
        FileInputStream fileInputStream = (FileInputStream) tVar.f809p;
        try {
            super(fileInputStream.getFD());
            this.f16565m = new b((U) tVar.o, (File) tVar.f808n, (F1) tVar.f807m);
            this.l = fileInputStream;
        } catch (IOException unused) {
            throw new FileNotFoundException("No file descriptor");
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final int read(byte[] bArr) {
        return ((Integer) this.f16565m.c(new C1458d0(this, 4, bArr))).intValue();
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) {
        return ((Integer) this.f16565m.c(new d(this, bArr, i7, i10, 0))).intValue();
    }
}
