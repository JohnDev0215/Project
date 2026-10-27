package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class e extends g.a.b.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i f5018c;

    public e(SecureRandom secureRandom, i iVar) {
        super(secureRandom, d(iVar));
        this.f5018c = iVar;
    }

    public static int d(i iVar) {
        return iVar.d() != 0 ? iVar.d() : iVar.f().bitLength();
    }

    public i c() {
        return this.f5018c;
    }
}
