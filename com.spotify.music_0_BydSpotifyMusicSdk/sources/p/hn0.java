package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hn0 implements y4 {
    public static final hn0 c = new hn0();
    public static final List e = v03.B("artistUnion");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        cn0 cn0Var = null;
        while (u71Var.W(e) == 0) {
            gn0 gn0Var = gn0.c;
            w1 w1Var = c5.a;
            cn0Var = (cn0) new wo1(gn0Var, true).a(u71Var, cVar);
        }
        v03.h(cn0Var);
        return new dn0(cn0Var);
    }
}
