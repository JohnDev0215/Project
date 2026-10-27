package g.a.d.c;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BigInteger f5560a;

    public g(BigInteger bigInteger) {
        this.f5560a = bigInteger;
    }

    @Override // g.a.d.c.a
    public int b() {
        return 1;
    }

    @Override // g.a.d.c.a
    public BigInteger c() {
        return this.f5560a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            return this.f5560a.equals(((g) obj).f5560a);
        }
        return false;
    }

    public int hashCode() {
        return this.f5560a.hashCode();
    }
}
