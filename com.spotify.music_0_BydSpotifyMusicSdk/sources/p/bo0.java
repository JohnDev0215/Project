package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class bo0 implements y4 {
    public static final List c = v03.B("discography");

    public static pn0 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ln0 ln0Var = null;
        while (u71Var.W(c) == 0) {
            xn0 xn0Var = xn0.c;
            w1 w1Var = c5.a;
            ln0Var = (ln0) new wo1(xn0Var, false).a(u71Var, cVar);
        }
        v03.h(ln0Var);
        return new pn0(ln0Var);
    }
}
