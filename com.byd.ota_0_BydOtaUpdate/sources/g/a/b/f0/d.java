package g.a.b.f0;

import g.a.b.t;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class d extends OutputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t f4742c;

    public d(t tVar) {
        this.f4742c = tVar;
    }

    @Override // java.io.OutputStream
    public void write(int i2) throws IOException {
        this.f4742c.update((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) throws IOException {
        this.f4742c.update(bArr, i2, i3);
    }
}
