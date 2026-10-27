package g.a.b.k0;

import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.s;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class b implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public x f5090a;

    public b(x xVar) {
        this.f5090a = xVar;
    }

    @Override // g.a.b.s
    public g.a.b.j0.b a(InputStream inputStream) throws IOException {
        byte[] bArr;
        int i2 = inputStream.read();
        if (i2 == 0) {
            throw new IOException("Sender's public key invalid.");
        }
        if (i2 == 2 || i2 == 3) {
            bArr = new byte[((this.f5090a.a().u() + 7) / 8) + 1];
        } else {
            if (i2 != 4 && i2 != 6 && i2 != 7) {
                throw new IOException("Sender's public key has invalid point encoding 0x" + Integer.toString(i2, 16));
            }
            bArr = new byte[(((this.f5090a.a().u() + 7) / 8) * 2) + 1];
        }
        bArr[0] = (byte) i2;
        g.a.f.k.a.d(inputStream, bArr, 1, bArr.length - 1);
        return new c0(this.f5090a.a().k(bArr), this.f5090a);
    }
}
