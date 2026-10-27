package g.a.b.j0;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class w implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f5074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f5075d;

    public w(c0 c0Var, c0 c0Var2) {
        Objects.requireNonNull(c0Var, "staticPublicKey cannot be null");
        Objects.requireNonNull(c0Var2, "ephemeralPublicKey cannot be null");
        if (!c0Var.b().equals(c0Var2.b())) {
            throw new IllegalArgumentException("static and ephemeral public keys have different domain parameters");
        }
        this.f5074c = c0Var;
        this.f5075d = c0Var2;
    }

    public c0 a() {
        return this.f5075d;
    }

    public c0 b() {
        return this.f5074c;
    }
}
