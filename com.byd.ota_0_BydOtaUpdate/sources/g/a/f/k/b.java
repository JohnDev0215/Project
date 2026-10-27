package g.a.f.k;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class b extends OutputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public OutputStream f5737c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public OutputStream f5738d;

    public b(OutputStream outputStream, OutputStream outputStream2) {
        this.f5737c = outputStream;
        this.f5738d = outputStream2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5737c.close();
        this.f5738d.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.f5737c.flush();
        this.f5738d.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i2) throws IOException {
        this.f5737c.write(i2);
        this.f5738d.write(i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.f5737c.write(bArr);
        this.f5738d.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        this.f5737c.write(bArr, i2, i3);
        this.f5738d.write(bArr, i2, i3);
    }
}
