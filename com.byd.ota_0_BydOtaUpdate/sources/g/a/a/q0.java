package g.a.a;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class q0 implements e, s1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f4001c;

    public q0(v vVar) {
        this.f4001c = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        try {
            return new p0(this.f4001c.d());
        } catch (IllegalArgumentException e2) {
            throw new ASN1Exception(e2.getMessage(), e2);
        }
    }

    @Override // g.a.a.e
    public q c() {
        try {
            return a();
        } catch (IOException e2) {
            throw new ASN1ParsingException("unable to get DER object", e2);
        } catch (IllegalArgumentException e3) {
            throw new ASN1ParsingException("unable to get DER object", e3);
        }
    }
}
