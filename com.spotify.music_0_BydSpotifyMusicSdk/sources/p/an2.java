package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class an2 implements y4 {
    public static final an2 c = new an2();
    public static final List e = v03.B("profile");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        tm2 tm2Var = null;
        while (u71Var.W(e) == 0) {
            cn2 cn2Var = cn2.c;
            w1 w1Var = c5.a;
            tm2Var = (tm2) new wo1(cn2Var, false).a(u71Var, cVar);
        }
        v03.h(tm2Var);
        return new rm2(tm2Var);
    }
}
