package p;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class wo extends InputStream {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ wo(ko koVar, int i) {
        this.c = i;
        this.e = koVar;
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                return ((ByteBuffer) obj).remaining();
            case 1:
                return (int) Math.min(((fo) obj).e, Integer.MAX_VALUE);
            default:
                w02 w02Var = (w02) obj;
                if (w02Var.k) {
                    throw new IOException("closed");
                }
                return (int) Math.min(w02Var.e.e, Integer.MAX_VALUE);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.c) {
            case 1:
                break;
            case 2:
                ((w02) this.e).close();
                break;
            default:
                super.close();
                break;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.c;
        Object obj = this.e;
        switch (i3) {
            case 0:
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                if (!byteBuffer.hasRemaining()) {
                    return -1;
                }
                int iMin = Math.min(i2, byteBuffer.remaining());
                byteBuffer.get(bArr, i, iMin);
                return iMin;
            case 1:
                v03.j(bArr, "sink");
                return ((fo) obj).K0(bArr, i, i2);
            default:
                v03.j(bArr, "data");
                w02 w02Var = (w02) obj;
                if (w02Var.k) {
                    throw new IOException("closed");
                }
                q30.i(bArr.length, i, i2);
                fo foVar = w02Var.e;
                if (foVar.e == 0 && w02Var.c.t(foVar, 8192L) == -1) {
                    return -1;
                }
                return foVar.K0(bArr, i, i2);
        }
    }

    public final String toString() {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 1:
                return ((fo) obj) + ".inputStream()";
            case 2:
                return ((w02) obj) + ".inputStream()";
            default:
                return super.toString();
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                if (byteBuffer.hasRemaining()) {
                    return byteBuffer.get() & 255;
                }
                return -1;
            case 1:
                fo foVar = (fo) obj;
                if (foVar.e > 0) {
                    return foVar.readByte() & 255;
                }
                return -1;
            default:
                w02 w02Var = (w02) obj;
                if (!w02Var.k) {
                    fo foVar2 = w02Var.e;
                    if (foVar2.e == 0 && w02Var.c.t(foVar2, 8192L) == -1) {
                        return -1;
                    }
                    return foVar2.readByte() & 255;
                }
                throw new IOException("closed");
        }
    }
}
