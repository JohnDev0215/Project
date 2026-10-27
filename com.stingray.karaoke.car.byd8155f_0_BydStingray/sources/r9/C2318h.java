package r9;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: r9.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2318h extends InputStream {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2321k f21931m;

    public /* synthetic */ C2318h(InterfaceC2321k interfaceC2321k, int i7) {
        this.l = i7;
        this.f21931m = interfaceC2321k;
    }

    private final void b() {
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        switch (this.l) {
            case 0:
                return (int) Math.min(((C2319i) this.f21931m).f21932m, Integer.MAX_VALUE);
            default:
                D d = (D) this.f21931m;
                if (d.f21898n) {
                    throw new IOException("closed");
                }
                return (int) Math.min(d.f21897m.f21932m, Integer.MAX_VALUE);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.l) {
            case 0:
                break;
            default:
                ((D) this.f21931m).close();
                break;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        switch (this.l) {
            case 0:
                C2319i c2319i = (C2319i) this.f21931m;
                if (c2319i.f21932m > 0) {
                    return c2319i.z() & 255;
                }
                return -1;
            default:
                D d = (D) this.f21931m;
                if (d.f21898n) {
                    throw new IOException("closed");
                }
                C2319i c2319i2 = d.f21897m;
                if (c2319i2.f21932m == 0 && d.l.p(c2319i2, 8192L) == -1) {
                    return -1;
                }
                return c2319i2.z() & 255;
        }
    }

    public final String toString() {
        switch (this.l) {
            case 0:
                return ((C2319i) this.f21931m) + ".inputStream()";
            default:
                return ((D) this.f21931m) + ".inputStream()";
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) throws IOException {
        switch (this.l) {
            case 0:
                A7.m.f(bArr, "sink");
                return ((C2319i) this.f21931m).v(bArr, i7, i10);
            default:
                A7.m.f(bArr, "data");
                D d = (D) this.f21931m;
                if (!d.f21898n) {
                    AbstractC2312b.e(bArr.length, i7, i10);
                    C2319i c2319i = d.f21897m;
                    if (c2319i.f21932m == 0 && d.l.p(c2319i, 8192L) == -1) {
                        return -1;
                    }
                    return c2319i.v(bArr, i7, i10);
                }
                throw new IOException("closed");
        }
    }
}
