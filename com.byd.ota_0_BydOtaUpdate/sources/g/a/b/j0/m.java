package g.a.b.j0;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class m implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k f5040c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f5041d;

    public m(k kVar, k kVar2) {
        Objects.requireNonNull(kVar, "staticPublicKey cannot be null");
        Objects.requireNonNull(kVar2, "ephemeralPublicKey cannot be null");
        if (!kVar.b().equals(kVar2.b())) {
            throw new IllegalArgumentException("Static and ephemeral public keys have different domain parameters");
        }
        this.f5040c = kVar;
        this.f5041d = kVar2;
    }

    public k a() {
        return this.f5041d;
    }

    public k b() {
        return this.f5040c;
    }
}
