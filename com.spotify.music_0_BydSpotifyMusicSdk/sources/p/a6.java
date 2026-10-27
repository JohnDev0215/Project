package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a6 implements y4 {
    public static final List c = v03.B("data");

    public static z5 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        w5 w5Var = null;
        while (u71Var.W(c) == 0) {
            b6 b6Var = b6.c;
            w1 w1Var = c5.a;
            w5Var = (w5) new wo1(b6Var, true).a(u71Var, cVar);
        }
        v03.h(w5Var);
        return new z5(w5Var);
    }
}
