package g.a.a;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class a0 implements e, s1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3737c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v f3738d;

    public a0(int i2, v vVar) {
        this.f3737c = i2;
        this.f3738d = vVar;
    }

    @Override // g.a.a.s1
    public q a() throws IOException {
        return new z(this.f3737c, this.f3738d.d());
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
