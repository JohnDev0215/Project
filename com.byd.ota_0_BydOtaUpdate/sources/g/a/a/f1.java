package g.a.a;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class f1 implements u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f3827c;

    public f1(v vVar) {
        this.f3827c = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new e1(this.f3827c.d(), false);
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
