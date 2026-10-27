package g.a.b.j0;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class l implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f5038c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f5039d;
    public k s;

    public l(j jVar, j jVar2) {
        this(jVar, jVar2, null);
    }

    public l(j jVar, j jVar2, k kVar) {
        Objects.requireNonNull(jVar, "staticPrivateKey cannot be null");
        Objects.requireNonNull(jVar2, "ephemeralPrivateKey cannot be null");
        i iVarB = jVar.b();
        if (!iVarB.equals(jVar2.b())) {
            throw new IllegalArgumentException("static and ephemeral private keys have different domain parameters");
        }
        if (kVar == null) {
            kVar = new k(iVarB.b().modPow(jVar2.c(), iVarB.f()), iVarB);
        } else if (!iVarB.equals(kVar.b())) {
            throw new IllegalArgumentException("ephemeral public key has different domain parameters");
        }
        this.f5038c = jVar;
        this.f5039d = jVar2;
        this.s = kVar;
    }

    public j a() {
        return this.f5039d;
    }

    public j b() {
        return this.f5038c;
    }
}
