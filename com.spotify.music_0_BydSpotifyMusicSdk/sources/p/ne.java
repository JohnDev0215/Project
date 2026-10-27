package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ne implements y4 {
    public static final List c = v03.B("data");

    public static me b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ke keVar = null;
        while (u71Var.W(c) == 0) {
            oe oeVar = oe.c;
            w1 w1Var = c5.a;
            keVar = (ke) new wo1(oeVar, true).a(u71Var, cVar);
        }
        v03.h(keVar);
        return new me(keVar);
    }
}
