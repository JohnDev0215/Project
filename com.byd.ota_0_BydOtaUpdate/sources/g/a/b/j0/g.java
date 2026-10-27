package g.a.b.j0;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class g implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f5024c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f5025d;
    public k s;

    public g(j jVar, j jVar2) {
        this(jVar, jVar2, null);
    }

    public g(j jVar, j jVar2, k kVar) {
        Objects.requireNonNull(jVar, "staticPrivateKey cannot be null");
        Objects.requireNonNull(jVar2, "ephemeralPrivateKey cannot be null");
        i iVarB = jVar.b();
        if (!iVarB.equals(jVar2.b())) {
            throw new IllegalArgumentException("Static and ephemeral private keys have different domain parameters");
        }
        if (kVar == null) {
            kVar = new k(iVarB.b().multiply(jVar2.c()), iVarB);
        } else if (!iVarB.equals(kVar.b())) {
            throw new IllegalArgumentException("Ephemeral public key has different domain parameters");
        }
        this.f5024c = jVar;
        this.f5025d = jVar2;
        this.s = kVar;
    }

    public j a() {
        return this.f5025d;
    }

    public k b() {
        return this.s;
    }

    public j c() {
        return this.f5024c;
    }
}
