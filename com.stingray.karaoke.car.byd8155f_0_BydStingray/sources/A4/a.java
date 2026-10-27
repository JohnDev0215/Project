package A4;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class a extends InputStream {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f410m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f411n;

    public a(InputStream inputStream) {
        this.l = 1;
        this.f411n = inputStream;
        this.f410m = TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
    }

    @Override // java.io.InputStream
    public final int available() {
        switch (this.l) {
            case 0:
                return ((ByteBuffer) this.f411n).remaining();
            default:
                return this.f410m;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        switch (this.l) {
            case 1:
                ((InputStream) this.f411n).close();
                break;
            default:
                super.close();
                break;
        }
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i7) {
        switch (this.l) {
            case 0:
                synchronized (this) {
                    this.f410m = ((ByteBuffer) this.f411n).position();
                }
                return;
            default:
                super.mark(i7);
                return;
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        switch (this.l) {
            case 0:
                return true;
            default:
                return super.markSupported();
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        switch (this.l) {
            case 0:
                ByteBuffer byteBuffer = (ByteBuffer) this.f411n;
                if (byteBuffer.hasRemaining()) {
                    return byteBuffer.get() & 255;
                }
                return -1;
            default:
                int i7 = ((InputStream) this.f411n).read();
                if (i7 == -1) {
                    this.f410m = 0;
                }
                return i7;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        switch (this.l) {
            case 0:
                synchronized (this) {
                    int i7 = this.f410m;
                    if (i7 == -1) {
                        throw new IOException("Cannot reset to unset mark position");
                    }
                    ((ByteBuffer) this.f411n).position(i7);
                }
                return;
            default:
                super.reset();
                return;
        }
    }

    @Override // java.io.InputStream
    public final long skip(long j3) {
        switch (this.l) {
            case 0:
                ByteBuffer byteBuffer = (ByteBuffer) this.f411n;
                if (!byteBuffer.hasRemaining()) {
                    return -1L;
                }
                long jMin = Math.min(j3, byteBuffer.remaining());
                byteBuffer.position((int) (((long) byteBuffer.position()) + jMin));
                return jMin;
            default:
                return ((InputStream) this.f411n).skip(j3);
        }
    }

    public a(ByteBuffer byteBuffer) {
        this.l = 0;
        this.f410m = -1;
        this.f411n = byteBuffer;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        switch (this.l) {
            case 1:
                int i7 = ((InputStream) this.f411n).read(bArr);
                if (i7 == -1) {
                    this.f410m = 0;
                }
                return i7;
            default:
                return super.read(bArr);
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) throws IOException {
        switch (this.l) {
            case 0:
                ByteBuffer byteBuffer = (ByteBuffer) this.f411n;
                if (!byteBuffer.hasRemaining()) {
                    return -1;
                }
                int iMin = Math.min(i10, byteBuffer.remaining());
                byteBuffer.get(bArr, i7, iMin);
                return iMin;
            default:
                int i11 = ((InputStream) this.f411n).read(bArr, i7, i10);
                if (i11 == -1) {
                    this.f410m = 0;
                }
                return i11;
        }
    }
}
