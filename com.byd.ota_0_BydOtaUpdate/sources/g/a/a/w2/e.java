package g.a.a.w2;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f4116c;

    public e(BigInteger bigInteger) {
        this.f4116c = bigInteger;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return new g.a.a.j(this.f4116c);
    }

    public BigInteger g() {
        return this.f4116c;
    }

    public String toString() {
        return "CRLNumber: " + g();
    }
}
