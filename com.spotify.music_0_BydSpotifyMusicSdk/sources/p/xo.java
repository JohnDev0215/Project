package p;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class xo extends OutputStream {
    public final /* synthetic */ int c = 1;
    public final Object e;

    public xo(fo foVar) {
        this.e = foVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.c) {
            case 1:
                break;
            default:
                super.close();
                break;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.c) {
            case 1:
                break;
            default:
                super.flush();
                break;
        }
    }

    public final String toString() {
        switch (this.c) {
            case 1:
                return ((fo) this.e) + ".outputStream()";
            default:
                return super.toString();
        }
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        int i3 = this.c;
        Object obj = this.e;
        switch (i3) {
            case 0:
                ((ByteBuffer) obj).put(bArr, i, i2);
                break;
            default:
                v03.j(bArr, "data");
                ((fo) obj).V0(bArr, i, i2);
                break;
        }
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        int i2 = this.c;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((ByteBuffer) obj).put((byte) i);
                break;
            default:
                ((fo) obj).W0(i);
                break;
        }
    }
}
