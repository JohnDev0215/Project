package g.a.b.k0;

import g.a.b.j0.i;
import g.a.b.j0.k;
import g.a.b.s;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class a implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f5089a;

    public a(i iVar) {
        this.f5089a = iVar;
    }

    @Override // g.a.b.s
    public g.a.b.j0.b a(InputStream inputStream) throws IOException {
        int iBitLength = (this.f5089a.f().bitLength() + 7) / 8;
        byte[] bArr = new byte[iBitLength];
        g.a.f.k.a.d(inputStream, bArr, 0, iBitLength);
        return new k(new BigInteger(1, bArr), this.f5089a);
    }
}
