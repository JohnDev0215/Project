package g.a.a;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f3791c;

    public d0(v vVar) {
        this.f3791c = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new c0(g.a.f.k.a.b(b()));
    }

    @Override // g.a.a.o
    public InputStream b() {
        return new l0(this.f3791c);
    }

    @Override // g.a.a.e
    public q c() {
        try {
            return a();
        } catch (IOException e2) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e2.getMessage(), e2);
        }
    }
}
