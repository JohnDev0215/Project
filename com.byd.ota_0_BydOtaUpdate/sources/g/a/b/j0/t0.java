package g.a.b.j0;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class t0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b0 f5063c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b0 f5064d;
    public c0 s;

    public t0(b0 b0Var, b0 b0Var2, c0 c0Var) {
        Objects.requireNonNull(b0Var, "staticPrivateKey cannot be null");
        Objects.requireNonNull(b0Var2, "ephemeralPrivateKey cannot be null");
        x xVarB = b0Var.b();
        if (!xVarB.equals(b0Var2.b())) {
            throw new IllegalArgumentException("Static and ephemeral private keys have different domain parameters");
        }
        if (c0Var == null) {
            c0Var = new c0(xVarB.b().y(b0Var2.c()), xVarB);
        } else if (!xVarB.equals(c0Var.b())) {
            throw new IllegalArgumentException("Ephemeral public key has different domain parameters");
        }
        this.f5063c = b0Var;
        this.f5064d = b0Var2;
        this.s = c0Var;
    }

    public b0 a() {
        return this.f5064d;
    }

    public c0 b() {
        return this.s;
    }

    public b0 c() {
        return this.f5063c;
    }
}
