package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class i0 extends g.a.b.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k0 f5034c;

    public i0(SecureRandom secureRandom, k0 k0Var) {
        super(secureRandom, k0Var.b().bitLength() - 1);
        this.f5034c = k0Var;
    }

    public k0 c() {
        return this.f5034c;
    }
}
