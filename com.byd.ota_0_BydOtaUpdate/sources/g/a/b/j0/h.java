package g.a.b.j0;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class h implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k f5028c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f5029d;

    public h(k kVar, k kVar2) {
        Objects.requireNonNull(kVar, "staticPublicKey cannot be null");
        Objects.requireNonNull(kVar2, "ephemeralPublicKey cannot be null");
        if (!kVar.b().equals(kVar2.b())) {
            throw new IllegalArgumentException("Static and ephemeral public keys have different domain parameters");
        }
        this.f5028c = kVar;
        this.f5029d = kVar2;
    }

    public k a() {
        return this.f5029d;
    }

    public k b() {
        return this.f5028c;
    }
}
