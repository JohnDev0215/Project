package p;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface xv {
    default Object a(Class cls) {
        return f(dz1.a(cls));
    }

    j80 b(dz1 dz1Var);

    yy1 c(dz1 dz1Var);

    default yy1 d(Class cls) {
        return g(dz1.a(cls));
    }

    default Set e(dz1 dz1Var) {
        return (Set) c(dz1Var).get();
    }

    default Object f(dz1 dz1Var) {
        yy1 yy1VarG = g(dz1Var);
        if (yy1VarG == null) {
            return null;
        }
        return yy1VarG.get();
    }

    yy1 g(dz1 dz1Var);
}
