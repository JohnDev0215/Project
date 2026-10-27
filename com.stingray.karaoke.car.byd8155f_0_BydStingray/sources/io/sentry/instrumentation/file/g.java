package io.sentry.instrumentation.file;

import B8.t;
import io.sentry.C1458d0;
import io.sentry.F1;
import io.sentry.U;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class g extends FileOutputStream {
    public final FileOutputStream l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f16568m;

    /* JADX WARN: Illegal instructions before constructor call */
    public g(t tVar) throws FileNotFoundException {
        FileOutputStream fileOutputStream = (FileOutputStream) tVar.f809p;
        try {
            super(fileOutputStream.getFD());
            this.f16568m = new b((U) tVar.o, (File) tVar.f808n, (F1) tVar.f807m);
            this.l = fileOutputStream;
        } catch (IOException unused) {
            throw new FileNotFoundException("No file descriptor");
        }
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16568m.a(this.l);
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream
    public final void write(int i7) {
        this.f16568m.c(new f(i7, 0, this));
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream
    public final void write(byte[] bArr) {
        this.f16568m.c(new C1458d0(this, 6, bArr));
    }

    @Override // java.io.FileOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i7, int i10) {
        this.f16568m.c(new d(this, bArr, i7, i10, 1));
    }
}
