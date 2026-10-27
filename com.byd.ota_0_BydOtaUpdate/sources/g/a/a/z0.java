package g.a.a;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class z0 implements o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public r1 f4197c;

    public z0(r1 r1Var) {
        this.f4197c = r1Var;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new y0(this.f4197c.s());
    }

    @Override // g.a.a.o
    public InputStream b() {
        return this.f4197c;
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
