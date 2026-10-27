package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class o extends g.a.b.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public r f5048c;

    public o(SecureRandom secureRandom, r rVar) {
        super(secureRandom, rVar.b().bitLength() - 1);
        this.f5048c = rVar;
    }

    public r c() {
        return this.f5048c;
    }
}
