package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class y extends g.a.b.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public x f5083c;

    public y(x xVar, SecureRandom secureRandom) {
        super(secureRandom, xVar.e().bitLength());
        this.f5083c = xVar;
    }

    public x c() {
        return this.f5083c;
    }
}
