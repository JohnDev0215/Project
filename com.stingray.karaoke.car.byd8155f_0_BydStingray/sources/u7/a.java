package U7;

import A7.m;
import Q7.Z;
import Q7.a0;
import Q7.b0;
import Q7.e0;
import Q7.f0;
import o7.C2053e;

/* JADX INFO: loaded from: classes.dex */
public final class a extends f0 {
    public static final a c = new a("package", false);

    @Override // Q7.f0
    public final Integer a(f0 f0Var) {
        m.f(f0Var, "visibility");
        if (this == f0Var) {
            return 0;
        }
        C2053e c2053e = e0.f7184a;
        return (f0Var == Z.c || f0Var == a0.c) ? 1 : -1;
    }

    @Override // Q7.f0
    public final String b() {
        return "public/*package*/";
    }

    @Override // Q7.f0
    public final f0 c() {
        return b0.c;
    }
}
