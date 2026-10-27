package r9;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class B extends OutputStream {
    public final /* synthetic */ C l;

    public B(C c) {
        this.l = c;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        this.l.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
        C c = this.l;
        if (c.f21896n) {
            return;
        }
        c.flush();
    }

    public final String toString() {
        return this.l + ".outputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i7) throws IOException {
        C c = this.l;
        if (c.f21896n) {
            throw new IOException("closed");
        }
        c.f21895m.X((byte) i7);
        c.b();
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i7, int i10) throws IOException {
        A7.m.f(bArr, "data");
        C c = this.l;
        if (!c.f21896n) {
            c.f21895m.W(bArr, i7, i10);
            c.b();
            return;
        }
        throw new IOException("closed");
    }
}
