package g.a.a;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class k0 implements e, s1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3885c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3886d;
    public v s;

    public k0(boolean z, int i2, v vVar) {
        this.f3885c = z;
        this.f3886d = i2;
        this.s = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return this.s.c(this.f3885c, this.f3886d);
    }

    @Override // g.a.a.e
    public q c() {
        try {
            return a();
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage());
        }
    }
}
