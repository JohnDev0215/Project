package g.a.c.e;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BigInteger f5259a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BigInteger f5260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5261c;

    public n(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f5259a = bigInteger;
        this.f5260b = bigInteger2;
        this.f5261c = bigInteger3;
    }

    public BigInteger a() {
        return this.f5261c;
    }

    public BigInteger b() {
        return this.f5259a;
    }

    public BigInteger c() {
        return this.f5260b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f5261c.equals(nVar.f5261c) && this.f5259a.equals(nVar.f5259a) && this.f5260b.equals(nVar.f5260b);
    }

    public int hashCode() {
        return (this.f5261c.hashCode() ^ this.f5259a.hashCode()) ^ this.f5260b.hashCode();
    }
}
