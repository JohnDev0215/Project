package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ff0 implements y4 {
    public static final List c = v03.B("data");

    public static df0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        af0 af0Var = null;
        while (u71Var.W(c) == 0) {
            ef0 ef0Var = ef0.c;
            w1 w1Var = c5.a;
            af0Var = (af0) new wo1(ef0Var, true).a(u71Var, cVar);
        }
        v03.h(af0Var);
        return new df0(af0Var);
    }
}
