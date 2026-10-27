package g.a.b.f0;

import g.a.b.t;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class c extends FilterInputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f4741c;

    public c(InputStream inputStream, t tVar) {
        super(inputStream);
        this.f4741c = tVar;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i2 = ((FilterInputStream) this).in.read();
        if (i2 >= 0) {
            this.f4741c.update((byte) i2);
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = ((FilterInputStream) this).in.read(bArr, i2, i3);
        if (i4 >= 0) {
            this.f4741c.update(bArr, i2, i4);
        }
        return i4;
    }
}
