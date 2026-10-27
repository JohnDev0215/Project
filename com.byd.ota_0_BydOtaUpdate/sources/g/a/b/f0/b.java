package g.a.b.f0;

import g.a.b.n;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class b extends OutputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public n f4740c;

    public b(n nVar) {
        this.f4740c = nVar;
    }

    public byte[] c() {
        byte[] bArr = new byte[this.f4740c.getDigestSize()];
        this.f4740c.doFinal(bArr, 0);
        return bArr;
    }

    @Override // java.io.OutputStream
    public void write(int i2) throws IOException {
        this.f4740c.update((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        this.f4740c.update(bArr, i2, i3);
    }
}
