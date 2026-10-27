package g.a.a;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class i0 implements u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f3867c;

    public i0(v vVar) {
        this.f3867c = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new h0(this.f3867c.d());
    }

    @Override // g.a.a.e
    public q c() {
        try {
            return a();
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage(), e2);
        }
    }
}
