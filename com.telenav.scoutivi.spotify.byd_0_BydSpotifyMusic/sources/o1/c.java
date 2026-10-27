package o1;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: BufferedOutputStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends OutputStream {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final OutputStream f8643e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private byte[] f8644g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private r1.b f8645h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f8646i;

    public c(OutputStream outputStream, r1.b bVar) {
        this(outputStream, bVar, 65536);
    }

    private void A() {
        byte[] bArr = this.f8644g;
        if (bArr != null) {
            this.f8645h.d(bArr);
            this.f8644g = null;
        }
    }

    private void m() throws IOException {
        int i7 = this.f8646i;
        if (i7 > 0) {
            this.f8643e.write(this.f8644g, 0, i7);
            this.f8646i = 0;
        }
    }

    private void z() throws IOException {
        if (this.f8646i == this.f8644g.length) {
            m();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.f8643e.close();
            A();
        } catch (Throwable th) {
            this.f8643e.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        m();
        this.f8643e.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i7) throws IOException {
        byte[] bArr = this.f8644g;
        int i8 = this.f8646i;
        this.f8646i = i8 + 1;
        bArr[i8] = (byte) i7;
        z();
    }

    c(OutputStream outputStream, r1.b bVar, int i7) {
        this.f8643e = outputStream;
        this.f8645h = bVar;
        this.f8644g = (byte[]) bVar.e(i7, byte[].class);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        int i9 = 0;
        do {
            int i10 = i8 - i9;
            int i11 = i7 + i9;
            int i12 = this.f8646i;
            if (i12 == 0 && i10 >= this.f8644g.length) {
                this.f8643e.write(bArr, i11, i10);
                return;
            }
            int iMin = Math.min(i10, this.f8644g.length - i12);
            System.arraycopy(bArr, i11, this.f8644g, this.f8646i, iMin);
            this.f8646i += iMin;
            i9 += iMin;
            z();
        } while (i9 < i8);
    }
}
