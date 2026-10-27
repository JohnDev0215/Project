package h2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: h2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1344a extends OutputStream {
    public final FileOutputStream l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15206m = false;

    public C1344a(File file) {
        this.l = new FileOutputStream(file);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        FileOutputStream fileOutputStream = this.l;
        if (this.f15206m) {
            return;
        }
        this.f15206m = true;
        flush();
        try {
            fileOutputStream.getFD().sync();
        } catch (IOException e10) {
            AbstractC1346c.A("AtomicFile", "Failed to sync file descriptor:", e10);
        }
        fileOutputStream.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        this.l.flush();
    }

    @Override // java.io.OutputStream
    public final void write(int i7) throws IOException {
        this.l.write(i7);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        this.l.write(bArr);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i7, int i10) throws IOException {
        this.l.write(bArr, i7, i10);
    }
}
