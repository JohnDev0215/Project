package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class d0 extends g.a.b.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f0 f5015c;

    public d0(SecureRandom secureRandom, f0 f0Var) {
        super(secureRandom, d(f0Var));
        this.f5015c = f0Var;
    }

    public static int d(f0 f0Var) {
        return f0Var.b() != 0 ? f0Var.b() : f0Var.c().bitLength();
    }

    public f0 c() {
        return this.f5015c;
    }
}
