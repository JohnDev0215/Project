package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class z extends a {
    public z(int i2, f fVar) {
        super(true, i2, s(fVar));
    }

    public static byte[] s(f fVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 != fVar.c(); i2++) {
            try {
                byteArrayOutputStream.write(((l) fVar.b(i2)).f("BER"));
            } catch (IOException e2) {
                throw new ASN1ParsingException("malformed object: " + e2, e2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // g.a.a.a, g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.k(this.f3735c ? 96 : 64, this.f3736d);
        pVar.c(128);
        pVar.d(this.s);
        pVar.c(0);
        pVar.c(0);
    }
}
